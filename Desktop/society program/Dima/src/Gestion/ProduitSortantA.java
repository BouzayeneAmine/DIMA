package Gestion;

import BDD.Conector;
import BDD.DbConnection;
import BDD.Parametre;
import BDD.ResultSetTableModel;
import com.sun.javafx.print.PrinterJobImpl;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ProduitSortantA extends javax.swing.JInternalFrame {

    ResultSet rs;
    DbConnection db;

    public ProduitSortantA() {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        initComponents();
        removeTitleBar();
        table();
        calcultotal();
    }

    void removeTitleBar() {
        putClientProperty("ProduitSortantA.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }

    public void calcultotal() {
        float c = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            String b = String.valueOf(jTable1.getValueAt(i, 11));
            String money = b.replace(',', '.');
            float d = Float.parseFloat(money);
            c = c + d;
        }
        jLabel3.setText(Float.toString(c));
        DecimalFormat df = new DecimalFormat("0");
        df.setRoundingMode(RoundingMode.DOWN);
        float bb = (Float.parseFloat(jLabel3.getText()));
        String money = df.format(bb).replace(',', '.');
        jLabel3.setText(money);
    }

    public void table() {
        String a[] = {"Id", "Numero", "Code_produit", "code_type", "Immatricule", "Date_entree", "Heure_entree", "Premier_pesage", "Date_sortie", "Heure_sortie", "Deuxieme_pesage", "Masse_nette"};
        rs = db.querySelect(a, "produit_sortant");
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    public void table10() {
        String a[] = {"Id", "Code", "Designation", "Lot", "Prix", "Coefficient"};
        rs = db.querySelect(a, "produit");
        jTable10.setModel(new ResultSetTableModel(rs));
    }

    public void table11() {
        String a[] = {"Id", "Nom", "Code", "Adresse"};
        rs = db.querySelect(a, "transporteur");
        jTable11.setModel(new ResultSetTableModel(rs));
    }

    public void table50() {
        String a[] = {"Id", "Nom", "Code", "Adresse"};
        rs = db.querySelect(a, "fournisseur");
        jTable50.setModel(new ResultSetTableModel(rs));
    }

    public void table60() {
        String a[] = {"Id", "Nom", "Code", "Adresse"};
        rs = db.querySelect(a, "client");
        jTable60.setModel(new ResultSetTableModel(rs));
    }

    public void table30() {
        String a[] = {"Id", "Type", "Code_Type", "Nom_Type", "Adresse_Type", "Code_Produit", "Designation_Produit", "Totale", "Date_debut", "Date_fin"};
        rs = db.querySelect(a, "rapport_produit_sortant");
        jTable30.setModel(new ResultSetTableModel(rs));
    }

    public void table40() {
        String a[] = {"Id", "Code_type", "Nom_type", "Adresse_type", "Produit", "Designation_produit", "Immatricule", "Date_premier", "Premier_pesage", "Date_deuxieme", "Deuxieme_pesage", "Masse_nette", "Totale", "Date_debut", "Date_fin"};
        rs = db.querySelect(a, "mv_produit_sortant");
        jTable40.setModel(new ResultSetTableModel(rs));
    }

    public void table70() {
        String a[] = {"Id", "Type", "Immatricule", "Tare"};
        rs = db.querySelectOrdre(a, "camion", "Immatricule");
        jTable20.setModel(new ResultSetTableModel(rs));
    }

    public void toExcel(JTable table, File file) {
        try {
            TableModel model = table.getModel();
            FileWriter excel = new FileWriter(file);
            for (int i = 1; i < model.getColumnCount(); i++) {
                excel.write(model.getColumnName(i) + "\t");
            }
            excel.write("\n");
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 1; j < model.getColumnCount(); j++) {
                    excel.write(model.getValueAt(i, j).toString() + "\t");
                }
                excel.write("\n");
            }
            excel.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void toPDF2() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("RapportProduitSortant.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(ProgrammeUn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toPDF3() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("MvProduitSortant.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(ProgrammeUn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toPDF4() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("RapportProduitSortantTotale.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(ProgrammeUn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable30 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable40 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable41 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable20 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable50 = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable60 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        a = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        de = new com.toedter.calendar.JDateChooser();
        jComboBox3 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton7 = new javax.swing.JButton();

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable11);

        jTable30.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable30);

        jTable40.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable40);

        jTable41.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(jTable41);

        jTable20.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(jTable20);

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(jTable10);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(jTable2);

        jTable50.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(jTable50);

        jTable60.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(jTable60);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel13.setBackground(new java.awt.Color(0, 51, 102));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Liste des produits sortantes");
        jLabel13.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        a.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        a.setDateFormatString("yyyy-MM-dd");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        de.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        de.setDateFormatString("yyyy-MM-dd");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Code de produit", "Numero", "Code Type", "Immatricule Camion", "Masse Nette" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search-engine.png"))); // NOI18N
        jButton1.setText("Chercher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("De");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/export.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Code de produit", "Numero", "Code Type", "Immatricule Camion", "Masse Nette" }));

        jLabel2.setText("A");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Date d entrée", "Date  de sorite" }));

        jLabel3.setFont(new java.awt.Font("HP Simplified", 2, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel4.setText("Total");

        jLabel5.setText("KG");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/relever.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rapport.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rapportDet.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(de, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(a, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox3, 0, 258, Short.MAX_VALUE)
                            .addComponent(jTextField2))
                        .addGap(6, 6, 6)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(41, 41, 41))
                            .addComponent(jLabel2))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(de, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(26, 26, 26))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(16, 16, 16))))))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MessageFormat header = new MessageFormat("Liste des produits sortantes");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
       
        try {
            jTable1.print(JTable.PrintMode.FIT_WIDTH, header, footer);

        } catch (java.awt.print.PrinterException e) {
            System.err.format("Erreur d'impression ", e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String deb = "2016-01-01";
        String fin = "2080-01-01";
        String text = "Date_entree";

        if (de.getCalendar() == null || a.getCalendar() == null) {
            deb = "2016-01-01";
            fin = "2080-01-01";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            deb = sdf.format(de.getDate());
            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            fin = sd.format(a.getDate());
        }
        if (jComboBox1.getSelectedItem().equals("Date d entrée")) {
            text = "Date_entree";
        } else if (jComboBox1.getSelectedItem().equals("Date  de sorite")) {
            text = "Date_sortie";
        }

        if (jTextField1.getText().equals("") && jTextField2.getText().equals("")) {
            rs = db.querySearchAll("produit_sortant", text, deb, fin);
        } else {
            String text1 = "Numero";
            String text2 = "Numero ";
            if (jComboBox2.getSelectedItem().equals("Numero")) {
                text1 = "Numero";
            } else if (jComboBox2.getSelectedItem().equals("Code Type")) {
                text1 = "code_type";
            } else if (jComboBox2.getSelectedItem().equals("Code de produit")) {
                text1 = "Code_produit";
            } else if (jComboBox2.getSelectedItem().equals("Immatricule Camion")) {
                text1 = "Immatricule";
            } else if (jComboBox2.getSelectedItem().equals("Masse Nette")) {
                text1 = "Masse_nette";
            }
            if (jComboBox3.getSelectedItem().equals("Numero")) {
                text2 = "Numero ";
            } else if (jComboBox3.getSelectedItem().equals("Code Type")) {
                text2 = "code_type ";
            } else if (jComboBox3.getSelectedItem().equals("Code de produit")) {
                text2 = "Code_produit ";
            } else if (jComboBox3.getSelectedItem().equals("Immatricule Camion")) {
                text2 = "Immatricule ";
            } else if (jComboBox3.getSelectedItem().equals("Masse Nette")) {
                text2 = "Masse_nette ";
            }
            rs = db.querySelectTwoALL("produit_sortant", text1 + "='" + jTextField1.getText() + "'", text2 + "LIKE '%" + jTextField2.getText() + "%' ", text, deb, fin);
        }
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        calcultotal();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(ProduitSortantA.this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            String path = fc.getSelectedFile().getParentFile().getPath();
            int len = filename.length();
            String ext = "";
            String file = "";

            if (len > 4) {
                ext = filename.substring(len - 4, len);
            }
            if (ext.equals(".xls")) {
                file = path + "\\" + filename;
            } else {
                file = path + "\\" + filename + ".xls";
            }
            toExcel(jTable1, new File(file));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (jTextField1.getText().equals("") || jTextField2.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir : \n 1 -le code produit dans le champ de texte à gauche  \n 2- le code client/fournisseur/transporteur dans le champ de texte à droite", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String deb = "2016-01-01";
            String fin = "2080-01-01";
            String text = "Date_entree";
            String AdresseType = "";
            String NomType = "";
            String id;
            int idd = 0;
            boolean testType = true;
            
            table11();
            table10();

            if (de.getCalendar() == null || a.getCalendar() == null) {
                deb = "2016-01-01";
                fin = "2080-01-01";
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
                deb = sdf.format(de.getDate());
                SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
                fin = sd.format(a.getDate());
            }

            if (jComboBox1.getSelectedItem().equals("Date d entrée")) {
                text = "Date_entree";
            } else if (jComboBox1.getSelectedItem().equals("Date  de sorite")) {
                text = "Date_sortie";
            }

            rs = db.querySelectAll("client", "Code='" + jTextField2.getText() + "'");
            jTable2.setModel(new ResultSetTableModel(rs));
            if (String.valueOf(jTable2.getValueAt(0, 1)).equals("null")) {
                rs = db.querySelectAll("fournisseur", "Code='" + jTextField2.getText() + "'");
                jTable2.setModel(new ResultSetTableModel(rs));
                if (String.valueOf(jTable2.getValueAt(0, 1)).equals("null")) {
                    rs = db.querySelectAll("transporteur", "Code='" + jTextField2.getText() + "'");
                    jTable2.setModel(new ResultSetTableModel(rs));
                    if (String.valueOf(jTable2.getValueAt(0, 1)).equals("null")) {
                        testType = false;
                    } else {
                        NomType = String.valueOf(jTable2.getValueAt(0, 1));
                        AdresseType = String.valueOf(jTable2.getValueAt(0, 3));
                    }
                } else {
                    NomType = String.valueOf(jTable2.getValueAt(0, 1));
                    AdresseType = String.valueOf(jTable2.getValueAt(0, 3));
                }
            } else {
                NomType = String.valueOf(jTable2.getValueAt(0, 1));
                AdresseType = String.valueOf(jTable2.getValueAt(0, 3));
            }

            rs = db.querySelectAll("produit", "Code='" + jTextField1.getText() + "'");
            jTable41.setModel(new ResultSetTableModel(rs));
            String DesignationProduit = String.valueOf(jTable41.getValueAt(0, 2));
            if (testType == true) {
                // System.out.println("Code produit: " + CodeProduit);
                rs = db.querySelectTwoALL("produit_sortant", "code_type='" + jTextField2.getText() + "'", "Code_produit='" + jTextField1.getText() + "'", text, deb, fin);
                jTable20.setModel(new ResultSetTableModel(rs));
                String test = String.valueOf(jTable20.getValueAt(0, 1));
                if (test.equals("null")) {
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(this, "Vérifiez que : \n 1- Ce code client/fournisseur/transporteur n éxiste pas \n 2- Ce code produit n éxiste pas \n 3- Ce client/fournisseur/transporteur n a rien eu de ce produit", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    jProgressBar1.setMaximum(jTable20.getRowCount() - 1);
                    db.queryDelete("mv_produit_sortant");
                    float c = 0;
                    for (int k = 0; k < jTable20.getRowCount(); k++) {
                        String b = String.valueOf(jTable20.getValueAt(k, 11)).replace(',', '.');
                        float d = Float.parseFloat(b);
                        c = c + d;
                    }
                    DecimalFormat df = new DecimalFormat("0");
                    df.setRoundingMode(RoundingMode.DOWN);
                    String Totale = df.format(c).replace(',', '.');

                    //System.out.println("Totale " + Totale);
                    for (int j = 0; j < jTable20.getRowCount(); j++) {
                        String Immatricul = String.valueOf(jTable20.getValueAt(j, 4));
                        String datePremier = String.valueOf(jTable20.getValueAt(j, 5));
                        String heurePremier = String.valueOf(jTable20.getValueAt(j, 6));
                        String premierPesage = String.valueOf(jTable20.getValueAt(j, 7));
                        String dateDeuxieme = String.valueOf(jTable20.getValueAt(j, 8));
                        String heureDeuxieme = String.valueOf(jTable20.getValueAt(j, 9));
                        String deuxiemePesage = String.valueOf(jTable20.getValueAt(j, 10));
                        String masse = String.valueOf(jTable20.getValueAt(j, 11));
                        idd = idd + 1;
                        id = String.valueOf(idd);
                        String[] colon = {"Id", "Code_type", "Nom_type", "Adresse_type", "Produit", "Designation_produit", "Immatricule", "Date_premier", "Premier_pesage", "Date_deuxieme", "Deuxieme_pesage", "Masse_nette", "Totale", "Date_debut", "Date_fin"};
                        String[] inf = {id, jTextField1.getText(), NomType, AdresseType, jTextField2.getText(), DesignationProduit, Immatricul, datePremier + " " + heurePremier, premierPesage, dateDeuxieme + " " + heureDeuxieme, deuxiemePesage, masse, Totale, deb, fin};
                        System.out.println(db.queryInsert("mv_produit_sortant", colon, inf));
                        jProgressBar1.setValue(j);
                    }
                    toPDF3();
                }
            } else {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Ce code n est ni un code client, ni un code fournisseur et ni un code transporteur !", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String deb = "2016-01-01";
        String fin = "2080-01-01";
        String text = "Date_entree";
        String id;
        int idd = 0;

        if (de.getCalendar() == null || a.getCalendar() == null) {
            deb = "2016-01-01";
            fin = "2080-01-01";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            deb = sdf.format(de.getDate());
            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            fin = sd.format(a.getDate());
        }

        if (jComboBox1.getSelectedItem().equals("Date d entrée")) {
            text = "Date_entree";
        } else if (jComboBox1.getSelectedItem().equals("Date  de sorite")) {
            text = "Date_sortie";
        }

        table11();
        table10();
        table50();
        table60();

        db.queryDelete("rapport_produit_sortant");
        jProgressBar1.setMaximum(jTable10.getRowCount() - 1);
        for (int i = 0; i < jTable10.getRowCount(); i++) {
            String CodeProduit = String.valueOf(jTable10.getValueAt(i, 1));
            String DesProduit = String.valueOf(jTable10.getValueAt(i, 2));
            // System.out.println("Code produit: " + CodeProduit);
            String Type = "Transporteur";
            for (int j = 0; j < jTable11.getRowCount(); j++) {

                String CodeType = String.valueOf(jTable11.getValueAt(j, 2));
                //System.out.println("Code Transporteur: " + CodeType);
                String NomType = String.valueOf(jTable11.getValueAt(j, 1));
                // System.out.println("Nom Transporteur: " + NomFournisseur);
                String AdresseType = String.valueOf(jTable11.getValueAt(j, 3));
                //System.out.println("TVA: " + TvaFournisseur);
                // System.out.println("Code produit: " + CodeProduit);
                rs = db.querySelectTwoALL("produit_sortant", "code_type='" + CodeType + "'", "Code_produit='" + CodeProduit + "'", text, deb, fin);
                jTable20.setModel(new ResultSetTableModel(rs));

                float c = 0;
                for (int k = 0; k < jTable20.getRowCount(); k++) {
                    String b = String.valueOf(jTable20.getValueAt(k, 11)).replace(',', '.');
                    float d = Float.parseFloat(b);
                    c = c + d;
                }
                DecimalFormat df = new DecimalFormat("0");
                df.setRoundingMode(RoundingMode.DOWN);
                String Totale = df.format(c).replace(',', '.');
                //System.out.println("Totale " + Totale);

                if (Integer.parseInt(Totale) != 0) {
                    idd = idd + 1;
                    id = String.valueOf(idd);
                    String[] colon = {"Id", "Type", "Code_Type", "Nom_Type", "Adresse_Type", "Code_Produit", "Designation_Produit", "Totale", "Date_debut", "Date_fin"};
                    String[] inf = {id, Type, CodeType, NomType, AdresseType, CodeProduit, DesProduit, Totale, deb, fin};
                    System.out.println(db.queryInsert("rapport_produit_sortant", colon, inf));
                    DesProduit = "#######";
                }
            }
            Type = "Fournisseur";
            for (int z = 0; z < jTable50.getRowCount(); z++) {

                String CodeType = String.valueOf(jTable50.getValueAt(z, 2));
                //System.out.println("Code Fournisseur: " + CodeType);
                String NomType = String.valueOf(jTable50.getValueAt(z, 1));
                //System.out.println("Nom Fournisseur: " + NomFournisseur);
                String AdresseType = String.valueOf(jTable50.getValueAt(z, 3));
                //System.out.println("TVA: " + TvaFournisseur);
                // System.out.println("Code produit: " + CodeProduit);
                rs = db.querySelectTwoALL("produit_sortant", "code_type='" + CodeType + "'", "Code_produit='" + CodeProduit + "'", text, deb, fin);
                jTable20.setModel(new ResultSetTableModel(rs));

                float c = 0;
                for (int k = 0; k < jTable20.getRowCount(); k++) {
                    String b = String.valueOf(jTable20.getValueAt(k, 11)).replace(',', '.');
                    float d = Float.parseFloat(b);
                    c = c + d;
                }
                DecimalFormat df = new DecimalFormat("0");
                df.setRoundingMode(RoundingMode.DOWN);
                String Totale = df.format(c).replace(',', '.');
                //System.out.println("Totale " + Totale);

                if (Integer.parseInt(Totale) != 0) {
                    idd = idd + 1;
                    id = String.valueOf(idd);
                    String[] colon = {"Id", "Type", "Code_Type", "Nom_Type", "Adresse_Type", "Code_Produit", "Designation_Produit", "Totale", "Date_debut", "Date_fin"};
                    String[] inf = {id, Type, CodeType, NomType, AdresseType, CodeProduit, DesProduit, Totale, deb, fin};
                    System.out.println(db.queryInsert("rapport_produit_sortant", colon, inf));
                    DesProduit = "#######";
                }
            }
            Type = "Client";
            for (int w = 0; w < jTable60.getRowCount(); w++) {

                String CodeType = String.valueOf(jTable60.getValueAt(w, 2));
                //System.out.println("Code Client: " + CodeType);
                String NomType = String.valueOf(jTable60.getValueAt(w, 1));
                //System.out.println("Nom Fournisseur: " + NomFournisseur);
                String AdresseType = String.valueOf(jTable60.getValueAt(w, 3));
                //System.out.println("TVA: " + TvaFournisseur);
                // System.out.println("Code produit: " + CodeProduit);
                rs = db.querySelectTwoALL("produit_sortant", "code_type='" + CodeType + "'", "Code_produit='" + CodeProduit + "'", text, deb, fin);
                jTable20.setModel(new ResultSetTableModel(rs));

                float c = 0;
                for (int k = 0; k < jTable20.getRowCount(); k++) {
                    String b = String.valueOf(jTable20.getValueAt(k, 11)).replace(',', '.');
                    float d = Float.parseFloat(b);
                    c = c + d;
                }
                DecimalFormat df = new DecimalFormat("0");
                df.setRoundingMode(RoundingMode.DOWN);
                String Totale = df.format(c).replace(',', '.');
                //System.out.println("Totale " + Totale);

                if (Integer.parseInt(Totale) != 0) {
                    idd = idd + 1;
                    id = String.valueOf(idd);
                    String[] colon = {"Id", "Type", "Code_Type", "Nom_Type", "Adresse_Type", "Code_Produit", "Designation_Produit", "Totale", "Date_debut", "Date_fin"};
                    String[] inf = {id, Type, CodeType, NomType, AdresseType, CodeProduit, DesProduit, Totale, deb, fin};
                    System.out.println(db.queryInsert("rapport_produit_sortant", colon, inf));
                    DesProduit = "#######";
                }
            }
            jProgressBar1.setValue(i);

        }
        toPDF2();

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String deb = "2016-01-01";
        String fin = "2080-01-01";
        String text = "Date_entree";
        String id;
        int idd = 0;

        if (de.getCalendar() == null || a.getCalendar() == null) {
            deb = "2016-01-01";
            fin = "2080-01-01";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            deb = sdf.format(de.getDate());
            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            fin = sd.format(a.getDate());
        }

        if (jComboBox1.getSelectedItem().equals("Date d entrée")) {
            text = "Date_entree";
        } else if (jComboBox1.getSelectedItem().equals("Date  de sorite")) {
            text = "Date_sortie";
        }

        table10();

        db.queryDelete("rapport_produit_sortant_totale");

        jProgressBar1.setMaximum(jTable10.getRowCount() - 1);

        for (int i = 0; i < jTable10.getRowCount(); i++) {
            String CodeProduit = String.valueOf(jTable10.getValueAt(i, 1));
            String DesProduit = String.valueOf(jTable10.getValueAt(i, 2));

            rs = db.querySelectTwoOne("produit_sortant", "Code_produit='" + CodeProduit + "'", text, deb, fin);
            jTable20.setModel(new ResultSetTableModel(rs));

            float c = 0;
            for (int k = 0; k < jTable20.getRowCount(); k++) {
                String b = String.valueOf(jTable20.getValueAt(k, 11)).replace(',', '.');
                float d = Float.parseFloat(b);
                c = c + d;
            }
            DecimalFormat df = new DecimalFormat("0");
            df.setRoundingMode(RoundingMode.DOWN);
            String Totale = df.format(c).replace(',', '.');
            //System.out.println("Totale " + Totale);
            if (c != 0) {
                idd = idd + 1;
                id = String.valueOf(idd);
                String[] colon = {"Id", "Code_Produit", "Designation_Produit", "Totale", "Date_debut", "Date_fin"};
                String[] inf = {id, CodeProduit, DesProduit, Totale, deb, fin};
                System.out.println(db.queryInsert("rapport_produit_sortant_totale", colon, inf));
            }
        }
        toPDF4();

    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser a;
    private com.toedter.calendar.JDateChooser de;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable20;
    private javax.swing.JTable jTable30;
    private javax.swing.JTable jTable40;
    private javax.swing.JTable jTable41;
    private javax.swing.JTable jTable50;
    private javax.swing.JTable jTable60;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}

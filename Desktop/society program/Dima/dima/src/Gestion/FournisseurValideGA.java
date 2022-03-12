package Gestion;

import BDD.Conector;
import BDD.ConnectComPort;
import BDD.DbConnection;
import BDD.Parametre;
import BDD.ResultSetTableModel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class FournisseurValideGA extends javax.swing.JInternalFrame {

    ConnectComPort dc;
    ResultSet rs;
    DbConnection db;

    public FournisseurValideGA() {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        dc = new ConnectComPort();
        initComponents();
        removeTitleBar();
        table();
        table0();
        calcultotal();
        Imprimante.setText(String.valueOf(jTable0.getValueAt(0, 1)));
    }

    void removeTitleBar() {
        putClientProperty("FournisseurValideA.isPalette", Boolean.TRUE);
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
        String a[] = {"Id", "Numero", "Code", "Produit", "Immatricule", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
        rs = db.querySelect(a, "fournisseur_valide");
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    public void table90() {
        String b[] = {"Id", "Societe", "Adresse", "TEL", "Fax"};
        rs = db.querySelect(b, "cordonee");
        jTable90.setModel(new ResultSetTableModel(rs));
        txnomSoc.setText(String.valueOf(jTable90.getValueAt(0, 1)));
        txAdresseSoc.setText(String.valueOf(jTable90.getValueAt(0, 2)));
        txTelSoc.setText(String.valueOf(jTable90.getValueAt(0, 3)));
        txFaxSoc.setText(String.valueOf(jTable90.getValueAt(0, 4)));
    }

    public void table0() {
        String b[] = {"ID", "Type"};
        rs = dc.querySelect(b, "imprimante");
        jTable0.setModel(DbUtils.resultSetToTableModel(rs));
    }

    public void tableProduit() {
        String a[] = {"Id", "Code", "Designation", "Lot", "Prix", "Coefficient"};
        rs = db.querySelect(a, "produit");
        jProduit.setModel(new ResultSetTableModel(rs));
    }

    public void tableFournisseur() {
        String a[] = {"Id", "Nom", "Code", "Adresse"};
        rs = db.querySelect(a, "fournisseur");
        jFournisseur.setModel(new ResultSetTableModel(rs));
    }
 public void toPDFA7() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
           // InputStream path = this.getClass().getResourceAsStream("BonDeSortieA7.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport("C:\\format\\BondeA7.jrxml");
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(ProgrammeUn.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public static String getComputerFullName() {
        String hostName = null;
        try {
            final InetAddress addr = InetAddress.getLocalHost();
            hostName = new String(addr.getHostName());
        } catch (final Exception e) {
        }//end try
        return hostName;
    }

    public void toPDFA4() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("BonDeSortieA4.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(ProgrammeUn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toPDFA5() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("BonDeSortieA5.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(ProgrammeUn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toPDFMatA4() {
        Conector cn = new Conector();
        byte[] report = null;
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("BonDeSortieMatA4.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);

            JasperExportManager.exportReportToPdfFile(jasperPrint, "BonFournisseurValide.pdf");
            File file = new File("BonFournisseurValide.pdf");
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }

        } catch (JRException ex) {
            Logger.getLogger(ProgrammeUn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FournisseurValideGA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void toPDFMatA5() {
        Conector cn = new Conector();
        byte[] report = null;
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("BonDeSortieMatA5.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);

            JasperExportManager.exportReportToPdfFile(jasperPrint, "BonFournisseurValide.pdf");
            File file = new File("BonFournisseurValide.pdf");
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }

        } catch (JRException ex) {
            Logger.getLogger(ProgrammeUn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FournisseurValideGA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void toPDF2() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("RapportFournisseur.jrxml");
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
            InputStream path = this.getClass().getResourceAsStream("MvFournisseur.jrxml");
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jProduit = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jFournisseur = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable41 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable20 = new javax.swing.JTable();
        Imprimante = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable0 = new javax.swing.JTable();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable90 = new javax.swing.JTable();
        txnomSoc = new javax.swing.JTextField();
        txAdresseSoc = new javax.swing.JTextField();
        txTelSoc = new javax.swing.JTextField();
        txFaxSoc = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        de = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        a = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton7 = new javax.swing.JButton();

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
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable4);

        jProduit.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jProduit);

        jFournisseur.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jFournisseur);

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
        jScrollPane9.setViewportView(jTable41);

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
        jScrollPane10.setViewportView(jTable20);

        jTable0.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane11.setViewportView(jTable0);

        jTable90.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane15.setViewportView(jTable90);

        jTable1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
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
        jLabel13.setText("Liste des fournisseurs valides");
        jLabel13.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Numero", "Code Fournisseur", "Code de produit", "Immatricule Camion", "Masse Nette" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Numero", "Code Fournisseur", "Code de produit", "Immatricule Camion", "Masse Nette" }));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Date d entrée", "Date  de sorite" }));

        de.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        de.setDateFormatString("yyyy-MM-dd");

        jLabel1.setText("De");

        jLabel2.setText("A");

        a.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        a.setDateFormatString("yyyy-MM-dd");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/export.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search-engine.png"))); // NOI18N
        jButton1.setText("Chercher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reimprimer.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setText("Total");

        jLabel3.setFont(new java.awt.Font("HP Simplified", 2, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel5.setText("KG");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/relever.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rapport.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jProgressBar1.setStringPainted(true);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(de, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jComboBox2, 0, 206, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2)
                            .addComponent(jComboBox3, 0, 221, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(de, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5)
                                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(22, 22, 22))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(48, 48, 48)
                                            .addComponent(jLabel5)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(25, 25, 25))))
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(41, 41, 41))
                            .addComponent(jLabel2))))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      String text = "Date";
        String text1 = "Numero";
        String text2 = "Numero ";
        String deb = "";
        String fin = "";
        if (jComboBox1.getSelectedItem().equals("Date d entrée")) {
            text = "Date_premier";
        } else if (jComboBox1.getSelectedItem().equals("Date  de sorite")) {
            text = "Date_deuxieme";
        }

        if (de.getCalendar() == null || a.getCalendar() == null) {
            deb = "2016-01-01";
            fin = "2080-01-01";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            deb = sdf.format(de.getDate());
            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            fin = sd.format(a.getDate());
        }

        if (jTextField1.getText().equals("") && jTextField2.getText().equals("")) {
            rs = db.querySearchAll("fournisseur_valide", text, deb, fin);
        } else {

            if (jComboBox2.getSelectedItem().equals("Numero")) {
                text1 = "Numero";
            } else if (jComboBox2.getSelectedItem().equals("Code Fournisseur")) {
                text1 = "Code";
            } else if (jComboBox2.getSelectedItem().equals("Code de produit")) {
                text1 = "Produit";
            } else if (jComboBox2.getSelectedItem().equals("Immatricule Camion")) {
                text1 = "Immatricule";
            } else if (jComboBox2.getSelectedItem().equals("Masse Nette")) {
                text1 = "Masse_nette";
            }

            if (jComboBox3.getSelectedItem().equals("Numero")) {
                text2 = "Numero ";
            } else if (jComboBox3.getSelectedItem().equals("Code Fournisseur")) {
                text2 = "Code ";
            } else if (jComboBox3.getSelectedItem().equals("Code de produit")) {
                text2 = "Produit ";
            } else if (jComboBox3.getSelectedItem().equals("Immatricule Camion")) {
                text2 = "Immatricule ";
            } else if (jComboBox3.getSelectedItem().equals("Masse Nette")) {
                text2 = "Masse_nette ";
            }

            rs = db.querySelectTwoALL("fournisseur_valide", text1 + "='" + jTextField1.getText() + "'", text2 + "LIKE '%" + jTextField2.getText() + "%' ", text, deb, fin);
        }
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        calcultotal();
        
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(FournisseurValideGA.this);
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MessageFormat header = new MessageFormat("Liste des fournisseurs valides");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            jTable1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Erreur d'impression ", e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String Numero = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        if (Numero.equals("null")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez selectionner un fournisseur pour réimprimer le bon", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String Code = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2));
            String Produit = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3));
            String Immatricule = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4));
            String Date_premier = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 5));
            String Heure_premier = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 6));
            String Premier_pesage = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 7));
            String Date_deuxieme = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 8));
            String Heure_deuxieme = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 9));
            String Deuxieme_pesage = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 10));
            String Masse_nette = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 11));

            rs = db.querySelectAll("fournisseur", "Code='" + Code + "'");
            jTable2.setModel(new ResultSetTableModel(rs));
            String clientNom = String.valueOf(jTable2.getValueAt(0, 1));
            String clientAdress = String.valueOf(jTable2.getValueAt(0, 3));

            rs = db.querySelectAll("produit", "Code='" + Produit + "'");
            jTable3.setModel(new ResultSetTableModel(rs));
            String produitDesignation = String.valueOf(jTable3.getValueAt(0, 2));
            String produitLot = String.valueOf(jTable3.getValueAt(0, 3));
            String produitPrix = String.valueOf(jTable3.getValueAt(0, 4));
            String prouitCoefficient = String.valueOf(jTable3.getValueAt(0, 5));

            rs = db.querySelectAll("camion", "Immatricule='" + Immatricule + "'");
            jTable4.setModel(new ResultSetTableModel(rs));
            String CamType = String.valueOf(jTable4.getValueAt(0, 1));
            String CamTare = String.valueOf(jTable4.getValueAt(0, 3));
            if (Imprimante.getText().equals("Normale A4")) {
                String[] colon3 = {"Numero", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                    "Lot_produit", "Prix_produit", "Coefficient_produit", "Camion_Immatricule", "Camion_type", "Camion_tare",
                    "Premier_pesage", "Premier_date", "Premier_Heure", "Deuxieme_pesage", "Deuxieme_date", "Deuxieme_heure", "Masse_nette"};
                String[] inf3 = {Numero, "Fournisseur", Code, clientNom, clientAdress, Produit, produitDesignation, produitLot, produitPrix, prouitCoefficient,
                    Immatricule, CamType, CamTare, Premier_pesage, Date_premier, Heure_premier, Deuxieme_pesage, Date_deuxieme, Heure_deuxieme, Masse_nette};
                System.out.println(db.queryUpdate("impression_bon", colon3, inf3, "id='" + 0 + "'"));
                toPDFA4();

            } else if (Imprimante.getText().equals("Normale A7")) {
                String[] colon3 = {"Numero", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                    "Lot_produit", "Prix_produit", "Coefficient_produit", "Camion_Immatricule", "Camion_type", "Camion_tare",
                    "Premier_pesage", "Premier_date", "Premier_Heure", "Deuxieme_pesage", "Deuxieme_date", "Deuxieme_heure", "Masse_nette"};
                String[] inf3 = {Numero, "Fournisseur", Code, clientNom, clientAdress, Produit, produitDesignation, produitLot, produitPrix, prouitCoefficient,
                    Immatricule, CamType, CamTare, Premier_pesage, Date_premier, Heure_premier, Deuxieme_pesage, Date_deuxieme, Heure_deuxieme, Masse_nette};
                System.out.println(db.queryUpdate("impression_bon", colon3, inf3, "id='" + 0 + "'"));
                toPDFA7();

            } else if (Imprimante.getText().equals("Matricielle A4")) {
                String[] colon3 = {"Numero", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                    "Lot_produit", "Prix_produit", "Coefficient_produit", "Camion_Immatricule", "Camion_type", "Camion_tare",
                    "Premier_pesage", "Premier_date", "Premier_Heure", "Deuxieme_pesage", "Deuxieme_date", "Deuxieme_heure", "Masse_nette"};
                String[] inf3 = {Numero, "Fournisseur", Code, clientNom, clientAdress, Produit, produitDesignation, produitLot, produitPrix, prouitCoefficient,
                    Immatricule, CamType, CamTare, Premier_pesage, Date_premier, Heure_premier, Deuxieme_pesage, Date_deuxieme, Heure_deuxieme, Masse_nette};
                System.out.println(db.queryUpdate("impression_bon", colon3, inf3, "id='" + 0 + "'"));
                toPDFMatA4();
            } else if (Imprimante.getText().equals("Matricielle A5")) {
                String[] colon3 = {"Numero", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                    "Lot_produit", "Prix_produit", "Coefficient_produit", "Camion_Immatricule", "Camion_type", "Camion_tare",
                    "Premier_pesage", "Premier_date", "Premier_Heure", "Deuxieme_pesage", "Deuxieme_date", "Deuxieme_heure", "Masse_nette"};
                String[] inf3 = {Numero, "Fournisseur", Code, clientNom, clientAdress, Produit, produitDesignation, produitLot, produitPrix, prouitCoefficient,
                    Immatricule, CamType, CamTare, Premier_pesage, Date_premier, Heure_premier, Deuxieme_pesage, Date_deuxieme, Heure_deuxieme, Masse_nette};
                System.out.println(db.queryUpdate("impression_bon", colon3, inf3, "id='" + 0 + "'"));
                toPDFMatA5();
            } else if (Imprimante.getText().equals("Ticket")) {
                table90();
                Ticket ticket = new Ticket(txnomSoc.getText(), txAdresseSoc.getText(), txTelSoc.getText(), txFaxSoc.getText(), Numero,
                        "Fournisseur", Code, clientNom, clientAdress, Produit, produitDesignation, produitLot, produitPrix, prouitCoefficient, Immatricule, CamType,
                        CamTare, Premier_pesage, Date_premier, Heure_premier, Deuxieme_pesage, Date_deuxieme, Heure_deuxieme, Masse_nette);
                ticket.print();
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       if (jTextField1.getText().equals("") || jTextField2.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir : \n 1 - le code fournisseur dans le champ de texte à gauche \n 2- le code produit dans le champ de texte à droite", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String deb = "2016-01-01";
            String fin = "2080-01-01";
            String text = "Date_premier";
            String id;
            int idd = 0;
            tableFournisseur();
            tableProduit();

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
                text = "Date_premier";
            } else if (jComboBox1.getSelectedItem().equals("Date  de sorite")) {
                text = "Date_deuxieme";
            }

            // System.out.println("Code produit: " + CodeProduit);
            rs = db.querySelectTwoALL("fournisseur_valide", "Code='" + jTextField1.getText() + "'", "Produit='" + jTextField2.getText() + "'", text, deb, fin);
            TableModel jTable3 = new ResultSetTableModel(rs);
            String test = String.valueOf(jTable3.getValueAt(0, 1));

            if (test.equals("null")) {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Vérifiez que : \n 1- Ce code fournisseur n éxiste pas \n 2- Ce code produit n éxiste pas \n 3- Ce fournisseur n a rien eu de ce produit", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                jProgressBar1.setMaximum(jTable3.getRowCount() - 1);
                
                db.queryDelete("mv_fournisseur");
                
                rs = db.querySelectAll("fournisseur", "Code='" + jTextField1.getText() + "'");
                TableModel jTable1 = new ResultSetTableModel(rs);
                String NomClient = String.valueOf(jTable1.getValueAt(0, 1));
                String AdresseClient = String.valueOf(jTable1.getValueAt(0, 3));

                rs = db.querySelectAll("produit", "Code='" + jTextField2.getText() + "'");
                TableModel jTable2 = new ResultSetTableModel(rs);
                String DesignationProduit = String.valueOf(jTable2.getValueAt(0, 2));

                float c = 0;
                float v = 0;
                for (int k = 0; k < jTable3.getRowCount(); k++) {
                    String b = String.valueOf(jTable3.getValueAt(k, 11)).replace(',', '.');
                    String premier = String.valueOf(jTable3.getValueAt(k, 7)).replace(',', '.');
                    String deuxieme = String.valueOf(jTable3.getValueAt(k, 10)).replace(',', '.');
                    float dPremier = Float.parseFloat(premier);
                    float dDeuxieme = Float.parseFloat(deuxieme);
                    float d = Float.parseFloat(b);
                    if (dPremier > dDeuxieme) {
                        c = c + d;
                    } else {
                        v = v + d;
                    }
                }
                DecimalFormat df = new DecimalFormat("0");
                df.setRoundingMode(RoundingMode.DOWN);
                String TotaleEntrer = df.format(c).replace(',', '.');
                String TotaleSortie = df.format(v).replace(',', '.');

                for (int j = 0; j < jTable3.getRowCount(); j++) {
                    String Immatricul = String.valueOf(jTable3.getValueAt(j, 4));
                    String datePremier = String.valueOf(jTable3.getValueAt(j, 5));
                    String heurePremier = String.valueOf(jTable3.getValueAt(j, 6));
                    String premierPesage = String.valueOf(jTable3.getValueAt(j, 7));
                    String dateDeuxieme = String.valueOf(jTable3.getValueAt(j, 8));
                    String heureDeuxieme = String.valueOf(jTable3.getValueAt(j, 9));
                    String deuxiemePesage = String.valueOf(jTable3.getValueAt(j, 10));
                    String masse = String.valueOf(jTable3.getValueAt(j, 11));
                    String premierPesagestring = premierPesage.replace(',', '.');
                    String deuxiemePesagestring = deuxiemePesage.replace(',', '.');
                    float dPremier = Float.parseFloat(premierPesagestring);
                    float dDeuxieme = Float.parseFloat(deuxiemePesagestring);
                    String Position = "";
                    if (dPremier < dDeuxieme) {
                        Position = "Entrer";
                    } else {
                        Position = "Sortie";
                    }
                    idd = idd + 1;
                    id = String.valueOf(idd);
                    String[] colon = {"Id", "Code_fournisseur", "Nom_fournisseur", "Adresse_fournisseur", "Produit", "Designation_produit", "Immatricule", "Date_premier", "Premier_pesage", "Date_deuxieme", "Deuxieme_pesage", "Masse_nette", "Totale_Entrer", "Totale_Sortie", "Date_debut", "Date_fin", "Etat"};
                    String[] inf = {id, jTextField1.getText(), NomClient, AdresseClient, jTextField2.getText(), DesignationProduit, Immatricul, datePremier + " " + heurePremier, premierPesage, dateDeuxieme + " " + heureDeuxieme, deuxiemePesage, masse, TotaleEntrer, TotaleSortie, deb, fin, Position};
                    System.out.println(db.queryInsert("mv_fournisseur", colon, inf));
                    jProgressBar1.setValue(j);
                }
                toPDF3();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String deb = "2016-01-01";
        String fin = "2080-01-01";
        String text = "Date_premier";
        String id;
        int idd = 0;
        boolean dd;

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
            text = "Date_premier";
        } else if (jComboBox1.getSelectedItem().equals("Date  de sorite")) {
            text = "Date_deuxieme";
        }

        tableFournisseur();
        tableProduit();
        db.queryDelete("rapport_fournisseur");

        jProgressBar1.setMaximum(jFournisseur.getRowCount() + jProduit.getRowCount() - 2);

        for (int i = 0; i < jFournisseur.getRowCount(); i++) {

            String CodeClient = String.valueOf(jFournisseur.getValueAt(i, 2));
            // System.out.println("Code Fournisseur: " + CodeFournisseur);
            String NomClient = String.valueOf(jFournisseur.getValueAt(i, 1));
            //System.out.println("Nom Fournisseur: " + NomFournisseur);
            String AdresseClient = String.valueOf(jFournisseur.getValueAt(i, 3));
            //System.out.println("TVA: " + TvaFournisseur);
            dd = false;
            for (int j = 0; j < jProduit.getRowCount(); j++) {
                String CodeProduit = String.valueOf(jProduit.getValueAt(j, 1));
                String DesProduit = String.valueOf(jProduit.getValueAt(j, 2));
                // System.out.println("Code produit: " + CodeProduit);
                rs = db.querySelectTwoALL("fournisseur_valide", "Code='" + CodeClient + "'", "Produit='" + CodeProduit + "'", text, deb, fin);
                TableModel jTable3 = new ResultSetTableModel(rs);
                //System.out.println("aaaa ");
                float c = 0;
                float v = 0;
                for (int k = 0; k < jTable3.getRowCount(); k++) {

                    String b = String.valueOf(jTable3.getValueAt(k, 11)).replace(',', '.');
                    String premier = String.valueOf(jTable3.getValueAt(k, 7)).replace(',', '.');
                    String deuxieme = String.valueOf(jTable3.getValueAt(k, 10)).replace(',', '.');
                    float dPremier = Float.parseFloat(premier);
                    float dDeuxieme = Float.parseFloat(deuxieme);
                    float d = Float.parseFloat(b);
                    if (dPremier > dDeuxieme) {
                        c = c + d;
                    } else {
                        v = v + d;
                    }
                }
                DecimalFormat df = new DecimalFormat("0");
                df.setRoundingMode(RoundingMode.DOWN);
                String TotaleEntrer = df.format(c).replace(',', '.');
                String TotaleSortie = df.format(v).replace(',', '.');
                //System.out.println("Totale " + Totale);

                if (Integer.parseInt(TotaleEntrer) != 0 || Integer.parseInt(TotaleSortie) != 0) {
                    String Cod = CodeClient;
                    if (dd == true) {
                        Cod = "#######";
                    }
                    idd = idd + 1;
                    id = String.valueOf(idd);
                    System.out.println(id);
                    String[] colon = {"Id", "Code_fournisseur", "Nom_fournisseur", "Adresse_fournisseur", "Code_Produit", "Designation_Produit", "Totale_Entrer", "Totale_sortie", "Date_debut", "Date_fin"};
                    String[] inf = {id, Cod, NomClient, AdresseClient, CodeProduit, DesProduit, TotaleEntrer, TotaleSortie, deb, fin};

                    System.out.println(db.queryInsert("rapport_fournisseur", colon, inf));

                    NomClient = "#######";
                    AdresseClient = "#######";
                    dd = true;
                }
                jProgressBar1.setValue(i + j);
            }
        }
        toPDF2();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String Numero = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        if (Numero.equals("null")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner dans le tableau un fournisseur à supprimer !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String password1 = "";
            String Typeaccess = "Directeur";
            rs = db.querySelectAll("utilisateur", "Titre='" + Typeaccess + "'");
            try {
                while (rs.next()) {
                    password1 = rs.getString("Password");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (JOptionPane.showInputDialog(this, "Veuillez saisir votre mot de passe Directeur :", "Verification", JOptionPane.OK_CANCEL_OPTION).equals(password1)) {
                String id = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
                String Code = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2));
                String Produit = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3));
                String Immatricule = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4));
                String Date_premier = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 5));
                String Heure_premier = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 6));
                String Premier_pesage = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 7)).replace(',', '.');
                String Date_deuxieme = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 8));
                String Heure_deuxieme = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 9));
                String Deuxieme_pesage = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 10)).replace(',', '.');;
                String Masse_nette = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 11));

                float dPremier = Float.parseFloat(Premier_pesage);
                float dDeuxieme = Float.parseFloat(Deuxieme_pesage);
                String IdProduit;
                String ProduitNom;

                String hostName = null;
                try {
                    final InetAddress addr = InetAddress.getLocalHost();
                    hostName = new String(addr.getHostName());
                } catch (final Exception e) {
                    hostName = ".";
                }

                if (dPremier <= dDeuxieme) {

                    rs = db.querySelectTwo("produit_sortant", "Numero='" + Numero + "'", "Code_type='" + Code + "'");
                    jTable2.setModel(new ResultSetTableModel(rs));
                    IdProduit = String.valueOf(jTable2.getValueAt(0, 0));
                    ProduitNom = "Produit Sortant";
                    db.queryDelete("produit_sortant", "id=" + IdProduit);

                } else {
                    rs = db.querySelectTwo("produit_entrant", "Numero='" + Numero + "'", "Code_type='" + Code + "'");
                    jTable2.setModel(new ResultSetTableModel(rs));
                    IdProduit = String.valueOf(jTable2.getValueAt(0, 0));
                    db.queryDelete("produit_sortant", "id=" + IdProduit);
                    ProduitNom = "Produit Entrant";
                }
                db.queryDelete("fournisseur_valide", "id=" + id);

                Date s = new Date();
                SimpleDateFormat d = new SimpleDateFormat("YYYY-MM-dd");
                SimpleDateFormat h = new SimpleDateFormat("HH:mm:ss");
                String txDate = d.format(s);
                String txHeure = h.format(s);

                String[] colon = {"Id_type", "Type", "Id_produit", "Produit_etat", "Numero", "Code", "Produit", "Immatricule", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette", "Date_supression", "Nom_machine"};
                String[] inf = {id, "Fournisseur Valide", IdProduit, ProduitNom, Numero, Code, Produit, Immatricule, Date_premier, Heure_premier, Premier_pesage,
                    Date_deuxieme, Heure_deuxieme, Deuxieme_pesage, Masse_nette, txDate + " " + txHeure, hostName};
                System.out.println(db.queryInsert("Suppression", colon, inf));
                table();

                JOptionPane.showMessageDialog(this, "Le fournisseur valide a été suprimé avec succès.");

            } else {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Le mot de passe est incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Imprimante;
    private com.toedter.calendar.JDateChooser a;
    private com.toedter.calendar.JDateChooser de;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JTable jFournisseur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable jProduit;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable0;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable20;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable41;
    private javax.swing.JTable jTable90;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txAdresseSoc;
    private javax.swing.JTextField txFaxSoc;
    private javax.swing.JTextField txTelSoc;
    private javax.swing.JTextField txnomSoc;
    // End of variables declaration//GEN-END:variables
}

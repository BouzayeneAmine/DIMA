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
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ClientValideDeuxA extends javax.swing.JInternalFrame {

    ConnectComPort dc;
    ResultSet rs;
    DbConnection db;

    public ClientValideDeuxA() {
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
        putClientProperty("ClientValideDeuxA.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }

    public void calcultotal() {
        float c = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            String b = String.valueOf(jTable1.getValueAt(i, 9));
            String money = b.replace(',', '.');
            float d = Float.parseFloat(money);
            c = c + d;
        }
        jLabel3.setText(Float.toString(c));
        DecimalFormat df = new DecimalFormat("0.0");
        df.setRoundingMode(RoundingMode.DOWN);
        float bb = (Float.parseFloat(jLabel3.getText()));
        String money = df.format(bb).replace(',', '.');
        jLabel3.setText(money);
    }

    public void table() {
String a[] = {"Id", "Numero", "mouvement", "Code_client", "Nom_client", "Code_produit", "Designation_produit","Nfabrication","Nenroleur","Laize","Grammage", "Date", "Heure", "Pesage"};        rs = db.querySelect(a, "client_valide_deux");
        rs = db.querySelect(a, "client_valide_deux");
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

    public void table10() {
String a[] = {"Id", "Code", "Designation", "Laize", "Grammage", "Coefficient"};
        rs = db.querySelect(a, "produit");
        jTable10.setModel(new ResultSetTableModel(rs));
    }

    public void table11() {
        String a[] = {"Id", "Nom", "Code", "Adresse"};
        rs = db.querySelect(a, "client");
        jTable11.setModel(new ResultSetTableModel(rs));
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

     public void toPDFA4() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            //InputStream path = this.getClass().getResourceAsStream("FORMAT A6.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport("C:\\format\\BondeTechnopaper.jrxml");
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(ProgrammeDeux.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toPDF2() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("RapportClientDeux.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(ProgrammeDeux.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toPDF3() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("MvClientDeux.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(ProgrammeDeux.class.getName()).log(Level.SEVERE, null, ex);
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
        jTable10 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable20 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable41 = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable0 = new javax.swing.JTable();
        Imprimante = new javax.swing.JTextField();
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
        a = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        de = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jComboBox1 = new javax.swing.JComboBox();

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
        jScrollPane5.setViewportView(jTable10);

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
        jScrollPane6.setViewportView(jTable11);

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
        jScrollPane7.setViewportView(jTable20);

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
        jScrollPane10.setViewportView(jTable41);

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel13.setBackground(new java.awt.Color(0, 51, 102));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Liste des clients valides");
        jLabel13.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        a.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        a.setDateFormatString("yyyy-MM-dd");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search-engine.png"))); // NOI18N
        jButton1.setText("Chercher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/export.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Numero", "Code client", "Code de produit", "Immatricule Camion", "Masse Nette" }));

        jLabel1.setText("De");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Code client", "Numero", "Code de produit", "Immatricule Camion", "Masse Nette" }));

        jLabel2.setText("A");

        de.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        de.setDateFormatString("yyyy-MM-dd");

        jLabel4.setText("Total");

        jLabel3.setFont(new java.awt.Font("HP Simplified", 2, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel5.setText("KG");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reimprimer.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Entrant", "Sortant" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 188, Short.MAX_VALUE)
                            .addComponent(de, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(a, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2)
                            .addComponent(jComboBox3, 0, 270, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(de, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(48, 48, 48)
                                    .addComponent(jLabel5))
                                .addComponent(jButton5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(25, 25, 25))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(ClientValideDeuxA.this);
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
        MessageFormat header = new MessageFormat("Liste des clients valides");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            jTable1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Erreur d'impression ", e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String text = "Date";
        String text1 = "Numero";
        String text2 = "Numero ";
        String text3 = "mouvement";
        String mouvement = jComboBox1.getSelectedItem().toString();
        String deb = "";
        String fin = "";

        if (de.getCalendar() == null || a.getCalendar() == null) {
            deb = "2016-01-01";
            fin = "2080-01-01";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            deb = sdf.format(de.getDate());
            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            fin = sd.format(a.getDate());
        }

        if (mouvement.equals(" ")) {

            if (jTextField1.getText().equals("") && jTextField2.getText().equals("")) {
                rs = db.querySearchAll("client_valide_deux", text, deb, fin);
            } else {

                if (jComboBox2.getSelectedItem().equals("Numero")) {
                    text1 = "Numero";
                } else if (jComboBox2.getSelectedItem().equals("Code client")) {
                    text1 = "Code_client";
                } else if (jComboBox2.getSelectedItem().equals("Code de produit")) {
                    text1 = "Code_produit";
                } else if (jComboBox2.getSelectedItem().equals("Masse Nette")) {
                    text1 = "Pesage";
                }

                if (jComboBox3.getSelectedItem().equals("Numero")) {
                    text2 = "Numero ";
                } else if (jComboBox3.getSelectedItem().equals("Code client")) {
                    text2 = "Code_client ";
                } else if (jComboBox3.getSelectedItem().equals("Code de produit")) {
                    text2 = "Code_produit ";
                } else if (jComboBox3.getSelectedItem().equals("Masse Nette")) {
                    text2 = "Pesage ";
                }

                rs = db.querySelectTwoALL("client_valide_deux", text1 + "='" + jTextField1.getText() + "'", text2 + "LIKE '%" + jTextField2.getText() + "%' ", text, deb, fin);
            }
        } else {

            if (jTextField1.getText().equals("") && jTextField2.getText().equals("")) {
                rs = db.querySelectTwoOne("client_valide_deux", text3 + "='" + mouvement + "'", text, deb, fin);
            } else {

                if (jComboBox2.getSelectedItem().equals("Numero")) {
                    text1 = "Numero";
                } else if (jComboBox2.getSelectedItem().equals("Code client")) {
                    text1 = "Code_client";
                } else if (jComboBox2.getSelectedItem().equals("Code de produit")) {
                    text1 = "Code_produit";
                } else if (jComboBox2.getSelectedItem().equals("Masse Nette")) {
                    text1 = "Pesage";
                }

                if (jComboBox3.getSelectedItem().equals("Numero")) {
                    text2 = "Numero ";
                } else if (jComboBox3.getSelectedItem().equals("Code client")) {
                    text2 = "Code_client ";
                } else if (jComboBox3.getSelectedItem().equals("Code de produit")) {
                    text2 = "Code_produit ";
                } else if (jComboBox3.getSelectedItem().equals("Masse Nette")) {
                    text2 = "Pesage ";
                }

                rs = db.querySelectThreeALL("client_valide_deux", text1 + "='" + jTextField1.getText() + "'", text3 + "='" + mouvement + "'", text2 + "LIKE '%" + jTextField2.getText() + "%' ", text, deb, fin);
            }
        }
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        calcultotal();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       String Numero = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        if (Numero.equals("null")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un client pour réimprimer le bon !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String mouvement = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2));
            String CodeClient = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3));
            String NomClient = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4));
            String CodeProduite = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 5));
            String DesignationProduit = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 6));
            String Nfabrication = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 7));
            String Nenroleur = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 8));
            String Laize = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 9));
            String Grammage = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 10));
            String Date = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 11));
            String Heure = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 12));
            String Pesage = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 13));

            rs = db.querySelectAll("client", "Code='" + CodeClient + "'");
            jTable2.setModel(new ResultSetTableModel(rs));
            String clientAdress = String.valueOf(jTable2.getValueAt(0, 3));

            rs = db.querySelectAll("produit", "Code='" + CodeProduite + "'");
            jTable3.setModel(new ResultSetTableModel(rs));
            String produitLot = String.valueOf(jTable3.getValueAt(0, 3));
            String produitPrix = String.valueOf(jTable3.getValueAt(0, 4));
            String prouitCoefficient = String.valueOf(jTable3.getValueAt(0, 5));

            
                String[] colon3 = {"Numero", "mouvement", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                        "Laize", "N_Fabrication", "Grammage", "Coefficient_produit", "N_Enroleur", "Date", "Heure", "Pesage","tare"};
                String[] inf3 = {Numero, mouvement, "client", CodeClient, NomClient, clientAdress, CodeProduite, DesignationProduit, Laize,
                    Nfabrication,Grammage, prouitCoefficient,Nenroleur, Date, Heure, Pesage};
                System.out.println(db.queryUpdate("impression_bon_deux", colon3, inf3, "id='" + 0 + "'"));
                toPDFA4();

            
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String deb = "2016-01-01";
        String fin = "2080-01-01";

        if (de.getCalendar() == null || a.getCalendar() == null) {
            deb = "2016-01-01";
            fin = "2080-01-01";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            deb = sdf.format(de.getDate());
            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            fin = sd.format(a.getDate());
        }

        if (jTextField1.getText().equals("") || jTextField2.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir : \n 1 - le code client dans le champ de texte à gauche \n 2- le code produit dans le champ de texte à droite", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String text = "Date";
            int idd = 0;
            String id;

            // table11();
            //table10();
            rs = db.querySelectAll("client", "Code='" + jTextField1.getText() + "'");
            jTable2.setModel(new ResultSetTableModel(rs));
            String NomClient = String.valueOf(jTable2.getValueAt(0, 1));
            String AdresseClient = String.valueOf(jTable2.getValueAt(0, 3));

            rs = db.querySelectAll("produit", "Code='" + jTextField2.getText() + "'");
            jTable41.setModel(new ResultSetTableModel(rs));
            String DesignationProduit = String.valueOf(jTable41.getValueAt(0, 2));

            // System.out.println("Code produit: " + CodeProduit);
            rs = db.querySelectTwoALL("client_valide_deux", "Code_client='" + jTextField1.getText() + "'", "Code_produit='" + jTextField2.getText() + "'", text, deb, fin);
            jTable20.setModel(new ResultSetTableModel(rs));
            String test = String.valueOf(jTable20.getValueAt(0, 1));
            if (test.equals("null")) {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Vérifiez que : \n 1- Ce code client n éxiste pas \n 2- Ce code produit n éxiste pas \n 3- Ce client n a rien eu de ce produit", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                jProgressBar1.setMaximum(jTable20.getRowCount() - 1);
                db.queryDelete("mv_client_deux");
                float c = 0;
                float v = 0;
                for (int k = 0; k < jTable20.getRowCount(); k++) {
                    String mvt = String.valueOf(jTable20.getValueAt(k, 2));
                    String b = String.valueOf(jTable20.getValueAt(k, 13)).replace(',', '.');
                    float dPremier = Float.parseFloat(b);

                    if (mvt.equals("Entrant")) {
                        c = c + dPremier;
                    } else {
                        v = v + dPremier;
                    }

                }
                DecimalFormat df = new DecimalFormat("0.0");
                df.setRoundingMode(RoundingMode.DOWN);
                String TotaleEntrer = df.format(c).replace(',', '.');
                String TotaleSortie = df.format(v).replace(',', '.');

                for (int j = 0; j < jTable20.getRowCount(); j++) {
                    String mvt = String.valueOf(jTable20.getValueAt(j, 2));
                    String Date = String.valueOf(jTable20.getValueAt(j, 11));
                    String Heure = String.valueOf(jTable20.getValueAt(j, 12));
                    String Pesage = String.valueOf(jTable20.getValueAt(j, 13));

                    String Position = "";
                    if (mvt.equals("Entrant")) {
                        Position = "Entrant";
                    } else {
                        Position = "Sortant";
                    }
                    idd = idd + 1;
                    id = String.valueOf(idd);
                    String[] colon = {"Id", "Code_client", "Nom_client", "Adresse_client", "Produit", "Designation_produit", "Date", "Heure", "Pesage", "Totale_Entrer", "Totale_Sortie", "Date_debut", "Date_fin", "Etat"};
                    String[] inf = {id, jTextField1.getText(), NomClient, AdresseClient, jTextField2.getText(), DesignationProduit, Date, Heure, Pesage, TotaleEntrer, TotaleSortie, deb, fin, Position};
                    System.out.println(db.queryInsert("mv_client_deux", colon, inf));
                    jProgressBar1.setValue(j);
                }

                toPDF3();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String deb = "2016-01-01";
        String fin = "2080-01-01";

        if (de.getCalendar() == null || a.getCalendar() == null) {
            deb = "2016-01-01";
            fin = "2080-01-01";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            deb = sdf.format(de.getDate());
            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            fin = sd.format(a.getDate());
        }

        String text = "Date";
        int idd = 0;
        String id;

        boolean dd;
        table11();
        table10();
        db.queryDelete("rapport_client_deux");

        jProgressBar1.setMaximum(jTable11.getRowCount() + jTable10.getRowCount() - 2);

        for (int i = 0; i < jTable11.getRowCount(); i++) {

            String CodeClient = String.valueOf(jTable11.getValueAt(i, 2));
            // System.out.println("Code Fournisseur: " + CodeFournisseur);
            String NomClient = String.valueOf(jTable11.getValueAt(i, 1));
            //System.out.println("Nom Fournisseur: " + NomFournisseur);
            String AdresseClient = String.valueOf(jTable11.getValueAt(i, 3));
            //System.out.println("TVA: " + TvaFournisseur);
            dd = false;
            for (int j = 0; j < jTable10.getRowCount(); j++) {
                String CodeProduit = String.valueOf(jTable10.getValueAt(j, 1));
                String DesProduit = String.valueOf(jTable10.getValueAt(j, 2));
                // System.out.println("Code produit: " + CodeProduit);
                rs = db.querySelectTwoALL("client_valide_deux", "Code_client='" + CodeClient + "'", "Code_produit='" + CodeProduit + "'", text, deb, fin);
                jTable20.setModel(new ResultSetTableModel(rs));

                float c = 0;
                float v = 0;
                for (int k = 0; k < jTable20.getRowCount(); k++) {
                    String mvt = String.valueOf(jTable20.getValueAt(k, 2));
                    String b = String.valueOf(jTable20.getValueAt(k, 13)).replace(',', '.');
                    float d = Float.parseFloat(b);
                    if (mvt.equals("Entrant")) {
                        c = c + d;
                    } else {
                        v = v + d;
                    }
                }
                DecimalFormat df = new DecimalFormat("0.0");
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
                    String[] colon = {"Id", "Code_client", "Nom_client", "Adresse", "Code_Produit", "Designation_Produit", "Totale_Entrer", "Totale_sortie", "Date_debut", "Date_fin"};
                    String[] inf = {id, NomClient, AdresseClient, CodeProduit, DesProduit, TotaleEntrer, TotaleSortie, deb, fin};
                    System.out.println(db.queryInsert("rapport_client_deux", colon, inf));
                    NomClient = "#######";
                    AdresseClient = "#######";
                    dd = true;
                }
                jProgressBar1.setValue(i + j);
            }

        }
        toPDF2();

    }//GEN-LAST:event_jButton6ActionPerformed

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
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable0;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
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

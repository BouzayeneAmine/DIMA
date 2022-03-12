/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion;
import BDD.Conector;
import BDD.ConnectComPort;
import BDD.DbConnection;
import BDD.Parametre;
import BDD.ResultSetTableModel;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.Math.abs;
import static java.lang.Math.round;
import static java.lang.Thread.sleep;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
public class programmeManuel extends javax.swing.JFrame  implements Runnable{

    ConnectComPort dc;
    ResultSet rs;
    DbConnection db;
    SerialPort serialPort;
    BDD.License l;
    public programmeManuel() {
         db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        dc = new ConnectComPort();
        l = new BDD.License();
        initComponents();
         this.pack();
        this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        table0();
        Imprimante.setText(String.valueOf(jTable0.getValueAt(0, 1)));
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
public void table8() {
        String a[] = {"Id", "Numero"};
        rs = db.querySelect(a, "nbon");
        jTable13.setModel(new ResultSetTableModel(rs));
    }
 public void table0() {
        String b[] = {"ID", "Type"};
        rs = dc.querySelect(b, "imprimante");
        jTable0.setModel(DbUtils.resultSetToTableModel(rs));
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

    public void toPDFA7() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("BonDeSortieA7.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport("C:\\format\\BonDeSortieA7.jrxml");
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

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        Numero = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable0 = new javax.swing.JTable();
        Imprimante = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable90 = new javax.swing.JTable();
        txnomSoc = new javax.swing.JLabel();
        txAdresseSoc = new javax.swing.JLabel();
        txTelSoc = new javax.swing.JLabel();
        txFaxSoc = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Panel_Menu = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        Panel_Pesage = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jRadioClient = new javax.swing.JRadioButton();
        jRadioFournisseur = new javax.swing.JRadioButton();
        jRadioFournisseur1 = new javax.swing.JRadioButton();
        clientNom = new javax.swing.JTextField();
        clientCode = new javax.swing.JTextField();
        clientAdress = new javax.swing.JTextField();
        produitDesignation = new javax.swing.JTextField();
        produitCode = new javax.swing.JTextField();
        produitLot = new javax.swing.JTextField();
        produitPrix = new javax.swing.JTextField();
        prouitCoefficient = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        prix = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        CamImm = new javax.swing.JTextField();
        CamType = new javax.swing.JTextField();
        CamTare = new javax.swing.JTextField();
        txDateE = new javax.swing.JTextField();
        txHeureE = new javax.swing.JTextField();
        txDateS = new javax.swing.JTextField();
        txHeureS = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        Panel_Poids = new javax.swing.JPanel();
        poidTarre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        poidBrute = new javax.swing.JTextField();
        poidNette = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable13);

        Numero.setText("jLabel11");

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
        jScrollPane2.setViewportView(jTable0);

        Imprimante.setText("jLabel11");

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
        jScrollPane3.setViewportView(jTable90);

        txnomSoc.setText("jLabel11");

        txAdresseSoc.setText("jLabel11");

        txTelSoc.setText("jLabel11");

        txFaxSoc.setText("jLabel11");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        Panel_Menu.setBackground(new java.awt.Color(0, 102, 153));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Ajouter Client.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Ajouter un transporteur.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Ajouter un fournisseur.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Ajouter Produit.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Ajouter camion.png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/transporteur.png"))); // NOI18N
        jButton14.setName(""); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fournisseur.png"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/client.png"))); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Produit entrant.png"))); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Produit sortant.png"))); // NOI18N
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_MenuLayout = new javax.swing.GroupLayout(Panel_Menu);
        Panel_Menu.setLayout(Panel_MenuLayout);
        Panel_MenuLayout.setHorizontalGroup(
            Panel_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_MenuLayout.createSequentialGroup()
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(717, Short.MAX_VALUE))
        );
        Panel_MenuLayout.setVerticalGroup(
            Panel_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_MenuLayout.createSequentialGroup()
                .addGroup(Panel_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(Panel_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Panel_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel1.add(Panel_Menu);
        Panel_Menu.setBounds(0, 0, 1840, 110);

        Panel_Pesage.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Pesage.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(null);

        jRadioClient.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioClient);
        jRadioClient.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRadioClient.setText("Client");
        jRadioClient.setOpaque(false);
        jRadioClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioClientActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioClient);
        jRadioClient.setBounds(110, 30, 80, 25);

        jRadioFournisseur.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioFournisseur);
        jRadioFournisseur.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRadioFournisseur.setText("Fournisseur");
        jRadioFournisseur.setOpaque(false);
        jRadioFournisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioFournisseurActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioFournisseur);
        jRadioFournisseur.setBounds(290, 30, 110, 25);

        jRadioFournisseur1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioFournisseur1);
        jRadioFournisseur1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRadioFournisseur1.setText("Transporteur");
        jRadioFournisseur1.setOpaque(false);
        jRadioFournisseur1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioFournisseur1ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioFournisseur1);
        jRadioFournisseur1.setBounds(180, 30, 110, 25);

        clientNom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        clientNom.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(clientNom);
        clientNom.setBounds(120, 70, 250, 40);

        clientCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        clientCode.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        clientCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientCodeActionPerformed(evt);
            }
        });
        jPanel3.add(clientCode);
        clientCode.setBounds(120, 130, 250, 40);

        clientAdress.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        clientAdress.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(clientAdress);
        clientAdress.setBounds(120, 190, 250, 40);

        produitDesignation.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        produitDesignation.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(produitDesignation);
        produitDesignation.setBounds(120, 310, 250, 40);

        produitCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        produitCode.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(produitCode);
        produitCode.setBounds(120, 370, 250, 40);

        produitLot.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        produitLot.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(produitLot);
        produitLot.setBounds(120, 430, 250, 40);

        produitPrix.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        produitPrix.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(produitPrix);
        produitPrix.setBounds(120, 490, 250, 40);

        prouitCoefficient.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(prouitCoefficient);
        prouitCoefficient.setBounds(120, 550, 250, 40);

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel9.setText("Coefficient :");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(20, 550, 100, 40);

        jLabel10.setFont(new java.awt.Font("Roboto Black", 0, 15)); // NOI18N
        jLabel10.setText("Produit :");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(20, 240, 70, 50);

        jLabel11.setFont(new java.awt.Font("Roboto Black", 0, 15)); // NOI18N
        jLabel11.setText("Client :");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(20, 20, 70, 50);

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel13.setText("Code :");
        jPanel3.add(jLabel13);
        jLabel13.setBounds(20, 130, 70, 40);

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel14.setText("Adresse :");
        jPanel3.add(jLabel14);
        jLabel14.setBounds(20, 190, 70, 40);

        jLabel15.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel15.setText("Désignation :");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(20, 310, 100, 40);

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel16.setText("Code Produit :");
        jPanel3.add(jLabel16);
        jLabel16.setBounds(20, 370, 100, 40);

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel17.setText("Lot  :");
        jPanel3.add(jLabel17);
        jLabel17.setBounds(20, 430, 100, 40);

        prix.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        prix.setText("Prix :");
        jPanel3.add(prix);
        prix.setBounds(20, 490, 100, 40);

        jLabel19.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel19.setText("Nom :");
        jPanel3.add(jLabel19);
        jLabel19.setBounds(20, 70, 70, 40);

        Panel_Pesage.add(jPanel3);
        jPanel3.setBounds(10, 10, 400, 700);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(null);

        CamImm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CamImm.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(CamImm);
        CamImm.setBounds(120, 70, 250, 40);

        CamType.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CamType.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(CamType);
        CamType.setBounds(120, 130, 250, 40);

        CamTare.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CamTare.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(CamTare);
        CamTare.setBounds(120, 190, 250, 40);

        txDateE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txDateE.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txDateE);
        txDateE.setBounds(120, 310, 250, 40);

        txHeureE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txHeureE.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txHeureE);
        txHeureE.setBounds(120, 370, 250, 40);

        txDateS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txDateS.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txDateS);
        txDateS.setBounds(120, 430, 250, 40);

        txHeureS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txHeureS.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txHeureS);
        txHeureS.setBounds(120, 490, 250, 40);

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel4.setText("Heure de sortie:");
        jPanel4.add(jLabel4);
        jLabel4.setBounds(10, 490, 110, 40);

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel5.setText("Heure d'entrée :");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(10, 370, 110, 40);

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel6.setText("Date d'entrée :");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(10, 310, 110, 40);

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel7.setText("Date de sortie:");
        jPanel4.add(jLabel7);
        jLabel7.setBounds(10, 430, 110, 40);

        jLabel8.setFont(new java.awt.Font("Roboto Black", 0, 15)); // NOI18N
        jLabel8.setText("Camion :");
        jPanel4.add(jLabel8);
        jLabel8.setBounds(20, 20, 70, 50);

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel12.setText("Camion Tare :");
        jPanel4.add(jLabel12);
        jLabel12.setBounds(10, 190, 100, 40);

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel20.setText("Immatricule :");
        jPanel4.add(jLabel20);
        jLabel20.setBounds(10, 70, 90, 40);

        jLabel21.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel21.setText("Camion Type :");
        jPanel4.add(jLabel21);
        jLabel21.setBounds(10, 130, 100, 40);

        Panel_Pesage.add(jPanel4);
        jPanel4.setBounds(430, 10, 400, 700);

        Panel_Poids.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Poids.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Panel_Poids.setOpaque(false);
        Panel_Poids.setLayout(null);

        poidTarre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        poidTarre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Poids.add(poidTarre);
        poidTarre.setBounds(135, 70, 240, 40);

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel1.setText("Poids Net           :");
        Panel_Poids.add(jLabel1);
        jLabel1.setBounds(10, 200, 120, 18);

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel2.setText("Premier pesage :");
        Panel_Poids.add(jLabel2);
        jLabel2.setBounds(10, 80, 120, 20);

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel3.setText("Poids Brut          :");
        Panel_Poids.add(jLabel3);
        jLabel3.setBounds(10, 140, 120, 18);

        poidBrute.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        poidBrute.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Poids.add(poidBrute);
        poidBrute.setBounds(135, 130, 240, 40);

        poidNette.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        poidNette.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Poids.add(poidNette);
        poidNette.setBounds(135, 190, 240, 40);

        jButton1.setFont(new java.awt.Font("Roboto Black", 0, 15)); // NOI18N
        jButton1.setText("annuler");
        Panel_Poids.add(jButton1);
        jButton1.setBounds(220, 340, 140, 60);

        jButton2.setFont(new java.awt.Font("Roboto Black", 0, 15)); // NOI18N
        jButton2.setText("confirmer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Panel_Poids.add(jButton2);
        jButton2.setBounds(40, 340, 140, 60);

        Panel_Pesage.add(Panel_Poids);
        Panel_Poids.setBounds(850, 110, 400, 450);

        jPanel1.add(Panel_Pesage);
        Panel_Pesage.setBounds(0, 110, 1430, 760);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1880, 1050);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        AjouterClient.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitSortant.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrant.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        TransporteurValide.getObj().dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        AjouterTransporteur.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitSortant.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrant.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterClient.getObj().dispose();
        TransporteurValide.getObj().dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        AjouterFournisseur.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitSortant.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrant.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
        TransporteurValide.getObj().dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        AjouterProduit.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitSortant.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrant.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
        TransporteurValide.getObj().dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        AjouterCamion.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitSortant.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrant.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
        TransporteurValide.getObj().dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        TransporteurValide.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitSortant.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrant.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        FournisseurValide.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitSortant.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrant.getObj().dispose();
        ClientValide.getObj().dispose();
        TransporteurValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        ClientValide.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitSortant.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrant.getObj().dispose();
        FournisseurValide.getObj().dispose();
        TransporteurValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        ProduitEntrant.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitSortant.getObj().dispose();
        ParametreConnection.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        TransporteurValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        ProduitSortant.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitEntrant.getObj().dispose();
        ParametreConnection.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        TransporteurValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jRadioClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioClientActionPerformed
 jLabel9.setText("Client"); 
        clientCode.setText("");
        clientNom.setText("");
        clientAdress.setText("");
    }//GEN-LAST:event_jRadioClientActionPerformed

    private void jRadioFournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioFournisseurActionPerformed
      jLabel9.setText("Fournisseur");
        clientCode.setText("");
        clientNom.setText("");
        clientAdress.setText("");
    }//GEN-LAST:event_jRadioFournisseurActionPerformed

    private void jRadioFournisseur1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioFournisseur1ActionPerformed
 jLabel9.setText("transporteur");      
        clientCode.setText("");
        clientNom.setText("");
        clientAdress.setText("");
    }//GEN-LAST:event_jRadioFournisseur1ActionPerformed

    private void clientCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientCodeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
            table8();
            Numero.setText(String.valueOf(jTable13.getValueAt(0, 1)));
            int nbr = Integer.parseInt(Numero.getText());
            Numero.setText(String.valueOf(nbr + 1));
            {
                    String[] colon = {"Numero", "Code", "Produit", "Immatricule", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
                    String[] inf = {Numero.getText(), clientCode.getText(), produitCode.getText(), CamImm.getText(),
                        txDateE.getText(), txHeureE.getText(), poidTarre.getText(), txDateS.getText(), txHeureS.getText(), poidBrute.getText(),
                        poidNette.getText()};
                    System.out.println(db.queryInsert("client_valide", colon, inf));
            }
            {
                    String[] colonn = {"Numero", "Code_produit", "code_type", "Immatricule", "Date_entree", "Heure_entree", "Premier_pesage", "Date_sortie", "Heure_sortie", "Deuxieme_pesage", "Masse_nette"};
                    String[] inff = {Numero.getText(), produitCode.getText(), clientCode.getText(), CamImm.getText(),
                        txDateE.getText(), txHeureE.getText(), poidTarre.getText(), txDateS.getText(), txHeureS.getText(), poidBrute.getText(),
                        poidNette.getText()};
                    System.out.println(db.queryInsert("produit_sortant", colonn, inff));
            }
                  
                    
                    {
                        String[] colon3 = {"Numero", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                            "Lot_produit", "Prix_produit", "Coefficient_produit", "Camion_Immatricule", "Camion_type", "Camion_tare",
                            "Premier_pesage", "Premier_date", "Premier_Heure", "Deuxieme_pesage", "Deuxieme_date", "Deuxieme_heure", "Masse_nette"};
                        String[] inf3 = {Numero.getText(), jLabel9.getText(), clientCode.getText(), clientNom.getText(), clientAdress.getText(),
                            produitCode.getText(), produitDesignation.getText(), produitLot.getText(), produitPrix.getText(), prouitCoefficient.getText(),
                            CamImm.getText(), CamType.getText(), CamTare.getText(), poidTarre.getText(), txDateE.getText(), txHeureE.getText(),
                            poidBrute.getText(), txDateS.getText(), txHeureS.getText(), poidNette.getText()};
                        System.out.println(db.queryUpdate("impression_bon", colon3, inf3, "id='" + 0 + "'"));
                         if (Imprimante.getText().equals("Normale A4")) {
                        JOptionPane.showMessageDialog(this, "Le client est sorti avec succès ! \n Merci d imprimer le bon de sortie sous format A4.");
                        toPDFA4();
                    } 
                         else if (Imprimante.getText().equals("Normale A5")) {
                        JOptionPane.showMessageDialog(this, "Le client est sorti avec succès ! \n Merci d imprimer le bon de sortie sous format A5.");
                        toPDFA5();
                    } 
                         else if (Imprimante.getText().equals("Normale A7")) {
                        JOptionPane.showMessageDialog(this, "Le client est sorti avec succès ! \n Merci d imprimer le bon de sortie sous format A7.");
                        toPDFA7();
                    } 
                    }
             String[] colonn = {"Numero"};
            String[] inff = {Numero.getText()};
            System.out.println(db.queryUpdate("nbon", colonn, inff, "id='" + "1" + "'"));
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(programmeManuel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(programmeManuel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(programmeManuel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(programmeManuel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new programmeManuel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CamImm;
    private javax.swing.JTextField CamTare;
    private javax.swing.JTextField CamType;
    private javax.swing.JLabel Imprimante;
    private javax.swing.JLabel Numero;
    private javax.swing.JPanel Panel_Menu;
    private javax.swing.JPanel Panel_Pesage;
    private javax.swing.JPanel Panel_Poids;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField clientAdress;
    private javax.swing.JTextField clientCode;
    private javax.swing.JTextField clientNom;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioClient;
    private javax.swing.JRadioButton jRadioFournisseur;
    private javax.swing.JRadioButton jRadioFournisseur1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable0;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable90;
    private javax.swing.JTextField poidBrute;
    private javax.swing.JTextField poidNette;
    private javax.swing.JTextField poidTarre;
    private javax.swing.JLabel prix;
    private javax.swing.JTextField produitCode;
    private javax.swing.JTextField produitDesignation;
    private javax.swing.JTextField produitLot;
    private javax.swing.JTextField produitPrix;
    private javax.swing.JTextField prouitCoefficient;
    private javax.swing.JLabel txAdresseSoc;
    private javax.swing.JTextField txDateE;
    private javax.swing.JTextField txDateS;
    private javax.swing.JLabel txFaxSoc;
    private javax.swing.JTextField txHeureE;
    private javax.swing.JTextField txHeureS;
    private javax.swing.JLabel txTelSoc;
    private javax.swing.JLabel txnomSoc;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

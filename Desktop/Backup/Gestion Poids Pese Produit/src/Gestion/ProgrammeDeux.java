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

public class ProgrammeDeux extends javax.swing.JFrame implements Runnable, SerialPortEventListener {

    ConnectComPort dc;
    ResultSet rs;
    DbConnection db;
    SerialPort serialPort;
    BDD.License l;
    private BufferedReader input;
    int TIME_OUT;
    int DATA_RATE;
    String com;
    public String getClientCode;
    public String getClientNom;
    public String getClientAdress;
    public String getProduitCode;
    public String getProduitDesignation;
    public String getProduitLaize;
    public String getProduitGrammage;
    public String getProduitCoef;
    public String getCamionType;
    public String getCamionTarre;
    public String getCamionImmatricule;
    public String getFourNom;
    public String getFourCode;
    public String getFourAdress;
    public String getTranNom;
    public String getTranCode;
    public String getTranAdress;

    public ProgrammeDeux() {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        dc = new ConnectComPort();
        l = new BDD.License();
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/Images/Logo.png")).getImage());
        datecourante();
        this.pack();
        this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        table();
        table0();
        table90();
       
        this.TIME_OUT = Integer.parseInt(String.valueOf(jTable2.getValueAt(0, 3)));
        this.DATA_RATE = Integer.parseInt(String.valueOf(jTable2.getValueAt(0, 2)));
        com = String.valueOf(jTable2.getValueAt(0, 1));

        SimpleRead(com);
        Imprimante.setText(String.valueOf(jTable0.getValueAt(0, 1)));
        /*if (poidBrute.getText().equals("No DATA") || poidBrute.getText().equals("ERR")) {
            premierPesage.setEnabled(false);
        }*/

    }

    public void postClient() {
        this.clientCode.setText(getClientCode);
        this.clientNom.setText(getClientNom);
        this.clientAdress.setText(getClientAdress);
    }

    public void postProduit() {
        this.produitDesignation.setText(getProduitDesignation);
        this.produitCode.setText(getProduitCode);
        this.prouitLaize.setText(getProduitLaize);
        this.produitGrammage.setText(getProduitGrammage);
        this.prouitCoefficient.setText(getProduitCoef);
    }

    public void postFournisseur() {
        this.clientCode.setText(getFourCode);
        this.clientNom.setText(getFourNom);
        this.clientAdress.setText(getFourAdress);
    }

    public void postTransporteur() {
        this.clientCode.setText(getTranCode);
        this.clientNom.setText(getTranNom);
        this.clientAdress.setText(getTranAdress);
    }

    public void table() {
        String b[] = {"ID", "PORT", "BAUD", "TIME"};
        rs = dc.querySelect(b, "Parametre");
        jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    }

    public void table0() {
        String b[] = {"ID", "Type"};
        rs = dc.querySelect(b, "imprimante");
        jTable0.setModel(DbUtils.resultSetToTableModel(rs));
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

    public void table2() {
        String a[] = {"Id", "Numero", "mouvement", "Code_fournisseur", "Nom_fournisseur", "Code_produit", "Designation_produit", "Date", "Heure", "Pesage"};
        rs = db.querySelect(a, "fournisseur_valide_deux");
        jTable3.setModel(new ResultSetTableModel(rs));
    }

    public void table3() {
        String a[] = {"Id", "Numero", "mouvement", "Code_client", "Nom_client", "Code_produit", "Designation_produit", "Date", "Heure", "Pesage"};
        rs = db.querySelect(a, "client_valide_deux");
        jTable4.setModel(new ResultSetTableModel(rs));
    }

    public void table4() {
        String a[] = {"Id", "Numero", "mouvement", "Code_transporteur", "Nom_transporteur", "Code_produit", "Designation_produit", "Date", "Heure", "Pesage"};
        rs = db.querySelect(a, "transporteur_valide_deux");
        jTable5.setModel(new ResultSetTableModel(rs));
    }

    public void table6() {
        String a[] = {"Id", "Numero", "Code_produit", "Designation_produit", "Code_client", "Nom_client", "Date", "Heure", "Pesage"};
        rs = db.querySelect(a, "produit_entrant_deux");
        jTable11.setModel(new ResultSetTableModel(rs));
    }

    public void table7() {
        String a[] = {"Id", "Numero", "Code_produit", "Designation_produit", "Code_client", "Nom_client", "Date", "Heure", "Pesage"};
        rs = db.querySelect(a, "produit_sortant_deux");
        jTable12.setModel(new ResultSetTableModel(rs));
    }

    public void table8() {
        String a[] = {"Id", "Numero"};
        rs = db.querySelect(a, "nbon_deux");
        jTable13.setModel(new ResultSetTableModel(rs));
    }

    public void date() {
        Date s = new Date();
        SimpleDateFormat d = new SimpleDateFormat("YYYY-MM-dd");
        SimpleDateFormat h = new SimpleDateFormat("HH:mm");
        SimpleDateFormat hs = new SimpleDateFormat("HH-mm-ss");
        txDate.setText(d.format(s));
        txHeure.setText(h.format(s));
        txHeureBon.setText(hs.format(s));
    }

    public void datecourante() {
        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar Cal = new GregorianCalendar();
                    int sconde = Cal.get(Calendar.SECOND);
                    int minute = Cal.get(Calendar.MINUTE);
                    int heure = Cal.get(Calendar.HOUR_OF_DAY);
                    jLabel14.setText(+heure + ":" + (minute) + ":" + sconde);
                    int mois = Cal.get(Calendar.MONTH);
                    int annee = Cal.get(Calendar.YEAR);
                    int jour = Cal.get(Calendar.DAY_OF_MONTH);
                    jLabel18.setText(+jour + "/" + (mois + 1) + "/" + annee);
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        clock.start();
    }

    void actualiser() {
        Id.setText("");
       // Batch.setText("");
       // date_expiration.setText("");
       // Numero.setText("");
        jLabel9.setText("Client");
        jLabel3.setText("");
        jLabel20.setText("");
        //produitCode.setText("");
//        produitDesignation.setText("");
//        produitLot.setText("");
//        produitPrix.setText("");
//        prouitCoefficient.setText("");
        jButton12.setEnabled(true);
        jButton13.setEnabled(true);
        //jRadioClient.setSelected(true);
        // jRadioClientEntrant.setSelected(true);
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

    public void toPDFMat() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("BonDeSortieMatDeux.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);

            JasperExportManager.exportReportToPdfFile(jasperPrint, txDate.getText() + "-" + txHeureBon.getText() + ".pdf");
            File file = new File(txDate.getText() + "-" + txHeureBon.getText() + ".pdf");
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
        } catch (JRException ex) {
            Logger.getLogger(ProgrammeDeux.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProgrammeDeux.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        txDate = new javax.swing.JTextField();
        txHeure = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        txtDate = new javax.swing.JTextField();
        txtHeure = new javax.swing.JTextField();
        Numero = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        poidBruteFinal = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        test = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        Id = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable0 = new javax.swing.JTable();
        Imprimante = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable90 = new javax.swing.JTable();
        txnomSoc = new javax.swing.JTextField();
        txAdresseSoc = new javax.swing.JTextField();
        txTelSoc = new javax.swing.JTextField();
        txFaxSoc = new javax.swing.JTextField();
        txHeureBon = new javax.swing.JTextField();
        Idtest2 = new javax.swing.JTextField();
        Idtest1 = new javax.swing.JTextField();
        buttonGroup2 = new javax.swing.ButtonGroup();
        Tare = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        nFabrication = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        nEnrolleur = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        poidBrute = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        premierPesage = new javax.swing.JButton();
        annulerPesage = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        clientCode = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        clientNom = new javax.swing.JTextField();
        clientAdress = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        produitDesignation = new javax.swing.JTextField();
        produitCode = new javax.swing.JTextField();
        produitGrammage = new javax.swing.JTextField();
        prouitCoefficient = new javax.swing.JTextField();
        prouitLaize = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

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

        jButton1.setText("Recherche");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "", "" }));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Digital-7", 0, 80)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setToolTipText("");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setOpaque(true);

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Serif LED Board-7", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("KG");
        jLabel10.setToolTipText("");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel10.setOpaque(true);

        jLabel21.setText("Bascule");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 60)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setToolTipText("");
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel20.setOpaque(true);

        jLabel12.setText("Prix total");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("DT");
        jLabel11.setToolTipText("");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel11.setOpaque(true);

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

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTable5);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jTable6);

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(jTable7);

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(jTable8);

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(jTable9);

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
        jScrollPane11.setViewportView(jTable11);

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane12.setViewportView(jTable12);

        jTextField1.setBackground(new java.awt.Color(0, 102, 153));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("2.025");
        jTextField1.setOpaque(false);

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("DT/KG");
        jLabel13.setToolTipText("");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        poidBruteFinal.setBackground(new java.awt.Color(0, 0, 0));
        poidBruteFinal.setFont(new java.awt.Font("Digital-7", 0, 80)); // NOI18N
        poidBruteFinal.setForeground(new java.awt.Color(0, 0, 204));
        poidBruteFinal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        poidBruteFinal.setToolTipText("");
        poidBruteFinal.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        poidBruteFinal.setOpaque(true);

        jLabel17.setText("Masse Brute");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Serif LED Board-7", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("KG");
        jLabel8.setToolTipText("");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel8.setOpaque(true);

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
        jScrollPane13.setViewportView(jTable13);

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
        jScrollPane14.setViewportView(jTable0);

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

        Tare.setText("0");

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gestion Pesage Pont Bascule 2.0");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jPanel3.setBackground(new java.awt.Color(0, 0, 153));

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Ajouter Client.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Ajouter Produit.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/client.png"))); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Produit sortant.png"))); // NOI18N
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("17:55:27");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("17:55:27");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo Mestiri 2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(13, 13, 13))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel18))))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(null);

        nFabrication.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        nFabrication.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nFabrication.setText("0");
        jPanel5.add(nFabrication);
        nFabrication.setBounds(180, 9, 230, 40);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("N° Fabrication :");
        jPanel5.add(jLabel6);
        jLabel6.setBounds(10, 9, 170, 40);

        nEnrolleur.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        nEnrolleur.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nEnrolleur.setText("0");
        nEnrolleur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nEnrolleurActionPerformed(evt);
            }
        });
        jPanel5.add(nEnrolleur);
        nEnrolleur.setBounds(180, 60, 230, 40);

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("N° Enrouleur:");
        jPanel5.add(jLabel15);
        jLabel15.setBounds(10, 60, 170, 40);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jProgressBar1.setForeground(new java.awt.Color(0, 153, 51));
        jProgressBar1.setMaximum(60000);
        jProgressBar1.setBorderPainted(false);
        jProgressBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jProgressBar1.setStringPainted(true);

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));

        jLabel2.setForeground(new java.awt.Color(204, 0, 0));

        poidBrute.setBackground(new java.awt.Color(204, 255, 255));
        poidBrute.setFont(new java.awt.Font("Digital-7", 0, 60)); // NOI18N
        poidBrute.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        poidBrute.setText("No DATA");
        poidBrute.setToolTipText("");
        poidBrute.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        poidBrute.setOpaque(true);

        jLabel24.setBackground(new java.awt.Color(204, 255, 255));
        jLabel24.setFont(new java.awt.Font("Serif LED Board-7", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("KG");
        jLabel24.setToolTipText("");
        jLabel24.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel24.setOpaque(true);

        jLabel25.setText("Poids Brut");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(poidBrute, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(poidBrute, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)))
                .addGap(21, 21, 21)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        premierPesage.setBackground(new java.awt.Color(0, 153, 0));
        premierPesage.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        premierPesage.setText("PESAGE");
        premierPesage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                premierPesageActionPerformed(evt);
            }
        });

        annulerPesage.setBackground(new java.awt.Color(255, 51, 51));
        annulerPesage.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        annulerPesage.setText("ANNULER");
        annulerPesage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerPesageActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton2.setText("TARE");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(premierPesage, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(282, 282, 282))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(annulerPesage, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(premierPesage, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(annulerPesage, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        clientCode.setEditable(false);
        clientCode.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        clientCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientCodeActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel9.setText("Client");

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        clientNom.setEditable(false);
        clientNom.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        clientNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientNomActionPerformed(evt);
            }
        });

        clientAdress.setEditable(false);
        clientAdress.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clientAdress, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(clientNom)
                    .addComponent(clientCode))
                .addGap(26, 26, 26))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(clientCode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clientNom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clientAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(null);

        jLabel22.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Coefficient");
        jPanel8.add(jLabel22);
        jLabel22.setBounds(250, 100, 160, 20);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton13);
        jButton13.setBounds(10, 10, 52, 42);

        produitDesignation.setEditable(false);
        produitDesignation.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jPanel8.add(produitDesignation);
        produitDesignation.setBounds(80, 10, 330, 30);

        produitCode.setEditable(false);
        produitCode.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jPanel8.add(produitCode);
        produitCode.setBounds(80, 60, 150, 35);

        produitGrammage.setEditable(false);
        produitGrammage.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        produitGrammage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produitGrammageActionPerformed(evt);
            }
        });
        jPanel8.add(produitGrammage);
        produitGrammage.setBounds(250, 60, 160, 35);

        prouitCoefficient.setEditable(false);
        prouitCoefficient.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jPanel8.add(prouitCoefficient);
        prouitCoefficient.setBounds(250, 120, 160, 35);

        prouitLaize.setEditable(false);
        prouitLaize.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jPanel8.add(prouitLaize);
        prouitLaize.setBounds(80, 120, 150, 35);

        jLabel23.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel23.setText("Produit");
        jPanel8.add(jLabel23);
        jLabel23.setBounds(10, 60, 70, 30);

        jLabel26.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Laize");
        jPanel8.add(jLabel26);
        jLabel26.setBounds(80, 100, 150, 20);

        jLabel27.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Grammage");
        jPanel8.add(jLabel27);
        jLabel27.setBounds(250, 40, 160, 20);

        jLabel28.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Code");
        jPanel8.add(jLabel28);
        jLabel28.setBounds(80, 40, 150, 20);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(65, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setText("Fichier");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Quitter");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edition");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem8.setText("Actualiser");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Retour");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Port indicateur");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Parametre");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Licence");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("?");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Aide");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("verifier les mise a jour");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("A propos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText(".");

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem10.setText("Client/Fournisseur/Transporteur");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem11.setText("Produit");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem12.setText("Camion");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(467, 467, 467))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
     FrameClient ma = new FrameClient(this);
        ma.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        FrameProduit ma = new FrameProduit(this);
        ma.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        AjouterClient.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        AjouterProduit.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        ClientValideDeux.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        ProduitSortantDeux.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        ParametreConnection.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment Quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.YES_OPTION) {
            db.closeconnexion();
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment Quitter ?", "Quitter?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            db.closeconnexion();
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed

    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (!poidBrute.getText().equals("Err")) {
            serialPort.close();
        }
        db.closeconnexion();
        Login l = new Login();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        ParametreConnection.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        CodeLicence.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Aide.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        CodeLicence.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        MiseAJour.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        APropos.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        ConnPort.getObj().setVisible(true);
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
     
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        FrameProduit ma = new FrameProduit(this);
        ma.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed

    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void clientNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientNomActionPerformed

    private void clientCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientCodeActionPerformed

    private void annulerPesageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerPesageActionPerformed
        actualiser();
    }//GEN-LAST:event_annulerPesageActionPerformed

    private void premierPesageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_premierPesageActionPerformed
        if (clientCode.getText().equals("") || clientNom.getText().equals("") || clientAdress.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un client ou un fournisseur ou un transporteur !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (poidBrute.getText().equals("Err") || poidBrute.getText().equals("No DATA")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Erreur de lecture ! Vérifiez votre port de communication avec l indicateur !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            date();
            table8();
            Numero.setText(String.valueOf(jTable13.getValueAt(0, 1)));
            int nbr = Integer.parseInt(Numero.getText());
            Numero.setText(String.valueOf(nbr + 1));

            

              
                    String[] colon = {"Numero", "mouvement", "Code_client", "Nom_client", "Code_produit", "Designation_produit","Nfabrication","Nenroleur","Grammage","Laize", "Date", "Heure", "Pesage","tare"};
                    String[] inf = {Numero.getText(), "Entrant", clientCode.getText(), clientNom.getText(), produitCode.getText(), produitDesignation.getText(),nFabrication.getText(),nEnrolleur.getText(),produitGrammage.getText(),prouitLaize.getText(), txDate.getText(), txHeure.getText(), poidBrute.getText(),Tare.getText()};
                    System.out.println(db.queryInsert("client_valide_deux", colon, inf));

                    String[] colonn = {"Numero", "Code_produit", "Designation_produit", "Code_type", "Nom_type","Nfabrication","Nenroleur","Grammage","Laize", "Date", "Heure", "Pesage","tare"};
                    String[] inff = {Numero.getText(), produitCode.getText(), produitDesignation.getText(), clientCode.getText(), clientNom.getText(),nFabrication.getText(),nEnrolleur.getText(),produitGrammage.getText(),prouitLaize.getText(),
                        txDate.getText(), txHeure.getText(), poidBrute.getText(),Tare.getText()};
                    System.out.println(db.queryInsert("produit_sortant_deux", colonn, inff));
                    
                    String[] colon3 = {"Numero", "mouvement", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                        "Laize", "N_Fabrication", "Grammage", "Coefficient_produit", "N_Enroleur", "Date", "Heure", "Pesage","tare"};
                    String[] inf3 = {Numero.getText(), "Entrant", jLabel9.getText(), clientCode.getText(), clientNom.getText(), clientAdress.getText(),
                        produitCode.getText(), produitDesignation.getText(), prouitLaize.getText(), nFabrication.getText(), produitGrammage.getText(), prouitCoefficient.getText(), nEnrolleur.getText(),
                        txDate.getText(), txHeure.getText(), poidBrute.getText(),Tare.getText()};
                    System.out.println(db.queryUpdate("impression_bon_deux", colon3, inf3, "id='" + 0 + "'"));
                    
                  
           
                JOptionPane.showMessageDialog(this, "Le client est sorti avec succès ! \n Merci d imprimer le bon de sortie sous format A4.");
                        toPDFA4(); 
                  
              
            String[] colonn6 = {"Numero"};
            String[] inff6 = {Numero.getText()};
            System.out.println(db.queryUpdate("nbon_deux", colonn6, inff6, "id='" + "1" + "'"));
            actualiser();
        }
    }//GEN-LAST:event_premierPesageActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Tare.setText(poidBrute.getText());
        System.out.println("tare="+Tare.getText());
        JOptionPane.showMessageDialog(this, "Tare enregistré avec succès !");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void nEnrolleurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nEnrolleurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nEnrolleurActionPerformed

    private void produitGrammageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produitGrammageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_produitGrammageActionPerformed

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
            java.util.logging.Logger.getLogger(ProgrammeDeux.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgrammeDeux.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgrammeDeux.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgrammeDeux.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProgrammeDeux().setVisible(true);
            }
        });
    }

    public void SimpleRead(String com) {

        System.setProperty("gnu.io.rxtx.SerialPorts", com);
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        String PORT_NAMES[] = {
            "/dev/tty.usbserial-A9007UX1", // Mac OS X
            "/dev/ttyACM0", // Raspberry Pi
            "/dev/ttyUSB0", // Linux
            com, // Windows
        };
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Impossible de trouver le port de communication ! Merci de prendre en considération ces remarques : \n 1-Vérifiez que l indicateur est allumé. \n 2-Vérifiez que le cable RS232 entre l indicateur et l ordinateur est branché \n 3-Vérifiez que le port de communication de l indicateur n est pas utilisé par un autre programme \n 4-Redémarer votre ordinateur et réessayez à nouveau", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.out.println("Could not find COM port.");
            poidBrute.setText("0");
            return;
        }

        try {

            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            // add event listeners
            serialPort.addEventListener((SerialPortEventListener) this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public void run() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void serialEvent(SerialPortEvent event) {
        int a = 7;
        int b = 2;
        if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine = input.readLine();

                /*if (inputLine.charAt(0) == '$') {//Indicateur GI400
                    a = 7;
                    b = 2;
                    premierPesage.setEnabled(true);
                }
                else if (inputLine.charAt(0) == 'S' || inputLine.charAt(0) == 'U')  {//hl318
                    a = 7;
                    b = 2;
                    premierPesage.setEnabled(true);
                }
                /*else if (inputLine.charAt(0) == 'S') {//Indicateur diniageo
                    a = 10;
                    b = 3;
                
                    premierPesage.setEnabled(false);
                    
                }*/
                //BR16
                /* int i =7;
                while ((inputLine.charAt(i)=='0')&&(i<13)){
                    i++;
                    a++;
                }
                if ((inputLine.charAt(a)=='.')){
                a--;}
                 */
                String firstPut = inputLine.substring(a, inputLine.length());
                String finalPut = firstPut.substring(0, firstPut.length() - b);
                System.out.println(inputLine);
                poidBrute.setText(finalPut);
                float kk = abs(Float.parseFloat(poidBrute.getText()) - Float.parseFloat(Tare.getText()));
                DecimalFormat df = new DecimalFormat("0");
                df.setRoundingMode(RoundingMode.DOWN);
                //float bb = (Float.parseFloat(tofloat.getText()));
                String honey = df.format(kk).replace('.', ',');
                jLabel4.setText(finalPut);
                //  poidBrute.setText(honey);

                int ss = round(Float.parseFloat(finalPut));
                jProgressBar1.setValue(ss);
                //  if (ss>0){ jProgressBar1.setValue(ss);}
                // else{jProgressBar1.setValue(0);}       
                if (Float.parseFloat(poidBrute.getText()) >= 49900) {
                    jLabel5.setText("Alerte ! Surpoids ! Risque de domage !");
                } else {
                    jLabel5.setText("");
                }
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Id;
    private javax.swing.JTextField Idtest1;
    private javax.swing.JTextField Idtest2;
    private javax.swing.JTextField Imprimante;
    private javax.swing.JTextField Numero;
    private javax.swing.JLabel Tare;
    private javax.swing.JButton annulerPesage;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField clientAdress;
    private javax.swing.JTextField clientCode;
    private javax.swing.JTextField clientNom;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable0;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTable jTable90;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField nEnrolleur;
    private javax.swing.JTextField nFabrication;
    private javax.swing.JLabel poidBrute;
    private javax.swing.JLabel poidBruteFinal;
    private javax.swing.JButton premierPesage;
    private javax.swing.JTextField produitCode;
    public javax.swing.JTextField produitDesignation;
    public javax.swing.JTextField produitGrammage;
    public javax.swing.JTextField prouitCoefficient;
    public javax.swing.JTextField prouitLaize;
    private javax.swing.JTextField test;
    private javax.swing.JTextField txAdresseSoc;
    private javax.swing.JTextField txDate;
    private javax.swing.JTextField txFaxSoc;
    private javax.swing.JTextField txHeure;
    private javax.swing.JTextField txHeureBon;
    private javax.swing.JTextField txTelSoc;
    private javax.swing.JTextField txnomSoc;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtHeure;
    // End of variables declaration//GEN-END:variables
}

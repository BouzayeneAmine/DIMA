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
import java.awt.Font;
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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class ProgrammeUn extends javax.swing.JFrame implements Runnable, SerialPortEventListener {

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
    public String getProduitLot;
    public String getProduitPrix;
    public String getProduitCoef;
    public String getCamionType;
    public String getCamionTarre;
    public String getCamionTtransporteur;
    public String getCamionImmatricule;
    public String getFourNom;
    public String getFourCode;
    public String getFourAdress;
    public String getTranNom;
    public String getTranCode;
    public String getTranAdress;

    public ProgrammeUn() {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        dc = new ConnectComPort();
        l = new BDD.License();
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/Images/Logo.png")).getImage());
        datecourante();
        this.pack();
        this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        poidNette.setOpaque(true);
        table();
        table1();
        table0();
        table90();
        getConvayeur();
        getChauffeur();
        table_facture_client();
        jRadioClient.setSelected(true);
        premierPesage.setEnabled(true);
        this.TIME_OUT = Integer.parseInt(String.valueOf(jTable2.getValueAt(0, 3)));
        this.DATA_RATE = Integer.parseInt(String.valueOf(jTable2.getValueAt(0, 2)));
        com = String.valueOf(jTable2.getValueAt(0, 1));
        fournisseur_libre.hide();
        SimpleRead(com);
        Imprimante.setText(String.valueOf(jTable0.getValueAt(0, 1)));
        if (poidTarre.getText().equals("")) {
            deuxPesage.setEnabled(false);
            poidTarre.setVisible(false);
            jLabel7.setVisible(false);
            jLabel16.setVisible(false);
        }

    }

    public void delete_table() {
        /// vider tableau de melange
        String q = "TRUNCATE  facture_client";
        rs = db.exécutionQuery(q);
        table_facture_client();
    }

    public void getChauffeur() {
        chauffeur_ComboBox.removeAllItems();
        String query = "Select * from chauffeur";
        rs = db.exécutionQuery(query);
        ArrayList liste = new ArrayList();
        try {
            while (rs.next()) {
                String str;

                str = rs.getString("Nom");

                //Assuming you have a user object
                System.err.println(str + "/n");
                liste.add(str);
                chauffeur_ComboBox.addItem(str);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterCamion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getConvayeur() {
        convoyeur_ComboBox.removeAllItems();
        String query = "Select * from convayeur";
        rs = db.exécutionQuery(query);
        ArrayList liste = new ArrayList();
        try {
            while (rs.next()) {
                String str;

                str = rs.getString("Nom");

                //Assuming you have a user object
                System.err.println(str + "/n");
                liste.add(str);
                convoyeur_ComboBox.addItem(str);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterCamion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean check_weight(String quantity, String poids) {
        Double difference = Double.valueOf(poids) - Double.valueOf(quantity);
        if (difference >= 20) {
        return false;
        }else
return true;
    }
///problem here in row
    public String quantite_totale() {
        double total=0;
        for(int i=0;i< facture_table.getRowCount();i++)
        total+=Double.valueOf(String.valueOf(facture_table.getValueAt(i, 4)));
        return String.valueOf(total);
    }

    public void actualiser_facture_client() {
        clientCode.setText("");
        clientAdress.setText("");
        clientNom.setText("");
        piece.setText("");
        poid_facture.setText("");
    }

    public void postClient() {
        this.clientCode.setText(getClientCode);
        this.clientNom.setText(getClientNom);
        this.clientAdress.setText(getClientAdress);
        if (clientCode.getText().equals("null")) {
            clientCode.setText("");
            clientNom.setText("");
            clientAdress.setText("");
        }

    }

    public void postProduit() {
        this.produitDesignation.setText(getProduitDesignation);
        this.produitCode.setText(getProduitCode);
        this.produitLot.setText(getProduitLot);
        this.produitPrix.setText(getProduitPrix);
        this.prouitCoefficient.setText(getProduitCoef);
        if (produitDesignation.getText().equals("null")) {
            produitDesignation.setText("");
            produitCode.setText("");
            produitLot.setText("");
            prouitCoefficient.setText("");
            produitPrix.setText("");
        }
    }

    public void postCamion() {
        this.CamType.setText(getCamionType);
        this.CamTare.setText(getCamionTarre);
        this.CamImm.setText(getCamionImmatricule);
        this.CamTransporteur.setText(getCamionTtransporteur);
        if (CamType.getText().equals("null")) {
            CamType.setText("");
            CamTare.setText("");
            CamImm.setText("");
        }
    }

    public void postFournisseur() {
        this.clientCode.setText(getFourCode);
        this.clientNom.setText(getFourNom);
        this.clientAdress.setText(getFourAdress);
        if (clientCode.getText().equals("null")) {
            clientCode.setText("");
            clientNom.setText("");
            clientAdress.setText("");
        }
    }

    public void postTransporteur() {
        this.clientCode.setText(getTranCode);
        this.clientNom.setText(getTranNom);
        this.clientAdress.setText(getTranAdress);
        if (clientCode.getText().equals("null")) {
            clientCode.setText("");
            clientNom.setText("");
            clientAdress.setText("");
        }
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

    public void table_facture_client() {
        String b[] = {"Id", "nom_client", "code_client", "piece", "poids_facture", "produit", "date"};
        rs = db.querySelect(b, "facture_client");
        facture_table.getTableHeader().setFont(new Font("roboto", Font.BOLD, 16));

        facture_table.setModel(DbUtils.resultSetToTableModel(rs));
        facture_table.getColumnModel().getColumn(0).setMinWidth(0);
        facture_table.getColumnModel().getColumn(0).setMaxWidth(0);
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

    public void table1() {
        String a[] = {"Id", "Numero", "Immatricule", "Type", "Code_Type", "Code_Produit", "Date_entree", "Heure_entree", "chauffeur", "convayeur", "Produit_des", "Produit_lot", "Produi_prix", "transporteur", "Cam_type", "cam_tare", "Premier_pesage"};
        rs = db.querySelect(a, "en_circulation");
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getTableHeader().setFont(new Font("roboto", Font.BOLD, 16));

        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(3).setMinWidth(0);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(4).setMinWidth(0);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(5).setMinWidth(0);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(10).setMinWidth(0);
        jTable1.getColumnModel().getColumn(10).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(11).setMinWidth(0);
        jTable1.getColumnModel().getColumn(11).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(12).setMinWidth(0);
        jTable1.getColumnModel().getColumn(12).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(13).setMinWidth(0);
        jTable1.getColumnModel().getColumn(13).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(14).setMinWidth(0);
        jTable1.getColumnModel().getColumn(14).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(15).setMinWidth(0);
        jTable1.getColumnModel().getColumn(15).setMaxWidth(0);
//        jTable1.getColumnModel().getColumn(16).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(16).setMaxWidth(0);
    }

    public void table2() {
        String a[] = {"Id", "Numero", "Code", "Produit", "Immatricule", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
        rs = db.querySelect(a, "fournisseur_valide");
        jTable3.setModel(new ResultSetTableModel(rs));
    }

    public void table3() {
        String a[] = {"Id", "Numero", "Code", "Produit", "Immatricule", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
        rs = db.querySelect(a, "client_valide");
        jTable4.setModel(new ResultSetTableModel(rs));
    }

    public void table4() {
        String a[] = {"Id", "Numero", "Code", "Produit", "Immatricule", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
        rs = db.querySelect(a, "transporteur_valide");
        jTable5.setModel(new ResultSetTableModel(rs));
    }

    public void table6() {
        String a[] = {"Id", "Numero", "Code_produit", "code_type", "Immatricule", "Date_entree", "Heure_entree", "Premier_pesage", "Date_sortie", "Heure_sortie", "Deuxieme_pesage", "Masse_nette"};
        rs = db.querySelect(a, "produit_entrant");
        jTable11.setModel(new ResultSetTableModel(rs));
    }

    public void table7() {
        String a[] = {"Id", "Numero", "Code_produit", "code_type", "Immatricule", "Date_entree", "Heure_entree", "Premier_pesage", "Date_sortie", "Heure_sortie", "Deuxieme_pesage", "Masse_nette"};
        rs = db.querySelect(a, "produit_sortant");
        jTable12.setModel(new ResultSetTableModel(rs));
    }

    public void table8() {
        String a[] = {"Id", "Numero"};
        rs = db.querySelect(a, "nbon");
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
                    if (sconde == 0 && minute == 0 && heure == 0) {
                        System.exit(0);
                    }
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
        Numero.setText("");
        jLabel9.setText("Client");
        txtDate.setText("");
        txtHeure.setText("");
        poidTarre.setText("");
        poidTarre.setVisible(false);
        jLabel7.setVisible(false);
        jLabel16.setVisible(false);
        jLabel3.setText("");
        jLabel20.setText("");
        poidBruteFinal.setText("");
        clientCode.setText("");
        clientNom.setText("");
        clientAdress.setText("");
        produitCode.setText("");
        produitDesignation.setText("");
        produitLot.setText("");
        produitPrix.setText("");
        prouitCoefficient.setText("");
        CamImm.setText("");
        CamType.setText("");
        CamTare.setText("");
        deuxPesage.setEnabled(false);
        premierPesage.setEnabled(true);
        jButton12.setEnabled(true);
        jButton13.setEnabled(true);
        jButton6.setEnabled(true);
        jRadioClient.setSelected(true);
        jRadioClient.setEnabled(true);
        jRadioFournisseur.setEnabled(true);
        table1();
        getChauffeur();
        getConvayeur();

        piece.setText("");
        poid_facture.setText("");
        CamTransporteur.setText("");
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
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("BonDeSortieMatA4.jrxml");
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
            Logger.getLogger(ProgrammeUn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProgrammeUn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toPDFMatA5() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("BonDeSortieMatA5.jrxml");
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
            Logger.getLogger(ProgrammeUn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProgrammeUn.class.getName()).log(Level.SEVERE, null, ex);
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
        clientAdress = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        ajouter_chauffeur = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ajouter_convoyeur = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        poidNette = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        poidTarre = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        poidBrute = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        deuxPesage = new javax.swing.JButton();
        premierPesage = new javax.swing.JButton();
        annulerPesage = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jRadioClient = new javax.swing.JRadioButton();
        jRadioFournisseur = new javax.swing.JRadioButton();
        clientCode = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        clientNom = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        poid_facture = new javax.swing.JTextField();
        piece = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        produitDesignation = new javax.swing.JTextField();
        produitLot = new javax.swing.JTextField();
        produitCode = new javax.swing.JTextField();
        produitPrix = new javax.swing.JTextField();
        prouitCoefficient = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        CamImm = new javax.swing.JTextField();
        CamType = new javax.swing.JTextField();
        CamTare = new javax.swing.JTextField();
        CamTransporteur = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        convoyeur_ComboBox = new javax.swing.JComboBox<>();
        chauffeur_ComboBox = new javax.swing.JComboBox<>();
        txtchauffeur = new javax.swing.JLabel();
        txtConvayeur = new javax.swing.JLabel();
        fournisseur_libre = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        facture_table = new javax.swing.JTable();
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

        clientAdress.setEditable(false);
        clientAdress.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N

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
        getContentPane().setLayout(null);

        jPanel7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel7.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(0, 0, 153));

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));

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

        ajouter_chauffeur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Ajouter un chauffeur.png"))); // NOI18N
        ajouter_chauffeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouter_chauffeurActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("17:55:27");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("17:55:27");

        ajouter_convoyeur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Ajouter un convayeur.png"))); // NOI18N
        ajouter_convoyeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouter_convoyeurActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
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
                        .addComponent(ajouter_convoyeur, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ajouter_chauffeur, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(768, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajouter_chauffeur, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajouter_convoyeur, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel18))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19)))
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel3);
        jPanel3.setBounds(0, 0, 2000, 150);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        jProgressBar1.setForeground(new java.awt.Color(0, 153, 51));
        jProgressBar1.setMaximum(60000);
        jProgressBar1.setBorderPainted(false);
        jProgressBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jProgressBar1.setStringPainted(true);
        jPanel1.add(jProgressBar1);
        jProgressBar1.setBounds(140, 250, 560, 17);

        poidNette.setBackground(new java.awt.Color(0, 0, 0));
        poidNette.setFont(new java.awt.Font("Roboto", 0, 70)); // NOI18N
        poidNette.setForeground(new java.awt.Color(0, 255, 0));
        poidNette.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        poidNette.setText("No DATA");
        poidNette.setToolTipText("");
        poidNette.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        poidNette.setOpaque(true);
        jPanel1.add(poidNette);
        poidNette.setBounds(140, 90, 507, 70);

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Serif LED Board-7", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 255, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("KG");
        jLabel6.setToolTipText("");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel6.setOpaque(true);
        jPanel1.add(jLabel6);
        jLabel6.setBounds(640, 90, 53, 70);

        poidTarre.setBackground(new java.awt.Color(0, 0, 0));
        poidTarre.setFont(new java.awt.Font("Roboto", 0, 70)); // NOI18N
        poidTarre.setForeground(new java.awt.Color(255, 0, 0));
        poidTarre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        poidTarre.setToolTipText("");
        poidTarre.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        poidTarre.setOpaque(true);
        jPanel1.add(poidTarre);
        poidTarre.setBounds(140, 170, 513, 70);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Serif LED Board-7", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("KG");
        jLabel7.setToolTipText("");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel7.setOpaque(true);
        jPanel1.add(jLabel7);
        jLabel7.setBounds(650, 170, 47, 70);

        jLabel15.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel15.setText("Poids Net");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(20, 120, 105, 29);

        jLabel16.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel16.setText("1er pesage");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(10, 190, 120, 29);

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(115, 2, 33, 20);

        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(154, 2, 32, 20);

        poidBrute.setBackground(new java.awt.Color(0, 0, 0));
        poidBrute.setFont(new java.awt.Font("Roboto", 0, 70)); // NOI18N
        poidBrute.setForeground(new java.awt.Color(0, 0, 255));
        poidBrute.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        poidBrute.setText("No DATA");
        poidBrute.setToolTipText("");
        poidBrute.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        poidBrute.setOpaque(true);
        jPanel1.add(poidBrute);
        poidBrute.setBounds(140, 10, 501, 70);

        jLabel24.setBackground(new java.awt.Color(0, 0, 0));
        jLabel24.setFont(new java.awt.Font("Serif LED Board-7", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("KG");
        jLabel24.setToolTipText("");
        jLabel24.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel24.setOpaque(true);
        jPanel1.add(jLabel24);
        jLabel24.setBounds(640, 10, 53, 70);

        jLabel25.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel25.setText("Poids Brut");
        jPanel1.add(jLabel25);
        jLabel25.setBounds(10, 30, 111, 29);

        jPanel7.add(jPanel1);
        jPanel1.setBounds(448, 162, 710, 290);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        deuxPesage.setBackground(new java.awt.Color(0, 153, 0));
        deuxPesage.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        deuxPesage.setText("Deuxieme pesage");
        deuxPesage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deuxPesageActionPerformed(evt);
            }
        });

        premierPesage.setBackground(new java.awt.Color(0, 153, 0));
        premierPesage.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        premierPesage.setText("Premier pesage");
        premierPesage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                premierPesageActionPerformed(evt);
            }
        });

        annulerPesage.setBackground(new java.awt.Color(255, 51, 51));
        annulerPesage.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        annulerPesage.setText("Annuler");
        annulerPesage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerPesageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(premierPesage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deuxPesage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(annulerPesage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(premierPesage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deuxPesage, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(annulerPesage, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.add(jPanel6);
        jPanel6.setBounds(1180, 150, 180, 350);

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel10.setLayout(null);

        buttonGroup1.add(jRadioClient);
        jRadioClient.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jRadioClient.setText("Client");
        jRadioClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioClientActionPerformed(evt);
            }
        });
        jPanel10.add(jRadioClient);
        jRadioClient.setBounds(133, 9, 90, 25);

        buttonGroup1.add(jRadioFournisseur);
        jRadioFournisseur.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jRadioFournisseur.setText("Fournisseur");
        jRadioFournisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioFournisseurActionPerformed(evt);
            }
        });
        jPanel10.add(jRadioFournisseur);
        jRadioFournisseur.setBounds(219, 9, 120, 25);

        clientCode.setEditable(false);
        clientCode.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jPanel10.add(clientCode);
        clientCode.setBounds(100, 50, 109, 35);

        jLabel9.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel9.setText("Client");
        jPanel10.add(jLabel9);
        jLabel9.setBounds(12, 58, 84, 17);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton12);
        jButton12.setBounds(12, 2, 52, 42);

        clientNom.setEditable(false);
        clientNom.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jPanel10.add(clientNom);
        clientNom.setBounds(219, 50, 210, 35);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(null);

        poid_facture.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        poid_facture.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(poid_facture);
        poid_facture.setBounds(100, 50, 310, 36);

        piece.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        piece.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(piece);
        piece.setBounds(100, 10, 310, 36);

        jLabel26.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel26.setText("Poids Facture");
        jPanel2.add(jLabel26);
        jLabel26.setBounds(10, 50, 90, 40);

        jLabel27.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel27.setText("Piéce");
        jPanel2.add(jLabel27);
        jLabel27.setBounds(10, 10, 90, 40);

        jPanel10.add(jPanel2);
        jPanel2.setBounds(0, 90, 430, 100);

        jPanel7.add(jPanel10);
        jPanel10.setBounds(10, 150, 430, 190);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.setLayout(null);

        jLabel22.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel22.setText("Produit");
        jPanel8.add(jLabel22);
        jLabel22.setBounds(12, 58, 60, 17);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton13);
        jButton13.setBounds(12, 4, 52, 42);

        produitDesignation.setEditable(false);
        produitDesignation.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jPanel8.add(produitDesignation);
        produitDesignation.setBounds(96, 11, 310, 35);

        produitLot.setEditable(false);
        produitLot.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jPanel8.add(produitLot);
        produitLot.setBounds(171, 50, 70, 31);

        produitCode.setEditable(false);
        produitCode.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jPanel8.add(produitCode);
        produitCode.setBounds(80, 50, 81, 31);

        produitPrix.setEditable(false);
        produitPrix.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jPanel8.add(produitPrix);
        produitPrix.setBounds(250, 50, 70, 31);

        prouitCoefficient.setEditable(false);
        prouitCoefficient.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jPanel8.add(prouitCoefficient);
        prouitCoefficient.setBounds(331, 50, 90, 31);

        jPanel7.add(jPanel8);
        jPanel8.setBounds(10, 420, 430, 93);

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setLayout(null);

        jLabel23.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel23.setText("Camion");
        jPanel11.add(jLabel23);
        jLabel23.setBounds(10, 50, 70, 30);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton6);
        jButton6.setBounds(12, 2, 52, 42);

        CamImm.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        CamImm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel11.add(CamImm);
        CamImm.setBounds(96, 2, 324, 30);

        CamType.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        CamType.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel11.add(CamType);
        CamType.setBounds(100, 80, 140, 30);

        CamTare.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        CamTare.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CamTare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CamTareActionPerformed(evt);
            }
        });
        jPanel11.add(CamTare);
        CamTare.setBounds(250, 80, 170, 30);

        CamTransporteur.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        CamTransporteur.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel11.add(CamTransporteur);
        CamTransporteur.setBounds(100, 41, 320, 30);

        jPanel7.add(jPanel11);
        jPanel11.setBounds(10, 470, 430, 120);

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        jPanel7.add(jTextField2);
        jTextField2.setBounds(740, 470, 290, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Recherche par Immatricule");
        jPanel7.add(jLabel4);
        jLabel4.setBounds(540, 460, 180, 50);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));
        jPanel7.add(jLabel5);
        jLabel5.setBounds(462, 470, 437, 48);

        jButton3.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jButton3.setText("supprimer");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton3);
        jButton3.setBounds(220, 590, 110, 40);

        jButton4.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jButton4.setText("modifier");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton4);
        jButton4.setBounds(120, 590, 100, 40);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(null);

        convoyeur_ComboBox.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        convoyeur_ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convoyeur_ComboBoxActionPerformed(evt);
            }
        });
        jPanel5.add(convoyeur_ComboBox);
        convoyeur_ComboBox.setBounds(100, 40, 310, 25);

        chauffeur_ComboBox.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        chauffeur_ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chauffeur_ComboBoxActionPerformed(evt);
            }
        });
        jPanel5.add(chauffeur_ComboBox);
        chauffeur_ComboBox.setBounds(100, 10, 310, 25);

        txtchauffeur.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        txtchauffeur.setText("Chauffeur");
        jPanel5.add(txtchauffeur);
        txtchauffeur.setBounds(10, 0, 90, 40);

        txtConvayeur.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        txtConvayeur.setText("Convayeur");
        jPanel5.add(txtConvayeur);
        txtConvayeur.setBounds(10, 40, 90, 30);
        jPanel5.add(fournisseur_libre);
        fournisseur_libre.setBounds(100, 10, 310, 60);

        jPanel7.add(jPanel5);
        jPanel5.setBounds(10, 340, 430, 80);

        jButton2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jButton2.setText("ajouter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton2);
        jButton2.setBounds(20, 590, 100, 40);

        jButton5.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jButton5.setText("vider");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton5);
        jButton5.setBounds(330, 590, 110, 40);

        getContentPane().add(jPanel7);
        jPanel7.setBounds(10, 0, 1536, 630);

        jTable1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
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

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(608, 625, 928, 360);

        facture_table.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        facture_table.setModel(new javax.swing.table.DefaultTableModel(
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
        facture_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                facture_tableMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(facture_table);

        getContentPane().add(jScrollPane16);
        jScrollPane16.setBounds(10, 630, 578, 301);

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void premierPesageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_premierPesageActionPerformed
        if (CamImm.getText().equals("") || CamType.getText().equals("") || CamTare.getText().equals("") || CamImm.getText().equals("null") || CamType.getText().equals("null") || CamTare.getText().equals("null")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un camion !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (poidNette.getText().equals("Err") || poidNette.getText().equals("No DATA")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Erreur de lecture ! Vérifiez votre port de communication avec l indicateur !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            date();
            table8();
            Numero.setText(String.valueOf(jTable13.getValueAt(0, 1)));
            int nbr = Integer.parseInt(Numero.getText());
            Numero.setText(String.valueOf(nbr + 1));
            String[] colon = {"Numero", "Immatricule", "Type", "Code_Type", "Code_Produit", "Date_entree", "Heure_entree", "Premier_pesage", "chauffeur", "convayeur", "Produit_des", "Produit_lot", "Produi_prix", "transporteur", "Cam_type", "cam_tare"};
            String[] inf = {Numero.getText(), CamImm.getText(), jLabel9.getText(), clientCode.getText(), produitCode.getText(),
                txDate.getText(), txHeure.getText(), poidNette.getText(), chauffeur_ComboBox.getSelectedItem().toString(), convoyeur_ComboBox.getSelectedItem().toString(), produitDesignation.getText(),
                produitLot.getText(), produitPrix.getText(), CamTransporteur.getText(), CamType.getText(), CamTare.getText()};
            System.out.println(db.queryInsert("en_circulation", colon, inf));

            String[] colonn = {"Numero"};
            String[] inff = {Numero.getText()};
            System.out.println(db.queryUpdate("nbon", colonn, inff, "id='" + "1" + "'"));

            actualiser();
            JOptionPane.showMessageDialog(this, "L utilisateur a été ajouté à la circulation.");
        }
    }//GEN-LAST:event_premierPesageActionPerformed

    private void deuxPesageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deuxPesageActionPerformed
        String masseNet = "erreur";
        String q = " SELECT * FROM stockage";
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        if (check_weight(quantite_totale(), poidNette.getText()))
        {
        Idtest2.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
        float bb = (Float.parseFloat(poidBrute.getText()) - Float.parseFloat(poidTarre.getText()));
        if (!Idtest2.getText().equals(Idtest1.getText())) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez bien présser sur le tableau !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            date();

            if (bb >= 0) {
                if (jLabel9.getText().equals("Client")) {
                    String[] colon = {"Numero", "Immatricule", "transporteur", "chauffeur", "convayeur", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
                    String[] inf = {Numero.getText(), CamImm.getText(), CamTransporteur.getText(), chauffeur_ComboBox.getSelectedItem().toString(), convoyeur_ComboBox.getSelectedItem().toString(),
                        txtDate.getText(), txtHeure.getText(), poidTarre.getText(), txDate.getText(), txHeure.getText(), poidBrute.getText(),
                        poidNette.getText()};
                    System.out.println(db.queryInsert("client_valide", colon, inf));
                    //// historique par facture 
                    int n = facture_table.getRowCount();
                    String facture = "";
                    String quantite = "";
                    String Client = "";

                    for (int i = 0; i < n; i++) {

                        facture = String.valueOf(facture_table.getValueAt(i, 3));
                        quantite = String.valueOf(facture_table.getValueAt(i, 4));
                        Client = String.valueOf(facture_table.getValueAt(i, 1));

                        String[] colonfac = {"Client", "Code", "Produit", "Immatricule", "Transporteur", "Chauffeur", "Convayeur", "Date", "Heure", "Facture", "Quantite"};
                        String[] inffac = {Client, clientCode.getText(), produitCode.getText(), CamImm.getText(), CamTransporteur.getText(), chauffeur_ComboBox.getSelectedItem().toString(), convoyeur_ComboBox.getSelectedItem().toString(),
                            txtDate.getText(), txtHeure.getText(), facture, quantite};
                        System.out.println(db.queryInsert("facture", colonfac, inffac));

                    }

                    //////historique par facture
                    String[] colonn = {"Numero", "Code_produit", "code_type", "Immatricule", "Date_entree", "Heure_entree", "Premier_pesage", "Date_sortie", "Heure_sortie", "Deuxieme_pesage", "Masse_nette"};
                    String[] inff = {Numero.getText(), produitCode.getText(), clientCode.getText(), CamImm.getText(),
                        txtDate.getText(), txtHeure.getText(), poidTarre.getText(), txDate.getText(), txHeure.getText(), poidBrute.getText(),
                        poidNette.getText()};
                    System.out.println(db.queryInsert("produit_sortant", colonn, inff));

                    String idd = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
                    db.queryDelete("en_circulation", "id=" + idd);
                    //stoker les donneé dans le tableau stokage pour calculer le masse net
                    String[] colonS = {"Numero", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                        "Lot_produit", "Prix_produit", "Coefficient_produit", "Camion_Immatricule", "Camion_type", "Camion_tare",
                        "Premier_pesage", "Premier_date", "Premier_Heure", "Deuxieme_pesage", "Deuxieme_date", "Deuxieme_heure"};
                    String[] infS = {Numero.getText(), jLabel9.getText(), clientCode.getText(), clientNom.getText(), clientAdress.getText(),
                        produitCode.getText(), produitDesignation.getText(), produitLot.getText(), produitPrix.getText(), prouitCoefficient.getText(),
                        CamImm.getText(), CamType.getText(), CamTare.getText(), poidTarre.getText(), txtDate.getText(), txtHeure.getText(),
                        poidBrute.getText(), txDate.getText(), txHeure.getText()};
                    System.out.println(db.queryUpdate("stockage", colonS, infS, "id='" + 0 + "'"));
                    // stocker les donneés dans le tableau d immpression bon pour imprimer
                    try {
                        rs = db.exécutionQuery(q);
                        rs.next();
                        Float premierPesage = Float.parseFloat(rs.getString(14));
                        Float deuxiemePesage = Float.parseFloat(rs.getString(17));
                        masseNet = df.format((abs(premierPesage - deuxiemePesage))).replaceAll(",", ".");

                    } catch (Exception e) {
                        System.out.println("there is problem in" + e.toString());
                    }

                    String[] colon3 = {"Numero", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                        "Lot_produit", "Prix_produit", "transporteur", "Camion_Immatricule", "Camion_type", "Camion_tare",
                        "Premier_pesage", "Premier_date", "Premier_Heure", "Deuxieme_pesage", "Deuxieme_date", "Deuxieme_heure", "Masse_nette", "chauffeur", "convayeur"};
                    String[] inf3 = {Numero.getText(), jLabel9.getText(), clientCode.getText(), clientNom.getText(), clientAdress.getText(),
                        produitCode.getText(), produitDesignation.getText(), produitLot.getText(), produitPrix.getText(), CamTransporteur.getText(),
                        CamImm.getText(), CamType.getText(), CamTare.getText(), poidTarre.getText(), txtDate.getText(), txtHeure.getText(),
                        poidBrute.getText(), txDate.getText(), txHeure.getText(), poidNette.getText(), chauffeur_ComboBox.getSelectedItem().toString(), convoyeur_ComboBox.getSelectedItem().toString()};
                    System.out.println(db.queryUpdate("impression_bon", colon3, inf3, "id='" + 0 + "'"));

                    JOptionPane.showMessageDialog(this, "Le client est sorti avec succès ! \n Merci d imprimer le bon .");
                    toPDFMatA5();

                } else if (jLabel9.getText().equals("Fournisseur")) {
                    String[] colon = {"Numero", "Code", "Produit", "Immatricule", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
                    String[] inf = {Numero.getText(), clientCode.getText(), produitCode.getText(), CamImm.getText(),
                        txtDate.getText(), txtHeure.getText(), poidTarre.getText(), txDate.getText(), txHeure.getText(), poidBrute.getText(),
                        poidNette.getText()};
                    System.out.println(db.queryInsert("fournisseur_valide", colon, inf));

                    String[] colonn = {"Numero", "Code_produit", "code_type", "Immatricule", "Date_entree", "Heure_entree", "Premier_pesage", "Date_sortie", "Heure_sortie", "Deuxieme_pesage", "Masse_nette"};
                    String[] inff = {Numero.getText(), produitCode.getText(), clientCode.getText(), CamImm.getText(),
                        txtDate.getText(), txtHeure.getText(), poidTarre.getText(), txDate.getText(), txHeure.getText(), poidBrute.getText(),
                        poidNette.getText()};
                    System.out.println(db.queryInsert("produit_sortant", colonn, inff));

                    String idd = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
                    db.queryDelete("en_circulation", "id=" + idd);

                    //stoker les donneé dans le tableau stokage pour calculer le masse net
                    String[] colonS = {"Numero", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                        "Lot_produit", "Prix_produit", "Coefficient_produit", "Camion_Immatricule", "Camion_type", "Camion_tare",
                        "Premier_pesage", "Premier_date", "Premier_Heure", "Deuxieme_pesage", "Deuxieme_date", "Deuxieme_heure"};
                    String[] infS = {Numero.getText(), jLabel9.getText(), clientCode.getText(), clientNom.getText(), clientAdress.getText(),
                        produitCode.getText(), produitDesignation.getText(), produitLot.getText(), produitPrix.getText(), prouitCoefficient.getText(),
                        CamImm.getText(), CamType.getText(), CamTare.getText(), poidTarre.getText(), txtDate.getText(), txtHeure.getText(),
                        poidBrute.getText(), txDate.getText(), txHeure.getText()};
                    System.out.println(db.queryUpdate("stockage", colonS, infS, "id='" + 0 + "'"));
                    // stocker les donneés dans le tableau d immpression bon pour imprimer
                    try {
                        
                        rs = db.exécutionQuery(q);
                        rs.next();
                        Float premierPesage = Float.parseFloat(rs.getString(14));
                        Float deuxiemePesage = Float.parseFloat(rs.getString(17));
                        masseNet = df.format((abs(premierPesage - deuxiemePesage))).replaceAll(",", ".");

                    } catch (Exception e) {
                        System.out.println("there is problem in" + e.toString());
                    }

                    String[] colon3 = {"Numero", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                        "Lot_produit", "Prix_produit", "Coefficient_produit", "Camion_Immatricule", "Camion_type", "Camion_tare",
                        "Premier_pesage", "Premier_date", "Premier_Heure", "Deuxieme_pesage", "Deuxieme_date", "Deuxieme_heure", "Masse_nette"};
                    String[] inf3 = {Numero.getText(), jLabel9.getText(), clientCode.getText(), clientNom.getText(), clientAdress.getText(),
                        produitCode.getText(), produitDesignation.getText(), produitLot.getText(), produitPrix.getText(), prouitCoefficient.getText(),
                        CamImm.getText(), CamType.getText(), CamTare.getText(), poidTarre.getText(), txtDate.getText(), txtHeure.getText(),
                        poidBrute.getText(), txDate.getText(), txHeure.getText(), poidNette.getText()};
                    System.out.println(db.queryUpdate("impression_bon", colon3, inf3, "id='" + 0 + "'"));

                    if (Imprimante.getText().equals("Normale A4")) {

                        JOptionPane.showMessageDialog(this, "Le fournisseur est sorti avec succès ! \n Merci d imprimer le bon de sortie sous format A4.");
                        toPDFA4();
                    }
                }
            } else {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "erreur");
            }
            delete_table();
            actualiser();
        }}
        else{
                        JOptionPane.showMessageDialog(this, "Ecart suppérieur à 20 kg,vous devez reglée les quantités des produits !!!!!");

        }
    }//GEN-LAST:event_deuxPesageActionPerformed

    private void annulerPesageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerPesageActionPerformed
        actualiser();
    }//GEN-LAST:event_annulerPesageActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if (jRadioClient.isSelected()) {
            FrameClient ma = new FrameClient(this);
            ma.setVisible(true);
        } else if (jRadioFournisseur.isSelected()) {
            FrameFournisseur fou = new FrameFournisseur(this);
            fou.setVisible(true);
        } else {
            FrameTransporteur fou = new FrameTransporteur(this);
            fou.setVisible(true);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        FrameProduit ma = new FrameProduit(this);
        ma.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jRadioClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioClientActionPerformed
        jLabel9.setText("Client");
        clientCode.setText("");
        clientNom.setText("");
        clientAdress.setText("");
        fournisseur_libre.hide();
        chauffeur_ComboBox.setVisible(true);
        convoyeur_ComboBox.setVisible(true);
    }//GEN-LAST:event_jRadioClientActionPerformed

    private void jRadioFournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioFournisseurActionPerformed
        jLabel9.setText("Fournisseur");
        clientCode.setText("");
        clientNom.setText("");
        clientAdress.setText("");
        fournisseur_libre.setVisible(true);
        chauffeur_ComboBox.hide();
        convoyeur_ComboBox.hide();
    }//GEN-LAST:event_jRadioFournisseurActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        FrameCamion ma = new FrameCamion(this);
        ma.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        Idtest1.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
        Numero.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1)));
        CamImm.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)));
        jLabel9.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3)));
        clientCode.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4)));
        produitCode.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 5)));
        txtDate.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 6)));
        txtHeure.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 7)));
        poidTarre.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 16)));
        chauffeur_ComboBox.setSelectedItem(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 9)));
        convoyeur_ComboBox.setSelectedItem(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 10)));
        produitDesignation.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 11)));
        produitLot.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 12)));
        produitPrix.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 13)));
        CamTransporteur.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 13)));
        CamType.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 15)));
        CamTare.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 16)));

        premierPesage.setEnabled(false);
        deuxPesage.setEnabled(true);
//        jButton12.setEnabled(false);
//        jButton13.setEnabled(false);
//        jButton6.setEnabled(false);
//        jRadioClient.setEnabled(false);
//        jRadioFournisseur.setEnabled(false);
        poidTarre.setVisible(true);
        jLabel7.setVisible(true);
        jLabel16.setVisible(true);
    }//GEN-LAST:event_jTable1MouseClicked

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

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        rs = db.querySelectAll("en_circulation", "Immatricule LIKE '%" + jTextField2.getText() + "%' ");
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(9).setMinWidth(9);
        jTable1.getColumnModel().getColumn(9).setMaxWidth(9);
        jTable1.getColumnModel().getColumn(10).setMinWidth(10);
        jTable1.getColumnModel().getColumn(10).setMaxWidth(10);
        jTable1.getColumnModel().getColumn(11).setMinWidth(11);
        jTable1.getColumnModel().getColumn(11).setMaxWidth(11);
        jTable1.getColumnModel().getColumn(12).setMinWidth(12);
        jTable1.getColumnModel().getColumn(12).setMaxWidth(12);
        jTable1.getColumnModel().getColumn(13).setMinWidth(13);
        jTable1.getColumnModel().getColumn(13).setMaxWidth(13);
        jTable1.getColumnModel().getColumn(14).setMinWidth(14);
        jTable1.getColumnModel().getColumn(14).setMaxWidth(14);
        jTable1.getColumnModel().getColumn(15).setMinWidth(15);
        jTable1.getColumnModel().getColumn(15).setMaxWidth(15);
        jTable1.getColumnModel().getColumn(16).setMinWidth(16);
        jTable1.getColumnModel().getColumn(16).setMaxWidth(16);
    }//GEN-LAST:event_jTextField2KeyReleased

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
        table1();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (!poidNette.getText().equals("0")) {
            serialPort.close();
        }
        db.closeconnexion();
        Login l = null;
        try {
            l = new Login();
        } catch (IOException ex) {
            Logger.getLogger(ProgrammeUn.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        ProduitEntrant.getObj().dispose();
        ProduitSortant.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        TransporteurValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
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
        ProduitEntrant.getObj().dispose();
        ProduitSortant.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        TransporteurValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
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
        ProduitEntrant.getObj().dispose();
        ProduitSortant.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        TransporteurValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
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
        ProduitEntrant.getObj().dispose();
        ProduitSortant.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        TransporteurValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
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
        ProduitEntrant.getObj().dispose();
        ProduitSortant.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        TransporteurValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
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
        ProduitEntrant.getObj().dispose();
        ProduitSortant.getObj().dispose();
        FournisseurValide.getObj().dispose();
        ClientValide.getObj().dispose();
        TransporteurValide.getObj().dispose();
        AjouterCamion.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        if (jRadioClient.isSelected()) {
            FrameClient ma = new FrameClient(this);
            ma.setVisible(true);
        } else if (jRadioFournisseur.isSelected()) {
            FrameFournisseur fou = new FrameFournisseur(this);
            fou.setVisible(true);
        } else {
            FrameTransporteur fou = new FrameTransporteur(this);
            fou.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        FrameProduit ma = new FrameProduit(this);
        ma.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        FrameCamion ma = new FrameCamion(this);
        ma.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        date();

        String[] colon = {"nom_client", "code_client", "piece", "poids_facture", "produit", "date"};
        String[] inf = {clientNom.getText(), clientCode.getText(), "FACT" + piece.getText(), poid_facture.getText(), produitDesignation.getText(), txDate.getText()};
        System.out.println(db.queryInsert("facture_client", colon, inf));
        table_facture_client();
        actualiser_facture_client();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void facture_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facture_tableMouseClicked
        // TODO add your handling code here:
        piece.setText(String.valueOf(facture_table.getValueAt(facture_table.getSelectedRow(), 3)));
        poid_facture.setText(String.valueOf(facture_table.getValueAt(facture_table.getSelectedRow(), 4)));
    }//GEN-LAST:event_facture_tableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String id = String.valueOf(facture_table.getValueAt(facture_table.getSelectedRow(), 0));
        System.err.println(db.queryDelete("facture_client", "Id=" + id));
        JOptionPane.showMessageDialog(this, "Facture a été supprimé avec succès.");
        table_facture_client();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String id = String.valueOf(facture_table.getValueAt(facture_table.getSelectedRow(), 0));
        String[] colon = {"nom_client", "code_client", "piece", "poids_facture", "produit", "date"};
        String[] inf = {clientNom.getText(), clientCode.getText(), piece.getText(), poid_facture.getText(), produitDesignation.getText(), txDate.getText()};
        System.out.println(db.queryUpdate("facture_client", colon, inf, "Id='" + id + "'"));
        table_facture_client();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void convoyeur_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convoyeur_ComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_convoyeur_ComboBoxActionPerformed

    private void chauffeur_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chauffeur_ComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chauffeur_ComboBoxActionPerformed

    private void ajouter_chauffeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouter_chauffeurActionPerformed
        AjouterChauffeur.getObj().setVisible(true);

    }//GEN-LAST:event_ajouter_chauffeurActionPerformed

    private void ajouter_convoyeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouter_convoyeurActionPerformed
        // TODO add your handling code here:
        AjouterConvayeur.getObj().setVisible(true);
    }//GEN-LAST:event_ajouter_convoyeurActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        delete_table();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void CamTareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CamTareActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CamTareActionPerformed

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
            java.util.logging.Logger.getLogger(ProgrammeUn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgrammeUn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgrammeUn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgrammeUn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProgrammeUn().setVisible(true);
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
            poidNette.setText("100");
            poidBrute.setText("0");
            deuxPesage.setEnabled(false);
            deuxPesage.setEnabled(false);
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
        int a = 3;
        int b = 12;
        //int b = 3;            KW
        if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {

                String inputLine = input.readLine();
                a = 3;
                b = 12;
//                if (inputLine.charAt(0) == 'S' || inputLine.charAt(0) == 'U') {     //Indicateur KW
//                    a = 7;
//                    b = 3;
//                    if (inputLine.charAt(0) == 'S') {
//                        jLabel1.setText("<>");
//                    } else {
//                        jLabel1.setText("");
//                    }
//                    if (jLabel1.getText().equals("<>") && !deuxPesage.isEnabled() && poidTarre.getText().equals("")) {
//                        premierPesage.setEnabled(true);
//                    } else {
//                        premierPesage.setEnabled(false);
//                    }
//                    if (!jLabel1.getText().equals("<>") || premierPesage.isEnabled() || poidTarre.getText().equals("")) {
//                        deuxPesage.setEnabled(false);
//                    } else {
//                        deuxPesage.setEnabled(true);
//                    }
//                    if (!poidBruteFinal.getText().equals("")) {
//                        deuxPesage.setEnabled(false);
//                        premierPesage.setEnabled(false);
//                    }
//                } 
//System.out.println("aaa"); 
//                if (inputLine.charAt(inputLine.length() - 2) == 'D') {         //Indicateur 3030
//                    a = 1;
//                    b = 3;
//                    if (inputLine.charAt(inputLine.length() - 1) == '2') {
//                        jLabel1.setText("<>");
//                    } else {
//                        jLabel1.setText("");
//                    }
//                    if (inputLine.charAt(0) == 'N' || inputLine.charAt(0) == ' ') {
//                        jLabel2.setText("");
//                    } else {
//                        jLabel2.setText(">0<");
//                    }
//                    if (jLabel1.getText().equals("<>")) {
//                        premierPesage.setEnabled(true);
//                    } else {
//                        premierPesage.setEnabled(false);
//                    }

                    String firstPut = inputLine.substring(a, inputLine.length());
                    String finalPut = firstPut.substring(0, firstPut.length() - b);
                    System.out.println(inputLine);

                    poidBrute.setText(finalPut);

                    if (poidTarre.getText().equals("")) {
                        String money = String.valueOf(finalPut).replaceAll(",", ".");
                        poidNette.setText(finalPut);
                    } else {
                        if (poidBruteFinal.getText().equals("")) {
                            float bb = abs(Float.parseFloat(poidBrute.getText()) - Float.parseFloat(poidTarre.getText()));
                            DecimalFormat df = new DecimalFormat("0");
                            df.setRoundingMode(RoundingMode.DOWN);
                            //float bb = (Float.parseFloat(tofloat.getText()));
                            String money = df.format(bb).replace(',', '.');
                            poidNette.setText(money);
                            // poidNette.setText(tofloat.getText());
                        }
                    }
                    int ss = round(Float.parseFloat(finalPut));
                    jProgressBar1.setValue(ss);
                    //  if (ss>0){ jProgressBar1.setValue(ss);}
                    // else{jProgressBar1.setValue(0);}       
                    if (Float.parseFloat(poidBrute.getText()) >= 89500) {
                        jLabel5.setText("Alerte ! Surpoids ! Risque de domage !");
                    } else {
                        jLabel5.setText("");
                    }
                
            } catch (Exception e) {

            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CamImm;
    private javax.swing.JTextField CamTare;
    private javax.swing.JTextField CamTransporteur;
    private javax.swing.JTextField CamType;
    private javax.swing.JTextField Id;
    private javax.swing.JTextField Idtest1;
    private javax.swing.JTextField Idtest2;
    private javax.swing.JTextField Imprimante;
    private javax.swing.JTextField Numero;
    private javax.swing.JButton ajouter_chauffeur;
    private javax.swing.JButton ajouter_convoyeur;
    private javax.swing.JButton annulerPesage;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> chauffeur_ComboBox;
    private javax.swing.JTextField clientAdress;
    private javax.swing.JTextField clientCode;
    private javax.swing.JTextField clientNom;
    private javax.swing.JComboBox<String> convoyeur_ComboBox;
    private javax.swing.JButton deuxPesage;
    private javax.swing.JTable facture_table;
    private javax.swing.JTextField fournisseur_libre;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JRadioButton jRadioClient;
    private javax.swing.JRadioButton jRadioFournisseur;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable0;
    private javax.swing.JTable jTable1;
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
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField piece;
    private javax.swing.JLabel poidBrute;
    private javax.swing.JLabel poidBruteFinal;
    private javax.swing.JLabel poidNette;
    private javax.swing.JLabel poidTarre;
    private javax.swing.JTextField poid_facture;
    private javax.swing.JButton premierPesage;
    private javax.swing.JTextField produitCode;
    public javax.swing.JTextField produitDesignation;
    public javax.swing.JTextField produitLot;
    public javax.swing.JTextField produitPrix;
    public javax.swing.JTextField prouitCoefficient;
    private javax.swing.JTextField test;
    private javax.swing.JTextField txAdresseSoc;
    private javax.swing.JTextField txDate;
    private javax.swing.JTextField txFaxSoc;
    private javax.swing.JTextField txHeure;
    private javax.swing.JTextField txHeureBon;
    private javax.swing.JTextField txTelSoc;
    private javax.swing.JTextField txnomSoc;
    private javax.swing.JLabel txtConvayeur;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtHeure;
    private javax.swing.JLabel txtchauffeur;
    // End of variables declaration//GEN-END:variables
}


package pkgnew.app;

import BDD.Conector;
import BDD.ConnectComPort;

import BDD.DbConnection;
import BDD.Parametre;
import BDD.ResultSetTableModel;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import static java.lang.Thread.sleep;
import javax.print.DocFlavor;

public class Historique extends javax.swing.JFrame {

    ResultSet rs;
    DbConnection db;
    ConnectComPort dc;

    public static Historique obj = null;
    private JLabel Heure = new JLabel();
    private JLabel Date = new JLabel();
    private DefaultTableCellRenderer headerRenderer;
    String Nom,id,Barcode,Categorie,Quantittie,Prix_Vente,total_base,total_product,Date_Sortie,Heure_Sortie,user;
    public Historique(String username1) {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        dc = new ConnectComPort();
        db.connexionDatabase();

        Heure1 = new JLabel();
        Date1 = new JLabel();

        date();
        datecourante();
        initComponents();
        this.setDefaultCloseOperation(0);

        try {
            NomUtilisateur();
        } catch (SQLException ex) {
            Logger.getLogger(Historique.class.getName()).log(Level.SEVERE, null, ex);
        }

        headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(30, 130, 82));
        headerRenderer.setForeground(new Color(255, 250, 240));

        /// bouton bilan actif
        bilan.show();
        stock.hide();
        action.hide();
        //.............

        total.show();
        stockrech.hide();
        actionrech.hide();

        AuthPanel.hide();
        ViderBilanBtn.show();
        ViderStockBtn.hide();
        ViderActionBtn.hide();

        tableBilan(); // dessine table bilan
        utilisateur.setText(username1);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        remplireVenduBrutNet();

    }

    public static Historique getObj() {
        if (obj == null) {
            obj = new Historique("");
        }
        return obj;
    }

    void NomUtilisateur() throws SQLException {
        String b[] = {"Login"};
        String q = "Select Login FROM Utilisateur WHERE type = 'directeur'";
        rs = db.exécutionQuery(q);

        while (rs.next()) {
            String indicateur = rs.getString("Login");
            txt_username.addItem(indicateur);
        }
    }

    public void date() {
        Date s = new Date();
        SimpleDateFormat d = new SimpleDateFormat("dd-MM-YYYY");
        SimpleDateFormat h = new SimpleDateFormat("HH:mm");
        Date.setText(d.format(s));
        Heure.setText(h.format(s));
    }

    public void datecourante() {
        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar Cal = new GregorianCalendar();
                    int sconde = Cal.get(Calendar.SECOND);
                    int minute = Cal.get(Calendar.MINUTE);
                    int heure = Cal.get(Calendar.HOUR_OF_DAY);
                    Heure1.setText(+heure + ":" + (minute) + ":" + sconde);
                    int mois = Cal.get(Calendar.MONTH);
                    int annee = Cal.get(Calendar.YEAR);
                    int jour = Cal.get(Calendar.DAY_OF_MONTH);
                    Date1.setText(+jour + "/" + (mois + 1) + "/" + annee);
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

    public void tableBilan() {
        String historique[] = {"Id", "Nom", "Barcode", "Categorie", "Quantite", "Prix_Vente", "total_base", "total", "Date_Sortie", "Heure_Sortie", "user"};
        rs = db.querySelect(historique, "historique");
        jTable1.setModel(new ResultSetTableModel(rs));

        jTable1.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(Color.BLACK);
        jTable1.getTableHeader().setForeground(new Color(19, 35, 132));

        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);

        jTable1.setRowHeight(30);

        jScrollPane1.getViewport().setBackground(new Color(255, 250, 240));
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
     }

    public void tableStock() {
        String stock_historique[] = {"User", "Nom", "Barcode", "Quantité_precedente", "Quantité_ajoutée", "Quantité_totale", "Date_ajout", "Heure_ajout"};
        rs = db.querySelect(stock_historique, "stock_historique");
        jTable1.setModel(new ResultSetTableModel(rs));

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(45);

        jTable1.setRowHeight(30);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
    }

    public void tableAction() {
        String action_historique[] = {"id", "user", "type_action", "description", "Date", "Heure"};
        rs = db.querySelect(action_historique, "action");
        jTable1.setModel(new ResultSetTableModel(rs));

        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);

        jTable1.setRowHeight(30);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
     }

    public void actualiser() {
        tableBilan();
    }

    public void toPDF4() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("Parcelcube.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(Historique.class.getName()).log(Level.SEVERE, null, ex);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barpanel = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        statbtn = new javax.swing.JButton();
        usersbtn = new javax.swing.JButton();
        salesbtn = new javax.swing.JButton();
        inventorybtn = new javax.swing.JButton();
        stockbtn = new javax.swing.JButton();
        registerbtn = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Session = new javax.swing.JPanel();
        utilisateur = new javax.swing.JLabel();
        LogoutBtnbg = new javax.swing.JLabel();
        LogoutBtn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Heure1 = new javax.swing.JLabel();
        Date1 = new javax.swing.JLabel();
        AuthPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        CancelBtn = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        confirmerBilanBtn = new javax.swing.JButton();
        confirmerStockBtn = new javax.swing.JButton();
        confirmerActionBtn = new javax.swing.JButton();
        txt_username = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        errormsg = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        imprimer = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        export = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        actionrech = new javax.swing.JButton();
        stockrech = new javax.swing.JButton();
        total = new javax.swing.JButton();
        de = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelVendus = new javax.swing.JLabel();
        labelVedus2 = new javax.swing.JLabel();
        txVendus = new javax.swing.JTextField();
        labelBrut = new javax.swing.JLabel();
        labelBrut2 = new javax.swing.JLabel();
        txTotal = new javax.swing.JTextField();
        labelNet = new javax.swing.JLabel();
        labelNet2 = new javax.swing.JLabel();
        net = new javax.swing.JTextField();
        a = new com.toedter.calendar.JDateChooser();
        Barsearch = new javax.swing.JTextField();
        searchbarbg = new javax.swing.JLabel();
        action = new javax.swing.JLabel();
        stock = new javax.swing.JLabel();
        bilan = new javax.swing.JLabel();
        bilanbtn = new javax.swing.JButton();
        stockhbtn = new javax.swing.JButton();
        actionhbtn = new javax.swing.JButton();
        ViderBilan = new javax.swing.JLabel();
        ViderStock = new javax.swing.JLabel();
        ViderAction = new javax.swing.JLabel();
        ViderBilanBtn = new javax.swing.JButton();
        ViderStockBtn = new javax.swing.JButton();
        ViderActionBtn = new javax.swing.JButton();
        ViderBilan1 = new javax.swing.JLabel();
        retour_product = new javax.swing.JButton();
        Background = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 250, 240));
        setMaximumSize(new java.awt.Dimension(1280, 800));
        setMinimumSize(new java.awt.Dimension(1280, 800));
        setSize(new java.awt.Dimension(1280, 800));
        getContentPane().setLayout(null);

        barpanel.setBackground(new java.awt.Color(255, 250, 240));
        barpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 130, 82), 2));
        barpanel.setForeground(new java.awt.Color(255, 253, 209));
        barpanel.setPreferredSize(new java.awt.Dimension(100, 1080));
        barpanel.setLayout(null);

        Logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/logoMAGAZI-04.png"))); // NOI18N
        barpanel.add(Logo);
        Logo.setBounds(0, 0, 80, 90);

        jLabel22.setBackground(new java.awt.Color(255, 250, 240));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/statistiques.png"))); // NOI18N
        jLabel22.setOpaque(true);
        barpanel.add(jLabel22);
        jLabel22.setBounds(10, 680, 60, 50);

        jLabel21.setBackground(new java.awt.Color(255, 250, 240));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/utilisateurs.png"))); // NOI18N
        jLabel21.setOpaque(true);
        barpanel.add(jLabel21);
        jLabel21.setBounds(10, 580, 60, 50);

        jLabel20.setBackground(new java.awt.Color(30, 130, 82));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/historiqueLight.png"))); // NOI18N
        jLabel20.setOpaque(true);
        barpanel.add(jLabel20);
        jLabel20.setBounds(10, 480, 60, 64);

        jLabel5.setBackground(new java.awt.Color(30, 130, 82));
        jLabel5.setOpaque(true);
        barpanel.add(jLabel5);
        jLabel5.setBounds(0, 475, 80, 70);

        jLabel19.setBackground(new java.awt.Color(255, 250, 240));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/inventory.png"))); // NOI18N
        jLabel19.setOpaque(true);
        barpanel.add(jLabel19);
        jLabel19.setBounds(10, 380, 60, 50);

        jLabel18.setBackground(new java.awt.Color(255, 250, 240));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/Stock.png"))); // NOI18N
        jLabel18.setOpaque(true);
        barpanel.add(jLabel18);
        jLabel18.setBounds(10, 280, 60, 50);

        jLabel16.setBackground(new java.awt.Color(255, 250, 240));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/cashregister.png"))); // NOI18N
        jLabel16.setOpaque(true);
        barpanel.add(jLabel16);
        jLabel16.setBounds(10, 180, 60, 50);

        statbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        statbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statbtnActionPerformed(evt);
            }
        });
        barpanel.add(statbtn);
        statbtn.setBounds(20, 680, 50, 50);

        usersbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        usersbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersbtnActionPerformed(evt);
            }
        });
        barpanel.add(usersbtn);
        usersbtn.setBounds(20, 580, 50, 45);

        salesbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        salesbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesbtnActionPerformed(evt);
            }
        });
        barpanel.add(salesbtn);
        salesbtn.setBounds(20, 490, 50, 50);

        inventorybtn.setPreferredSize(new java.awt.Dimension(80, 80));
        inventorybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventorybtnActionPerformed(evt);
            }
        });
        barpanel.add(inventorybtn);
        inventorybtn.setBounds(20, 380, 50, 50);

        stockbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        stockbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockbtnActionPerformed(evt);
            }
        });
        barpanel.add(stockbtn);
        stockbtn.setBounds(20, 280, 50, 50);

        registerbtn.setBackground(new java.awt.Color(255, 255, 255));
        registerbtn.setBorder(null);
        registerbtn.setBorderPainted(false);
        registerbtn.setOpaque(false);
        registerbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        registerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerbtnActionPerformed(evt);
            }
        });
        barpanel.add(registerbtn);
        registerbtn.setBounds(20, 180, 50, 50);

        jLabel30.setBackground(new java.awt.Color(255, 250, 240));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/credit.png"))); // NOI18N
        jLabel30.setOpaque(true);
        barpanel.add(jLabel30);
        jLabel30.setBounds(10, 760, 60, 50);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        barpanel.add(jButton2);
        jButton2.setBounds(10, 770, 60, 30);

        getContentPane().add(barpanel);
        barpanel.setBounds(2, 0, 80, 890);

        jPanel2.setBackground(new java.awt.Color(255, 250, 240));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 130, 82), 2));
        jPanel2.setForeground(new java.awt.Color(30, 130, 82));
        jPanel2.setLayout(null);

        Session.setBackground(new java.awt.Color(255, 250, 240));
        Session.setRequestFocusEnabled(false);
        Session.setLayout(null);

        utilisateur.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        utilisateur.setForeground(new java.awt.Color(5, 45, 26));
        utilisateur.setText("User");
        Session.add(utilisateur);
        utilisateur.setBounds(25, 10, 110, 40);

        LogoutBtnbg.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        LogoutBtnbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/logoutbtn.png"))); // NOI18N
        Session.add(LogoutBtnbg);
        LogoutBtnbg.setBounds(145, 10, 100, 40);

        LogoutBtn.setBackground(new java.awt.Color(255, 255, 255));
        LogoutBtn.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        LogoutBtn.setForeground(new java.awt.Color(255, 0, 51));
        LogoutBtn.setBorder(null);
        LogoutBtn.setBorderPainted(false);
        LogoutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LogoutBtn.setOpaque(false);
        LogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBtnActionPerformed(evt);
            }
        });
        Session.add(LogoutBtn);
        LogoutBtn.setBounds(150, 20, 90, 20);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/sessionbg.png"))); // NOI18N
        Session.add(jLabel15);
        jLabel15.setBounds(5, 0, 260, 60);

        jPanel2.add(Session);
        Session.setBounds(90, 10, 270, 60);

        jLabel12.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(30, 130, 82));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("HISTORIQUE");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(810, 0, 290, 80);

        Heure1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Heure1.setForeground(new java.awt.Color(5, 45, 26));
        jPanel2.add(Heure1);
        Heure1.setBounds(370, 0, 100, 80);

        Date1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Date1.setForeground(new java.awt.Color(5, 45, 26));
        jPanel2.add(Date1);
        Date1.setBounds(500, 0, 100, 80);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(50, 0, 1220, 80);

        AuthPanel.setBackground(new java.awt.Color(30, 130, 82));
        AuthPanel.setForeground(new java.awt.Color(255, 250, 240));
        AuthPanel.setFont(new java.awt.Font("Roboto Medium", 0, 36)); // NOI18N
        AuthPanel.setLayout(null);

        jLabel17.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 250, 240));
        jLabel17.setText("Veuillez entrer votre login et mot de passe:");
        AuthPanel.add(jLabel17);
        jLabel17.setBounds(20, 10, 490, 80);

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/AnnulerAuthbtn.png"))); // NOI18N
        AuthPanel.add(jLabel23);
        jLabel23.setBounds(280, 320, 210, 70);

        CancelBtn.setText("annuler");
        CancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelBtnActionPerformed(evt);
            }
        });
        AuthPanel.add(CancelBtn);
        CancelBtn.setBounds(290, 330, 180, 50);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/viderBtn.png"))); // NOI18N
        AuthPanel.add(jLabel14);
        jLabel14.setBounds(30, 320, 210, 70);

        confirmerBilanBtn.setText("Confirmer Bilan");
        confirmerBilanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmerBilanBtnActionPerformed(evt);
            }
        });
        AuthPanel.add(confirmerBilanBtn);
        confirmerBilanBtn.setBounds(40, 330, 180, 50);

        confirmerStockBtn.setText("Confirmer Stock");
        confirmerStockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmerStockBtnActionPerformed(evt);
            }
        });
        AuthPanel.add(confirmerStockBtn);
        confirmerStockBtn.setBounds(40, 330, 180, 50);

        confirmerActionBtn.setText("Confirmer Action");
        confirmerActionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmerActionBtnActionPerformed(evt);
            }
        });
        AuthPanel.add(confirmerActionBtn);
        confirmerActionBtn.setBounds(40, 330, 180, 50);

        txt_username.setBackground(new java.awt.Color(30, 130, 82));
        txt_username.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        txt_username.setForeground(new java.awt.Color(30, 130, 82));
        txt_username.setOpaque(false);
        AuthPanel.add(txt_username);
        txt_username.setBounds(140, 110, 220, 50);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/passwordbg.png"))); // NOI18N
        jLabel6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jLabel6KeyTyped(evt);
            }
        });
        AuthPanel.add(jLabel6);
        jLabel6.setBounds(140, 190, 220, 50);

        txt_password.setBackground(new java.awt.Color(30, 130, 82));
        txt_password.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        txt_password.setForeground(new java.awt.Color(255, 250, 240));
        txt_password.setBorder(null);
        txt_password.setCaretColor(new java.awt.Color(30, 130, 82));
        txt_password.setSelectedTextColor(new java.awt.Color(30, 130, 82));
        txt_password.setSelectionColor(new java.awt.Color(255, 250, 240));
        AuthPanel.add(txt_password);
        txt_password.setBounds(160, 200, 190, 30);

        errormsg.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        errormsg.setForeground(new java.awt.Color(224, 73, 51));
        errormsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AuthPanel.add(errormsg);
        errormsg.setBounds(30, 270, 450, 30);

        getContentPane().add(AuthPanel);
        AuthPanel.setBounds(290, 270, 520, 410);

        jPanel1.setBackground(new java.awt.Color(255, 250, 240));
        jPanel1.setMaximumSize(new java.awt.Dimension(1280, 800));
        jPanel1.setMinimumSize(new java.awt.Dimension(1280, 800));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 800));
        jPanel1.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/historiquebg.png"))); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(140, 390, 840, 680);

        jTable1.setBackground(new java.awt.Color(255, 250, 240));
        jTable1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jTable1.setForeground(new java.awt.Color(5, 45, 26));
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
        jTable1.setGridColor(new java.awt.Color(255, 250, 240));
        jTable1.setSelectionBackground(new java.awt.Color(30, 130, 82));
        jTable1.setSelectionForeground(new java.awt.Color(255, 250, 240));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(140, 390, 810, 490);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/imprimerbtn.png"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(1010, 110, 230, 70);

        imprimer.setBackground(new java.awt.Color(0, 0, 153));
        imprimer.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        imprimer.setForeground(new java.awt.Color(255, 255, 255));
        imprimer.setText("imprimer");
        imprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimerActionPerformed(evt);
            }
        });
        jPanel1.add(imprimer);
        imprimer.setBounds(1020, 120, 180, 50);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/exporterbtn.png"))); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(1010, 190, 230, 70);

        export.setBackground(new java.awt.Color(0, 0, 153));
        export.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        export.setForeground(new java.awt.Color(255, 255, 255));
        export.setText("export");
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });
        jPanel1.add(export);
        export.setBounds(1020, 200, 180, 50);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/recherchebtn.png"))); // NOI18N
        jPanel1.add(jLabel11);
        jLabel11.setBounds(810, 285, 200, 70);

        actionrech.setBackground(new java.awt.Color(0, 0, 153));
        actionrech.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        actionrech.setForeground(new java.awt.Color(255, 255, 255));
        actionrech.setText("action");
        actionrech.setBorder(null);
        actionrech.setBorderPainted(false);
        actionrech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionrechActionPerformed(evt);
            }
        });
        jPanel1.add(actionrech);
        actionrech.setBounds(820, 300, 180, 40);

        stockrech.setBackground(new java.awt.Color(0, 0, 153));
        stockrech.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        stockrech.setForeground(new java.awt.Color(255, 255, 255));
        stockrech.setText("stock");
        stockrech.setBorder(null);
        stockrech.setBorderPainted(false);
        stockrech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockrechActionPerformed(evt);
            }
        });
        jPanel1.add(stockrech);
        stockrech.setBounds(820, 300, 180, 40);

        total.setBackground(new java.awt.Color(0, 0, 153));
        total.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setText("total");
        total.setBorder(null);
        total.setBorderPainted(false);
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });
        jPanel1.add(total);
        total.setBounds(820, 300, 180, 40);

        de.setBackground(new java.awt.Color(255, 255, 255));
        de.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        de.setToolTipText("");
        de.setDateFormatString("dd-MM-yyyy");
        de.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        de.setOpaque(false);
        de.setPreferredSize(new java.awt.Dimension(150, 50));
        de.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deMouseClicked(evt);
            }
        });
        jPanel1.add(de);
        de.setBounds(200, 220, 160, 40);

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(5, 45, 26));
        jLabel3.setText("De");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(150, 219, 30, 40);

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(5, 45, 26));
        jLabel4.setText("A");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(430, 230, 40, 14);

        labelVendus.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        labelVendus.setForeground(new java.awt.Color(30, 130, 82));
        labelVendus.setText("Vendus ");
        jPanel1.add(labelVendus);
        labelVendus.setBounds(1010, 390, 110, 29);

        labelVedus2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/textfieldbg.png"))); // NOI18N
        jPanel1.add(labelVedus2);
        labelVedus2.setBounds(1010, 420, 200, 60);

        txVendus.setEditable(false);
        txVendus.setBackground(new java.awt.Color(255, 250, 240));
        txVendus.setFont(new java.awt.Font("Roboto Medium", 0, 28)); // NOI18N
        txVendus.setForeground(new java.awt.Color(5, 45, 26));
        txVendus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txVendus.setText("0");
        txVendus.setBorder(null);
        txVendus.setFocusable(false);
        txVendus.setName(""); // NOI18N
        txVendus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txVendusMouseClicked(evt);
            }
        });
        jPanel1.add(txVendus);
        txVendus.setBounds(1020, 430, 180, 40);

        labelBrut.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        labelBrut.setForeground(new java.awt.Color(30, 130, 82));
        labelBrut.setText("Brut ");
        jPanel1.add(labelBrut);
        labelBrut.setBounds(1010, 500, 150, 30);

        labelBrut2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/textfieldbg.png"))); // NOI18N
        jPanel1.add(labelBrut2);
        labelBrut2.setBounds(1010, 530, 200, 60);

        txTotal.setEditable(false);
        txTotal.setBackground(new java.awt.Color(255, 250, 240));
        txTotal.setFont(new java.awt.Font("Roboto Medium", 0, 28)); // NOI18N
        txTotal.setForeground(new java.awt.Color(5, 45, 26));
        txTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txTotal.setText("0");
        txTotal.setBorder(null);
        txTotal.setFocusable(false);
        txTotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txTotalMouseClicked(evt);
            }
        });
        txTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTotalActionPerformed(evt);
            }
        });
        jPanel1.add(txTotal);
        txTotal.setBounds(1020, 540, 180, 40);

        labelNet.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        labelNet.setForeground(new java.awt.Color(30, 130, 82));
        labelNet.setText("Net ");
        jPanel1.add(labelNet);
        labelNet.setBounds(1010, 620, 150, 30);

        labelNet2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/textfieldbg.png"))); // NOI18N
        jPanel1.add(labelNet2);
        labelNet2.setBounds(1010, 650, 200, 60);

        net.setEditable(false);
        net.setBackground(new java.awt.Color(255, 250, 240));
        net.setFont(new java.awt.Font("Roboto Medium", 0, 28)); // NOI18N
        net.setForeground(new java.awt.Color(5, 45, 26));
        net.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        net.setText("0");
        net.setBorder(null);
        net.setFocusable(false);
        net.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                netMouseClicked(evt);
            }
        });
        net.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                netActionPerformed(evt);
            }
        });
        jPanel1.add(net);
        net.setBounds(1020, 660, 190, 40);

        a.setBackground(new java.awt.Color(255, 255, 255));
        a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        a.setToolTipText("");
        a.setDateFormatString("dd-MM-yyyy");
        a.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        a.setOpaque(false);
        a.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel1.add(a);
        a.setBounds(470, 220, 160, 40);

        Barsearch.setBackground(new java.awt.Color(235, 231, 224));
        Barsearch.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Barsearch.setForeground(new java.awt.Color(5, 45, 26));
        Barsearch.setBorder(null);
        Barsearch.setSelectedTextColor(new java.awt.Color(255, 250, 240));
        Barsearch.setSelectionColor(new java.awt.Color(5, 45, 26));
        Barsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BarsearchKeyTyped(evt);
            }
        });
        jPanel1.add(Barsearch);
        Barsearch.setBounds(160, 305, 470, 30);

        searchbarbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/searchbarbg.png"))); // NOI18N
        jPanel1.add(searchbarbg);
        searchbarbg.setBounds(140, 290, 560, 60);

        action.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/action.png"))); // NOI18N
        jPanel1.add(action);
        action.setBounds(148, 120, 550, 60);

        stock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/stockh.png"))); // NOI18N
        jPanel1.add(stock);
        stock.setBounds(148, 120, 550, 60);

        bilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/bilan.png"))); // NOI18N
        jPanel1.add(bilan);
        bilan.setBounds(148, 120, 550, 60);

        bilanbtn.setText("jButton1");
        bilanbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilanbtnActionPerformed(evt);
            }
        });
        jPanel1.add(bilanbtn);
        bilanbtn.setBounds(154, 130, 130, 40);

        stockhbtn.setText("jButton1");
        stockhbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockhbtnActionPerformed(evt);
            }
        });
        jPanel1.add(stockhbtn);
        stockhbtn.setBounds(312, 130, 130, 40);

        actionhbtn.setText("jButton1");
        actionhbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionhbtnActionPerformed(evt);
            }
        });
        jPanel1.add(actionhbtn);
        actionhbtn.setBounds(470, 130, 130, 40);

        ViderBilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/ViderBilanBtn.png"))); // NOI18N
        jPanel1.add(ViderBilan);
        ViderBilan.setBounds(1010, 720, 200, 70);

        ViderStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/ViderStockBtn.png"))); // NOI18N
        jPanel1.add(ViderStock);
        ViderStock.setBounds(1010, 720, 200, 70);

        ViderAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/ViderActionBtn.png"))); // NOI18N
        jPanel1.add(ViderAction);
        ViderAction.setBounds(1010, 720, 200, 70);

        ViderBilanBtn.setText("vider bilan");
        ViderBilanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViderBilanBtnActionPerformed(evt);
            }
        });
        jPanel1.add(ViderBilanBtn);
        ViderBilanBtn.setBounds(1070, 730, 140, 50);

        ViderStockBtn.setText("vider stock");
        ViderStockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViderStockBtnActionPerformed(evt);
            }
        });
        jPanel1.add(ViderStockBtn);
        ViderStockBtn.setBounds(1030, 730, 140, 50);

        ViderActionBtn.setText("vider action");
        ViderActionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViderActionBtnActionPerformed(evt);
            }
        });
        jPanel1.add(ViderActionBtn);
        ViderActionBtn.setBounds(1030, 730, 140, 50);

        ViderBilan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/retour.png"))); // NOI18N
        jPanel1.add(ViderBilan1);
        ViderBilan1.setBounds(1010, 770, 200, 130);

        retour_product.setText("jButton1");
        retour_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retour_productActionPerformed(evt);
            }
        });
        jPanel1.add(retour_product);
        retour_product.setBounds(1010, 810, 190, 50);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1280, 940);

        Background.setBackground(new java.awt.Color(255, 250, 240));
        Background.setForeground(new java.awt.Color(255, 250, 240));
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1280, 800);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:
        System.err.println(de.getDate());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault());

        String q = "";

        if (Barsearch.getText() != null) { //Si la barsearch est non vide
            if (de.getDate() != null && a.getDate() != null) { // les 2 dates sont selectionnées
                String deb = sdf.format(de.getDate());
                String fin = sdf.format(a.getDate());
                q = "Select * from historique WHERE Nom LIKE '%" + Barsearch.getText() + "%' AND Date_Sortie >= '" + deb + "' AND Date_Sortie <= '" + fin + "' "
                        + " OR Categorie LIKE '%" + Barsearch.getText() + "%' AND Date_Sortie >= '" + deb + "' AND Date_Sortie <= '" + fin + "' "
                        + " OR user LIKE '%" + Barsearch.getText() + "%' AND Date_Sortie >= '" + deb + "' AND Date_Sortie <= '" + fin + "' "
                        + " OR Barcode LIKE '%" + Barsearch.getText() + "%' AND Date_Sortie >= '" + deb + "' AND Date_Sortie <= '" + fin + "' ";
            } else if (de.getDate() != null) { // seule la date debut est selectionnée
                String deb = sdf.format(de.getDate());

                q = "Select * from historique WHERE Nom LIKE '%" + Barsearch.getText() + "%' AND Date_Sortie >= '" + deb + "' "
                        + " OR Categorie LIKE '%" + Barsearch.getText() + "%' AND Date_Sortie >= '" + deb + "' "
                        + " OR user LIKE '%" + Barsearch.getText() + "%' AND Date_Sortie >= '" + deb + "' "
                        + " OR Barcode LIKE '%" + Barsearch.getText() + "%' AND Date_Sortie >= '" + deb + "' ";
            } else if (a.getDate() != null) { // seule la date fin est selectionnée

                String fin = sdf.format(a.getDate());
                q = "Select * from historique WHERE Nom LIKE '%" + Barsearch.getText() + "%' AND Date_Sortie <= '" + fin + "' "
                        + " OR Categorie LIKE '%" + Barsearch.getText() + "%' AND Date_Sortie <= '" + fin + "' "
                        + " OR user LIKE '%" + Barsearch.getText() + "%' AND Date_Sortie <= '" + fin + "' "
                        + " OR Barcode LIKE '%" + Barsearch.getText() + "%' AND Date_Sortie <= '" + fin + "' ";
            } else {
                q = "Select * from historique WHERE Nom LIKE '%" + Barsearch.getText() + "%' "
                        + " OR Categorie LIKE '%" + Barsearch.getText() + "%' "
                        + " OR user LIKE '%" + Barsearch.getText() + "%' "
                        + " OR Barcode LIKE '%" + Barsearch.getText() + "%' ";
            }
        } else if (de.getDate() != null && a.getDate() != null) {
            String deb = sdf.format(de.getDate());
            String fin = sdf.format(a.getDate());// les 2 dates sont selectionnées
            q = "Select * from historique  Date_Sortie >= '" + deb + "' AND Date_Sortie <= '" + fin + "' ";

        } else if (de.getDate() != null) { // seule la date debut est selectionnée
            String deb = sdf.format(de.getDate());

            q = "Select * from historique WHERE  Date_Sortie >= '" + deb + "' ";
        } else if (a.getDate() != null) { // seule la date fin est selectionnée

            String fin = sdf.format(a.getDate());
            q = "Select * from historique WHERE Date_Sortie <= '" + fin + "' ";
        } else {
            q = "Select * from historique ";
        }
        rs = db.exécutionQuery(q);
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
         remplireVenduBrutNet();
    }//GEN-LAST:event_totalActionPerformed

    public void remplireVenduBrutNet() {
        
        int qte = 0;
        Double brut = 0.000;
        Double net = 0.000;

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        df.setMinimumFractionDigits(3);

        for (int k = 0; k < jTable1.getRowCount(); k++) { //compte le nombre d'articles vendus
            String b = String.valueOf(jTable1.getValueAt(k, 4));
            Integer d = Integer.parseInt(b);
            qte = qte + d;
        }
        txVendus.setText(String.valueOf(qte));

        for (int k = 0; k < jTable1.getRowCount(); k++) { //compte le brut ( somme des totals )
                String b; 
                Double d;
            
                try {
                
                    b = String.valueOf(jTable1.getValueAt(k, 7));
                    d = Double.parseDouble(b);
                    brut = brut + d;
                    
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            
        }
        //System.out.println("brut= " + brut);
        String brutstring = df.format(brut).replace(",", ".");
        System.out.println("brutstring = " + brutstring);
        txTotal.setText(brutstring);

        for (int k = 0; k < jTable1.getRowCount(); k++) { //compte le net
            
            try {
                String b = String.valueOf(jTable1.getValueAt(k, 6));
                String p = String.valueOf(jTable1.getValueAt(k, 7));
                Double pb = Double.parseDouble(b);
                Double pv = Double.parseDouble(p);
                net = net + pv - pb;
                
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            
            
        }
        //System.out.println("net = " + net);
        String netstring = df.format(net).replace(",", ".");

        System.out.println("netstring = " + netstring);
        this.net.setText(netstring);

    }

    private void imprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimerActionPerformed
        // TODO add your handling code here
        MessageFormat header = new MessageFormat("Liste des produits entrantes");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            jTable1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Erreur d'impression ", e.getMessage());
        }
    }//GEN-LAST:event_imprimerActionPerformed

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(Historique.this);
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
    }//GEN-LAST:event_exportActionPerformed

    private void txTotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txTotalMouseClicked
        // TODO add your handling code here:

        //  txTotal.setText(Double.toString(Total));

    }//GEN-LAST:event_txTotalMouseClicked

    private void txVendusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txVendusMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_txVendusMouseClicked

    private void netMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_netMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_netMouseClicked

    private void deMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deMouseClicked
        // TODO add your handling code here:
        tableBilan();
    }//GEN-LAST:event_deMouseClicked

    private void txTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTotalActionPerformed

    private void netActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_netActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_netActionPerformed

    private void BarsearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BarsearchKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_BarsearchKeyTyped

    private void bilanbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilanbtnActionPerformed
        // TODO add your handling code here:
        Barsearch.setText("");

        ViderBilanBtn.show();
        ViderStockBtn.hide();
        ViderActionBtn.hide();

        ViderBilan.show();
        ViderStock.hide();
        ViderAction.hide();

        // bouton bilan actif
        bilan.show();
        stock.hide();
        action.hide();
        //.............
        // label historique
        labelBrut.show();
        labelBrut2.show();
        txTotal.show();
        labelVendus.show();
        labelVedus2.show();
        txVendus.show();
        labelNet.show();
        labelNet2.show();
        net.show();
        //.............
        // bouton recherche bilan actif
        total.show();
        stockrech.hide();
        actionrech.hide();
        //........

        tableBilan();

    }//GEN-LAST:event_bilanbtnActionPerformed

    private void stockhbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockhbtnActionPerformed
        // TODO add your handling code here:
        Barsearch.setText("");

        ViderBilanBtn.hide();
        ViderStockBtn.show();
        ViderActionBtn.hide();

        ViderBilan.hide();
        ViderStock.show();
        ViderAction.hide();

        // bouton stock actif
        bilan.hide();
        stock.show();
        action.hide();
        //.............
        // label historique
        labelBrut.hide();
        labelBrut2.hide();
        txTotal.hide();
        labelVendus.hide();
        labelVedus2.hide();
        txVendus.hide();
        labelNet.hide();
        labelNet2.hide();
        net.hide();
        //.............
        // bouton recherche stock actif
        total.hide();
        stockrech.show();
        actionrech.hide();
        //........

        tableStock(); //dessine table Stock 
    }//GEN-LAST:event_stockhbtnActionPerformed

    private void actionhbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionhbtnActionPerformed
        // TODO add your handling code here:
        Barsearch.setText("");

        ViderBilanBtn.hide();
        ViderStockBtn.hide();
        ViderActionBtn.show();

        ViderBilan.hide();
        ViderStock.hide();
        ViderAction.show();

        // bouton action actif
        bilan.hide();
        stock.hide();
        action.show();
        //.............
        // label historique
        labelBrut.hide();
        labelBrut2.hide();
        txTotal.hide();
        labelVendus.hide();
        labelVedus2.hide();
        txVendus.hide();
        labelNet.hide();
        labelNet2.hide();
        net.hide();
        //.............
        // bouton recherche bilan actif
        total.hide();
        stockrech.hide();
        actionrech.show();
        //........

        tableAction();
    }//GEN-LAST:event_actionhbtnActionPerformed

    private void stockrechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockrechActionPerformed
        // TODO add your handling code here:

        System.err.println(de.getDate());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault());

        String q = "";

        if (Barsearch.getText() != null) { //Si la barsearch est non vide
            if (de.getDate() != null && a.getDate() != null) { // les 2 dates sont selectionnées
                String deb = sdf.format(de.getDate());
                String fin = sdf.format(a.getDate());

                q = "Select * from stock_historique WHERE Nom LIKE '%" + Barsearch.getText() + "%' AND Date_ajout >= '" + deb + "' AND Date_ajout <= '" + fin + "' "
                        + " OR User LIKE '%" + Barsearch.getText() + "%' AND Date_ajout >= '" + deb + "' AND Date_ajout <= '" + fin + "' "
                        + " OR Barcode LIKE '%" + Barsearch.getText() + "%' AND Date_ajout >= '" + deb + "' AND Date_ajout <= '" + fin + "' ";
            } else if (de.getDate() != null) { // seule la date debut est selectionnée
                String deb = sdf.format(de.getDate());
                q = "Select * from stock_historique WHERE Nom LIKE '%" + Barsearch.getText() + "%' AND Date_ajout >= '" + deb + "' "
                        + " OR User LIKE '%" + Barsearch.getText() + "%' AND Date_ajout >= '" + deb + "' "
                        + " OR Barcode LIKE '%" + Barsearch.getText() + "%' AND Date_ajout >= '" + deb + "' ";
            } else if (a.getDate() != null) { // seule la date fin est selectionnée
                String fin = sdf.format(a.getDate());
                q = "Select * from stock_historique WHERE Nom LIKE '%" + Barsearch.getText() + "%' AND Date_ajout <= '" + fin + "' "
                        + " OR User LIKE '%" + Barsearch.getText() + "%' AND Date_ajout <= '" + fin + "' "
                        + " OR Barcode LIKE '%" + Barsearch.getText() + "%' AND Date_ajout <= '" + fin + "' ";
            } else { //aucune date n'est selectionnée
                q = "Select * from stock_historique WHERE Nom LIKE '%" + Barsearch.getText() + "%' "
                        + " OR User LIKE '%" + Barsearch.getText() + "%' "
                        + " OR Barcode LIKE '%" + Barsearch.getText() + "%' ";
            }
        } else if (de.getDate() != null && a.getDate() != null) { // les 2 dates sont selectionnées
            String deb = sdf.format(de.getDate());
            String fin = sdf.format(a.getDate());
            q = "Select * from stock_historique  Date_ajout >= '" + deb + "' AND Date_ajout <= '" + fin + "' ";

        } else if (de.getDate() != null) { // seule la date debut est selectionnée
            String deb = sdf.format(de.getDate());
            q = "Select * from stock_historique WHERE  Date_ajout >= '" + deb + "' ";
        } else if (a.getDate() != null) { // seule la date fin est selectionnée
            String fin = sdf.format(a.getDate());
            q = "Select * from stock_historique WHERE Date_ajout <= '" + fin + "' ";
        } else {
            q = "Select * from stock_historique ";
        }
        rs = db.exécutionQuery(q);
        jTable1.setModel(new ResultSetTableModel(rs));

        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(45);

        jTable1.setRowHeight(30);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
         //remplireVenduBrutNet(); 
    }//GEN-LAST:event_stockrechActionPerformed

    private void actionrechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionrechActionPerformed
        // TODO add your handling code here:
        System.err.println(de.getDate());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault());

        String q = "";

        if (Barsearch.getText() != null) { //Si la barsearch est non vide
            if (de.getDate() != null && a.getDate() != null) { // les 2 dates sont selectionnées
                String deb = sdf.format(de.getDate());
                String fin = sdf.format(a.getDate());

                q = "Select * from action WHERE type_action LIKE '%" + Barsearch.getText() + "%' AND Date >= '" + deb + "' AND Date <= '" + fin + "' "
                        + " OR user LIKE '%" + Barsearch.getText() + "%' AND Date >= '" + deb + "' AND Date <= '" + fin + "' "
                        + " OR description LIKE '%" + Barsearch.getText() + "%' AND Date >= '" + deb + "' AND Date <= '" + fin + "' ";
            } else if (de.getDate() != null) { // seule la date debut est selectionnée
                String deb = sdf.format(de.getDate());
                q = "Select * from action WHERE type_action LIKE '%" + Barsearch.getText() + "%' AND Date >= '" + deb + "' "
                        + " OR user LIKE '%" + Barsearch.getText() + "%' AND Date >= '" + deb + "' "
                        + " OR description LIKE '%" + Barsearch.getText() + "%' AND Date >= '" + deb + "' ";
            } else if (a.getDate() != null) { // seule la date fin est selectionnée
                String fin = sdf.format(a.getDate());
                q = "Select * from action WHERE type_action LIKE '%" + Barsearch.getText() + "%' AND Date <= '" + fin + "' "
                        + " OR user LIKE '%" + Barsearch.getText() + "%' AND Date <= '" + fin + "' "
                        + " OR description LIKE '%" + Barsearch.getText() + "%' AND Date <= '" + fin + "' ";
            } else { //aucune date n'est selectionnée
                q = "Select * from action WHERE type_action LIKE '%" + Barsearch.getText() + "%' "
                        + " OR user LIKE '%" + Barsearch.getText() + "%' "
                        + " OR description LIKE '%" + Barsearch.getText() + "%' ";
            }
        } else if (de.getDate() != null && a.getDate() != null) { // les 2 dates sont selectionnées
            String deb = sdf.format(de.getDate());
            String fin = sdf.format(a.getDate());
            q = "Select * from action  Date_ajout >= '" + deb + "' AND Date <= '" + fin + "' ";

        } else if (de.getDate() != null) { // seule la date debut est selectionnée
            String deb = sdf.format(de.getDate());
            q = "Select * from action WHERE  Date >= '" + deb + "' ";
        } else if (a.getDate() != null) { // seule la date fin est selectionnée
            String fin = sdf.format(a.getDate());
            q = "Select * from action WHERE Date <= '" + fin + "' ";
        } else {
            q = "Select * from action ";
        }
        rs = db.exécutionQuery(q);
        jTable1.setModel(new ResultSetTableModel(rs));

        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }//GEN-LAST:event_actionrechActionPerformed

    private void statbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statbtnActionPerformed
        // TODO add your handling code here:
        //statistique.getObj().setVisible(true);
        db.closeconnexion();
        statistique a = new statistique(utilisateur.getText());
        a.setVisible(true);
                this.dispose();

    }//GEN-LAST:event_statbtnActionPerformed

    private void usersbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersbtnActionPerformed
        // TODO add your handling code here:
        db.closeconnexion();
        Utilisateurs a = new Utilisateurs(utilisateur.getText());
        a.setVisible(true);
                this.dispose();

    }//GEN-LAST:event_usersbtnActionPerformed

    private void salesbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesbtnActionPerformed
        // TODO add your handling code here:

        // this.dispose();
    }//GEN-LAST:event_salesbtnActionPerformed

    private void inventorybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventorybtnActionPerformed
        // TODO add your handling code here:
        db.closeconnexion();
        Inventory a = new Inventory(utilisateur.getText());
        a.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_inventorybtnActionPerformed

    private void stockbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockbtnActionPerformed
        // TODO add your handling code here:
db.closeconnexion();
        stock a = new stock(utilisateur.getText());
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_stockbtnActionPerformed

    private void registerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerbtnActionPerformed
        // TODO add your handling code here:
        db.closeconnexion();
        POS a = new POS(utilisateur.getText());
        a.setVisible(true);
                this.dispose();

    }//GEN-LAST:event_registerbtnActionPerformed

    private void LogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBtnActionPerformed
        // TODO add your handling code here:

        Login a;
        try {
            db.queryDelete("caisse");
            a = new Login();

            // action logout
            String user = utilisateur.getText();
            String action = "log out";

            String Description = "";

            String[] actions = {"user", "type_action", "description", "Date", "Heure"};
            String[] inf3 = {user, action, Description, Date.getText(), Heure.getText()};
            System.out.println(db.queryInsert("action", actions, inf3));

            //..................................
            this.hide();
            a.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(POS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_LogoutBtnActionPerformed

    private void ViderBilanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViderBilanBtnActionPerformed
        // TODO add your handling code here:

        jPanel1.hide();

        AuthPanel.show();

        confirmerBilanBtn.show();
        confirmerStockBtn.hide();
        confirmerActionBtn.hide();
    }//GEN-LAST:event_ViderBilanBtnActionPerformed

    private void ViderStockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViderStockBtnActionPerformed
        // TODO add your handling code here:

        jPanel1.hide();

        AuthPanel.show();

        confirmerBilanBtn.hide();
        confirmerStockBtn.show();
        confirmerActionBtn.hide();
    }//GEN-LAST:event_ViderStockBtnActionPerformed

    private void CancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBtnActionPerformed
        // TODO add your handling code here:

        AuthPanel.hide();
        jPanel1.show();
    }//GEN-LAST:event_CancelBtnActionPerformed

    private void confirmerBilanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmerBilanBtnActionPerformed
        // TODO add your handling code here:
        String username = null, password = null, hak = null;
        rs = db.querySelectAll("utilisateur", "Login='" + txt_username.getSelectedItem() + "' and Password='" + txt_password.getText() + "' and type = 'directeur'");
        try {
            while (rs.next()) {
                username = rs.getString("Login");
                password = rs.getString("Password");
                hak = rs.getString("type");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (username == null && password == null) {
            getToolkit().beep();
            errormsg.setText("Le login ou le mot de passe est incorrect");
        } else {
            if (hak.equals("directeur")) {
                db.queryDelete("historique");
                AuthPanel.hide();
                jPanel1.show();
                tableBilan();
            }

        }
    }//GEN-LAST:event_confirmerBilanBtnActionPerformed

    private void jLabel6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel6KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6KeyTyped

    private void confirmerStockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmerStockBtnActionPerformed
        // TODO add your handling code here:
        String username = null, password = null, hak = null;
        rs = db.querySelectAll("utilisateur", "Login='" + txt_username.getSelectedItem() + "' and Password='" + txt_password.getText() + "' and type = 'directeur'");
        try {
            while (rs.next()) {
                username = rs.getString("Login");
                password = rs.getString("Password");
                hak = rs.getString("type");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (username == null && password == null) {
            getToolkit().beep();
            errormsg.setText("Le login ou le mot de passe est incorrect");
        } else {
            if (hak.equals("directeur")) {
                db.queryDelete("stock_historique");
                AuthPanel.hide();
                jPanel1.show();
                tableStock();
            }

        }
    }//GEN-LAST:event_confirmerStockBtnActionPerformed

    private void confirmerActionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmerActionBtnActionPerformed
        // TODO add your handling code here:
        String username = null, password = null, hak = null;
        rs = db.querySelectAll("utilisateur", "Login='" + txt_username.getSelectedItem() + "' and Password='" + txt_password.getText() + "' and type = 'directeur'");
        try {
            while (rs.next()) {
                username = rs.getString("Login");
                password = rs.getString("Password");
                hak = rs.getString("type");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (username == null && password == null) {
            getToolkit().beep();
            errormsg.setText("Le login ou le mot de passe est incorrect");
        } else {
            if (hak.equals("directeur")) {
                db.queryDelete("action");
                AuthPanel.hide();
                jPanel1.show();
                tableAction();
            }

        }
    }//GEN-LAST:event_confirmerActionBtnActionPerformed

    private void ViderActionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViderActionBtnActionPerformed
        // TODO add your handling code here:

        jPanel1.hide();

        AuthPanel.show();

        confirmerBilanBtn.hide();
        confirmerStockBtn.hide();
        confirmerActionBtn.show();
    }//GEN-LAST:event_ViderActionBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Credit a = new Credit(utilisateur.getText());
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void retour_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retour_productActionPerformed
        // add quantitie to stock :
        String q ="update produit_stock "
                + "set Quantite = Quantite +'"+Quantittie+"'"
                + "where Barcode ='"+Barcode +"'";
        System.out.println( db.exécutionUpdate(q));
        // supprimer commande de l historique 
        db.queryDelete("Historique", "id=" + id);
            actualiser();
        
        
    }//GEN-LAST:event_retour_productActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        id = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        Barcode = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2));
        Nom = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        Quantittie = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4));
       
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(Historique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Historique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Historique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Historique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Historique("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AuthPanel;
    private javax.swing.JPanel Background;
    private javax.swing.JTextField Barsearch;
    private javax.swing.JButton CancelBtn;
    private javax.swing.JLabel Date1;
    private javax.swing.JLabel Heure1;
    private javax.swing.JLabel Logo;
    private javax.swing.JButton LogoutBtn;
    private javax.swing.JLabel LogoutBtnbg;
    private javax.swing.JPanel Session;
    private javax.swing.JLabel ViderAction;
    private javax.swing.JButton ViderActionBtn;
    private javax.swing.JLabel ViderBilan;
    private javax.swing.JLabel ViderBilan1;
    private javax.swing.JButton ViderBilanBtn;
    private javax.swing.JLabel ViderStock;
    private javax.swing.JButton ViderStockBtn;
    private com.toedter.calendar.JDateChooser a;
    private javax.swing.JLabel action;
    private javax.swing.JButton actionhbtn;
    private javax.swing.JButton actionrech;
    private javax.swing.JPanel barpanel;
    private javax.swing.JLabel bilan;
    private javax.swing.JButton bilanbtn;
    private javax.swing.JButton confirmerActionBtn;
    private javax.swing.JButton confirmerBilanBtn;
    private javax.swing.JButton confirmerStockBtn;
    private com.toedter.calendar.JDateChooser de;
    private javax.swing.JLabel errormsg;
    private javax.swing.JButton export;
    private javax.swing.JButton imprimer;
    private javax.swing.JButton inventorybtn;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelBrut;
    private javax.swing.JLabel labelBrut2;
    private javax.swing.JLabel labelNet;
    private javax.swing.JLabel labelNet2;
    private javax.swing.JLabel labelVedus2;
    private javax.swing.JLabel labelVendus;
    private javax.swing.JTextField net;
    private javax.swing.JButton registerbtn;
    private javax.swing.JButton retour_product;
    private javax.swing.JButton salesbtn;
    private javax.swing.JLabel searchbarbg;
    private javax.swing.JButton statbtn;
    private javax.swing.JLabel stock;
    private javax.swing.JButton stockbtn;
    private javax.swing.JButton stockhbtn;
    private javax.swing.JButton stockrech;
    private javax.swing.JButton total;
    private javax.swing.JTextField txTotal;
    private javax.swing.JTextField txVendus;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JComboBox txt_username;
    private javax.swing.JButton usersbtn;
    private javax.swing.JLabel utilisateur;
    // End of variables declaration//GEN-END:variables
}

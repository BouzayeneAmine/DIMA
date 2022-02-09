/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnew.app;

import BDD.ConnectComPort;
import BDD.DbConnection;
import BDD.Parametre;
import BDD.ResultSetTableModel;
import java.awt.Color;
import java.awt.Font;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableCellRenderer;
import static java.lang.Thread.sleep;

/**
 *
 * @author asuss
 */
public class Inventory extends javax.swing.JFrame {

    public static Inventory obj = null;
    ResultSet rs;
    DbConnection db;
    ConnectComPort dc;
    private static String id;
    private static String nom;
    private static String categorie;
    private static String prix;
    private static String cout;
    private static String max;
    private static String min;
    private static String barcode;
    private DefaultTableCellRenderer headerRenderer;
    private String selectedCat;

    public Inventory(String username) {
        initComponents();
        this.setDefaultCloseOperation(0);

        headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(30, 130, 82));
        headerRenderer.setForeground(new Color(255, 250, 240));

        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        dc = new ConnectComPort();
        db.connexionDatabase();

        date();
        datecourante();
        table();
        utilisateur.setText(username);
        this.setExtendedState(this.MAXIMIZED_BOTH);

        if (isUser(username)) {

            jLabel20.hide();
            jLabel21.hide();
            jLabel22.hide();

            salesbtn.hide();
            usersbtn.hide();
            statbtn.hide();

            supprimerbtn.hide();
            supprimerbtnbg.hide();
        }

        Barsearch.requestFocus();
        selectedCat = "";

    }

    public boolean isUser(String username) {
        String q = "Select type from utilisateur where Login ='" + username + "'";
        rs = db.exécutionQuery(q);
        try {
            rs.next();
            ///System.err.println(rs.getString(1));
            if ((rs.getString(1).toLowerCase()).equals("utilisateur")) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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

    public void table() {
        String colon[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};
        rs = db.querySelectOrdre(colon, "produit", "nom");

        jTable1.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(Color.BLACK);
        jTable1.getTableHeader().setForeground(new Color(19, 35, 132));

        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.setRowHeight(30);

        jScrollPane1.getViewport().setBackground(new Color(255, 250, 240));
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }

    public static Inventory getObj() {
        if (obj == null) {

            obj = new Inventory("");
        }
        return obj;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Date = new javax.swing.JLabel();
        Heure = new javax.swing.JLabel();
        General_Panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        modifierPrduitbtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        ajouterProduitbtn = new javax.swing.JButton();
        supprimerbtnbg = new javax.swing.JLabel();
        supprimerbtn = new javax.swing.JButton();
        Barsearch = new javax.swing.JTextField();
        searchbarbg = new javax.swing.JLabel();
        category_bar = new javax.swing.JPanel();
        CatPanel = new javax.swing.JLabel();
        CatfruitssecsPanel = new javax.swing.JLabel();
        CatconservesPanel = new javax.swing.JLabel();
        CatpatesPanel = new javax.swing.JLabel();
        CataccPanel = new javax.swing.JLabel();
        CatrechargePanel = new javax.swing.JLabel();
        CatcigarettePanel = new javax.swing.JLabel();
        CattabacPanel = new javax.swing.JLabel();
        CatgouterPanel = new javax.swing.JLabel();
        CatboissonPanel = new javax.swing.JLabel();
        CateauPanel = new javax.swing.JLabel();
        FruitssecsBtn = new javax.swing.JButton();
        ConserveBtn = new javax.swing.JButton();
        PatesBtn = new javax.swing.JButton();
        AccBtn = new javax.swing.JButton();
        rechargeBtn = new javax.swing.JButton();
        cigaretteBtn = new javax.swing.JButton();
        tabacBtn = new javax.swing.JButton();
        gouterBtn = new javax.swing.JButton();
        boissonBtn = new javax.swing.JButton();
        eauBtn = new javax.swing.JButton();
        barpanel = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        statbtn = new javax.swing.JButton();
        usersbtn = new javax.swing.JButton();
        salesbtn = new javax.swing.JButton();
        inventorybtn = new javax.swing.JButton();
        stockbtn = new javax.swing.JButton();
        registerbtn = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Session = new javax.swing.JPanel();
        utilisateur = new javax.swing.JLabel();
        LogoutBtnbg = new javax.swing.JLabel();
        LogoutBtn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Heure1 = new javax.swing.JLabel();
        Date1 = new javax.swing.JLabel();

        Date.setText("jLabel4");

        Heure.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1280, 800));
        setMinimumSize(new java.awt.Dimension(1280, 800));
        setPreferredSize(new java.awt.Dimension(1280, 800));
        setSize(new java.awt.Dimension(1280, 800));
        getContentPane().setLayout(null);

        General_Panel.setBackground(new java.awt.Color(255, 250, 240));
        General_Panel.setMaximumSize(new java.awt.Dimension(1280, 800));
        General_Panel.setMinimumSize(new java.awt.Dimension(1280, 800));
        General_Panel.setPreferredSize(new java.awt.Dimension(1280, 800));
        General_Panel.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/stockbg.png"))); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        General_Panel.add(jLabel2);
        jLabel2.setBounds(140, 270, 680, 570);

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
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setOpaque(false);
        jTable1.setSelectionBackground(new java.awt.Color(30, 130, 82));
        jTable1.setSelectionForeground(new java.awt.Color(255, 250, 240));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        General_Panel.add(jScrollPane1);
        jScrollPane1.setBounds(140, 270, 680, 510);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/modifierInvtbtn.png"))); // NOI18N
        General_Panel.add(jLabel1);
        jLabel1.setBounds(940, 300, 230, 70);

        modifierPrduitbtn.setBackground(new java.awt.Color(0, 0, 102));
        modifierPrduitbtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        modifierPrduitbtn.setForeground(new java.awt.Color(255, 255, 255));
        modifierPrduitbtn.setText("modifier produits");
        modifierPrduitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierPrduitbtnActionPerformed(evt);
            }
        });
        General_Panel.add(modifierPrduitbtn);
        modifierPrduitbtn.setBounds(950, 310, 180, 50);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/ajouterbtn.png"))); // NOI18N
        General_Panel.add(jLabel3);
        jLabel3.setBounds(940, 380, 230, 70);

        ajouterProduitbtn.setBackground(new java.awt.Color(0, 0, 102));
        ajouterProduitbtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ajouterProduitbtn.setForeground(new java.awt.Color(255, 255, 255));
        ajouterProduitbtn.setText("ajouter produits");
        ajouterProduitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterProduitbtnActionPerformed(evt);
            }
        });
        General_Panel.add(ajouterProduitbtn);
        ajouterProduitbtn.setBounds(950, 390, 180, 50);

        supprimerbtnbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/supprimerbtn.png"))); // NOI18N
        General_Panel.add(supprimerbtnbg);
        supprimerbtnbg.setBounds(940, 460, 230, 70);

        supprimerbtn.setText("jButton1");
        supprimerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerbtnActionPerformed(evt);
            }
        });
        General_Panel.add(supprimerbtn);
        supprimerbtn.setBounds(950, 470, 180, 50);

        Barsearch.setBackground(new java.awt.Color(235, 231, 224));
        Barsearch.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Barsearch.setForeground(new java.awt.Color(5, 45, 26));
        Barsearch.setBorder(null);
        Barsearch.setOpaque(false);
        Barsearch.setSelectedTextColor(new java.awt.Color(255, 250, 240));
        Barsearch.setSelectionColor(new java.awt.Color(5, 45, 26));
        Barsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BarsearchActionPerformed(evt);
            }
        });
        Barsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BarsearchKeyReleased(evt);
            }
        });
        General_Panel.add(Barsearch);
        Barsearch.setBounds(160, 110, 470, 30);

        searchbarbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/searchbarbg.png"))); // NOI18N
        General_Panel.add(searchbarbg);
        searchbarbg.setBounds(140, 95, 560, 60);

        category_bar.setBackground(new java.awt.Color(255, 255, 255));
        category_bar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        category_bar.setOpaque(false);
        category_bar.setLayout(null);

        CatPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/CatPanel.png"))); // NOI18N
        category_bar.add(CatPanel);
        CatPanel.setBounds(0, 0, 500, 60);

        CatfruitssecsPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barfruitssecs.png"))); // NOI18N
        category_bar.add(CatfruitssecsPanel);
        CatfruitssecsPanel.setBounds(0, 0, 500, 60);

        CatconservesPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barconserve.png"))); // NOI18N
        category_bar.add(CatconservesPanel);
        CatconservesPanel.setBounds(0, 0, 500, 60);

        CatpatesPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barpates.png"))); // NOI18N
        category_bar.add(CatpatesPanel);
        CatpatesPanel.setBounds(0, 0, 500, 60);

        CataccPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/baraccessoires.png"))); // NOI18N
        category_bar.add(CataccPanel);
        CataccPanel.setBounds(0, 0, 500, 60);

        CatrechargePanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barrecharge.png"))); // NOI18N
        category_bar.add(CatrechargePanel);
        CatrechargePanel.setBounds(0, 0, 500, 60);

        CatcigarettePanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barcigarettes.png"))); // NOI18N
        category_bar.add(CatcigarettePanel);
        CatcigarettePanel.setBounds(0, 0, 500, 60);

        CattabacPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/bartabac.png"))); // NOI18N
        category_bar.add(CattabacPanel);
        CattabacPanel.setBounds(0, 0, 500, 60);

        CatgouterPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/bargouter.png"))); // NOI18N
        category_bar.add(CatgouterPanel);
        CatgouterPanel.setBounds(0, 0, 500, 60);

        CatboissonPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barboisson.png"))); // NOI18N
        category_bar.add(CatboissonPanel);
        CatboissonPanel.setBounds(0, 0, 500, 60);

        CateauPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/bareau.png"))); // NOI18N
        category_bar.add(CateauPanel);
        CateauPanel.setBounds(0, 0, 500, 60);

        FruitssecsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FruitssecsBtnActionPerformed(evt);
            }
        });
        category_bar.add(FruitssecsBtn);
        FruitssecsBtn.setBounds(440, 10, 50, 35);

        ConserveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConserveBtnActionPerformed(evt);
            }
        });
        category_bar.add(ConserveBtn);
        ConserveBtn.setBounds(380, 10, 60, 35);

        PatesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatesBtnActionPerformed(evt);
            }
        });
        category_bar.add(PatesBtn);
        PatesBtn.setBounds(340, 10, 40, 35);

        AccBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccBtnActionPerformed(evt);
            }
        });
        category_bar.add(AccBtn);
        AccBtn.setBounds(280, 10, 60, 35);

        rechargeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechargeBtnActionPerformed(evt);
            }
        });
        category_bar.add(rechargeBtn);
        rechargeBtn.setBounds(220, 10, 60, 35);

        cigaretteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cigaretteBtnActionPerformed(evt);
            }
        });
        category_bar.add(cigaretteBtn);
        cigaretteBtn.setBounds(160, 10, 60, 35);

        tabacBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tabacBtnActionPerformed(evt);
            }
        });
        category_bar.add(tabacBtn);
        tabacBtn.setBounds(130, 10, 40, 35);

        gouterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gouterBtnActionPerformed(evt);
            }
        });
        category_bar.add(gouterBtn);
        gouterBtn.setBounds(90, 10, 40, 35);

        boissonBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boissonBtnActionPerformed(evt);
            }
        });
        category_bar.add(boissonBtn);
        boissonBtn.setBounds(40, 10, 40, 35);

        eauBtn.setBackground(new java.awt.Color(255, 255, 255));
        eauBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        eauBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eauBtnActionPerformed(evt);
            }
        });
        category_bar.add(eauBtn);
        eauBtn.setBounds(5, 14, 35, 32);

        General_Panel.add(category_bar);
        category_bar.setBounds(140, 175, 800, 70);

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
        jLabel22.setBounds(10, 630, 60, 50);

        jLabel21.setBackground(new java.awt.Color(255, 250, 240));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/utilisateurs.png"))); // NOI18N
        jLabel21.setOpaque(true);
        barpanel.add(jLabel21);
        jLabel21.setBounds(10, 540, 60, 50);

        jLabel20.setBackground(new java.awt.Color(255, 250, 240));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/historique.png"))); // NOI18N
        jLabel20.setOpaque(true);
        barpanel.add(jLabel20);
        jLabel20.setBounds(10, 430, 60, 64);

        jLabel19.setBackground(new java.awt.Color(30, 130, 82));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/inventoryLight.png"))); // NOI18N
        jLabel19.setOpaque(true);
        barpanel.add(jLabel19);
        jLabel19.setBounds(10, 320, 60, 50);

        jLabel18.setBackground(new java.awt.Color(255, 250, 240));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/Stock.png"))); // NOI18N
        jLabel18.setOpaque(true);
        barpanel.add(jLabel18);
        jLabel18.setBounds(10, 210, 60, 50);

        jLabel16.setBackground(new java.awt.Color(255, 250, 240));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/cashregister.png"))); // NOI18N
        jLabel16.setOpaque(true);
        barpanel.add(jLabel16);
        jLabel16.setBounds(10, 120, 60, 50);

        jLabel5.setBackground(new java.awt.Color(30, 130, 82));
        jLabel5.setOpaque(true);
        barpanel.add(jLabel5);
        jLabel5.setBounds(0, 310, 80, 70);

        statbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        statbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statbtnActionPerformed(evt);
            }
        });
        barpanel.add(statbtn);
        statbtn.setBounds(10, 630, 60, 50);

        usersbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        usersbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersbtnActionPerformed(evt);
            }
        });
        barpanel.add(usersbtn);
        usersbtn.setBounds(10, 540, 60, 45);

        salesbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        salesbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesbtnActionPerformed(evt);
            }
        });
        barpanel.add(salesbtn);
        salesbtn.setBounds(10, 430, 60, 50);

        inventorybtn.setPreferredSize(new java.awt.Dimension(80, 80));
        inventorybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventorybtnActionPerformed(evt);
            }
        });
        barpanel.add(inventorybtn);
        inventorybtn.setBounds(10, 320, 60, 50);

        stockbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        stockbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockbtnActionPerformed(evt);
            }
        });
        barpanel.add(stockbtn);
        stockbtn.setBounds(10, 210, 60, 50);

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
        registerbtn.setBounds(10, 120, 60, 50);

        jLabel30.setBackground(new java.awt.Color(255, 250, 240));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/credit.png"))); // NOI18N
        jLabel30.setOpaque(true);
        barpanel.add(jLabel30);
        jLabel30.setBounds(10, 720, 60, 50);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        barpanel.add(jButton2);
        jButton2.setBounds(10, 730, 60, 30);

        General_Panel.add(barpanel);
        barpanel.setBounds(2, 0, 80, 800);

        jPanel1.setBackground(new java.awt.Color(255, 250, 240));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 130, 82), 2));
        jPanel1.setForeground(new java.awt.Color(30, 130, 82));
        jPanel1.setLayout(null);

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

        jPanel1.add(Session);
        Session.setBounds(90, 10, 270, 60);

        jLabel10.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(30, 130, 82));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("INVENTAIRE");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(820, 0, 330, 80);

        Heure1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Heure1.setForeground(new java.awt.Color(5, 45, 26));
        Heure1.setText("22:10");
        jPanel1.add(Heure1);
        Heure1.setBounds(380, 0, 100, 80);

        Date1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Date1.setForeground(new java.awt.Color(5, 45, 26));
        Date1.setText("31-12-2019");
        jPanel1.add(Date1);
        Date1.setBounds(510, 0, 100, 80);

        General_Panel.add(jPanel1);
        jPanel1.setBounds(50, 0, 1220, 80);

        getContentPane().add(General_Panel);
        General_Panel.setBounds(0, 0, 1280, 800);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modifierPrduitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierPrduitbtnActionPerformed
        // TODO add your handling code here:
        /*if(jTable1.getSelectedRow()!=-1)*/

        if (jTable1.getSelectedRow() >= 0) {
            id = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            nom = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
            barcode = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2));
            categorie = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3));
            prix = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 5));
            cout = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4));
            max = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 6));
            min = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 7));
            System.out.println(nom);
            EditProduct e = new EditProduct(utilisateur.getText());

            e.show();
            this.hide();
        }
    }//GEN-LAST:event_modifierPrduitbtnActionPerformed
    public static String getId() {
        return Inventory.id;
    }

    public static String getBarcode() {
        return Inventory.barcode;
    }

    public static String getname() {
        return Inventory.nom;
    }

    public static String getCat() {
        return Inventory.categorie;
    }

    public static String getPrix() {
        return Inventory.prix;
    }

    public static String getCout() {
        return Inventory.cout;
    }

    public static String getMax() {
        return Inventory.max;
    }

    public static String getMin() {
        return Inventory.min;
    }


    private void ajouterProduitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterProduitbtnActionPerformed
        // TODO add your handling code here:
        id = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        nom = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        barcode = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2));
        categorie = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3));
        prix = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 5));
        cout = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4));
        max = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 6));
        min = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 7));
        System.out.println(nom);

        AddProduct e = new AddProduct(utilisateur.getText());
        e.show();
        this.hide();
    }//GEN-LAST:event_ajouterProduitbtnActionPerformed

    private void BarsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BarsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BarsearchActionPerformed

    private void BarsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BarsearchKeyReleased
        // TODO add your handling code here:
        String a[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};

        String q = "Select Id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min "
                + "from produit "
                + "where "
                + "nom LIKE '%" + Barsearch.getText() + "%'"
                + "OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                + "OR Categorie LIKE '%" + Barsearch.getText() + "%' "
                + "OR Barcode LIKE '%" + Barsearch.getText() + "%' ";

        switch (selectedCat) {
            case "eau":
                q = "Select Id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min "
                        + "from produit "
                        + "where "
                        + "(nom LIKE '%" + Barsearch.getText() + "%'"
                        + "OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Categorie LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                        + "AND Categorie LIKE '%" + selectedCat + "%' "
                        + "ORDER BY id DESC";
                break;
            case "boisson":
                q = "Select Id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min "
                        + "from produit "
                        + "where "
                        + "(nom LIKE '%" + Barsearch.getText() + "%'"
                        + "OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Categorie LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                        + "AND Categorie LIKE '%" + selectedCat + "%' "
                        + "ORDER BY id DESC";

                break;
            case "gouter":
                q = "Select Id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min "
                        + "from produit "
                        + "where "
                        + "(nom LIKE '%" + Barsearch.getText() + "%'"
                        + "OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Categorie LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                        + "AND Categorie LIKE '%" + selectedCat + "%' "
                        + "ORDER BY id DESC";

                break;
            case "tabac":
                q = "Select Id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min "
                        + "from produit "
                        + "where "
                        + "(nom LIKE '%" + Barsearch.getText() + "%'"
                        + "OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Categorie LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                        + "AND Categorie LIKE '%" + selectedCat + "%' "
                        + "ORDER BY id DESC";

                break;
            case "cigarette":
                q = "Select Id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min "
                        + "from produit "
                        + "where "
                        + "(nom LIKE '%" + Barsearch.getText() + "%'"
                        + "OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Categorie LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                        + "AND nom LIKE '%" + selectedCat + "%' "
                        + "ORDER BY id DESC";

                break;
            case "recharge téléphonique":
                q = "Select Id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min "
                        + "from produit "
                        + "where "
                        + "(nom LIKE '%" + Barsearch.getText() + "%'"
                        + "OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Categorie LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                        + "AND Categorie LIKE '%" + selectedCat + "%' "
                        + "ORDER BY id DESC";

                break;
            case "accessoire":
                q = "Select Id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min "
                        + "from produit "
                        + "where "
                        + "(nom LIKE '%" + Barsearch.getText() + "%'"
                        + "OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Categorie LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                        + "AND Categorie LIKE '%" + selectedCat + "%' "
                        + "ORDER BY id DESC";

                break;
            case "pates":
                q = "Select Id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min "
                        + "from produit "
                        + "where "
                        + "(nom LIKE '%" + Barsearch.getText() + "%'"
                        + "OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Categorie LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                        + "AND Categorie LIKE '%" + selectedCat + "%' "
                        + "ORDER BY id DESC";

                break;
            case "conserve":
                q = "Select Id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min "
                        + "from produit "
                        + "where "
                        + "(nom LIKE '%" + Barsearch.getText() + "%'"
                        + "OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Categorie LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                        + "AND Categorie LIKE '%" + selectedCat + "%' "
                        + "ORDER BY id DESC";

                break;
            case "fruits secs":
                q = "Select Id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min "
                        + "from produit "
                        + "where "
                        + "(nom LIKE '%" + Barsearch.getText() + "%'"
                        + "OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Categorie LIKE '%" + Barsearch.getText() + "%' "
                        + "OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                        + "AND Categorie LIKE '%" + selectedCat + "%' "
                        + "ORDER BY id DESC";

                break;
            default:
                break;
        }

        rs = db.exécutionQuery(q);
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        evt.consume();

    }//GEN-LAST:event_BarsearchKeyReleased

    private void AccBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccBtnActionPerformed
        // TODO add your handling code here:

        //change photo of the bar
        CatPanel.hide();
        CateauPanel.hide();
        CatboissonPanel.hide();
        CatgouterPanel.hide();
        CattabacPanel.hide();
        CatcigarettePanel.hide();
        CatrechargePanel.hide();
        CataccPanel.show();
        CatpatesPanel.hide();
        CatconservesPanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "accessoire" + "%' ");

        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        selectedCat = "accessoire";
    }//GEN-LAST:event_AccBtnActionPerformed

    private void rechargeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechargeBtnActionPerformed
        // TODO add your handling code here:

        //change photo of the bar
        CatPanel.hide();
        CateauPanel.hide();
        CatboissonPanel.hide();
        CatgouterPanel.hide();
        CattabacPanel.hide();
        CatcigarettePanel.hide();
        CatrechargePanel.show();
        CataccPanel.hide();
        CatpatesPanel.hide();
        CatconservesPanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "recharge téléphonique" + "%' ");

        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        selectedCat = "recharge téléphonique";

    }//GEN-LAST:event_rechargeBtnActionPerformed

    private void cigaretteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cigaretteBtnActionPerformed
        // TODO add your handling code here:

        //change photo of the bar
        CatPanel.hide();
        CateauPanel.hide();
        CatboissonPanel.hide();
        CatgouterPanel.hide();
        CattabacPanel.hide();
        CatcigarettePanel.show();
        CatrechargePanel.hide();
        CataccPanel.hide();
        CatpatesPanel.hide();
        CatconservesPanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};
        rs = db.fcSelectCommand(a, "produit", "nom LIKE '%" + "cigarette" + "%' ");

        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        selectedCat = "cigarette";
    }//GEN-LAST:event_cigaretteBtnActionPerformed

    private void tabacBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tabacBtnActionPerformed
        // TODO add your handling code here:

        //change photo of the bar
        CatPanel.hide();
        CateauPanel.hide();
        CatboissonPanel.hide();
        CatgouterPanel.hide();
        CattabacPanel.show();
        CatcigarettePanel.hide();
        CatrechargePanel.hide();
        CataccPanel.hide();
        CatpatesPanel.hide();
        CatconservesPanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "tabac" + "%' ");

        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        selectedCat = "tabac";
    }//GEN-LAST:event_tabacBtnActionPerformed

    private void gouterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gouterBtnActionPerformed
        // TODO add your handling code here:

        //change photo of the bar
        CatPanel.hide();
        CateauPanel.hide();
        CatboissonPanel.hide();
        CatgouterPanel.show();
        CattabacPanel.hide();
        CatcigarettePanel.hide();
        CatrechargePanel.hide();
        CataccPanel.hide();
        CatpatesPanel.hide();
        CatconservesPanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "gouter" + "%' ");

        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        selectedCat = "gouter";

    }//GEN-LAST:event_gouterBtnActionPerformed

    private void boissonBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boissonBtnActionPerformed
        // TODO add your handling code here:

        //change photo of the bar
        CatPanel.hide();
        CateauPanel.hide();
        CatboissonPanel.show();
        CatgouterPanel.hide();
        CattabacPanel.hide();
        CatcigarettePanel.hide();
        CatrechargePanel.hide();
        CataccPanel.hide();
        CatpatesPanel.hide();
        CatconservesPanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "boisson" + "%' ");

        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        selectedCat = "boisson";
    }//GEN-LAST:event_boissonBtnActionPerformed

    private void eauBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eauBtnActionPerformed
        // TODO add your handling code here:
        //change photo of the bar
        CatPanel.hide();
        CateauPanel.show();
        CatboissonPanel.hide();
        CatgouterPanel.hide();
        CattabacPanel.hide();
        CatcigarettePanel.hide();
        CatrechargePanel.hide();
        CataccPanel.hide();
        CatpatesPanel.hide();
        CatconservesPanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "eau" + "%' ");

        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        selectedCat = "eau";
    }//GEN-LAST:event_eauBtnActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        id = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        nom = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        barcode = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2));
        categorie = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3));
        prix = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 5));
        cout = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4));
        max = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 6));
        min = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 7));
    }//GEN-LAST:event_jTable1MouseClicked

    private void supprimerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerbtnActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRow() >= 0) {
            if (JOptionPane.showConfirmDialog(this, "Etes vous sur de vouloir supprimer ce client ?", "Attention", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

                String user = utilisateur.getText();
                String action = "supprimer";
                String Barcode = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2));
                String name = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
                System.err.println(Barcode);
                String Description = Barcode;
                String d = Date.getText();
                String h = Heure.getText();
                String[] actions = {"user", "type_action", "description", "Date", "Heure"};
                String[] inf3 = {user, action, Description, d, h};
                System.out.println(db.queryInsert("action", actions, inf3));
                String id = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
                db.queryDelete("produit", "nom='" + name + "'");
                db.queryDelete("produit_stock", "nom='" + name + "'");
                table();
                JOptionPane.showMessageDialog(this, "Le produit a été suprimé avec succès");
            }
        }


    }//GEN-LAST:event_supprimerbtnActionPerformed

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
            String d = Date.getText();
            String h = Heure.getText();
            String[] actions = {"user", "type_action", "description", "Date", "Heure"};
            String[] inf3 = {user, action, Description, d, h};
            System.out.println(db.queryInsert("action", actions, inf3));

            //..................................
            this.hide();
            a.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(POS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_LogoutBtnActionPerformed

    private void statbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statbtnActionPerformed
        // TODO add your handling code here:
        //statistique.getObj().setVisible(true);
        db.closeconnexion();
        statistique a = new statistique(utilisateur.getText());
        a.setVisible(true);
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
        db.closeconnexion();
        Historique a = new Historique(utilisateur.getText());
        a.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_salesbtnActionPerformed

    private void inventorybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventorybtnActionPerformed
        // TODO add your handling code here:

        // this.dispose();
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

    private void PatesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatesBtnActionPerformed

        //change photo of the bar
        CatPanel.hide();
        CateauPanel.hide();
        CatboissonPanel.hide();
        CatgouterPanel.hide();
        CattabacPanel.hide();
        CatcigarettePanel.hide();
        CatrechargePanel.hide();
        CataccPanel.hide();
        CatpatesPanel.show();
        CatconservesPanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "pates" + "%' ");

        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        selectedCat = "pates";
    }//GEN-LAST:event_PatesBtnActionPerformed

    private void ConserveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConserveBtnActionPerformed

        //change photo of the bar
        CatPanel.hide();
        CateauPanel.hide();
        CatboissonPanel.hide();
        CatgouterPanel.hide();
        CattabacPanel.hide();
        CatcigarettePanel.hide();
        CatrechargePanel.hide();
        CataccPanel.hide();
        CatpatesPanel.hide();
        CatconservesPanel.show();
        CatfruitssecsPanel.hide();

        //.........
        String a[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "conserve" + "%' ");

        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        selectedCat = "conserve";

    }//GEN-LAST:event_ConserveBtnActionPerformed

    private void FruitssecsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FruitssecsBtnActionPerformed
        //change photo of the bar
        CatPanel.hide();
        CateauPanel.hide();
        CatboissonPanel.hide();
        CatgouterPanel.hide();
        CattabacPanel.hide();
        CatcigarettePanel.hide();
        CatrechargePanel.hide();
        CataccPanel.hide();
        CatpatesPanel.hide();
        CatconservesPanel.hide();
        CatfruitssecsPanel.show();

        //.........
        String a[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "fruits secs" + "%' ");

        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        selectedCat = "fruits secs";

    }//GEN-LAST:event_FruitssecsBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Credit a = new Credit(utilisateur.getText());
        a.setVisible(true);
        this.dispose();
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
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventory("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AccBtn;
    private javax.swing.JTextField Barsearch;
    private javax.swing.JLabel CatPanel;
    private javax.swing.JLabel CataccPanel;
    private javax.swing.JLabel CatboissonPanel;
    private javax.swing.JLabel CatcigarettePanel;
    private javax.swing.JLabel CatconservesPanel;
    private javax.swing.JLabel CateauPanel;
    private javax.swing.JLabel CatfruitssecsPanel;
    private javax.swing.JLabel CatgouterPanel;
    private javax.swing.JLabel CatpatesPanel;
    private javax.swing.JLabel CatrechargePanel;
    private javax.swing.JLabel CattabacPanel;
    private javax.swing.JButton ConserveBtn;
    private javax.swing.JLabel Date;
    private javax.swing.JLabel Date1;
    private javax.swing.JButton FruitssecsBtn;
    private javax.swing.JPanel General_Panel;
    private javax.swing.JLabel Heure;
    private javax.swing.JLabel Heure1;
    private javax.swing.JLabel Logo;
    private javax.swing.JButton LogoutBtn;
    private javax.swing.JLabel LogoutBtnbg;
    private javax.swing.JButton PatesBtn;
    private javax.swing.JPanel Session;
    private javax.swing.JButton ajouterProduitbtn;
    private javax.swing.JPanel barpanel;
    private javax.swing.JButton boissonBtn;
    private javax.swing.JPanel category_bar;
    private javax.swing.JButton cigaretteBtn;
    private javax.swing.JButton eauBtn;
    private javax.swing.JButton gouterBtn;
    private javax.swing.JButton inventorybtn;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton modifierPrduitbtn;
    private javax.swing.JButton rechargeBtn;
    private javax.swing.JButton registerbtn;
    private javax.swing.JButton salesbtn;
    private javax.swing.JLabel searchbarbg;
    private javax.swing.JButton statbtn;
    private javax.swing.JButton stockbtn;
    private javax.swing.JButton supprimerbtn;
    private javax.swing.JLabel supprimerbtnbg;
    private javax.swing.JButton tabacBtn;
    private javax.swing.JButton usersbtn;
    private javax.swing.JLabel utilisateur;
    // End of variables declaration//GEN-END:variables
}

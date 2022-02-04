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
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableCellRenderer;
import static java.lang.Math.abs;
import static java.lang.Thread.sleep;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author asuss
 */
public class stock extends javax.swing.JFrame {
    
    public static stock obj = null;
    ResultSet rs;
    DbConnection db;
    ConnectComPort dc ;
    private DefaultTableCellRenderer headerRenderer;
    String username; 
    
    AbstractTableModel tablemodel; 
    private String selectedCat;
    
    
    public stock(String username) {
        
        this.username = username; 
        
        
        initComponents();
        this.setDefaultCloseOperation(0);
       
       
        headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(30, 130, 82));
        headerRenderer.setForeground(new Color(255, 250, 240));
       
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        dc = new ConnectComPort();
        db.connexionDatabase();
        table();
        date();
        datecourante();
        
        utilisateur.setText(username);
        //System.err.println(isUser(username));
        
        if(isUser(username)){
            ReduceBtn.hide();
            ReduceBtnbg.hide();
            
            jLabel20.hide();
            jLabel21.hide();
            jLabel22.hide();
            
            salesbtn.hide();
            usersbtn.hide();
            statbtn.hide();
        }
        
        selectedCat=""; 
        
        this.setExtendedState(this.MAXIMIZED_BOTH);
        Barsearch.requestFocus();
    }
    public static stock getObj() {
        if (obj == null) {

            obj = new stock("");
        }
        return obj;
    }
    
    public boolean isUser(String username){
        String q = "Select type from utilisateur where Login ='"+username+"'"; 
        rs = db.exécutionQuery(q); 
     try { 
         rs.next();
         //System.err.println(rs.getString(1));
         if((rs.getString(1).toLowerCase()).equals("utilisateur"))
             return true; 
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
        //jScrollPane1.gets
        
        String colon[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente","Max","Min","Quantite"};
        rs = db.querySelectOrdre(colon, "produit_stock", "nom");
 
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        jTable1.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(Color.BLACK);
        jTable1.getTableHeader().setForeground(new Color(19,35,132));
        
        
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(30);

       jScrollPane1.getViewport().setBackground(new Color(255,250,240));
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        
        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
                jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
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

        Date = new javax.swing.JLabel();
        Heure = new javax.swing.JLabel();
        General_Panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        RefillBtnbg = new javax.swing.JLabel();
        RefillBtn = new javax.swing.JButton();
        ReduceBtnbg = new javax.swing.JLabel();
        ReduceBtn = new javax.swing.JButton();
        Barsearch = new javax.swing.JTextField();
        searchbarbg = new javax.swing.JLabel();
        Quantity = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        category_bar = new javax.swing.JPanel();
        CatPanel = new javax.swing.JLabel();
        CatfruitssecsPanel = new javax.swing.JLabel();
        CatconservePanel = new javax.swing.JLabel();
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
        jLabel3 = new javax.swing.JLabel();
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
        setMinimumSize(new java.awt.Dimension(1300, 760));
        setSize(new java.awt.Dimension(1300, 760));
        getContentPane().setLayout(null);

        General_Panel.setBackground(new java.awt.Color(255, 250, 240));
        General_Panel.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        General_Panel.setPreferredSize(new java.awt.Dimension(1230, 100));
        General_Panel.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/stockbg.png"))); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        General_Panel.add(jLabel2);
        jLabel2.setBounds(140, 270, 780, 680);

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
        jTable1.setGridColor(new java.awt.Color(255, 250, 240));
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setOpaque(false);
        jTable1.setRowHeight(30);
        jTable1.setSelectionBackground(new java.awt.Color(30, 130, 82));
        jTable1.setSelectionForeground(new java.awt.Color(255, 250, 240));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getAccessibleContext().setAccessibleName("");

        General_Panel.add(jScrollPane1);
        jScrollPane1.setBounds(140, 270, 780, 630);

        RefillBtnbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/remplirstockbtn.png"))); // NOI18N
        General_Panel.add(RefillBtnbg);
        RefillBtnbg.setBounds(1000, 290, 230, 70);

        RefillBtn.setBackground(new java.awt.Color(51, 51, 255));
        RefillBtn.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        RefillBtn.setForeground(new java.awt.Color(255, 255, 255));
        RefillBtn.setBorder(null);
        RefillBtn.setBorderPainted(false);
        RefillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefillBtnActionPerformed(evt);
            }
        });
        General_Panel.add(RefillBtn);
        RefillBtn.setBounds(1010, 300, 180, 50);

        ReduceBtnbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/reduirestockbtn.png"))); // NOI18N
        General_Panel.add(ReduceBtnbg);
        ReduceBtnbg.setBounds(1000, 370, 230, 70);

        ReduceBtn.setBackground(new java.awt.Color(255, 255, 255));
        ReduceBtn.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        ReduceBtn.setForeground(new java.awt.Color(22, 41, 153));
        ReduceBtn.setText("reduce stock");
        ReduceBtn.setBorder(null);
        ReduceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReduceBtnActionPerformed(evt);
            }
        });
        General_Panel.add(ReduceBtn);
        ReduceBtn.setBounds(1010, 380, 180, 50);

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

        searchbarbg.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        searchbarbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/searchbarbg.png"))); // NOI18N
        General_Panel.add(searchbarbg);
        searchbarbg.setBounds(140, 95, 560, 60);

        Quantity.setBackground(new java.awt.Color(255, 250, 240));
        Quantity.setFont(new java.awt.Font("Roboto Medium", 0, 36)); // NOI18N
        Quantity.setForeground(new java.awt.Color(5, 45, 26));
        Quantity.setBorder(null);
        Quantity.setOpaque(false);
        Quantity.setSelectedTextColor(new java.awt.Color(255, 250, 240));
        Quantity.setSelectionColor(new java.awt.Color(5, 45, 26));
        Quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                QuantityKeyTyped(evt);
            }
        });
        General_Panel.add(Quantity);
        Quantity.setBounds(1010, 540, 180, 60);

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(5, 45, 26));
        jLabel1.setText("Quantity :");
        General_Panel.add(jLabel1);
        jLabel1.setBounds(1000, 490, 110, 40);

        category_bar.setBackground(new java.awt.Color(255, 255, 255));
        category_bar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        category_bar.setOpaque(false);
        category_bar.setLayout(null);

        CatPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/CatPanel.png"))); // NOI18N
        category_bar.add(CatPanel);
        CatPanel.setBounds(0, 0, 820, 60);

        CatfruitssecsPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barfruitssecs.png"))); // NOI18N
        category_bar.add(CatfruitssecsPanel);
        CatfruitssecsPanel.setBounds(0, 0, 820, 60);

        CatconservePanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barconserve.png"))); // NOI18N
        category_bar.add(CatconservePanel);
        CatconservePanel.setBounds(0, 0, 820, 60);

        CatpatesPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barpates.png"))); // NOI18N
        category_bar.add(CatpatesPanel);
        CatpatesPanel.setBounds(0, 0, 820, 60);

        CataccPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/baraccessoires.png"))); // NOI18N
        category_bar.add(CataccPanel);
        CataccPanel.setBounds(0, 0, 820, 60);

        CatrechargePanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barrecharge.png"))); // NOI18N
        category_bar.add(CatrechargePanel);
        CatrechargePanel.setBounds(0, 0, 820, 60);

        CatcigarettePanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barcigarettes.png"))); // NOI18N
        category_bar.add(CatcigarettePanel);
        CatcigarettePanel.setBounds(0, 0, 820, 60);

        CattabacPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/bartabac.png"))); // NOI18N
        category_bar.add(CattabacPanel);
        CattabacPanel.setBounds(0, 0, 820, 60);

        CatgouterPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/bargouter.png"))); // NOI18N
        category_bar.add(CatgouterPanel);
        CatgouterPanel.setBounds(0, 0, 820, 60);

        CatboissonPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barboisson.png"))); // NOI18N
        category_bar.add(CatboissonPanel);
        CatboissonPanel.setBounds(0, 0, 820, 60);

        CateauPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/bareau.png"))); // NOI18N
        category_bar.add(CateauPanel);
        CateauPanel.setBounds(0, 0, 820, 60);

        FruitssecsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FruitssecsBtnActionPerformed(evt);
            }
        });
        category_bar.add(FruitssecsBtn);
        FruitssecsBtn.setBounds(700, 10, 80, 35);

        ConserveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConserveBtnActionPerformed(evt);
            }
        });
        category_bar.add(ConserveBtn);
        ConserveBtn.setBounds(620, 10, 70, 35);

        PatesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatesBtnActionPerformed(evt);
            }
        });
        category_bar.add(PatesBtn);
        PatesBtn.setBounds(560, 10, 50, 35);

        AccBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccBtnActionPerformed(evt);
            }
        });
        category_bar.add(AccBtn);
        AccBtn.setBounds(450, 10, 100, 35);

        rechargeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechargeBtnActionPerformed(evt);
            }
        });
        category_bar.add(rechargeBtn);
        rechargeBtn.setBounds(355, 10, 90, 35);

        cigaretteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cigaretteBtnActionPerformed(evt);
            }
        });
        category_bar.add(cigaretteBtn);
        cigaretteBtn.setBounds(265, 10, 80, 35);

        tabacBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tabacBtnActionPerformed(evt);
            }
        });
        category_bar.add(tabacBtn);
        tabacBtn.setBounds(210, 10, 40, 35);

        gouterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gouterBtnActionPerformed(evt);
            }
        });
        category_bar.add(gouterBtn);
        gouterBtn.setBounds(145, 10, 50, 35);

        boissonBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boissonBtnActionPerformed(evt);
            }
        });
        category_bar.add(boissonBtn);
        boissonBtn.setBounds(70, 10, 60, 35);

        eauBtn.setBackground(new java.awt.Color(255, 255, 255));
        eauBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        eauBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eauBtnActionPerformed(evt);
            }
        });
        category_bar.add(eauBtn);
        eauBtn.setBounds(10, 10, 50, 35);

        General_Panel.add(category_bar);
        category_bar.setBounds(140, 175, 850, 70);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/quantitebg.png"))); // NOI18N
        General_Panel.add(jLabel3);
        jLabel3.setBounds(990, 530, 210, 80);

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

        jLabel20.setBackground(new java.awt.Color(255, 250, 240));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/historique.png"))); // NOI18N
        jLabel20.setOpaque(true);
        barpanel.add(jLabel20);
        jLabel20.setBounds(10, 480, 60, 64);

        jLabel19.setBackground(new java.awt.Color(255, 250, 240));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/inventory.png"))); // NOI18N
        jLabel19.setOpaque(true);
        barpanel.add(jLabel19);
        jLabel19.setBounds(10, 380, 60, 50);

        jLabel18.setBackground(new java.awt.Color(30, 130, 82));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/stockLight.png"))); // NOI18N
        jLabel18.setOpaque(true);
        barpanel.add(jLabel18);
        jLabel18.setBounds(10, 280, 60, 50);

        jLabel16.setBackground(new java.awt.Color(255, 250, 240));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/cashregister.png"))); // NOI18N
        jLabel16.setOpaque(true);
        barpanel.add(jLabel16);
        jLabel16.setBounds(10, 180, 60, 50);

        jLabel5.setBackground(new java.awt.Color(30, 130, 82));
        jLabel5.setOpaque(true);
        barpanel.add(jLabel5);
        jLabel5.setBounds(0, 270, 80, 70);

        statbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        statbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statbtnActionPerformed(evt);
            }
        });
        barpanel.add(statbtn);
        statbtn.setBounds(10, 680, 60, 50);

        usersbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        usersbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersbtnActionPerformed(evt);
            }
        });
        barpanel.add(usersbtn);
        usersbtn.setBounds(10, 580, 60, 45);

        salesbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        salesbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesbtnActionPerformed(evt);
            }
        });
        barpanel.add(salesbtn);
        salesbtn.setBounds(10, 490, 60, 50);

        inventorybtn.setPreferredSize(new java.awt.Dimension(80, 80));
        inventorybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventorybtnActionPerformed(evt);
            }
        });
        barpanel.add(inventorybtn);
        inventorybtn.setBounds(10, 380, 60, 50);

        stockbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        stockbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockbtnActionPerformed(evt);
            }
        });
        barpanel.add(stockbtn);
        stockbtn.setBounds(10, 280, 60, 50);

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
        registerbtn.setBounds(10, 180, 60, 50);

        General_Panel.add(barpanel);
        barpanel.setBounds(2, 0, 80, 2000);

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
        jLabel10.setText("STOCK");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(950, 0, 290, 80);

        Heure1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Heure1.setForeground(new java.awt.Color(5, 45, 26));
        Heure1.setText("22:10");
        jPanel1.add(Heure1);
        Heure1.setBounds(510, 5, 100, 70);

        Date1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Date1.setForeground(new java.awt.Color(5, 45, 26));
        Date1.setText("31-12-2019");
        jPanel1.add(Date1);
        Date1.setBounds(640, 5, 100, 70);

        General_Panel.add(jPanel1);
        jPanel1.setBounds(50, 0, 2030, 80);

        getContentPane().add(General_Panel);
        General_Panel.setBounds(0, 0, 2000, 2000);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("empty-statement")
    private void RefillBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefillBtnActionPerformed
        // TODO add your handling code here:
       
        if (jTable1.getSelectedRow()>=0) {
            
            // EditProduct.getObj().setVisible(true);
            String quantite ;
            //read quantity to add from Textfield
            quantite = Quantity.getText();
            String QuantityInTable = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 8));
            int a ;
            a =abs(Integer.parseInt(QuantityInTable)+Integer.parseInt(quantite));
            System.out.println("a = " + a);
            //Quantity.setText(Float.toString(a));
            if(a<=Integer.parseInt(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 6)))){

           
           
           
           // action remplir
            String user = utilisateur.getText();
            String action = "remplir stock";
            String Barcode =String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)) ;
            System.out.println(Barcode);
            String Description = Barcode + ", Quantité ajoutée "+ Quantity.getText();
            String d = Date.getText();
            String h=Heure.getText();
            String[] actions = {"user", "type_action", "description", "Date", "Heure"};
            String[] inf3 = {user , action, Description,d, h};
            System.out.println(db.queryInsert("action", actions, inf3));
        //..................................
           
        // ajouter au tableau stock_historique
            
            String nom = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1)) ;
            String qty_ajoutee = Quantity.getText(); 
            String qty_precedente = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 8));
            String qty_totale = String.valueOf(Integer.parseInt(qty_ajoutee)+Integer.parseInt(qty_precedente)); 
            System.err.println(qty_ajoutee+" "+qty_precedente+" "+qty_totale);
            
            String[] s_h = {"User", "Barcode", "Nom", "Quantité_ajoutée","Quantité_precedente", "Quantité_totale", "Date_ajout","Heure_ajout"};
            String[] inf4 = {user , Barcode, nom,qty_ajoutee,qty_precedente, qty_totale, d ,h};
            System.out.println(db.queryInsert("stock_historique", s_h, inf4));
        //....................................
           
                String[] colon = { "Quantite"};
                String[] inf = {String.valueOf(a)};
                String id = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
                
                
                if(nom.contains("cigarette") && isUser(username)){
                    nom = nom.replace("cigarette", "pqt"); 
                    System.out.println(nom);
                    rs = db.exécutionQuery("Select Quantite from produit_stock where nom ='"+nom+"'"); 
                try { 
                    rs.next();
                    //System.out.println(rs.getString(1));
                    int qte = Integer.parseInt(rs.getString(1)); 
                    boolean b = qte >=1; 
                    if (qte >= 1) {
                        
                        qte = qte - 1; 
                        String[] c = {"Quantite"}; 
                        String[] i = {String.valueOf(qte)}; 
                        
                        db.queryUpdate("produit_stock", c, i, "nom ='"+nom+"'"); 
                        System.out.println(db.queryUpdate("produit_stock", colon, inf, "Id='" + id + "'"));
                    }else{
                        getToolkit().beep();
                        JOptionPane.showMessageDialog(this, "il n'y a pas assez de paquets", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                
                } catch (SQLException ex) {
                    Logger.getLogger(stock.class.getName()).log(Level.SEVERE, null, ex);
                }
                }else{
                    System.out.println(db.queryUpdate("produit_stock", colon, inf, "Id='" + id + "'"));
                }
                
               
                table();
       }else{
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "cela dépasse la quantité maximale", "Erreur", JOptionPane.ERROR_MESSAGE);
            Quantity.setText("");
       }
       Quantity.setText("");
       Quantity.requestFocus();
       //Barsearch.setText("");
        }
       
    }//GEN-LAST:event_RefillBtnActionPerformed

    private void ReduceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReduceBtnActionPerformed
        // TODO add your handling code here:
       
        if (jTable1.getSelectedRow()>=0) {
        //AddProduct.getObj().setVisible(true);
       String quantite ;
       quantite = Quantity.getText();
       String min = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 8));
       int a ;
       a =Integer.parseInt(min)-Integer.parseInt(quantite);
       Quantity.setText(Integer.toString(a));
       if(a>=0){
           if(a<Integer.parseInt(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 7)))){
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "vous avez moins que la quantité minimale", "Erreur", JOptionPane.ERROR_MESSAGE);
           }
           
            // action remplir
            String user = utilisateur.getText();
            String action = "réduire stock";
            String Barcode =String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)) ;
            System.err.println(Barcode);
            String Description = Barcode + ", Quantité soustraite "+ Quantity.getText();
            String d = Date.getText();
            String h=Heure.getText();
            String[] actions = {"user", "type_action", "description", "Date", "Heure"};
            String[] inf3 = {user , action, Description,d, h};
            System.out.println(db.queryInsert("action", actions, inf3));
        
            //..................................
           
        // ajouter au tableau stock_historique
            
            String nom = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1)) ;
            String qty_ajoutee =  Quantity.getText(); 
            String qty_precedente = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 8));
            String qty_totale = String.valueOf(Integer.parseInt(qty_precedente)-Integer.parseInt(qty_ajoutee)); 
            qty_ajoutee = "-"+qty_ajoutee; 
            System.err.println(qty_ajoutee+" "+qty_precedente+" "+qty_totale);
            
            String[] s_h = {"User", "Barcode", "Nom", "Quantité_ajoutée","Quantité_precedente", "Quantité_totale", "Date_ajout","Heure_ajout"};
            String[] inf4 = {user , Barcode, nom,qty_ajoutee,qty_precedente, qty_totale, d ,h};
            System.out.println(db.queryInsert("stock_historique", s_h, inf4));
        
        //....................................
           
           
            String[] colon = { "Quantite"};
            String[] inf = {Quantity.getText()};
            String id = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            System.out.println(db.queryUpdate("produit_stock", colon, inf, "Id='" + id + "'"));
            table();
            Quantity.setText("");
            
       }else{
           getToolkit().beep();
           JOptionPane.showMessageDialog(this, "le stock sera negatif", "Erreur", JOptionPane.ERROR_MESSAGE);
           Quantity.setText("");
       }
       Quantity.setText("");
       Barsearch.requestFocus();
       Barsearch.setText("");
        }
    }//GEN-LAST:event_ReduceBtnActionPerformed

    private void BarsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BarsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BarsearchActionPerformed

    private void BarsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BarsearchKeyReleased
        // TODO add your handling code here:
        String a[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente","Max","Min","Quantite"};
            
        String q = "Select id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min, Quantite "
                                                                                + "from produit_stock "
                                                                                + "where "
                                                                                + "nom LIKE '%" + Barsearch.getText() + "%'"
                                                                                +"OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                                                                                +"OR Categorie LIKE '%" + Barsearch.getText() + "%' "
                                                                                +"OR Barcode LIKE '%" + Barsearch.getText() + "%' "
                                                                                +"ORDER BY Quantite ";
        switch(selectedCat){
            case "eau":
                q = "Select id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min, Quantite "
                                                                                + "from produit_stock "
                                                                                + "where "
                                                                                + "(nom LIKE '%" + Barsearch.getText() + "%'"
                                                                                +"OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                                                                                +"OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                                                                                +"AND Categorie LIKE '%" + selectedCat + "%' "
                                                                                +"ORDER BY Quantite ";
                break;
            case "boisson":
               q = "Select id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min, Quantite "
                                                                                + "from produit_stock "
                                                                                + "where "
                                                                                + "(nom LIKE '%" + Barsearch.getText() + "%'"
                                                                                +"OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                                                                                +"OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                                                                                +"AND Categorie LIKE '%" + selectedCat + "%' "
                                                                                +"ORDER BY Quantite ";
        
                break;
            case "gouter":
                q = "Select id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min, Quantite "
                                                                                + "from produit_stock "
                                                                                + "where "
                                                                                + "(nom LIKE '%" + Barsearch.getText() + "%'"
                                                                                +"OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                                                                                +"OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                                                                                +"AND Categorie LIKE '%" + selectedCat + "%' "
                                                                                +"ORDER BY Quantite ";
        
                break;
            case "tabac":
               q = "Select id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min, Quantite "
                                                                                + "from produit_stock "
                                                                                + "where "
                                                                                + "(nom LIKE '%" + Barsearch.getText() + "%'"
                                                                                +"OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                                                                                +"OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                                                                                +"AND Categorie LIKE '%" + selectedCat + "%' "
                                                                                +"ORDER BY Quantite ";
        
                break;
            case "cigarette":
                q = "Select id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min, Quantite "
                                                                                + "from produit_stock "
                                                                                + "where "
                                                                                + "(nom LIKE '%" + Barsearch.getText() + "%'"
                                                                                +"OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                                                                                +"OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                                                                                +"AND nom LIKE '%" + selectedCat + "%' "
                                                                                +"ORDER BY Quantite ";
        
                break;
            case "recharge téléphonique":
                q = "Select id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min, Quantite "
                                                                                + "from produit_stock "
                                                                                + "where "
                                                                                + "(nom LIKE '%" + Barsearch.getText() + "%'"
                                                                                +"OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                                                                                +"OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                                                                                +"AND Categorie LIKE '%" + selectedCat + "%' "
                                                                                +"ORDER BY Quantite ";
        
                break;
            case "accessoire":
                q = "Select id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min, Quantite "
                                                                                + "from produit_stock "
                                                                                + "where "
                                                                                + "(nom LIKE '%" + Barsearch.getText() + "%'"
                                                                                +"OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                                                                                +"OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                                                                                +"AND Categorie LIKE '%" + selectedCat + "%' "
                                                                                +"ORDER BY Quantite ";
        
                break;
            case "pates":
                q = "Select id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min, Quantite "
                                                                                + "from produit_stock "
                                                                                + "where "
                                                                                + "(nom LIKE '%" + Barsearch.getText() + "%'"
                                                                                +"OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                                                                                +"OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                                                                                +"AND Categorie LIKE '%" + selectedCat + "%' "
                                                                                +"ORDER BY Quantite ";
        
                break;
            case "conserve":
                q = "Select id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min, Quantite "
                                                                                + "from produit_stock "
                                                                                + "where "
                                                                                + "(nom LIKE '%" + Barsearch.getText() + "%'"
                                                                                +"OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                                                                                +"OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                                                                                +"AND Categorie LIKE '%" + selectedCat + "%' "
                                                                                +"ORDER BY Quantite ";
        
                break;
            case "fruits secs":
                q = "Select id, nom, Barcode, Categorie, Prix_Base, Prix_Vente, Max, Min, Quantite "
                                                                                + "from produit_stock "
                                                                                + "where "
                                                                                + "(nom LIKE '%" + Barsearch.getText() + "%'"
                                                                                +"OR Prix_vente LIKE '%" + Barsearch.getText() + "%' "
                                                                                +"OR Barcode LIKE '%" + Barsearch.getText() + "%' )"
                                                                                +"AND Categorie LIKE '%" + selectedCat + "%' "
                                                                                +"ORDER BY Quantite ";
        
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

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        
        String name = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1)); 
        System.out.println(name); 
        boolean cig = name.contains("cigarette"); 
        
        if (isUser(username) && cig ) {
                Quantity.setText("20");
                Quantity.setEditable(false); 
        }else{
            Quantity.setText("");
                Quantity.setEditable(true); 
        }
       
  //     Quantity.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 8)));
        
    }//GEN-LAST:event_jTable1MousePressed

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
        CatconservePanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] ={"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente","Max","Min","Quantite"};
        rs = db.fcSelectCommand(a,"produit_stock", "Categorie LIKE '%" + "accessoire" + "%' ");

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
        CatconservePanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] ={"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente","Max","Min","Quantite"};
        rs = db.fcSelectCommand(a,"produit_stock", "Categorie LIKE '%" + "recharge téléphonique" + "%' ");

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
        CatconservePanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] ={"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente","Max","Min","Quantite"};
        rs = db.fcSelectCommand(a,"produit_stock", "nom LIKE '%" + "cigarette" + "%' ");

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
        CatconservePanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] ={"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente","Max","Min","Quantite"};
        rs = db.fcSelectCommand(a,"produit_stock", "Categorie LIKE '%" + "tabac" + "%' ");

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
        CatconservePanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] ={"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente","Max","Min","Quantite"};
        rs = db.fcSelectCommand(a,"produit_stock", "Categorie LIKE '%" + "gouter" + "%' ");

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
        CatconservePanel.hide();
        CatfruitssecsPanel.hide();

        //.........
        String a[] ={"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente","Max","Min","Quantite"};
        rs = db.fcSelectCommand(a,"produit_stock", "Categorie LIKE '%" + "boisson" + "%' ");

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
        CatconservePanel.hide();
        CatfruitssecsPanel.hide();
        //.........
        String a[] ={"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente","Max","Min","Quantite"};
        rs = db.fcSelectCommand(a,"produit_stock", "Categorie LIKE '%" + "eau" + "%' ");

        jTable1.setModel(new ResultSetTableModel(rs));
         jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);
        
        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
                jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        selectedCat = "eau"; 
        
    }//GEN-LAST:event_eauBtnActionPerformed

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
db.closeconnexion();
        Historique a = new Historique(utilisateur.getText());
        a.setVisible(true);
         this.dispose();
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

        
        //this.dispose();
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
db.closeconnexion();
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

    private void QuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QuantityKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if (!((Character.isDigit(car)) || (car == KeyEvent.VK_BACK_SPACE) || (car == KeyEvent.VK_DELETE) || (Quantity.getText().length() > 4))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_QuantityKeyTyped

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
        CatconservePanel.hide();
        CatfruitssecsPanel.hide();
        
        //.........
        String a[] ={"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente","Max","Min","Quantite"};
        rs = db.fcSelectCommand(a,"produit_stock", "Categorie LIKE '%" + "pates" + "%' ");

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
        CatconservePanel.show();
        CatfruitssecsPanel.hide();

        //.........
        String a[] ={"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente","Max","Min","Quantite"};
        rs = db.fcSelectCommand(a,"produit_stock", "Categorie LIKE '%" + "conserve" + "%' ");

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
        CatconservePanel.hide();
        CatfruitssecsPanel.show();
        //.........
        String a[] ={"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente","Max","Min","Quantite"};
        rs = db.fcSelectCommand(a,"produit_stock", "Categorie LIKE '%" + "fruits secs" + "%' ");

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
            java.util.logging.Logger.getLogger(stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new stock("").setVisible(true);
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
    private javax.swing.JLabel CatconservePanel;
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
    private javax.swing.JTextField Quantity;
    private javax.swing.JButton ReduceBtn;
    private javax.swing.JLabel ReduceBtnbg;
    private javax.swing.JButton RefillBtn;
    private javax.swing.JLabel RefillBtnbg;
    private javax.swing.JPanel Session;
    private javax.swing.JPanel barpanel;
    private javax.swing.JButton boissonBtn;
    private javax.swing.JPanel category_bar;
    private javax.swing.JButton cigaretteBtn;
    private javax.swing.JButton eauBtn;
    private javax.swing.JButton gouterBtn;
    private javax.swing.JButton inventorybtn;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton rechargeBtn;
    private javax.swing.JButton registerbtn;
    private javax.swing.JButton salesbtn;
    private javax.swing.JLabel searchbarbg;
    private javax.swing.JButton statbtn;
    private javax.swing.JButton stockbtn;
    private javax.swing.JButton tabacBtn;
    private javax.swing.JButton usersbtn;
    private javax.swing.JLabel utilisateur;
    // End of variables declaration//GEN-END:variables
}

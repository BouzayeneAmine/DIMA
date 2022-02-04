package pkgnew.app;

import BDD.DbConnection;
import BDD.Parametre;
import BDD.ResultSetTableModel;
import java.awt.Color;
import java.awt.Font;
import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;

public class Utilisateurs extends javax.swing.JFrame {

    ResultSet rs;
    DbConnection db;
    public static Utilisateurs obj = null;
    //private String Date, Heure; 
    private DefaultTableCellRenderer headerRenderer;
    private JLabel Heure = new JLabel();
    private JLabel Date = new JLabel();
    

    public Utilisateurs(String username) {
        
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        db.connexionDatabase();
        Heure1 = new JLabel(); 
        Date1 = new JLabel(); 
        
        date();
        datecourante();
        
        initComponents();
        this.setDefaultCloseOperation(0);
        
        headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(30, 130, 82));
        headerRenderer.setForeground(new Color(255, 250, 240));
        
        
//  removeTitleBar();
        utilisateur.setText(username);
        table();
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }

    public static Utilisateurs getObj() {
        if (obj == null) {
            obj = new Utilisateurs("");
        }
        return obj;
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
        String a[] = {"Id", "Login", "Password", "type"};
        rs = db.querySelect(a, "utilisateur");
        
        jTable3.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        jTable3.getTableHeader().setOpaque(false);
        jTable3.getTableHeader().setBackground(Color.BLACK);
        jTable3.getTableHeader().setForeground(new Color(19,35,132));
        
        jTable3.setModel(new ResultSetTableModel(rs));
        jTable3.getColumnModel().getColumn(0).setMinWidth(0);
        jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
        
        jScrollPane3.getViewport().setBackground(new Color(255,250,240));
        jScrollPane3.setBorder(BorderFactory.createEmptyBorder());
        
        for (int i = 0; i < jTable3.getModel().getColumnCount(); i++) {
                jTable3.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
    }

    void actualiser() {
        txLogin.setText("");
        txPassword.setText("");
        txTest.setText("");
        txTest1.setText("");
        table();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txTest = new javax.swing.JTextField();
        txTest1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
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
        jLabel7 = new javax.swing.JLabel();
        txPassword = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txLogin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        Session = new javax.swing.JPanel();
        utilisateur = new javax.swing.JLabel();
        LogoutBtnbg = new javax.swing.JLabel();
        LogoutBtn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        Date1 = new javax.swing.JLabel();
        Heure1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

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

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1300, 760));
        setMinimumSize(new java.awt.Dimension(1300, 760));
        setPreferredSize(new java.awt.Dimension(1300, 760));
        setSize(new java.awt.Dimension(1300, 760));
        getContentPane().setLayout(null);

        jPanel4.setBackground(new java.awt.Color(255, 250, 240));
        jPanel4.setLayout(null);

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

        jLabel21.setBackground(new java.awt.Color(30, 130, 82));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/utilisateursLight.png"))); // NOI18N
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

        jLabel5.setBackground(new java.awt.Color(30, 130, 82));
        jLabel5.setOpaque(true);
        barpanel.add(jLabel5);
        jLabel5.setBounds(0, 570, 80, 70);

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

        jPanel4.add(barpanel);
        barpanel.setBounds(2, 0, 80, 2000);

        jPanel1.setBackground(new java.awt.Color(255, 250, 240));
        jPanel1.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(760, 120, 310, 50);

        txPassword.setBackground(new java.awt.Color(255, 250, 240));
        txPassword.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        txPassword.setForeground(new java.awt.Color(5, 45, 26));
        txPassword.setBorder(null);
        txPassword.setOpaque(false);
        txPassword.setSelectedTextColor(new java.awt.Color(255, 250, 240));
        txPassword.setSelectionColor(new java.awt.Color(5, 45, 26));
        txPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txPasswordKeyTyped(evt);
            }
        });
        jPanel1.add(txPassword);
        txPassword.setBounds(780, 130, 270, 30);

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(30, 130, 82));
        jLabel2.setText("Mot de passe :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(580, 130, 190, 29);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(210, 120, 330, 50);

        txLogin.setBackground(new java.awt.Color(255, 250, 240));
        txLogin.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        txLogin.setForeground(new java.awt.Color(5, 45, 26));
        txLogin.setBorder(null);
        txLogin.setOpaque(false);
        txLogin.setSelectedTextColor(new java.awt.Color(255, 250, 240));
        txLogin.setSelectionColor(new java.awt.Color(5, 45, 26));
        txLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txLoginKeyTyped(evt);
            }
        });
        jPanel1.add(txLogin);
        txLogin.setBounds(230, 130, 270, 30);

        jLabel6.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(30, 130, 82));
        jLabel6.setText("Login :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(120, 110, 80, 60);

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(30, 130, 82));
        jLabel3.setText("Rôle :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(120, 200, 80, 29);

        jComboBox1.setBackground(new java.awt.Color(5, 45, 26));
        jComboBox1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(5, 45, 26));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "directeur", "Utilisateur" }));
        jComboBox1.setBorder(null);
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(210, 190, 300, 50);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/annulerbtn.png"))); // NOI18N
        jPanel1.add(jLabel11);
        jLabel11.setBounds(850, 290, 230, 60);

        jButton2.setText("annuler");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(860, 300, 180, 40);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/confirmerbtn.png"))); // NOI18N
        jPanel1.add(jLabel9);
        jLabel9.setBounds(130, 280, 230, 80);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/modifierbtn.png"))); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(370, 280, 230, 80);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/supprimerbtn.png"))); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(610, 280, 230, 80);

        jButton3.setText("supprimer");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(620, 300, 180, 40);

        jButton4.setText("modifier");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(380, 300, 180, 40);

        jButton1.setText("confirmer");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(140, 300, 180, 40);

        jPanel4.add(jPanel1);
        jPanel1.setBounds(120, 110, 1160, 370);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/userbg.png"))); // NOI18N
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel4);
        jLabel4.setBounds(310, 550, 800, 370);

        jTable3.setBackground(new java.awt.Color(255, 250, 240));
        jTable3.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jTable3.setForeground(new java.awt.Color(5, 45, 26));
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
        jTable3.setOpaque(false);
        jTable3.setRowHeight(30);
        jTable3.setSelectionBackground(new java.awt.Color(30, 130, 82));
        jTable3.setSelectionForeground(new java.awt.Color(255, 250, 240));
        jTable3.setShowVerticalLines(false);
        jTable3.getTableHeader().setResizingAllowed(false);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable3MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel4.add(jScrollPane3);
        jScrollPane3.setBounds(310, 550, 800, 340);

        jPanel3.setBackground(new java.awt.Color(255, 250, 240));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 130, 82), 2));
        jPanel3.setForeground(new java.awt.Color(30, 130, 82));
        jPanel3.setLayout(null);

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

        jPanel3.add(Session);
        Session.setBounds(90, 10, 270, 60);

        Date1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Date1.setForeground(new java.awt.Color(5, 45, 26));
        jPanel3.add(Date1);
        Date1.setBounds(640, 5, 100, 70);

        Heure1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Heure1.setForeground(new java.awt.Color(5, 45, 26));
        jPanel3.add(Heure1);
        Heure1.setBounds(510, 5, 100, 70);

        jLabel13.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(30, 130, 82));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("UTILISATEURS");
        jPanel3.add(jLabel13);
        jLabel13.setBounds(840, 0, 400, 80);

        jPanel4.add(jPanel3);
        jPanel3.setBounds(50, 0, 2030, 80);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 0, 2060, 2000);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        rs = db.querySelectAll("utilisateur", "Login='" + txLogin.getText() + "'");
        jTable2.setModel(new ResultSetTableModel(rs));
        txTest.setText(String.valueOf(jTable2.getValueAt(0, 2)));
        if (txLogin.getText().equals("") || txPassword.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez entrer les informations requises", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (!txTest.getText().equals("null")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "ce Login existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] colon = {"Login", "Password", "type"};
            String[] inf = {txLogin.getText(), txPassword.getText(), jComboBox1.getSelectedItem().toString()};
            System.out.println(db.queryInsert("utilisateur", colon, inf));
            actualiser();
            JOptionPane.showMessageDialog(this, "Votre Utilisateur a été ajouté avec succès");

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String id = String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 0));
        if (txTest1.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez selectionner un Utilisateur dans le tableau", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (!txTest1.getText().equals(id)) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez selectionner un Utilisateur dans le tableau", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (JOptionPane.showConfirmDialog(this, "Etes vous sur de vouloir supprimer ce client ?", "Attention", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            db.queryDelete("utilisateur", "id=" + id);
            actualiser();
            JOptionPane.showMessageDialog(this, "L Utilisateur a été suprimé avec succès");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txLoginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txLoginKeyTyped
        char car = evt.getKeyChar();
        if ((car == '\'') || (txLogin.getText().length() > 26)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txLoginKeyTyped

    private void txPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPasswordKeyTyped
        char car = evt.getKeyChar();
        if ((car == '\'') || (txPassword.getText().length() > 9)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txPasswordKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String zd = String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 0));
        if (!txTest1.getText().equals(zd)) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez selectionner un Utilisateur dans le tableau", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (txLogin.getText().equals("") || txPassword.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un utilisateur a modifié", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] colon = {"Login", "Password", "Titre"};
            String[] inf = {txLogin.getText(), txPassword.getText(), jComboBox1.getSelectedItem().toString()};
            String id = String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 0));
            System.out.println(db.queryUpdate("utilisateur", colon, inf, "id='" + id + "'"));
            table();
            actualiser();
            JOptionPane.showMessageDialog(this, "Votre utilisateur a été modifié avec succès");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MousePressed
        // TODO add your handling code here:

        //     Quantity.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 8)));

    }//GEN-LAST:event_jTable3MousePressed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        txTest1.setText(String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 0)));
        txLogin.setText(String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 1)));
        txPassword.setText(String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 2)));
        jComboBox1.setSelectedItem(String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 3)));
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        rs = db.querySelectAll("utilisateur", "Login='" + txLogin.getText() + "'");
        jTable2.setModel(new ResultSetTableModel(rs));
        txTest.setText(String.valueOf(jTable2.getValueAt(0, 2)));
        if (txLogin.getText().equals("") || txPassword.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez entrer les informations requises", "Erreur", JOptionPane.ERROR_MESSAGE);
        }  else {
            String id = String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 0));

            String[] colon = {"Login", "Password", "type"};
            String[] inf = {txLogin.getText(), txPassword.getText(), jComboBox1.getSelectedItem().toString()};
           System.out.println(db.queryUpdate("Utilisateur", colon, inf, "Id='" + id+ "'"));
            actualiser();
            JOptionPane.showMessageDialog(this, "Votre Utilisateur a été ajouté avec succès");

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void statbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statbtnActionPerformed
        // TODO add your handling code here:
        //statistique.getObj().setVisible(true);
        db.closeconnexion();
        statistique a = new statistique(utilisateur.getText());
        a.setVisible(true);
        this.hide();
    }//GEN-LAST:event_statbtnActionPerformed

    private void usersbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersbtnActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_usersbtnActionPerformed

    private void salesbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesbtnActionPerformed
        // TODO add your handling code here:
         db.closeconnexion();
        Historique a = new Historique(utilisateur.getText());
        a.setVisible(true);
        this.hide();
        // this.dispose();
    }//GEN-LAST:event_salesbtnActionPerformed

    private void inventorybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventorybtnActionPerformed
        // TODO add your handling code here:
         db.closeconnexion();
        Inventory a = new Inventory(utilisateur.getText());
        a.setVisible(true);
        this.hide();
        // this.dispose();
    }//GEN-LAST:event_inventorybtnActionPerformed

    private void stockbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockbtnActionPerformed
        // TODO add your handling code here:
 db.closeconnexion();
        stock a = new stock(utilisateur.getText());
        a.setVisible(true);
        this.hide();
        //this.dispose();
    }//GEN-LAST:event_stockbtnActionPerformed

    private void registerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerbtnActionPerformed
        // TODO add your handling code here:
         db.closeconnexion();
        POS a = new POS(utilisateur.getText());
        a.setVisible(true);
        this.hide();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date1;
    private javax.swing.JLabel Heure1;
    private javax.swing.JLabel Logo;
    private javax.swing.JButton LogoutBtn;
    private javax.swing.JLabel LogoutBtnbg;
    private javax.swing.JPanel Session;
    private javax.swing.JPanel barpanel;
    private javax.swing.JButton inventorybtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton registerbtn;
    private javax.swing.JButton salesbtn;
    private javax.swing.JButton statbtn;
    private javax.swing.JButton stockbtn;
    private javax.swing.JTextField txLogin;
    private javax.swing.JTextField txPassword;
    private javax.swing.JTextField txTest;
    private javax.swing.JTextField txTest1;
    private javax.swing.JButton usersbtn;
    private javax.swing.JLabel utilisateur;
    // End of variables declaration//GEN-END:variables
}

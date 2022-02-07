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
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import javax.swing.table.DefaultTableCellRenderer;

import static java.lang.Thread.sleep;
import javax.swing.JOptionPane;

public class Credit extends javax.swing.JFrame {

    ResultSet rs;
    DbConnection db;
    public static Credit obj = null;
    //private String Date, Heure; 
    private DefaultTableCellRenderer headerRenderer;
    private JLabel Heure = new JLabel();
    private JLabel Date = new JLabel();
    String barcodeProduit;

    public Credit(String username) {

        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        db.connexionDatabase();
        initComponents();

        Heure1 = new JLabel();
        Date1 = new JLabel();

        date();
        datecourante();

        this.setDefaultCloseOperation(0);

        headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(30, 130, 82));
        headerRenderer.setForeground(new Color(255, 250, 240));

//  removeTitleBar();
        utilisateur.setText(username);
        table();
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }

    public static Credit getObj() {
        if (obj == null) {
            obj = new Credit("");
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

        String q = "select Client,Numero,SUM(total),acompte from credit group by Client ";
        rs = db.exécutionQuery(q);
        jTable3.setModel(new ResultSetTableModel(rs));

        jTable3.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        jTable3.getTableHeader().setOpaque(false);
        jTable3.getTableHeader().setBackground(Color.BLACK);
        jTable3.getTableHeader().setForeground(new Color(19, 35, 132));

        jTable3.setModel(new ResultSetTableModel(rs));

        jScrollPane3.getViewport().setBackground(new Color(255, 250, 240));
        jScrollPane3.setBorder(BorderFactory.createEmptyBorder());

        for (int i = 0; i < jTable3.getModel().getColumnCount(); i++) {
            jTable3.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

    }

    void actualiser() {
        client.setText("");
        credit.setText("");
        acompte.setText("");
        txTest1.setText("");
        numero.setText("");
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
        jLabel21 = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        statbtn = new javax.swing.JButton();
        usersbtn = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        salesbtn = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        inventorybtn = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        stockbtn = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        registerbtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        credit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        client = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        annulerBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        supprimerBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        modifierBtn = new javax.swing.JButton();
        confirmerBtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        acompte = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
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
        setMinimumSize(new java.awt.Dimension(1300, 760));
        setSize(new java.awt.Dimension(1300, 760));
        getContentPane().setLayout(null);

        jPanel4.setBackground(new java.awt.Color(255, 250, 240));
        jPanel4.setLayout(null);

        barpanel.setBackground(new java.awt.Color(255, 250, 240));
        barpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 130, 82), 2));
        barpanel.setForeground(new java.awt.Color(255, 253, 209));
        barpanel.setPreferredSize(new java.awt.Dimension(100, 1080));
        barpanel.setLayout(null);

        jLabel21.setBackground(new java.awt.Color(255, 250, 240));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/utilisateurs.png"))); // NOI18N
        jLabel21.setOpaque(true);
        barpanel.add(jLabel21);
        jLabel21.setBounds(10, 580, 60, 50);

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

        statbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/Stock6.png"))); // NOI18N
        statbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        statbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statbtnActionPerformed(evt);
            }
        });
        barpanel.add(statbtn);
        statbtn.setBounds(20, 680, 50, 50);

        usersbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/Stock5.png"))); // NOI18N
        usersbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        usersbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersbtnActionPerformed(evt);
            }
        });
        barpanel.add(usersbtn);
        usersbtn.setBounds(20, 580, 50, 45);

        jLabel20.setBackground(new java.awt.Color(255, 250, 240));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/historique.png"))); // NOI18N
        jLabel20.setOpaque(true);
        barpanel.add(jLabel20);
        jLabel20.setBounds(10, 480, 60, 64);

        salesbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/Stock4.png"))); // NOI18N
        salesbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        salesbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesbtnActionPerformed(evt);
            }
        });
        barpanel.add(salesbtn);
        salesbtn.setBounds(20, 490, 50, 50);

        jLabel19.setBackground(new java.awt.Color(255, 250, 240));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/inventory.png"))); // NOI18N
        jLabel19.setOpaque(true);
        barpanel.add(jLabel19);
        jLabel19.setBounds(10, 380, 60, 50);

        inventorybtn.setPreferredSize(new java.awt.Dimension(80, 80));
        inventorybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventorybtnActionPerformed(evt);
            }
        });
        barpanel.add(inventorybtn);
        inventorybtn.setBounds(20, 380, 50, 50);

        jLabel18.setBackground(new java.awt.Color(255, 250, 240));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/Stock.png"))); // NOI18N
        jLabel18.setOpaque(true);
        barpanel.add(jLabel18);
        jLabel18.setBounds(10, 280, 60, 50);

        stockbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/Stock3.png"))); // NOI18N
        stockbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        stockbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockbtnActionPerformed(evt);
            }
        });
        barpanel.add(stockbtn);
        stockbtn.setBounds(20, 280, 50, 50);

        jLabel16.setBackground(new java.awt.Color(255, 250, 240));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/cashregister.png"))); // NOI18N
        jLabel16.setOpaque(true);
        barpanel.add(jLabel16);
        jLabel16.setBounds(10, 180, 60, 50);

        jLabel30.setBackground(new java.awt.Color(255, 250, 240));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/credit.png"))); // NOI18N
        jLabel30.setOpaque(true);
        barpanel.add(jLabel30);
        jLabel30.setBounds(14, 760, 50, 50);

        jLabel5.setBackground(new java.awt.Color(30, 130, 82));
        jLabel5.setOpaque(true);
        barpanel.add(jLabel5);
        jLabel5.setBounds(0, 750, 80, 70);

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

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        barpanel.add(jButton2);
        jButton2.setBounds(10, 770, 60, 30);

        jPanel4.add(barpanel);
        barpanel.setBounds(2, 0, 80, 2000);

        jPanel1.setBackground(new java.awt.Color(255, 250, 240));
        jPanel1.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(760, 120, 310, 50);

        credit.setBackground(new java.awt.Color(255, 250, 240));
        credit.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        credit.setForeground(new java.awt.Color(5, 45, 26));
        credit.setBorder(null);
        credit.setOpaque(false);
        credit.setSelectedTextColor(new java.awt.Color(255, 250, 240));
        credit.setSelectionColor(new java.awt.Color(5, 45, 26));
        credit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                creditKeyTyped(evt);
            }
        });
        jPanel1.add(credit);
        credit.setBounds(780, 130, 270, 30);

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(30, 130, 82));
        jLabel2.setText("Acompte:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(580, 200, 160, 29);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(210, 120, 330, 50);

        client.setBackground(new java.awt.Color(255, 250, 240));
        client.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        client.setForeground(new java.awt.Color(5, 45, 26));
        client.setBorder(null);
        client.setOpaque(false);
        client.setSelectedTextColor(new java.awt.Color(255, 250, 240));
        client.setSelectionColor(new java.awt.Color(5, 45, 26));
        client.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                clientKeyTyped(evt);
            }
        });
        jPanel1.add(client);
        client.setBounds(230, 130, 270, 30);

        jLabel6.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(30, 130, 82));
        jLabel6.setText("Client ");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(120, 110, 80, 60);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/annulerbtn.png"))); // NOI18N
        jPanel1.add(jLabel11);
        jLabel11.setBounds(850, 290, 230, 60);

        annulerBtn.setText("annuler");
        annulerBtn.setBorder(null);
        annulerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerBtnActionPerformed(evt);
            }
        });
        jPanel1.add(annulerBtn);
        annulerBtn.setBounds(860, 300, 180, 40);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/confirmerbtn.png"))); // NOI18N
        jPanel1.add(jLabel9);
        jLabel9.setBounds(130, 280, 230, 80);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/supprimerbtn.png"))); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(610, 280, 230, 80);

        supprimerBtn.setText("supprimer");
        supprimerBtn.setBorder(null);
        supprimerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerBtnActionPerformed(evt);
            }
        });
        jPanel1.add(supprimerBtn);
        supprimerBtn.setBounds(620, 300, 180, 40);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/acomptebtn.png"))); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(370, 280, 210, 80);

        modifierBtn.setBorder(null);
        modifierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierBtnActionPerformed(evt);
            }
        });
        jPanel1.add(modifierBtn);
        modifierBtn.setBounds(380, 297, 180, 40);

        confirmerBtn.setText("confirmer");
        confirmerBtn.setBorder(null);
        confirmerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmerBtnActionPerformed(evt);
            }
        });
        jPanel1.add(confirmerBtn);
        confirmerBtn.setBounds(140, 300, 180, 40);

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N
        jPanel1.add(jLabel12);
        jLabel12.setBounds(760, 190, 310, 50);

        acompte.setBackground(new java.awt.Color(255, 250, 240));
        acompte.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        acompte.setForeground(new java.awt.Color(5, 45, 26));
        acompte.setBorder(null);
        acompte.setOpaque(false);
        acompte.setSelectedTextColor(new java.awt.Color(255, 250, 240));
        acompte.setSelectionColor(new java.awt.Color(5, 45, 26));
        acompte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                acompteKeyTyped(evt);
            }
        });
        jPanel1.add(acompte);
        acompte.setBounds(780, 200, 270, 30);

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(30, 130, 82));
        jLabel3.setText("Crédit :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(580, 130, 190, 29);

        jLabel14.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(30, 130, 82));
        jLabel14.setText("Numéro");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(120, 160, 100, 90);

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N
        jPanel1.add(jLabel17);
        jLabel17.setBounds(210, 170, 330, 80);

        numero.setBackground(new java.awt.Color(255, 250, 240));
        numero.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        numero.setForeground(new java.awt.Color(5, 45, 26));
        numero.setBorder(null);
        numero.setOpaque(false);
        numero.setSelectedTextColor(new java.awt.Color(255, 250, 240));
        numero.setSelectionColor(new java.awt.Color(5, 45, 26));
        numero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroKeyTyped(evt);
            }
        });
        jPanel1.add(numero);
        numero.setBounds(230, 200, 270, 20);

        jPanel4.add(jPanel1);
        jPanel1.setBounds(120, 110, 1160, 370);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/creditbg.png"))); // NOI18N
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel4);
        jLabel4.setBounds(107, 550, 1120, 470);

        jTable3.setBackground(new java.awt.Color(255, 250, 240));
        jTable3.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
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
        jScrollPane3.setBounds(110, 550, 1110, 340);

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

    private void jTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MousePressed
        // TODO add your handling code here:

        //     Quantity.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 8)));

    }//GEN-LAST:event_jTable3MousePressed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        client.setText(String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 0)));
        numero.setText(String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 1)));
        credit.setText(String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 2)));
        acompte.setText(String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 3)));

        String q = "select Client ,Nom,Barcode ,Categorie,sum(Quantite),Prix_Vente,total_base,sum(total),Date_Sortie,Heure_Sortie,user from credit where Client LIKE '%" + client.getText() + "%'" + "group by Barcode";
        rs = db.exécutionQuery(q);
        jTable3.setModel(new ResultSetTableModel(rs));
        jTable3.getColumnModel().getColumn(0).setMinWidth(0);
        jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable3.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        jTable3.getTableHeader().setOpaque(false);
        jTable3.getTableHeader().setBackground(Color.BLACK);
        jTable3.getTableHeader().setForeground(new Color(19, 35, 132));

        jScrollPane3.getViewport().setBackground(new Color(255, 250, 240));
        jScrollPane3.setBorder(BorderFactory.createEmptyBorder());

        for (int i = 0; i < jTable3.getModel().getColumnCount(); i++) {
            jTable3.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void statbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statbtnActionPerformed
        // TODO add your handling code here:
        //statistique.getObj().setVisible(true);
        statistique a = new statistique(utilisateur.getText());
        a.setVisible(true);
        this.hide();
    }//GEN-LAST:event_statbtnActionPerformed

    private void usersbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersbtnActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_usersbtnActionPerformed

    private void salesbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesbtnActionPerformed
        // TODO add your handling code here:
        Historique a = new Historique(utilisateur.getText());
        a.setVisible(true);
        this.hide();
        // this.dispose();
    }//GEN-LAST:event_salesbtnActionPerformed

    private void inventorybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventorybtnActionPerformed
        // TODO add your handling code here:
        Inventory a = new Inventory(utilisateur.getText());
        a.setVisible(true);
        this.hide();
        // this.dispose();
    }//GEN-LAST:event_inventorybtnActionPerformed

    private void stockbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockbtnActionPerformed
        // TODO add your handling code here:

        stock a = new stock(utilisateur.getText());
        a.setVisible(true);
        this.hide();
        //this.dispose();
    }//GEN-LAST:event_stockbtnActionPerformed

    private void registerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerbtnActionPerformed
        // TODO add your handling code here:
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

    private void confirmerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmerBtnActionPerformed
        String client_nom, nom, barcode, Quantity, total, total_base, categorie;
        client_nom = client.getText();
        int j = jTable3.getRowCount();
        try {

            for (int i = 0; i < j; i++) {

                nom = (String.valueOf(jTable3.getValueAt(i, 1)));
                barcode = String.valueOf(jTable3.getValueAt(i, 2));
                categorie = (String.valueOf(jTable3.getValueAt(i, 3)));
                Quantity = (String.valueOf(jTable3.getValueAt(i, 4)));
                String Prix = (String.valueOf(jTable3.getValueAt(i, 5)));
                total = (String.valueOf(jTable3.getValueAt(i, 7)));
                total_base = (String.valueOf(jTable3.getValueAt(i, 6)));

                String[] historique = {"Nom", "Barcode", "Categorie", "Quantite", "Prix_Vente", "total_base", "total", "Date_Sortie", "Heure_Sortie", "user"};
                String[] inf2 = {nom, barcode, categorie, Quantity, Prix, total_base, total, Date.getText(), Heure.getText(), utilisateur.getText()};
                db.queryInsert("historique", historique, inf2);
                db.queryDelete("credit", "Client='" + client_nom + "'");

            }
            actualiser();
        } catch (Exception e) {
            System.out.println("e=" + e.toString());
        }
    }//GEN-LAST:event_confirmerBtnActionPerformed

    private void modifierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierBtnActionPerformed

        if (client.getText().equals("") || acompte.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez choisir un client", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {

            String[] colon = {"Client", "acompte"};
            String[] inf = {client.getText(), acompte.getText()};
            System.out.println(db.queryUpdate("credit", colon, inf, "Client='" + client.getText() + "'"));
            actualiser();
            JOptionPane.showMessageDialog(this, "Votre acompte a été ajouté avec succès");

        }
    }//GEN-LAST:event_modifierBtnActionPerformed

    private void supprimerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerBtnActionPerformed
        if (client.getText().equals("") || acompte.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez choisir un client", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String client_nom, nom, barcode, Quantity, total, total_base, categorie;

            nom = (String.valueOf(jTable3.getValueAt(0, 1)));
            barcode = String.valueOf(jTable3.getValueAt(0, 2));
            categorie = (String.valueOf(jTable3.getValueAt(0, 3)));
            Quantity = (String.valueOf(jTable3.getValueAt(0, 4)));
            String Prix = (String.valueOf(jTable3.getValueAt(0, 5)));
            total = (String.valueOf(jTable3.getValueAt(0, 7)));
            total_base = (String.valueOf(jTable3.getValueAt(0, 6)));
            String q = "delete  from credit"
                    + " where"
                    + " Barcode='" + credit.getText() + "'"
                    + "AND"
                    + " Client='" + client.getText() + "'";
            try {
                System.out.println(db.exécutionUpdate(q));
            } catch (Exception e) {
                System.out.println("e=" + e.toString());
            }

            JOptionPane.showMessageDialog(this, "l'article a été supprimé avec succès");
            actualiser();

        }
    }//GEN-LAST:event_supprimerBtnActionPerformed

    private void annulerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerBtnActionPerformed
        actualiser();
    }//GEN-LAST:event_annulerBtnActionPerformed

    private void clientKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clientKeyTyped
        char car = evt.getKeyChar();
        if ((car == '\'') || (client.getText().length() > 26)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_clientKeyTyped

    private void creditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_creditKeyTyped
        char car = evt.getKeyChar();
        if ((car == '\'') || (credit.getText().length() > 9)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_creditKeyTyped

    private void acompteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acompteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_acompteKeyTyped

    private void numeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Credit a = new Credit(utilisateur.getText());
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date1;
    private javax.swing.JLabel Heure1;
    private javax.swing.JLabel Logo;
    private javax.swing.JButton LogoutBtn;
    private javax.swing.JLabel LogoutBtnbg;
    private javax.swing.JPanel Session;
    private javax.swing.JTextField acompte;
    private javax.swing.JButton annulerBtn;
    private javax.swing.JPanel barpanel;
    private javax.swing.JTextField client;
    private javax.swing.JButton confirmerBtn;
    private javax.swing.JTextField credit;
    private javax.swing.JButton inventorybtn;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
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
    private javax.swing.JButton modifierBtn;
    private javax.swing.JTextField numero;
    private javax.swing.JButton registerbtn;
    private javax.swing.JButton salesbtn;
    private javax.swing.JButton statbtn;
    private javax.swing.JButton stockbtn;
    private javax.swing.JButton supprimerBtn;
    private javax.swing.JTextField txTest;
    private javax.swing.JTextField txTest1;
    private javax.swing.JButton usersbtn;
    private javax.swing.JLabel utilisateur;
    // End of variables declaration//GEN-END:variables
}

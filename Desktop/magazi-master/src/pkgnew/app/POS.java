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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import net.proteanit.sql.DbUtils;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableCellRenderer;

import static java.lang.Thread.sleep;

public class POS extends javax.swing.JFrame {

    ResultSet rs;
    DbConnection db;
    ConnectComPort dc;
    double num, ans, pb, pum;
    int calculation;
    public String getLogin;
    BDD.License l;
    String txnomcaise;
    String txquantiteaise;
    String prixVenteaise;
    String totalbaseaise;
    String totalaise;
    String ClientCredit;
    String NumeroCredit;
    private DefaultTableCellRenderer headerRenderer;

    public POS(String username) {
        try {

            initComponents();
            this.setDefaultCloseOperation(0);

            headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(new Color(30, 130, 82));
            headerRenderer.setForeground(new Color(255, 250, 240));

            db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
            dc = new ConnectComPort();
            db.connexionDatabase();
            //credit panel hide 
            credit_panel.hide();
            date();
            datecourante();
            table();
            table2();
            tableBon();
            table7();

            l = new BDD.License();
            utilisateur.setText(username);
            this.setExtendedState(this.MAXIMIZED_BOTH);
            produit();

            recherche_Barcode.requestFocus();

            ///calcule total
            float c = 0;
            for (int k = 0; k < jTable1.getRowCount(); k++) {
                String b = String.valueOf(jTable1.getValueAt(k, 7)).replace(',', '.');
                float d = Float.parseFloat(b);
                c = c + d;

            }

            totale.setText(Float.toString(c));

            CaissePanel.hide();

            ///
            {
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
                String a[] = {"nom", "Prix_vente", "Prix_base"};
                rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "eau" + "%' ");

                jTable8.setModel(new ResultSetTableModel(rs));
                ArrayList l = new ArrayList();

                for (int i = 0; i < jTable8.getRowCount(); i++) {
                    l.add(String.valueOf(jTable8.getValueAt(i, 0)));

                }
                System.out.println(l.size());
                panel_product(l);

            }
            //this.setUndecorated(true);

            {
                String q = "SELECT type from utilisateur WHERE Login =' " + utilisateur.getText() + "'";

            }

            if (isUser(username)) {

                jLabel20.hide();
                jLabel21.hide();
                jLabel22.hide();

                salesbtn.hide();
                usersbtn.hide();
                statbtn.hide();
            }

            MyKeyListener keyboard = new MyKeyListener();
            general_panel.addKeyListener(keyboard);
            general_panel.setFocusable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
            db.queryDelete("caisse");
        }
    }

    // tableau numero de ticket
    public void tableBon() {
        String a[] = {"Id", "Numero"};
        rs = db.querySelect(a, "nbon");
        jTable13.setModel(new ResultSetTableModel(rs));
    }

    public boolean isUser(String username) {
        String q = "Select type from utilisateur where Login ='" + username + "'";
        rs = db.exécutionQuery(q);
        try {
            rs.next();
            //System.err.println(rs.getString(1));
            if ((rs.getString(1).toLowerCase()).equals("utilisateur")) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void table_inventaire() {
        String b[] = {"id", "nom", "Barcode", "categorie", "prix_vente", "prix_base", "Max", "Min"};
        rs = db.querySelect(b, "produit");
        jTable2.setModel(DbUtils.resultSetToTableModel(rs));

    }

    public void produit() {

        String a[] = {"nom", "Prix_vente", "Prix_base"};
        rs = db.querySelect(a, "produit");
        jTable8.setModel(new ResultSetTableModel(rs));
        jTable8.getColumnModel().getColumn(0).setMinWidth(0);
        jTable8.getColumnModel().getColumn(0).setMaxWidth(0);

    }

    public void client() {

        String a[] = {"nom", "numero"};
        rs = db.querySelect(a, "client");
        client_table.setModel(new ResultSetTableModel(rs));
        client_table.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        client_table.getTableHeader().setOpaque(false);
        client_table.getTableHeader().setBackground(Color.BLACK);
        client_table.getTableHeader().setForeground(new Color(19, 35, 132));

        client_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        client_table.setRowHeight(30);

        jScrollPane9.getViewport().setBackground(new Color(255, 250, 240));
        jScrollPane9.setBorder(BorderFactory.createLineBorder(new Color(255, 250, 240), 0));

        for (int i = 0; i < client_table.getModel().getColumnCount(); i++) {
            client_table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

    }

    public void getUser() {

    }

    public void panel_product(ArrayList l) {
        this.produits.removeAll();
        this.produits.repaint();
        String a[] = {"nom", "Barcode", "Categorie", "Prix_vente", "Prix_base"};
        int x = 0;
        int y = 0;
        int i = l.size() - 1;

        for (int j = 0; j < i && j < 30; j++) {
            if (x > 550) {
                x = 0;
                y = y + 78;
            }

            JButton b = new JButton(String.valueOf(j));
            b.setText((String) l.get(j));
            b.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    b.getText();
                    rs = db.fcSelectCommand(a, "produit", " nom LIKE '%" + b.getText() + "%' ");
                    System.out.println(b.getText());
                    jTable2.setModel(new ResultSetTableModel(rs));
                    jTable2.getColumnModel().getColumn(0).setMinWidth(150);
                    jTable2.getColumnModel().getColumn(0).setMaxWidth(150);
                    jTable2.getColumnModel().getColumn(1).setMinWidth(0);
                    jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
                    jTable2.getColumnModel().getColumn(2).setMinWidth(0);
                    jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
                    jTable2.getColumnModel().getColumn(4).setMinWidth(0);
                    jTable2.getColumnModel().getColumn(4).setMaxWidth(0);
                    jTable2.setRowSelectionInterval(0, 0);
                    afficheur.setText((String.valueOf(jTable2.getValueAt(0, 3))));
                    Nom.setText(String.valueOf(jTable2.getValueAt(0, 0)));
                    Prix_Vente.setText(String.valueOf(jTable2.getValueAt(0, 3)));
                    prix_base.setText((String.valueOf(jTable2.getValueAt(0, 4))));
                    recherche_Barcode.setText((String.valueOf(jTable2.getValueAt(0, 1))));
                    recherche_Barcode.requestFocus();

                }
            });

            b.setBounds(x, y, 150, 70);
            //b.setPreferredSize(new Dimension(80, 20)); //
            b.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
            b.setForeground(new Color(30, 130, 82));
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            //b.setBorder(BorderFactory.createLineBorder(new Color(19, 35, 132), 2));
            this.produits.add(b);

            x = x + 160;

        }
        this.produits.add(jLabel13);
    }

    public void table() {
        String a[] = {"nom", "Barcode", "Categorie", "Prix_vente", "Prix_base"};
        rs = db.querySelect(a, "produit");
        jTable2.setModel(new ResultSetTableModel(rs));

        jTable2.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        jTable2.getTableHeader().setOpaque(false);
        jTable2.getTableHeader().setBackground(Color.BLACK);
        jTable2.getTableHeader().setForeground(new Color(19, 35, 132));

        jTable2.getColumnModel().getColumn(0).setMinWidth(150);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(150);
        jTable2.getColumnModel().getColumn(4).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(4).setMinWidth(0);
        jTable2.getColumnModel().getColumn(4).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(1).setMinWidth(0);
        jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable2.setRowHeight(30);

    }

    public void table7() {
        String a[] = {"id", "nom", "Prix_vente", "Quantite"};
        rs = db.querySelect(a, "produit_stock");
        jTable7.setModel(new ResultSetTableModel(rs));
        jTable7.getColumnModel().getColumn(0).setMinWidth(0);
        jTable7.getColumnModel().getColumn(0).setMaxWidth(0);

    }

    public void table2() {
        String a[] = {"id", "Nom", "Barcode", "Categorie", "Quantite", "Prix_vente", "total_base", "total"};
        rs = db.querySelect(a, "caisse");
        jTable1.setModel(new ResultSetTableModel(rs));

        jTable1.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(Color.BLACK);
        jTable1.getTableHeader().setForeground(new Color(19, 35, 132));

        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(6).setMinWidth(0);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.setRowHeight(30);

        jScrollPane1.getViewport().setBackground(new Color(255, 250, 240));
        jScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(255, 250, 240), 0));

        for (int i = 0; i < jTable1.getModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

    }

    public void calculTotal() {
        Float total;
        total = Float.parseFloat(totale.getText()) + Float.parseFloat(afficheur.getText());
        totale.setText(Float.toString(total));

    }

    public void arithmetic_operation() {
        switch (calculation) {

            case 3:

                ans = num * Double.parseDouble(afficheur.getText());
                System.out.println(num + ", " + ans);
                pb = Double.parseDouble(Quantite.getText()) * Double.parseDouble(prix_base.getText());
                System.out.println(pb);
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(3);
                df.setMinimumFractionDigits(3);
                String valeur = df.format(ans).replace(",", ".");

                System.err.println("total =" + valeur);

                String valeur1 = df.format(pb).replace(",", ".");

                System.err.println("totale base" + valeur1);

                //txTotal.setText(valeur);
                totale.setText(valeur);
                total_base.setText(valeur1);
                break;

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

    public void actualiser() {
        Nom.setText("");
        Prix_Vente.setText("");
        Quantite.setText("");
        totale.setText("");
        secondafficheur.setText("");
        afficheur.setText("");
        table();
        table2();
        tableBon();
        recherche_Barcode.setText("");
        Barsearch.setText("");
        credit_panel.hide();

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
        total_base = new javax.swing.JLabel();
        jRibbonFrame1 = new org.pushingpixels.flamingo.api.ribbon.JRibbonFrame();
        txTest1 = new javax.swing.JLabel();
        Numero = new javax.swing.JLabel();
        Barcode = new javax.swing.JLabel();
        Categorie = new javax.swing.JLabel();
        Prix_vente = new javax.swing.JLabel();
        Quantite = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        backcalcule = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Heure = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        prix_base = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txTest2 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        Nom = new javax.swing.JLabel();
        Prix_Vente = new javax.swing.JLabel();
        Barcodelabel = new javax.swing.JLabel();
        categorie2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        CaissePanel = new javax.swing.JPanel();
        BrutLabel1 = new javax.swing.JLabel();
        NetLabel1 = new javax.swing.JLabel();
        NetLabel = new javax.swing.JLabel();
        BrutLabel = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        CancelBtn = new javax.swing.JButton();
        EndSessionBtn = new javax.swing.JButton();
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
        Date1 = new javax.swing.JLabel();
        Heure1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        general_panel = new javax.swing.JPanel();
        category_bar = new javax.swing.JPanel();
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
        produits = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        CommandePanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        EnterBtn = new javax.swing.JButton();
        NumPad1 = new javax.swing.JButton();
        NumPad2 = new javax.swing.JButton();
        NumPad3 = new javax.swing.JButton();
        NumPad4 = new javax.swing.JButton();
        NumPad5 = new javax.swing.JButton();
        NumPad6 = new javax.swing.JButton();
        NumPad7 = new javax.swing.JButton();
        NumPad8 = new javax.swing.JButton();
        NumPad9 = new javax.swing.JButton();
        NumPad0 = new javax.swing.JButton();
        NumPadX = new javax.swing.JButton();
        NumPadCE = new javax.swing.JButton();
        NumPadC = new javax.swing.JButton();
        totale = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jButton51 = new javax.swing.JButton();
        jPanel36 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton61 = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jButton64 = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton65 = new javax.swing.JButton();
        jButton66 = new javax.swing.JButton();
        jButton67 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jButton68 = new javax.swing.JButton();
        jButton69 = new javax.swing.JButton();
        jButton70 = new javax.swing.JButton();
        jButton71 = new javax.swing.JButton();
        jButton72 = new javax.swing.JButton();
        jButton73 = new javax.swing.JButton();
        jButton74 = new javax.swing.JButton();
        jButton75 = new javax.swing.JButton();
        jButton76 = new javax.swing.JButton();
        jButton77 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        afficheur = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        afficheurbg1 = new javax.swing.JLabel();
        recherche_Barcode = new javax.swing.JTextField();
        secondafficheur = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTable2 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        afficheurbg = new javax.swing.JLabel();
        Barsearch = new javax.swing.JTextField();
        searchbarbg = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        annulerBtn = new javax.swing.JButton();
        supprimerBtn = new javax.swing.JButton();
        confirmerBtn = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        credit_panel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        client_table = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        clientNumero = new javax.swing.JTextField();
        clientNom = new javax.swing.JTextField();
        ajout_client_button = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        Background = new javax.swing.JPanel();

        Date.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Date.setText("31-12-2019");

        total_base.setOpaque(true);

        txTest1.setText("jLabel6");

        Numero.setText("jLabel6");

        Barcode.setText("jLabel6");

        Categorie.setText("jLabel6");

        Prix_vente.setText("jLabel7");

        Quantite.setText("jLabel8");

        jLabel6.setText("jLabel6");

        backcalcule.setText("1");

        jLabel2.setText("jLabel2");

        Heure.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Heure.setText("22:10");

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

        prix_base.setOpaque(true);

        jLabel3.setText("jLabel3");
        jLabel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel3KeyPressed(evt);
            }
        });

        txTest2.setText("jLabel11");

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

        Nom.setText("jLabel11");

        Prix_Vente.setText("jLabel11");

        Barcodelabel.setText("jLabel4");

        categorie2.setText("jLabel4");

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
        jScrollPane2.setViewportView(jTable13);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setSize(new java.awt.Dimension(1300, 760));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        getContentPane().setLayout(null);

        CaissePanel.setBackground(new java.awt.Color(30, 130, 82));
        CaissePanel.setForeground(new java.awt.Color(255, 250, 240));
        CaissePanel.setFont(new java.awt.Font("Roboto Medium", 0, 36)); // NOI18N
        CaissePanel.setLayout(null);

        BrutLabel1.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        BrutLabel1.setForeground(new java.awt.Color(255, 250, 240));
        BrutLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BrutLabel1.setText("brut:");
        CaissePanel.add(BrutLabel1);
        BrutLabel1.setBounds(110, 120, 130, 60);

        NetLabel1.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        NetLabel1.setForeground(new java.awt.Color(255, 250, 240));
        NetLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NetLabel1.setText("net: ");
        CaissePanel.add(NetLabel1);
        NetLabel1.setBounds(110, 200, 130, 60);

        NetLabel.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        NetLabel.setForeground(new java.awt.Color(255, 250, 240));
        NetLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        NetLabel.setText("000.000");
        CaissePanel.add(NetLabel);
        NetLabel.setBounds(110, 200, 470, 60);

        BrutLabel.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        BrutLabel.setForeground(new java.awt.Color(255, 250, 240));
        BrutLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BrutLabel.setText("000.000");
        CaissePanel.add(BrutLabel);
        BrutLabel.setBounds(110, 120, 470, 60);

        jLabel17.setFont(new java.awt.Font("Roboto Medium", 0, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 250, 240));
        jLabel17.setText("Votre revenu pour ce jour est:");
        CaissePanel.add(jLabel17);
        jLabel17.setBounds(20, 10, 520, 80);

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/Annulerlogoutbtn.png"))); // NOI18N
        CaissePanel.add(jLabel23);
        jLabel23.setBounds(420, 300, 350, 70);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/Endsessionbtn.png"))); // NOI18N
        CaissePanel.add(jLabel14);
        jLabel14.setBounds(40, 300, 350, 70);

        CancelBtn.setText("annuler");
        CancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelBtnActionPerformed(evt);
            }
        });
        CaissePanel.add(CancelBtn);
        CancelBtn.setBounds(430, 310, 300, 50);

        EndSessionBtn.setText("finir la session");
        EndSessionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EndSessionBtnActionPerformed(evt);
            }
        });
        CaissePanel.add(EndSessionBtn);
        EndSessionBtn.setBounds(50, 310, 300, 50);

        getContentPane().add(CaissePanel);
        CaissePanel.setBounds(100, 190, 790, 400);

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

        jLabel18.setBackground(new java.awt.Color(255, 250, 240));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/Stock.png"))); // NOI18N
        jLabel18.setOpaque(true);
        barpanel.add(jLabel18);
        jLabel18.setBounds(10, 280, 60, 50);

        jLabel16.setBackground(new java.awt.Color(22, 41, 153));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/cashregisterLight.png"))); // NOI18N
        barpanel.add(jLabel16);
        jLabel16.setBounds(0, 180, 80, 50);

        jLabel5.setBackground(new java.awt.Color(30, 130, 82));
        jLabel5.setOpaque(true);
        barpanel.add(jLabel5);
        jLabel5.setBounds(0, 170, 80, 70);

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

        Date1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Date1.setForeground(new java.awt.Color(5, 45, 26));
        Date1.setText("31-12-2019");
        jPanel1.add(Date1);
        Date1.setBounds(640, 5, 100, 70);

        Heure1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Heure1.setForeground(new java.awt.Color(5, 45, 26));
        Heure1.setText("22:10");
        jPanel1.add(Heure1);
        Heure1.setBounds(510, 5, 100, 70);

        jLabel10.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(30, 130, 82));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("CAISSE");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(860, 0, 170, 80);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(50, 0, 1220, 80);

        general_panel.setBackground(new java.awt.Color(255, 250, 240));
        general_panel.setToolTipText("");
        general_panel.setMaximumSize(new java.awt.Dimension(1920, 1080));
        general_panel.setMinimumSize(new java.awt.Dimension(1920, 1080));
        general_panel.setPreferredSize(new java.awt.Dimension(1290, 720));
        general_panel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                general_panelKeyTyped(evt);
            }
        });
        general_panel.setLayout(null);

        category_bar.setBackground(new java.awt.Color(255, 255, 255));
        category_bar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        category_bar.setOpaque(false);
        category_bar.setLayout(null);

        CatfruitssecsPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barfruitssecs.png"))); // NOI18N
        category_bar.add(CatfruitssecsPanel);
        CatfruitssecsPanel.setBounds(0, 22, 510, 60);

        CatconservesPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barconserve.png"))); // NOI18N
        category_bar.add(CatconservesPanel);
        CatconservesPanel.setBounds(0, 22, 510, 60);

        CatpatesPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barpates.png"))); // NOI18N
        category_bar.add(CatpatesPanel);
        CatpatesPanel.setBounds(0, 22, 500, 60);

        CataccPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/baraccessoires.png"))); // NOI18N
        category_bar.add(CataccPanel);
        CataccPanel.setBounds(0, 22, 500, 60);

        CatrechargePanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barrecharge.png"))); // NOI18N
        category_bar.add(CatrechargePanel);
        CatrechargePanel.setBounds(0, 22, 510, 60);

        CatcigarettePanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barcigarettes.png"))); // NOI18N
        category_bar.add(CatcigarettePanel);
        CatcigarettePanel.setBounds(0, 22, 500, 60);

        CattabacPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/bartabac.png"))); // NOI18N
        category_bar.add(CattabacPanel);
        CattabacPanel.setBounds(0, 22, 510, 60);

        CatgouterPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/bargouter.png"))); // NOI18N
        category_bar.add(CatgouterPanel);
        CatgouterPanel.setBounds(0, 22, 500, 60);

        CatboissonPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barboisson.png"))); // NOI18N
        category_bar.add(CatboissonPanel);
        CatboissonPanel.setBounds(0, 22, 510, 60);

        CateauPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/bareau.png"))); // NOI18N
        category_bar.add(CateauPanel);
        CateauPanel.setBounds(0, 22, 500, 60);

        FruitssecsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FruitssecsBtnActionPerformed(evt);
            }
        });
        category_bar.add(FruitssecsBtn);
        FruitssecsBtn.setBounds(430, 35, 60, 35);

        ConserveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConserveBtnActionPerformed(evt);
            }
        });
        category_bar.add(ConserveBtn);
        ConserveBtn.setBounds(380, 35, 50, 35);

        PatesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatesBtnActionPerformed(evt);
            }
        });
        category_bar.add(PatesBtn);
        PatesBtn.setBounds(350, 35, 40, 35);

        AccBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccBtnActionPerformed(evt);
            }
        });
        category_bar.add(AccBtn);
        AccBtn.setBounds(285, 36, 60, 35);

        rechargeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechargeBtnActionPerformed(evt);
            }
        });
        category_bar.add(rechargeBtn);
        rechargeBtn.setBounds(220, 36, 60, 35);

        cigaretteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cigaretteBtnActionPerformed(evt);
            }
        });
        category_bar.add(cigaretteBtn);
        cigaretteBtn.setBounds(160, 36, 60, 35);

        tabacBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tabacBtnActionPerformed(evt);
            }
        });
        category_bar.add(tabacBtn);
        tabacBtn.setBounds(130, 36, 40, 35);

        gouterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gouterBtnActionPerformed(evt);
            }
        });
        category_bar.add(gouterBtn);
        gouterBtn.setBounds(90, 36, 40, 35);

        boissonBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boissonBtnActionPerformed(evt);
            }
        });
        category_bar.add(boissonBtn);
        boissonBtn.setBounds(35, 36, 50, 35);

        eauBtn.setBackground(new java.awt.Color(255, 255, 255));
        eauBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        eauBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eauBtnActionPerformed(evt);
            }
        });
        category_bar.add(eauBtn);
        eauBtn.setBounds(8, 36, 30, 35);

        general_panel.add(category_bar);
        category_bar.setBounds(100, 150, 800, 100);

        produits.setBackground(new java.awt.Color(255, 250, 240));
        produits.setLayout(null);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/quickbtns.png"))); // NOI18N
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        produits.add(jLabel13);
        jLabel13.setBounds(10, 10, 620, 710);

        general_panel.add(produits);
        produits.setBounds(110, 250, 620, 740);

        CommandePanel.setBackground(new java.awt.Color(255, 250, 240));
        CommandePanel.setLayout(null);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/numPad.png"))); // NOI18N
        CommandePanel.add(jLabel8);
        jLabel8.setBounds(250, 150, 240, 250);

        EnterBtn.setBackground(new java.awt.Color(0, 0, 153));
        EnterBtn.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        EnterBtn.setForeground(new java.awt.Color(255, 255, 255));
        EnterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnterBtnActionPerformed(evt);
            }
        });
        CommandePanel.add(EnterBtn);
        EnterBtn.setBounds(380, 340, 110, 50);

        NumPad1.setBackground(new java.awt.Color(0, 0, 153));
        NumPad1.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        NumPad1.setForeground(new java.awt.Color(255, 255, 255));
        NumPad1.setText("1");
        NumPad1.setBorder(null);
        NumPad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumPad1ActionPerformed(evt);
            }
        });
        CommandePanel.add(NumPad1);
        NumPad1.setBounds(255, 160, 45, 45);

        NumPad2.setBackground(new java.awt.Color(0, 0, 153));
        NumPad2.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        NumPad2.setForeground(new java.awt.Color(255, 255, 255));
        NumPad2.setText("2");
        NumPad2.setBorder(null);
        NumPad2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumPad2ActionPerformed(evt);
            }
        });
        CommandePanel.add(NumPad2);
        NumPad2.setBounds(317, 160, 45, 45);

        NumPad3.setBackground(new java.awt.Color(0, 0, 153));
        NumPad3.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        NumPad3.setForeground(new java.awt.Color(255, 255, 255));
        NumPad3.setText("3");
        NumPad3.setBorder(null);
        NumPad3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumPad3ActionPerformed(evt);
            }
        });
        CommandePanel.add(NumPad3);
        NumPad3.setBounds(378, 160, 45, 45);

        NumPad4.setBackground(new java.awt.Color(0, 0, 153));
        NumPad4.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        NumPad4.setForeground(new java.awt.Color(255, 255, 255));
        NumPad4.setText("4");
        NumPad4.setBorder(null);
        NumPad4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumPad4ActionPerformed(evt);
            }
        });
        CommandePanel.add(NumPad4);
        NumPad4.setBounds(255, 222, 45, 45);

        NumPad5.setBackground(new java.awt.Color(0, 0, 153));
        NumPad5.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        NumPad5.setForeground(new java.awt.Color(255, 255, 255));
        NumPad5.setText("5");
        NumPad5.setBorder(null);
        NumPad5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumPad5ActionPerformed(evt);
            }
        });
        CommandePanel.add(NumPad5);
        NumPad5.setBounds(317, 222, 45, 45);

        NumPad6.setBackground(new java.awt.Color(0, 0, 153));
        NumPad6.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        NumPad6.setForeground(new java.awt.Color(255, 255, 255));
        NumPad6.setText("6");
        NumPad6.setBorder(null);
        NumPad6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumPad6ActionPerformed(evt);
            }
        });
        CommandePanel.add(NumPad6);
        NumPad6.setBounds(378, 222, 45, 45);

        NumPad7.setBackground(new java.awt.Color(0, 0, 153));
        NumPad7.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        NumPad7.setForeground(new java.awt.Color(255, 255, 255));
        NumPad7.setText("7");
        NumPad7.setBorder(null);
        NumPad7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumPad7ActionPerformed(evt);
            }
        });
        CommandePanel.add(NumPad7);
        NumPad7.setBounds(255, 283, 45, 45);

        NumPad8.setBackground(new java.awt.Color(0, 0, 153));
        NumPad8.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        NumPad8.setForeground(new java.awt.Color(255, 255, 255));
        NumPad8.setText("8");
        NumPad8.setBorder(null);
        NumPad8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumPad8ActionPerformed(evt);
            }
        });
        CommandePanel.add(NumPad8);
        NumPad8.setBounds(317, 283, 45, 45);

        NumPad9.setBackground(new java.awt.Color(0, 0, 153));
        NumPad9.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        NumPad9.setForeground(new java.awt.Color(255, 255, 255));
        NumPad9.setText("9");
        NumPad9.setBorder(null);
        NumPad9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumPad9ActionPerformed(evt);
            }
        });
        CommandePanel.add(NumPad9);
        NumPad9.setBounds(378, 283, 45, 45);

        NumPad0.setBackground(new java.awt.Color(0, 0, 153));
        NumPad0.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        NumPad0.setForeground(new java.awt.Color(255, 255, 255));
        NumPad0.setText("0");
        NumPad0.setBorder(null);
        NumPad0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumPad0ActionPerformed(evt);
            }
        });
        CommandePanel.add(NumPad0);
        NumPad0.setBounds(317, 345, 45, 45);

        NumPadX.setBackground(new java.awt.Color(0, 0, 153));
        NumPadX.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        NumPadX.setForeground(new java.awt.Color(255, 255, 255));
        NumPadX.setText("x");
        NumPadX.setBorder(null);
        NumPadX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumPadXActionPerformed(evt);
            }
        });
        CommandePanel.add(NumPadX);
        NumPadX.setBounds(440, 160, 45, 45);

        NumPadCE.setBackground(new java.awt.Color(0, 0, 153));
        NumPadCE.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        NumPadCE.setForeground(new java.awt.Color(255, 255, 255));
        NumPadCE.setText("CE");
        NumPadCE.setBorder(null);
        NumPadCE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumPadCEActionPerformed(evt);
            }
        });
        CommandePanel.add(NumPadCE);
        NumPadCE.setBounds(440, 222, 45, 45);

        NumPadC.setBackground(new java.awt.Color(0, 0, 153));
        NumPadC.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        NumPadC.setForeground(new java.awt.Color(255, 255, 255));
        NumPadC.setText("C");
        NumPadC.setBorder(null);
        NumPadC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumPadCActionPerformed(evt);
            }
        });
        CommandePanel.add(NumPadC);
        NumPadC.setBounds(440, 283, 45, 45);

        totale.setBackground(new java.awt.Color(241, 241, 241));
        totale.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        totale.setForeground(new java.awt.Color(5, 45, 26));
        totale.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totale.setText("0");
        totale.setAlignmentX(0.5F);
        totale.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        totale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totaleMouseClicked(evt);
            }
        });
        CommandePanel.add(totale);
        totale.setBounds(10, 370, 200, 70);

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setLayout(null);

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

        jPanel35.add(jScrollPane3);
        jScrollPane3.setBounds(10, 20, 340, 360);

        jButton48.setText("jButton12");
        jPanel35.add(jButton48);
        jButton48.setBounds(390, 360, 79, 70);

        jButton49.setText("jButton12");
        jPanel35.add(jButton49);
        jButton49.setBounds(390, 120, 79, 70);

        jButton50.setText("jButton12");
        jPanel35.add(jButton50);
        jButton50.setBounds(390, 200, 79, 70);

        jLabel38.setBackground(new java.awt.Color(241, 241, 241));
        jLabel38.setText("jLabel36");
        jLabel38.setOpaque(true);
        jPanel35.add(jLabel38);
        jLabel38.setBounds(390, 20, 430, 70);

        jLabel39.setBackground(new java.awt.Color(241, 241, 241));
        jLabel39.setText("jLabel36");
        jLabel39.setOpaque(true);
        jPanel35.add(jLabel39);
        jLabel39.setBounds(10, 390, 340, 70);

        jButton51.setText("jButton12");
        jPanel35.add(jButton51);
        jButton51.setBounds(390, 280, 79, 70);

        CommandePanel.add(jPanel35);
        jPanel35.setBounds(0, 0, 0, 0);

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setLayout(null);

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

        jPanel36.add(jScrollPane4);
        jScrollPane4.setBounds(10, 20, 340, 360);

        jButton61.setText("jButton12");
        jPanel36.add(jButton61);
        jButton61.setBounds(390, 360, 79, 70);

        jButton62.setText("jButton12");
        jPanel36.add(jButton62);
        jButton62.setBounds(390, 120, 79, 70);

        jButton63.setText("jButton12");
        jPanel36.add(jButton63);
        jButton63.setBounds(390, 200, 79, 70);

        jLabel40.setBackground(new java.awt.Color(241, 241, 241));
        jLabel40.setText("jLabel36");
        jLabel40.setOpaque(true);
        jPanel36.add(jLabel40);
        jLabel40.setBounds(390, 20, 430, 70);

        jLabel41.setBackground(new java.awt.Color(241, 241, 241));
        jLabel41.setText("jLabel36");
        jLabel41.setOpaque(true);
        jPanel36.add(jLabel41);
        jLabel41.setBounds(10, 390, 340, 70);

        jButton64.setText("jButton12");
        jPanel36.add(jButton64);
        jButton64.setBounds(390, 280, 79, 70);

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setLayout(null);

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

        jPanel37.add(jScrollPane5);
        jScrollPane5.setBounds(10, 20, 340, 360);

        jButton65.setText("jButton12");
        jPanel37.add(jButton65);
        jButton65.setBounds(390, 360, 79, 70);

        jButton66.setText("jButton12");
        jPanel37.add(jButton66);
        jButton66.setBounds(390, 120, 79, 70);

        jButton67.setText("jButton12");
        jPanel37.add(jButton67);
        jButton67.setBounds(390, 200, 79, 70);

        jLabel42.setBackground(new java.awt.Color(241, 241, 241));
        jLabel42.setText("jLabel36");
        jLabel42.setOpaque(true);
        jPanel37.add(jLabel42);
        jLabel42.setBounds(390, 20, 430, 70);

        jLabel43.setBackground(new java.awt.Color(241, 241, 241));
        jLabel43.setText("jLabel36");
        jLabel43.setOpaque(true);
        jPanel37.add(jLabel43);
        jLabel43.setBounds(10, 390, 340, 70);

        jButton68.setText("jButton12");
        jPanel37.add(jButton68);
        jButton68.setBounds(390, 280, 79, 70);

        jPanel36.add(jPanel37);
        jPanel37.setBounds(0, 0, 0, 0);

        jButton69.setText("jButton12");
        jPanel36.add(jButton69);
        jButton69.setBounds(490, 120, 79, 70);

        jButton70.setText("jButton12");
        jPanel36.add(jButton70);
        jButton70.setBounds(490, 200, 79, 70);

        jButton71.setText("jButton12");
        jPanel36.add(jButton71);
        jButton71.setBounds(490, 280, 79, 70);

        jButton72.setText("jButton12");
        jPanel36.add(jButton72);
        jButton72.setBounds(680, 120, 79, 70);

        jButton73.setText("jButton12");
        jPanel36.add(jButton73);
        jButton73.setBounds(680, 200, 79, 70);

        jButton74.setText("jButton12");
        jPanel36.add(jButton74);
        jButton74.setBounds(680, 280, 79, 70);

        jButton75.setText("jButton12");
        jPanel36.add(jButton75);
        jButton75.setBounds(580, 120, 79, 70);

        jButton76.setText("jButton12");
        jPanel36.add(jButton76);
        jButton76.setBounds(580, 200, 79, 70);

        jButton77.setText("jButton12");
        jPanel36.add(jButton77);
        jButton77.setBounds(580, 280, 79, 70);

        CommandePanel.add(jPanel36);
        jPanel36.setBounds(0, 0, 0, 0);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/commandebg.png"))); // NOI18N
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        CommandePanel.add(jLabel9);
        jLabel9.setBounds(0, 460, 530, 210);

        jTable1.setBackground(new java.awt.Color(255, 250, 240));
        jTable1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
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
        jTable1.setAlignmentX(2.0F);
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(255, 250, 240));
        jTable1.setOpaque(false);
        jTable1.setRowHeight(20);
        jTable1.setSelectionBackground(new java.awt.Color(30, 130, 82));
        jTable1.setSelectionForeground(new java.awt.Color(255, 250, 240));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        CommandePanel.add(jScrollPane1);
        jScrollPane1.setBounds(0, 460, 520, 200);

        afficheur.setBackground(new java.awt.Color(255, 250, 240));
        afficheur.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        afficheur.setForeground(new java.awt.Color(5, 45, 26));
        afficheur.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        afficheur.setText("0");
        afficheur.setBorder(null);
        afficheur.setCaretColor(new java.awt.Color(5, 45, 26));
        afficheur.setFocusable(false);
        afficheur.setOpaque(false);
        afficheur.setSelectedTextColor(new java.awt.Color(255, 250, 240));
        afficheur.setSelectionColor(new java.awt.Color(30, 130, 82));
        afficheur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afficheurActionPerformed(evt);
            }
        });
        afficheur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                afficheurKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                afficheurKeyTyped(evt);
            }
        });
        CommandePanel.add(afficheur);
        afficheur.setBounds(260, 90, 210, 50);

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(30, 130, 90));
        jLabel1.setText("Dt");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        CommandePanel.add(jLabel1);
        jLabel1.setBounds(230, 400, 40, 50);

        afficheurbg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/barcodebg.png"))); // NOI18N
        afficheurbg1.setText("jLabel13");
        afficheurbg1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        afficheurbg1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                afficheurbg1KeyReleased(evt);
            }
        });
        CommandePanel.add(afficheurbg1);
        afficheurbg1.setBounds(250, 15, 240, 30);

        recherche_Barcode.setBackground(new java.awt.Color(255, 250, 240));
        recherche_Barcode.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        recherche_Barcode.setForeground(new java.awt.Color(5, 45, 26));
        recherche_Barcode.setBorder(null);
        recherche_Barcode.setSelectedTextColor(new java.awt.Color(255, 250, 240));
        recherche_Barcode.setSelectionColor(new java.awt.Color(30, 130, 82));
        recherche_Barcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recherche_BarcodeActionPerformed(evt);
            }
        });
        recherche_Barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                recherche_BarcodeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recherche_BarcodeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                recherche_BarcodeKeyTyped(evt);
            }
        });
        CommandePanel.add(recherche_Barcode);
        recherche_Barcode.setBounds(260, 20, 220, 20);

        secondafficheur.setBackground(new java.awt.Color(255, 250, 240));
        secondafficheur.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        secondafficheur.setForeground(new java.awt.Color(5, 45, 26));
        secondafficheur.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        secondafficheur.setBorder(null);
        secondafficheur.setOpaque(false);
        CommandePanel.add(secondafficheur);
        secondafficheur.setBounds(370, 70, 100, 20);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/Listbg.png"))); // NOI18N
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        CommandePanel.add(jLabel4);
        jLabel4.setBounds(1, 15, 220, 310);

        jTable2.setBackground(new java.awt.Color(255, 250, 240));
        jTable2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jTable2.setForeground(new java.awt.Color(5, 45, 26));
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
        jTable2.setFocusable(false);
        jTable2.setGridColor(new java.awt.Color(255, 250, 240));
        jTable2.setOpaque(false);
        jTable2.setSelectionBackground(new java.awt.Color(30, 130, 82));
        jTable2.setSelectionForeground(new java.awt.Color(255, 250, 240));
        jTable2.setShowHorizontalLines(false);
        jTable2.setShowVerticalLines(false);
        jTable2.getTableHeader().setResizingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        CommandePanel.add(jTable2);
        jTable2.setBounds(4, 20, 215, 300);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/totalbg.png"))); // NOI18N
        CommandePanel.add(jLabel11);
        jLabel11.setBounds(0, 360, 250, 90);

        jLabel12.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(30, 130, 90));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("total");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        CommandePanel.add(jLabel12);
        jLabel12.setBounds(10, 330, 220, 30);

        afficheurbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/afficheurbg.png"))); // NOI18N
        afficheurbg.setText("jLabel13");
        afficheurbg.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        CommandePanel.add(afficheurbg);
        afficheurbg.setBounds(250, 50, 240, 95);

        general_panel.add(CommandePanel);
        CommandePanel.setBounds(730, 90, 540, 670);

        Barsearch.setBackground(new java.awt.Color(235, 231, 224));
        Barsearch.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Barsearch.setForeground(new java.awt.Color(5, 45, 26));
        Barsearch.setBorder(null);
        Barsearch.setOpaque(false);
        Barsearch.setSelectedTextColor(new java.awt.Color(255, 250, 240));
        Barsearch.setSelectionColor(new java.awt.Color(5, 45, 26));
        Barsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BarsearchKeyReleased(evt);
            }
        });
        general_panel.add(Barsearch);
        Barsearch.setBounds(160, 110, 470, 30);

        searchbarbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/searchbarbg.png"))); // NOI18N
        general_panel.add(searchbarbg);
        searchbarbg.setBounds(140, 95, 560, 60);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/pos.png"))); // NOI18N
        general_panel.add(jLabel7);
        jLabel7.setBounds(770, 760, 490, 50);

        annulerBtn.setBackground(new java.awt.Color(0, 0, 102));
        annulerBtn.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        annulerBtn.setForeground(new java.awt.Color(255, 255, 255));
        annulerBtn.setText("cancel");
        annulerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerBtnActionPerformed(evt);
            }
        });
        general_panel.add(annulerBtn);
        annulerBtn.setBounds(770, 770, 140, 30);

        supprimerBtn.setBackground(new java.awt.Color(254, 3, 48));
        supprimerBtn.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        supprimerBtn.setForeground(new java.awt.Color(255, 255, 255));
        supprimerBtn.setText("delete");
        supprimerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerBtnActionPerformed(evt);
            }
        });
        general_panel.add(supprimerBtn);
        supprimerBtn.setBounds(940, 770, 130, 30);

        confirmerBtn.setBackground(new java.awt.Color(0, 255, 51));
        confirmerBtn.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        confirmerBtn.setForeground(new java.awt.Color(255, 255, 255));
        confirmerBtn.setText("confirmer");
        confirmerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmerBtnActionPerformed(evt);
            }
        });
        confirmerBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                confirmerBtnKeyReleased(evt);
            }
        });
        general_panel.add(confirmerBtn);
        confirmerBtn.setBounds(1100, 770, 130, 40);

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/creditbtn.png"))); // NOI18N
        general_panel.add(jLabel28);
        jLabel28.setBounds(930, 820, 160, 50);

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        general_panel.add(jButton1);
        jButton1.setBounds(930, 830, 140, 30);

        credit_panel.setBackground(new java.awt.Color(255, 250, 240));
        credit_panel.setLayout(null);

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/commandebg.png"))); // NOI18N
        jLabel27.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        credit_panel.add(jLabel27);
        jLabel27.setBounds(20, 150, 530, 260);

        client_table.setBackground(new java.awt.Color(255, 250, 240));
        client_table.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        client_table.setModel(new javax.swing.table.DefaultTableModel(
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
        client_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                client_tableMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(client_table);

        credit_panel.add(jScrollPane9);
        jScrollPane9.setBounds(30, 150, 500, 240);

        jLabel24.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(30, 130, 82));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/ajouterbtn.png"))); // NOI18N
        credit_panel.add(jLabel24);
        jLabel24.setBounds(190, 80, 220, 50);

        jLabel25.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(30, 130, 82));
        jLabel25.setText("client :");
        credit_panel.add(jLabel25);
        jLabel25.setBounds(10, 20, 90, 40);

        clientNumero.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        clientNumero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        clientNumero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));
        credit_panel.add(clientNumero);
        clientNumero.setBounds(330, 20, 180, 40);

        clientNom.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        clientNom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        clientNom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51)));
        credit_panel.add(clientNom);
        clientNom.setBounds(70, 20, 170, 40);

        ajout_client_button.setText("jButton3");
        ajout_client_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajout_client_buttonActionPerformed(evt);
            }
        });
        credit_panel.add(ajout_client_button);
        ajout_client_button.setBounds(200, 80, 180, 50);

        jLabel26.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(30, 130, 82));
        jLabel26.setText("Numero :");
        credit_panel.add(jLabel26);
        jLabel26.setBounds(250, 20, 80, 40);

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/confirmerbtn.png"))); // NOI18N
        credit_panel.add(jLabel29);
        jLabel29.setBounds(190, 410, 150, 70);

        jButton3.setText("jButton1");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        credit_panel.add(jButton3);
        jButton3.setBounds(200, 430, 130, 30);

        general_panel.add(credit_panel);
        credit_panel.setBounds(720, 80, 540, 490);

        getContentPane().add(general_panel);
        general_panel.setBounds(0, 0, 1290, 950);

        Background.setBackground(new java.awt.Color(255, 250, 240));
        Background.setForeground(new java.awt.Color(255, 250, 240));
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1290, 2000);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passerLeProduit() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        df.setMinimumFractionDigits(3);

        try {
            txTest1.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 0)));
            prix_base.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 4)));
            Nom.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 0)));
            Prix_Vente.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 3)));
            String Barcode = String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 1));
            String Categorie = String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 2));
            //Disable selection of another product through Table2
            jTable2.show();
            produits.show();
            //....

            if (secondafficheur.getText().equals("")) {
                totale.setText(afficheur.getText());
                System.out.println("total =" + totale.getText());
                total_base.setText(prix_base.getText());
                Quantite.setText("1");
            } else {
                int f = Integer.parseInt(afficheur.getText());
                System.out.println("prix =" + prix_base.getText());
                Quantite.setText(Integer.toString(f));

                arithmetic_operation();

            }

            afficheur.setText("");
            secondafficheur.setText("");

            String caisse[] = {"Nom", "Barcode", "Categorie", "quantite", "Prix_vente", "total_base", "total"};
            String[] inf = {Nom.getText(), Barcode, Categorie, Quantite.getText(), Prix_Vente.getText(), total_base.getText(), totale.getText()};
            System.out.println(db.queryInsert("caisse", caisse, inf));

            Double c = 0.000;

            for (int k = 0; k < jTable1.getRowCount(); k++) {
                String b = String.valueOf(jTable1.getValueAt(k, 5)).replace(',', '.');
                Double d = Double.parseDouble(b);

                c = c + d;

            }

            System.err.println("c = " + c);

            String g = df.format(c).replace(",", ".");
            System.err.println("g = " + g);
            totale.setText(g);
            actualiser();

            Double r = 0.000;
            for (int k = 0; k < jTable1.getRowCount(); k++) {
                String b = String.valueOf(jTable1.getValueAt(k, 7)).replace(',', '.');
                Double d = Double.parseDouble(b);
                r = r + d;

            }
            System.err.println("r = " + r);
            String q = df.format(r).replace(",", ".");

            System.err.println("q = " + q);

            totale.setText(q);
            recherche_Barcode.requestFocus();

        } catch (Exception e) {
            System.err.println(e.toString());
        }

    }

    private void EnterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnterBtnActionPerformed
// TODO add your handling code here:
        passerLeProduit();
    }//GEN-LAST:event_EnterBtnActionPerformed

    private void NumPad8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumPad8ActionPerformed
        // TODO add your handling code here:
        afficheur.setText(afficheur.getText() + "8");
        prix_base.setText(prix_base.getText() + "8");
        general_panel.requestFocus();
    }//GEN-LAST:event_NumPad8ActionPerformed

    private void NumPad0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumPad0ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        afficheur.setText(afficheur.getText() + "0");
        prix_base.setText(prix_base.getText() + "0");
        general_panel.requestFocus();
    }//GEN-LAST:event_NumPad0ActionPerformed

    private void NumPad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumPad1ActionPerformed
        // TODO add your handling code here:
        afficheur.setText(afficheur.getText() + "1");
        prix_base.setText(prix_base.getText() + "1");
        general_panel.requestFocus();

    }//GEN-LAST:event_NumPad1ActionPerformed

    private void NumPad2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumPad2ActionPerformed
        // TODO add your handling code here:
        afficheur.setText(afficheur.getText() + "2");
        prix_base.setText(prix_base.getText() + "2");
        general_panel.requestFocus();
    }//GEN-LAST:event_NumPad2ActionPerformed

    private void NumPad3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumPad3ActionPerformed
        // TODO add your handling code here:
        afficheur.setText(afficheur.getText() + "3");
        prix_base.setText(prix_base.getText() + "3");
        general_panel.requestFocus();
    }//GEN-LAST:event_NumPad3ActionPerformed

    private void NumPad4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumPad4ActionPerformed
        // TODO add your handling code here:
        afficheur.setText(afficheur.getText() + "4");
        prix_base.setText(prix_base.getText() + "4");
        general_panel.requestFocus();
    }//GEN-LAST:event_NumPad4ActionPerformed

    private void NumPad5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumPad5ActionPerformed
        // TODO add your handling code here:
        afficheur.setText(afficheur.getText() + "5");
        prix_base.setText(prix_base.getText() + "5");
        general_panel.requestFocus();
    }//GEN-LAST:event_NumPad5ActionPerformed

    private void NumPad6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumPad6ActionPerformed
        // TODO add your handling code here:
        afficheur.setText(afficheur.getText() + "6");
        prix_base.setText(prix_base.getText() + "6");
        general_panel.requestFocus();
    }//GEN-LAST:event_NumPad6ActionPerformed

    private void NumPad7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumPad7ActionPerformed
        // TODO add your handling code here:
        afficheur.setText(afficheur.getText() + "7");
        prix_base.setText(prix_base.getText() + "7");
        general_panel.requestFocus();
    }//GEN-LAST:event_NumPad7ActionPerformed

    private void NumPad9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumPad9ActionPerformed
        // TODO add your handling code here:
        afficheur.setText(afficheur.getText() + "9");
        prix_base.setText(prix_base.getText() + "9");
        general_panel.requestFocus();
    }//GEN-LAST:event_NumPad9ActionPerformed

    private void NumPadXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumPadXActionPerformed

// TODO add your handling code here:
        try {
            num = Double.parseDouble(afficheur.getText());
            pum = Double.parseDouble(prix_base.getText());
            calculation = 3;
            secondafficheur.setText(afficheur.getText() + "X");
            afficheur.setText("");
            prix_base.setText("");

            jTable2.hide();

            produits.hide();
        } catch (Exception e) {
        }

        general_panel.requestFocus();

    }//GEN-LAST:event_NumPadXActionPerformed

    private void NumPadCEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumPadCEActionPerformed
        // TODO add your handling code here:
        if (!secondafficheur.getText().isEmpty()) {
            int lenght = afficheur.getText().length();
            int number = afficheur.getText().length() - 1;
            String store;
            int lenght1 = prix_base.getText().length();
            int number1 = prix_base.getText().length() - 1;
            String store1;

            if (lenght > 0) {
                StringBuilder back = new StringBuilder(afficheur.getText());
                StringBuilder back2 = new StringBuilder(prix_base.getText());
                back.deleteCharAt(number);
                back2.deleteCharAt(number1);
                store = back.toString();
                store1 = back2.toString();
                afficheur.setText(store);
                prix_base.setText(store1);

            }
        }
        general_panel.requestFocus();
    }//GEN-LAST:event_NumPadCEActionPerformed

    private void NumPadCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumPadCActionPerformed
        // TODO add your handling code here:

        afficheur.setText("");
        secondafficheur.setText("");
        jTable2.show();
        produits.show();
        recherche_Barcode.setText("");
        recherche_Barcode.requestFocus();

    }//GEN-LAST:event_NumPadCActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        try {
            txTest1.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 0)));
            afficheur.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 3)));
            prix_base.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 4)));
            Nom.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 0)));
            Prix_Vente.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 3)));
            recherche_Barcode.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 1)));
            recherche_Barcode.requestFocus();
        } catch (Exception e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(this, "opétation echoué ");
            actualiser();

        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void recherche_BarcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherche_BarcodeKeyTyped

    }//GEN-LAST:event_recherche_BarcodeKeyTyped

    private void confirmerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmerBtnActionPerformed
        // TODO add your handling code here:
        try {

            int R = 0;
            String emptyStockNames = "";

            int j = jTable1.getRowCount();
            System.out.println("nombre de produits au tableau = " + j);

            String nom;
            String barcode;
            String categorie;
            String Quantity;
            String Quantity_stock;
            String total;
            String total_base;
            String a[] = {"id", "nom", "Prix_vente", "Quantite"};

            boolean stockIsEmpty = false;
            try {
                for (int i = 0; i < j; i++) {

                    nom = (String.valueOf(jTable1.getValueAt(i, 1)));
                    barcode = (String.valueOf(jTable1.getValueAt(i, 2)));
                    Quantity = (String.valueOf(jTable1.getValueAt(i, 4)));
                    System.out.println(nom + " " + Quantity + " " + barcode);
                    rs = db.fcSelectCommand(a, "produit_stock", "Barcode = '" + barcode + "' ");
                    jTable7.setModel(new ResultSetTableModel(rs));
                    Quantity_stock = (String.valueOf(jTable7.getValueAt(0, 3)));

                    System.out.println("quantité " + Quantity);
                    System.out.println("quantité stock " + Quantity_stock);

                    R = Integer.parseInt(Quantity_stock) - Integer.parseInt(Quantity);

                    System.out.println("R = " + R);

                    if (R < 0) {
                        stockIsEmpty = true;
                        emptyStockNames = emptyStockNames + nom + " le stock est vide " + System.lineSeparator();
                    }
                }

                if (stockIsEmpty) {
                    JOptionPane.showMessageDialog(this, emptyStockNames);
                } else {
                    j = jTable1.getRowCount();

                    System.out.println(" nombre d'article dans la commande = " + j);
                    // impression de entete
                    // numero de ticket
                    Numero.setText(String.valueOf(jTable13.getValueAt(0, 1)));
                    int nbr = Integer.parseInt(Numero.getText());
                    Numero.setText(String.valueOf(nbr + 1));

                    //
                    Ticket1 ticket1 = new Ticket1("MAGASIN_YASSINE", "RUE_FARHAT_HACHED_BOUHAJLA_KAIROUAN", Numero.getText(), totale.getText(), Date1.getText(), Heure1.getText());
                    ticket1.print();
                    ////////

                    for (int i = 0; i < j; i++) {

                        nom = (String.valueOf(jTable1.getValueAt(i, 1)));
                        barcode = String.valueOf(jTable1.getValueAt(i, 2));
                        categorie = (String.valueOf(jTable1.getValueAt(i, 3)));
                        Quantity = (String.valueOf(jTable1.getValueAt(i, 4)));
                        String Prix = (String.valueOf(jTable1.getValueAt(i, 5)));
                        total = (String.valueOf(jTable1.getValueAt(i, 7)));
                        total_base = (String.valueOf(jTable1.getValueAt(i, 6)));

                        rs = db.fcSelectCommand(a, "produit_stock", "Barcode = '" + barcode + "' ");
                        jTable7.setModel(new ResultSetTableModel(rs));
                        Quantity_stock = (String.valueOf(jTable7.getValueAt(0, 3)));

                        System.out.println("quantite saisie=" + Quantity);

                        System.out.println("quantite stock=" + Quantity_stock);

                        R = Integer.parseInt(Quantity_stock) - Integer.parseInt(Quantity);

                        String produit_stock[] = {"Quantite"};
                        String inf[] = {String.valueOf(R)};
                        db.queryUpdate("produit_stock", produit_stock, inf, "Barcode='" + barcode + "'");

                        String[] historique = {"Nom", "Barcode", "Categorie", "Quantite", "Prix_Vente", "total_base", "total", "Date_Sortie", "Heure_Sortie", "user"};
                        String[] inf2 = {nom, barcode, categorie, Quantity, Prix, total_base, total, Date.getText(), Heure1.getText(), utilisateur.getText()};
                        db.queryInsert("historique", historique, inf2);
                        db.queryDelete("caisse", "Barcode ='" + barcode + "'");

                        ///////////////////////////////////////////  
                        try {
                            Ticket ticket = new Ticket(nom, Prix, Quantity);
                            ticket.print();
                            //update numero de ticket
                            String numero_bon[] = {"numero"};
                            String inf4[] = {Numero.getText()};

                            System.err.println(db.queryUpdate("nbon", numero_bon, inf4, "id='" + 0 + "'"));
                            ///////

                        } catch (Exception e) {
                            System.err.println("ouups");
                        }

                        //////////////////
                    }
                    Ticket2 ticket2 = new Ticket2();
                    ticket2.print();
                    //JOptionPane.showMessageDialog(this, "Le client est sorti avec succès ! \n Merci d imprimer le ticket de sortie.");
                    totale.setText("0.000");
                    table();
                    table2();

                    /////////////////////////////////
                }
            } catch (Exception e) {
                System.err.println("problem is " + e.toString());
            }
        } catch (Exception e) {
            System.err.println("problem is " + e.toString());
            JOptionPane.showMessageDialog(this, "vous avez entrer un produit null");
            db.queryDelete("caisse");
        }
        recherche_Barcode.requestFocus();
    }//GEN-LAST:event_confirmerBtnActionPerformed

    private void totaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totaleMouseClicked
        // TODO add your handling code here:
/*
        float c = 0;
        for (int k = 0; k < jTable1.getRowCount(); k++) {
            String b = String.valueOf(jTable1.getValueAt(k, 3)).replace(',', '.');
            float d = Float.parseFloat(b);
            c = c + d;

        }

        totale.setText(Float.toString(c));
         */
    }//GEN-LAST:event_totaleMouseClicked

    private void BarsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BarsearchKeyReleased
        // TODO add your handling code here:

        //passer les produit par entrer
        if ((evt.getKeyCode() == KeyEvent.VK_MULTIPLY)) {
            if (Barsearch.getText().equals("*")) {

                JOptionPane.showMessageDialog(this, "vous devez entrer un produit !!!!");
                Barsearch.setText("");

            } else if ((Nom.getText().equals("null")) || (Prix_Vente.getText().equals("null")) || (prix_base.getText().equals("null"))) {
                JOptionPane.showMessageDialog(this, "entrer un code a barre valide");
                recherche_Barcode.setText("");

            } else {

                Barsearch.setText("");
                try {
                    num = Double.parseDouble(afficheur.getText());
                    pum = Double.parseDouble(prix_base.getText());
                    calculation = 3;
                    secondafficheur.setText(afficheur.getText() + "X");
                    afficheur.setText("");
                    prix_base.setText("");

                    jTable2.hide();

                    produits.hide();

                } catch (Exception e) {
                    System.err.println("multiplicity button problrm" + e.toString());
                }

                general_panel.requestFocus();
                recherche_Barcode.setFocusable(true);
            }
        } //////////// confirmer code ///////////
        else if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            if (Barsearch.getText().equals("")) {

                JOptionPane.showMessageDialog(this, "vous devez entrer un produit !!!!");
            } else if ((Nom.getText().equals("null")) || (Prix_Vente.getText().equals("null")) || (prix_base.getText().equals("null"))) {
                JOptionPane.showMessageDialog(this, "entrer un code a barre valide");
                recherche_Barcode.setText("");
            } else {
                passerLeProduit();

            }
        } else {

            //////////////
            try {

                String q = "Select nom, Barcode, Categorie, Prix_vente, Prix_base  from produit where nom LIKE '%" + Barsearch.getText() + "%'"
                        + "OR Categorie LIKE '%" + Barsearch.getText() + "%'"
                        + "OR Prix_vente LIKE '%" + Barsearch.getText() + "%'"
                        + "OR Prix_base LIKE '%" + Barsearch.getText() + "%'";
                rs = db.exécutionQuery(q);
//rs =db.querySelectTwo("produit", "nom LIKE '%" + Barsearch.getText() + "%'", "nom LIKE '%" + Barsearch.getText() + "%'");
                jTable2.setModel(new ResultSetTableModel(rs));
                jTable2.getColumnModel().getColumn(1).setMinWidth(0);
                jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
                jTable2.getColumnModel().getColumn(2).setMinWidth(0);
                jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
                jTable2.getColumnModel().getColumn(4).setMinWidth(0);
                jTable2.getColumnModel().getColumn(4).setMaxWidth(0);
                //selection d e1 ere produit de tableau et affichage de poid sur le caissse
                jTable2.setRowSelectionInterval(0, 0);
                afficheur.setText((String.valueOf(jTable2.getValueAt(0, 3))));
                Nom.setText(String.valueOf(jTable2.getValueAt(0, 0)));
                Prix_Vente.setText(String.valueOf(jTable2.getValueAt(0, 3)));
                prix_base.setText((String.valueOf(jTable2.getValueAt(0, 4))));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "vous devez entrer un produit existant !!!");
                Barsearch.setText("");
                Barsearch.requestFocus();
                afficheur.setText("0");
            }
        }

    }//GEN-LAST:event_BarsearchKeyReleased

    private void supprimerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerBtnActionPerformed
        String id = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        if (txTest2.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez selectionner une commande dans le tableau", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (!txTest2.getText().equals(id)) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez selectionner une commande dans le tableau", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {

            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(3);
            df.setMinimumFractionDigits(3);

            String b = (jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 7)).toString();
            //System.out.println(b);
            Float d = Float.parseFloat(b);
            //System.out.println(d);
            Float t = Float.parseFloat(totale.getText());
            //System.out.println(t);
            t = t - d;
            //System.out.println(t);
            String s = df.format(t).replace(",", ".");
            //System.out.println(s);

            db.queryDelete("caisse", "id=" + id);
            actualiser();
            totale.setText(df.format(t).replace(",", "."));
            //JOptionPane.showMessageDialog(this, "commande supprmée avec succés");
        }

        recherche_Barcode.requestFocus();
    }//GEN-LAST:event_supprimerBtnActionPerformed

    private void jLabel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel3KeyPressed
        // TODO add your handling code here:

        float c = 0;
        for (int k = 0; k < jTable1.getRowCount(); k++) {
            String b = String.valueOf(jTable1.getValueAt(k, 4)).replace(',', '.');
            float d = Float.parseFloat(b);
            c = c + d;

        }

        totale.setText(Float.toString(c));
    }//GEN-LAST:event_jLabel3KeyPressed

    private void afficheurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_afficheurKeyPressed
        // TODO add your handling code here:
        String a[] = {"nom", "Prix_vente", "Prix_base"};
        rs = db.fcSelectCommand(a, "produit", "Prix_vente LIKE '%" + recherche_Barcode.getText() + "%' ");

        jTable2.setModel(new ResultSetTableModel(rs));
        jTable2.getColumnModel().getColumn(2).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
    }//GEN-LAST:event_afficheurKeyPressed

    private void afficheurKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_afficheurKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if (!(Character.isDigit(car)) || (car == KeyEvent.VK_BACK_SPACE)) {
            if (car != '.') {
                getToolkit().beep();
                evt.consume();
            }
        }
    }//GEN-LAST:event_afficheurKeyTyped

    private void annulerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerBtnActionPerformed
        // TODO add your handling code here:
        db.queryDelete("caisse");
        table();
        actualiser();
        totale.setText("0.000");
        recherche_Barcode.requestFocus();
        CommandePanel.show();
    }//GEN-LAST:event_annulerBtnActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        txTest2.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
        txnomcaise = (String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1)));
        txquantiteaise = (String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)));
        prixVenteaise = (String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)));
        totalbaseaise = (String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)));
        totalaise = (String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)));
    }//GEN-LAST:event_jTable1MouseClicked

    private void afficheurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afficheurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_afficheurActionPerformed

    private void LogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBtnActionPerformed
        // TODO add your handling code here:
        try {

            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(3);
            df.setMinimumFractionDigits(3);

            DecimalFormat intf = new DecimalFormat("00");

            Date s = new Date();
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-YYYY");
            SimpleDateFormat h = new SimpleDateFormat("HH:mm");
            String date = d.format(s);
            String heure = h.format(s);
            System.err.println(date);
            System.err.println(heure);

            String reqbrut = "";
            String reqnet = "";
            int i = Integer.parseInt(heure.substring(0, 2));
            boolean b = i >= 5;
            if (b) {

                int joursuiv = Integer.parseInt(date.substring(0, 2));
                joursuiv = joursuiv + 1;
                String jourSuiv = intf.format(joursuiv);
                String dateSuiv = jourSuiv + date.substring(2);

                reqbrut = "SELECT SUM( total ) "
                        + "FROM `historique` "
                        + "WHERE user ='" + utilisateur.getText() + "' "
                        + "AND ((Date_Sortie = '" + date + "' AND Heure_Sortie >= '05:00') "
                        + "OR (Date_Sortie = '" + dateSuiv + "' AND Heure_Sortie <= '03:00'))";

                reqnet = "SELECT SUM( total ) - SUM(total_base) "
                        + "FROM `historique` "
                        + "WHERE user ='" + utilisateur.getText() + "' "
                        + "AND ((Date_Sortie = '" + date + "' AND Heure_Sortie >= '05:00') "
                        + "OR (Date_Sortie = '" + dateSuiv + "' AND Heure_Sortie <= '03:00'))";

            } else {
                int jourprec = Integer.parseInt(date.substring(0, 2));
                jourprec = jourprec - 1;
                String jourPrec = intf.format(jourprec);
                String datePrec = jourPrec + date.substring(2);

                reqbrut = "SELECT SUM( total ) "
                        + "FROM `historique` "
                        + "WHERE user ='" + utilisateur.getText() + "' "
                        + "AND ((Date_Sortie = '" + datePrec + "' AND Heure_Sortie >= '05:00') "
                        + "OR (Date_Sortie = '" + date + "' AND Heure_Sortie <= '03:00'))";

                reqnet = "SELECT SUM( total ) - SUM(total_base) "
                        + "FROM `historique` "
                        + "WHERE user ='" + utilisateur.getText() + "' "
                        + "AND ((Date_Sortie = '" + datePrec + "' AND Heure_Sortie >= '05:00') "
                        + "OR (Date_Sortie = '" + date + "' AND Heure_Sortie <= '03:00'))";

            }

            rs = db.exécutionQuery(reqbrut);
            String brut = "vide";
            String net = "vide";
            try {
                rs.next();
                System.out.println(rs.getString(1));
                brut = df.format(Double.parseDouble(rs.getString(1)));

            } catch (Exception e) {
                brut = "000.000";
            }

            rs = db.exécutionQuery(reqnet);

            try {
                rs.next();
                System.out.println(rs.getString(1));
                net = df.format(Double.parseDouble(rs.getString(1)));

            } catch (Exception e) {
                net = "000.000";
            }

            BrutLabel.setText(brut + "  DT");
            NetLabel.setText(net + "  DT");
            CaissePanel.show();
            general_panel.hide();

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }//GEN-LAST:event_LogoutBtnActionPerformed

    private void tabacBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tabacBtnActionPerformed
        // TODO add your handling code here:

        //change photo of the bar
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
        String a[] = {"nom", "Barcode", "Categorie", "Prix_vente", "Prix_base"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "tabac" + "%' ");

        jTable2.setModel(new ResultSetTableModel(rs));
        jTable8.setModel(new ResultSetTableModel(rs));
        jTable2.getColumnModel().getColumn(0).setMinWidth(150);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(150);
        jTable2.getColumnModel().getColumn(1).setMinWidth(0);
        jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(2).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(4).setMinWidth(0);
        jTable2.getColumnModel().getColumn(4).setMaxWidth(0);

        ArrayList l = new ArrayList();

        for (int i = 0; i <= jTable8.getRowCount(); i++) {
            l.add(String.valueOf(jTable8.getValueAt(i, 0)));

        }
        System.out.println(l.size());
        panel_product(l);
    }//GEN-LAST:event_tabacBtnActionPerformed

    private void AccBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccBtnActionPerformed
        // TODO add your handling code here:

        //change photo of the bar
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
        String a[] = {"nom", "Barcode", "Categorie", "Prix_vente", "Prix_base"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "accessoire" + "%' ");

        jTable2.setModel(new ResultSetTableModel(rs));
        jTable8.setModel(new ResultSetTableModel(rs));
        jTable2.getColumnModel().getColumn(0).setMinWidth(150);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(150);
        jTable2.getColumnModel().getColumn(1).setMinWidth(0);
        jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(2).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(4).setMinWidth(0);
        jTable2.getColumnModel().getColumn(4).setMaxWidth(0);

        ArrayList l = new ArrayList();

        for (int i = 0; i <= jTable8.getRowCount(); i++) {
            l.add(String.valueOf(jTable8.getValueAt(i, 0)));

        }
        System.out.println(l.size());
        panel_product(l);
    }//GEN-LAST:event_AccBtnActionPerformed

    private void gouterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gouterBtnActionPerformed
        // TODO add your handling code here:

        //change photo of the bar
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
        String a[] = {"nom", "Barcode", "Categorie", "Prix_vente", "Prix_base"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "gouter" + "%' ");

        jTable2.setModel(new ResultSetTableModel(rs));
        jTable8.setModel(new ResultSetTableModel(rs));
        jTable2.getColumnModel().getColumn(0).setMinWidth(150);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(150);
        jTable2.getColumnModel().getColumn(1).setMinWidth(0);
        jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(2).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(4).setMinWidth(0);
        jTable2.getColumnModel().getColumn(4).setMaxWidth(0);

        ArrayList l = new ArrayList();

        for (int i = 0; i <= jTable8.getRowCount(); i++) {
            l.add(String.valueOf(jTable8.getValueAt(i, 0)));

        }
        System.out.println(l.size());
        panel_product(l);
    }//GEN-LAST:event_gouterBtnActionPerformed

    private void boissonBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boissonBtnActionPerformed
        // TODO add your handling code here:

        //change photo of the bar
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

        String a[] = {"nom", "Barcode", "Categorie", "Prix_vente", "Prix_base"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "boisson" + "%' ");

        jTable2.setModel(new ResultSetTableModel(rs));
        jTable8.setModel(new ResultSetTableModel(rs));
        jTable2.getColumnModel().getColumn(0).setMinWidth(150);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(150);
        jTable2.getColumnModel().getColumn(1).setMinWidth(0);
        jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(2).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(4).setMinWidth(0);
        jTable2.getColumnModel().getColumn(4).setMaxWidth(0);

        ArrayList l = new ArrayList();

        for (int i = 0; i <= jTable8.getRowCount(); i++) {
            l.add(String.valueOf(jTable8.getValueAt(i, 0)));

        }
        System.out.println(l.size());
        panel_product(l);
    }//GEN-LAST:event_boissonBtnActionPerformed

    private void eauBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eauBtnActionPerformed
        // TODO add your handling code here:
        //change photo of the bar
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
        String a[] = {"nom", "Barcode", "Categorie", "Prix_vente", "Prix_base"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "eau" + "%' ");

        jTable2.setModel(new ResultSetTableModel(rs));
        jTable8.setModel(new ResultSetTableModel(rs));
        jTable2.getColumnModel().getColumn(0).setMinWidth(150);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(150);
        jTable2.getColumnModel().getColumn(1).setMinWidth(0);
        jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(2).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(4).setMinWidth(0);
        jTable2.getColumnModel().getColumn(4).setMaxWidth(0);

        ArrayList l = new ArrayList();

        for (int i = 0; i <= jTable8.getRowCount(); i++) {
            l.add(String.valueOf(jTable8.getValueAt(i, 0)));

        }
        System.out.println(l.size());
        panel_product(l);

    }//GEN-LAST:event_eauBtnActionPerformed

    private void cigaretteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cigaretteBtnActionPerformed
        // TODO add your handling code here:

        //change photo of the bar
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
        String a[] = {"nom", "Barcode", "Categorie", "Prix_vente", "Prix_base"};
        rs = db.fcSelectCommand(a, "produit", "nom LIKE '%" + "cigarette" + "%' ");

        jTable2.setModel(new ResultSetTableModel(rs));
        jTable8.setModel(new ResultSetTableModel(rs));
        jTable2.getColumnModel().getColumn(0).setMinWidth(150);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(150);
        jTable2.getColumnModel().getColumn(1).setMinWidth(0);
        jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(2).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(4).setMinWidth(0);
        jTable2.getColumnModel().getColumn(4).setMaxWidth(0);

        ArrayList l = new ArrayList();

        for (int i = 0; i <= jTable8.getRowCount(); i++) {
            l.add(String.valueOf(jTable8.getValueAt(i, 0)));

        }
        System.out.println(l.size());
        panel_product(l);
    }//GEN-LAST:event_cigaretteBtnActionPerformed

    private void rechargeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechargeBtnActionPerformed
        // TODO add your handling code here:

        //change photo of the bar
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
        String a[] = {"nom", "Barcode", "Categorie", "Prix_vente", "Prix_base"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "recharge téléphonique" + "%' ");

        jTable2.setModel(new ResultSetTableModel(rs));
        jTable8.setModel(new ResultSetTableModel(rs));
        jTable2.getColumnModel().getColumn(0).setMinWidth(150);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(150);
        jTable2.getColumnModel().getColumn(1).setMinWidth(0);
        jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(2).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(4).setMinWidth(0);
        jTable2.getColumnModel().getColumn(4).setMaxWidth(0);

        ArrayList l = new ArrayList();

        for (int i = 0; i <= jTable8.getRowCount(); i++) {
            l.add(String.valueOf(jTable8.getValueAt(i, 0)));

        }
        System.out.println(l.size());
        panel_product(l);
    }//GEN-LAST:event_rechargeBtnActionPerformed

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
        db.closeconnexion();
        stock a = new stock(utilisateur.getText());
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_stockbtnActionPerformed

    private void registerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerbtnActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_registerbtnActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        this.hide();
        System.out.println(evt.getKeyChar());
    }//GEN-LAST:event_formKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        // TODO add your handling code here:
        System.out.println("Key is typed");
    }//GEN-LAST:event_formKeyTyped

    private void general_panelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_general_panelKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_general_panelKeyTyped

    private void CancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBtnActionPerformed
        // TODO add your handling code here:

        CaissePanel.hide();
        general_panel.show();
        recherche_Barcode.requestFocus();

    }//GEN-LAST:event_CancelBtnActionPerformed

    private void EndSessionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EndSessionBtnActionPerformed
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
    }//GEN-LAST:event_EndSessionBtnActionPerformed

    private void recherche_BarcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherche_BarcodeKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_recherche_BarcodeKeyPressed

    private void recherche_BarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recherche_BarcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recherche_BarcodeActionPerformed

    private void recherche_BarcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recherche_BarcodeKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_MULTIPLY) {
            if (recherche_Barcode.getText().equals("*")) {
                JOptionPane.showMessageDialog(this, "vous devez entrer un code a barre");
                recherche_Barcode.setText("");

            } else if ((Nom.getText().equals("null")) || (Prix_Vente.getText().equals("null")) || (prix_base.getText().equals("null"))) {
                JOptionPane.showMessageDialog(this, "entrer un code a barre valide");
                recherche_Barcode.setText("");

            } else {
                recherche_Barcode.setText("");
                recherche_Barcode.setFocusable(false);
                try {
                    num = Double.parseDouble(afficheur.getText());
                    pum = Double.parseDouble(prix_base.getText());
                    calculation = 3;
                    secondafficheur.setText(afficheur.getText() + "X");
                    afficheur.setText("");
                    prix_base.setText("");

                    jTable2.hide();

                    produits.hide();
                } catch (Exception e) {
                }

                general_panel.requestFocus();
                recherche_Barcode.setFocusable(true);
            }
        } //////////// confirmer code ///////////
        else if ((evt.getKeyCode() == KeyEvent.VK_SPACE)) {
            if (recherche_Barcode.getText().equals(" ")) {
                JOptionPane.showMessageDialog(this, "vous devez entrer un code a barre");
                recherche_Barcode.setText("");
            } else if ((Nom.getText().equals("null")) || (Prix_Vente.getText().equals("null")) || (prix_base.getText().equals("null"))) {
                JOptionPane.showMessageDialog(this, "entrer un code a barre valide");
                recherche_Barcode.setText("");
            } else {
                passerLeProduit();
            }
        } else {
            String a[] = {"nom", "Barcode", "Categorie", "Prix_vente", "Prix_base"};
            rs = db.fcSelectCommand(a, "produit", "Barcode = '" + recherche_Barcode.getText() + "'");

            jTable2.setModel(new ResultSetTableModel(rs));
            jTable2.getColumnModel().getColumn(1).setMinWidth(0);
            jTable2.getColumnModel().getColumn(1).setMaxWidth(0);

            jTable2.getColumnModel().getColumn(2).setMinWidth(0);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(0);

            jTable2.getColumnModel().getColumn(4).setMinWidth(0);
            jTable2.getColumnModel().getColumn(4).setMaxWidth(0);

            jTable2.selectAll();
            afficheur.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 3)));
            prix_base.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 4)));
            Nom.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 0)));
            Prix_Vente.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 3)));
            String Barcode = String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 1));
            String Categorie = String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 2));
            System.out.println(afficheur.getText());
        }
        if (afficheur.getText().equals("null")) {
            afficheur.setText("0");
        }
    }//GEN-LAST:event_recherche_BarcodeKeyReleased

    private void afficheurbg1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_afficheurbg1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_afficheurbg1KeyReleased

    private void confirmerBtnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmerBtnKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmerBtnKeyReleased

    private void PatesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatesBtnActionPerformed
        //change photo of the bar
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
        String a[] = {"nom", "Barcode", "Categorie", "Prix_vente", "Prix_base"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "pates" + "%' ");

        jTable2.setModel(new ResultSetTableModel(rs));
        jTable8.setModel(new ResultSetTableModel(rs));
        jTable2.getColumnModel().getColumn(0).setMinWidth(150);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(150);
        jTable2.getColumnModel().getColumn(1).setMinWidth(0);
        jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(2).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(4).setMinWidth(0);
        jTable2.getColumnModel().getColumn(4).setMaxWidth(0);

        ArrayList l = new ArrayList();

        for (int i = 0; i <= jTable8.getRowCount(); i++) {
            l.add(String.valueOf(jTable8.getValueAt(i, 0)));

        }
        System.out.println(l.size());
        panel_product(l);
    }//GEN-LAST:event_PatesBtnActionPerformed

    private void ConserveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConserveBtnActionPerformed
        //change photo of the bar
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
        String a[] = {"nom", "Barcode", "Categorie", "Prix_vente", "Prix_base"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "conserve" + "%' ");

        jTable2.setModel(new ResultSetTableModel(rs));
        jTable8.setModel(new ResultSetTableModel(rs));
        jTable2.getColumnModel().getColumn(0).setMinWidth(150);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(150);
        jTable2.getColumnModel().getColumn(1).setMinWidth(0);
        jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(2).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(4).setMinWidth(0);
        jTable2.getColumnModel().getColumn(4).setMaxWidth(0);

        ArrayList l = new ArrayList();

        for (int i = 0; i <= jTable8.getRowCount(); i++) {
            l.add(String.valueOf(jTable8.getValueAt(i, 0)));

        }
        System.out.println(l.size());
        panel_product(l);
    }//GEN-LAST:event_ConserveBtnActionPerformed

    private void FruitssecsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FruitssecsBtnActionPerformed
        //change photo of the bar
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
        String a[] = {"nom", "Barcode", "Categorie", "Prix_vente", "Prix_base"};
        rs = db.fcSelectCommand(a, "produit", "Categorie LIKE '%" + "fruits secs" + "%' ");

        jTable2.setModel(new ResultSetTableModel(rs));
        jTable8.setModel(new ResultSetTableModel(rs));
        jTable2.getColumnModel().getColumn(0).setMinWidth(150);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(150);
        jTable2.getColumnModel().getColumn(1).setMinWidth(0);
        jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(2).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(4).setMinWidth(0);
        jTable2.getColumnModel().getColumn(4).setMaxWidth(0);

        ArrayList l = new ArrayList();

        for (int i = 0; i <= jTable8.getRowCount(); i++) {
            l.add(String.valueOf(jTable8.getValueAt(i, 0)));

        }
        System.out.println(l.size());
        panel_product(l);
    }//GEN-LAST:event_FruitssecsBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ////// generate client table //////
        client();
        ////// show client credit panel//////
        CommandePanel.hide();
        credit_panel.show();
        
        

       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Credit a = new Credit(utilisateur.getText());
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ajout_client_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajout_client_buttonActionPerformed
        // TODO add your handling code here:
        String[] colon = {"nom", "numero"};
        String[] inf = {clientNom.getText(), clientNumero.getText()};
        System.out.println(db.queryInsert("client", colon, inf));
        client();
        JOptionPane.showMessageDialog(this, "Votre client  a été ajouté avec succès");
    }//GEN-LAST:event_ajout_client_buttonActionPerformed

    private void client_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_client_tableMouseClicked
        // TODO add your handling code here:
        /// envoyer client nom et numero vers credit 
        ClientCredit = String.valueOf(client_table.getValueAt(client_table.getSelectedRow(), 0));
        NumeroCredit = String.valueOf(client_table.getValueAt(client_table.getSelectedRow(), 1));
        
    }//GEN-LAST:event_client_tableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         /////////// payer ///////
        int R = 0;
        String emptyStockNames = "";

        int j = jTable1.getRowCount();
        System.out.println("nombre de produits au tableau = " + j);

        String nom;
        String barcode;
        String categorie;
        String Quantity;
        String Quantity_stock;
        String total;
        String total_base;
        String a[] = {"id", "nom", "Prix_vente", "Quantite"};

        boolean stockIsEmpty = false;
        try {
            for (int i = 0; i < j; i++) {

                nom = (String.valueOf(jTable1.getValueAt(i, 1)));
                barcode = (String.valueOf(jTable1.getValueAt(i, 2)));
                Quantity = (String.valueOf(jTable1.getValueAt(i, 4)));
                System.out.println(nom + " " + Quantity + " " + barcode);
                rs = db.fcSelectCommand(a, "produit_stock", "Barcode = '" + barcode + "' ");
                jTable7.setModel(new ResultSetTableModel(rs));

                Quantity_stock = (String.valueOf(jTable7.getValueAt(0, 3)));

                System.out.println("quantité " + Quantity);
                System.out.println("quantité stock " + Quantity_stock);

                R = Integer.parseInt(Quantity_stock) - Integer.parseInt(Quantity);

                System.out.println("R = " + R);

                if (R < 0) {
                    stockIsEmpty = true;
                    emptyStockNames = emptyStockNames + nom + " le stock est vide " + System.lineSeparator();
                }
            }

            if (stockIsEmpty) {
                JOptionPane.showMessageDialog(this, emptyStockNames);
            } else {
                j = jTable1.getRowCount();
                System.out.println(" nombre d'article dans la commande = " + j);
                for (int i = 0; i < j; i++) {

                    nom = (String.valueOf(jTable1.getValueAt(i, 1)));
                    barcode = String.valueOf(jTable1.getValueAt(i, 2));
                    categorie = (String.valueOf(jTable1.getValueAt(i, 3)));
                    Quantity = (String.valueOf(jTable1.getValueAt(i, 4)));
                    String Prix = (String.valueOf(jTable1.getValueAt(i, 5)));
                    total = (String.valueOf(jTable1.getValueAt(i, 7)));
                    total_base = (String.valueOf(jTable1.getValueAt(i, 6)));

                    rs = db.fcSelectCommand(a, "produit_stock", "Barcode = '" + barcode + "' ");
                    jTable7.setModel(new ResultSetTableModel(rs));

                    Quantity_stock = (String.valueOf(jTable7.getValueAt(0, 3)));

                    System.out.println("quantite saisie=" + Quantity);

                    System.out.println("quantite stock=" + Quantity_stock);

                    R = Integer.parseInt(Quantity_stock) - Integer.parseInt(Quantity);

                    String produit_stock[] = {"Quantite"};
                    String inf[] = {String.valueOf(R)};
                    db.queryUpdate("produit_stock", produit_stock, inf, "Barcode='" + barcode + "'");
                    String[] historique = {"Client", "Numero", "Nom", "Barcode", "Categorie", "Quantite", "Prix_Vente", "total_base", "total", "Date_Sortie", "Heure_Sortie", "user", "acompte"};
                    String[] inf2 = {ClientCredit, NumeroCredit, nom, barcode, categorie, Quantity, Prix, total_base, total, Date.getText(), Heure.getText(), utilisateur.getText(), "0"};
                    db.queryInsert("credit", historique, inf2);
                    db.queryDelete("caisse", "Barcode ='" + barcode + "'");

                }
                totale.setText("0.000");
                table();
                table2();
                recherche_Barcode.requestFocus();
                credit_panel.hide();
                CommandePanel.show();
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_NUMPAD0:
                    afficheur.setText(afficheur.getText() + "0");
                    prix_base.setText(prix_base.getText() + "0");
                    break;
                case KeyEvent.VK_NUMPAD1:
                    afficheur.setText(afficheur.getText() + "1");
                    prix_base.setText(prix_base.getText() + "1");
                    break;
                case KeyEvent.VK_NUMPAD2:
                    afficheur.setText(afficheur.getText() + "2");
                    prix_base.setText(prix_base.getText() + "2");
                    break;
                case KeyEvent.VK_NUMPAD3:
                    afficheur.setText(afficheur.getText() + "3");
                    prix_base.setText(prix_base.getText() + "3");
                    break;
                case KeyEvent.VK_NUMPAD4:
                    afficheur.setText(afficheur.getText() + "4");
                    prix_base.setText(prix_base.getText() + "4");
                    break;
                case KeyEvent.VK_NUMPAD5:
                    afficheur.setText(afficheur.getText() + "5");
                    prix_base.setText(prix_base.getText() + "5");
                    break;
                case KeyEvent.VK_NUMPAD6:
                    afficheur.setText(afficheur.getText() + "6");
                    prix_base.setText(prix_base.getText() + "6");
                    break;
                case KeyEvent.VK_NUMPAD7:
                    afficheur.setText(afficheur.getText() + "7");
                    prix_base.setText(prix_base.getText() + "7");
                    break;
                case KeyEvent.VK_NUMPAD8:
                    afficheur.setText(afficheur.getText() + "8");
                    prix_base.setText(prix_base.getText() + "8");
                    break;
                case KeyEvent.VK_NUMPAD9:
                    afficheur.setText(afficheur.getText() + "9");
                    prix_base.setText(prix_base.getText() + "9");
                    break;

//                case KeyEvent.VK_DECIMAL:
//                    afficheur.setText(afficheur.getText() + ".");
//                    prix_base.setText(prix_base.getText() + ".");
//                    break;
                case KeyEvent.VK_BACK_SPACE:
                    if (!secondafficheur.getText().isEmpty()) {
                        int lenght = afficheur.getText().length();
                        int number = afficheur.getText().length() - 1;
                        String store;
                        int lenght1 = prix_base.getText().length();
                        int number1 = prix_base.getText().length() - 1;
                        String store1;

                        if (lenght > 0) {
                            StringBuilder back = new StringBuilder(afficheur.getText());
                            StringBuilder back2 = new StringBuilder(prix_base.getText());
                            back.deleteCharAt(number);
                            back2.deleteCharAt(number1);
                            store = back.toString();
                            store1 = back2.toString();
                            afficheur.setText(store);
                            prix_base.setText(store1);

                        }
                    }
                    break;

                case KeyEvent.VK_ENTER:

                    passerLeProduit();

                    break;

                case KeyEvent.VK_DELETE:
                    afficheur.setText("");
                    secondafficheur.setText("");
                    jTable2.show();
                    produits.show();
                    recherche_Barcode.setText("");
                    recherche_Barcode.requestFocus();
                    break;
                default:
                // code block
            }

        }

    }

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
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new POS("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AccBtn;
    private javax.swing.JPanel Background;
    private javax.swing.JLabel Barcode;
    private javax.swing.JLabel Barcodelabel;
    private javax.swing.JTextField Barsearch;
    private javax.swing.JLabel BrutLabel;
    private javax.swing.JLabel BrutLabel1;
    private javax.swing.JPanel CaissePanel;
    private javax.swing.JButton CancelBtn;
    private javax.swing.JLabel CataccPanel;
    private javax.swing.JLabel CatboissonPanel;
    private javax.swing.JLabel CatcigarettePanel;
    private javax.swing.JLabel CatconservesPanel;
    private javax.swing.JLabel CateauPanel;
    private javax.swing.JLabel Categorie;
    private javax.swing.JLabel CatfruitssecsPanel;
    private javax.swing.JLabel CatgouterPanel;
    private javax.swing.JLabel CatpatesPanel;
    private javax.swing.JLabel CatrechargePanel;
    private javax.swing.JLabel CattabacPanel;
    private javax.swing.JPanel CommandePanel;
    private javax.swing.JButton ConserveBtn;
    private javax.swing.JLabel Date;
    private javax.swing.JLabel Date1;
    private javax.swing.JButton EndSessionBtn;
    private javax.swing.JButton EnterBtn;
    private javax.swing.JButton FruitssecsBtn;
    private javax.swing.JLabel Heure;
    private javax.swing.JLabel Heure1;
    private javax.swing.JLabel Logo;
    private javax.swing.JButton LogoutBtn;
    private javax.swing.JLabel LogoutBtnbg;
    private javax.swing.JLabel NetLabel;
    private javax.swing.JLabel NetLabel1;
    private javax.swing.JLabel Nom;
    private javax.swing.JButton NumPad0;
    private javax.swing.JButton NumPad1;
    private javax.swing.JButton NumPad2;
    private javax.swing.JButton NumPad3;
    private javax.swing.JButton NumPad4;
    private javax.swing.JButton NumPad5;
    private javax.swing.JButton NumPad6;
    private javax.swing.JButton NumPad7;
    private javax.swing.JButton NumPad8;
    private javax.swing.JButton NumPad9;
    private javax.swing.JButton NumPadC;
    private javax.swing.JButton NumPadCE;
    private javax.swing.JButton NumPadX;
    private javax.swing.JLabel Numero;
    private javax.swing.JButton PatesBtn;
    private javax.swing.JLabel Prix_Vente;
    private javax.swing.JLabel Prix_vente;
    private javax.swing.JLabel Quantite;
    private javax.swing.JPanel Session;
    private javax.swing.JTextField afficheur;
    private javax.swing.JLabel afficheurbg;
    private javax.swing.JLabel afficheurbg1;
    private javax.swing.JButton ajout_client_button;
    private javax.swing.JButton annulerBtn;
    private javax.swing.JLabel backcalcule;
    private javax.swing.JPanel barpanel;
    private javax.swing.JButton boissonBtn;
    private javax.swing.JLabel categorie2;
    private javax.swing.JPanel category_bar;
    private javax.swing.JButton cigaretteBtn;
    private javax.swing.JTextField clientNom;
    private javax.swing.JTextField clientNumero;
    private javax.swing.JTable client_table;
    private javax.swing.JButton confirmerBtn;
    private javax.swing.JPanel credit_panel;
    private javax.swing.JButton eauBtn;
    private javax.swing.JPanel general_panel;
    private javax.swing.JButton gouterBtn;
    private javax.swing.JButton inventorybtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JButton jButton74;
    private javax.swing.JButton jButton75;
    private javax.swing.JButton jButton76;
    private javax.swing.JButton jButton77;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private org.pushingpixels.flamingo.api.ribbon.JRibbonFrame jRibbonFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JLabel prix_base;
    private javax.swing.JPanel produits;
    private javax.swing.JButton rechargeBtn;
    private javax.swing.JTextField recherche_Barcode;
    private javax.swing.JButton registerbtn;
    private javax.swing.JButton salesbtn;
    private javax.swing.JLabel searchbarbg;
    private javax.swing.JTextField secondafficheur;
    private javax.swing.JButton statbtn;
    private javax.swing.JButton stockbtn;
    private javax.swing.JButton supprimerBtn;
    private javax.swing.JButton tabacBtn;
    private javax.swing.JLabel total_base;
    private javax.swing.JLabel totale;
    private javax.swing.JLabel txTest1;
    private javax.swing.JLabel txTest2;
    private javax.swing.JButton usersbtn;
    private javax.swing.JLabel utilisateur;
    // End of variables declaration//GEN-END:variables
}

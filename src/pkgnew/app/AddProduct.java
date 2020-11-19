/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnew.app;

import BDD.ResultSetTableModel;
import BDD.DbConnection;
import java.sql.ResultSet;
import BDD.Parametre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author asuss
 */
public class AddProduct extends javax.swing.JFrame {

    ResultSet rs;
    DbConnection db;

    public static AddProduct obj = null;

    /**
     * Creates new form Product
     */
    public AddProduct(String username1) {
        initComponents();
        this.setDefaultCloseOperation(0);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        table();
        utilisateur.setText(username1);
        date();
        datecourante();

        //********add a listener to the CategoryComboBox*******************************
        //PaquetCheckBox.setVisible(false);
        CigaretteCheckBox.setVisible(false);

        CategoryComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                System.out.println(".itemStateChanged() " + CategoryComboBox.getSelectedItem());
                if (CategoryComboBox.getSelectedItem().equals("tabac")) {
                    //PaquetCheckBox.setVisible(true);
                    CigaretteCheckBox.setVisible(true);
                } else {
                    //PaquetCheckBox.setVisible(false);
                    CigaretteCheckBox.setVisible(false);
                }
            }
        });

        CigarettePanel.setVisible(false);

        CigaretteCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(".ActionPerformed ");

                if (CigaretteCheckBox.isSelected()) {

                    CigarettePanel.setVisible(true);

                } else {

                    CigarettePanel.setVisible(false);
                }

            }
        });

        //********add a listener to the CategoryComboBox*******************************
    }

    public void actualiser() {
        txBarcode.setText("");
        CategoryComboBox.setSelectedItem("");
        txName.setText("");
        txVente_Prix.setText("");
        txBase_Prix.setText("");
        txVente_Prix1.setText("");
        txBase_Prix1.setText("");
        txMax.setText("");
        txMin.setText("");
        txTest.setText("");
        txTest1.setText("");
        table();

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

    public static AddProduct getObj(String username1) {
        if (obj == null) {

            obj = new AddProduct(username1);
        }
        return obj;
    }

    public void table() {
        String colon[] = {"Id", "nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};
        rs = db.querySelectOrdre(colon, "produit", "nom");
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        db.closeconnexion();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txTest = new javax.swing.JLabel();
        txTest1 = new javax.swing.JLabel();
        utilisateur = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Heure = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        modifierbg = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        annulerbg = new javax.swing.JLabel();
        annulerbtn = new javax.swing.JButton();
        txMin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txMax = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txBase_Prix = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txVente_Prix = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        CategoryComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txBarcode = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        Heure1 = new javax.swing.JLabel();
        Date1 = new javax.swing.JLabel();
        CigaretteCheckBox = new javax.swing.JCheckBox();
        CigarettePanel = new javax.swing.JPanel();
        txVente_Prix1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txBase_Prix1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txMax1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txMin1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

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

        txTest.setText("jLabel6");

        txTest1.setText("jLabel6");

        utilisateur.setText("jLabel7");

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
        jScrollPane1.setViewportView(jTable1);

        Heure.setText("jLabel4");

        Date.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(650, 700));
        setSize(new java.awt.Dimension(650, 700));

        jPanel1.setBackground(new java.awt.Color(255, 250, 240));
        jPanel1.setMaximumSize(new java.awt.Dimension(689, 455));
        jPanel1.setMinimumSize(new java.awt.Dimension(689, 455));
        jPanel1.setLayout(null);

        modifierbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/confirmerbtn.png"))); // NOI18N
        modifierbg.setText("jLabel11");
        jPanel1.add(modifierbg);
        modifierbg.setBounds(500, 110, 200, 52);

        jButton1.setBackground(new java.awt.Color(0, 204, 0));
        jButton1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("confirmer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(510, 110, 180, 50);

        annulerbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/annulerbtn.png"))); // NOI18N
        annulerbg.setText("jLabel11");
        jPanel1.add(annulerbg);
        annulerbg.setBounds(740, 110, 200, 52);

        annulerbtn.setBackground(new java.awt.Color(255, 51, 51));
        annulerbtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        annulerbtn.setForeground(new java.awt.Color(255, 255, 255));
        annulerbtn.setText("annuler");
        annulerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerbtnActionPerformed(evt);
            }
        });
        jPanel1.add(annulerbtn);
        annulerbtn.setBounds(750, 110, 180, 50);

        txMin.setBackground(new java.awt.Color(255, 250, 240));
        txMin.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        txMin.setForeground(new java.awt.Color(5, 45, 26));
        txMin.setText("0");
        txMin.setBorder(null);
        txMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txMinActionPerformed(evt);
            }
        });
        txMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txMinKeyTyped(evt);
            }
        });
        jPanel1.add(txMin);
        txMin.setBounds(950, 510, 260, 30);

        jLabel4.setBackground(new java.awt.Color(255, 250, 240));
        jLabel4.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(30, 130, 82));
        jLabel4.setText("Min :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(760, 510, 150, 30);

        txMax.setBackground(new java.awt.Color(255, 250, 240));
        txMax.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        txMax.setForeground(new java.awt.Color(5, 45, 26));
        txMax.setText("100");
        txMax.setBorder(null);
        txMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txMaxKeyTyped(evt);
            }
        });
        jPanel1.add(txMax);
        txMax.setBounds(420, 500, 260, 30);

        jLabel3.setBackground(new java.awt.Color(255, 250, 240));
        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(30, 130, 82));
        jLabel3.setText("Max :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(260, 500, 70, 30);

        jLabel8.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(30, 130, 82));
        jLabel8.setText("DT");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(1170, 420, 40, 30);

        txBase_Prix.setBackground(new java.awt.Color(255, 250, 240));
        txBase_Prix.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        txBase_Prix.setForeground(new java.awt.Color(5, 45, 26));
        txBase_Prix.setBorder(null);
        txBase_Prix.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txBase_PrixKeyTyped(evt);
            }
        });
        jPanel1.add(txBase_Prix);
        txBase_Prix.setBounds(950, 420, 210, 30);

        jLabel5.setBackground(new java.awt.Color(255, 250, 240));
        jLabel5.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(30, 130, 82));
        jLabel5.setText("Prix de base :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(760, 420, 220, 30);

        jLabel9.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(30, 130, 82));
        jLabel9.setText("DT");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(640, 420, 50, 30);

        txVente_Prix.setBackground(new java.awt.Color(255, 250, 240));
        txVente_Prix.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        txVente_Prix.setForeground(new java.awt.Color(5, 45, 26));
        txVente_Prix.setBorder(null);
        txVente_Prix.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txVente_PrixKeyTyped(evt);
            }
        });
        jPanel1.add(txVente_Prix);
        txVente_Prix.setBounds(420, 420, 210, 30);

        jLabel2.setBackground(new java.awt.Color(255, 250, 240));
        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(30, 130, 82));
        jLabel2.setText("Prix vente :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(260, 420, 130, 30);

        txName.setBackground(new java.awt.Color(255, 250, 240));
        txName.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        txName.setForeground(new java.awt.Color(5, 45, 26));
        txName.setBorder(null);
        jPanel1.add(txName);
        txName.setBounds(420, 340, 260, 30);

        jLabel6.setBackground(new java.awt.Color(255, 250, 240));
        jLabel6.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(30, 130, 82));
        jLabel6.setText("Nom :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(260, 340, 120, 30);

        CategoryComboBox.setBackground(new java.awt.Color(5, 45, 26));
        CategoryComboBox.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        CategoryComboBox.setForeground(new java.awt.Color(5, 45, 26));
        CategoryComboBox.setMaximumRowCount(10);
        CategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "eau", "boisson", "gouter", "tabac", "accessoire", "pates", "recharge téléphonique", "fruits secs", "conserve" }));
        CategoryComboBox.setBorder(null);
        jPanel1.add(CategoryComboBox);
        CategoryComboBox.setBounds(920, 250, 270, 50);

        jLabel1.setBackground(new java.awt.Color(255, 250, 240));
        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(30, 130, 82));
        jLabel1.setText("Catégorie :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(770, 260, 190, 30);

        txBarcode.setBackground(new java.awt.Color(255, 250, 240));
        txBarcode.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        txBarcode.setForeground(new java.awt.Color(5, 45, 26));
        txBarcode.setBorder(null);
        jPanel1.add(txBarcode);
        txBarcode.setBounds(420, 260, 260, 30);

        jLabel10.setBackground(new java.awt.Color(255, 250, 240));
        jLabel10.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(30, 130, 82));
        jLabel10.setText("Barcode :");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(260, 260, 120, 30);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N
        jPanel1.add(jLabel17);
        jLabel17.setBounds(400, 410, 320, 50);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N
        jPanel1.add(jLabel12);
        jLabel12.setBounds(930, 500, 320, 50);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N
        jPanel1.add(jLabel13);
        jLabel13.setBounds(400, 250, 320, 50);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N
        jPanel1.add(jLabel14);
        jLabel14.setBounds(400, 330, 320, 50);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N
        jPanel1.add(jLabel15);
        jLabel15.setBounds(930, 410, 320, 50);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N
        jPanel1.add(jLabel16);
        jLabel16.setBounds(400, 490, 320, 50);

        jPanel2.setBackground(new java.awt.Color(255, 250, 240));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 130, 82), 2));
        jPanel2.setForeground(new java.awt.Color(30, 130, 82));
        jPanel2.setLayout(null);

        jLabel19.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(30, 130, 82));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Ajouter Produit");
        jPanel2.add(jLabel19);
        jLabel19.setBounds(20, 0, 490, 80);

        Heure1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Heure1.setForeground(new java.awt.Color(5, 45, 26));
        Heure1.setText("22:10");
        jPanel2.add(Heure1);
        Heure1.setBounds(510, 5, 100, 70);

        Date1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Date1.setForeground(new java.awt.Color(5, 45, 26));
        Date1.setText("31-12-2019");
        jPanel2.add(Date1);
        Date1.setBounds(640, 5, 100, 70);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 2100, 80);

        CigaretteCheckBox.setBackground(new java.awt.Color(30, 130, 82));
        CigaretteCheckBox.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        CigaretteCheckBox.setForeground(new java.awt.Color(30, 130, 82));
        CigaretteCheckBox.setText("cigarettes");
        CigaretteCheckBox.setContentAreaFilled(false);
        CigaretteCheckBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(CigaretteCheckBox);
        CigaretteCheckBox.setBounds(920, 320, 140, 33);

        CigarettePanel.setOpaque(false);

        txVente_Prix1.setBackground(new java.awt.Color(255, 250, 240));
        txVente_Prix1.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        txVente_Prix1.setForeground(new java.awt.Color(5, 45, 26));
        txVente_Prix1.setBorder(null);
        txVente_Prix1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txVente_Prix1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txVente_Prix1KeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(30, 130, 82));
        jLabel11.setText("DT");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N

        jLabel20.setBackground(new java.awt.Color(255, 250, 240));
        jLabel20.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(30, 130, 82));
        jLabel20.setText("Coût Cig :");

        txBase_Prix1.setBackground(new java.awt.Color(255, 250, 240));
        txBase_Prix1.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        txBase_Prix1.setForeground(new java.awt.Color(5, 45, 26));
        txBase_Prix1.setBorder(null);
        txBase_Prix1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txBase_Prix1KeyTyped(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N

        jLabel22.setBackground(new java.awt.Color(255, 250, 240));
        jLabel22.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(30, 130, 82));
        jLabel22.setText("Max Cig :");

        txMax1.setBackground(new java.awt.Color(255, 250, 240));
        txMax1.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        txMax1.setForeground(new java.awt.Color(5, 45, 26));
        txMax1.setText("100");
        txMax1.setBorder(null);
        txMax1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txMax1KeyTyped(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N

        jLabel24.setBackground(new java.awt.Color(255, 250, 240));
        jLabel24.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(30, 130, 82));
        jLabel24.setText("Min Cig :");

        txMin1.setBackground(new java.awt.Color(255, 250, 240));
        txMin1.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        txMin1.setForeground(new java.awt.Color(5, 45, 26));
        txMin1.setText("0");
        txMin1.setBorder(null);
        txMin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txMin1ActionPerformed(evt);
            }
        });
        txMin1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txMin1KeyTyped(evt);
            }
        });

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/fieldbg.png"))); // NOI18N

        jLabel26.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(30, 130, 82));
        jLabel26.setText("DT");

        jLabel7.setBackground(new java.awt.Color(255, 250, 240));
        jLabel7.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(30, 130, 82));
        jLabel7.setText("Prix Cig :");

        javax.swing.GroupLayout CigarettePanelLayout = new javax.swing.GroupLayout(CigarettePanel);
        CigarettePanel.setLayout(CigarettePanelLayout);
        CigarettePanelLayout.setHorizontalGroup(
            CigarettePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(CigarettePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CigarettePanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(CigarettePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CigarettePanelLayout.createSequentialGroup()
                            .addGroup(CigarettePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(CigarettePanelLayout.createSequentialGroup()
                                    .addGap(380, 380, 380)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(CigarettePanelLayout.createSequentialGroup()
                                    .addGap(140, 140, 140)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(CigarettePanelLayout.createSequentialGroup()
                                    .addGap(160, 160, 160)
                                    .addComponent(txVente_Prix1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(40, 40, 40)
                            .addGroup(CigarettePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(CigarettePanelLayout.createSequentialGroup()
                                    .addGap(170, 170, 170)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(CigarettePanelLayout.createSequentialGroup()
                                    .addGap(190, 190, 190)
                                    .addComponent(txBase_Prix1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(CigarettePanelLayout.createSequentialGroup()
                                    .addGap(410, 410, 410)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(CigarettePanelLayout.createSequentialGroup()
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(CigarettePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(CigarettePanelLayout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(txMax1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(40, 40, 40)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addGroup(CigarettePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(CigarettePanelLayout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(txMin1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        CigarettePanelLayout.setVerticalGroup(
            CigarettePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
            .addGroup(CigarettePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CigarettePanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(CigarettePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(CigarettePanelLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(CigarettePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txVente_Prix1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txBase_Prix1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(30, 30, 30)
                    .addGroup(CigarettePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CigarettePanelLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CigarettePanelLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(txMax1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(CigarettePanelLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CigarettePanelLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(CigarettePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(CigarettePanelLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(txMin1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel1.add(CigarettePanel);
        CigarettePanel.setBounds(250, 570, 1000, 200);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1920, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            rs = db.querySelectAll("produit", "Barcode='" + txBarcode.getText() + "'");
          

            if (txBarcode.getText().equals("") || txName.getText().equals("") || txMax.getText().equals("") || txMin.getText().equals("") || CategoryComboBox.getSelectedItem().equals("")
                    || txBase_Prix.getText().equals("") || txVente_Prix.getText().equals("")) {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Veuillez saisir tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (rs.isBeforeFirst()) {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Ce code produit existe déjà !", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (Integer.parseInt(txMax.getText()) < Integer.parseInt(txMin.getText())) {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "La quantité maximale doit etre superieure à la quantité minimale !", "Erreur", JOptionPane.ERROR_MESSAGE);
            
            } else if (Double.parseDouble(txVente_Prix.getText()) < Double.parseDouble(txBase_Prix.getText())) {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Le prix de vente doit être superieur au prix de base!", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                db.closeconnexion();
                String user = utilisateur.getText();
                String action = "ajouter";
                String Description = txBarcode.getText();
                System.err.println(Description);
                String d = Date.getText();
                String h = Heure.getText();
                String[] actions = {"user", "type_action", "description", "Date", "Heure"};
                String[] inf3 = {user, action, Description, d, h};
                System.out.println(db.queryInsert("action", actions, inf3));
                db.closeconnexion();
                
                
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(3);
                df.setMinimumFractionDigits(3);
                
                String prixVente = df.format(Double.parseDouble(txVente_Prix.getText())); 
                String prixBase = df.format(Double.parseDouble(txBase_Prix.getText())); 
                

                
                System.out.println(prixVente+"  "+prixBase);
                
                //**************** add product to table produit *******************************
                String[] colon = {"nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min"};

                if (CategoryComboBox.getSelectedItem().equals("tabac")) {

                    {
                        String[] inf = {txName.getText() + " pqt", txBarcode.getText(), CategoryComboBox.getSelectedItem().toString(), prixBase, prixVente, txMax.getText(), txMin.getText()};
                        System.out.println(db.queryInsert("produit", colon, inf));
                        db.closeconnexion();

                    }

                    if (CigaretteCheckBox.isSelected()) {
                        
                        String prixVenteCig = df.format(Double.parseDouble(txVente_Prix1.getText())); 
                        String prixBaseCig = df.format(Double.parseDouble(txBase_Prix1.getText())); 
                        
                        System.out.println(prixVenteCig+"  "+prixBaseCig);
                        
                        String[] inf = {txName.getText() + " cigarette", txBarcode.getText(), CategoryComboBox.getSelectedItem().toString(), prixBaseCig, prixVenteCig, txMax1.getText(), txMin1.getText()};
                        System.out.println(db.queryInsert("produit", colon, inf));
                        db.closeconnexion();
                    }
                } else {

                    String[] inf = {txName.getText(), txBarcode.getText(), CategoryComboBox.getSelectedItem().toString(), prixBase, prixVente, txMax.getText(), txMin.getText()};
                    System.out.println(db.queryInsert("produit", colon, inf));
                    db.closeconnexion();

                }

                //**************** add product to table produit *******************************
                //System.out.println(db.queryInsert("produit", colon, inf));
                String[] colon2 = {"nom", "Barcode", "Categorie", "Prix_Base", "Prix_Vente", "Max", "Min", "Quantite"};

                if (CategoryComboBox.getSelectedItem().equals("tabac")) {

                    {
                        //String formattedPrice = String.format("%.4f", txVente_Prix.getText()); 
                        //System.err.println(formattedPrice);

                        String[] inf = {txName.getText() + " pqt", txBarcode.getText(), CategoryComboBox.getSelectedItem().toString(), prixBase, prixVente, txMax.getText(), txMin.getText(), "0"};
                        System.out.println(db.queryInsert("produit_stock", colon2, inf));
                                            db.closeconnexion();

                    }

                    if (CigaretteCheckBox.isSelected()) {
                        
                        String prixVenteCig = df.format(Double.parseDouble(txVente_Prix1.getText())); 
                        String prixBaseCig = df.format(Double.parseDouble(txBase_Prix1.getText())); 
                        
                        System.out.println(prixVenteCig+"  "+prixBaseCig);
                        
                        
                        String[] inf = {txName.getText() + " cigarette", txBarcode.getText(), CategoryComboBox.getSelectedItem().toString(), prixBaseCig, prixVenteCig, txMax1.getText(), txMin1.getText(), "0"};
                        System.out.println(db.queryInsert("produit_stock", colon2, inf));
                                            db.closeconnexion();

                    }
                } else {

                    String[] inf = {txName.getText(), txBarcode.getText(), CategoryComboBox.getSelectedItem().toString(), prixBase, prixVente, txMax.getText(), txMin.getText(), "0"};
                    System.out.println(db.queryInsert("produit_stock", colon2, inf));
                                        db.closeconnexion();


                }

                actualiser();

                this.hide();
                db.closeconnexion();
                Inventory inv = new Inventory(utilisateur.getText());
                inv.show();

            }
            table();
        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void annulerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerbtnActionPerformed
        // TODO add your handling code here:
        actualiser();
        this.hide();
        db.closeconnexion();
        Inventory inv = new Inventory(utilisateur.getText());
        inv.show();

    }//GEN-LAST:event_annulerbtnActionPerformed

    private void txMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txMinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txMinActionPerformed

    private void txVente_PrixKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txVente_PrixKeyTyped
        // TODO add your handling code here:
        if (!(evt.getKeyChar() == '0'
                || evt.getKeyChar() == '1'
                || evt.getKeyChar() == '2'
                || evt.getKeyChar() == '3'
                || evt.getKeyChar() == '4'
                || evt.getKeyChar() == '5'
                || evt.getKeyChar() == '6'
                || evt.getKeyChar() == '7'
                || evt.getKeyChar() == '8'
                || evt.getKeyChar() == '9'
                || evt.getKeyChar() == '.'
                || evt.equals(KeyEvent.VK_BACK_SPACE))
                || txVente_Prix.getText().length() >= 6) {
            evt.consume();
        }

        if (evt.getKeyChar() == '.' && txVente_Prix.getText().contains(".")) {
            evt.consume();
        }

        if (evt.getKeyChar() == '.' && txVente_Prix.getText().length() == 0) {
            evt.consume();
        }
    }//GEN-LAST:event_txVente_PrixKeyTyped

    private void txBase_PrixKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBase_PrixKeyTyped
        // TODO add your handling code here:
        if (!(evt.getKeyChar() == '0'
                || evt.getKeyChar() == '1'
                || evt.getKeyChar() == '2'
                || evt.getKeyChar() == '3'
                || evt.getKeyChar() == '4'
                || evt.getKeyChar() == '5'
                || evt.getKeyChar() == '6'
                || evt.getKeyChar() == '7'
                || evt.getKeyChar() == '8'
                || evt.getKeyChar() == '9'
                || evt.getKeyChar() == '.'
                || evt.equals(KeyEvent.VK_BACK_SPACE))
                || txBase_Prix.getText().length() >= 6) {
            evt.consume();
        }

        if (evt.getKeyChar() == '.' && txBase_Prix.getText().contains(".")) {
            evt.consume();
        }

        if (evt.getKeyChar() == '.' && txBase_Prix.getText().length() == 0) {
            evt.consume();
        }
    }//GEN-LAST:event_txBase_PrixKeyTyped

    private void txMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txMaxKeyTyped
        // TODO add your handling code here:
        if (!(evt.getKeyChar() == '0'
                || evt.getKeyChar() == '1'
                || evt.getKeyChar() == '2'
                || evt.getKeyChar() == '3'
                || evt.getKeyChar() == '4'
                || evt.getKeyChar() == '5'
                || evt.getKeyChar() == '6'
                || evt.getKeyChar() == '7'
                || evt.getKeyChar() == '8'
                || evt.getKeyChar() == '9'
                || evt.equals(KeyEvent.VK_BACK_SPACE)) || txMax.getText().length() >= 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txMaxKeyTyped

    private void txMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txMinKeyTyped
        // TODO add your handling code here:
        if (!(evt.getKeyChar() == '0'
                || evt.getKeyChar() == '1'
                || evt.getKeyChar() == '2'
                || evt.getKeyChar() == '3'
                || evt.getKeyChar() == '4'
                || evt.getKeyChar() == '5'
                || evt.getKeyChar() == '6'
                || evt.getKeyChar() == '7'
                || evt.getKeyChar() == '8'
                || evt.getKeyChar() == '9'
                || evt.equals(KeyEvent.VK_BACK_SPACE)) || txMin.getText().length() >= 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txMinKeyTyped

    private void txVente_Prix1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txVente_Prix1KeyTyped
        if (!(evt.getKeyChar() == '0'
                || evt.getKeyChar() == '1'
                || evt.getKeyChar() == '2'
                || evt.getKeyChar() == '3'
                || evt.getKeyChar() == '4'
                || evt.getKeyChar() == '5'
                || evt.getKeyChar() == '6'
                || evt.getKeyChar() == '7'
                || evt.getKeyChar() == '8'
                || evt.getKeyChar() == '9'
                || evt.getKeyChar() == '.'
                || evt.equals(KeyEvent.VK_BACK_SPACE))
                || txVente_Prix1.getText().length() >= 6) {
            evt.consume();
        }

        if (evt.getKeyChar() == '.' && txVente_Prix1.getText().contains(".")) {
            evt.consume();
        }

        if (evt.getKeyChar() == '.' && txVente_Prix1.getText().length() == 0) {
            evt.consume();
        }
    }//GEN-LAST:event_txVente_Prix1KeyTyped

    private void txBase_Prix1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBase_Prix1KeyTyped
        if (!(evt.getKeyChar() == '0'
                || evt.getKeyChar() == '1'
                || evt.getKeyChar() == '2'
                || evt.getKeyChar() == '3'
                || evt.getKeyChar() == '4'
                || evt.getKeyChar() == '5'
                || evt.getKeyChar() == '6'
                || evt.getKeyChar() == '7'
                || evt.getKeyChar() == '8'
                || evt.getKeyChar() == '9'
                || evt.getKeyChar() == '.'
                || evt.equals(KeyEvent.VK_BACK_SPACE))
                || txBase_Prix1.getText().length() >= 6) {
            evt.consume();
        }

        if (evt.getKeyChar() == '.' && txBase_Prix1.getText().contains(".")) {
            evt.consume();
        }

        if (evt.getKeyChar() == '.' && txBase_Prix1.getText().length() == 0) {
            evt.consume();
        }
    }//GEN-LAST:event_txBase_Prix1KeyTyped

    private void txMax1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txMax1KeyTyped
        if (!(evt.getKeyChar() == '0'
                || evt.getKeyChar() == '1'
                || evt.getKeyChar() == '2'
                || evt.getKeyChar() == '3'
                || evt.getKeyChar() == '4'
                || evt.getKeyChar() == '5'
                || evt.getKeyChar() == '6'
                || evt.getKeyChar() == '7'
                || evt.getKeyChar() == '8'
                || evt.getKeyChar() == '9'
                || evt.equals(KeyEvent.VK_BACK_SPACE)) || txMax1.getText().length() >= 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txMax1KeyTyped

    private void txMin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txMin1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txMin1ActionPerformed

    private void txMin1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txMin1KeyTyped
        // TODO add your handling code here:
        if (!(evt.getKeyChar() == '0'
                || evt.getKeyChar() == '1'
                || evt.getKeyChar() == '2'
                || evt.getKeyChar() == '3'
                || evt.getKeyChar() == '4'
                || evt.getKeyChar() == '5'
                || evt.getKeyChar() == '6'
                || evt.getKeyChar() == '7'
                || evt.getKeyChar() == '8'
                || evt.getKeyChar() == '9'
                || evt.equals(KeyEvent.VK_BACK_SPACE)) || txMin1.getText().length() >= 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txMin1KeyTyped

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
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddProduct("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CategoryComboBox;
    private javax.swing.JCheckBox CigaretteCheckBox;
    private javax.swing.JPanel CigarettePanel;
    private javax.swing.JLabel Date;
    private javax.swing.JLabel Date1;
    private javax.swing.JLabel Heure;
    private javax.swing.JLabel Heure1;
    private javax.swing.JLabel annulerbg;
    private javax.swing.JButton annulerbtn;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel modifierbg;
    private javax.swing.JTextField txBarcode;
    private javax.swing.JTextField txBase_Prix;
    private javax.swing.JTextField txBase_Prix1;
    private javax.swing.JTextField txMax;
    private javax.swing.JTextField txMax1;
    private javax.swing.JTextField txMin;
    private javax.swing.JTextField txMin1;
    private javax.swing.JTextField txName;
    private javax.swing.JLabel txTest;
    private javax.swing.JLabel txTest1;
    private javax.swing.JTextField txVente_Prix;
    private javax.swing.JTextField txVente_Prix1;
    private javax.swing.JLabel utilisateur;
    // End of variables declaration//GEN-END:variables
}

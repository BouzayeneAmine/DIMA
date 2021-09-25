package Gestion;

import BDD.DbConnection;
import BDD.Parametre;
import BDD.ResultSetTableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.TableModel;

public class ListeSuppressionDeux extends javax.swing.JInternalFrame {

    ResultSet rs;
    DbConnection db;

    public ListeSuppressionDeux() {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        initComponents();
        removeTitleBar();
        table();
        calcultotal();
    }

    void removeTitleBar() {
        putClientProperty("ListeSuppressionDeux.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }

    public void calcultotal() {
        jLabel3.setText(String.valueOf(jTable1.getRowCount()));
        float c = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            String b = String.valueOf(jTable1.getValueAt(i, 10));
            String money = b.replace(',', '.');
            float d = Float.parseFloat(money);
            c = c + d;
        }
        jLabel6.setText(Float.toString(c));
        DecimalFormat df = new DecimalFormat("0");
        df.setRoundingMode(RoundingMode.DOWN);
        float bb = (Float.parseFloat(jLabel6.getText()));
        String money = df.format(bb).replace(',', '.');
        jLabel6.setText(money);
    }

    public void table() {
        String a[] = {"Id", "Id_type", "Type", "Id_produit", "Produit_etat", "Numero", "Code", "Produit", "Date", "Heure", "Pesage", "Date_supression", "Nom_machine"};
        rs = db.querySelect(a, "suppression_deux");
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(1);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(1);
        jTable1.getColumnModel().getColumn(3).setMinWidth(3);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(3);
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        a = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        de = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

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

        jLabel13.setBackground(new java.awt.Color(0, 51, 102));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Liste des éléments supprimés");
        jLabel13.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        a.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        a.setDateFormatString("yyyy-MM-dd");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search-engine.png"))); // NOI18N
        jButton1.setText("Chercher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/export.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Type", "Produit", "Numero", "Code Type", "Code Produit", "Immatricule", "Nom Machine" }));

        jLabel1.setText("De");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Type", "Produit", "Numero", "Code Type", "Code Produit", "Immatricule", "Nom Machine" }));

        jLabel2.setText("A");

        de.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        de.setDateFormatString("yyyy-MM-dd");

        jLabel4.setText("Nombre de suppression :");

        jLabel3.setFont(new java.awt.Font("HP Simplified", 2, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Backup.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setText("Totale :");

        jLabel6.setFont(new java.awt.Font("HP Simplified", 2, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel7.setText("KG");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Date", "Date de suppression" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, 188, Short.MAX_VALUE)
                    .addComponent(de, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(a, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jComboBox2, 0, 259, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2)
                            .addComponent(jComboBox3, 0, 270, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(de, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 26, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(11, 11, 11))
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(ListeSuppressionDeux.this);
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
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (de.getCalendar() == null || a.getCalendar() == null) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une date de début et de fin !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String text = "Date";
            String text1 = "Type ";
            String text2 = "Type ";

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            String deb = sdf.format(de.getDate());
            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            String fin = sd.format(a.getDate());

            if (jComboBox2.getSelectedItem().equals("Type")) {
                text1 = "Type ";
            } else if (jComboBox2.getSelectedItem().equals("Produit")) {
                text1 = "Produit_etat ";
            } else if (jComboBox2.getSelectedItem().equals("Numero")) {
                text1 = "Numero ";
            } else if (jComboBox2.getSelectedItem().equals("Code Type")) {
                text1 = "Code ";
            } else if (jComboBox2.getSelectedItem().equals("Code Produit")) {
                text1 = "Produit ";
            } else if (jComboBox2.getSelectedItem().equals("Nom Machine")) {
                text1 = "Nom_machine ";
            }

            if (jComboBox3.getSelectedItem().equals("Type")) {
                text2 = "Type ";
            } else if (jComboBox3.getSelectedItem().equals("Produit")) {
                text2 = "Produit_etat ";
            } else if (jComboBox3.getSelectedItem().equals("Numero")) {
                text2 = "Numero ";
            } else if (jComboBox3.getSelectedItem().equals("Code Type")) {
                text2 = "Code ";
            } else if (jComboBox3.getSelectedItem().equals("Code Produit")) {
                text2 = "Produit ";
            } else if (jComboBox3.getSelectedItem().equals("Nom Machine")) {
                text2 = "Nom_machine ";
            }

            if (jComboBox1.getSelectedItem().equals("Date d entrée")) {
                text = "Date";
            } else if (jComboBox1.getSelectedItem().equals("Date de suppression")) {
                text = "Date_supression";
            }
            rs = db.querySelectTwoALL("suppression_deux", text1 + "LIKE '%" + jTextField1.getText() + "%' ", text2 + "LIKE '%" + jTextField2.getText() + "%' ", text, deb, fin);
            jTable1.setModel(new ResultSetTableModel(rs));
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(1).setMinWidth(1);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(1);
            jTable1.getColumnModel().getColumn(3).setMinWidth(3);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(3);
            calcultotal();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked


    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String Numero = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 5));
        if (Numero.equals("null")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un élément suprimé dans le tableau !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String password1 = "";
            String Typeaccess = "Directeur";
            rs = db.querySelectAll("utilisateur", "Titre='" + Typeaccess + "'");
            try {
                while (rs.next()) {
                    password1 = rs.getString("Password");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (JOptionPane.showInputDialog(this, "Veuillez saisir votre mot de passe Directeur :", "Verification", JOptionPane.OK_CANCEL_OPTION).equals(password1)) {
                String idType = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
                String Type = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2));
                String idproduit = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3));
                String produitetat = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4));
                String Typetxt = "rien";
                String bdType = "rien";
                String bdproduit = "rien";
                String mvt = "rien";

                if (Type.equals("Client valide")) {
                    bdType = "client_valide_deux";
                    Typetxt = "client";

                } else if (Type.equals("Fournisseur valide")) {
                    bdType = "fournisseur_valide_deux";
                    Typetxt = "fournisseur";
                } else if (Type.equals("Transporteur valide")) {
                    bdType = "transporteur_valide_deux";
                    Typetxt = "transporteur";
                }

                if (produitetat.equals("Produit Sortant")) {
                    bdproduit = "produit_sortant_deux";
                    mvt = "Sortant";
                } else if (produitetat.equals("Produit Entrant")) {
                    bdproduit = "produit_entrant_deux";
                    mvt = "Entrant";
                }

                rs = db.querySelectAll(bdType, "Id='" + idType + "'");
                jTable3.setModel(new ResultSetTableModel(rs));
                String IdTest3 = String.valueOf(jTable3.getValueAt(0, 1));

                rs = db.querySelectAll(bdproduit, "Id='" + idproduit + "'");
                jTable4.setModel(new ResultSetTableModel(rs));
                String IdTest2 = String.valueOf(jTable4.getValueAt(0, 1));

                if (IdTest2.equals("null") && IdTest3.equals("null")) {
                    String zd = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
                    String Code = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 6));
                    String Produit = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 7));
                    String Date = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 8));
                    String Heure = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 9));
                    String Pesage = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 10));
                    String DateSupression = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 11));
                    String machine = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 12));

                    rs = db.querySelectAll(Typetxt, "Code='" + Code + "'");
                    TableModel jTable = new ResultSetTableModel(rs);
                    String NomClient = String.valueOf(jTable.getValueAt(0, 1));

                    rs = db.querySelectAll("produit", "Code='" + Produit + "'");
                    TableModel jTable2 = new ResultSetTableModel(rs);
                    String DesProduit = String.valueOf(jTable2.getValueAt(0, 1));

                    Date s = new Date();
                    SimpleDateFormat d = new SimpleDateFormat("YYYY-MM-dd");
                    SimpleDateFormat h = new SimpleDateFormat("HH:mm:ss");
                    String txDate = d.format(s);
                    String txHeure = h.format(s);

                    String hostName = null;
                    try {
                        final InetAddress addr = InetAddress.getLocalHost();
                        hostName = new String(addr.getHostName());
                    } catch (final Exception e) {
                        hostName = ".";
                    }

                    String[] colon = {"Id", "Numero", "mouvement", "Code_" + Typetxt, "Nom_" + Typetxt, "Code_produit", "Designation_produit", "Date", "Heure", "Pesage"};
                    String[] inf = {idType, Numero, mvt, Code, NomClient, Produit, DesProduit, Date, Heure, Pesage};
                    System.out.println(db.queryInsert(bdType, colon, inf));

                    String[] colonn = {"Id", "Numero", "Code_produit", "Designation_produit", "code_type", "Nom_type", "Date", "Heure", "Pesage"};
                    String[] inff = {idproduit, Numero, Produit, DesProduit, Code, NomClient, Date, Heure, Pesage};
                    System.out.println(db.queryInsert(bdproduit, colonn, inff));

                    String[] colo = {"Id_type", "Type", "Id_produit", "Produit_etat", "Numero", "Code", "Produit", "Date", "Heure", "Pesage", "Date_supression", "Nom_machine", "Date_recuperation", "Nom_machine_recuperation"};
                    String[] in = {idType, Type, idproduit, produitetat, Numero, Code, Produit, Date, Heure, Pesage, DateSupression, machine, txDate + " " + txHeure, hostName};
                    System.out.println(db.queryInsert("recuperation_deux", colo, in));

                    db.queryDelete("suppression_deux", "id=" + zd);

                    table();
                    JOptionPane.showMessageDialog(this, "L élément a été récupéré avec succès.");
                } else {
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(this, "Impossible de récupérer cet élément !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Le mot de passe est incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser a;
    private com.toedter.calendar.JDateChooser de;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}

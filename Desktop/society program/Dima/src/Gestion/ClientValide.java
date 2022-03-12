package Gestion;

import BDD.DbConnection;
import BDD.Parametre;
import BDD.ResultSetTableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ClientValide extends javax.swing.JFrame {

    int click = 0;
    ResultSet rs;
    DbConnection db;
    public static ClientValide obj = null;
    String table_name = "client_valide";

    public ClientValide() {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/Images/Logo.png")).getImage());
        table();
        pesageCheckBox.setSelected(true);
    }

    public static ClientValide getObj() {
        if (obj == null) {
            obj = new ClientValide();
        }
        return obj;
    }

    public void table() {
        String a[] = {"Id", "Numero", "Immatricule", "transporteur", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
        rs = db.querySelect(a, "client_valide");
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    public void facture() {
        String q = "select Client,Code,Produit,Immatricule,Transporteur,Chauffeur,Convayeur,Date,Heure,Facture,SUM(Quantite) from facture GROUP BY Immatricule,Date";
        rs = db.exécutionQuery(q);
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(2).setMinWidth(0);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(9).setMinWidth(0);
        jTable1.getColumnModel().getColumn(9).setMaxWidth(0);

    }

    public void facture_date(String colum, String field, String date, String dateE, String dateS) {
        String q = "select Client,Code,Produit,Immatricule,Transporteur,Chauffeur,Convayeur,Date,Heure,Facture,SUM(Quantite) from facture "
                + " WHERE " + colum + "='" + field + "' "
                + " AND " + date + " BETWEEN '" + dateE + "' AND '" + dateS + "' "
                + " GROUP BY Immatricule";
        rs = db.exécutionQuery(q);
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(2).setMinWidth(0);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(0);

    }

    public void facture_date_w_field(String date, String dateE, String dateS) {
        String q = "select Client,Code,Produit,Immatricule,Transporteur,Chauffeur,Convayeur,Date,Heure,Facture,SUM(Quantite) from facture "
                + " WHERE "
                + date + " BETWEEN '" + dateE + "' AND '" + dateS + "' "
                + " GROUP BY Immatricule";
        rs = db.exécutionQuery(q);
        jTable1.setModel(new ResultSetTableModel(rs));

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        de = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        a = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        pesageCheckBox = new javax.swing.JCheckBox();
        factureCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LISTE DES CLIENTS VALIDES");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
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

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/export.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("De");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Code client", "Numero", "Immatricule Camion", "transporteur", "chauffeur", "convayeur" }));

        jLabel2.setText("A");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Date d entrée", "Date  de sorite" }));

        de.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        de.setDateFormatString("yyyy-MM-dd");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search-engine.png"))); // NOI18N
        jButton1.setText("Chercher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        a.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        a.setDateFormatString("yyyy-MM-dd");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/initialiser.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(0, 51, 102));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Liste des clients valides");
        jLabel13.setOpaque(true);

        buttonGroup1.add(pesageCheckBox);
        pesageCheckBox.setFont(new java.awt.Font("Roboto Light", 0, 18)); // NOI18N
        pesageCheckBox.setSelected(true);
        pesageCheckBox.setText("PESAGE");
        pesageCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesageCheckBoxActionPerformed(evt);
            }
        });

        buttonGroup1.add(factureCheckBox);
        factureCheckBox.setFont(new java.awt.Font("Roboto Light", 0, 18)); // NOI18N
        factureCheckBox.setText("FACTURE");
        factureCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                factureCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(de, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(a, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox2, 0, 302, Short.MAX_VALUE)
                            .addComponent(jTextField1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(pesageCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(factureCheckBox)
                        .addContainerGap(45, Short.MAX_VALUE))))
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pesageCheckBox)
                                    .addComponent(factureCheckBox)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(de, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel2)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(766, 656));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(ClientValide.this);
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MessageFormat header = new MessageFormat("Liste des clients valides");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            jTable1.print(JTable.PrintMode.FIT_WIDTH, header, footer);

        } catch (java.awt.print.PrinterException e) {
            System.err.format("Erreur d'impression ", e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String text = "Date_premier";
        String text1 = "Numero";
        String text2 = "Numero ";
        String deb = "2016-01-01";
        String fin = "2080-01-01";

        if (jComboBox1.getSelectedItem().equals("Date d entrée") || (pesageCheckBox.isSelected())) {
            text = "Date_premier";
        } else if (jComboBox1.getSelectedItem().equals("Date  de sorite") || (pesageCheckBox.isSelected())) {
            text = "Date_deuxieme";
        }

        if (de.getCalendar() == null || a.getCalendar() == null) {
            deb = "2016-01-01";
            fin = "2080-01-01";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            deb = sdf.format(de.getDate());
            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
            fin = sd.format(a.getDate());
        }

        if (jTextField1.getText().equals("")) {
            if (pesageCheckBox.isSelected()) {
                rs = db.querySearchAll("client_valide", text, deb, fin);
            } else if (factureCheckBox.isSelected()) {
                table_name = "facture";
                text = "Date";
                facture_date_w_field(text, deb, fin);
            }
        } else {

            if (jComboBox2.getSelectedItem().equals("Numero")) {
                text1 = "Numero";
            } else if (jComboBox2.getSelectedItem().equals("transporteur")) {
                text1 = "transporteur";
            } else if (jComboBox2.getSelectedItem().equals("chauffeur")) {
                text1 = "chauffeur";
            } else if (jComboBox2.getSelectedItem().equals("Immatricule Camion")) {
                text1 = "Immatricule";
            } else if (jComboBox2.getSelectedItem().equals("convayeur")) {
                text1 = "convayeur";
            }

            if (pesageCheckBox.isSelected()) {
                table_name = "client_valide";
                text = "Date_premier";
                rs = db.querySelectTwoOne(table_name, text1 + "='" + jTextField1.getText() + "'", text, deb, fin);
            } else if (factureCheckBox.isSelected()) {
                table_name = "facture";
                text = "Date";
                facture_date(text1, jTextField1.getText(), text, deb, fin);
            }

        }
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.setModel(new ResultSetTableModel(rs));
         jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
         jTable1.getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
         jTable1.getColumnModel().getColumn(2).setMinWidth(0);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(0);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (pesageCheckBox.isSelected()) {
            table();
        } else if (factureCheckBox.isSelected()) {
            facture();
        }
        jTextField1.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        table();
        jTextField1.setText("");
    }//GEN-LAST:event_formWindowActivated

    private void pesageCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesageCheckBoxActionPerformed
        // TODO add your handling code here:
        table();
    }//GEN-LAST:event_pesageCheckBoxActionPerformed

    private void factureCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factureCheckBoxActionPerformed
        // TODO add your handling code here:
        facture();

    }//GEN-LAST:event_factureCheckBoxActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String heure;
        heure = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 8));
        System.out.println("heure ==========" + heure);

        if (2 == evt.getClickCount()) {
            String deb, fin;

            String immatricule = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3));
            if (de.getCalendar() == null || a.getCalendar() == null) {
                deb = "2016-01-01";
                fin = "2080-01-01";
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
                deb = sdf.format(de.getDate());
                SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
                fin = sd.format(a.getDate());

            }
            String q = "select Client,Code,Produit,Immatricule,Transporteur,Chauffeur,Convayeur,Date,Heure,Facture,Quantite from facture "
                    + "where Immatricule=" + "'" + immatricule + "'"
                    + " AND " + "date" + " BETWEEN '" + deb + "' AND '" + fin + "' "
                    + " AND " + "Heure='" + heure + "'";

            rs = db.exécutionQuery(q);
            jTable1.setModel(new ResultSetTableModel(rs));
            // Do something
        } else if (1 == evt.getClickCount()) {

            click++;
            String immatricule = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3));
            String deb, fin;

            if (de.getCalendar() == null || a.getCalendar() == null) {
                deb = "2016-01-01";
                fin = "2080-01-01";
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
                deb = sdf.format(de.getDate());
                SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
                fin = sd.format(a.getDate());

            }
            String q = "select Client,Code,Produit,Immatricule,Transporteur,Chauffeur,Convayeur,Date,Heure,Facture,SUM(Quantite) from facture "
                    + "where Immatricule=" + "'" + immatricule + "'"
                    + " AND " + "date" + " BETWEEN '" + deb + "' AND '" + fin + "' "
                    + "group by Heure";

            rs = db.exécutionQuery(q);
            jTable1.setModel(new ResultSetTableModel(rs));
            jTable1.setModel(new ResultSetTableModel(rs));
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(1).setMinWidth(0);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(2).setMinWidth(0);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(0);
        }

//        jTable1MouseClicked(evt);{
//                String deb,fin; 
//
//           String immatricule = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3));
//        if (de.getCalendar() == null || a.getCalendar() == null) {
//             deb = "2016-01-01";
//             fin = "2080-01-01";
//        } else {
//            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
//            deb = sdf.format(de.getDate());
//            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
//            fin = sd.format(a.getDate());
//        
//        }
//       String  q = "select Client,Code,Produit,Immatricule,Transporteur,Chauffeur,Convayeur,Date,Heure,Facture,Quantite from facture "
//                + "where Immatricule=" + "'" + immatricule + "'"
//                + " AND " + "date" + " BETWEEN '" + deb +  "' AND '" + fin + "' "
//                ;
//                
//        rs = db.exécutionQuery(q);
//        jTable1.setModel(new ResultSetTableModel(rs));
//    }}

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
//           String text = "Date_premier";
//        String text1 = "Numero";
//        String text2 = "Numero ";
//        String deb = "2016-01-01";
//        String fin = "2080-01-01";
//        if (jComboBox1.getSelectedItem().equals("Date d entrée")) {
//            text = "Date_premier";
//        } else if (jComboBox1.getSelectedItem().equals("Date  de sorite")) {
//            text = "Date_deuxieme";
//        }
//
//        if (de.getCalendar() == null || a.getCalendar() == null) {
//            deb = "2016-01-01";
//            fin = "2080-01-01";
//        } else {
//            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
//            deb = sdf.format(de.getDate());
//            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
//            fin = sd.format(a.getDate());
//        }
//
//        if (jTextField1.getText().equals("") ) {
//            rs = db.querySearchAll("client_valide", text, deb, fin);
//        } else {
//
//            if (jComboBox2.getSelectedItem().equals("Numero")) {
//                text1 = "Numero";
//            } else if (jComboBox2.getSelectedItem().equals("transporteur")) {
//                text1 = "transporteur";
//            } else if (jComboBox2.getSelectedItem().equals("chauffeur")) {
//                text1 = "chauffeur";
//            } else if (jComboBox2.getSelectedItem().equals("Immatricule Camion")) {
//                text1 = "Immatricule";
//            } else if (jComboBox2.getSelectedItem().equals("convayeur")) {
//                text1 = "convayeur";
//            }
//            
//            if (pesageCheckBox.isSelected())
//                table_name="client_valide";
//            else if(factureCheckBox.isSelected())
//                table_name="facture";
//           
//           
//            rs = db.querySelectTwoOne(table_name, text1 + "='" + jTextField1.getText() + "'", text, deb, fin);
//        }
//        jTable1.setModel(new ResultSetTableModel(rs));
//        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
//        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
    }//GEN-LAST:event_jTextField1KeyTyped

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
            java.util.logging.Logger.getLogger(ClientValide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientValide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientValide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientValide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientValide().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser a;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser de;
    private javax.swing.JCheckBox factureCheckBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox pesageCheckBox;
    // End of variables declaration//GEN-END:variables
}

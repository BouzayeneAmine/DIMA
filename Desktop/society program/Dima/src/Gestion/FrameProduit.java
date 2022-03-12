package Gestion;

import BDD.DbConnection;
import BDD.Parametre;
import BDD.ResultSetTableModel;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class FrameProduit extends javax.swing.JFrame {

    ProgrammeUn programmeDeux;
    ResultSet rs;
    DbConnection db;

    public FrameProduit(ProgrammeUn p) {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/Images/Logo.png")).getImage());
        programmeDeux = p;
        table();
    }

    public void table() {
        String a[] = {"Id", "Code", "Designation", "Lot", "Prix", "type"};
        rs = db.querySelectOrdre(a, "produit", "Code");
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        produitCoef = new javax.swing.JTextField();
        produitPrix = new javax.swing.JTextField();
        produitLot = new javax.swing.JTextField();
        produitDesignation = new javax.swing.JTextField();
        produitCode = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        idTest1 = new javax.swing.JTextField();
        idTest2 = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        PFinisCheckBox = new javax.swing.JCheckBox();
        MPremiereCheckBox = new javax.swing.JCheckBox();

        jButton3.setText("Recherche");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PRODUIT");

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/confirm.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Annuler.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Code", "Designation", "Lot", "Prix", "Coefficient" }));

        jLabel13.setBackground(new java.awt.Color(0, 51, 102));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Produit");
        jLabel13.setOpaque(true);

        jLabel7.setText("Recherche par :");

        buttonGroup1.add(PFinisCheckBox);
        PFinisCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PFinisCheckBox.setText("P.Finis");
        PFinisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PFinisCheckBoxActionPerformed(evt);
            }
        });

        buttonGroup1.add(MPremiereCheckBox);
        MPremiereCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MPremiereCheckBox.setText("M.Premiére");
        MPremiereCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPremiereCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(87, 87, 87)
                        .addComponent(PFinisCheckBox)
                        .addGap(18, 18, 18)
                        .addComponent(MPremiereCheckBox)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PFinisCheckBox)
                            .addComponent(MPremiereCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(728, 669));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        produitCode.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1)));
        produitDesignation.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)));
        produitLot.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3)));
        produitPrix.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4)));
        produitCoef.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 5)));
        if (produitCode.getText().equals("") || produitDesignation.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Vueillez sélectrionner un produit !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            programmeDeux.getProduitCode = this.produitCode.getText();
            programmeDeux.getProduitDesignation = this.produitDesignation.getText();
            programmeDeux.getProduitLot = this.produitLot.getText();
            programmeDeux.getProduitPrix = this.produitPrix.getText();
            programmeDeux.getProduitCoef = this.produitCoef.getText();
            programmeDeux.postProduit();
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (jComboBox1.getSelectedItem().equals("Code")) {
            rs = db.querySelectAll("produit", "Code LIKE '%" + jTextField1.getText() + "%' ");
        } else if (jComboBox1.getSelectedItem().equals("Designation")) {
            rs = db.querySelectAll("produit", "Designation LIKE '%" + jTextField1.getText() + "%' ");
        } else if (jComboBox1.getSelectedItem().equals("Lot")) {
            rs = db.querySelectAll("produit", "Lot LIKE '%" + jTextField1.getText() + "%' ");
        } else if (jComboBox1.getSelectedItem().equals("Prix")) {
            rs = db.querySelectAll("produit", "Prix LIKE '%" + jTextField1.getText() + "%' ");
        } else if (jComboBox1.getSelectedItem().equals("type")) {
            rs = db.querySelectAll("produit", "type LIKE '%" + jTextField1.getText() + "%' ");
        }
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void PFinisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PFinisCheckBoxActionPerformed
        // TODO add your handling code here:
        String query= "SELECT * from produit where type='Produis Finis'";
        rs=db.exécutionQuery(query);
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
    }//GEN-LAST:event_PFinisCheckBoxActionPerformed

    private void MPremiereCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPremiereCheckBoxActionPerformed
        // TODO add your handling code here:
        String query= "SELECT * from produit where type='Matiere Premiere'";
        rs=db.exécutionQuery(query);
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
    }//GEN-LAST:event_MPremiereCheckBoxActionPerformed

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
            java.util.logging.Logger.getLogger(FrameProduit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameProduit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameProduit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameProduit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new FrameProduit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox MPremiereCheckBox;
    private javax.swing.JCheckBox PFinisCheckBox;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField idTest1;
    private javax.swing.JTextField idTest2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField produitCode;
    private javax.swing.JTextField produitCoef;
    private javax.swing.JTextField produitDesignation;
    private javax.swing.JTextField produitLot;
    private javax.swing.JTextField produitPrix;
    // End of variables declaration//GEN-END:variables
}

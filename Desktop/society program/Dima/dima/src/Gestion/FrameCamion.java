package Gestion;

import BDD.DbConnection;
import BDD.Parametre;
import BDD.ResultSetTableModel;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class FrameCamion extends javax.swing.JFrame {

    ProgrammeUn programmeDeux;
    ResultSet rs;
    DbConnection db;

    public FrameCamion(ProgrammeUn p) {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/Images/Logo.png")).getImage());
        table();
        programmeDeux = p;
    }

    public void table() {
        String a[] = {"Id", "Type", "Immatricule", "Tare","Nom_Transporteur"};
        rs = db.querySelectOrdre(a, "camion", "Immatricule");
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        camionType = new javax.swing.JTextField();
        camionTarre = new javax.swing.JTextField();
        camionImmatricule = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        idTest1 = new javax.swing.JTextField();
        idTest2 = new javax.swing.JTextField();
        camionTransporteur = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jButton3.setText("Recherche");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        camionTransporteur.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CAMION");

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

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Immatricule", "Type", "Tare" }));

        jLabel13.setBackground(new java.awt.Color(0, 51, 102));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Camion");
        jLabel13.setOpaque(true);

        jLabel7.setText("Recherche par :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
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
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(742, 674));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        camionType.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1)));
        camionImmatricule.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)));
        camionTarre.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3)));
        camionTransporteur.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 4)));
        if (camionType.getText().equals("") || camionImmatricule.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Vueillez sélectrionner un camion !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            programmeDeux.getCamionType = this.camionType.getText();
            programmeDeux.getCamionTarre = this.camionTarre.getText();
            programmeDeux.getCamionImmatricule = this.camionImmatricule.getText();
            programmeDeux.getCamionTtransporteur = this.camionTransporteur.getText();
            programmeDeux.postCamion();
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTextField1.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "SVP entrer quelque chose");
        } else {
            if (jComboBox1.getSelectedItem().equals("Type")) {
                rs = db.querySelectAll("camion", "Type LIKE '%" + jTextField1.getText() + "%' ");
            } else if (jComboBox1.getSelectedItem().equals("Immatricule")) {
                rs = db.querySelectAll("camion", "Immatricule LIKE '%" + jTextField1.getText() + "%' ");
            } else if (jComboBox1.getSelectedItem().equals("Tare")) {
                rs = db.querySelectAll("camion", "Tare LIKE '%" + jTextField1.getText() + "%' ");
            }
            jTable1.setModel(new ResultSetTableModel(rs));
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jComboBox1.getSelectedItem().equals("Type")) {
                rs = db.querySelectAll("camion", "Type LIKE '%" + jTextField1.getText() + "%' ");
            } else if (jComboBox1.getSelectedItem().equals("Immatricule")) {
                rs = db.querySelectAll("camion", "Immatricule LIKE '%" + jTextField1.getText() + "%' ");
            } else if (jComboBox1.getSelectedItem().equals("Tare")) {
                rs = db.querySelectAll("camion", "Tare LIKE '%" + jTextField1.getText() + "%' ");
            }
            jTable1.setModel(new ResultSetTableModel(rs));
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

    }//GEN-LAST:event_jTextField1KeyReleased

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
            java.util.logging.Logger.getLogger(FrameCamion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameCamion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameCamion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameCamion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new FrameCamion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField camionImmatricule;
    private javax.swing.JTextField camionTarre;
    private javax.swing.JLabel camionTransporteur;
    private javax.swing.JTextField camionType;
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
    // End of variables declaration//GEN-END:variables
}

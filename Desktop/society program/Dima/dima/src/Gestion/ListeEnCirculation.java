package Gestion;

import BDD.DbConnection;
import BDD.Parametre;
import BDD.ResultSetTableModel;
import java.sql.ResultSet;
import javax.swing.JRootPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class ListeEnCirculation extends javax.swing.JInternalFrame {

    ResultSet rs;
    DbConnection db;

    public ListeEnCirculation() {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        initComponents();
        removeTitleBar();
        table1();
    }

    void removeTitleBar() {
        putClientProperty("ListeEnCirculation.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }

    public void table1() {
        String a[] = {"Id", "Numero", "Immatricule", "Type", "Code_Type", "Code_Produit", "Date_entree", "Heure_entree", "Premier_pesage", "Client_Nom", "Client_Adresse", "Produit_des", "Produit_lot", "Produi_prix", "Produit_coef", "Cam_type", "cam_tare"};
        rs = db.querySelect(a, "en_circulation");
        jTable1.setModel(new ResultSetTableModel(rs));
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(9).setMinWidth(9);
        jTable1.getColumnModel().getColumn(9).setMaxWidth(9);
        jTable1.getColumnModel().getColumn(10).setMinWidth(10);
        jTable1.getColumnModel().getColumn(10).setMaxWidth(10);
        jTable1.getColumnModel().getColumn(11).setMinWidth(11);
        jTable1.getColumnModel().getColumn(11).setMaxWidth(11);
        jTable1.getColumnModel().getColumn(12).setMinWidth(12);
        jTable1.getColumnModel().getColumn(12).setMaxWidth(12);
        jTable1.getColumnModel().getColumn(13).setMinWidth(13);
        jTable1.getColumnModel().getColumn(13).setMaxWidth(13);
        jTable1.getColumnModel().getColumn(14).setMinWidth(14);
        jTable1.getColumnModel().getColumn(14).setMaxWidth(14);
        jTable1.getColumnModel().getColumn(15).setMinWidth(15);
        jTable1.getColumnModel().getColumn(15).setMaxWidth(15);
        jTable1.getColumnModel().getColumn(16).setMinWidth(16);
        jTable1.getColumnModel().getColumn(16).setMaxWidth(16);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel13.setBackground(new java.awt.Color(0, 51, 102));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Liste des camions en circulation");
        jLabel13.setOpaque(true);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel13;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

package Gestion;

import BDD.ConnectComPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Math.round;
import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Afficheur extends javax.swing.JFrame implements Runnable, SerialPortEventListener {

    //BDD.License l;
    ConnectComPort dc;
    ResultSet rs;
    SerialPort serialPort;
    private BufferedReader input;
    int TIME_OUT;
    int DATA_RATE;
    String com;

    public Afficheur() {
        dc = new ConnectComPort();
       // l = new BDD.License();
        initComponents();
        //this.setIconImage(new ImageIcon(getClass().getResource("/Images/Logo.png")).getImage());
        datecourante();
        this.pack();
        this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        table();
        this.TIME_OUT = Integer.parseInt(String.valueOf(jTable1.getValueAt(0, 3)));
        this.DATA_RATE = Integer.parseInt(String.valueOf(jTable1.getValueAt(0, 2)));
        com = String.valueOf(jTable1.getValueAt(0, 1));
        SimpleRead(com);
    }

    public void table() {
        String b[] = {"ID", "PORT", "BAUD", "TIME"};
        rs = dc.querySelect(b, "Parametre");
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    }

    public void datecourante() {
        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar Cal = new GregorianCalendar();
                    int sconde = Cal.get(Calendar.SECOND);
                    int minute = Cal.get(Calendar.MINUTE);
                    int heure = Cal.get(Calendar.HOUR_OF_DAY);
                    jLabel14.setText(+heure + ":" + (minute) + ":" + sconde);
                    int mois = Cal.get(Calendar.MONTH);
                    int annee = Cal.get(Calendar.YEAR);
                    int jour = Cal.get(Calendar.DAY_OF_MONTH);
                    jLabel18.setText(+jour + "/" + (mois + 1) + "/" + annee);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        poidNette = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gestion Pesage Pont Bascule 2.0");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 0, 153));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("17:55:27");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("17:55:27");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo Mestiri 2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(13, 13, 13))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel18))))
        );

        poidNette.setBackground(new java.awt.Color(0, 0, 0));
        poidNette.setFont(new java.awt.Font("Digital-7", 0, 200)); // NOI18N
        poidNette.setForeground(new java.awt.Color(0, 255, 0));
        poidNette.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        poidNette.setText("..");
        poidNette.setToolTipText("");
        poidNette.setOpaque(true);

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Serif LED Board-7", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 255, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("KG");
        jLabel6.setToolTipText("");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel6.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));

        jProgressBar1.setForeground(new java.awt.Color(0, 153, 51));
        jProgressBar1.setMaximum(60000);
        jProgressBar1.setBorderPainted(false);
        jProgressBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jProgressBar1.setStringPainted(true);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setText("Fichier");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Quitter");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edition");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem8.setText("Actualiser");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Retour");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Port indicateur");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Parametre");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Licence");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("?");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem3.setText("Aide");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem1.setText("verifier les mise a jour");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem4.setText("A propos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(poidNette, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(poidNette, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment Quitter ?", " Quitter?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed

    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (!poidNette.getText().equals("Err")) {
            serialPort.close();
        }
        Login l = new Login();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        ConnPort.getObj().setVisible(true);
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        ParametreConnection.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        CodeLicence.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Aide.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        MiseAJour.getObj().dispose();
        CodeLicence.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        MiseAJour.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        APropos.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        APropos.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        MiseAJour.getObj().dispose();
        Aide.getObj().dispose();
        CodeLicence.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ProduitEntrantDeux.getObj().dispose();
        ProduitSortantDeux.getObj().dispose();
        FournisseurValideDeux.getObj().dispose();
        ClientValideDeux.getObj().dispose();
        TransporteurValideDeux.getObj().dispose();
        AjouterProduit.getObj().dispose();
        AjouterFournisseur.getObj().dispose();
        AjouterTransporteur.getObj().dispose();
        AjouterClient.getObj().dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment Quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Afficheur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Afficheur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Afficheur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Afficheur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Afficheur().setVisible(true);
            }
        });
    }

    public void SimpleRead(String com) {
        System.setProperty("gnu.io.rxtx.SerialPorts", com);
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        String PORT_NAMES[] = {
            com, // Windows
        };
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            poidNette.setText("Err");
            return;
        }

        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            // add event listeners
            serialPort.addEventListener((SerialPortEventListener) this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public void run() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void serialEvent(SerialPortEvent event) {
        int a;
        int b;
        if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine = input.readLine();
                if (inputLine.charAt(0) == 'S' || inputLine.charAt(0) == 'U') {     //Indicateur KW
                    a = 7;
                    b = 2;
                    if (inputLine.charAt(0) == 'S') {
                        jLabel1.setText("<>");
                    } else {
                        jLabel1.setText("");
                    }

                    // System.out.println("aaa");
                } else if (inputLine.charAt(inputLine.length() - 2) == 'D') {         //Indicateur Pont de bascule
                    a = 1;
                    b = 3;
                    if (inputLine.charAt(inputLine.length() - 1) == '2') {
                        jLabel1.setText("<>");
                    } else {
                        jLabel1.setText("");
                    }

                    if (inputLine.charAt(0) == 'N' || inputLine.charAt(0) == ' ') {
                        jLabel2.setText("");
                    } else {
                        jLabel2.setText(">0<");
                    }

                    //System.out.println("aaa");
                } else if (inputLine.charAt(inputLine.length() - 3) == '.') {
                    // System.out.println("aaa");
                    a = 1;
                    b = 1;
                } else {
                    a = 1;
                    b = 2;
                    // System.out.println("aaa");
                }

                String firstPut = inputLine.substring(a, inputLine.length());
                String finalPut = firstPut.substring(0, firstPut.length() - b);
                System.out.println(inputLine);
                poidNette.setText(finalPut);

                int ss = round(Float.parseFloat(finalPut));
                jProgressBar1.setValue(ss);
                //  if (ss>0){ jProgressBar1.setValue(ss);}
                // else{jProgressBar1.setValue(0);}       
                if (Float.parseFloat(poidNette.getText()) >= 59000) {
                    jLabel5.setText("Alerte ! Surpoids ! Risque de domage");
                } else {
                    jLabel5.setText("");
                }
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel poidNette;
    // End of variables declaration//GEN-END:variables
}

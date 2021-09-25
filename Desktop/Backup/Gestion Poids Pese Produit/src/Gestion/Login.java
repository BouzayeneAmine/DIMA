package Gestion;

import BDD.DbConnection;
//import BDD.LicenseKeyGUI;
import BDD.Parametre;
//import com.license4j.License;
//import com.license4j.ValidationStatus;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    ResultSet rs;
    DbConnection db;
    String username1, password1, hak;
    //private LicenseKeyGUI licenseKeyGUI;

    public Login() {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/Images/Logo.png")).getImage());
        //licenseKeyGUI = new LicenseKeyGUI(this, true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        txt_password = new javax.swing.JPasswordField();
        txt_username = new javax.swing.JTextField();
        Description = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("LOGIN");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Button.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 260, 60));

        txt_password.setBackground(new java.awt.Color(0, 0, 0));
        txt_password.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        txt_password.setForeground(new java.awt.Color(255, 255, 255));
        txt_password.setBorder(null);
        txt_password.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_password.setOpaque(false);
        txt_password.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txt_passwordMouseMoved(evt);
            }
        });
        txt_password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_passwordMouseExited(evt);
            }
        });
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passwordKeyPressed(evt);
            }
        });
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 214, 380, 70));

        txt_username.setBackground(new java.awt.Color(0, 0, 0));
        txt_username.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        txt_username.setForeground(new java.awt.Color(255, 255, 255));
        txt_username.setBorder(null);
        txt_username.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_username.setOpaque(false);
        txt_username.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txt_usernameMouseMoved(evt);
            }
        });
        txt_username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_usernameMouseExited(evt);
            }
        });
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        txt_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_usernameKeyPressed(evt);
            }
        });
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 380, 70));

        Description.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Description.setForeground(new java.awt.Color(255, 255, 255));
        Description.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(Description, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 440, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Login.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 530));

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

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Imprimante");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Licence");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setText("Licence2");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

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

        setSize(new java.awt.Dimension(557, 590));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        rs = db.querySelectAll("utilisateur", "Login='" + txt_username.getText() + "' and Password='" + txt_password.getText() + "'");
        try {
            while (rs.next()) {
                username1 = rs.getString("Login");
                password1 = rs.getString("Password");
                hak = rs.getString("Titre");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (username1 == null && password1 == null) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Le nom utilisateur ou le mot de passe est incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            if (hak.equals("Superviseur")) {
                InterfaceAdminDeux a = new InterfaceAdminDeux();
                a.setVisible(true);
                this.dispose();
            } else if (hak.equals("Utilisateur")) {
                ProgrammeDeux k = new ProgrammeDeux();
                k.setVisible(true);
                this.dispose();
            } else if (hak.equals("Directeur")) {
                InterfaceGrandAdminDeux k = new InterfaceGrandAdminDeux();
                k.setVisible(true);
                this.dispose();
            } else {
                Afficheur k = new Afficheur();
                k.setVisible(true);
                this.dispose();
            }

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

            String[] colon = {"Login", "Type", "Date", "Heure", "Nom_PC"};
            String[] inf = {username1, hak, txDate, txHeure, hostName};
            System.out.println(db.queryInsert("connexion", colon, inf));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment Quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void txt_usernameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_usernameMouseExited
        Description.setText("");
    }//GEN-LAST:event_txt_usernameMouseExited

    private void txt_usernameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_usernameMouseMoved
        Description.setText("Veuillez saisir votre login pour accéder à votre session");
    }//GEN-LAST:event_txt_usernameMouseMoved

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed

    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_usernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            rs = db.querySelectAll("utilisateur", "Login='" + txt_username.getText() + "' and Password='" + txt_password.getText() + "'");
            try {
                while (rs.next()) {
                    username1 = rs.getString("Login");
                    password1 = rs.getString("Password");
                    hak = rs.getString("Titre");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (username1 == null && password1 == null) {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Le nom utilisateur ou le mot de passe est incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                if (hak.equals("Superviseur")) {
                    InterfaceAdminDeux a = new InterfaceAdminDeux();
                    a.setVisible(true);
                    this.dispose();
                } else if (hak.equals("Utilisateur")) {
                    ProgrammeDeux k = new ProgrammeDeux();
                    k.setVisible(true);
                    this.dispose();
                } else if (hak.equals("Directeur")) {
                    InterfaceGrandAdminDeux k = new InterfaceGrandAdminDeux();
                    k.setVisible(true);
                    this.dispose();
                } else {
                    Afficheur k = new Afficheur();
                    k.setVisible(true);
                    this.dispose();
                }

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

                String[] colon = {"Login", "Type", "Date", "Heure", "Nom_PC"};
                String[] inf = {username1, hak, txDate, txHeure, hostName};
                System.out.println(db.queryInsert("connexion", colon, inf));
            }
        }
    }//GEN-LAST:event_txt_usernameKeyPressed

    private void txt_passwordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_passwordMouseExited
        Description.setText("");
    }//GEN-LAST:event_txt_passwordMouseExited

    private void txt_passwordMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_passwordMouseMoved
        Description.setText("Veuillez saisir votre mot de passe pour accéder à votre session");
    }//GEN-LAST:event_txt_passwordMouseMoved

    private void txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            rs = db.querySelectAll("utilisateur", "Login='" + txt_username.getText() + "' and Password='" + txt_password.getText() + "'");
            try {
                while (rs.next()) {
                    username1 = rs.getString("Login");
                    password1 = rs.getString("Password");
                    hak = rs.getString("Titre");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (username1 == null && password1 == null) {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Le nom utilisateur ou le mot de passe est incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                if (hak.equals("Superviseur")) {
                    InterfaceAdminDeux a = new InterfaceAdminDeux();
                    a.setVisible(true);
                    this.dispose();
                } else if (hak.equals("Utilisateur")) {
                    ProgrammeDeux k = new ProgrammeDeux();
                    k.setVisible(true);
                    this.dispose();
                } else if (hak.equals("Directeur")) {
                    InterfaceGrandAdminDeux k = new InterfaceGrandAdminDeux();
                    k.setVisible(true);
                    this.dispose();
                } else {
                    Afficheur k = new Afficheur();
                    k.setVisible(true);
                    this.dispose();
                }

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

                String[] colon = {"Login", "Type", "Date", "Heure", "Nom_PC"};
                String[] inf = {username1, hak, txDate, txHeure, hostName};
                System.out.println(db.queryInsert("connexion", colon, inf));
            }
        }
    }//GEN-LAST:event_txt_passwordKeyPressed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment Quitter ?", " Quitter?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            db.closeconnexion();
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed

    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Login l = new Login();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        ParametreConnection.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        CodeLicence.getObj().dispose();
        Aide.getObj().dispose();
        MiseAJour.getObj().dispose();
        APropos.getObj().dispose();
        ParametreImprimente.getObj().dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Aide.getObj().setVisible(true);
        CodeLicence.getObj().dispose();
        ParametreConnection.getObj().dispose();
        MiseAJour.getObj().dispose();
        APropos.getObj().dispose();
        ParametreImprimente.getObj().dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        MiseAJour.getObj().setVisible(true);
        CodeLicence.getObj().dispose();
        Aide.getObj().dispose();
        ParametreConnection.getObj().dispose();
        APropos.getObj().dispose();
        ParametreImprimente.getObj().dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        APropos.getObj().setVisible(true);
        CodeLicence.getObj().dispose();
        Aide.getObj().dispose();
        MiseAJour.getObj().dispose();
        ParametreConnection.getObj().dispose();
        ParametreImprimente.getObj().dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        CodeLicence.getObj().setVisible(true);
        ParametreConnection.getObj().dispose();
        Aide.getObj().dispose();
        MiseAJour.getObj().dispose();
        APropos.getObj().dispose();
        ParametreImprimente.getObj().dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        ConnPort.getObj().setVisible(true);
        ParametreConnection.getObj().dispose();
        CodeLicence.getObj().dispose();
        Aide.getObj().dispose();
        MiseAJour.getObj().dispose();
        APropos.getObj().dispose();
        ParametreImprimente.getObj().dispose();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        ParametreImprimente.getObj().setVisible(true);
        ParametreConnection.getObj().dispose();
        Aide.getObj().dispose();
        MiseAJour.getObj().dispose();
        APropos.getObj().dispose();
        ConnPort.getObj().dispose();
        CodeLicence.getObj().dispose();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        /**
         * License licens = licenseKeyGUI.checkLicense();
         *
         * if (licens != null) { if (licens.getValidationStatus() ==
         * ValidationStatus.LICENSE_VALID) { /** License is valid, so run your
         * software product.
         */

        /**
         * But If license require activation, check if license is activated. If
         * license is not activated check the activation period. If allowed
         * activation period is expired but user still did not complete
         * activation, display license GUI for user to complete activation.
         */
        /* if (licens.isActivationRequired() && licens.getLicenseActivationDaysRemaining(null) == 0) {
         JOptionPane.showMessageDialog(null, "Your license activation period is over, activate on the next window.", "License Activation", WIDTH);

         // This is an example, and we just disable main file menu.
         //  filejMenu.setEnabled(false);

         licenseKeyGUI.setVisible(true);
         }
         } else {
         /**
         * If license status is not valid, display message to display
         * license status; and disable some software features etc.
         */
        /* JOptionPane.showMessageDialog(null, "Your license is not valid (" + licens.getValidationStatus() + ")", "License Error", WIDTH);

         // This is an example, and we just disable main file menu.
         // filejMenu.setEnabled(false);

         licenseKeyGUI.setVisible(true);
         }
         } else {
         JOptionPane.showMessageDialog(null, "You should have a valid license to run this software.", "License Error", JOptionPane.ERROR_MESSAGE);

         // This is an example, and we just disable main file menu.
         //filejMenu.setEnabled(false);

         licenseKeyGUI.setVisible(true);
         } */
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // licenseKeyGUI.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Description;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}

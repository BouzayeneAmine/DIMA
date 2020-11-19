package pkgnew.app;

import BDD.DbConnection;
import BDD.Parametre;
import com.license4j.License;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    ResultSet rs;
    DbConnection db;
    String username1, password1, hak;

    public Login() throws SQLException {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        initComponents();

        correctAllNumbers();

        date();
        datecourante();
        this.setUndecorated(true);
        NomUtilisateur();
        // this.setIconImage(new ImageIcon(getClass().getResource("/Images/Logo.png")).getImage());
    }

    public void correctAllNumbers() {
        String updateSQL = "UPDATE historique set "
                + "                Prix_vente = replace (Prix_vente, ',', '.'),"
                + "                total_base = replace (total_base, ',', '.'),"
                + "                total = replace (total, ',', '.')";
        db.exécutionUpdate(updateSQL);
        db.closeconnexion();

        updateSQL = "UPDATE produit set "
                + "Prix_vente = replace (Prix_vente, ',', '.'),"
                + "Prix_base = replace (Prix_base, ',', '.')";
        db.exécutionUpdate(updateSQL);
        db.closeconnexion();

        updateSQL = "UPDATE produit_stock set "
                + "                Prix_vente = replace (Prix_vente, ',', '.'),"
                + "                Prix_base = replace (Prix_base, ',', '.')";
        db.exécutionUpdate(updateSQL);
        db.closeconnexion();

        System.out.println("correct all Numbers");
    }

    public void date() {
        java.util.Date s = new java.util.Date();
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

    void NomUtilisateur() throws SQLException {
        String b[] = {"Login"};
        rs = db.querySelect(b, "Utilisateur");

        while (rs.next()) {
            String indicateur = rs.getString("Login");
            txt_username.addItem(indicateur);
        }
        db.closeconnexion();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinnerDateEditor1 = new com.toedter.calendar.JSpinnerDateEditor();
        Heure = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        Heure1 = new javax.swing.JLabel();
        Date1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        Description = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_username = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("LOGIN");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Heure.setBackground(new java.awt.Color(22, 41, 153));
        Heure.setForeground(new java.awt.Color(30, 130, 82));
        Heure.setText("jLabel7");
        getContentPane().add(Heure, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        Date.setBackground(new java.awt.Color(22, 41, 153));
        Date.setForeground(new java.awt.Color(30, 130, 82));
        Date.setText("jLabel7");
        getContentPane().add(Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        Heure1.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        Heure1.setForeground(new java.awt.Color(30, 130, 82));
        Heure1.setText("22:10");
        getContentPane().add(Heure1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Date1.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        Date1.setForeground(new java.awt.Color(30, 130, 82));
        Date1.setText("31-12-2019");
        getContentPane().add(Date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        jButton1.setBackground(new java.awt.Color(30, 130, 82));
        jButton1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/loginbtn.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 560, 60));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/passwordbg.png"))); // NOI18N
        jLabel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jLabel4KeyTyped(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, -1, 40));

        txt_password.setBackground(new java.awt.Color(30, 130, 82));
        txt_password.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        txt_password.setForeground(new java.awt.Color(255, 250, 240));
        txt_password.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_password.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_password.setCaretColor(new java.awt.Color(30, 130, 82));
        txt_password.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_password.setMargin(new java.awt.Insets(2, 5, 2, 2));
        txt_password.setOpaque(false);
        txt_password.setSelectedTextColor(new java.awt.Color(30, 130, 82));
        txt_password.setSelectionColor(new java.awt.Color(255, 250, 240));
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
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 269, 190, 30));

        Description.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        Description.setForeground(new java.awt.Color(255, 255, 255));
        Description.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(Description, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 560, 20));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 250, 240));
        jLabel2.setText("Mot de passe:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 150, 40));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 250, 240));
        jLabel3.setText("Utilisateur:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 120, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/Sans titre-1.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, -10, 160, 90));

        txt_username.setBackground(new java.awt.Color(30, 130, 82));
        txt_username.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        txt_username.setForeground(new java.awt.Color(30, 130, 82));
        txt_username.setOpaque(false);
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 220, 40));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/MAGAZI.png"))); // NOI18N
        jLabel5.setFocusable(false);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 560, 80));

        jLabel1.setBackground(new java.awt.Color(30, 130, 82));
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -20, 590, 570));

        jMenuBar1.setBackground(new java.awt.Color(22, 41, 153));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorderPainted(false);
        jMenuBar1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jMenuBar1.setOpaque(false);

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

        setSize(new java.awt.Dimension(577, 541));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        rs = db.querySelectAll("utilisateur", "Login='" + txt_username.getSelectedItem().toString() + "' and Password='" + txt_password.getText() + "'");

        try {
            while (rs.next()) {
                username1 = rs.getString("Login");
                password1 = rs.getString("Password");
                hak = rs.getString("type");

            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
//Date        
        java.util.Date s = new java.util.Date();
        SimpleDateFormat d = new SimpleDateFormat("YYYY-MM-dd");
        SimpleDateFormat h = new SimpleDateFormat("HH:mm:ss");
        String txDate = d.format(s);
        String txHeure = h.format(s);
//Nom du hote
        String hostName = null;
        try {
            final InetAddress addr = InetAddress.getLocalHost();
            hostName = new String(addr.getHostName());
        } catch (final Exception e) {
            hostName = ".";
        }
        if (username1 == null && password1 == null) {
            getToolkit().beep();

//Afficher le message            
            JOptionPane.showMessageDialog(this, "Le nom utilisateur ou le mots de pass est incorrect");
        } else {
            if (hak.equals("directeur")) {
                db.closeconnexion();
                POS a = new POS(username1);
                a.setVisible(true);
                this.dispose();
            } else if (hak.equals("Utilisateur")) {
                db.closeconnexion();
                POS k = new POS(username1);
                k.setVisible(true);
                this.dispose();
            }

//Ajouter le connexion
            String[] colon = {"Login", "Type", "Date", "Heure", "Nom_PC"};
            String[] inf = {username1, hak, txDate, txHeure, hostName};
            System.out.println(db.queryInsert("connexion", colon, inf));
            db.closeconnexion();
            // action logout
            String user = txt_username.getSelectedItem().toString();
            String action = "log in";

            String Description = "";

            String[] actions = {"user", "type_action", "description", "Date", "Heure"};
            String[] inf3 = {user, action, Description, txDate, txHeure};
            System.out.println(db.queryInsert("action", actions, inf3));
            db.closeconnexion();
            //..................................

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment Quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
        //if (rep == JOptionPane.YES_OPTION) {
        db.closeconnexion();
        System.exit(0);
        // }
    }//GEN-LAST:event_formWindowClosing

    private void txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            rs = db.querySelectAll("utilisateur", "Login='" + txt_username.getSelectedItem() + "' and Password='" + txt_password.getText() + "'");

            try {
                while (rs.next()) {
                    username1 = rs.getString("Login");
                    password1 = rs.getString("Password");
                    hak = rs.getString("type");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (username1 == null && password1 == null) {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Le nom utilisateur ou le mots de pass est incorrect");
            } else {
                if (hak.equals("directeur")) {
                    db.closeconnexion();
                    POS a = new POS(username1);
                    a.setVisible(true);
                    this.dispose();
                } else if (hak.equals("Utilisateur")) {
                    db.closeconnexion();
                    POS k = new POS(username1);
                    k.setVisible(true);
                    this.dispose();
                }

                // action logout
                String user = txt_username.getSelectedItem().toString();
                String action = "log in";
                String d = Date.getText();
                String h = Heure.getText();
                String Description = "";

                String[] actions = {"user", "type_action", "description", "Date", "Heure"};
                String[] inf3 = {user, action, Description, d, h};
                System.out.println(db.queryInsert("action", actions, inf3));
                db.closeconnexion();
                //..................................
            }
        }
    }//GEN-LAST:event_txt_passwordKeyPressed

    private void txt_passwordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_passwordMouseExited
        Description.setText("");
    }//GEN-LAST:event_txt_passwordMouseExited

    private void txt_passwordMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_passwordMouseMoved
        Description.setText("Veuillez entrer votre mot de passe pour accéder à votre session");
    }//GEN-LAST:event_txt_passwordMouseMoved

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        //APropos.getObj().setVisible(true);
        // License.getObj().dispose();
        //Aide.getObj().dispose();
        //MiseAJour.getObj().dispose();
        ParametreConnection.getObj().dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // MiseAJour.getObj().setVisible(true);
        //License.getObj().dispose();
        //Aide.getObj().dispose();
        ParametreConnection.getObj().dispose();
        // APropos.getObj().dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //Aide.getObj().setVisible(true);
        //icense.getObj().dispose();
        ParametreConnection.getObj().dispose();
        // MiseAJour.getObj().dispose();
        //APropos.getObj().dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        CodeLicence.getObj().setVisible(true);
        ParametreConnection.getObj().dispose();
        // Aide.getObj().dispose();
        // MiseAJour.getObj().dispose();
        // APropos.getObj().dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        ParametreConnection.getObj().setVisible(true);
        ConnPort.getObj().dispose();
        //License.getObj().dispose();
        //getObj().dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        ConnPort.getObj().setVisible(true);
        ParametreConnection.getObj().dispose();
        // License.getObj().dispose();
        // Aide.getObj().dispose();
        // MiseAJour.getObj().dispose();
        // APropos.getObj().dispose();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
            Login l = new Login();
            l.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed

    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //  if (JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment Quitter ?", " Quitter?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
        db.closeconnexion();
        System.exit(0);
        // }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jLabel4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4KeyTyped

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Login().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date;
    private javax.swing.JLabel Date1;
    private javax.swing.JLabel Description;
    private javax.swing.JLabel Heure;
    private javax.swing.JLabel Heure1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private com.toedter.calendar.JSpinnerDateEditor jSpinnerDateEditor1;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JComboBox txt_username;
    // End of variables declaration//GEN-END:variables
}

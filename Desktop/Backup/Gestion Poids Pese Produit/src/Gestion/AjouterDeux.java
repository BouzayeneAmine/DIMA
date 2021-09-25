package Gestion;

import BDD.Conector;
import BDD.DbConnection;
import BDD.Parametre;
import BDD.ResultSetTableModel;
import BDD.VirtualKeyboard;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import static java.lang.Math.abs;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class AjouterDeux extends javax.swing.JInternalFrame {

    ResultSet rs;
    DbConnection db;
    public String getClientCode;
    public String getClientNom;
    public String getClientAdress;
    public String getProduitCode;
    public String getProduitDesignation;
    public String getProduitLot;
    public String getProduitPrix;
    public String getProduitCoef;
    public String getCamionType;
    public String getCamionTarre;
    public String getCamionImmatricule;
    public String getFourNom;
    public String getFourCode;
    public String getFourAdress;
    public String getTranNom;
    public String getTranCode;
    public String getTranAdress;

    public AjouterDeux() {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        initComponents();
        removeTitleBar();
        jRadioClient.setSelected(true);
    }

    void removeTitleBar() {
        putClientProperty("Ajouter.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }

    public void postClient() {
        this.clientCode.setText(getClientCode);
        this.clientNom.setText(getClientNom);
        this.clientAdress.setText(getClientAdress);
    }

    public void postProduit() {
        this.produitDesignation.setText(getProduitDesignation);
        this.produitCode.setText(getProduitCode);
        this.produitLot.setText(getProduitLot);
        this.produitPrix.setText(getProduitPrix);
        this.prouitCoefficient.setText(getProduitCoef);
    }

    public void postCamion() {
        this.CamType.setText(getCamionType);
        this.CamTare.setText(getCamionTarre);
        this.CamImm.setText(getCamionImmatricule);
    }

    public void postFournisseur() {
        this.clientCode.setText(getFourCode);
        this.clientNom.setText(getFourNom);
        this.clientAdress.setText(getFourAdress);
    }

    public void postTransporteur() {
        this.clientCode.setText(getTranCode);
        this.clientNom.setText(getTranNom);
        this.clientAdress.setText(getTranAdress);
    }

    public void table1() {
        String a[] = {"Id", "Numero"};
        rs = db.querySelect(a, "nbon");
        jTable1.setModel(new ResultSetTableModel(rs));

    }

    public void table2() {
        String a[] = {"Id", "Numero", "Code", "Produit", "Immatricule", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
        rs = db.querySelect(a, "fournisseur_valide");
        jTable3.setModel(new ResultSetTableModel(rs));
    }

    public void table3() {
        String a[] = {"Id", "Numero", "Code", "Produit", "Immatricule", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
        rs = db.querySelect(a, "client_valide");
        jTable4.setModel(new ResultSetTableModel(rs));
    }

    public void table4() {
        String a[] = {"Id", "Numero", "Code", "Produit", "Immatricule", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
        rs = db.querySelect(a, "transporteur_valide");
        jTable5.setModel(new ResultSetTableModel(rs));
    }

    public void table6() {
        String a[] = {"Id", "Numero", "Code_produit", "code_type", "Immatricule", "Date_entree", "Heure_entree", "Premier_pesage", "Date_sortie", "Heure_sortie", "Deuxieme_pesage", "Masse_nette"};
        rs = db.querySelect(a, "produit_entrant");
        jTable11.setModel(new ResultSetTableModel(rs));
    }

    public void table7() {
        String a[] = {"Id", "Numero", "Code_produit", "code_type", "Immatricule", "Date_entree", "Heure_entree", "Premier_pesage", "Date_sortie", "Heure_sortie", "Deuxieme_pesage", "Masse_nette"};
        rs = db.querySelect(a, "produit_sortant");
        jTable12.setModel(new ResultSetTableModel(rs));
    }

    void actualiser() {
        Numero.setText("");
        clientCode.setText("");
        produitCode.setText("");
        CamImm.setText("");
        txtDate.setText("");
        txtHeure.setText("");
        poidTarre.setText("");
        txDate.setText("");
        txHeure.setText("");
        poidBrute.setText("");
        poidNette.setText("");
        toFloat.setText("");
        clientNom.setText("");
        clientAdress.setText("");
        produitDesignation.setText("");
        produitLot.setText("");
        produitPrix.setText("");
        prouitCoefficient.setText("");
        CamType.setText("");
        CamTare.setText("");
    }

    public void toPDF() {
        Conector cn = new Conector();
        try {
            JasperReport jasperReport = null;
            InputStream path = this.getClass().getResourceAsStream("BonDeSortie.jrxml");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap parameters = new HashMap();
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn.getConexion());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(ProgrammeDeux.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Numero = new javax.swing.JTextField();
        poidNette = new javax.swing.JTextField();
        toFloat = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jRadioClient = new javax.swing.JRadioButton();
        jRadioFournisseur = new javax.swing.JRadioButton();
        clientCode = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        clientNom = new javax.swing.JTextField();
        clientAdress = new javax.swing.JTextField();
        jRadioFournisseur1 = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        produitDesignation = new javax.swing.JTextField();
        produitLot = new javax.swing.JTextField();
        produitCode = new javax.swing.JTextField();
        produitPrix = new javax.swing.JTextField();
        prouitCoefficient = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        CamImm = new javax.swing.JTextField();
        CamType = new javax.swing.JTextField();
        CamTare = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        poidBrute = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        poidTarre = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtDate = new javax.swing.JFormattedTextField();
        txDate = new javax.swing.JFormattedTextField();
        txtHeure = new javax.swing.JFormattedTextField();
        txHeure = new javax.swing.JFormattedTextField();

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

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(jTable9);

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane10.setViewportView(jTable10);

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane11.setViewportView(jTable11);

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane12.setViewportView(jTable12);

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

        jTable1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
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

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(jRadioClient);
        jRadioClient.setText("Client");
        jRadioClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioClientActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioFournisseur);
        jRadioFournisseur.setText("Fournisseur");
        jRadioFournisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioFournisseurActionPerformed(evt);
            }
        });

        clientCode.setEditable(false);

        jLabel9.setText("Client");

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        clientNom.setEditable(false);

        clientAdress.setEditable(false);

        buttonGroup1.add(jRadioFournisseur1);
        jRadioFournisseur1.setText("Transporteur");
        jRadioFournisseur1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioFournisseur1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jRadioClient, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clientCode)))
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jRadioFournisseur, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(clientNom))
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clientAdress)
                    .addComponent(jRadioFournisseur1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioClient)
                            .addComponent(jRadioFournisseur)
                            .addComponent(jRadioFournisseur1)))
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientCode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientNom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setText("Produit");

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        produitDesignation.setEditable(false);

        produitLot.setEditable(false);

        produitCode.setEditable(false);

        produitPrix.setEditable(false);

        prouitCoefficient.setEditable(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(43, 43, 43)
                        .addComponent(produitCode, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(produitLot, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(produitPrix, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prouitCoefficient, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(produitDesignation)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produitDesignation, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(produitCode, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(jLabel22)
                            .addComponent(produitLot, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(produitPrix, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                        .addGap(2, 2, 2))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(prouitCoefficient))))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setText("Camion");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        CamImm.setEditable(false);

        CamType.setEditable(false);

        CamTare.setEditable(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(CamType, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CamTare, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
                    .addComponent(CamImm))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CamImm)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CamTare, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CamType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(2, 2, 2))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/confirm.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(0, 51, 102));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Ajouter");
        jLabel13.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel25.setText("Date premier");

        jLabel26.setText("Heure premier");

        jLabel27.setText("Deuxieme pesage");

        jLabel28.setText("Date deuxieme");

        poidBrute.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                poidBruteKeyTyped(evt);
            }
        });

        jLabel24.setText("Premier pesage");

        jLabel29.setText("Heure deuxieme");

        poidTarre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                poidTarreMouseClicked(evt);
            }
        });
        poidTarre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                poidTarreKeyTyped(evt);
            }
        });

        jLabel31.setText("KG");

        jLabel30.setText("KG");

        try {
            txtDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        try {
            txDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/#### ")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        try {
            txtHeure.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtHeure.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        try {
            txHeure.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txHeure.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)))
                    .addComponent(jLabel29)
                    .addComponent(jLabel28))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(poidBrute, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txHeure, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtHeure, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDate, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(poidTarre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(poidTarre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24))
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtHeure, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(poidBrute, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27))
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txHeure, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioClientActionPerformed
        jLabel9.setText("Client");
        clientCode.setText("");
        clientNom.setText("");
        clientAdress.setText("");
    }//GEN-LAST:event_jRadioClientActionPerformed

    private void jRadioFournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioFournisseurActionPerformed
        jLabel9.setText("Fournisseur");
        clientCode.setText("");
        clientNom.setText("");
        clientAdress.setText("");
    }//GEN-LAST:event_jRadioFournisseurActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if (jRadioClient.isSelected()) {
            FrameClientAd ma = new FrameClientAd(this);
            ma.setVisible(true);
        } else if (jRadioFournisseur.isSelected()) {
            FrameFournisseurAd fou = new FrameFournisseurAd(this);
            fou.setVisible(true);
        } else {
            FrameTransporteurAd fou = new FrameTransporteurAd(this);
            fou.setVisible(true);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jRadioFournisseur1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioFournisseur1ActionPerformed
        jLabel9.setText("Transporteur");
        clientCode.setText("");
        clientNom.setText("");
        clientAdress.setText("");
    }//GEN-LAST:event_jRadioFournisseur1ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        FrameProduitAd ma = new FrameProduitAd(this);
        ma.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (CamImm.getText().equals("") || produitCode.getText().equals("") || clientCode.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un camion et un produit et un type", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else if (poidTarre.getText().equals("") || txtDate.getText().equals("") || txtHeure.getText().equals("")
                || poidBrute.getText().equals("") || txDate.getText().equals("") || txHeure.getText().equals("")) {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez entrer les informations", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            table1();

            Numero.setText(String.valueOf(jTable1.getValueAt(0, 1)));
            int nbr = Integer.parseInt(Numero.getText());
            int numero = nbr + 1;
            Numero.setText(String.valueOf(numero));

            toFloat.setText(Float.toString(abs(Float.parseFloat(poidBrute.getText()) - Float.parseFloat(poidTarre.getText()))));
            DecimalFormat df = new DecimalFormat("0.000");
            df.setRoundingMode(RoundingMode.DOWN);
            float bb = (Float.parseFloat(toFloat.getText()));
            String money = df.format(bb).replace(',', '.');
            poidNette.setText(money);
            
            if (jLabel9.getText().equals("Client")) {
                String[] colon = {"Numero", "Code", "Produit", "Immatricule", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
                String[] inf = {Numero.getText(), clientCode.getText(), produitCode.getText(), CamImm.getText(),
                    txtDate.getText(), txtHeure.getText(), poidTarre.getText(), txDate.getText(), txHeure.getText(), poidBrute.getText(),
                    poidNette.getText()};
                System.out.println(db.queryInsert("client_valide", colon, inf));
                String[] colonn = {"Numero", "Code_produit", "code_type", "Immatricule", "Date_entree", "Heure_entree", "Premier_pesage", "Date_sortie", "Heure_sortie", "Deuxieme_pesage", "Masse_nette"};
                String[] inff = {Numero.getText(), produitCode.getText(), clientCode.getText(), CamImm.getText(),
                    txtDate.getText(), txtHeure.getText(), poidTarre.getText(), txDate.getText(), txHeure.getText(), poidBrute.getText(),
                    poidNette.getText()};
                System.out.println(db.queryInsert("produit_sortant", colonn, inff));

                String[] colon3 = {"Numero", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                    "Lot_produit", "Prix_produit", "Coefficient_produit", "Camion_Immatricule", "Camion_type", "Camion_tare",
                    "Premier_pesage", "Premier_date", "Premier_Heure", "Deuxieme_pesage", "Deuxieme_date", "Deuxieme_heure", "Masse_nette"};
                String[] inf3 = {Numero.getText(), jLabel9.getText(), clientCode.getText(), clientNom.getText(), clientAdress.getText(),
                    produitCode.getText(), produitDesignation.getText(), produitLot.getText(), produitPrix.getText(), prouitCoefficient.getText(),
                    CamImm.getText(), CamType.getText(), CamTare.getText(), poidTarre.getText(), txtDate.getText(), txtHeure.getText(),
                    poidBrute.getText(), txDate.getText(), txHeure.getText(), poidNette.getText()};
                System.out.println(db.queryUpdate("impression_bon", colon3, inf3, "id='" + 0 + "'"));
                
                JOptionPane.showMessageDialog(this, "Votre Client a été Sortie avec succès! Merci d'imprimer le bon de sortie");
                toPDF();
                
                String[] colonnn = {"Numero"};
                String[] infff = {Numero.getText()};
                System.out.println(db.queryUpdate("nbon", colonnn, infff, "id='" + "1" + "'"));

            } else if (jLabel9.getText().equals("Transporteur")) {
                String[] colon = {"Numero", "Code", "Produit", "Immatricule", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
                String[] inf = {Numero.getText(), clientCode.getText(), produitCode.getText(), CamImm.getText(),
                    txtDate.getText(), txtHeure.getText(), poidTarre.getText(), txDate.getText(), txHeure.getText(), poidBrute.getText(),
                    poidNette.getText()};
                System.out.println(db.queryInsert("transporteur_valide", colon, inf));
                String[] colonn = {"Numero", "Code_produit", "code_type", "Immatricule", "Date_entree", "Heure_entree", "Premier_pesage", "Date_sortie", "Heure_sortie", "Deuxieme_pesage", "Masse_nette"};
                String[] inff = {Numero.getText(), produitCode.getText(), clientCode.getText(), CamImm.getText(),
                    txtDate.getText(), txtHeure.getText(), poidTarre.getText(), txDate.getText(), txHeure.getText(), poidBrute.getText(),
                    poidNette.getText()};
                System.out.println(db.queryInsert("produit_sortant", colonn, inff));

                String[] colon3 = {"Numero", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                    "Lot_produit", "Prix_produit", "Coefficient_produit", "Camion_Immatricule", "Camion_type", "Camion_tare",
                    "Premier_pesage", "Premier_date", "Premier_Heure", "Deuxieme_pesage", "Deuxieme_date", "Deuxieme_heure", "Masse_nette"};
                String[] inf3 = {Numero.getText(), jLabel9.getText(), clientCode.getText(), clientNom.getText(), clientAdress.getText(),
                    produitCode.getText(), produitDesignation.getText(), produitLot.getText(), produitPrix.getText(), prouitCoefficient.getText(),
                    CamImm.getText(), CamType.getText(), CamTare.getText(), poidTarre.getText(), txtDate.getText(), txtHeure.getText(),
                    poidBrute.getText(), txDate.getText(), txHeure.getText(), poidNette.getText()};
                System.out.println(db.queryUpdate("impression_bon", colon3, inf3, "id='" + 0 + "'"));
                
                JOptionPane.showMessageDialog(this, "Votre Transporteur a été Sortie avec succès! Merci d'imprimer le bon de sortie");
                toPDF();
                
                String[] colonnn = {"Numero"};
                String[] infff = {Numero.getText()};
                System.out.println(db.queryUpdate("nbon", colonnn, infff, "id='" + "1" + "'"));

            } else if (jLabel9.getText().equals("Fournisseur")) {
                String[] colon = {"Numero", "Code", "Produit", "Immatricule", "Date_premier", "Heure_premier", "Premier_pesage", "Date_deuxieme", "Heure_deuxieme", "Deuxieme_pesage", "Masse_nette"};
                String[] inf = {Numero.getText(), clientCode.getText(), produitCode.getText(), CamImm.getText(),
                    txtDate.getText(), txtHeure.getText(), poidTarre.getText(), txDate.getText(), txHeure.getText(), poidBrute.getText(),
                    poidNette.getText()};
                System.out.println(db.queryInsert("fournisseur_valide", colon, inf));
                String[] colonn = {"Numero", "Code_produit", "code_type", "Immatricule", "Date_entree", "Heure_entree", "Premier_pesage", "Date_sortie", "Heure_sortie", "Deuxieme_pesage", "Masse_nette"};
                String[] inff = {Numero.getText(), produitCode.getText(), clientCode.getText(), CamImm.getText(),
                    txtDate.getText(), txtHeure.getText(), poidTarre.getText(), txDate.getText(), txHeure.getText(), poidBrute.getText(),
                    poidNette.getText()};
                System.out.println(db.queryInsert("produit_entrant", colonn, inff));

                String[] colon3 = {"Numero", "Type", "Code_type", "Nom_type", "Adresse_type", "Code_produit", "Designation_produit",
                    "Lot_produit", "Prix_produit", "Coefficient_produit", "Camion_Immatricule", "Camion_type", "Camion_tare",
                    "Premier_pesage", "Premier_date", "Premier_Heure", "Deuxieme_pesage", "Deuxieme_date", "Deuxieme_heure", "Masse_nette"};
                String[] inf3 = {Numero.getText(), jLabel9.getText(), clientCode.getText(), clientNom.getText(), clientAdress.getText(),
                    produitCode.getText(), produitDesignation.getText(), produitLot.getText(), produitPrix.getText(), prouitCoefficient.getText(),
                    CamImm.getText(), CamType.getText(), CamTare.getText(), poidTarre.getText(), txtDate.getText(), txtHeure.getText(),
                    poidBrute.getText(), txDate.getText(), txHeure.getText(), poidNette.getText()};
                System.out.println(db.queryUpdate("impression_bon", colon3, inf3, "id='" + 0 + "'"));
                
                JOptionPane.showMessageDialog(this, "Votre Fournisseur a été sortie avec succès! Merci d'imprimer le bon de pesage");
                toPDF();
                
                String[] colonnn = {"Numero"};
                String[] infff = {Numero.getText()};
                System.out.println(db.queryUpdate("nbon", colonnn, infff, "id='" + "1" + "'"));

            } else {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "erreur");
            }
            actualiser();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void poidTarreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_poidTarreKeyTyped
        char car = evt.getKeyChar();
        if (!(Character.isDigit(car)) || (car == KeyEvent.VK_BACK_SPACE)) {
            if (car != '.') {
                getToolkit().beep();
                evt.consume();
            }
        }
    }//GEN-LAST:event_poidTarreKeyTyped

    private void poidBruteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_poidBruteKeyTyped
        char car = evt.getKeyChar();
        if (!(Character.isDigit(car)) || (car == KeyEvent.VK_BACK_SPACE)) {
            if (car != '.') {
                getToolkit().beep();
                evt.consume();
            }
        }
    }//GEN-LAST:event_poidBruteKeyTyped

    private void poidTarreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_poidTarreMouseClicked
        
    }//GEN-LAST:event_poidTarreMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CamImm;
    private javax.swing.JTextField CamTare;
    private javax.swing.JTextField CamType;
    private javax.swing.JTextField Numero;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField clientAdress;
    private javax.swing.JTextField clientCode;
    private javax.swing.JTextField clientNom;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioClient;
    private javax.swing.JRadioButton jRadioFournisseur;
    private javax.swing.JRadioButton jRadioFournisseur1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField poidBrute;
    private javax.swing.JTextField poidNette;
    private javax.swing.JTextField poidTarre;
    private javax.swing.JTextField produitCode;
    public javax.swing.JTextField produitDesignation;
    public javax.swing.JTextField produitLot;
    public javax.swing.JTextField produitPrix;
    public javax.swing.JTextField prouitCoefficient;
    private javax.swing.JTextField toFloat;
    private javax.swing.JFormattedTextField txDate;
    private javax.swing.JFormattedTextField txHeure;
    private javax.swing.JFormattedTextField txtDate;
    private javax.swing.JFormattedTextField txtHeure;
    // End of variables declaration//GEN-END:variables
}

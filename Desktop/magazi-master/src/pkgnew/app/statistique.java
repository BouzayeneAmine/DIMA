/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnew.app;

import BDD.ConnectComPort;
import BDD.DbConnection;
import BDD.Parametre;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.JCommon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import static java.lang.Thread.sleep;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

/**
 *
 * @author asuss
 */
public final class statistique extends javax.swing.JFrame {

    public static statistique obj = null;
    DbConnection db;
    ConnectComPort dc;

    ResultSet rs;
    JFreeChart bestsellersPie;
    JFreeChart WeeklyBarChart;
    private JLabel Heure = new JLabel();
    private JLabel Date = new JLabel();
    DecimalFormat df = new DecimalFormat();
    DecimalFormat intf = new DecimalFormat();

    ChartPanel cpQte, cpBft, bp;

    public statistique(String username1) {
        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);
        dc = new ConnectComPort();
        db.connexionDatabase();

        intf.setMaximumFractionDigits(0);
        df.setMaximumFractionDigits(3);
        df.setMinimumFractionDigits(3);

        Heure1 = new JLabel();
        Date1 = new JLabel();

        date();
        datecourante();

        initComponents();
        this.setDefaultCloseOperation(0);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        utilisateur.setText(username1);

        CemoisLabel.show();
        MoisPrecLabel.hide();

        cpQte = drawBestSellerByQuantityChart("this month");
        cpBft = drawBestSellerByBenefitChart("this month");

        bp = drawWeeklyBarChart("this month");

        PieChartPanelQte.setLayout(new BorderLayout());
        PieChartPanelQte.add(cpQte, BorderLayout.CENTER);
        PieChartPanelQte.setBackground(new Color(255, 250, 240));

        PieChartPanelBft.setLayout(new BorderLayout());
        PieChartPanelBft.add(cpBft, BorderLayout.CENTER);
        PieChartPanelBft.setBackground(new Color(255, 250, 240));

        BarChartPanel.setLayout(new BorderLayout());
        BarChartPanel.add(bp, BorderLayout.CENTER);
        BarChartPanel.setBackground(new Color(255, 250, 240));

        String requeteSVV = "SELECT SUM( Prix_vente * Quantite )\n"
                + "FROM `produit_stock`\n";

        String requeteSVA = "SELECT SUM( Prix_base * Quantite )\n"
                + "FROM `produit_stock`\n";

        rs = db.exécutionQuery(requeteSVV);
        try {
            rs.next();
            Double SVVNum = Double.parseDouble(rs.getString(1));
            StockVVNum.setText(df.format(SVVNum));
        } catch (Exception e) {
        }

        rs = db.exécutionQuery(requeteSVA);
        try {
            rs.next();
            Double SVANum = Double.parseDouble(rs.getString(1));
            StockVANum.setText(df.format(SVANum));
        } catch (Exception e) {
        }

    }

    public ChartPanel drawBestSellerByBenefitChart(String month) {

        //20/12/2020
        Date s = new Date();
        SimpleDateFormat d = new SimpleDateFormat("dd-MM-YYYY");
        String date = d.format(s);

        String mois = date.substring(3, 5);
        String annee = date.substring(6);
        String monthString = mois + "-" + annee;

        if (month.equals("last month")) {
            int m = Integer.parseInt(mois) - 1;
            int y = Integer.parseInt(annee);
            if (m <= 0) {
                m = 12;
                y = y - 1;
            }
            DecimalFormat form = new DecimalFormat("00");
            mois = form.format(m);
            annee = String.valueOf(y);
            monthString = mois + "-" + annee;
        }

        String q = "SELECT Nom, SUM( total - total_base ) AS TotalBenefit\n"
                + "FROM historique\n"
                + "WHERE "
                + "Date_Sortie LIKE '__-" + monthString + "' "
                + "GROUP BY Nom\n"
                + "ORDER BY SUM( total - total_base ) DESC\n"
                + "LIMIT 0 , 5";

        DefaultPieDataset dataset = new DefaultPieDataset();

        rs = db.exécutionQuery(q);

        ArrayList<String> noms = new ArrayList<String>();
        try {
            while (rs.next()) {
                dataset.setValue(rs.getString(1), Double.parseDouble(rs.getString(2)));
                System.err.println(rs.getString(1) + "  " + rs.getString(2));
                noms.add(rs.getString("Nom"));
            }

            try {
                Net1.setText("<html><p>" + noms.get(0) + ":  " + df.format(Double.parseDouble(String.valueOf(dataset.getValue(0)))) + "  dt</p></html>");
            } catch (Exception e) {
                System.err.println(e.toString());
                Net1.setText("");
            }

            try {
                Net2.setText("<html><p>" + noms.get(1) + ":   " + df.format(Double.parseDouble(String.valueOf(dataset.getValue(1)))) + "  dt</p></html>");
            } catch (Exception e) {
                Net2.setText("");
            }

            try {
                Net3.setText("<html><p>" + noms.get(2) + ":   " + df.format(Double.parseDouble(String.valueOf(dataset.getValue(2)))) + "  dt</p></html>");
            } catch (Exception e) {
                Net3.setText("");
            }

            try {
                Net4.setText("<html><p>" + noms.get(3) + ":   " + df.format(Double.parseDouble(String.valueOf(dataset.getValue(3)))) + "  dt</p></html>");
            } catch (Exception e) {
                Net4.setText("");
            }

            try {
                Net5.setText("<html><p>" + noms.get(4) + ":   " + df.format(Double.parseDouble(String.valueOf(dataset.getValue(4)))) + "  dt</p></html>");
            } catch (Exception e) {
                Net5.setText("");
            }

        } catch (SQLException ex) {
            Logger.getLogger(statistique.class.getName()).log(Level.SEVERE, null, ex);
        }

        bestsellersPie = ChartFactory.createPieChart(null, dataset, false, true, true);
        bestsellersPie.setBorderPaint(new Color(255, 250, 240));
        bestsellersPie.setBorderStroke(new BasicStroke(0));
        bestsellersPie.setBackgroundPaint(new Color(255, 250, 240));
        PiePlot pieplot = (PiePlot) bestsellersPie.getPlot();

        pieplot.setSectionPaint(0, new Color(5, 45, 26));
        pieplot.setSectionPaint(1, new Color(18, 79, 50));
        pieplot.setSectionPaint(2, new Color(30, 130, 82));
        pieplot.setSectionPaint(3, new Color(68, 207, 140));
        pieplot.setSectionPaint(4, new Color(130, 245, 210));

        pieplot.setBackgroundPaint(new Color(255, 250, 240));
        pieplot.setBaseSectionOutlinePaint(new Color(5, 45, 26));
        pieplot.setOutlinePaint(null);

        pieplot.setOutlineStroke(new BasicStroke(0));
        ChartPanel bestSellersChartPanel = new ChartPanel(bestsellersPie);
        ///bestSellersChartPanel.setBackground(new Color(255, 250, 240)); 

        return bestSellersChartPanel;
    }

    public ChartPanel drawBestSellerByQuantityChart(String month) {

        Date s = new Date();
        SimpleDateFormat d = new SimpleDateFormat("dd-MM-YYYY");
        String date = d.format(s);

        String mois = date.substring(3, 5);
        String annee = date.substring(6);
        String monthString = mois + "-" + annee;

        if (month.equals("last month")) {
            int m = Integer.parseInt(mois) - 1;
            int y = Integer.parseInt(annee);
            if (m <= 0) {
                m = 12;
                y = y - 1;
            }
            DecimalFormat form = new DecimalFormat("00");
            mois = form.format(m);
            annee = String.valueOf(y);
            monthString = mois + "-" + annee;
        }

        String q = "SELECT Nom, SUM( Quantite ) AS TotalQuantity\n"
                + "FROM historique\n"
                + "WHERE "
                + "Date_Sortie LIKE '__-" + monthString + "' "
                + "GROUP BY Nom\n"
                + "ORDER BY SUM( Quantite ) DESC\n"
                + "LIMIT 0 , 5";

        DefaultPieDataset dataset = new DefaultPieDataset();

        rs = db.exécutionQuery(q);

        ArrayList<String> noms = new ArrayList<String>();

        try {
            while (rs.next()) {
                dataset.setValue(rs.getString(1), Integer.parseInt(rs.getString(2)));
                noms.add(rs.getString(1));
            }

            try {
                Qte1.setText("<html><p>" + noms.get(0) + ":   " + intf.format(Double.parseDouble(String.valueOf(dataset.getValue(0)))) + "  dt</p></html>");
            } catch (Exception e) {
                Qte1.setText("");
            }

            try {
                Qte2.setText("<html><p>" + noms.get(1) + ":   " + intf.format(Double.parseDouble(String.valueOf(dataset.getValue(1)))) + "  dt</p></html>");
            } catch (Exception e) {
                Qte2.setText("");
            }

            try {
                Qte3.setText("<html><p>" + noms.get(2) + ":   " + intf.format(Double.parseDouble(String.valueOf(dataset.getValue(2)))) + "  dt</p></html>");
            } catch (Exception e) {
                Qte3.setText("");
            }

            try {
                Qte4.setText("<html><p>" + noms.get(3) + ":   " + intf.format(Double.parseDouble(String.valueOf(dataset.getValue(3)))) + "  dt</p></html>");
            } catch (Exception e) {
                Qte4.setText("");
            }

            try {
                Qte5.setText("<html><p>" + noms.get(4) + ":   " + intf.format(Double.parseDouble(String.valueOf(dataset.getValue(4)))) + "  dt</p></html>");
            } catch (Exception e) {
                Qte5.setText("");
            }

        } catch (SQLException ex) {
            Logger.getLogger(statistique.class.getName()).log(Level.SEVERE, null, ex);
        }

        bestsellersPie = ChartFactory.createPieChart(null, dataset, false, true, true);
        bestsellersPie.setBorderPaint(new Color(255, 250, 240));
        bestsellersPie.setBorderStroke(new BasicStroke(0));
        bestsellersPie.setBackgroundPaint(new Color(255, 250, 240));
        PiePlot pieplot = (PiePlot) bestsellersPie.getPlot();

        pieplot.setSectionPaint(0, new Color(5, 45, 26));
        pieplot.setSectionPaint(1, new Color(18, 79, 50));
        pieplot.setSectionPaint(2, new Color(30, 130, 82));
        pieplot.setSectionPaint(3, new Color(68, 207, 140));
        pieplot.setSectionPaint(4, new Color(130, 245, 210));

        pieplot.setBaseSectionOutlinePaint(new Color(5, 45, 26));
        pieplot.setBackgroundPaint(new Color(255, 250, 240));

        pieplot.setOutlinePaint(null);
        pieplot.setOutlineStroke(new BasicStroke(0));
        ChartPanel bestSellersChartPanel = new ChartPanel(bestsellersPie);
        //bestSellersChartPanel.setBackground(new Color(255, 250, 240)); 

        return bestSellersChartPanel;
    }

    public ChartPanel drawWeeklyBarChart(String month) {
        ResultSet week;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String brut_1, brut_2, brut_3, brut_4;
        String dep_1, dep_2, dep_3, dep_4;

        Date s = new Date();
        SimpleDateFormat d = new SimpleDateFormat("dd-MM-YYYY");
        SimpleDateFormat h = new SimpleDateFormat("HH:mm");
        String date = d.format(s);

        String mois = date.substring(3, 5);
        String annee = date.substring(6);
        String monthString = mois + "-" + annee;

        if (month.equals("last month")) {
            int m = Integer.parseInt(mois) - 1;
            int y = Integer.parseInt(annee);
            if (m <= 0) {
                m = 12;
                y = y - 1;
            }
            DecimalFormat form = new DecimalFormat("00");
            mois = form.format(m);
            annee = String.valueOf(y);
            monthString = mois + "-" + annee;
        }

        String Q = "SELECT SUM(total), Sum(total_base) "
                + "FROM `historique` "
                + "WHERE "
                + "Date_Sortie >= '01-" + monthString + "' "
                + "AND Date_Sortie <= '07-" + monthString + "' "
                + "AND Date_Sortie LIKE '__-" + monthString + "' ";

        week = db.exécutionQuery(Q);
        try {
            week.next();
            brut_1 = week.getString(1);
            dep_1 = week.getString(2);
        } catch (Exception e) {
            brut_1 = "0";
            dep_1 = "0";
        }

        Q = "SELECT SUM(total), Sum(total_base) "
                + "FROM `historique` "
                + "WHERE "
                + "Date_Sortie >= '08-" + monthString + "' "
                + "AND Date_Sortie <= '15-" + monthString + "' "
                + "AND Date_Sortie LIKE '__-" + monthString + "' ";

        week = db.exécutionQuery(Q);
        try {
            week.next();
            brut_2 = week.getString(1);
            dep_2 = week.getString(2);
        } catch (Exception e) {
            brut_2 = "0";
            dep_2 = "0";
        }

        Q = "SELECT SUM(total), Sum(total_base) "
                + "FROM `historique` "
                + "WHERE "
                + "Date_Sortie >= '16-" + monthString + "' "
                + "AND Date_Sortie <= '23-" + monthString + "' "
                + "AND Date_Sortie LIKE '__-" + monthString + "' ";

        week = db.exécutionQuery(Q);
        try {
            week.next();
            brut_3 = week.getString(1);
            dep_3 = week.getString(2);
        } catch (Exception e) {
            brut_3 = "0";
            dep_3 = "0";
        }

        Q = "SELECT SUM(total), Sum(total_base) "
                + "FROM `historique` "
                + "WHERE "
                + "Date_Sortie >= '24-" + monthString + "' "
                + "AND Date_Sortie <= '31-" + monthString + "' "
                + "AND Date_Sortie LIKE '__-" + monthString + "' ";

        week = db.exécutionQuery(Q);
        try {
            week.next();
            brut_4 = week.getString(1);
            dep_4 = week.getString(2);
        } catch (Exception e) {
            brut_4 = "0";
            dep_4 = "0";
        }

        if (brut_1 == null) {
            brut_1 = "0";
        }
        if (brut_2 == null) {
            brut_2 = "0";
        }
        if (brut_3 == null) {
            brut_3 = "0";
        }
        if (brut_4 == null) {
            brut_4 = "0";
        }

        if (dep_1 == null) {
            dep_1 = "0";
        }
        if (dep_2 == null) {
            dep_2 = "0";
        }
        if (dep_3 == null) {
            dep_3 = "0";
        }
        if (dep_4 == null) {
            dep_4 = "0";
        }

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        df.setMinimumFractionDigits(3);

        dataset.addValue(Double.parseDouble(brut_1), "brut", "semaine 1");
        dataset.addValue(Double.parseDouble(brut_2), "brut", "semaine 2");
        dataset.addValue(Double.parseDouble(brut_3), "brut", "semaine 3");
        dataset.addValue(Double.parseDouble(brut_4), "brut", "semaine 4");

        dataset.addValue(Double.parseDouble(dep_1), "dépenses", "semaine 1");
        dataset.addValue(Double.parseDouble(dep_2), "dépenses", "semaine 2");
        dataset.addValue(Double.parseDouble(dep_3), "dépenses", "semaine 3");
        dataset.addValue(Double.parseDouble(dep_4), "dépenses", "semaine 4");

        String chiffreDaffaire = df.format(Double.parseDouble(brut_1)
                + Double.parseDouble(brut_2)
                + Double.parseDouble(brut_3)
                + Double.parseDouble(brut_4));

        ChiffreDaffaireNum.setText(chiffreDaffaire);

        String benefices = df.format(Double.parseDouble(brut_1) - Double.parseDouble(dep_1)
                + Double.parseDouble(brut_2) - Double.parseDouble(dep_2)
                + Double.parseDouble(brut_3) - Double.parseDouble(dep_3)
                + Double.parseDouble(brut_4) - Double.parseDouble(dep_4));

        BeneficesNum.setText(benefices);

        ChiffreDaffaireNum.setText(chiffreDaffaire);

        WeeklyBarChart = ChartFactory.createBarChart3D(null, "Semaines", "valeurs ( en dinars )", dataset, PlotOrientation.VERTICAL, false, true, true);

        CategoryPlot pieplot = WeeklyBarChart.getCategoryPlot();
        pieplot.setRangeGridlinePaint(new Color(30, 130, 82));
        //pieplot.set(1, new Color(30, 130klk, 82));

        BarRenderer renderer = (BarRenderer) pieplot.getRenderer();
        renderer.setSeriesPaint(0, new Color(30, 130, 82));

        pieplot.setBackgroundPaint(new Color(255, 250, 240));
        pieplot.setOutlinePaint(null);
        pieplot.setOutlineStroke(new BasicStroke(0));

        //WeeklyBarChart.setBorderPaint(new Color(255, 250, 240));
        WeeklyBarChart.setBorderStroke(new BasicStroke(0));
        WeeklyBarChart.setBackgroundPaint(new Color(255, 250, 240));

        ChartPanel WeeklyBarChartPanel = new ChartPanel(WeeklyBarChart);
        return WeeklyBarChartPanel;
    }

//    public String changeDoubleToInt(String s)
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

    public static statistique getObj() {
        if (obj == null) {

            obj = new statistique("");
        }
        return obj;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Statistiques_Panel = new javax.swing.JPanel();
        Qte5 = new javax.swing.JLabel();
        Qte4 = new javax.swing.JLabel();
        Qte3 = new javax.swing.JLabel();
        Qte2 = new javax.swing.JLabel();
        Qte1 = new javax.swing.JLabel();
        barpanel = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        statbtn = new javax.swing.JButton();
        usersbtn = new javax.swing.JButton();
        salesbtn = new javax.swing.JButton();
        inventorybtn = new javax.swing.JButton();
        stockbtn = new javax.swing.JButton();
        registerbtn = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Session = new javax.swing.JPanel();
        utilisateur = new javax.swing.JLabel();
        LogoutBtnbg = new javax.swing.JLabel();
        LogoutBtn = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Heure1 = new javax.swing.JLabel();
        Date1 = new javax.swing.JLabel();
        PieChartPanelBft = new javax.swing.JPanel();
        PieChartPanelQte = new javax.swing.JPanel();
        BarChartPanel = new javax.swing.JPanel();
        TOP_Profit = new javax.swing.JLabel();
        TOP_Qte2 = new javax.swing.JLabel();
        Net5 = new javax.swing.JLabel();
        Net4 = new javax.swing.JLabel();
        Net3 = new javax.swing.JLabel();
        Net2 = new javax.swing.JLabel();
        Net1 = new javax.swing.JLabel();
        StockVVNum = new javax.swing.JLabel();
        StockVV = new javax.swing.JLabel();
        StockVANum = new javax.swing.JLabel();
        StockVA = new javax.swing.JLabel();
        ChiffreDaffaire = new javax.swing.JLabel();
        ChiffreDaffaireNum = new javax.swing.JLabel();
        BeneficesNum = new javax.swing.JLabel();
        Benefices = new javax.swing.JLabel();
        CemoisLabel = new javax.swing.JLabel();
        MoisPrecLabel = new javax.swing.JLabel();
        thisMonthBtn = new javax.swing.JButton();
        lastMonthBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Statistiques_Panel.setBackground(new java.awt.Color(255, 250, 240));
        Statistiques_Panel.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        Statistiques_Panel.setPreferredSize(new java.awt.Dimension(1230, 100));
        Statistiques_Panel.setLayout(null);

        Qte5.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        Qte5.setForeground(new java.awt.Color(30, 130, 82));
        Qte5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Qte5.setText("Top 5 par quantité");
        Qte5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(Qte5);
        Qte5.setBounds(880, 490, 130, 60);

        Qte4.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        Qte4.setForeground(new java.awt.Color(30, 130, 82));
        Qte4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Qte4.setText("Top 5 par quantité");
        Qte4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(Qte4);
        Qte4.setBounds(880, 430, 130, 60);

        Qte3.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        Qte3.setForeground(new java.awt.Color(30, 130, 82));
        Qte3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Qte3.setText("Top 5 par quantité");
        Qte3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(Qte3);
        Qte3.setBounds(880, 370, 130, 60);

        Qte2.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        Qte2.setForeground(new java.awt.Color(30, 130, 82));
        Qte2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Qte2.setText("Top 5 par quantité");
        Qte2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(Qte2);
        Qte2.setBounds(880, 310, 130, 60);

        Qte1.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        Qte1.setForeground(new java.awt.Color(30, 130, 82));
        Qte1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Qte1.setText("Top 5 par quantité");
        Qte1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(Qte1);
        Qte1.setBounds(880, 250, 130, 60);

        barpanel.setBackground(new java.awt.Color(255, 250, 240));
        barpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 130, 82), 2));
        barpanel.setForeground(new java.awt.Color(255, 253, 209));
        barpanel.setPreferredSize(new java.awt.Dimension(100, 1080));
        barpanel.setLayout(null);

        Logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/logoMAGAZI-04.png"))); // NOI18N
        barpanel.add(Logo);
        Logo.setBounds(0, 0, 80, 90);

        jLabel22.setBackground(new java.awt.Color(30, 130, 82));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/statistiquesLight.png"))); // NOI18N
        jLabel22.setOpaque(true);
        barpanel.add(jLabel22);
        jLabel22.setBounds(10, 680, 60, 50);

        jLabel21.setBackground(new java.awt.Color(255, 250, 240));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/utilisateurs.png"))); // NOI18N
        jLabel21.setOpaque(true);
        barpanel.add(jLabel21);
        jLabel21.setBounds(10, 580, 60, 50);

        jLabel20.setBackground(new java.awt.Color(255, 250, 240));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/historique.png"))); // NOI18N
        jLabel20.setOpaque(true);
        barpanel.add(jLabel20);
        jLabel20.setBounds(10, 480, 60, 64);

        jLabel19.setBackground(new java.awt.Color(255, 250, 240));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/inventory.png"))); // NOI18N
        jLabel19.setOpaque(true);
        barpanel.add(jLabel19);
        jLabel19.setBounds(10, 380, 60, 50);

        jLabel18.setBackground(new java.awt.Color(255, 250, 240));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/Stock.png"))); // NOI18N
        jLabel18.setOpaque(true);
        barpanel.add(jLabel18);
        jLabel18.setBounds(10, 280, 60, 50);

        jLabel16.setBackground(new java.awt.Color(255, 250, 240));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/cashregister.png"))); // NOI18N
        jLabel16.setOpaque(true);
        barpanel.add(jLabel16);
        jLabel16.setBounds(10, 180, 60, 50);

        jLabel5.setBackground(new java.awt.Color(30, 130, 82));
        jLabel5.setOpaque(true);
        barpanel.add(jLabel5);
        jLabel5.setBounds(0, 670, 80, 70);

        statbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        statbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statbtnActionPerformed(evt);
            }
        });
        barpanel.add(statbtn);
        statbtn.setBounds(10, 680, 60, 50);

        usersbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        usersbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersbtnActionPerformed(evt);
            }
        });
        barpanel.add(usersbtn);
        usersbtn.setBounds(10, 580, 60, 45);

        salesbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        salesbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesbtnActionPerformed(evt);
            }
        });
        barpanel.add(salesbtn);
        salesbtn.setBounds(10, 490, 60, 50);

        inventorybtn.setPreferredSize(new java.awt.Dimension(80, 80));
        inventorybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventorybtnActionPerformed(evt);
            }
        });
        barpanel.add(inventorybtn);
        inventorybtn.setBounds(10, 380, 60, 50);

        stockbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        stockbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockbtnActionPerformed(evt);
            }
        });
        barpanel.add(stockbtn);
        stockbtn.setBounds(10, 280, 60, 50);

        registerbtn.setBackground(new java.awt.Color(255, 255, 255));
        registerbtn.setBorder(null);
        registerbtn.setBorderPainted(false);
        registerbtn.setOpaque(false);
        registerbtn.setPreferredSize(new java.awt.Dimension(80, 80));
        registerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerbtnActionPerformed(evt);
            }
        });
        barpanel.add(registerbtn);
        registerbtn.setBounds(10, 180, 60, 50);

        jLabel30.setBackground(new java.awt.Color(255, 250, 240));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/credit.png"))); // NOI18N
        jLabel30.setOpaque(true);
        barpanel.add(jLabel30);
        jLabel30.setBounds(10, 760, 60, 50);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        barpanel.add(jButton2);
        jButton2.setBounds(10, 770, 60, 30);

        Statistiques_Panel.add(barpanel);
        barpanel.setBounds(2, 0, 80, 2000);

        jPanel2.setBackground(new java.awt.Color(255, 250, 240));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 130, 82), 2));
        jPanel2.setForeground(new java.awt.Color(30, 130, 82));
        jPanel2.setLayout(null);

        Session.setBackground(new java.awt.Color(255, 250, 240));
        Session.setRequestFocusEnabled(false);
        Session.setLayout(null);

        utilisateur.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        utilisateur.setForeground(new java.awt.Color(5, 45, 26));
        utilisateur.setText("User");
        Session.add(utilisateur);
        utilisateur.setBounds(25, 10, 110, 40);

        LogoutBtnbg.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        LogoutBtnbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/logoutbtn.png"))); // NOI18N
        Session.add(LogoutBtnbg);
        LogoutBtnbg.setBounds(145, 10, 100, 40);

        LogoutBtn.setBackground(new java.awt.Color(255, 255, 255));
        LogoutBtn.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        LogoutBtn.setForeground(new java.awt.Color(255, 0, 51));
        LogoutBtn.setBorder(null);
        LogoutBtn.setBorderPainted(false);
        LogoutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LogoutBtn.setOpaque(false);
        LogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBtnActionPerformed(evt);
            }
        });
        Session.add(LogoutBtn);
        LogoutBtn.setBounds(150, 20, 90, 20);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/sessionbg.png"))); // NOI18N
        Session.add(jLabel17);
        jLabel17.setBounds(5, 0, 260, 60);

        jPanel2.add(Session);
        Session.setBounds(90, 10, 270, 60);

        jLabel12.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(30, 130, 82));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("STATISTIQUES");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(870, 0, 370, 80);

        Heure1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Heure1.setForeground(new java.awt.Color(5, 45, 26));
        jPanel2.add(Heure1);
        Heure1.setBounds(510, 5, 100, 70);

        Date1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Date1.setForeground(new java.awt.Color(5, 45, 26));
        jPanel2.add(Date1);
        Date1.setBounds(640, 5, 100, 70);

        Statistiques_Panel.add(jPanel2);
        jPanel2.setBounds(50, 0, 2030, 80);

        PieChartPanelBft.setBackground(new java.awt.Color(255, 250, 240));

        javax.swing.GroupLayout PieChartPanelBftLayout = new javax.swing.GroupLayout(PieChartPanelBft);
        PieChartPanelBft.setLayout(PieChartPanelBftLayout);
        PieChartPanelBftLayout.setHorizontalGroup(
            PieChartPanelBftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        PieChartPanelBftLayout.setVerticalGroup(
            PieChartPanelBftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        Statistiques_Panel.add(PieChartPanelBft);
        PieChartPanelBft.setBounds(150, 260, 260, 250);

        PieChartPanelQte.setBackground(new java.awt.Color(255, 250, 240));

        javax.swing.GroupLayout PieChartPanelQteLayout = new javax.swing.GroupLayout(PieChartPanelQte);
        PieChartPanelQte.setLayout(PieChartPanelQteLayout);
        PieChartPanelQteLayout.setHorizontalGroup(
            PieChartPanelQteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        PieChartPanelQteLayout.setVerticalGroup(
            PieChartPanelQteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        Statistiques_Panel.add(PieChartPanelQte);
        PieChartPanelQte.setBounds(610, 260, 260, 250);

        BarChartPanel.setBackground(new java.awt.Color(255, 250, 240));

        javax.swing.GroupLayout BarChartPanelLayout = new javax.swing.GroupLayout(BarChartPanel);
        BarChartPanel.setLayout(BarChartPanelLayout);
        BarChartPanelLayout.setHorizontalGroup(
            BarChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        BarChartPanelLayout.setVerticalGroup(
            BarChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        Statistiques_Panel.add(BarChartPanel);
        BarChartPanel.setBounds(150, 610, 850, 340);

        TOP_Profit.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        TOP_Profit.setForeground(new java.awt.Color(30, 130, 82));
        TOP_Profit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TOP_Profit.setText("Top 5 par profit");
        TOP_Profit.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(TOP_Profit);
        TOP_Profit.setBounds(150, 220, 230, 30);

        TOP_Qte2.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        TOP_Qte2.setForeground(new java.awt.Color(30, 130, 82));
        TOP_Qte2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TOP_Qte2.setText("Top 5 par quantité");
        Statistiques_Panel.add(TOP_Qte2);
        TOP_Qte2.setBounds(610, 220, 210, 30);

        Net5.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        Net5.setForeground(new java.awt.Color(30, 130, 82));
        Net5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Net5.setText("Top 5 par quantité");
        Net5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Net5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(Net5);
        Net5.setBounds(430, 490, 140, 60);

        Net4.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        Net4.setForeground(new java.awt.Color(30, 130, 82));
        Net4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Net4.setText("Top 5 par quantité");
        Net4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Net4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(Net4);
        Net4.setBounds(430, 430, 140, 60);

        Net3.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        Net3.setForeground(new java.awt.Color(30, 130, 82));
        Net3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Net3.setText("Top 5 par quantité");
        Net3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Net3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(Net3);
        Net3.setBounds(430, 370, 140, 60);

        Net2.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        Net2.setForeground(new java.awt.Color(30, 130, 82));
        Net2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Net2.setText("Top 5 par quantité");
        Net2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Net2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(Net2);
        Net2.setBounds(430, 310, 140, 60);

        Net1.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        Net1.setForeground(new java.awt.Color(30, 130, 82));
        Net1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Net1.setText("Top 5 par quantité");
        Net1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Net1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(Net1);
        Net1.setBounds(430, 250, 140, 60);

        StockVVNum.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        StockVVNum.setForeground(new java.awt.Color(30, 130, 82));
        StockVVNum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        StockVVNum.setText("000.000");
        Statistiques_Panel.add(StockVVNum);
        StockVVNum.setBounds(1090, 320, 300, 50);

        StockVV.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        StockVV.setForeground(new java.awt.Color(5, 45, 26));
        StockVV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        StockVV.setText("Valeur de Vente du Stock");
        Statistiques_Panel.add(StockVV);
        StockVV.setBounds(1090, 280, 300, 30);

        StockVANum.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        StockVANum.setForeground(new java.awt.Color(30, 130, 82));
        StockVANum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        StockVANum.setText("000.000");
        Statistiques_Panel.add(StockVANum);
        StockVANum.setBounds(1090, 430, 300, 50);

        StockVA.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        StockVA.setForeground(new java.awt.Color(5, 45, 26));
        StockVA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        StockVA.setText("Valeur d'Achat du Stock");
        Statistiques_Panel.add(StockVA);
        StockVA.setBounds(1090, 390, 300, 30);

        ChiffreDaffaire.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        ChiffreDaffaire.setForeground(new java.awt.Color(5, 45, 26));
        ChiffreDaffaire.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChiffreDaffaire.setText("Chiffre d'affaire:");
        Statistiques_Panel.add(ChiffreDaffaire);
        ChiffreDaffaire.setBounds(1050, 680, 300, 30);

        ChiffreDaffaireNum.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        ChiffreDaffaireNum.setForeground(new java.awt.Color(30, 130, 82));
        ChiffreDaffaireNum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChiffreDaffaireNum.setText("000.000");
        Statistiques_Panel.add(ChiffreDaffaireNum);
        ChiffreDaffaireNum.setBounds(1050, 720, 300, 50);

        BeneficesNum.setFont(new java.awt.Font("Roboto Black", 0, 48)); // NOI18N
        BeneficesNum.setForeground(new java.awt.Color(30, 130, 82));
        BeneficesNum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BeneficesNum.setText("000.000");
        Statistiques_Panel.add(BeneficesNum);
        BeneficesNum.setBounds(1050, 830, 300, 50);

        Benefices.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        Benefices.setForeground(new java.awt.Color(5, 45, 26));
        Benefices.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Benefices.setText("Bénéfices:");
        Statistiques_Panel.add(Benefices);
        Benefices.setBounds(1050, 790, 300, 30);

        CemoisLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/cemois.png"))); // NOI18N
        Statistiques_Panel.add(CemoisLabel);
        CemoisLabel.setBounds(130, 120, 500, 60);

        MoisPrecLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/moisPrec.png"))); // NOI18N
        Statistiques_Panel.add(MoisPrecLabel);
        MoisPrecLabel.setBounds(130, 120, 500, 60);

        thisMonthBtn.setText("jButton1");
        thisMonthBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thisMonthBtnActionPerformed(evt);
            }
        });
        Statistiques_Panel.add(thisMonthBtn);
        thisMonthBtn.setBounds(140, 130, 200, 40);

        lastMonthBtn.setText("jButton1");
        lastMonthBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastMonthBtnActionPerformed(evt);
            }
        });
        Statistiques_Panel.add(lastMonthBtn);
        lastMonthBtn.setBounds(400, 130, 200, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/frame2.png"))); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(jLabel1);
        jLabel1.setBounds(130, 600, 1350, 370);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/frame.png"))); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(jLabel2);
        jLabel2.setBounds(130, 200, 890, 370);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE POS/frame3.png"))); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Statistiques_Panel.add(jLabel3);
        jLabel3.setBounds(1050, 200, 410, 370);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2000, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Statistiques_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 2000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2000, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Statistiques_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 2000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBtnActionPerformed
        // TODO add your handling code here:

        Login a;
        try {
            db.queryDelete("caisse");
            a = new Login();

            // action logout
            String user = utilisateur.getText();
            String action = "log out";

            String Description = "";

            String[] actions = {"user", "type_action", "description", "Date", "Heure"};
            String[] inf3 = {user, action, Description, Date.getText(), Heure.getText()};
            System.out.println(db.queryInsert("action", actions, inf3));

            //..................................
            this.hide();
            a.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(POS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_LogoutBtnActionPerformed

    private void statbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statbtnActionPerformed
        // TODO add your handling code here:
        //statistique.getObj().setVisible(true);

    }//GEN-LAST:event_statbtnActionPerformed

    private void usersbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersbtnActionPerformed
        // TODO add your handling code here:
        db.closeconnexion();

        Utilisateurs a = new Utilisateurs(utilisateur.getText());
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_usersbtnActionPerformed

    private void salesbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesbtnActionPerformed
        // TODO add your handling code here:
        db.closeconnexion();

        Historique a = new Historique(utilisateur.getText());
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_salesbtnActionPerformed

    private void inventorybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventorybtnActionPerformed
        // TODO add your handling code here:
        db.closeconnexion();

        Inventory a = new Inventory(utilisateur.getText());
        a.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_inventorybtnActionPerformed

    private void stockbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockbtnActionPerformed
        // TODO add your handling code here:
        db.closeconnexion();
        stock a = new stock(utilisateur.getText());
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_stockbtnActionPerformed

    private void registerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerbtnActionPerformed
        // TODO add your handling code here:
        db.closeconnexion();

        POS a = new POS(utilisateur.getText());
        a.setVisible(true);
                this.dispose();

    }//GEN-LAST:event_registerbtnActionPerformed

    private void thisMonthBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thisMonthBtnActionPerformed
        // TODO add your handling code here:
        PieChartPanelBft.removeAll();
        PieChartPanelQte.removeAll();
        BarChartPanel.removeAll();

        PieChartPanelBft.add(drawBestSellerByBenefitChart("this month"), BorderLayout.CENTER);
        PieChartPanelQte.add(drawBestSellerByQuantityChart("this month"), BorderLayout.CENTER);
        BarChartPanel.add(drawWeeklyBarChart("this month"), BorderLayout.CENTER);

        CemoisLabel.show();
        MoisPrecLabel.hide();
    }//GEN-LAST:event_thisMonthBtnActionPerformed

    private void lastMonthBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastMonthBtnActionPerformed
        // TODO add your handling code here:
        PieChartPanelBft.removeAll();
        PieChartPanelQte.removeAll();
        BarChartPanel.removeAll();

        PieChartPanelBft.add(drawBestSellerByBenefitChart("last month"), BorderLayout.CENTER);
        PieChartPanelQte.add(drawBestSellerByQuantityChart("last month"), BorderLayout.CENTER);
        BarChartPanel.add(drawWeeklyBarChart("last month"), BorderLayout.CENTER);

        CemoisLabel.hide();
        MoisPrecLabel.show();
    }//GEN-LAST:event_lastMonthBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Credit a = new Credit(utilisateur.getText());
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(statistique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(statistique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(statistique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(statistique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new statistique("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BarChartPanel;
    private javax.swing.JLabel Benefices;
    private javax.swing.JLabel BeneficesNum;
    private javax.swing.JLabel CemoisLabel;
    private javax.swing.JLabel ChiffreDaffaire;
    private javax.swing.JLabel ChiffreDaffaireNum;
    private javax.swing.JLabel Date1;
    private javax.swing.JLabel Heure1;
    private javax.swing.JLabel Logo;
    private javax.swing.JButton LogoutBtn;
    private javax.swing.JLabel LogoutBtnbg;
    private javax.swing.JLabel MoisPrecLabel;
    private javax.swing.JLabel Net1;
    private javax.swing.JLabel Net2;
    private javax.swing.JLabel Net3;
    private javax.swing.JLabel Net4;
    private javax.swing.JLabel Net5;
    private javax.swing.JPanel PieChartPanelBft;
    private javax.swing.JPanel PieChartPanelQte;
    private javax.swing.JLabel Qte1;
    private javax.swing.JLabel Qte2;
    private javax.swing.JLabel Qte3;
    private javax.swing.JLabel Qte4;
    private javax.swing.JLabel Qte5;
    private javax.swing.JPanel Session;
    private javax.swing.JPanel Statistiques_Panel;
    private javax.swing.JLabel StockVA;
    private javax.swing.JLabel StockVANum;
    private javax.swing.JLabel StockVV;
    private javax.swing.JLabel StockVVNum;
    private javax.swing.JLabel TOP_Profit;
    private javax.swing.JLabel TOP_Qte2;
    private javax.swing.JPanel barpanel;
    private javax.swing.JButton inventorybtn;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton lastMonthBtn;
    private javax.swing.JButton registerbtn;
    private javax.swing.JButton salesbtn;
    private javax.swing.JButton statbtn;
    private javax.swing.JButton stockbtn;
    private javax.swing.JButton thisMonthBtn;
    private javax.swing.JButton usersbtn;
    private javax.swing.JLabel utilisateur;
    // End of variables declaration//GEN-END:variables
}

package BDD;

import java.sql.*;

public class Parametre {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    public String IPHOST = getIP();
    public String DB = getDB();
    public String HOST_DB = "jdbc:mysql://" + IPHOST + ":3306/" + DB;
    public String USERNAME_DB = getUSERNAMEDB();
    public String PASSWORD_DB = getPASS();
    public static int PORT = 11111;
    public static String USER;

    public String getIP() {

        String IP = "";
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection("jdbc:sqlite:ConPort.db", "", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Connexion WHERE ID=1");
            IP = rs.getString("IP");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return IP;
    }

    public String getDB() {

        String DBB = "";
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection("jdbc:sqlite:ConPort.db", "", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Connexion WHERE ID=1");
            DBB = rs.getString("DB");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return DBB;
    }

    public String getUSERNAMEDB() {

        String PSE = "";
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection("jdbc:sqlite:ConPort.db", "", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Connexion WHERE ID=1");
            PSE = rs.getString("PSE");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return PSE;
    }

    public String getPASS() {

        String PASS = "";
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection("jdbc:sqlite:ConPort.db", "", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Connexion WHERE ID=1");
            PASS = rs.getString("PASS");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return PASS;
    }

    //public static String PORT="11111";
    //public static String USER;
}

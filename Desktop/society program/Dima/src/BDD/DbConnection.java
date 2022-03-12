package BDD;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

public class DbConnection {

    Connection connection;
    Statement statement;
    String SQL;
    String url;
    String username;
    String password;
    Socket client;
    int Port;
    String Host;
    ConnectComPort cp;
    ResultSet rs;

    public DbConnection(String url, String username, String password, String Host, int Port) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.Host = Host;
        this.Port = Port;
        cp = new ConnectComPort();

    }

    //fonction pour faire connection au base de donnée
    public Connection connexionDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {

            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());//Afficher ou se trouve le probleme getMessage

            if (e.getMessage().startsWith("Access denied for")) {

                String b[] = {"ID", "IP", "DB", "PSE", "PASS"};
                rs = cp.querySelect(b, "Connexion");
                TableModel jTable1 = new ResultSetTableModel(rs);

                String txIP = String.valueOf(jTable1.getValueAt(0, 1));
                String txDB = String.valueOf(jTable1.getValueAt(0, 2));
                String txUt = String.valueOf(jTable1.getValueAt(0, 3));
                String txPss = String.valueOf(jTable1.getValueAt(0, 4));

                String[] colon = {"IP", "DB", "PSE", "PASS"};
                String[] inf = {txIP, txDB, txUt, txPss};

                String aa = "1";
                System.out.println(cp.queryUpdate("Connexion", colon, inf, "ID='" + aa + "'"));
                connexionDatabasetest();
                JOptionPane.showMessageDialog(null, "Le logiciel va démarrer correctement la prochaine fois.");
                System.exit(0);
            }
        }
        return connection;
    }

    public Connection connexionDatabasetest() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);
            JOptionPane.showMessageDialog(null, "Connexion etablie !");//Afficher ou se trouve le probleme getMessage
        } catch (Exception e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erreur Connexion !");//Afficher ou se trouve le probleme getMessage
        }
        return connection;
    }

    //fermer la base de donnée
    public Connection closeconnexion() {
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println(e);//
        }
        return connection;
    }

    public ResultSet exécutionQuery(String sql) {
        connexionDatabase();
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            // System.out.println(sql);
        } catch (SQLException ex) {
            System.err.println(ex);

        }
        return resultSet;
    }

    //exécuter update
    public String exécutionUpdate(String sql) {
        connexionDatabase();
        String result = "";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            result = sql;

        } catch (SQLException ex) {
            result = ex.toString();
        }
        return result;
    }

    //requete pour afficher tous 
    public ResultSet querySelectAll(String nomTable) {

        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable;
        //System.out.println(SQL);
        return this.exécutionQuery(SQL);
    }

    // afficher tous avec de paramètre ("etat")
    public ResultSet querySelectAll(String nomTable, String état) {

        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable + " WHERE " + état;
        return this.exécutionQuery(SQL);

    }

    public ResultSet querySearchAll(String nomTable, String état, String étatPremier, String étatSeconde) {

        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable + " WHERE " + état + " BETWEEN '" + étatPremier + "' AND '" + étatSeconde + "' ";
        return this.exécutionQuery(SQL);

    }

    public ResultSet querySelectTwo(String nomTable, String état, String éta) {

        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable + " WHERE " + état + " AND " + éta;
        return this.exécutionQuery(SQL);

    }

    public ResultSet querySelectTwoOne(String nomTable, String état, String étatt, String étatPremie, String étatSecond) {

        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable + " WHERE " + état + " AND " + étatt + " BETWEEN '" + étatPremie + "' AND '" + étatSecond + "' ";
        return this.exécutionQuery(SQL);

    }

    public ResultSet querySelectTwoALL(String nomTable, String état, String éta, String étatt, String étatPremie, String étatSecond) {

        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable + " WHERE " + état + " AND " + éta + " AND " + étatt + " BETWEEN '" + étatPremie + "' AND '" + étatSecond + "' ";
        return this.exécutionQuery(SQL);

    }

    //
    public ResultSet querySelect(String[] nomColonne, String nomTable) {

        connexionDatabase();
        int i;
        SQL = "SELECT ";

        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }
        SQL += " FROM " + nomTable;
        return this.exécutionQuery(SQL);
    }

    public ResultSet querySelectIndex(String[] nomColonne, String nomTable, String index) {

        connexionDatabase();
        int i;
        SQL = "SELECT " + index;

        /*for (i = 0; i <= nomColonne.length - 1; i++) {
         SQL += nomColonne[i];
         if (i < nomColonne.length - 1) {
         SQL += ",";
         }
         }*/
        SQL += " FROM " + nomTable;
        return this.exécutionQuery(SQL);
    }

    public ResultSet querySelectOrdre(String[] nomColonne, String nomTable, String ordre) {

        connexionDatabase();
        int i;
        SQL = "SELECT ";

        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }
        SQL += " FROM " + nomTable + " ORDER BY " + ordre;
        return this.exécutionQuery(SQL);
    }

    //
    public ResultSet fcSelectCommand(String[] nomColonne, String nomTable, String état) {

        connexionDatabase();
        int i;
        SQL = "SELECT ";
        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }
        SQL += " FROM " + nomTable + " WHERE " + état;
        return this.exécutionQuery(SQL);
    }

    // entrée de donnée
    public String queryInsert(String nomTable, String[] contenuTableau) {

        connexionDatabase();
        int i;
        SQL = "INSERT INTO " + nomTable + " VALUES(";

        for (i = 0; i <= contenuTableau.length - 1; i++) {
            SQL += "'" + contenuTableau[i] + "'";
            if (i < contenuTableau.length - 1) {
                SQL += ",";
            }
        }
        SQL += ")";
        return this.exécutionUpdate(SQL);
    }

    //
    public String queryInsert(String nomTable, String[] nomColonne, String[] contenuTableau) {

        connexionDatabase();
        int i;
        SQL = "INSERT INTO " + nomTable + "(";
        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }
        SQL += ") VALUES(";
        for (i = 0; i <= contenuTableau.length - 1; i++) {
            SQL += "'" + contenuTableau[i] + "'";
            if (i < contenuTableau.length - 1) {
                SQL += ",";
            }
        }

        SQL += ")";
        return this.exécutionUpdate(SQL);

    }

    //
    public String queryUpdate(String nomTable, String[] nomColonne, String[] contenuTableau, String état) {

        connexionDatabase();
        int i;
        SQL = "UPDATE " + nomTable + " SET ";

        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i] + "='" + contenuTableau[i] + "'";
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }
        SQL += " WHERE " + état;
        return this.exécutionUpdate(SQL);

    }

    // Supression sans parametre
    public String queryDelete(String nomtable) {

        connexionDatabase();
        SQL = "DELETE FROM " + nomtable;

        return this.exécutionUpdate(SQL);
    }

    //suprimer avec paramètre
    public String queryDelete(String nomTable, String état) {

        connexionDatabase();
        SQL = "DELETE FROM " + nomTable + " WHERE " + état;
        return this.exécutionUpdate(SQL);
    }

}

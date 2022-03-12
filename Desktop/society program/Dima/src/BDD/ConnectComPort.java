package BDD;

//import java.net.Socket;
import java.sql.*;
import javax.swing.*;

public class ConnectComPort {

    Connection connection;
    Statement statement;
    String SQL;

    public ConnectComPort() {
    }

    public Connection connexionDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ConPort.db", "", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return connection;
    }

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
            System.out.println(sql);
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
        System.out.println(SQL);
        return this.exécutionQuery(SQL);
    }

    // afficher tous avec de paramètre ("etat")

    public ResultSet querySelectAll(String nomTable, String état) {

        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable + " WHERE " + état;
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

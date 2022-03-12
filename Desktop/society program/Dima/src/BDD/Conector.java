package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conector {

    private Connection conexion;
    private String error = null;

    public Conector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conexion = DriverManager.getConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB);
        } catch (ClassNotFoundException | SQLException ex) {
            this.error = ex.getMessage();
        }
    }

    public String getError() {
        return this.error;
    }

    public Connection getConexion() {
        return this.conexion;
    }

    public void Cerrar() {
        try {
            this.conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

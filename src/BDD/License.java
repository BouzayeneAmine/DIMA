package BDD;

import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class License {

    ConnectComPort cp;
    ResultSet rs;

    public License() {
        cp = new ConnectComPort();
        String b[] = {"ID", "Licence"};
        rs = cp.querySelect(b, "Licence");
        TableModel jTable = DbUtils.resultSetToTableModel(rs);
        String code = String.valueOf(jTable.getValueAt(0, 1));
        Calendar expireDate = Calendar.getInstance();
        // January is 0 (y, m, d)
        expireDate.set(2020, 11, 24);

        // Get current date and compare
        if (Calendar.getInstance().after(expireDate) && !code.equals("HDL8-MSZ5-MMDZ-115A")) {
            // Die
            JOptionPane.showMessageDialog(null, "Version d'essai expiré ! entrer un code de licence ");
            System.exit(0);
        }
        if (Calendar.getInstance().equals(expireDate) && !code.equals("HDL8-MSZ5-MMDZ-115A")) {
            JOptionPane.showMessageDialog(null, "Dernier jour d'essaie ! ");
        }

    }
}

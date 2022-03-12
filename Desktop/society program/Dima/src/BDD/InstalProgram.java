package BDD;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.StrictMath.random;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class InstalProgram {

    ConnectComPort cp;
    ResultSet rs;

    public void keyTyped() {
        KeyEvent e = null;
        if (e.getKeyCode() == KeyEvent.VK_F) {
            JOptionPane.showMessageDialog(null, "test");
        }
    }

    public InstalProgram() throws IOException {
        cp = new ConnectComPort();
        String b[] = {"ID", "Year", "Month", "Day", "Test"};
        rs = cp.querySelect(b, "Install");
        TableModel jTable = DbUtils.resultSetToTableModel(rs);
        String YearInstallFirst = String.valueOf(jTable.getValueAt(0, 1));
        String MonthInstallFirst = String.valueOf(jTable.getValueAt(0, 2));
        String DayInstallFirst = String.valueOf(jTable.getValueAt(0, 3));
        String testProgram = String.valueOf(jTable.getValueAt(0, 4));

        if (testProgram.equals("0")) {

            Calendar c = Calendar.getInstance();

            String[] colon = {"Year", "Month", "Day", "Test"};
            String[] inf = {String.valueOf(c.get(Calendar.YEAR)), String.valueOf(c.get(Calendar.MONTH)), String.valueOf(c.get(Calendar.DATE)), "1"};
            String aa = "1";
            System.out.println(cp.queryUpdate("Install", colon, inf, "ID='" + aa + "'"));

        } else if (testProgram.equals("1")) {
            Calendar expireDate = Calendar.getInstance();
            expireDate.set(2018, 6, 21);

            Calendar DateInstall = Calendar.getInstance();
            expireDate.set(Integer.parseInt(YearInstallFirst), Integer.parseInt(MonthInstallFirst) - 1, Integer.parseInt(DayInstallFirst));

            if (DateInstall.after(expireDate)) {

                String letters = "ABCDEFGHJKMNPQRSTUVWXYZ1234567890";
                String pw = "";
                for (int i = 0; i < 7; i++) {
                    int index = (int) (random() * letters.length());
                    pw += letters.substring(index, index + 1);
                }
                String Password = String.valueOf(pw);
                System.out.println(Password);
                if (JOptionPane.showConfirmDialog(null, "Logiciel hors license légale ! Veuillez contacter MESTIRI PESAGE pour plus d'information ", "Attention", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    String value = "";

                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                    System.out.println("Enter String");
                    value = br.readLine();
                    
                    if (value.equals(Password + "MestiriPesage")) {
                        String[] colon = {"Test"};
                        String[] inf = {"2"};
                        String aa = "1";
                        System.out.println(cp.queryUpdate("Install", colon, inf, "ID='" + aa + "'"));
                        JOptionPane.showMessageDialog(null, "OK");

                    } else {
                        JOptionPane.showMessageDialog(null, "NO");
                        System.exit(0);
                    }

                } else {
                    System.exit(0);
                }
                // JOptionPane.showMessageDialog(null, "Logiciel hors license légale ! Veuillez contacter MESTIRI PESAGE pour plus d'information");
                //String value = JOptionPane.showInputDialog("Logiciel hors license légale ! Veuillez contacter MESTIRI PESAGE pour plus d'information");

            }
        } else if (testProgram.equals("2")) {
            String value = JOptionPane.showInputDialog("Votre Code Secret : ");
            if (value.equals("MestiriPesage")) {
                String[] colon = {"Test"};
                String[] inf = {"3"};
                String aa = "1";
                System.out.println(cp.queryUpdate("Install", colon, inf, "ID='" + aa + "'"));
                JOptionPane.showMessageDialog(null, "OK");
            } else {
                System.exit(0);
            }

        } else if (!testProgram.equals("3")) {
            JOptionPane.showMessageDialog(null, "Logiciel hors license légale ! Veuillez contacter MESTIRI PESAGE pour plus d'information");
            System.exit(0);

        }
    }
}

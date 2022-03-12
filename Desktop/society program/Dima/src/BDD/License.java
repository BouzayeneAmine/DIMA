package BDD;

import static java.lang.StrictMath.random;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class License {

    DbConnection db;
    ConnectComPort cp;
    ResultSet rs;
    static char[] chars = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
        'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
        'y', 'z', '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'A', 'B', 'C', 'D',
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z'
    };
// Caesar cipher

    static String encrypt(String text, int offset) {
        char[] plain = text.toCharArray();

        for (int i = 0; i < plain.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (j <= chars.length - offset) {
                    if (plain[i] == chars[j]) {
                        plain[i] = chars[j + offset];
                        break;
                    }
                } else if (plain[i] == chars[j]) {
                    plain[i] = chars[j - (chars.length - offset + 1)];
                }
            }
        }
        return String.valueOf(plain);
    }

    static String decrypt(String cip, int offset) {
        char[] cipher = cip.toCharArray();
        for (int i = 0; i < cipher.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (j >= offset && cipher[i] == chars[j]) {
                    cipher[i] = chars[j - offset];
                    break;
                }
                if (cipher[i] == chars[j] && j < offset) {
                    cipher[i] = chars[(chars.length - offset + 1) + j];
                    break;
                }
            }
        }
        return String.valueOf(cipher);
    }

    public License() {

        db = new DbConnection(new Parametre().HOST_DB, new Parametre().USERNAME_DB, new Parametre().PASSWORD_DB, new Parametre().IPHOST, new Parametre().PORT);

        String a[] = {"Id", "Code_licence", "Jour", "Mois", "Annee", "Test"};
        rs = db.querySelect(a, "license");
        TableModel jTable3 = new ResultSetTableModel(rs);
        String test = String.valueOf(jTable3.getValueAt(0, 5));

        if (!test.equals("1")) {

            String letters = "ABCDEFGHJKMNPQRSTUVWXYZ1234567890";
            String pw = "";
            for (int i = 0; i < 7; i++) {
                int index = (int) (random() * letters.length());
                pw += letters.substring(index, index + 1);
            }
            String text = String.valueOf(pw);

            //String text = "This is awesome!";
            int offset = 5;

            String enc = encrypt(text, offset);

            System.out.println("Encrypted text: " + enc);
            String deadmic = decrypt(enc, offset);

            //  System.out.println ( "Decrypted text: " + dec);
            Calendar c = Calendar.getInstance();
            String[] colon = {"Code_licence", "Jour", "Mois", "Annee", "Test"};
            String[] inf = {enc, String.valueOf(c.get(Calendar.DATE)), String.valueOf(c.get(Calendar.MONTH) + 1), String.valueOf(c.get(Calendar.YEAR)), "1"};

            String aa = "1";

            System.out.println(db.queryUpdate("license", colon, inf, "Id='" + aa + "'"));

            JOptionPane.showMessageDialog(null, "Redémarrer votre logiciel ");
            System.exit(0);

        }
        String Code_licence = String.valueOf(jTable3.getValueAt(0, 1));
        String Jour = String.valueOf(jTable3.getValueAt(0, 2));
        String Mois = String.valueOf(jTable3.getValueAt(0, 3));
        String Annee = String.valueOf(jTable3.getValueAt(0, 4));
        int offset = 5;
        String dec = decrypt(Code_licence, offset);

        cp = new ConnectComPort();
        String b[] = {"ID", "Licence"};
        rs = cp.querySelect(b, "Licence");
        TableModel jTable = DbUtils.resultSetToTableModel(rs);
        String code = String.valueOf(jTable.getValueAt(0, 1));
        Calendar expireDate = Calendar.getInstance();
        // January is 0 (y, m, d)
        int y = Integer.parseInt(Annee);
        int m = Integer.parseInt(Mois) ;// January is 0
        if (m > 11) {
            y = y + 1;
            m = m - 11;
        }
        int d = Integer.parseInt(Jour);

        String Code1 = dec;
        System.out.println("Decrypted text: " + Code1);

        String Code2 = "PPAS-AQXP-MLSD-PLZS";
        String Code3 = "VBEZ-LPDE-NNBN-DDBZ";
        String Code4 = "KSIS-FODS-CXVD-LOPP";
        String Code5 = "AAZC-AAZV-AAZB-AAZN";
        if (code.equals(Code2)) {
            m = m + 1;
            if (m > 11) {
                m = 0;
                y = y + 1;
            }
        } else if (code.equals(Code3)) {
            m = m + 2;
            if (m > 11) {
                m = 0;
                y = y + 1;
            }
        } else if (code.equals(Code4)) {
            m = m + 3;
            if (m > 11) {
                m = 0;
                y = y + 1;
            }
        } else if (code.equals(Code5)) {
            m = m + 4;
            if (m > 11) {
                m = 0;
                y = y + 1;
            }
        }

        expireDate.set(y, m, d);

        if (Calendar.getInstance().after(expireDate) && !code.equals(Code1)) {
            // Die
            JOptionPane.showMessageDialog(null, "Version d'essai expiré ! entrer un code de licence \n Veuillez contacter Mestiri Pesage \n Pour demander votre code merci de nous envoyer ce Code : " + Code_licence + " par SMS à 27 712 000");
            System.exit(0);
        }
        if (Calendar.getInstance().equals(expireDate) && !code.equals(Code1)) {
            JOptionPane.showMessageDialog(null, "Dernier jour d'essaie ! ");

        }
    }
}

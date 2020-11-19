package pkgnew.app;

import javax.swing.JOptionPane;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.Doc;
import javax.print.ServiceUI;
import javax.print.attribute.*;

public class Ticket1 {

    //Ticket attribute content
    private String contentTicket = "**************************************\n"
            + "TICKET DE PESAGE\n"
            + "{{NomSoci}}\n"
            + "{{AdresseSoci}} \n"
            + "=====================================\n"
            + "NUMERO TICKET : {{NumroTicket}}\n"
            + "=====================================\n"
            + "Totale : {{Total}} DT\n"
            + "=====================================\n"
               
            + "Date : {{Date}}\n"
            + "Heure : {{Heure}}\n"
           ;

    //El constructor que setea los valores a la instancia
    Ticket1(String NomSoci, String AdresseSoci, String numeroTicket, String total, String date, String heure) {
        this.contentTicket = this.contentTicket.replace("{{NomSoci}}", NomSoci);
        this.contentTicket = this.contentTicket.replace("{{AdresseSoci}}", AdresseSoci);
        this.contentTicket = this.contentTicket.replace("{{NumroTicket}}", numeroTicket);
        
        this.contentTicket = this.contentTicket.replace("{{Total}}", total);
        this.contentTicket = this.contentTicket.replace("{{Date}}", date);
        this.contentTicket = this.contentTicket.replace("{{Heure}}", heure);

    }

    public void printString(String printerName, String text) {

        // find the printService of name printerName
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        PrintService service = findPrintService(printerName, printService);

        DocPrintJob job = service.createPrintJob();

        try {

            byte[] bytes;

            // important for umlaut chars
            bytes = text.getBytes("CP437");

            Doc doc = new SimpleDoc(bytes, flavor, null);

            job.print(doc, null);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void printBytes(String printerName, byte[] bytes) {

        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        PrintService service = findPrintService(printerName, printService);

        DocPrintJob job = service.createPrintJob();

        try {

            Doc doc = new SimpleDoc(bytes, flavor, null);

            job.print(doc, null);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de coupe: " + e.getMessage());
        }
    }

    private PrintService findPrintService(String printerName, PrintService[] services) {
        for (PrintService service : services) {
            if (service.getName().equalsIgnoreCase(printerName)) {
                return service;
            }
        }

        return null;
    }

    public void print() {

        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();

        byte[] bytes;
        byte[] cutP = new byte[]{0x1d, 'V', 1};

        bytes = this.contentTicket.getBytes();

        Doc doc = new SimpleDoc(bytes, flavor, null);

        try {
            DocPrintJob job = service.createPrintJob();
            job.print(doc, null);
            printBytes("SLK-TE212", cutP);

        } catch (Exception er) {
          
        }
    }

}

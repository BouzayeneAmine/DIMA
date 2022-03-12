package Gestion;

import javax.swing.JOptionPane;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.Doc;
import javax.print.ServiceUI;
import javax.print.attribute.*;

public class Ticket {

    //Ticket attribute content
    private String contentTicket = "**************************************\n"
            + "TICKET DE PESAGE\n"
            + "{{NomSoci}}\n"
            + "{{AdresseSoci}} \n"
            + "TEL :{{TelSoci}} \n"
            + "FAX :{{FaxSoci}} \n"
            + "**************************************\n"
            + "NUMERO TICKET : {{Numero}}\n"
            + "=====================================\n"
            + "POIDS NET : {{PoidsNet}} KG\n"
            + "=====================================\n"
            + "{{Type}}\n"
            + "Votre Code : {{Code}}\n"
            + "Votre Nom : {{Nom}}\n"
            + "Votre Adresse : {{Adresse}}\n"
            + "=====================================\n"
            + "PRODUIT\n"
            + "Code produit {{CodeProduit}}\n"
            + "Designation produit : {{DesignationProduit}}\n"
            + "Lot Produit : {{Lot}}\n"
            + "Prix Produit : {{Prix}}\n"
            + "Coefficeint Produit : {{Coefficeint}}\n"
            + "=====================================\n"
            + "CAMION\n"
            + "Immatricule : {{Immatricule}}\n"
            + "Type : {{TypeCamion}}\n"
            + "Tare : {{Tare}} KG\n"
            + "=====================================\n"
            + "PREMIER PESAGE : {{Premier}} KG\n"
            + "Date : {{DateEntree}}\n"
            + "Heure : {{HeureEntree}}\n"
            + "=====================================\n"
            + "DEUXIEME PESAGE : {{Deuxieme}} KG\n"
            + "Date : {{DateSortie}}\n"
            + "Heure : {{HeureSortie}}\n"
            + "=====================================\n"
            + "\n"
            + "Merci pour votre visite...\n"
            + "\n"
            + "**************************************\n"
            + "\n"
            + "\n"
            + "\n"
            + "\n";

    //El constructor que setea los valores a la instancia
    Ticket(String NomSoci, String AdresseSoci, String TelSoci, String FaxSoci, String Numero, String Type, String Code, String Nom, String Adresse, String CodeProduit, String DesignationProduit, String Lot, String Prix, String Coefficeint,
            String Immatricule, String TypeCamion, String Tare, String Premier, String DateEntree, String HeureEntree, String Deuxieme, String DateSortie, String HeureSortie, String PoidsNet) {
        this.contentTicket = this.contentTicket.replace("{{NomSoci}}", NomSoci);
        this.contentTicket = this.contentTicket.replace("{{AdresseSoci}}", AdresseSoci);
        this.contentTicket = this.contentTicket.replace("{{TelSoci}}", TelSoci);
        this.contentTicket = this.contentTicket.replace("{{FaxSoci}}", FaxSoci);
        this.contentTicket = this.contentTicket.replace("{{Numero}}", Numero);
        this.contentTicket = this.contentTicket.replace("{{Type}}", Type);
        this.contentTicket = this.contentTicket.replace("{{Code}}", Code);
        this.contentTicket = this.contentTicket.replace("{{Nom}}", Nom);
        this.contentTicket = this.contentTicket.replace("{{Adresse}}", Adresse);
        this.contentTicket = this.contentTicket.replace("{{CodeProduit}}", CodeProduit);
        this.contentTicket = this.contentTicket.replace("{{DesignationProduit}}", DesignationProduit);
        this.contentTicket = this.contentTicket.replace("{{Lot}}", Lot);
        this.contentTicket = this.contentTicket.replace("{{Prix}}", Prix);
        this.contentTicket = this.contentTicket.replace("{{Coefficeint}}", Coefficeint);
        this.contentTicket = this.contentTicket.replace("{{Immatricule}}", Immatricule);
        this.contentTicket = this.contentTicket.replace("{{TypeCamion}}", TypeCamion);
        this.contentTicket = this.contentTicket.replace("{{Tare}}", Tare);
        this.contentTicket = this.contentTicket.replace("{{Premier}}", Premier);
        this.contentTicket = this.contentTicket.replace("{{DateEntree}}", DateEntree);
        this.contentTicket = this.contentTicket.replace("{{HeureEntree}}", HeureEntree);
        this.contentTicket = this.contentTicket.replace("{{Deuxieme}}", Deuxieme);
        this.contentTicket = this.contentTicket.replace("{{DateSortie}}", DateSortie);
        this.contentTicket = this.contentTicket.replace("{{HeureSortie}}", HeureSortie);
        this.contentTicket = this.contentTicket.replace("{{PoidsNet}}", PoidsNet);
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

    private PrintService findPrintService(String printerName,PrintService[] services) {
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
        PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, pras);

        byte[] bytes;
        byte[] cutP = new byte[]{0x1d, 'V', 1};

        bytes = this.contentTicket.getBytes();

        Doc doc = new SimpleDoc(bytes, flavor, null);

        try {
            DocPrintJob job = service.createPrintJob();
            job.print(doc, null);
            printBytes("SLK-TE212", cutP);

        } catch (Exception er) {
            JOptionPane.showMessageDialog(null, "Erreur d'impression Ticket: " + er.getMessage());
        }
    }

}
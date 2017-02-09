package dorel.crm.preluari;

import dorel.aplicatie.interfaces.CommonInterface;
import dorel.aplicatie.interfaces.TabelaSqlInterface;
import dorel.basicopp.excelreport.FisExcel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import javax.swing.JOptionPane;

public class Excel_1 {

    public DateFormat dfm;
    public Calendar cal;

    public Excel_1() {
        dfm = new SimpleDateFormat("dd.MM.yyyy");
        dfm.setTimeZone(TimeZone.getTimeZone("Europe/Bucharest")); // EET, Eastern Eeuropean Time
        cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Bucharest"));
    }

    public void preiaFisier(CommonInterface common, String numeFisExcel, String modFact) {
        //String numeFisExcel = "D:\\Lucru\\Java\\CRM\\Mondosoft 2017.xlsx";
        FisExcel fe = new FisExcel(numeFisExcel, FisExcel.TipFisExcel.xls);
        fe.openFile();
        fe.setCurrentSheet("Sheet1", false);
        for (int i = 5; i < 575; i++) {
            fe.setCurrentRow(i, false);  // nrRow incep de la 0 (nrRowExcel-1)
            String numar = fe.getStringCellValue(1);  // nrCol incep de la 0 (nrColExcel-1)
            Date data = fe.getDateCellValue(2);
            String denumire = fe.getStringCellValue(3);
            String denumire_posta = fe.getStringCellValue(4);
            String cui = fe.getStringCellValue(5);
            String strada = fe.getStringCellValue(6);
            String nr_strada = fe.getStringCellValue(7);
            String localitate = fe.getStringCellValue(8);
            String judet = fe.getStringCellValue(9);
            String cod_judet = fe.getStringCellValue(10);
            String cont_banca = fe.getStringCellValue(11);
            String banca = fe.getStringCellValue(12);
            Date dela_data = fe.getDateCellValue(13);
            Date panala_data = fe.getDateCellValue(14);

            //double cotaTVA = fe.getDoubleCellValue(15);
            //double valoare = fe.getDoubleCellValue(16);
            //double valTVA = fe.getDoubleCellValue(17);
            //double total = fe.getDoubleCellValue(18);
            //
            //String cotaTVA = fe.getStringCellValue(15); // referinta
            String valoare = fe.getStringCellValue(16);
            //String valTVA = fe.getStringCellValue(17);  // calculat
            // String total = fe.getStringCellValue(18);  // calculat

            String continut = fe.getStringCellValue(19);
            String observatii = fe.getStringCellValue(20);
            String telefon = fe.getStringCellValue(21);
            String cod_postal = fe.getStringCellValue(22);
            String primar = fe.getStringCellValue(23);
            String contabil = fe.getStringCellValue(24);
            String functie = fe.getStringCellValue(25);
            //
            TabelaSqlInterface client = common.getTabela("clienti");
            Map<String, String> mapValoriClienti = new HashMap<>();
            mapValoriClienti.put("denumire", denumire);
            mapValoriClienti.put("denumire_posta", denumire_posta);
            mapValoriClienti.put("atribut_fiscal", "");
            mapValoriClienti.put("cui", cui);
            mapValoriClienti.put("judet_id", getIdJudet(cod_judet));
            //mapValoriClienti.put("localitate_id", localitate);
            mapValoriClienti.put("strada", strada);
            mapValoriClienti.put("nr_strada", nr_strada);
            mapValoriClienti.put("cod_postal", cod_postal);
            mapValoriClienti.put("cont_banca", cont_banca);
            mapValoriClienti.put("banca", banca);
            mapValoriClienti.put("telefon", telefon);
            mapValoriClienti.put("functie_conducator", functie);
            mapValoriClienti.put("conducator", primar);
            mapValoriClienti.put("functie_contabil", "Contabil");
            mapValoriClienti.put("contabil", contabil);
            client.puneValoriDinMap(mapValoriClienti);
            client.setId(0);
            common.getDataSource().saveInreg(client);
            int idClient = client.getId();
            //
            TabelaSqlInterface contracte = common.getTabela("contracte");
            Map<String, String> mapValoriContracte = new HashMap<>();
            mapValoriContracte.put("tip_contract", "Mentenanta");
            mapValoriContracte.put("client_id", String.valueOf(idClient));
            mapValoriContracte.put("furnizor_id", "1");
            //Mentenanta
            mapValoriContracte.put("numar", numar);
            //mapValoriContracte.put("data", dfm.format(data));
            if (data == null) {
                mapValoriContracte.put("data", "");
            } else {
                mapValoriContracte.put("data", dfm.format(data));
            }
            //mapValoriContracte.put("dela_data", dfm.format(dela_data));
            //if (dela_data == null || dela_data.equals("")) {
            if (dela_data == null) {
                mapValoriContracte.put("dela_data", "");
            } else {
                mapValoriContracte.put("dela_data", dfm.format(dela_data));
            }
            //mapValoriContracte.put("panala_data", dfm.format(panala_data));
            //if (panala_data == null || panala_data.equals("")) {
            if (panala_data == null) {
                mapValoriContracte.put("panala_data", "");
            } else {
                mapValoriContracte.put("panala_data", dfm.format(panala_data));
            }
            mapValoriContracte.put("continut", continut);
            mapValoriContracte.put("valoare", valoare);
            mapValoriContracte.put("observatii", observatii);
            mapValoriContracte.put("mod_fact", modFact);
            contracte.puneValoriDinMap(mapValoriContracte);
            contracte.setId(0);
            common.getDataSource().saveInreg(contracte);
        }

    }

    private String getIdJudet(String cod) {
        switch (cod) {
            case "[AG]":
                return "3";
            case "[B]":
                return "42";
            case "[BC]":
                return "4";
            case "[BR]":
                return "9";
            case "[BZ]":
                return "10";
            case "[CL]":
                return "12";
            case "[CS]":
                return "11";
            case "[CT]":
                return "14";
            case "[DB]":
                return "16";
            case "[DJ]":
                return "17";
            case "[GJ]":
                return "20";
            case "[GL]":
                return "18";
            case "[GR]":
                return "19";
            case "[IF]":
                return "25";
            case "[MS]":
                return "28";
            case "[OT]":
                return "30";
            case "[PH]":
                return "31";
            case "[TL]":
                return "38";
            case "[TM]":
                return "37";
            case "[TR]":
                return "36";
            case "[VL]":
                return "40";
        }
        JOptionPane.showMessageDialog(null, "Cod judet necunoscut:" + cod);
        return "1";
    }
}

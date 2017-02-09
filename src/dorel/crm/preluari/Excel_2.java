package dorel.crm.preluari;

import dorel.aplicatie.interfaces.CommonInterface;
import dorel.aplicatie.interfaces.TabelaSqlInterface;
import dorel.basicopp.excelreport.FisExcel;
import dorel.basicopp.io.Director;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Excel_2 {

    public DateFormat dfm;
    public Calendar cal;
    CommonInterface common;

    public Excel_2(CommonInterface common) {
        dfm = new SimpleDateFormat("dd.MM.yyyy");
        dfm.setTimeZone(TimeZone.getTimeZone("Europe/Bucharest")); // EET, Eastern Eeuropean Time
        cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Bucharest"));
        this.common=common;
    }

    public void preiaFisier() {
        String numeDir = "D:\\Lucru\\Java\\CRM\\Contracte";
        Director dir = new Director(numeDir);
        List<File> listaFisiere = dir.getFiles();
        List<File> listaFisiereXls = new ArrayList<>();
        for (File fis : listaFisiere) {
            if (fis.getName().endsWith(".xls")) {
                listaFisiereXls.add(fis);
            }
        }
        for (File fis : listaFisiereXls) {
            preiauUnFisier(fis);
            //break;  // intrerup executia
        }
    }

    private void preiauUnFisier(File fis) {
        String numeFisExcel = fis.getAbsolutePath();
        System.out.println(numeFisExcel);
        FisExcel fe = new FisExcel(numeFisExcel, FisExcel.TipFisExcel.xls);
        fe.openFile();
        fe.setCurrentSheet("Judet", false);
        String denumire = "PRIMARIA";
        for (int i=2; i<1000; i++){  // pt a nu merge la infinit
            fe.setCurrentRow(i, false);
            denumire = fe.getStringCellValue(1);
            if (denumire.isEmpty()){
                break;
            }
            //
            TabelaSqlInterface client = common.getTabela("clienti");
            Map<String, String> mapValoriClienti = new HashMap<>();
            mapValoriClienti.put("denumire", denumire);
            client.puneValoriDinMap(mapValoriClienti);
            common.getDataSource().saveInreg(client);
            int idClient = client.getId();
            //
            TabelaSqlInterface contracte = common.getTabela("contracte");
            Map<String, String> mapValoriContracte = new HashMap<>();
            mapValoriContracte.put("numar", fe.getStringCellValue(4));
            mapValoriContracte.put("client_id", String.valueOf(idClient));
            contracte.puneValoriDinMap(mapValoriContracte);
            common.getDataSource().saveInreg(contracte);
            
        }
    }

    //return;
//        String numeFisExcel = "D:\\Lucru\\Java\\CRM\\Mondosoft 2017.xls";
//        //String numeFisExcel = "D:\\Lucru\\Java\\CRM\\Mondosoft 2017.xlsx";
//        FisExcel fe = new FisExcel(numeFisExcel, FisExcel.TipFisExcel.xls);
//        fe.openFile();
//        fe.setCurrentSheet("Sheet1", false);
//        for (int i = 5; i < 575; i++) {
//            fe.setCurrentRow(5, false);  // nrRow incep de la 0 (nrRowExcel-1)
//            String numar = fe.getStringCellValue(1);  // nrCol incep de la 0 (nrColExcel-1)
//            Date data = fe.getDateCellValue(2);
//            String denumire = fe.getStringCellValue(3);
//            String denumire_posta = fe.getStringCellValue(4);
//            String cui = fe.getStringCellValue(5);
//            String strada = fe.getStringCellValue(6);
//            String nr_strada = fe.getStringCellValue(7);
//            String localitate = fe.getStringCellValue(8);
//            String judet = fe.getStringCellValue(9);
//            String cont_banca = fe.getStringCellValue(11);
//            String trezoreria = fe.getStringCellValue(12);
//            Date dela_data = fe.getDateCellValue(13);
//            Date panala_data = fe.getDateCellValue(14);
//
//            //double cotaTVA = fe.getDoubleCellValue(15);
//            //double valoare = fe.getDoubleCellValue(16);
//            //double valTVA = fe.getDoubleCellValue(17);
//            //double total = fe.getDoubleCellValue(18);
//            //
//            //String cotaTVA = fe.getStringCellValue(15); // referinta
//            String valoare = fe.getStringCellValue(16);
//            //String valTVA = fe.getStringCellValue(17);  // calculat
//            // String total = fe.getStringCellValue(18);  // calculat
//
//            String tipContract = fe.getStringCellValue(19);
//            String observatii = fe.getStringCellValue(20);
//            String telefon = fe.getStringCellValue(21);
//            String cod_postal = fe.getStringCellValue(22);
//            String primar = fe.getStringCellValue(23);
//            String contabil = fe.getStringCellValue(24);
//            String functie = fe.getStringCellValue(25);
//            //
//            TabelaSqlInterface client = common.getTabela("clienti");
//            Map<String, String> mapValoriClienti = new HashMap<>();
//            mapValoriClienti.put("denumire", denumire);
//            mapValoriClienti.put("denumire_posta", denumire_posta);
//            mapValoriClienti.put("cui", cui);
//            mapValoriClienti.put("judet", judet);
//            mapValoriClienti.put("localitate", localitate);
//            mapValoriClienti.put("strada", strada);
//            mapValoriClienti.put("nr_strada", nr_strada);
//            mapValoriClienti.put("cod_postal", cod_postal);
//            mapValoriClienti.put("cont_banca", cont_banca);
//            mapValoriClienti.put("trezoreria", trezoreria);
//            mapValoriClienti.put("telefon", telefon);
//            mapValoriClienti.put("primar", primar);
//            mapValoriClienti.put("contabil", contabil);
//            mapValoriClienti.put("functie", functie);
//            client.puneValoriDinMap(mapValoriClienti);
//            common.getDataSource().saveInreg(client);
//            int idClient = client.getId();
//            //
//            TabelaSqlInterface contracte = common.getTabela("contracte");
//            Map<String, String> mapValoriContracte = new HashMap<>();
//            mapValoriContracte.put("numar", numar);
//            mapValoriContracte.put("data", dfm.format(data));
//            mapValoriContracte.put("dela_data", dfm.format(dela_data));
//            mapValoriContracte.put("panala_data", dfm.format(panala_data));
//            mapValoriContracte.put("valoare", valoare);
//            mapValoriContracte.put("client_id", String.valueOf(idClient));
//            mapValoriContracte.put("observatii", observatii);
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
////            mapValoriContracte.put("", );
//            contracte.puneValoriDinMap(mapValoriContracte);
//            common.getDataSource().saveInreg(contracte);
//        }
}

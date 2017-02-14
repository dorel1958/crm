package dorel.crm.clase;

import dorel.aplicatie.interfaces.CommonInterface;
import dorel.aplicatie.interfaces.PopuleazaInterface;
import dorel.aplicatie.interfaces.TabelaSqlInterface;
import dorel.basicopp.Eroare;
import dorel.basicopp.datatypes.MyString;
import dorel.basicopp.datatypes.Numere;
import dorel.basicopp.excelreport.FisExcel;
import dorel.basicopp.io.TextReader;
import dorel.basicopp.sumecontrol.Cui;
import dorel.basicopp.sumecontrol.Iban;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.swing.JOptionPane;

public class PopuleazaCRM implements PopuleazaInterface {

    CommonInterface common;
    public DateFormat dfm;
    public Calendar cal;
    Eroare eroare;

    public PopuleazaCRM(CommonInterface common) {
        this.common = common;
        dfm = new SimpleDateFormat("dd.MM.yyyy");
        dfm.setTimeZone(TimeZone.getTimeZone("Europe/Bucharest")); // EET, Eastern Eeuropean Time
        cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Bucharest"));
    }

    @Override
    public void populeazaTabela(String numeTabela) {
        switch (numeTabela) {
            case "setari":
                populeazaSetari();
                break;
            case "judete":
                populeazaJudete();
                break;
            case "localitati":
                populeazaLocalitati();
                break;
            case "conturi_banca":
                populeazaConturiBanca();
                break;
            case "furnizori":
                populeazaFurnizori();
                break;
            case "contracte":
                // mondosoft
                eroare = new Eroare();
                eroare.setVerbose(true);
                eroare.setShowMessageCorect(false);
                preiaUnFisier(common, "D:\\Lucru\\Java\\CRM\\Mondosoft 2017.xls", "Lunar", 1);
                //Valoarea '4992998' din coloana cui mai există.
                //Valoarea '2845346' din coloana cui mai există.
                //best
                //...
                eroare.afisazaFisierErori();
                break;
            default:
                break;
        }
    }

    //<editor-fold defaultstate="collapsed" desc="setari">
    private void populeazaSetari() {
        saveOSetare("cota_tva", "19", "Cota TVA folosită la facturare.");
    }

    // VX 589422
    // 1591213352361
    private void saveOSetare(String denumire, String valoare, String explicatii) {
        TabelaSqlInterface setari = common.getTabela("setari");
        Map<String, String> mapValori = new HashMap<>();
        mapValori.put("denumire", denumire);
        mapValori.put("valoare", valoare);
        mapValori.put("explicatii", explicatii);
        setari.puneValoriDinMap(mapValori);
        setari.setId(0);
        common.getDataSource().saveInreg(setari);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="judete">
    private void populeazaJudete() {
        saveUnJudet("01", "Alba", "AB");
        saveUnJudet("02", "Arad", "AR");
        saveUnJudet("03", "Argeș", "AG");
        saveUnJudet("04", "Bacău", "BC");
        saveUnJudet("05", "Bihor", "BH");
        saveUnJudet("06", "Bistrița-Năsăud", "BN");
        saveUnJudet("07", "Botoșani", "BT");
        saveUnJudet("08", "Brașov", "BV");
        saveUnJudet("09", "Brăila", "BR");
        saveUnJudet("10", "Buzău", "BZ");
        saveUnJudet("11", "Caraș-Severin", "CS");
        saveUnJudet("12", "Călărași", "CL");
        saveUnJudet("13", "Cluj", "CJ");
        saveUnJudet("14", "Constanța", "CT");
        saveUnJudet("15", "Covasna", "CV");
        saveUnJudet("16", "Dâmbovița", "DB");
        saveUnJudet("17", "Dolj", "DJ");
        saveUnJudet("18", "Galați", "GL");
        saveUnJudet("19", "Giurgiu", "GR");
        saveUnJudet("20", "Gorj", "Gj");
        saveUnJudet("21", "Harghita", "HG");
        saveUnJudet("22", "Hunedoara", "HR");
        saveUnJudet("23", "Ialomița", "IL");
        saveUnJudet("24", "Iași", "IS");
        saveUnJudet("25", "Ilfov", "IF");
        saveUnJudet("26", "Maramureș", "MM");
        saveUnJudet("27", "Mehedinți", "MH");
        saveUnJudet("28", "Mureș", "MS");
        saveUnJudet("29", "Neamț", "NT");
        saveUnJudet("30", "Olt", "OT");
        saveUnJudet("31", "Prahova", "PH");
        saveUnJudet("32", "Satu-Mare", "SM");
        saveUnJudet("33", "Sălaj", "SJ");
        saveUnJudet("34", "Sibiu", "SB");
        saveUnJudet("35", "Suceava", "SV");
        saveUnJudet("36", "Teleorman", "TR");
        saveUnJudet("37", "Timiș", "TM");
        saveUnJudet("38", "Tulcea", "TL");
        saveUnJudet("39", "Vaslui", "VS");
        saveUnJudet("40", "Vâlcea", "VL");
        saveUnJudet("41", "Vrancea", "VN");
        saveUnJudet("42", "Mun. București", "B");
    }

    private void saveUnJudet(String cod, String denumire, String prescurtare) {
        TabelaSqlInterface judet = common.getTabela("judete");
        Map<String, String> mapValoriJudete = new HashMap<>();
        mapValoriJudete.put("denumire", denumire);
        mapValoriJudete.put("cod", cod);
        mapValoriJudete.put("prescurtare", prescurtare);
        judet.puneValoriDinMap(mapValoriJudete);
        judet.setId(0);
        common.getDataSource().saveInreg(judet);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="localitati">
    private void populeazaLocalitati() {
        String numeFis = "date\\localitati.txt";
        File fis = new File(numeFis);
        if (fis.exists()) {
            TextReader tr = new TextReader(numeFis);
            String line;
            while ((line = tr.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] aLine = line.split(",");
                    switch (aLine.length) {
                        case 3:
                            switch (aLine[2]) {
                                case "sat":
                                    break;
                                case "sector":
                                    saveLocalitate(Numere.stringToInt(aLine[0]), aLine[1], "Oraș");
                                    break;
                                case "oras":
                                    saveLocalitate(Numere.stringToInt(aLine[0]), aLine[1], "Oraș");
                                    break;
                                case "municipiu":
                                    saveLocalitate(Numere.stringToInt(aLine[0]), aLine[1], "Municipiu");
                                    break;
                                default:
                                    System.out.println("aLine[2] necunoscut:|" + aLine[2] + "|");
                            }
                            break;
                        case 4:
                            if (aLine[3].equals("comuna")) {
                                saveLocalitate(Numere.stringToInt(aLine[0]), aLine[1], "Comună");
                            } else {
                                System.out.println("PopuleazaCRM.populeazaLocalitati - aLine[3] nu este comuna:|" + aLine[3] + "|");
                            }
                            break;
                        default:
                            System.out.println("PopuleazaCRM.populeazaLocalitati - aLine.length: " + aLine.length);
                            break;
                    }
                }
            }
            tr.close();
        }
    }

    private void saveLocalitate(int cod_judet, String denumire, String denumireTipUAT) {
        TabelaSqlInterface localitate = common.getTabela("localitati");
        Map<String, String> mapValori = new HashMap<>();
        mapValori.put("judet_id", String.valueOf(cod_judet));
        mapValori.put("denumire", denumire);
        mapValori.put("tip_uat", denumireTipUAT);
        //mapValoriClienti.put("cod_siruta", codSIRUTA);
        localitate.puneValoriDinMap(mapValori);
        localitate.setId(0);
        common.getDataSource().saveInreg(localitate);

    }
//    private void populeazaLocalitatiExcel() {
//        String numeFisExcel = "D:\\Lucru\\Java\\CRM\\cod_siruta_si_uat_pe_judete.xls";
//        FisExcel fe = new FisExcel(numeFisExcel, FisExcel.TipFisExcel.xls);
//        fe.openFile();
//        fe.setCurrentSheet("toata tara", false);
//        for (int i = 8; i < 3236; i++) {
//            fe.setCurrentRow(i, false);  // nrRow incep de la 0 (nrRowExcel-1)
//            String codJudet = fe.getStringCellValue(1);  // nrCol incep de la 0 (nrColExcel-1)
//            //String codTipUAT = fe.getStringCellValue(3);
//            //String denumireTipUAT = fe.getStringCellValue(4);
//            String denumireTipUAT = "";
//            switch (fe.getStringCellValue(3)) {
//                case "11":
//                    denumireTipUAT = "Consiliu judetean";
//                    break;
//                case "12":
//                    denumireTipUAT = "Municipiu";
//                    break;
//                case "13":
//                    denumireTipUAT = "Oraș";
//                    break;
//                case "14":
//                    denumireTipUAT = "Comună";
//                    break;
//                case "15":
//                    denumireTipUAT = "Primăria M. Buc";
//                    break;
//                case "16":
//                    denumireTipUAT = "Primăria de sector al M. Buc.";
//                    break;
//
//            }
//            String codSIRUTA = fe.getStringCellValue(5);
//            //String denumire = fe.getStringCellValue(6);
//            String denumire = fe.getStringCellValue(8);
//            if (denumire.startsWith("Comuna ") || denumire.startsWith("COMUNA ")) {
//                denumire = denumire.substring(7);
//            }
//            if (denumire.startsWith("Mun. ") || denumire.startsWith("MUN. ")) {
//                denumire = denumire.substring(5);
//            }
//            if (denumire.startsWith("Municipiul ")) {
//                denumire = denumire.substring(11);
//            }
//            if (denumire.startsWith("Oras ") || denumire.startsWith("ORAS ")) {
//                denumire = denumire.substring(5);
//            }
//            if (denumire.startsWith("Primaria ")) {
//                denumire = denumire.substring(9);
//            }
//            //
//            TabelaSqlInterface localitate = common.getTabela("localitati");
//            Map<String, String> mapValoriClienti = new HashMap<>();
//            mapValoriClienti.put("judet_id", String.valueOf(Numere.stringToInt(codJudet)));
//            mapValoriClienti.put("denumire", denumire);
//            mapValoriClienti.put("tip_uat", denumireTipUAT);
//            mapValoriClienti.put("cod_siruta", codSIRUTA);
//            localitate.puneValoriDinMap(mapValoriClienti);
//            localitate.setId(0);
//            common.getDataSource().saveInreg(localitate);
//        }
//    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="conturi_banca">
    private void populeazaConturiBanca() {
        // MONDOSOFT
        saveUnContBanca(1, "TREZORERIA RM. VALCEA", "RO65TREZ6715069XXX000405");
        saveUnContBanca(1, "BRD BANK RM. VALCEA", "RO52BRDE390SV38736083900");
        saveUnContBanca(1, "UNICREDIT TIRIAC BANK RM. VALCEA", "RO60BACX0000000172893007");
        saveUnContBanca(1, "CEC BANK RM. VALCEA", "RO05CECEVL0101RON0378476");
        saveUnContBanca(1, "ING BANK RM. VALCEA", "RO74INGB5587999900567136");
        // INCOM
        saveUnContBanca(2, "TREZORERIA RM. VALCEA", "RO38TREZ6715069XXX004480");
        saveUnContBanca(2, "UNICREDIT TIRIAC BANK RM. VALCEA", "RO83BACX0000000194158000");
        // Viltech
        saveUnContBanca(3, "TREZORERIA RM. VALCEA", "RO65TREZ6715069XXX004479");
        saveUnContBanca(3, "UNICREDIT TIRIAC BANK RM. VALCEA", "RO39BACX0000000194165000");
        // Best
        saveUnContBanca(4, "TREZORERIA RM. VALCEA", "RO35TREZ6715069XXX004243");
        saveUnContBanca(4, "UNICREDIT TIRIAC BANK RM. VALCEA", "RO84BACX0000000194138000");
        // Astech
        saveUnContBanca(5, "TREZORERIA RM. VALCEA", "RO92TREZ6715069XXX004478");
        saveUnContBanca(5, "UNICREDIT TIRIAC BANK RM. VALCEA", "RO25BACX0000000194154000");
        saveUnContBanca(5, "ING BANK RM. VALCEA", "RO84INGB0000999901409585");
        saveUnContBanca(5, "CEC BANK RM. VALCEA", "RO38CECEVL0130RON0579692");
        // Elida
        saveUnContBanca(6, "TREZORERIA RM. VALCEA", "RO84TREZ6715069XXX001421");
        saveUnContBanca(6, "UNICREDIT TIRIAC BANK RM. VALCEA", "RO12BACX0000000768945000");
        // Profix
        saveUnContBanca(7, "TREZORERIA RM. VALCEA", "RO08TREZ6715069XXX004244");
        saveUnContBanca(7, "UNICREDIT TIRIAC BANK RM. VALCEA", "RO44BACX0000000194162000");
    }

    private void saveUnContBanca(int furnizor_id, String banca, String cont) {
        Iban iban = new Iban();
        if (!iban.testIBAN(cont)) {
            JOptionPane.showMessageDialog(null, "Eroare IBAN:" + cont + " " + iban.getMesajEroare());
        }
        TabelaSqlInterface conturi_banca = common.getTabela("conturi_banca");
        Map<String, String> mapValoriJudete = new HashMap<>();
        mapValoriJudete.put("furnizor_id", String.valueOf(furnizor_id));
        mapValoriJudete.put("banca", banca);
        mapValoriJudete.put("cont", cont);
        conturi_banca.puneValoriDinMap(mapValoriJudete);
        conturi_banca.setId(0);
        common.getDataSource().saveInreg(conturi_banca);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="furnizori">
    private void populeazaFurnizori() {
        saveUnFurnizor("SC Mondofsoft srl", "J38/246/13.08.1999", "RO", "12054381", "VALCEA, RM VALCEA, STR. OSTROVENI, NR 136 A", "VX 589422", "1591213352361", "intocmit");
        saveUnFurnizor("SC Mondosoft Incom srl", "J38/524/29.05.2007", "", "21821513", "VALCEA, RM VALCEA, STR. OSTROVENI, NR 136 A", "VX 589422", "1591213352361", "intocmit");
        saveUnFurnizor("SC Mondosoft Viltech srl", "J38/523/29.03.2007", "", "21821505", "VALCEA, RM VALCEA, STR. OSTROVENI, NR 136 A", "VX 589422", "1591213352361", "intocmit");
        saveUnFurnizor("SC Mondosoft Best srl", "J38/1107/19.12.2006", "", "20065446", "VALCEA, RM VALCEA, STR. OSTROVENI, NR 136 A", "VX 589422", "1591213352361", "intocmit");
        saveUnFurnizor("SC Mondosoft Astech srl", "J8/521/29.05.2007", "RO", "21821521", "VALCEA, RM VALCEA, STR. OSTROVENI, NR 136 A", "VX 589422", "1591213352361", "intocmit");
        saveUnFurnizor("SC Elida srl", "J38/513/06.05.1994", "", "5682065", "VALCEA, RM VALCEA, STR. OSTROVENI, NR 136 A", "VX 589422", "1591213352361", "intocmit");
        saveUnFurnizor("SC Mondosoft Profix srl", "J38/1104/19.12.2006", "", "20065411", "VALCEA, RM VALCEA, STR. OSTROVENI, NR 136 A", "VX 589422", "1591213352361", "intocmit");
    }

    // VX 589422
    // 1591213352361
    private void saveUnFurnizor(String denumire, String reg_com, String atr_fiscal, String cui, String adresa, String ci_intocmit, String cnp_intocmit, String intocmit) {
        Cui testCui = new Cui();
        if (!testCui.testCUI(cui, false)) {
            JOptionPane.showMessageDialog(null, "Eroare CUI:" + cui + " " + testCui.getMesajEroare());
        }

        TabelaSqlInterface firma = common.getTabela("furnizori");
        Map<String, String> mapValori = new HashMap<>();
        mapValori.put("denumire", denumire);
        mapValori.put("reg_com", reg_com);
        mapValori.put("atr_fiscal", atr_fiscal);
        mapValori.put("cui", cui);
        mapValori.put("adresa", adresa);
        mapValori.put("ci_intocmit", ci_intocmit);
        mapValori.put("cnp_intocmit", cnp_intocmit);
        mapValori.put("intocmit", intocmit);
        firma.puneValoriDinMap(mapValori);
        firma.setId(0);
        common.getDataSource().saveInreg(firma);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="clienti si contracte apelat din contracte">
    public void preiaUnFisier(CommonInterface common, String numeFisExcel, String modFact, int furnizor_id) {
        //String numeFisExcel = "D:\\Lucru\\Java\\CRM\\Mondosoft 2017.xlsx";
        FisExcel fe = new FisExcel(numeFisExcel, FisExcel.TipFisExcel.xls);
        fe.openFile();
        fe.setCurrentSheet("Sheet1", false);
        for (int i = 5; i < 575; i++) {
            fe.setCurrentRow(i, false);  // nrRow incep de la 0 (nrRowExcel-1)
            String numar = fe.getStringCellValue(1);  // nrCol incep de la 0 (nrColExcel-1)
            Date data = fe.getDateCellValue(2);
            String denumire = fe.getStringCellValue(3).trim();
            String denumire_posta = fe.getStringCellValue(4).trim();
            String cui = fe.getStringCellValue(5);
            String strada = fe.getStringCellValue(6);
            String nr_strada = fe.getStringCellValue(7);
            String localitate = fe.getStringCellValue(8);
            //String judet = fe.getStringCellValue(9);
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
            eroare.setInfo("Client:" + denumire);
            String judet_id = getIdJudet(cod_judet);
            mapValoriClienti.put("judet_id", judet_id);
            mapValoriClienti.put("localitate_id", getIdLocalitate(localitate, judet_id));
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
            mapValoriContracte.put("furnizor_id", String.valueOf(furnizor_id));
            //Mentenanta
            mapValoriContracte.put("numar", numar);
            if (data == null) {
                mapValoriContracte.put("data", "");
            } else {
                mapValoriContracte.put("data", dfm.format(data));
            }
            if (dela_data == null) {
                mapValoriContracte.put("dela_data", "");
            } else {
                mapValoriContracte.put("dela_data", dfm.format(dela_data));
            }
            if (panala_data == null) {
                mapValoriContracte.put("panala_data", "");
            } else {
                mapValoriContracte.put("panala_data", dfm.format(panala_data));
            }
            mapValoriContracte.put("continut", continut);
            mapValoriContracte.put("valoare", valoare);
            mapValoriContracte.put("observatii", observatii);
            mapValoriContracte.put("mod_fact", modFact);
            mapValoriContracte.put("stare_contract", "Valabil");
            mapValoriContracte.put("nu_fact", "F");
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
        eroare.writeEroare("Cod judet necunoscut:" + cod);
        return "1";
    }

    private String getIdLocalitate(String denumire, String judet_id) {
        String denumireExcel = MyString.faraCarRom(denumire).toLowerCase();
        List<TabelaSqlInterface> localitati = common.getDataSource().listInreg("localitati", 0, "denumire", "");
        for (TabelaSqlInterface localitate : localitati) {
            String l_judet_id = localitate.getColoane().getValoare("judet_id");
            if (l_judet_id.equals(judet_id)) {
                String l_denumire = localitate.getColoane().getValoare("denumire");
                String denumireMySQL = MyString.faraCarRom(l_denumire).toLowerCase();
                if (denumireMySQL.equals(denumireExcel)) {
                    return String.valueOf(localitate.getId());
                }
            }
        }
        //System.out.println("Nu am gasit:" + denumire + " judet=" + judet_id);
        eroare.writeEroare("Nu am gasit:" + denumire + " judet=" + judet_id);
        return "0";
    }
    //</editor-fold>
}

package dorel.crm.rapoarte;

import dorel.aplicatie.interfaces.CommonInterface;
import dorel.basicopp.excelreport.FisExcel;
import dorel.basicopp.excelreport.RandString;
import dorel.basicopp.server.ServerTools;
import dorel.metodenumerice.ordonare.OrdObject;
import dorel.metodenumerice.ordonare.Ordonare;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.swing.JOptionPane;

public class Rapoarte {

    private final CommonInterface common;
    private final DateFormat dfmRo;
    private final DateFormat dfmMySql;
    private final List<ColoanaRaport> listaColoaneDisponibile;
    private final List<ColoanaFiltru> listaColoaneFiltru;
    private final boolean afisazaConditiaFiltru = false;  // numai Debug

    public Rapoarte(CommonInterface common) {
        this.common = common;
        dfmRo = new SimpleDateFormat("dd.MM.yyyy");
        dfmRo.setTimeZone(TimeZone.getTimeZone("Europe/Bucharest")); // EET, Eastern European Time
        dfmMySql = new SimpleDateFormat("yyyy-MM-dd");
        dfmMySql.setTimeZone(TimeZone.getTimeZone("Europe/Bucharest")); // EET, Eastern European Time
        String strDataUserCurent = common.getUserCurent().getAnc() + "-" + common.getUserCurent().getLunac() + "-" + common.getUserCurent().getZiuac();
        //
        listaColoaneFiltru = new ArrayList<>();
        listaColoaneFiltru.add(new ColoanaFiltru("filtru", "", ColoanaFiltru.TipFiltru.FREE_FORM));
        //
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_tip_contract", "tip_contract=", ColoanaFiltru.TipFiltru.COMPARE_WITH_STRING));
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_mod_fact", "mod_fact=", ColoanaFiltru.TipFiltru.COMPARE_WITH_STRING));
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_stare_contract", "stare_contract=", ColoanaFiltru.TipFiltru.COMPARE_WITH_STRING));
        //
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_judet_id", "jud.id=", ColoanaFiltru.TipFiltru.COMPARE_WITH_ID));
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_furnizor_id", "furn.id=", ColoanaFiltru.TipFiltru.COMPARE_WITH_ID));
        //
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_in_perioada", "'" + strDataUserCurent + "' BETWEEN dela_data AND panala_data", ColoanaFiltru.TipFiltru.BOOLEAN_FIX_FORM));
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_numai_fact", "nu_fact='F'", ColoanaFiltru.TipFiltru.BOOLEAN_FIX_FORM));
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_numai_nu_fact", "nu_fact='T'", ColoanaFiltru.TipFiltru.BOOLEAN_FIX_FORM));
        //
        listaColoaneDisponibile = new ArrayList<>();
        listaColoaneDisponibile.add(new ColoanaRaport("tip_contract", "Tip contract", false));
        listaColoaneDisponibile.add(new ColoanaRaport("numar", "Număr contract", false));
        listaColoaneDisponibile.add(new ColoanaRaport("numar_client", "Număr contract la client", false));
        listaColoaneDisponibile.add(new ColoanaRaport("data", "Data contract", true));
        listaColoaneDisponibile.add(new ColoanaRaport("dela_data", "De la data", true));
        listaColoaneDisponibile.add(new ColoanaRaport("panala_data", "Pana la data", true));
        listaColoaneDisponibile.add(new ColoanaRaport("continut", "Continut", false));
        listaColoaneDisponibile.add(new ColoanaRaport("valoare", "Valoare", false));
        listaColoaneDisponibile.add(new ColoanaRaport("stare_contract", "Stare contract", false));
        listaColoaneDisponibile.add(new ColoanaRaport("memento", "Memento", false));
        listaColoaneDisponibile.add(new ColoanaRaport("subunitati", "Subunitati", false));
        listaColoaneDisponibile.add(new ColoanaRaport("observatii", "Observatii", false));
        listaColoaneDisponibile.add(new ColoanaRaport("mod_fact", "Mod facturare", false));
        listaColoaneDisponibile.add(new ColoanaRaport("nu_fact", "Nu factureaza", false));
        //
        listaColoaneDisponibile.add(new ColoanaRaport("cli_denumire", "Denumire", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_denumire_posta", "Denumire posta", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_reg_com", "R.C.", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_atrib_fisc", "Atribut fiscal", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_cui", "CUI", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_strada", "Strada", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_nr_strada", "Numar strada", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_cod_postal", "Cod postal", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_banca", "Banca", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_cont_banca", "Cont banca", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_telefon", "Telefon", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_fax", "Fax", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_functie_conducator", "Functie conducator", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_conducator", "Conducator", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_functie_contabil", "Functie contabil", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_contabil", "Contabil", false));
        //
        listaColoaneDisponibile.add(new ColoanaRaport("cli_judet", "Judet", false));
        listaColoaneDisponibile.add(new ColoanaRaport("cli_localitate", "Localitate", false));
        //
        listaColoaneDisponibile.add(new ColoanaRaport("furn_denumire", "Furnizor", false));
        listaColoaneDisponibile.add(new ColoanaRaport("furn_reg_com", "R.C. furnizor", false));
        listaColoaneDisponibile.add(new ColoanaRaport("furn_atr_fiscal", "Atribut fiscal furnizor", false));
        listaColoaneDisponibile.add(new ColoanaRaport("furn_cui", "cui furnizor", false));
        listaColoaneDisponibile.add(new ColoanaRaport("furn_adresa", "Adrsa furnizor", false));
        listaColoaneDisponibile.add(new ColoanaRaport("furn_cnp_intocmit", "CNP intocmit", false));
        listaColoaneDisponibile.add(new ColoanaRaport("furn_ci_intocmit", "CI intocmit", false));
        listaColoaneDisponibile.add(new ColoanaRaport("furn_intocmit", "Intocmit", false));
    }

    public void list(int idSelectat) {
        String comanda;
        ResultSet rs;
        String filtruWhere = "";
        //
        // incarca setari raport
        comanda = "SELECT * FROM rapoarte WHERE id=" + idSelectat + ";";
        if (common.getDataSource().executaComandaRs(comanda)) {
            rs = common.getDataSource().getResultSet();
            List<OrdObject> listaColoaneRaport = new ArrayList<>();
            try {
                if (rs.next()) {

                    //<editor-fold defaultstate="collapsed" desc="coloane incluse in raport">
                    for (ColoanaRaport coloanaRaport : listaColoaneDisponibile) {
                        int poz = rs.getInt(coloanaRaport.getDenumire());
                        if (poz > 0) {
                            listaColoaneRaport.add(new ColoanaRaport(poz, coloanaRaport.getDenumire(), coloanaRaport.getTitluColoana(), coloanaRaport.isFormatatCaData()));
                        }
                    }
                    Ordonare.ordoneazaList(listaColoaneRaport, true);
                    //</editor-fold>

                    //<editor-fold defaultstate="collapsed" desc="conditii de filtrare">
                    boolean ePrima = true;
                    for (ColoanaFiltru coloanaFiltru : listaColoaneFiltru) {
                        String valoare = rs.getString(coloanaFiltru.getDenumire());
                        switch (coloanaFiltru.getTipFiltru()) {
                            case COMPARE_WITH_DATE:
                                if (!valoare.isEmpty()) {
                                    if (ePrima) {
                                        ePrima = false;
                                    } else {
                                        filtruWhere += " AND";
                                    }
                                    filtruWhere += " " + coloanaFiltru.getFiltru_where() + ServerTools.sqlString(dfmMySql.format(dfmRo.parse(valoare)));
                                }
                                break;
                            case COMPARE_WITH_STRING:
                                if (!valoare.isEmpty()) {
                                    if (ePrima) {
                                        ePrima = false;
                                    } else {
                                        filtruWhere += " AND";
                                    }
                                    filtruWhere += " " + coloanaFiltru.getFiltru_where() + ServerTools.sqlString(valoare);
                                }
                                break;
                            case BOOLEAN_FIX_FORM:
                                if (valoare.equals("T")) {
                                    if (ePrima) {
                                        ePrima = false;
                                    } else {
                                        filtruWhere += " AND";
                                    }
                                    filtruWhere += " " + coloanaFiltru.getFiltru_where();
                                }
                                break;
                            case COMPARE_WITH_NUMBER:
                                if (!valoare.isEmpty()) {
                                    if (ePrima) {
                                        ePrima = false;
                                    } else {
                                        filtruWhere += " AND";
                                    }
                                    filtruWhere += " " + coloanaFiltru.getFiltru_where() + valoare;
                                }
                                break;
                            case COMPARE_WITH_ID:
                                if (!(valoare.isEmpty() || valoare.trim().equals("0"))) {
                                    if (ePrima) {
                                        ePrima = false;
                                    } else {
                                        filtruWhere += " AND";
                                    }
                                    filtruWhere += " " + coloanaFiltru.getFiltru_where() + valoare;
                                }
                                break;
                            case FREE_FORM:
                                if (!valoare.isEmpty()) {
                                    if (ePrima) {
                                        ePrima = false;
                                    } else {
                                        filtruWhere += " AND";
                                    }
                                    filtruWhere += " " + valoare;
                                }
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Rapoarte.list - coloanaFiltru.getTipFiltru() necunoscut:" + coloanaFiltru.getTipFiltru().toString());
                        }
                    }
                    if (afisazaConditiaFiltru) {
                        System.out.println(filtruWhere);
                    }
                    //</editor-fold>
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Rapoarte.list 1 - SQLException:" + ex.getLocalizedMessage());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Rapoarte.list 1 - ParseException:" + ex.getLocalizedMessage());
            }

            //<editor-fold defaultstate="collapsed" desc="incarca date + raport excel">
            comanda = getComandaCompleta();

            if (!filtruWhere.isEmpty()) {
                comanda += " WHERE";
                comanda += filtruWhere;
            }
            //if (afisazaConditiaFiltru) {
            //    System.out.println(comanda);
            //}
            if (common.getDataSource().executaComandaRs(comanda)) {
                rs = common.getDataSource().getResultSet();
                try {
                    String numeFisExcel = "D:\\Lucru\\Java\\CRM\\rap.xls";
                    FisExcel fe = new FisExcel(numeFisExcel, FisExcel.TipFisExcel.xls);
                    fe.genExcel("Raport");
                    fe.startNew();
                    fe.setCurrentSheet("Raport", false);
                    RandString rand;
                    //
                    //<editor-fold defaultstate="collapsed" desc="titlu coloane">
                    rand = new RandString();
                    for (OrdObject coloanaRaport : listaColoaneRaport) {
                        ColoanaRaport col = (ColoanaRaport) coloanaRaport;
                        rand.addColoana(col.getTitluColoana());
                    }
                    fe.addRow(2, 0, rand.getRandul(), true);
                    //</editor-fold>
                    //
                    //<editor-fold defaultstate="collapsed" desc="continut">
                    while (rs.next()) {
                        rand = new RandString();
                        for (OrdObject coloanaRaport : listaColoaneRaport) {
                            ColoanaRaport col = (ColoanaRaport) coloanaRaport;
                            if (((ColoanaRaport) coloanaRaport).isFormatatCaData()) {
                                rand.addColoana(formateazaDataMySQL_Ro(rs.getString(col.getDenumire())));
                            } else {
                                rand.addColoana(rs.getString(col.getDenumire()));
                            }
                        }

                        fe.addRow(0, 0, rand.getRandul(), false);
                    }
                    //</editor-fold>
                    fe.autoSizeColumns(0, rand.getNrColoane());
                    fe.writeToFile();
                    //
                    fe.viewFisExcel();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Rapoarte.list 2 - SQLException:" + ex.getLocalizedMessage());
                }
            }
            //</editor-fold>
        }
    }

    private String getComandaCompleta() {
        String comanda = "SELECT";
        comanda += " con.tip_contract";
        comanda += ",con.numar";
        comanda += ",con.numar_client";
        comanda += ",con.data";
        comanda += ",con.dela_data";
        comanda += ",con.panala_data";
        comanda += ",con.continut";
        comanda += ",con.valoare";
        comanda += ",con.stare_contract";
        comanda += ",con.memento";
        comanda += ",con.subunitati";
        comanda += ",con.observatii";
        comanda += ",con.mod_fact";
        comanda += ",con.nu_fact";
        comanda += ",con.viz";
        //
        comanda += ",cli.denumire AS cli_denumire";
        comanda += ",cli.denumire_posta AS cli_denumire_posta";
        comanda += ",cli.reg_com AS cli_reg_com";
        comanda += ",cli.atrib_fisc AS cli_atrib_fisc";
        comanda += ",cli.cui AS cli_cui";
        comanda += ",cli.judet_id AS cli_judet_id";
        comanda += ",cli.localitate_id AS cli_localitate_id";
        comanda += ",cli.strada AS cli_strada";
        comanda += ",cli.nr_strada AS cli_nr_strada";
        comanda += ",cli.cod_postal AS cli_cod_postal";
        comanda += ",cli.banca AS cli_banca";
        comanda += ",cli.cont_banca AS cli_cont_banca";
        comanda += ",cli.telefon AS cli_telefon";
        comanda += ",cli.fax AS cli_fax";
        comanda += ",cli.functie_conducator AS cli_functie_conducator";
        comanda += ",cli.conducator AS cli_conducator";
        comanda += ",cli.functie_contabil AS cli_functie_contabil";
        comanda += ",cli.contabil AS cli_contabil";
        //
        comanda += ",jud.denumire AS cli_judet";
        comanda += ",loc.denumire AS cli_localitate";
        //
        comanda += ",furn.denumire AS furn_denumire";
        comanda += ",furn.reg_com AS furn_reg_com";
        comanda += ",furn.atr_fiscal AS furn_atr_fiscal";
        comanda += ",furn.cui AS furn_cui";
        comanda += ",furn.adresa AS furn_adresa";
        comanda += ",furn.cnp_intocmit AS furn_cnp_intocmit";
        comanda += ",furn.ci_intocmit AS furn_ci_intocmit";
        comanda += ",furn.intocmit AS furn_intocmit";
        //
        comanda += " FROM";
        comanda += " contracte con";
        comanda += " LEFT JOIN clienti cli ON cli.id=con.client_id";
        comanda += " LEFT JOIN furnizori furn ON furn.id=con.furnizor_id";
        comanda += " LEFT JOIN localitati loc ON loc.id=cli.localitate_id";
        comanda += " LEFT JOIN judete jud ON jud.id=cli.judet_id";
        //comanda += " WHERE";
        //comanda += " con.stare_contract='Valabil'";
        //comanda += " ";
        return comanda;
    }

    private String formateazaDataMySQL_Ro(String dataInitiala) {
        String dataFinala = "";
        if (dataInitiala != null) {
            if (!dataInitiala.isEmpty()) {
                try {
                    Date d_data = dfmMySql.parse(dataInitiala);
                    dataFinala = dfmRo.format(d_data);
                } catch (ParseException ex) {
                    //
                }
            }
        }
        return dataFinala;
    }
}

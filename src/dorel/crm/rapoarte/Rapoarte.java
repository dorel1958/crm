package dorel.crm.rapoarte;

import dorel.aplicatie.interfaces.CommonInterface;
import dorel.basicopp.excelreport.FisExcel;
import dorel.basicopp.excelreport.RandString;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Rapoarte {

    private final CommonInterface common;
    private final DateFormat dfmRo;
    private final DateFormat dfmMySql;
    private final List<ColoanaRaport> listaColoaneDisponibile;
    private final List<ColoanaFiltru> listaColoaneFiltru;

    public Rapoarte(CommonInterface common) {
        this.common = common;
        dfmRo = new SimpleDateFormat("dd.MM.yyyy");
        dfmRo.setTimeZone(TimeZone.getTimeZone("Europe/Bucharest")); // EET, Eastern European Time
        dfmMySql = new SimpleDateFormat("yyyy-MM-dd");
        dfmMySql.setTimeZone(TimeZone.getTimeZone("Europe/Bucharest")); // EET, Eastern European Time
        String strDataUserCurent = common.getUserCurent().getAnc() + "-" + common.getUserCurent().getLunac() + "-" + common.getUserCurent().getZiuac();
        //
        listaColoaneFiltru = new ArrayList<>();
        listaColoaneFiltru.add(new ColoanaFiltru("filtru", "", false, false, false));
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_tip_contract", "tip_contract=", false, true, false));
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_mod_fact", "mod_fact=", false, true, false));
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_judet_id", "jud.id=", false, false, false));
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_furnizor_id", "furn.id=", false, false, false));
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_in_perioada", "'" + strDataUserCurent + "' BETWEEN dela_data AND panala_data", false, false, true));
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_stare_contract", "stare_contract=", false, true, false));
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_numai_fact", "nu_fact='F'", false, false, true));
        listaColoaneFiltru.add(new ColoanaFiltru("filtru_numai_nu_fact", "nu_fact='T'", false, false, true));
        //
        listaColoaneDisponibile = new ArrayList<>();
        listaColoaneDisponibile.add(new ColoanaRaport(1, "tip_contract", "Tip contract", false));
        listaColoaneDisponibile.add(new ColoanaRaport(2, "numar", "Număr contract", false));
        listaColoaneDisponibile.add(new ColoanaRaport(3, "numar_client", "Număr contract la client", false));
        listaColoaneDisponibile.add(new ColoanaRaport(4, "data", "Data contract", true));
        listaColoaneDisponibile.add(new ColoanaRaport(5, "dela_data", "De la data", true));
        listaColoaneDisponibile.add(new ColoanaRaport(6, "panala_data", "Pana la data", true));
        listaColoaneDisponibile.add(new ColoanaRaport(7, "continut", "Continut", false));
        listaColoaneDisponibile.add(new ColoanaRaport(8, "valoare", "Valoare", false));
        listaColoaneDisponibile.add(new ColoanaRaport(9, "stare_contract", "Stare contract", false));
        listaColoaneDisponibile.add(new ColoanaRaport(10, "subunitati", "Subunitati", false));
        listaColoaneDisponibile.add(new ColoanaRaport(11, "observatii", "Observatii", false));
        listaColoaneDisponibile.add(new ColoanaRaport(12, "mod_fact", "Mod facturare", false));
        listaColoaneDisponibile.add(new ColoanaRaport(13, "nu_fact", "Nu factureaza", false));
        //
        listaColoaneDisponibile.add(new ColoanaRaport(14, "cli_denumire", "Denumire", false));
        listaColoaneDisponibile.add(new ColoanaRaport(15, "cli_denumire_posta", "Denumire posta", false));
        listaColoaneDisponibile.add(new ColoanaRaport(16, "cli_reg_com", "R.C.", false));
        listaColoaneDisponibile.add(new ColoanaRaport(17, "cli_atrib_fisc", "Atribut fiscal", false));
        listaColoaneDisponibile.add(new ColoanaRaport(18, "cli_cui", "CUI", false));
        listaColoaneDisponibile.add(new ColoanaRaport(19, "cli_strada", "Strada", false));
        listaColoaneDisponibile.add(new ColoanaRaport(20, "cli_nr_strada", "Numar strada", false));
        listaColoaneDisponibile.add(new ColoanaRaport(21, "cli_cod_postal", "Cod postal", false));
        listaColoaneDisponibile.add(new ColoanaRaport(22, "cli_banca", "Banca", false));
        listaColoaneDisponibile.add(new ColoanaRaport(23, "cli_cont_banca", "Cont banca", false));
        listaColoaneDisponibile.add(new ColoanaRaport(24, "cli_telefon", "Telefon", false));
        listaColoaneDisponibile.add(new ColoanaRaport(25, "cli_fax", "Fax", false));
        listaColoaneDisponibile.add(new ColoanaRaport(26, "cli_functie_conducator", "Functie conducator", false));
        listaColoaneDisponibile.add(new ColoanaRaport(27, "cli_conducator", "Conducator", false));
        listaColoaneDisponibile.add(new ColoanaRaport(28, "cli_functie_contabil", "Functie contabil", false));
        listaColoaneDisponibile.add(new ColoanaRaport(29, "cli_contabil", "Contabil", false));
        //
        listaColoaneDisponibile.add(new ColoanaRaport(30, "cli_judet", "Judet", false));
        listaColoaneDisponibile.add(new ColoanaRaport(31, "cli_localitate", "Localitate", false));
        //
        listaColoaneDisponibile.add(new ColoanaRaport(32, "furn_denumire", "Furnizor", false));
        listaColoaneDisponibile.add(new ColoanaRaport(33, "furn_reg_com", "R.C. furnizor", false));
        listaColoaneDisponibile.add(new ColoanaRaport(34, "furn_atr_fiscal", "Atribut fiscal furnizor", false));
        listaColoaneDisponibile.add(new ColoanaRaport(35, "furn_cui", "cui furnizor", false));
        listaColoaneDisponibile.add(new ColoanaRaport(36, "furn_adresa", "Adrsa furnizor", false));
        listaColoaneDisponibile.add(new ColoanaRaport(37, "furn_cnp_intocmit", "CNP intocmit", false));
        listaColoaneDisponibile.add(new ColoanaRaport(38, "furn_ci_intocmit", "CI intocmit", false));
        listaColoaneDisponibile.add(new ColoanaRaport(39, "furn_intocmit", "Intocmit", false));
    }

    public void listTot() {
        if (common.getDataSource().executaComandaRs(getComandaCompleta())) {
            ResultSet rs = common.getDataSource().getResultSet();
            try {
                String numeFisExcel = "D:\\Lucru\\Java\\CRM\\rapTot.xls";
                FisExcel fe = new FisExcel(numeFisExcel, FisExcel.TipFisExcel.xls);
                fe.genExcel("Raport");
                fe.startNew();
                fe.setCurrentSheet("Raport", false);
                RandString rand;
                //
                // rand titlu coloane
                rand = new RandString();

                rand.addColoana("tip_contract");
                rand.addColoana("numar");
                rand.addColoana("numar_client");
                rand.addColoana("data");
                rand.addColoana("dela_data");
                rand.addColoana("panala_data");
                rand.addColoana("continut");
                rand.addColoana("valoare");
                rand.addColoana("stare_contract");
                rand.addColoana("subunitati");
                rand.addColoana("observatii");
                rand.addColoana("mod_fact");
                rand.addColoana("nu_fact");
                rand.addColoana("denumire");
                rand.addColoana("denumire_posta");
                rand.addColoana("reg_com");
                rand.addColoana("atrib_fisc");
                rand.addColoana("cui");
                rand.addColoana("strada");
                rand.addColoana("nr_strada");
                rand.addColoana("cod_postal");
                rand.addColoana("banca");
                rand.addColoana("cont_banca");
                rand.addColoana("telefon");
                rand.addColoana("fax");
                rand.addColoana("functie_conducator");
                rand.addColoana("conducator");
                rand.addColoana("functie_contabil");
                rand.addColoana("contabil");

                rand.addColoana("judet");
                rand.addColoana("localitate");

                rand.addColoana("furn_denumire");
                rand.addColoana("furn_reg_com");
                rand.addColoana("furn_atr_fiscal");
                rand.addColoana("furn_cui");
                rand.addColoana("furn_adresa");
                rand.addColoana("cnp_intocmit");
                rand.addColoana("ci_intocmit");
                rand.addColoana("intocmit");

                fe.addRow(2, 0, rand.getRandul(), true);
                //
                // continut tabel
                while (rs.next()) {
                    rand = new RandString();
                    rand.addColoana(rs.getString("tip_contract"));
                    rand.addColoana(rs.getString("numar"));
                    rand.addColoana(rs.getString("numar_client"));

                    rand.addColoana(formateazaData(rs.getString("data")));
                    rand.addColoana(formateazaData(rs.getString("dela_data")));
                    rand.addColoana(formateazaData(rs.getString("panala_data")));

                    rand.addColoana(rs.getString("continut"));
                    rand.addColoana(rs.getString("valoare"));
                    rand.addColoana(rs.getString("stare_contract"));
                    rand.addColoana(rs.getString("subunitati"));
                    rand.addColoana(rs.getString("observatii"));
                    rand.addColoana(rs.getString("mod_fact"));
                    rand.addColoana(rs.getString("nu_fact"));
                    rand.addColoana(rs.getString("denumire"));
                    rand.addColoana(rs.getString("denumire_posta"));
                    rand.addColoana(rs.getString("reg_com"));
                    rand.addColoana(rs.getString("atrib_fisc"));
                    rand.addColoana(rs.getString("cui"));
                    rand.addColoana(rs.getString("strada"));
                    rand.addColoana(rs.getString("nr_strada"));
                    rand.addColoana(rs.getString("cod_postal"));
                    rand.addColoana(rs.getString("banca"));
                    rand.addColoana(rs.getString("cont_banca"));
                    rand.addColoana(rs.getString("telefon"));
                    rand.addColoana(rs.getString("fax"));
                    rand.addColoana(rs.getString("functie_conducator"));
                    rand.addColoana(rs.getString("conducator"));
                    rand.addColoana(rs.getString("functie_contabil"));
                    rand.addColoana(rs.getString("contabil"));

                    rand.addColoana(rs.getString("judet"));
                    rand.addColoana(rs.getString("localitate"));

                    rand.addColoana(rs.getString("furn_denumire"));
                    rand.addColoana(rs.getString("furn_reg_com"));
                    rand.addColoana(rs.getString("furn_atr_fiscal"));
                    rand.addColoana(rs.getString("furn_cui"));
                    rand.addColoana(rs.getString("furn_adresa"));
                    rand.addColoana(rs.getString("cnp_intocmit"));
                    rand.addColoana(rs.getString("ci_intocmit"));
                    rand.addColoana(rs.getString("intocmit"));

                    fe.addRow(0, 0, rand.getRandul(), false);
                }
                fe.autoSizeColumns(0, rand.getNrColoane());
                fe.writeToFile();
                //
                fe.viewFisExcel();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Rapoarte.listTot - SQLException:" + ex.getLocalizedMessage());
            }
        }
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
                    // coloanele incluse in raport
                    for (ColoanaRaport coloanaRaport : listaColoaneDisponibile) {
                        int poz = rs.getInt(coloanaRaport.getDenumire());
                        if (poz > 0) {
                            listaColoaneRaport.add(new ColoanaRaport(poz, coloanaRaport.getDenumire(), coloanaRaport.getTitluColoana(), coloanaRaport.isFormatatCaData()));
                        }
                    }
                    // conditiile de filtrare
                    boolean ePrima = true;
                    for (ColoanaFiltru coloanaFiltru : listaColoaneFiltru) {
                        String valoare = rs.getString(coloanaFiltru.getDenumire());
                        if (coloanaFiltru.valoareaIsData()) {
                            if (!valoare.isEmpty()) {
                                if (ePrima) {
                                    ePrima = false;
                                } else {
                                    filtruWhere += " AND";
                                }
                                filtruWhere += " " + coloanaFiltru.getFiltru_where() + "'" + dfmMySql.format(dfmRo.parse(valoare)) + "'";
                            }
                        } else {
                            if (coloanaFiltru.valoareaIsString()) {
                                if (!valoare.isEmpty()) {
                                    if (ePrima) {
                                        ePrima = false;
                                    } else {
                                        filtruWhere += " AND";
                                    }
                                    filtruWhere += " " + coloanaFiltru.getFiltru_where() + "'" + valoare + "'";
                                }
                            } else {
                                if (coloanaFiltru.valoareaIsLogical()) {
                                    if (valoare.equals("T")) {
                                        if (ePrima) {
                                            ePrima = false;
                                        } else {
                                            filtruWhere += " AND";
                                        }
                                        filtruWhere += " " + coloanaFiltru.getFiltru_where();
                                    }
                                } else {
                                    if (!(valoare.equals("0") || valoare.isEmpty())) {
                                        if (ePrima) {
                                            ePrima = false;
                                        } else {
                                            filtruWhere += " AND";
                                        }
                                        filtruWhere += " " + coloanaFiltru.getFiltru_where() + valoare;
                                    }
                                }
                            }
                        }
                    }
                    System.out.println(filtruWhere);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Rapoarte.list 1 - SQLException:" + ex.getLocalizedMessage());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Rapoarte.list 1 - ParseException:" + ex.getLocalizedMessage());
            }
            Ordonare.ordoneazaList(listaColoaneRaport, true);
            //
            // incarca date raport
            comanda = getComandaCompleta();

            if (!filtruWhere.isEmpty()) {
                comanda += " WHERE";
                comanda += filtruWhere;
            }
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
                    // titlu coloane
                    rand = new RandString();
                    for (OrdObject coloanaRaport : listaColoaneRaport) {
                        ColoanaRaport col = (ColoanaRaport) coloanaRaport;
                        rand.addColoana(col.getTitluColoana());
                    }
                    fe.addRow(2, 0, rand.getRandul(), true);
                    //
                    // continut
                    while (rs.next()) {
                        rand = new RandString();
                        for (OrdObject coloanaRaport : listaColoaneRaport) {
                            ColoanaRaport col = (ColoanaRaport) coloanaRaport;
                            if (((ColoanaRaport) coloanaRaport).isFormatatCaData()) {
                                rand.addColoana(formateazaData(rs.getString(col.getDenumire())));
                            } else {
                                rand.addColoana(rs.getString(col.getDenumire()));
                            }
                        }

                        fe.addRow(0, 0, rand.getRandul(), false);
                    }
                    fe.autoSizeColumns(0, rand.getNrColoane());
                    fe.writeToFile();
                    //
                    fe.viewFisExcel();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Rapoarte.list 2 - SQLException:" + ex.getLocalizedMessage());
                }
            }
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
        comanda += ",con.subunitati";
        comanda += ",con.observatii";
        comanda += ",con.mod_fact";
        comanda += ",con.nu_fact";
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

    private String formateazaData(String dataInitiala) {
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

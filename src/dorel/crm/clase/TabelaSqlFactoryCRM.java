package dorel.crm.clase;

import dorel.aplicatie.actiuni.ActiuneButonComanda;
import dorel.aplicatie.interfaces.CommonInterface;
import dorel.aplicatie.swing.MyComboBoxModel;
import dorel.aplicatie.swing.PatraticaControl;
import dorel.aplicatie.interfaces.TabelaSqlInterface;
import dorel.aplicatie.swing.ListaCopii;
import dorel.aplicatie.tabele.InfoIR;
import dorel.aplicatie.tabele.clase.TabelaSqlFactoryHelper;
import dorel.aplicatie.tabele.clase.ColoanaTabela;
import dorel.aplicatie.tabele.clase.Coloane;
import dorel.aplicatie.tabele.clase.TabelaSqlHelper;
import dorel.crm.actiuni.ActiuneRaport;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TabelaSqlFactoryCRM extends TabelaSqlFactoryHelper {

    @Override
    public TabelaSqlInterface getTabela(String numeTabela, CommonInterface common) {
        if (numeTabela.equals("users")) {
            return getTabelaUser(common, false);
        }
        TabelaSqlInterface tabela;
        Coloane coloane = new Coloane();
        ColoanaTabela coloana;
        switch (numeTabela) {
            case "setari":
                return getTabelaSetari(common);
            case "judete":
                //<editor-fold defaultstate="collapsed" desc="judete">
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cod");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 2;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Cod";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus codul judetului.";
                coloana.uniqueValues = true;
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("denumire");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 20;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Denumire";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus denumirea.";
                coloana.uniqueValues = true;
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("prescurtare");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 2;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Prescurtare";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus prescurtarea.";
                coloana.uniqueValues = true;
                coloane.addColoana(coloana);
                // ------------------
                List<InfoIR> listaInfoIRjudete = new ArrayList<>();
                InfoIR infoIRjudete;
                infoIRjudete = new InfoIR("localitati", "judet_id");
                listaInfoIRjudete.add(infoIRjudete);
                List<ActiuneButonComanda> listaActiuniJudete = new ArrayList<>();
                tabela = new TabelaSqlHelper("judete", "Judete", "cod", "denumire", "denumire", coloane, common, listaInfoIRjudete, "denumire", "", "", "", 20, new Dimension(150, 300), 0, listaActiuniJudete);
                ListaCopii listaCopiiJudete = new ListaCopii(common, this.getTabela("localitati", common));  //coloana judet_id e definita la LOCALITATI !!!
                tabela.setPanelEast(listaCopiiJudete);
                break;
            //</editor-fold>
            case "localitati":
                //<editor-fold defaultstate="collapsed" desc="localitati">
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("judet_id");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.REFERINTA;
                coloana.dimX = 20;
                coloana.labelControl = "Judet";
                coloana.numeTabelaReferinta = "judete";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EQUAL_ZERO;
                coloana.mesajInCorect = "Nu ați selectat Judetul.";
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("denumire");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 60;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Denumire";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus Denumirea.";
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("tip_uat");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 30;
                //
                coloana.tipControl = PatraticaControl.TipControl.COMBOBOX;
                coloana.dimX = 20;
                coloana.labelControl = "Tip UAT";
                List<String> listaTipUat = new ArrayList<>();
                listaTipUat.add("");
                //listaTipUat.add("Consiliu judetean");
                listaTipUat.add("Municipiu");
                listaTipUat.add("Oraș");
                listaTipUat.add("Comună");
                //listaTipUat.add("Primăria M. Buc");
                listaTipUat.add("Sector");
                MyComboBoxModel comboBoxModelTipUAT = new MyComboBoxModel(listaTipUat);
                coloana.comboBoxModel = comboBoxModelTipUAT;
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați selectat tipul UAT.";
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cod_siruta");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 10;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Cod SIRUTA";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus Codul SIRUTA.";
                coloane.addColoana(coloana);
                // ------------------
                List<InfoIR> listaInfoIRSiruta = new ArrayList<>();
                List<ActiuneButonComanda> listaActiuniLocalitati = new ArrayList<>();
                tabela = new TabelaSqlHelper("localitati", "Localitati SIRUTA", "denumire", "denumire", "denumire", coloane, common, listaInfoIRSiruta, "denumire", "judet_id", "judete", "judet_id", 17, new Dimension(230, 400), 0, listaActiuniLocalitati);
                break;
            //</editor-fold>
            case "furnizori":
                //<editor-fold defaultstate="collapsed" desc="furnizori">
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("denumire");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Denumire";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus denumirea.";
                //
                coloana.uniqueValues = true;
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("reg_com");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 20;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 15;
                coloana.labelControl = "R. C.";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //
                coloana.uniqueValues = true;
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("atr_fiscal");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 2;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Atribut fiscal";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus Atributul fiscal.";
                //
                //coloana.uniqueValues = true;
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cui");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 13;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "CUI";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus CUI-ul.";
                //
                coloana.uniqueValues = true;
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("adresa");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Adresa";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus adresa.";
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cnp_intocmit");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 13;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 20;
                coloana.labelControl = "CNP intocmit";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus Functia contabilului.";
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("ci_intocmit");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 13;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 20;
                coloana.labelControl = "CI intocmit";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus Functia contabilului.";
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("intocmit");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Intocmit";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus Contabilul.";
                coloane.addColoana(coloana);
                // ------------------
                List<InfoIR> listaInfoIRfirm = new ArrayList<>();
                InfoIR infoIRfirma;
                infoIRfirma = new InfoIR("banci", "firma_id");
                listaInfoIRfirm.add(infoIRfirma);
                List<ActiuneButonComanda> listaActiuniFurnizori = new ArrayList<>();
                tabela = new TabelaSqlHelper("furnizori", "Furnizori", "denumire", "denumire", "denumire", coloane, common, listaInfoIRfirm, "denumire", "", "", "", 10, new Dimension(150, 300), 0, listaActiuniFurnizori);
                //
                ListaCopii listaCopiiFurnizori = new ListaCopii(common, this.getTabela("conturi_banca", common));
                tabela.setPanelEast(listaCopiiFurnizori);
                break;
            //</editor-fold>
            case "conturi_banca":
                //<editor-fold defaultstate="collapsed" desc="conturi_banca">
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("furnizor_id");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.REFERINTA;
                coloana.dimX = 20;
                coloana.labelControl = "Furnizor";
                coloana.numeTabelaReferinta = "furnizori";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EQUAL_ZERO;
                coloana.mesajInCorect = "Nu ați selectat Furnizorul.";
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("banca");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 40;
                //coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Banca";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus Banca.";
                //coloana.uniqueValues = true;
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cont");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 24;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 20;
                coloana.labelControl = "Cont";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus contul.";
                coloana.uniqueValues = true;
                coloane.addColoana(coloana);
                // ------------------
                List<InfoIR> listaInfoIRbanci = new ArrayList<>();
                List<ActiuneButonComanda> listaActiuniConturiBanca = new ArrayList<>();
                tabela = new TabelaSqlHelper("conturi_banca", "Conturi banca", "cont", "cont", "cont", coloane, common, listaInfoIRbanci, "cont", "furnizor_id", "furnizori", "furnizor_id", 15, new Dimension(210, 350), 0, listaActiuniConturiBanca);
                break;
            //</editor-fold>
            case "clienti":
                //<editor-fold defaultstate="collapsed" desc="clienti">
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("judet_id");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.REFERINTA;
                coloana.dimX = 20;
                coloana.labelControl = "Judet";
                coloana.numeTabelaReferinta = "judete";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EQUAL_ZERO;
                coloana.mesajInCorect = "Nu ați selectat Judetul.";
                //
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("localitate_id");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.REFERINTA;
                coloana.dimX = 20;
                coloana.labelControl = "Localitate";
                coloana.numeTabelaReferinta = "localitati";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EQUAL_ZERO;
                coloana.mesajInCorect = "Nu ați selectat Localitatea.";
                //
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("denumire");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                coloana.precizieSql = 0;
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 40;
                coloana.labelControl = "Denumire";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus denumirea.";
                //
                //coloana.uniqueValues = true;
                //
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("denumire_posta");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                coloana.precizieSql = 0;
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Denumire posta";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus denumirea pentru posta.";
                //
                //coloana.uniqueValues = true;
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("reg_com");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 20;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 15;
                coloana.labelControl = "R. C.";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //
                // AR trebuui sa fie UNCA
                //coloana.uniqueValues = true;
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("atrib_fisc");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 2;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Atribut fiscal";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus CUI-ul.";
                //
                //coloana.uniqueValues = true;
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cui");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 13;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "CUI";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus CUI-ul.";
                //
                //coloana.uniqueValues = true; // are cu pt scoala...
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("strada");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Strada";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus strada.";
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("nr_strada");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 20;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Numarul";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus numarul.";
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cod_postal");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 10;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Cod postal";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus codul postal.";
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("banca");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 20;
                coloana.labelControl = "Banca";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus banca.";
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cont_banca");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 24;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Cont banca";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus contul banca.";
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("telefon");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 20;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Telefon";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus telefonul.";
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("fax");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 20;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Fax";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus telefonul.";
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("functie_conducator");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 20;
                coloana.labelControl = "Functie conducator";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus Functia conducatorului.";
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("conducator");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Conducator";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus Conducatorul.";
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("functie_contabil");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 20;
                coloana.labelControl = "Functie contabil";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus Functia contabilului.";
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("contabil");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Contabil";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus Contabilul.";
                coloane.addColoana(coloana);
                // ------------------
                List<InfoIR> listaInfoIRclienti = new ArrayList<>();
                InfoIR infoIRclienti;
                infoIRclienti = new InfoIR("contracte", "client_id");
                listaInfoIRclienti.add(infoIRclienti);
                List<ActiuneButonComanda> listaActiuniClienti = new ArrayList<>();
                tabela = new TabelaSqlHelper("clienti", "Clienti", "denumire", "denumire", "denumire", coloane, common, listaInfoIRclienti, "denumire", "", "judete", "judet_id", 37, new Dimension(440, 600), 0, listaActiuniClienti);
                //
                ListaCopii listaCopiiClienti = new ListaCopii(common, this.getTabela("contracte", common));
                tabela.setPanelEast(listaCopiiClienti);
                break;
            //</editor-fold>
            case "contracte":
                //<editor-fold defaultstate="collapsed" desc="contracte">
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("tip_contract");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 10;
                //
                coloana.tipControl = PatraticaControl.TipControl.COMBOBOX;
                coloana.dimX = 20;
                coloana.labelControl = "Tip contract";
                List<String> listaTip = new ArrayList<>();
                listaTip.add("");
                listaTip.add("Furnizare");
                listaTip.add("Mentenanta");
                MyComboBoxModel comboBoxModelTip = new MyComboBoxModel(listaTip);
                coloana.comboBoxModel = comboBoxModelTip;
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați selectat tipul contractului.";
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("furnizor_id");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.REFERINTA;
                coloana.dimX = 20;
                coloana.labelControl = "Furnizor";
                coloana.numeTabelaReferinta = "furnizori";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EQUAL_ZERO;
                coloana.mesajInCorect = "Nu ați selectat furnizorul.";
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("client_id");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.REFERINTA;
                coloana.dimX = 20;
                coloana.labelControl = "Client";
                coloana.numeTabelaReferinta = "clienti";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EQUAL_ZERO;
                coloana.mesajInCorect = "Nu ați selectat clientul.";
                //
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("numar");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 10;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Număr";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus numărul contractului.";
                //
                //coloana.uniqueValues = true;
                //
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("numar_client");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 10;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Număr client";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus numărul contractului la client.";
                //
                //coloana.uniqueValues = true;
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("data");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.DATE;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.DATE;
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Data";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_DATE;
                coloana.mesajInCorect = "Nu ați introdus o dată validă.";
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("dela_data");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.DATE;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.DATE;
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "De la data";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_DATE_OR_NULL;
                coloana.mesajInCorect = "Nu ați introdus De la data valida.";
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("panala_data");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.DATE;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.DATE;
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Pana la data";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_DATE_OR_NULL;
                coloana.mesajInCorect = "Nu ați introdus Pana la data valida.";
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("continut");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Continut";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus tipul contractului.";
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("valoare");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.DOUBLE;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.DECIMAL;
                coloana.lungimeSql = 15;
                coloana.precizieSql = 3;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 10;
                coloana.labelControl = "Valoare";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EQUAL_ZERO;
                coloana.mesajInCorect = "Nu ați introdus valoarea.";
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("stare_contract");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 60;
                //
                coloana.tipControl = PatraticaControl.TipControl.COMBOBOX;
                coloana.dimX = 20;
                coloana.labelControl = "Stare";
                List<String> listaStare = new ArrayList<>();
                listaStare.add("");
                listaStare.add("Precontract");
                listaStare.add("Trimis");
                listaStare.add("Valabil");
                listaStare.add("Expirat");
                MyComboBoxModel comboBoxModelStare = new MyComboBoxModel(listaStare);
                coloana.comboBoxModel = comboBoxModelStare;
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați selectat Starea contractului.";
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("subunitati");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Subunitati";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus tipul contractului.";
                //
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("observatii");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 255;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 30;
                coloana.labelControl = "Observații";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus tipul contractului.";
                //
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("mod_fact");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 15;
                //
                coloana.tipControl = PatraticaControl.TipControl.COMBOBOX;
                coloana.dimX = 20;
                coloana.labelControl = "Mod facturare";
                List<String> listaModFact = new ArrayList<>();
                listaModFact.add("");
                listaModFact.add("Lunar");
                listaModFact.add("La 2 luni");
                listaModFact.add("Trimestrial");
                listaModFact.add("Semestrial");
                listaModFact.add("Anual");
                MyComboBoxModel comboBoxModelModFact = new MyComboBoxModel(listaModFact);
                coloana.comboBoxModel = comboBoxModelModFact;
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați selectat Modul de facturare.";
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("nu_fact");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 1;
                coloana.valoareImplicita = "F";
                //
                coloana.tipControl = PatraticaControl.TipControl.CHECKBOX;
                //coloana.dimX = 20;
                coloana.labelControl = "Nu facturează luna curentă";
                //
                //coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                //coloana.mesajInCorect = "Nu ați selectat Modul de faxcturare.";
                coloane.addColoana(coloana);
                // ------------------
                List<InfoIR> listaInfoIRm = new ArrayList<>();
                List<ActiuneButonComanda> listaActiuniContracte = new ArrayList<>();
                tabela = new TabelaSqlHelper("contracte", "Contracte", "data, numar", "dela_data+' '+panala_data+' '+numar", "dela_data,panala_data,numar", coloane, common, listaInfoIRm, "numar", "client_id", "furnizori", "furnizor_id", 13, new Dimension(190, 500), 0, listaActiuniContracte);
                break;
            //</editor-fold>
            case "rapoarte":
                //<editor-fold defaultstate="collapsed" desc="rapoarte">
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("denumire");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 20;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 20;
                coloana.labelControl = "Denumire";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.EMPTY_STRING;
                coloana.mesajInCorect = "Nu ați introdus denumirea.";
                coloana.uniqueValues = true;
                coloane.addColoana(coloana);
                // ------------------
                // --------------  Filtre -----
                coloana = new ColoanaTabela();
                coloana.setNume("filtru");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 250;
                coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 20;
                coloana.labelControl = "Filtru";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("filtru_mod_fact");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 15;
                //
                coloana.tipControl = PatraticaControl.TipControl.COMBOBOX;
                coloana.dimX = 20;
                coloana.labelControl = "Filtru Mod facturare";
                List<String> listaFiltruModFact = new ArrayList<>();
                listaFiltruModFact.add("");
                listaFiltruModFact.add("Lunar");
                listaFiltruModFact.add("La 2 luni");
                listaFiltruModFact.add("Trimestrial");
                listaFiltruModFact.add("Semestrial");
                listaFiltruModFact.add("Anual");
                MyComboBoxModel comboBoxModelFiltruModFact = new MyComboBoxModel(listaFiltruModFact);
                coloana.comboBoxModel = comboBoxModelFiltruModFact;
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("filtru_judet_id");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.REFERINTA;
                coloana.dimX = 20;
                coloana.labelControl = "Filtru Judet";
                coloana.numeTabelaReferinta = "judete";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //
                coloane.addColoana(coloana);
                // -------------------
                coloana = new ColoanaTabela();
                coloana.setNume("filtru_furnizor_id");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.REFERINTA;
                coloana.dimX = 20;
                coloana.labelControl = "Filtru Furnizor";
                coloana.numeTabelaReferinta = "furnizori";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("filtru_in_perioada");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.STRING;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.CHAR;
                coloana.lungimeSql = 1;
                //coloana.precizieSql = 0;
                //
                coloana.setValoareImplicita("T");
                //
                coloana.tipControl = PatraticaControl.TipControl.CHECKBOX;
                //coloana.dimX =5;
                coloana.labelControl = "Numai contracte valabile la data curentă";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.NU_TESTEZ_CORECT;
                //coloana.mesajInCorect = "Nu ați introdus data curenta.";
                //coloana.uniqueValues = true;
                coloane.addColoana(coloana);
                // ------------------
                // ---------- COLOANE --------
                coloana = new ColoanaTabela();
                coloana.setNume("tip_contract");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Tip contract";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("numar");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Număr";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("numar_client");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Număr client";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("data");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Data";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("dela_data");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "De la data";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("panala_data");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Până la data";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("continut");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Conținut";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("valoare");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Valoare";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("stare_contract");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Stare contract";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("subunitati");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Subunități";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("observatii");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Observații";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("mod_fact");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Mod facturare";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("nu_fact");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Nu facturează";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_denumire");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client denumire";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_denumire_posta");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client denumire poșta";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_reg_com");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client R.C.";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_atrib_fisc");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client atribut fiscal";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_cui");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client CUI";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_judet");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client județ";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_localitate");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client localitate";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_strada");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client strada";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_nr_strada");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client numar strada";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_cod_postal");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client cod poștal";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_banca");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client banca";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_cont_banca");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client cont banca";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_telefon");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client telefon";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_fax");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client fax";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_functie_conducator");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client funcție conducător";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_conducator");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client conducător";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_functie_contabil");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client funcție contabil";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("cli_contabil");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Client contabil";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("furn_denumire");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Furnizor denumire";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("furn_reg_com");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Furnizor R.C.";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("furn_atr_fiscal");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Furnizor atribut fiscal";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("furn_cui");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Furnizor CUI";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("furn_adresa");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Furnizor adrtesa";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("furn_cnp_intocmit");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Furnizor CNP întocmit";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("furn_ci_intocmit");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Furnizor CI întocmit";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                coloana = new ColoanaTabela();
                coloana.setNume("furn_intocmit");
                coloana.tipDataJava = ColoanaTabela.TipDataJava.INTEGER;
                //
                coloana.tipDataSql = ColoanaTabela.TipDataSql.INTEGER;
                //
                coloana.setValoareImplicita("0");
                //
                coloana.tipControl = PatraticaControl.TipControl.TEXT;
                coloana.dimX = 5;
                coloana.labelControl = "Furnizor întocmit";
                //
                coloana.tipTestCorect = ColoanaTabela.TipTestCorect.VALID_INTEGER;
                coloane.addColoana(coloana);
                // ------------------
                List<InfoIR> listaInfoIRrapoarte = new ArrayList<>();

                List<ActiuneButonComanda> listaActiuniRapoarte = new ArrayList<>();
                ActiuneButonComanda action = new ActiuneRaport(common, new javax.swing.ImageIcon(getClass().getResource("/dorel/crm/resources/rapoarte.png")));
                listaActiuniRapoarte.add(action);

                tabela = new TabelaSqlHelper("rapoarte", "Rapoarte", "denumire", "denumire", "denumire", coloane, common, listaInfoIRrapoarte, "denumire", "", "", "", 20, new Dimension(100, 300), 15, listaActiuniRapoarte);
                break;
            //</editor-fold>
            default:
                JOptionPane.showMessageDialog(null, "TabelaSqlFactory.getTabela - nume tabela necunoscut:" + numeTabela);
                tabela = null;
                break;
        }
        return tabela;
    }
}

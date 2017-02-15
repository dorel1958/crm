package dorel.crm.rapoarte;

import dorel.aplicatie.tabele.clase.ColoanaTabela;

public class ColoanaFiltru {

    public enum TipFiltru {

        COMPARE_WITH_ID,          // compara cu ID DACA id!=0
        COMPARE_WITH_STRING,      //
        COMPARE_WITH_NUMBER,      // = numar
        COMPARE_WITH_DATE,        // compara cu data formatata tip MySQL
        FREE_FORM,         // pune valoarea campului SI ATAT
        BOOLEAN_FIX_FORM,  // daca valoarea unui camp boolean == "T" -> pune textul fix: filtru_where
    }

    private final String denumire;
    private final TipFiltru tipFiltru;
    private final String filtru_where;

    //<editor-fold defaultstate="collapsed" desc="Get Set">
    public TipFiltru getTipFiltru() {
        return tipFiltru;
    }

    public String getFiltru_where() {
        return filtru_where;
    }

    public String getDenumire() {
        return denumire;
    }
    //</editor-fold>

    public ColoanaFiltru(String denumire, String filtru_where, TipFiltru tipFiltru) {
        this.denumire = denumire;
        this.tipFiltru = tipFiltru;
        this.filtru_where = filtru_where;
    }

}

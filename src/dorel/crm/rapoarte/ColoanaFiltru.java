package dorel.crm.rapoarte;

public class ColoanaFiltru {

    private String denumire;
    private String filtru_where;
    private boolean valoareaIsString;
    private boolean valoareaIsData;
    private boolean valoareaIsLogical;

    //<editor-fold defaultstate="collapsed" desc="Get Set">
    public boolean valoareaIsLogical() {
        return valoareaIsLogical;
    }

    public boolean valoareaIsData() {
        return valoareaIsData;
    }

    public void setValoareaIsData(boolean valoareaIsData) {
        this.valoareaIsData = valoareaIsData;
    }

    public boolean valoareaIsString() {
        return valoareaIsString;
    }

    public void setValoareaIsString(boolean valoareaIsString) {
        this.valoareaIsString = valoareaIsString;
    }

    public String getFiltru_where() {
        return filtru_where;
    }

    public void setFiltru_where(String filtru_where) {
        this.filtru_where = filtru_where;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }
    //</editor-fold>

    public ColoanaFiltru(String denumire, String filtru_where, boolean valoareaIsData, boolean valoareaIsString, boolean valoareaIsLogical) {
        this.denumire = denumire;
        this.filtru_where = filtru_where;
        this.valoareaIsData=valoareaIsData;
        this.valoareaIsString=valoareaIsString;
        this.valoareaIsLogical=valoareaIsLogical;
    }

}

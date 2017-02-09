package dorel.crm.rapoarte;

import dorel.metodenumerice.ordonare.OrdObjectHelper;

public class ColoanaRaport extends OrdObjectHelper {

    private int index;
    private String denumire;
    private boolean formatatCaData;
    private String titluColoana;
    //private String filtru_where;

    //<editor-fold defaultstate="collapsed" desc="Get Set">
    public String getTitluColoana() {
        return titluColoana;
    }

    public void setTitluColoana(String titluColoana) {
        this.titluColoana = titluColoana;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public boolean isFormatatCaData() {
        return formatatCaData;
    }

    public void setFormatatCaData(boolean formatatCaData) {
        this.formatatCaData = formatatCaData;
    }
    //</editor-fold>

    public ColoanaRaport(int index, String denumire, String titluColoana, boolean formatatCaData) {
        this.index = index;
        this.denumire = denumire;
        this.formatatCaData=formatatCaData;
        this.titluColoana=titluColoana;
        //this.filtru_where=filtru_where;
    }

    @Override
    public double getValoareOrdonare() {
        return index;
    }

}

package dorel.crm.clase;

import dorel.aplicatie.CommonHelper;
import dorel.aplicatie.DataSourceHelper;
import dorel.aplicatie.interfaces.PopuleazaInterface;
import dorel.aplicatie.interfaces.TabelaSqlInterface;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public final class CommonCRM extends CommonHelper {

    private Date data_curenta;
    private BigDecimal cotaTVA;

    public BigDecimal getCotaTVA() {
        return cotaTVA;
    }

    public void setCotaTVA(BigDecimal cotaTVA) {
        this.cotaTVA = cotaTVA;
    }

    public Date getData_curenta() {
        return data_curenta;
    }

    public void setData_curenta(Date data_curenta) {
        this.data_curenta = data_curenta;
    }

    public CommonCRM() {
        super();

        tabelaSqlFactory = new TabelaSqlFactoryCRM();
        // se pune DUPA crearea tabelaSqlFactory unde este IMPLICIT definita tabela users
        PopuleazaInterface populeaza = new PopuleazaCRM(this);
        dataSource = new DataSourceHelper(this, populeaza, true); // AICI creaza BD daca nu este + tabela users
        if (dataSource.isEroare()) {
            return;
        }
        TabelaSqlInterface tabela;
        // tabela users e creata implicit
        // tabela setari e creata implicit

        tabela = tabelaSqlFactory.getTabelaSetari(this);
        tabele.add(tabela);
        
        tabela = tabelaSqlFactory.getTabela("judete", this);
        tabele.add(tabela);

        tabela = tabelaSqlFactory.getTabela("localitati", this);
        tabele.add(tabela);

        tabela = tabelaSqlFactory.getTabela("furnizori", this);
        tabele.add(tabela);

        tabela = tabelaSqlFactory.getTabela("conturi_banca", this);
        tabele.add(tabela);

        tabela = tabelaSqlFactory.getTabela("clienti", this);
        tabele.add(tabela);

        tabela = tabelaSqlFactory.getTabela("contracte", this);
        tabele.add(tabela);

        tabela = tabelaSqlFactory.getTabela("rapoarte", this);
        tabele.add(tabela);

        // se pune DUPA initializarea listei de tabele
        // creaza si populeaza tabelele ce nu mai exista (chiar daca lipseste numai una)
        dataSource.genereazaTabele(populeaza);
        //
    }

}

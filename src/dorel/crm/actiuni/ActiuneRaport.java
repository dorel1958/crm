package dorel.crm.actiuni;

import dorel.aplicatie.actiuni.ActiuneButonComanda;
import dorel.aplicatie.interfaces.CommonInterface;
import dorel.crm.rapoarte.Rapoarte;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public final class ActiuneRaport extends ActiuneButonComanda {

    public ActiuneRaport(CommonInterface common, ImageIcon icon) {
        super("Raport", "Executie raport", common, icon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Rapoarte raportExcel = new Rapoarte(common);
        raportExcel.list(ferDialogTabela.getIdSelectat());
    }

}

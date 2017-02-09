package dorel.crm.actiuni;

import dorel.aplicatie.interfaces.CommonInterface;
import dorel.crm.rapoarte.RapoarteDialog;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.SHORT_DESCRIPTION;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public final class ActiuneRapoarte extends AbstractAction {

    JFrame frame;
    CommonInterface common;

    public ActiuneRapoarte(JFrame frame, CommonInterface common, ImageIcon icon) {
        super("Rapoarte", icon);
        putValue(SHORT_DESCRIPTION, "Efectuare Rpoarte");
        //putValue(MNEMONIC_KEY, 0);
        this.common = common;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RapoarteDialog rapoarte = new RapoarteDialog(frame, common);
    }

}

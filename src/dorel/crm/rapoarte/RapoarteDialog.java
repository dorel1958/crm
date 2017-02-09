package dorel.crm.rapoarte;

import dorel.aplicatie.interfaces.CommonInterface;
import dorel.basicopp.swing.PanelFactory;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RapoarteDialog extends JDialog implements ActionListener {

    CommonInterface common;

    public RapoarteDialog(JFrame frame, CommonInterface common) {
        super(frame, true);
        this.common = common;
        this.initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Rapoarte");

        //<editor-fold defaultstate="collapsed" desc="Bottom Buttons...">
        List<Component> lButoane = new ArrayList<>();
        //
        JButton butListeazaTot = new JButton("Listează tot");
        butListeazaTot.addActionListener(this);
        lButoane.add(butListeazaTot);
        //
        JButton butListeaza = new JButton("Listează");
        butListeaza.addActionListener(this);
        lButoane.add(butListeaza);
        //
        Component spatiu2 = Box.createRigidArea(new Dimension(40, 0));
        lButoane.add(spatiu2);

        JButton butExit = new JButton("Exit");
        butExit.addActionListener(this);
        lButoane.add(butExit);
        add(PanelFactory.createHorizontalButtonsRow(lButoane), BorderLayout.SOUTH);
        //</editor-fold>        

        pack();

        //<editor-fold defaultstate="collapsed" desc="Center in Screen">
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Determine the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = 0;
        if (dim.width > w) {
            x = (dim.width - w) / 2;
        }
        int y = 0;
        if (dim.height > h) {
            y = (dim.height - h) / 2;
        }
        // Move the window
        this.setLocation(x, y);
        //</editor-fold>

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Rapoarte rapoarte = new Rapoarte(common);
        switch (e.getActionCommand()) {
            case "Listează tot":
                rapoarte.listTot();
                break;
            case "Listează":
                JOptionPane.showMessageDialog(this, "List");
                break;
            case "Exit":
                setVisible(false);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Comanda necunoscuta:" + e.getActionCommand());
        }
    }

}

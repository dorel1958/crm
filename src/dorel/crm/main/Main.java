package dorel.crm.main;

import dorel.aplicatie.actiuni.ActiuneBackAdmin;
import dorel.aplicatie.actiuni.ActiuneTabela;
import dorel.aplicatie.interfaces.CommonInterface;
import dorel.crm.clase.CommonCRM;
import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class Main extends dorel.aplicatie.ferestre.Main {

    ActiuneBackAdmin actiuneBackAdmin;
            
    public static void main(String[] args) {
        Main main = new Main();
        //
        CommonInterface common = new CommonCRM();
        if (common.getDataSource().isEroare()) {
            return;
        }
        main.SetCommon(common);
        //
        boolean cuParola = true;
        if (cuParola) {
            if (main.testParola()) {
                main.initComponents();

                main.setActions();
                main.setMenu();
                main.setToolbars();

                main.pack();
                main.setVisible(true);
            } else {
                System.exit(0);
            }
        } else {
            main.initComponents();

            main.setActions();
            main.setMenu();
            main.setToolbars();

            main.pack();
            main.setVisible(true);
        }
    }

    private void setActions() {
        actiuneBackAdmin=new ActiuneBackAdmin(common,new javax.swing.ImageIcon(getClass().getResource("/dorel/crm/resources/judete.png")));
        actiuni.addActiune("setari", new ActiuneTabela(this, common, "Setări", "Editare setări", new javax.swing.ImageIcon(getClass().getResource("/dorel/crm/resources/setari.png")), "setari"));
        actiuni.addActiune("users", new ActiuneTabela(this, common, "Utilizatori", "Editare utilizatori", new javax.swing.ImageIcon(getClass().getResource("/dorel/aplicatie/resources/users.png")), "users"));
        actiuni.addActiune("judete", new ActiuneTabela(this, common, "Județe", "Editare județe", new javax.swing.ImageIcon(getClass().getResource("/dorel/crm/resources/judete.png")), "judete"));
        actiuni.addActiune("localitati", new ActiuneTabela(this, common, "Localități", "Editare localități", new javax.swing.ImageIcon(getClass().getResource("/dorel/crm/resources/localitati.gif")), "localitati"));
        actiuni.addActiune("furnizori", new ActiuneTabela(this, common, "Furnizori", "Editare Furnzori", new javax.swing.ImageIcon(getClass().getResource("/dorel/crm/resources/furnizori.gif")), "furnizori"));
        actiuni.addActiune("clienti", new ActiuneTabela(this, common, "Clienți", "Editare clienți", new javax.swing.ImageIcon(getClass().getResource("/dorel/crm/resources/clienti.gif")), "clienti"));
        actiuni.addActiune("contracte", new ActiuneTabela(this, common, "Contracte", "Editare contracte", new javax.swing.ImageIcon(getClass().getResource("/dorel/crm/resources/contract.gif")), "contracte"));
        actiuni.addActiune("rapoarte", new ActiuneTabela(this, common, "Rapoarte", "Editare rapoarte", new javax.swing.ImageIcon(getClass().getResource("/dorel/crm/resources/rapoarte.png")), "rapoarte"));
        //actiuni.addActiune("rapoarte", new ActiuneRapoarte(this, common, new javax.swing.ImageIcon(getClass().getResource("/dorel/crm/resources/rapoarte.png"))));
    }

    private void setMenu() {
        
        JMenu adminMenu = new JMenu("Administrare");
        adminMenu.add(new JMenuItem(actiuneBackAdmin));
        mainMenu.add(adminMenu);
        //
        JMenu nomMenu = new JMenu("Nomenclatoare");
        nomMenu.add(new JMenuItem(actiuni.getActiune("users")));
        nomMenu.add(new JMenuItem(actiuni.getActiune("setari")));
        nomMenu.add(new JMenuItem(actiuni.getActiune("judete")));
        nomMenu.add(new JMenuItem(actiuni.getActiune("localitati")));
        nomMenu.add(new JMenuItem(actiuni.getActiune("furnizori")));
        nomMenu.add(new JMenuItem(actiuni.getActiune("clienti")));
        nomMenu.add(new JMenuItem(actiuni.getActiune("contracte")));
        mainMenu.add(nomMenu);
        //
        JMenu rapMenu = new JMenu("Rapoarte");
        rapMenu.add(new JMenuItem(actiuni.getActiune("rapoarte")));
        mainMenu.add(rapMenu);
        //
        JMenu exitMenu = new JMenu("Exit");
        exitMenu.add(new JMenuItem(actiuni.getActiune("exit")));
        mainMenu.add(exitMenu);
    }

    private void setToolbars() {
        JToolBar tbAdmin = new JToolBar("Administrare");
        tbAdmin.add(newToolbarButton(actiuneBackAdmin));
        panelNorth.add(tbAdmin);

        JToolBar tbFile = new JToolBar("Nomenclatoare");
        tbFile.add(newToolbarButton(actiuni.getActiune("users")));
        tbFile.add(newToolbarButton(actiuni.getActiune("setari")));
        tbFile.add(newToolbarButton(actiuni.getActiune("judete")));
        tbFile.add(newToolbarButton(actiuni.getActiune("localitati")));
        tbFile.add(newToolbarButton(actiuni.getActiune("furnizori")));
        tbFile.add(newToolbarButton(actiuni.getActiune("clienti")));
        tbFile.add(newToolbarButton(actiuni.getActiune("contracte")));
        tbFile.add(newToolbarButton(actiuni.getActiune("exit")));
        panelNorth.add(tbFile);

        JToolBar tbRapoarte = new JToolBar("Rapoarte");
        tbRapoarte.add(newToolbarButton(actiuni.getActiune("rapoarte")));
        panelNorth.add(tbRapoarte);
    }

    private JButton newToolbarButton(AbstractAction action) {
        // primeste Key de la tastatura NUMAI obiectul ce are focus la momentul respectiv
        // nu mai permit focusarea butoanelor din Toolbar -> focusul ramane pe designer
        // am pus key listenerul pe designer (as fi putut pune si pe toate butoanele)
        JButton buton = new JButton(action);
        buton.setFont(new Font("SansSerif", Font.BOLD, 9));
        //
        // pt a disparea textul scris in buton
        buton.setText("");
        //
        buton.setFocusable(false);
        return buton;
    }

}

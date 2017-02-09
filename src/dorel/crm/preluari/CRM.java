package dorel.crm.preluari;

import dorel.aplicatie.ferestre.FerDialogTabela;
import dorel.aplicatie.ferestre.FerTestParola;
import dorel.aplicatie.interfaces.CommonInterface;
import dorel.basicopp.swing.PanelFactory;
import dorel.crm.clase.CommonCRM;
import dorel.crm.preluari.PrelLocalitati;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CRM extends JFrame implements ActionListener {

    private CommonInterface common;

    public static void main(String[] args) {
        CRM main = new CRM();
        main.initCommon();
        if (main.testParola()) {
            main.initComponents();
        } else {
            System.exit(0);
        }
    }

    private void initCommon() {
        common = new CommonCRM();
    }

    private void initComponents() {
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("CRM");
        setLayout(new BorderLayout());

        List<Component> lButoane = new ArrayList<>();

        JButton butUsers = new JButton("Utilizatori");
        butUsers.addActionListener(this);
        lButoane.add(butUsers);

        JButton butSetari = new JButton("Setări");
        butSetari.addActionListener(this);
        lButoane.add(butSetari);

        JButton butConturiBanca = new JButton("Conturi banca");
        butConturiBanca.addActionListener(this);
        lButoane.add(butConturiBanca);

        JButton butFurnizori = new JButton("Furnizori");
        butFurnizori.addActionListener(this);
        lButoane.add(butFurnizori);

        JButton butJudete = new JButton("Județe");
        butJudete.addActionListener(this);
        lButoane.add(butJudete);

        JButton butLocalitati = new JButton("Localități");
        butLocalitati.addActionListener(this);
        lButoane.add(butLocalitati);

        JButton butClienti = new JButton("Clienți");
        butClienti.addActionListener(this);
        lButoane.add(butClienti);

        JButton butContracte = new JButton("Contracte");
        butContracte.addActionListener(this);
        lButoane.add(butContracte);

        JButton butTest = new JButton("Test");
        butTest.addActionListener(this);
        lButoane.add(butTest);

        //JButton butAchizitii = new JButton("Facturi");
        //butAchizitii.addActionListener(this);
        //lButoane.add(butAchizitii);
        //JButton butIstoricFurnizori = new JButton("Incasari");
        //butIstoricFurnizori.addActionListener(this);
        //lButoane.add(butIstoricFurnizori);
        
        //JButton butReadExcel1 = new JButton("Read Excel 1");
        //butReadExcel1.addActionListener(this);
        //lButoane.add(butReadExcel1);

        JButton butReadExcel2 = new JButton("Read Excel 2");
        butReadExcel2.addActionListener(this);
        lButoane.add(butReadExcel2);

        JButton butPuneTaxe = new JButton("Iesire");
        butPuneTaxe.addActionListener(this);
        lButoane.add(butPuneTaxe);

        JPanel panelS = PanelFactory.createHorizontalButtonsRow(lButoane);
        add(panelS, BorderLayout.SOUTH);

        pack();

        // <editor-fold defaultstate="collapsed" desc="Center in Screen">      
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
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Utilizatori":
                FerDialogTabela ferUser = new FerDialogTabela(this, common, "users", false, 0);
                break;
            case "Setări":
                FerDialogTabela ferSetari = new FerDialogTabela(this, common, "setari", false, 0);
                break;
            case "Județe":
                FerDialogTabela ferJudete = new FerDialogTabela(this, common, "judete", false, 0);
                break;
            case "Furnizori":
                FerDialogTabela ferFurnizori = new FerDialogTabela(this, common, "furnizori", false, 0);
                break;
            case "Conturi banca":
                FerDialogTabela ferCb = new FerDialogTabela(this, common, "conturi_banca", false, 0);
                break;
            case "Localități":
                FerDialogTabela ferLocalitati = new FerDialogTabela(this, common, "localitati", false, 0);
                break;
            case "Clienți":
                FerDialogTabela ferClienti = new FerDialogTabela(this, common, "clienti", false, 0);
                break;
            case "Contracte":
                FerDialogTabela ferContracte = new FerDialogTabela(this, common, "contracte", false, 0);
                break;
            case "Test":
                //PrelLocalitati pl = new PrelLocalitati();
                //pl.preiaFisiere();
                JOptionPane.showMessageDialog(this, "Test");
                break;
            case "Facturi":
                JOptionPane.showMessageDialog(this, "Facturi");
                break;
            case "Incasari":
                JOptionPane.showMessageDialog(this, "Incasari");
                break;
//            case "Read Excel 1":
//                //JOptionPane.showMessageDialog(this, "Read Excel 1");
//                Excel_1 excel1 = new Excel_1();
//                String numeFisExcel = "D:\\Lucru\\Java\\CRM\\Mondosoft 2017.xls";
//                excel1.preiaFisier(common, numeFisExcel, "Lunar");
//                break;
            case "Read Excel 2":
                //JOptionPane.showMessageDialog(this, "Read Excel 1");
                Excel_2 excel2 = new Excel_2(common);
                excel2.preiaFisier();
                break;
            case "Iesire":
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Comanda necumoscuta:" + ae.getActionCommand());
                break;
        }
    }

    private boolean testParola() {
        if (common.getDataSource().isEroare()) {
            return false;
        } else {
            FerTestParola ftp = new FerTestParola(common);
            return ftp.getResponse();
        }
    }
}

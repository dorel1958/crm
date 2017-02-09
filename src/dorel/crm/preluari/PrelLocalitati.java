package dorel.crm.preluari;

import dorel.basicopp.datatypes.Numere;
import dorel.basicopp.io.Director;
import dorel.basicopp.io.TextReader;
import dorel.basicopp.io.TextWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PrelLocalitati {

    public void preiaFisiere() {
        String numeDir = "D:\\Lucru\\Java\\CRM\\Localitati";
        Director dir = new Director(numeDir);
        List<File> listaFisiere = dir.getFiles();
        List<File> listaFisiereTxt = new ArrayList<>();
        for (File fis : listaFisiere) {
            if (fis.getName().endsWith(".txt")) {
                listaFisiereTxt.add(fis);
            }
        }
        TextWriter tw = new TextWriter("D:\\Lucru\\Java\\CRM\\LocalitatiCurate\\localitati.txt", false, TextWriter.Encoding.UTF8);
        for (File fis : listaFisiereTxt) {
            preiauUnFisier(fis, tw);
        }
        tw.close();
    }

    private void preiauUnFisier(File fis, TextWriter tw) {
        //String numefis = fis.getName();
        int judet_id = Numere.stringToInt(fis.getName().substring(0, 2));
        String numeFis = fis.getAbsolutePath();
        //System.out.println(numeFis);
        String denumire = "necunoscuta";
        String tip = "necunoscut";
        TextReader tr = new TextReader(numeFis);
        String line;
        while ((line = tr.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                if (!(line.endsWith(".jpg") || line.endsWith(".JPG") || line.endsWith(".jpeg") || line.endsWith(".png") || line.endsWith(".JPEG") || line.endsWith(".PNG"))) {
                    if (line.equals("reședință de comună")) {
                        tw.write(",comuna");
                    } else {
                        if (line.endsWith(" sat")) {
                            denumire = line.substring(0, line.length() - 4);
                            tip = "sat";
                        } else {
                            if (line.endsWith(" oraș")) {
                                denumire = line.substring(0, line.length() - 5);
                                tip = "oras";
                            } else {
                                if (line.endsWith(" municipiu")) {
                                    denumire = line.substring(0, line.length() - 10);
                                    tip = "municipiu";
                                } else {
                                    if (line.endsWith(" sector")) {
                                        denumire = line.substring(0, line.length() - 7);
                                        tip = "sector";
                                    } else {
                                        System.out.println("Linie ciudata:"+line+" judet_id="+judet_id);
                                    }
                                }
                            }
                        }
                        tw.writeLine("");
                        tw.write(judet_id + "," + denumire + "," + tip);
                    }
                }
            }
        }
        tr.close();
    }
}
// am in acelasi judet sate cu acelasi nume in comune diferite

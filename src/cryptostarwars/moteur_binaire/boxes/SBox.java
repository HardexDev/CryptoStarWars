package cryptostarwars.moteur_binaire.boxes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import cryptostarwars.moteur_binaire.MotBinaire;

/**
 * Box de substitution
 */
public class SBox implements IBox {

    private ArrayList<Long> tableau;
    
    public SBox(String adresseFichier) throws FileNotFoundException {
        String token1 = "";
        Scanner textFile = new Scanner(new File(adresseFichier)).useDelimiter(",s*");
        ArrayList<Long> sbox = new ArrayList<>();

        while (textFile.hasNext()) {
            token1 = textFile.next();
            String empty = "";
            token1 = token1.replace("0x", empty);
            token1 = token1.replaceAll("\r\n", empty);
            sbox.add(Long.parseLong(token1, 16));
        }
        textFile.close();

        this.tableau = sbox;
    }
    
    @Override
    public MotBinaire appliquer(MotBinaire entree) {
        return new MotBinaire(this.tableau.get(entree.asInteger()));
    }
    
}

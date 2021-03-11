/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptostarwars;

import cryptostarwars.modules.Module;
import cryptostarwars.modules.communication.Communication;
import cryptostarwars.modules.conversions.Conversions;
import cryptostarwars.modules.decryptage.Decryptage;
import cryptostarwars.modules.operations.Operations;
import cryptostarwars.modules.permutation.Permutation;
import cryptostarwars.modules.s_box.SBox;
import cryptostarwars.moteur_binaire.EnregistreurFichierBinaire;
import cryptostarwars.moteur_binaire.LecteurFichierBinaire;
import cryptostarwars.moteur_binaire.MotBinaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

/**
 *
 * @author Alexis Robin
 */
public class CryptoStarWars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        LecteurFichierBinaire lecteur1 = new LecteurFichierBinaire("C:/Users/jules/Desktop/CryptoStarWars/src/cryptostarwars/donnees/PlanCrypte");
        LecteurFichierBinaire lecteur2 = new LecteurFichierBinaire("C:/Users/jules/Desktop/CryptoStarWars/src/cryptostarwars/donnees/cle");
        EnregistreurFichierBinaire enregistreur = new EnregistreurFichierBinaire("C:/Users/jules/Desktop/plan.jpg");
        
        MotBinaire M, K, C;
        BitSet bitSetM, bitSetK;
        byte[] bytesM, bytesK;
        
        bytesM = lecteur1.nextBytes();
        bytesK = lecteur2.nextBytes();
        
        do {
            M = new MotBinaire();
        
            for (byte octet : bytesM) {
                M = new MotBinaire(octet).concatenation(M);
            }
            
            K = new MotBinaire();
        
            for (byte octet : bytesK) {
                K = new MotBinaire(octet).concatenation(K);
            }
            
            C = Decryptage.decrypter(M, K);


            enregistreur.enregistrer(C);
            
            bytesM = lecteur1.nextBytes();
            bytesK = lecteur2.nextBytes();
        } while (bytesM != null);
        
        lecteur1.close();
        lecteur2.close();
        enregistreur.close();
    }
}

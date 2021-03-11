/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptostarwars.modules.decryptage;

import cryptostarwars.connexion.Connexion;
import cryptostarwars.modules.Module;
import cryptostarwars.moteur_binaire.MotBinaire;
import cryptostarwars.moteur_binaire.boxes.PBox;
import cryptostarwars.moteur_binaire.boxes.SBox;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

/**
 *
 * @author jules
 */
public class Decryptage implements Module {
    
    /**
     * Algorithme de décryptage
     * @param M Le message de 32 bits dont on veut calculer F(M)
     * @return F(M)
     * @throws FileNotFoundException 
     */
    static public MotBinaire F(MotBinaire M) throws FileNotFoundException {
        MotBinaire mb;
        MotBinaire D;
        MotBinaire mbPBox;
        PBox pBox = new PBox();
        MotBinaire mbSBox;
        SBox sBox = new SBox("C:/Users/jules/Desktop/CryptoStarWars/src/cryptostarwars/donnees/sbox.txt");
        
        D = M.scinder(8).get(3);
        
        mbPBox = pBox.appliquer(D);
        
        mbSBox = sBox.appliquer(mbPBox);
        
        mb = mbSBox.additionMod2p32(M);
        
        return mb;
    }
    
    static public MotBinaire decrypter(MotBinaire M, MotBinaire K) throws FileNotFoundException {
        MotBinaire M1, M2, K1, K2, I1, I2, C, C1, C2;
        BitSet bitSetM, bitSetK;
        byte[] bytesM, bytesK;
        
        ArrayList<MotBinaire> mbM = M.scinder(32);
        M1 = mbM.get(1);
        M2 = mbM.get(0);
        K1 = K.scinder(32).get(1);
        K2 = K.scinder(32).get(0);

        I1 = M2.xor(Decryptage.F(M1.xor(K1))); 
        I2 = M1.xor(K1);
        C1 = I2.xor(Decryptage.F(I1.xor(K2)));
        C2 = I1.xor(K2);
        C = C1.concatenation(C2);
            
        return C;
    }

    @Override
    public void connexionPhase2() throws IOException {
        Connexion connexion = new Connexion("AHSKGUENSKRUJEN");
        connexion.start();
        
        String message1, message2, messageDecode;
        MotBinaire M, M1, M2, K, K1, K2, I1, I2, C, C1, C2;
        BitSet bitSetM, bitSetK;
        
        message1 = connexion.recevoirMessage();
        message2 = connexion.recevoirMessage();
        
        do {
            messageDecode = "";
            
            bitSetM = new BitSet(message1.length());
            bitSetK = new BitSet(message2.length());
            
            M = new MotBinaire(message1);
            K = new MotBinaire(message2);
            
            C = decrypter(M, K);
            
            messageDecode = C.toString();
            
            connexion.envoyerMessage(messageDecode);
            
            message1 = connexion.recevoirMessage();
            message2 = connexion.recevoirMessage();
        } while (!message1.equals("END") && !message2.equals("END"));
    }

    @Override
    public void connexionPhase3() throws IOException {
        
    }
    
}

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
        BitSet D = new BitSet();
        MotBinaire mbPBox;
        PBox pBox = new PBox();
        MotBinaire mbSBox;
        SBox sBox = new SBox("C:/Users/jules/Desktop/CryptoStarWars - Copie/src/cryptostarwars/donnees/sbox.txt");
        
        System.out.println("M = " + M.getBitSet());
        
        for (int i = 0; i < 8; i++) {
            D.set(i, M.getBitSet().get(i));
        }
        
        System.out.println("D = " + D);
        
        mbPBox = new MotBinaire(pBox.appliquer(new MotBinaire(D, 8)).getBitSet(), 8);
        
        System.out.println("PBox = " + mbPBox.getBitSet());
        
        mbSBox = new MotBinaire(sBox.appliquer(mbPBox).getBitSet(), 8);
        
        System.out.println("SBox = " + mbSBox.getBitSet());
        
        mb = M.additionMod2p32(new MotBinaire(mbSBox.getBitSet(), 8));
        
        return mb;
    }

    @Override
    public void connexionPhase2() throws IOException {
        Connexion connexion = new Connexion("AHSKGUENSKRUJEN");
        connexion.start();
        
        String message1, message2, messageDecode;
        MotBinaire motBinaire1, motBinaire2;
        BitSet bitSet1, bitSet2;
        
        do {
            message1 = connexion.recevoirMessage();
            message2 = connexion.recevoirMessage();
            messageDecode = "";
            
            bitSet1 = new BitSet(message1.length());
            bitSet2 = new BitSet(message2.length());
            
            // Conversion des messages en BitSet
            for (int i = 0; i < message1.length(); i++) {
                if (message1.charAt(i) == '1') {
                    bitSet1.set(i);
                }
                if (message2.charAt(i) == '1') {
                    bitSet2.set(i);
                }
            }
            
            motBinaire1 = new MotBinaire(bitSet1, message1.length());
            motBinaire2 = new MotBinaire(bitSet2, message1.length());
            
            BitSet resultat = this.F(motBinaire1).getBitSet();
            
            // Conversion du BitSet en String
            for (int i = 0; i < 64; i++) {
                if (resultat.get(i)) 
                    messageDecode += "1";
                else
                    messageDecode += "0";
            }
            
            connexion.envoyerMessage(messageDecode);
        } while (!message1.equals("END"));
    }

    @Override
    public void connexionPhase3() throws IOException {
        
    }
    
}

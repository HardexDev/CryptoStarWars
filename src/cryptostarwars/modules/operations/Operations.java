/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptostarwars.modules.operations;

import cryptostarwars.connexion.Connexion;
import cryptostarwars.modules.Module;
import cryptostarwars.moteur_binaire.MotBinaire;
import java.io.IOException;
import java.util.BitSet;

/**
 *
 * @author jules
 */
public class Operations implements Module {

    /*
    @Override
    public void connexionPhase2() throws IOException {
        // Mot de passe = caractères ASCII du mot de passe crypté
        Connexion connexion = new Connexion("65446970457584");
        connexion.start();
        
        String message1, message2, messageDecode;
        MotBinaire motBinaire1, motBinaire2;
        
        do {
            message1 = connexion.recevoirMessage();
            message2 = connexion.recevoirMessage();
            messageDecode = "";

            for (int i = 0; i < message1.length(); i++) {
                
                if (message1.charAt(i) == message2.charAt(i))
                    messageDecode += "0";
                else
                    messageDecode += "1";
                
            }

            connexion.envoyerMessage(messageDecode);
        } while (!message1.equals("END"));
    }
*/
    
    @Override
    public void connexionPhase2() throws IOException {
        // Mot de passe = caractères ASCII du mot de passe crypté
        Connexion connexion = new Connexion("65446970457584");
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
            motBinaire2 = new MotBinaire(bitSet2, message2.length());

            BitSet xor = motBinaire1.xor(motBinaire2).getBitSet();
            
            // Conversion du BitSet en String
            for (int i = 0; i < 32; i++) {
                if (xor.get(i)) 
                    messageDecode += "1";
                else
                    messageDecode += "0";
            }
            
            connexion.envoyerMessage(messageDecode);
        } while (!message1.equals("END"));
    }

    /*
    @Override
    public void connexionPhase3() throws IOException {
        // Erreur avec le mdp, normalement c'est un U à la fin et non un V
        Connexion connexion = new Connexion("UAMQLGAV");
        connexion.start();
        
        String message1, message2, messageDecode;
        int retenue;
        
        do {
            message1 = connexion.recevoirMessage();
            message2 = connexion.recevoirMessage();
            messageDecode = "";
            retenue = 0;

            for (int i = message1.length() - 1; i >= 0; i--) {
                int b1 = Integer.parseInt(String.valueOf(message1.charAt(i)));
                int b2 = Integer.parseInt(String.valueOf(message2.charAt(i)));
                
                System.out.println(b1 + " + " + b2 + " + " + retenue + " = " + (b1 + b2 + retenue));
                switch (b1 + b2 + retenue) {
                    case 0:
                        messageDecode = "0" + messageDecode;
                        break;
                    case 1:
                        retenue = 0;
                        messageDecode = "1" + messageDecode;
                        break;
                    case 2:
                        retenue = 1;
                        messageDecode = "0" + messageDecode;
                        break;
                    case 3:
                        retenue = 1;
                        messageDecode = "1" + messageDecode;
                        break;
                }
            }
            
            if (retenue == 1)
                messageDecode = "1" + messageDecode;

            connexion.envoyerMessage(messageDecode);
        } while (!message1.equals("END"));
    }
    */
    
    @Override
    public void connexionPhase3() throws IOException {
        // Erreur avec le mdp, normalement c'est un U à la fin et non un V
        Connexion connexion = new Connexion("UAMQLGAV");
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
            motBinaire2 = new MotBinaire(bitSet2, message2.length());
            
            BitSet somme = motBinaire1.additionMod2p32(motBinaire2).getBitSet();
            
            // Conversion du BitSet en String
            for (int i = 0; i < 32; i++) {
                if (somme.get(i)) 
                    messageDecode += "1";
                else
                    messageDecode += "0";
            }

            connexion.envoyerMessage(messageDecode);
        } while (!message1.equals("END"));
    }
}

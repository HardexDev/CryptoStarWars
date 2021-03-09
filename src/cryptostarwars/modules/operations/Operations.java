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
    
}

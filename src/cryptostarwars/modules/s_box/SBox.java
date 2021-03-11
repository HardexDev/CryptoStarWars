/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptostarwars.modules.s_box;

import cryptostarwars.connexion.Connexion;
import cryptostarwars.modules.Module;
import cryptostarwars.moteur_binaire.MotBinaire;

import java.io.IOException;

/**
 *
 * @author jules
 */
public class SBox implements Module {

    @Override
    public void connexionPhase2() throws IOException {
        Connexion connexion = new Connexion("1979897079");
        cryptostarwars.moteur_binaire.boxes.SBox sbox = new cryptostarwars.moteur_binaire.boxes.SBox("C:/Users/jules/Desktop/CryptoStarWars - Copie/src/cryptostarwars/donnees/sbox.txt");
        connexion.start();
        String messageRecu = connexion.recevoirMessage();
        while(!messageRecu.equals("END")){
            connexion.envoyerMessage(sbox.appliquer(new MotBinaire(messageRecu)).toString());
            messageRecu = connexion.recevoirMessage();
        }
    }

    @Override
    public void connexionPhase3() throws IOException {
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptostarwars.modules.conversions;

import com.sun.deploy.util.StringUtils;
import cryptostarwars.connexion.Connexion;
import cryptostarwars.modules.Module;
import cryptostarwars.moteur_binaire.MotBinaire;

import java.io.IOException;

/**
 *
 * @author jules
 */
public class Conversions implements Module {

    @Override
    public void connexionPhase2() throws IOException {
        Connexion connexion = new Connexion("YOU SHOOT LIKE A STORMTROOPER !");
        connexion.start();
        String messageRecu = connexion.recevoirMessage();
        while (!messageRecu.equals("END")) {
            try {
                Long l = Long.parseLong(messageRecu);
                connexion.envoyerMessage(new MotBinaire(l).toString());
                messageRecu = connexion.recevoirMessage();
            } catch (NumberFormatException e) {
                char c = messageRecu.charAt(0);
                connexion.envoyerMessage(new MotBinaire(c).toString());
                messageRecu = connexion.recevoirMessage();
            }
        }
    }

    @Override
    public void connexionPhase3() throws IOException {
        Connexion connexion = new Connexion("DARKVADOR");
        connexion.start();
        String messageRecu = connexion.recevoirMessage();
        for (int i=0; i<5; i++) {
            connexion.envoyerMessage(String.valueOf(new MotBinaire(messageRecu).asInteger()));
            messageRecu = connexion.recevoirMessage();
        }

        for (int i=0; i<5; i++) {
            connexion.envoyerMessage(new MotBinaire(messageRecu).asString());
            messageRecu = connexion.recevoirMessage();
        }
    }
    
}

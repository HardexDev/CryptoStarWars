/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptostarwars.modules.permutation;

import cryptostarwars.connexion.Connexion;
import cryptostarwars.modules.Module;
import cryptostarwars.moteur_binaire.MotBinaire;
import cryptostarwars.moteur_binaire.boxes.PBox;
import java.io.IOException;

/**
 *
 * @author jules
 */
public class Permutation implements Module {

    @Override
    public void connexionPhase2() throws IOException {
       Connexion connexion = new Connexion("10010011001110");
       PBox p = new PBox();
       connexion.start();
       String messageRecu = connexion.recevoirMessage();
       while(!messageRecu.equals("END")){
           connexion.envoyerMessage(p.appliquer(new MotBinaire(messageRecu)).toString());
           messageRecu = connexion.recevoirMessage();
       }
       
    }
    

    @Override
    public void connexionPhase3() throws IOException {
        
    }
    
}

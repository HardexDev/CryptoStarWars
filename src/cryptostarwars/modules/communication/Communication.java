package cryptostarwars.modules.communication;

import cryptostarwars.connexion.Connexion;
import cryptostarwars.modules.Module;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexis Robin
 */
public class Communication implements Module {
    
    public Communication() {}
    
    /**
     * Connexion à la phase 2 de Communication
     */
    @Override
    public void connexionPhase2() throws IOException {
        Connexion connexion = new Connexion("ANEWHOPE");
        connexion.start();
    }
    
    /**
     * Connexion à la phase 3 de Communication
     */
    @Override
    public void connexionPhase3() throws IOException {
        Connexion connexion = new Connexion("For the Emperor");
        connexion.start();
    }
}

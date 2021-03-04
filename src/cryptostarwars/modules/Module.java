/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptostarwars.modules;

import java.io.IOException;

/**
 *
 * @author jules
 */
public interface Module {
    /**
     * Connexion à la phase 2 d'un module
     */
    public void connexionPhase2() throws IOException;
    
    /**
     * Connexion à la phase 3 d'un module
     */
    public void connexionPhase3() throws IOException;
}

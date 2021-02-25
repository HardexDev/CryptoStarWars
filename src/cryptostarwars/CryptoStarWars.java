/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptostarwars;

import java.io.IOException;

/**
 *
 * @author Alexis Robin
 */
public class CryptoStarWars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Communication com = new Communication();
        com.start();
    }
    
}

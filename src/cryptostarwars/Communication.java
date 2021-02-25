package cryptostarwars;

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
public class Communication {
    private Socket socket;
    private BufferedReader fluxEntrant;
    private PrintWriter fluxSortant;
    private boolean isDiscussionTerminee; //La discussion avec le serveur est-elle terminée
    private String messageEnvoye; //Dernier message envoyé au serveur (ou devant être envoyé)
    private String messageRecu;
    
    public Communication(){
        
    }
    public void connexion() throws IOException {
        //Création du socket entre client et serveur
        this.socket = new Socket("127.0.0.1",1977);
    }
    public void creationFlux() throws IOException {
        //Création du gestionnaire de flux entrant
        InputStreamReader iSReader = new InputStreamReader(this.socket.getInputStream());
        this.fluxEntrant = new BufferedReader(iSReader);
        //Création du gestionnaire de flux sortant
        this.fluxSortant = new PrintWriter(this.socket.getOutputStream(),true);
    }
    public void envoyerMessage(String message) {
        System.out.println(">>"+message);
        this.fluxSortant.println(message);
    }
    public String recevoirMessage() throws IOException {
        String message = this.fluxEntrant.readLine();
        System.out.println("<<"+message);
        return message;
    }
    public void start() throws IOException {
        //LANCEMENT DU MODULE DE COMMUNICATION
        //Connexion au serveur
        this.connexion();
        //Création des gestoionnaires des flux de communication
        this.creationFlux();
        this.envoyerMessage("For the Emperor");
        messageRecu = this.recevoirMessage();
        while (!messageRecu.equals("END")){
            this.envoyerMessage(messageRecu);
            messageRecu = this.recevoirMessage();
        }
    }
}

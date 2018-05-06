/*
 * Description: Ce fichier est utilisé pour se connecter au serveur SMTP,
 *              envoyer les emails aux victimes et se déconnecter.
 * Fichier:     SmtpClient.java
 * Auteurs:     Cyril de Bourgues
 *              Nuno Miguel Cerca Abrantes Silva
 * Date:        23.04.2018
 */
package heigvd.res.fee.pkg2018.labo.pkg03.smtp;

import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author migue
 */
public interface ISmtpClient {
    
    /**
     * Permet de se connect à un serveur SMTP
     */
    public void connect();
    
    /**
     * Permet de se connect à un serveur SMTP
     * @param smtpServerAddress
     * @param smtpServerPort 
     */
    public void connect(String smtpServerAddress, int smtpServerPort);
    
    /**
     * Permet de se déconnecter proprement du serveur SMTP
     */
    public void disconnect();
    
    /**
     * Permet de se déconnecter proprement du serveur SMTP
     * @param pw
     * @param br
     * @param socket 
     */
    public void disconnect(PrintWriter pw, BufferedReader br, Socket socket);
    
    /**
     * Permet de créer l'email et de l'envoyer avec les différentes commandes
     * SMTP. A relever que la connection au serveur doit déjà être ouverte.
     * @param message contient les informations à mettre dans l'email
     * @throws IOException 
     */
    public void sendMessage(Message message) throws IOException;
}

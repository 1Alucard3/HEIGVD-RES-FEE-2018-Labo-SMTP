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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient{
    
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
    
    private String smtpServerAddress;
    private int smtpServerPort;
    private Socket socket;
    private PrintWriter pw;
    private BufferedReader br;
    private boolean autoFlush = true;
    
    /* Constructeur sans paramètre */
    public SmtpClient(){
    }
    
    /* Constructeur avec l'adresse du serveur sous forme d'un string et du
       numéro de port */
    public SmtpClient(String smtpServerAddress, int smtpServerPort){
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = smtpServerPort;
    }
    
    @Override
    public void connect(){
        try {
            /* Création du socket pour la communication entre le serveur et 
               le client */
            socket = new Socket(smtpServerAddress, smtpServerPort);
            
            /* Lis ce que le serveur envoie comme réponse */
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), autoFlush);
            /* Ecris les commandes au serveur */
            br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            
            String line = br.readLine();
            LOG.info(line);
            
            /* Lance une exception si le serveur envoie autre chose que 220  */
            if(!line.startsWith(ProtocoleSMTP.REP_SERVER_220)){
                throw new IOException("SMTP error: " + line);
            }
            
            pw.println(ProtocoleSMTP.CMD_HELLO);
            pw.flush();

            /* Permet de lire toutes les lignes envoyer par le server */
            while(!line.startsWith(ProtocoleSMTP.REP_SERVER_END_OK)){
                line = br.readLine();
                LOG.info(line);
            }
                    
        } catch (IOException ex) {
            Logger.getLogger(SmtpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void connect(String smtpServerAddress, int smtpServerPort){
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = smtpServerPort;
        
        connect();
    }

    @Override
    public void disconnect() {
        try {
            String line;
            pw.println(ProtocoleSMTP.CMD_QUIT);
            line = br.readLine();
            LOG.info(line);
            pw.flush();
            /* Important de ne pas oublier de fermer les sockets */
            br.close();
            pw.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(SmtpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void disconnect(PrintWriter pw, BufferedReader br, Socket socket){
        this.pw = pw;
        this.br = br;
        this.socket = socket;
        
        disconnect();
    }

    @Override
    public void sendMessage(Message message) throws IOException {
        String line;
      
        /* Commande qui initialise la création d'un email */
        pw.print(ProtocoleSMTP.CMD_MAIL_FROM);
        /* Récupère l'info*/
        pw.println(message.getFrom());
        pw.flush();
        line = br.readLine();
        LOG.info(line);
        
        /* Commande pour les récepteurs */
        for(String to : message.getTo()){
            pw.print(ProtocoleSMTP.CMD_RCPT_TO);
            pw.println(to);
            pw.flush();
            line = br.readLine();
            LOG.info(line);
        }
        
        /* Commande pour les personnes en copie */
        for(String to : message.getCc()){
            pw.print(ProtocoleSMTP.CMD_RCPT_TO);
            pw.println(to);
            pw.flush();
            line = br.readLine();
            LOG.info(line);
        }
        
        /* Commande pour les personnes en copie mais invisible */
        for(String to : message.getBcc()){
            pw.print(ProtocoleSMTP.CMD_RCPT_TO);
            pw.println(to);
            pw.flush();
            line = br.readLine();
            LOG.info(line);
        }
        
        /* Création de l'email */
        pw.println(ProtocoleSMTP.CMD_DATA);
        pw.flush();
        line = br.readLine();
        LOG.info(line);
        pw.println("Content-Type: text/plain; charset=\"utf-8\"");
        
        /* Expéditeur */
        pw.println(ProtocoleSMTP.MESSAGE_FROM + message.getFrom());
        
        /* Récepteurs */
        pw.print(ProtocoleSMTP.MESSAGE_TO + message.getTo()[0]);
        for(int i = 1; i < message.getTo().length; ++i){
            pw.print(", " + message.getTo()[i]);
        }
        pw.println();
        
        /* Personnes en copie */
        pw.print(ProtocoleSMTP.MESSAGE_CC + message.getCc()[0]);
        for(int i = 1; i < message.getCc().length; ++i){
            pw.print(", " + message.getCc()[i]);
        }
        pw.println();
        
        /* Personnes en copie mais invisible */
        if(message.getBcc().length > 0){
            pw.print(ProtocoleSMTP.MESSAGE_BCC + message.getBcc()[0]);
            for(int i = 1; i < message.getBcc().length; ++i){
                pw.print(", " + message.getBcc()[i]);
            }
            pw.println();
        }
        
        /* Sujet */
        if(!message.getSubject().equals("")){
            pw.println(ProtocoleSMTP.MESSAGE_SUBJECT + message.getSubject());
            pw.println();
        }
        
        pw.flush();
        
        LOG.info(message.getBody());
        pw.println(message.getBody());
        pw.println();
        pw.println(".");
        pw.flush();
        line = br.readLine();
        LOG.info(line);
        
    }

    
}

/*
 * Description: Fichier main de l'application. Cette dernière permet de
 *              générer des emails dont l'expéditeur et les récepteurs
 *              sont des victimes. C'est-à-dire que l'email est envoyé
 *              au nom de quelqu'un à d'autres personnes à leur insu.
 * Fichier:     MailRobot.java
 * Auteurs:     Cyril de Bourgues
 *              Nuno Miguel Cerca Abrantes Silva
 * Date:        23.04.2018
 */
package heigvd.res.fee.pkg2018.labo.pkg03;

import heigvd.res.fee.pkg2018.labo.pkg03.config.ConfigurationManager;
import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Group;
import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Person;
import heigvd.res.fee.pkg2018.labo.pkg03.model.prank.Prank;
import heigvd.res.fee.pkg2018.labo.pkg03.model.prank.PrankGenerator;
import heigvd.res.fee.pkg2018.labo.pkg03.smtp.SmtpClient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class MailRobot {

    public static void main(String[] args) throws UnknownHostException, IOException {

        ConfigurationManager cm = new ConfigurationManager(); 
        SmtpClient sc = new SmtpClient(cm.getSMTPServerAddr(), cm.getSMTPServerPort());
        PrankGenerator pg = new PrankGenerator(cm);
        List<Prank> lp = pg.generatePranks();
        
        /* Connexion au serveur SMTP */
        sc.connect();
        
        /* Génère les différents emails à envoyer */
        for(Prank p : lp){
            sc.sendMessage(p.generateMailMessage());       
        }
        
        /* Déconnexion du serveur SMTP */
        sc.disconnect();
       
    }
    
}

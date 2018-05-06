/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author migue
 */
public class MailRobot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        //System.out.println("Bonjour, Bienvenue!!");
        //System.out.print("Veuillez entrer le nombre de groupe de victime que vous désirez faire: ");
        //int nbGroup;
        //Person andre = new Person()
        //Group victims = new Group();
        
        
        ConfigurationManager cm = new ConfigurationManager();
        
        SmtpClient sc = new SmtpClient(cm.getSmtpServerAddress(), cm.getSmtpServerPort());
        PrankGenerator pg = new PrankGenerator(cm);
        List<Prank> lp = pg.generatePranks();
        
        
        sc.connect();
        
        for(Prank p : lp){
            sc.sendMessage(p.generateMailMessage());       
        }
        
        sc.disconnect();
       
    }
    
}

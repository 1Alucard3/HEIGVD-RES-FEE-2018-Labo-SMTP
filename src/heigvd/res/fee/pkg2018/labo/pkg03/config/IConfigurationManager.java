/*
 * Description: Interface du fichier ConfigurationManager.
 * Fichier:     IConfigurationManager.java
 * Auteurs:     Cyril de Bourgues
 *              Nuno Miguel Cerca Abrantes Silva
 * Date:        23.04.2018
 */
package heigvd.res.fee.pkg2018.labo.pkg03.config;

import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Person;
import java.util.List;

public interface IConfigurationManager {
    
    /**
     * Retourne la liste des victimes
     * @return 
     */
    public List<Person> getVictims();
    
    /**
     * Set la liste des victimes
     * @param victims 
     */
    public void setVictims(List<Person> victims);
    
    /**
     * Retourne la liste des messages
     * @return 
     */
    public List<String> getMessages();
    
    /**
     * Set la liste des messages
     * @param messages 
     */
    public void setMessages(List<String> messages);
    
    /**
     * Retourne le nombre de groupe
     * @return 
     */
    public int getNbGroups();
    
    /**
     * Set le nombre de groupe de victimes
     * @param nbGroups 
     */
    public void setNbGroups(int nbGroups);
    
    /**
     * Retourne l'adresse du server SMTP sous forme d'un String
     * @return 
     */
    public String getSMTPServerAddr();
    
    /**
     * Set l'adresse du serveur
     * @param smtpServerAddr 
     */
    public void setSMTPServerAddr(String smtpServerAddr);
    
    /**
     * Retourne le numéro de port sur lequel le serveur écoute
     * @return 
     */
    public int getSMTPServerPort();
    
    /**
     * Set le port sur lequel le serveur va écouter
     * @param smtpServerAddr 
     */
    public void setSMTPServerPort(int smtpServerAddr);
    
    /**
     * Retourne les personnes à mettre en Cc 
     * @return 
     */
    public List<Person> getPersonsToCC();
    
    /**
     * Set les personnes à mettre en Cc
     * @param personsToCC 
     */
    public void setPersonsToCC(List<Person> personsToCC);
    
    /**
     * Retourne les personnes à mettre en copie mais invisible 
     * @return 
     */
    public List<Person> getPersonsToBCC();
    
    /**
     * Set les personnes à mettre en copie mais invisible
     * @param personsToCC 
     */
    public void setPersonsToBCC(List<Person> personsToBCC);
    
}

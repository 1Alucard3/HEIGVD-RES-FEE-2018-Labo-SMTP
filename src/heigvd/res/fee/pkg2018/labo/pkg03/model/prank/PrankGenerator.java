/*
 * Description: Cette classe permet de générer les différentes plaisanteries
 *              Cela prend les différents éléments et génère une liste de Prank
 * Fichier:     PrankGenerator.java
 * Auteurs:     Cyril de Bourgues
 *              Nuno Miguel Cerca Abrantes Silva
 * Date:        23.04.2018
 */
package heigvd.res.fee.pkg2018.labo.pkg03.model.prank;

import heigvd.res.fee.pkg2018.labo.pkg03.config.ConfigurationManager;
import heigvd.res.fee.pkg2018.labo.pkg03.config.IConfigurationManager;
import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Group;
import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class PrankGenerator {
    
    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());
    
    private ConfigurationManager configurationManager;
    
    /* Constructeur avec un objet ConfigurationManager en paramètre. C'est ce 
       dernier qui contient toutes les infos sur les victimes et le nombre de
       groupe à faire, etc ... */
    public PrankGenerator(ConfigurationManager configurationManager){
        this.configurationManager = configurationManager;
    }
    
    /**
     * Génère une liste de Prank (plaisanterie)
     * @return une liste de Prank
     */
    public List<Prank> generatePranks(){
        
        /* Tableau des Prank*/
        List<Prank> pranks = new ArrayList<>();
        
        /* Récupération de la liste des messages */
        List<String> messages = configurationManager.getMessages();
        int messageIndex = 0;
        
        /* Nomber de groupe et de victimes */
        int nbGroups = configurationManager.getNbGroups();
        int nbVictims = configurationManager.getVictims().size();
        
        /* Vérification qu'on a au moins 3 personnes par groupe */
        if(nbVictims / nbGroups < 3){
            nbGroups = nbVictims / 3;
            LOG.warning("Il n'y a pas assez de victimes. Il faut au minimum 3 victimes par groupe.");          
        }
        
        /* On génère les groupes de victimes */
        List<Group> groups = generateGroups(configurationManager.getVictims(), nbGroups);
        
        /* Boucle pour parcourir les différents groupes de victimes et créer les Prank */
        for(Group group : groups){
            Prank prank = new Prank();
            
            List<Person> victims = group.getMembers();
            /* On mélange les victimes mais on aurait pour faire que celle en position 
               0 était l'expéditeur si on veut être sûr de qui c'est */
            Collections.shuffle(victims);
            Person sender = victims.remove(0);
            /* Expéditeur */
            prank.setVictimSender(sender);
            /* Récepteurs */
            prank.addVictimRecipients(victims);
            
            /* Ajoute les personnes en copie */
            prank.addPersonsToCC(configurationManager.getPersonsToCC());
            
            /* Ajoute les personnes en copie mais invisible */
            prank.addPersonsToBCC(configurationManager.getPersonsToBCC());
            
            String message = messages.get(messageIndex);
            
            /* Ajoute le message (sujet et texte */
            prank.setMessage(message);
            messageIndex = (messageIndex + 1) % messages.size();
            pranks.add(prank);
        }
        
        return pranks;
    }
    
    /**
     * Méthode qui permet de générer les groupes selon le nombre de victimes et
     * de groupe à faire
     * @param victims contient la liste des victimes
     * @param numberOfGroups contient le nombre de groupe à faire
     * @return 
     */
    public List<Group> generateGroups(List<Person> victims, int numberOfGroups){
        List<Person> availablesVictims = new ArrayList(victims);
        /* Mélange les victimes afin de créer des groupes aléatoires. Il est 
           possible d'enlever cela si on désire faire un groupe bien spécifique */
        Collections.shuffle(availablesVictims);
        List<Group> groups = new ArrayList<>();
        
        /* Ajoute un objet Group à la liste des groupes*/
        for(int i = 0; i < numberOfGroups; ++i){
            Group group = new Group();
            groups.add(group);
        }
        
        int turn = 0;
        
        /* Ajoute les victimes une à la fois par groupe */
        while(availablesVictims.size() > 0){
            turn = (turn + 1) % groups.size();
            Person victim = availablesVictims.remove(0);
            groups.get(turn).addMember(victim);
        }
        
        return groups;
    }
}

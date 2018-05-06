/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author migue
 */
public class PrankGenerator {
    
    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());
    
    private ConfigurationManager configurationManager;
    
    public PrankGenerator(ConfigurationManager configurationManager){
        this.configurationManager = configurationManager;
    }
    
    public List<Prank> generatePranks(){
        List<Prank> pranks = new ArrayList<>();
        
        List<String> messages = configurationManager.getMessages();
        int messageIndex = 0;
        
        int numberOfGroups = configurationManager.getNumberOfGroups();
        int numberOfVictims = configurationManager.getVictims().size();
        
        if(numberOfVictims / numberOfGroups < 3){
            numberOfGroups = numberOfVictims / 3;
            LOG.warning("There are not enough victims to generate the desired number of groups. We can only generate a max of " 
                    + numberOfGroups + " groups to have a least 3 victims per group.");
            
        }
        
        List<Group> groups = generateGroups(configurationManager.getVictims(), numberOfGroups);
        for(Group group : groups){
            Prank prank = new Prank();
            
            List<Person> victims = group.getMembers();
            Collections.shuffle(victims);
            Person sender = victims.remove(0);
            prank.setVictimSender(sender);
            prank.addVictimRecipients(victims);
            
            prank.addWitnessRecipients(configurationManager.getWitnessesToCC());
            
            String message = messages.get(messageIndex);
            prank.setMessage(message);
            messageIndex = (messageIndex + 1) % messages.size();
            pranks.add(prank);
        }
        
        return pranks;
    }
    
    public List<Group> generateGroups(List<Person> victims, int numberOfGroups){
        List<Person> availablesVictims = new ArrayList(victims);
        Collections.shuffle(availablesVictims);
        List<Group> groups = new ArrayList<>();
        for(int i = 0; i < numberOfGroups; ++i){
            Group group = new Group();
            groups.add(group);
        }
        int turn = 0;
        //Group targetGroup;
        while(availablesVictims.size() > 0){
            //targetGroup = groups.get(turn);
            turn = (turn + 1) % groups.size();
            Person victim = availablesVictims.remove(0);
            groups.get(turn).addMember(victim);
            //targetGroup.addMember(victim);
        }
        
        return groups;
    }
}

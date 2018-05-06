/*
 * Description: Cette classe permet de créer l'objet qui représente la plaisanterie
 *              effectuée à la victime. Cela génère donc l'email à envoyer.
 * Fichier:     Prank.java
 * Auteurs:     Cyril de Bourgues
 *              Nuno Miguel Cerca Abrantes Silva
 * Date:        23.04.2018
 */
package heigvd.res.fee.pkg2018.labo.pkg03.model.prank;

import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Message;
import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Prank {
    private Person victimSender;
    private List<Person> victimRecipients = new ArrayList<>();
    private List<Person> personsToCC = new ArrayList<>();
    private List<Person> personsToBCC = new ArrayList<>();
    
    private String message;
    /* Constructeur sans paramètre */
    public Prank(){}
    
    /* Constructeur avec tous les attributs comme paramètres */
    public Prank(Person victimSender, List<Person> victimRecipients, List<Person> personsToCC, List<Person> personsToBCC, String message){
        this.victimSender = victimSender;
        this.victimRecipients = victimRecipients;
        this.personsToCC = personsToCC;
        this.personsToBCC = personsToBCC;
        this.message = message;
    }
    
    /**
     * Génère l'email à envoyer.
     * @return 
     */
    public Message generateMailMessage(){
        Message msg = new Message() {};
        
        /* Expéditeur */
        msg.setFrom(victimSender.getEmailAddr());
        
        /* Récepteurs */
        String[] to = victimRecipients
                .stream()
                .map(p->p.getEmailAddr())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setTo(to);
        
        /* Les personnes en copie */
        String[] cc = personsToCC
                .stream()
                .map(p->p.getEmailAddr())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setCc(cc);
        
        /* Les personnes en copie invisible */
        String[] Bcc = personsToBCC
                .stream()
                .map(p->p.getEmailAddr())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setBcc(Bcc);
        
        /* Le sujet s'il y en a un */
        if(!msg.getSubject().equals("")){
            msg.setSubject(message);
        }
        
        /* Le texte de l'email */
        msg.setBody(this.message + victimSender.getFirstName());
        
        return msg;
    }
    
    /**
     * Retourne l'expéditeur
     * @return 
     */
    public Person getVictimSender(){
        return victimSender;
    }
    
    /**
     * Set un expéditeur
     * @param victimSender 
     */
    public void setVictimSender(Person victimSender){
        this.victimSender = victimSender;
    }
    
    /**
     * Retourne le message
     * @return 
     */
    public String getMessage(){
        return message;
    }
    
    /**
     * Set le message
     * @param message 
     */
    public void setMessage(String message){
        this.message = message;
    }
    
    /**
     * Ajoute une victime dans la liste des récepteurs
     * @param victims 
     */
    public void addVictimRecipients(List<Person> victims){
        victimRecipients.addAll(victims);
    }
    
    /**
     * Ajoute une personne à la liste des personnes en copie
     * @param personsToCC 
     */
    public void addPersonsToCC(List<Person> personsToCC){
        this.personsToCC.addAll(personsToCC);
    }
    
    /**
     * Ajoute une personne à la liste des personnes en copie mais non visible
     * @param personsToBCC 
     */
    public void addPersonsToBCC(List<Person> personsToBCC){
        this.personsToBCC.addAll(personsToBCC);
    }
    
    /**
     * Retourne une liste des récepteurs
     * @return 
     */
    public List<Person> getVictimRecipients(){
        return new ArrayList(victimRecipients);
    }
    
    /**
     * Retourne la liste des personnes en copie
     * @return 
     */
    public List<Person> getPersonsToCC(){
        return new ArrayList(personsToCC);
    }
    
    /**
     * Retourne la liste des personnes en copie mais invisible
     * @return 
     */
    public List<Person> getPersonsToBCC(){
        return new ArrayList(personsToBCC);
    }
}

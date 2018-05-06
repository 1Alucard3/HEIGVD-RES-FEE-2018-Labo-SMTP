/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heigvd.res.fee.pkg2018.labo.pkg03.model.prank;

import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Message;
import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author migue
 */
public class Prank {
    private Person victimSender;
    private final List<Person> victimRecipients = new ArrayList<>();
    private final List<Person> witnessRecipients = new ArrayList<>();
    private String message;
    
    public Person getVictimSender(){
        return victimSender;
    }
    
    public void setVictimSender(Person victimSender){
        this.victimSender = victimSender;
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    
    public void addVictimRecipients(List<Person> victims){
        victimRecipients.addAll(victims);
    }
    
    public void addWitnessRecipients(List<Person> witnesses){
        witnessRecipients.addAll(witnesses);
    }
    
    public List<Person> getVictimRecipients(){
        return new ArrayList(victimRecipients);
    }
    
    public List<Person> getWitnessRecipients(){
        return new ArrayList(witnessRecipients);
    }
    
    public Message generateMailMessage(){
        Message msg = new Message() {};
        
        msg.setFrom(victimSender.getAddress());
        
        String[] to = victimRecipients
                .stream()
                .map(p->p.getAddress())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setTo(to);
        
        String[] cc = witnessRecipients
                .stream()
                .map(p->p.getAddress())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setCc(cc);
        
        if(!msg.getSubject().equals("")){
            msg.setSubject(message);
        }
        
        msg.setBody(this.message + victimSender.getFirstName());
        
        return msg;
    }
}

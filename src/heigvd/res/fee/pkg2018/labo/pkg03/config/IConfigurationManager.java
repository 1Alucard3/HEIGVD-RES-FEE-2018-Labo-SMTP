/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heigvd.res.fee.pkg2018.labo.pkg03.config;

import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Person;
import java.util.List;

/**
 *
 * @author migue
 */
public interface IConfigurationManager {
    
    public List<Person> getVictims();
    
    public List<String> getMessages();
    
    public int getNumberOfGroups();
    
    public String getSmtpServerAddress();
    
    public int getSmtpServerPort();
    
    public List<Person> getWitnessesToCC();
    
}

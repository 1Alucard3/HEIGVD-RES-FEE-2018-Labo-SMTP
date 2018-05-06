/*
 * Description: Cette classe Person permet de créer un fichier de type Person.
 *              Un objet Person a 3 attributs; un prénom, un nom et une adresse
 *              email. Dans cette application, les personnes sont les différentes
 *              victimes.
 * Fichier:     Person.java
 * Auteurs:     Cyril de Bourgues
 *              Nuno Miguel Cerca Abrantes Silva
 * Date:        23.04.2018
 */
package heigvd.res.fee.pkg2018.labo.pkg03.model.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    
    private String firstName;
    private String lastName;
    private String emailAddr;
    
    /* Constructeur sans paramètre */
    public Person(){}
    
    /* Constructeur avec les différents attributs */
    public Person(String firstName, String lastName, String emailAddr){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddr = emailAddr;
    }
    
    /* Constructeur avec uniquement l'adresse email comme paramètre */
    public Person(String emailAddr){
        this.emailAddr = emailAddr;
        
        /* RegEx permettant de récupérer le prénom et le nom depuis l'adresse email */
        Pattern pattern = Pattern.compile("(.*)\\.(.*)@");
        Matcher matcher = pattern.matcher(emailAddr);
        boolean found = matcher.find();
        if(found){
            this.firstName = matcher.group(1);
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            this.lastName = matcher.group(2);
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        }
    }
    
    /**
     * Retourne le prénom
     * @return 
     */
    public String getFirstName(){
        return firstName;
    }
    
    /**
     * Set le prénom
     * @param firstName 
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    /**
     * Retourne le nom
     * @return 
     */
    public String getLastName(){
        return lastName;
    }
    
    /**
     * Set le nom
     * @param lastName 
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    /**
     * Retourne l'email
     * @return 
     */
    public String getEmailAddr(){
        return emailAddr;
    }
    
    /**
     * Set l'email
     * @param emailAddr 
     */
    public void setEmailAddr(String emailAddr){
        this.emailAddr = emailAddr;
    }
}

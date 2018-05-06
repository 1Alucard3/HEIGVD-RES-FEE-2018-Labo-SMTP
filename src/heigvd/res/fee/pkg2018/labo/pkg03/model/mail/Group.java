/*
 * Description: Cette classe permet de créer un groupe de personnes.
 *              
 * Fichier:     Group.java
 * Auteurs:     Cyril de Bourgues
 *              Nuno Miguel Cerca Abrantes Silva
 * Date:        23.04.2018
 */
package heigvd.res.fee.pkg2018.labo.pkg03.model.mail;

import java.util.ArrayList;
import java.util.List;

public class Group {
    
    private List<Person> members = new ArrayList<>();
    
    /* Constructeur sans paramètre */
    public Group(){}
    
    /* Constructeur avec une list de personne */
    public Group(List<Person> members){
        this.members = members;
    }
    
    /**
     * Ajoute une personne dans le groupe
     * @param person 
     */
    public void addMember(Person person){
        members.add(person);
    }
    
    /**
     * Supprime une personne dans le groupe. Attention le groupe ne peut 
     * contenir moins de 3 personnes.
     * @param pos est la position dans le groupe de la personne à enlever
     * @return
     */
    public boolean delMember(int pos){
        if(members.size() - 1 >= pos && members.size() > 3){
            members.remove(pos);
            return true;
        }
        return false;
    }
    
    /**
     * Supprime une personne dans le groupe. Attention le groupe ne peut 
     * contenir moins de 3 personnes.
     * @param personFirstName est le prénom de la personne
     * @param personLastName est le nom de famille de la personne
     * @return 
     */
    public boolean delMember(String personFirstName, String personLastName){
        for(Person p : members){
            if(p.getFirstName().equals(personFirstName) && p.getLastName().equals(personLastName) && members.size() > 3){
                members.remove(p);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Retourne un tableau avec les membres
     * @return 
     */
    public List<Person> getMembers(){
        return new ArrayList<>(members);
    }
    
    /**
     * Set une nouvelle liste de membres
     * @param members 
     */
    public void setMembers(List<Person> members){
        this.members = members;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heigvd.res.fee.pkg2018.labo.pkg03.model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author migue
 */
public class Group {
    
    private final List<Person> members = new ArrayList<>();
    
    public void addMember(Person person){
        members.add(person);
    }
    
    public List<Person> getMembers(){
        return new ArrayList<>(members);
    }
}

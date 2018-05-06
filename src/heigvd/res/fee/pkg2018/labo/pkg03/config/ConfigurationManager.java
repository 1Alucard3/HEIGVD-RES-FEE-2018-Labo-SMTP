/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heigvd.res.fee.pkg2018.labo.pkg03.config;

import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Person;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author migue
 */
public class ConfigurationManager implements IConfigurationManager{
    private String smtpServerAddress;
    private int smtpServerPort;
    private final List<Person> victims;
    private final List<String> messages;
    private int numberOfGroups;
    private List<Person> witnessesToCC;
    
    public ConfigurationManager() throws IOException{
        victims = loadAddressesFromFile(".\\src\\heigvd\\res\\fee\\pkg2018\\labo\\pkg03\\config\\victims.RES.utf8");
        messages = loadMessagesFromFile(".\\src\\heigvd\\res\\fee\\pkg2018\\labo\\pkg03\\config\\messages.utf8");
        loadProperties(".\\src\\heigvd\\res\\fee\\pkg2018\\labo\\pkg03\\config\\config.properties");
    }
    
    private void loadProperties(String fileName) throws IOException{
        FileInputStream fis = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.load(fis);
        this.smtpServerAddress = properties.getProperty("smtpServerAddress");
        this.smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        this.numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));
        
        this.witnessesToCC = new ArrayList<>();
        String witnesses = properties.getProperty("witnessesToCC");
        String[] witnessesAddresses = witnesses.split(",");
        for(String address : witnessesAddresses){
            this.witnessesToCC.add(new Person(address));
        }
    }
    
    private List<Person> loadAddressesFromFile(String fileName) throws IOException{
        List<Person> result;
        try(FileInputStream fis = new FileInputStream(fileName)){
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            try(BufferedReader reader = new BufferedReader(isr)){
                result = new ArrayList<>();
                String address = reader.readLine();
                while(address != null){
                    result.add(new Person(address));
                    address = reader.readLine();
                }
            }
        }
        return result;
    }
    
    private List<String> loadMessagesFromFile(String fileName) throws IOException{
        List<String> result;
        try(FileInputStream fis = new FileInputStream(fileName)){
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            try(BufferedReader reader = new BufferedReader(isr)){
                result = new ArrayList<>();
                String line = reader.readLine();
                while(line != null){
                    StringBuilder body = new StringBuilder();
                    while((line != null) && (!line.equals("==="))){
                        body.append(line);
                        body.append("\r\n");
                        line = reader.readLine();
                    }
                    result.add(body.toString());
                    line = reader.readLine();
                }
            }
        }
        return result;
    }
    
    @Override
    public List<Person> getVictims(){
        return victims;
    }
    
    @Override
    public List<String> getMessages(){
        return messages;
    }
    
    @Override
    public int getNumberOfGroups(){
        return numberOfGroups;
    }
    
    @Override
    public String getSmtpServerAddress(){
        return smtpServerAddress;
    }
    
    @Override
    public int getSmtpServerPort(){
        return smtpServerPort;
    }
    
    @Override
    public List<Person> getWitnessesToCC(){
        return witnessesToCC;
    }
}

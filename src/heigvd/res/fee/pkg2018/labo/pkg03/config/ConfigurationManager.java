/*
 * Description: Cette classe permet de récupérer différentes informations
 *              dans plusieurs fichiers de configuration:
 *              1) config.properties contient l'adresse du serveur, le port sur
 *                 lequel le contacter, le nombre de groupe de victime à générer
 *                 et pour finir il est possible de mettre plusieurs personnes
 *                 en Cc. Ils doivent être séparés par une virgule.
 *              2) messages.utf8 contient les différents messages à envoyer dans
 *                 les emails.
 *              3) victimes.RES.utf8 contient les adresses emails des différentes
 *                 victimes.
 * Fichier:     ConfigurationManager.java
 * Auteurs:     Cyril de Bourgues
 *              Nuno Miguel Cerca Abrantes Silva
 * Date:        23.04.2018
 */
package heigvd.res.fee.pkg2018.labo.pkg03.config;

import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Person;
import heigvd.res.fee.pkg2018.labo.pkg03.smtp.ProtocoleSMTP;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigurationManager implements IConfigurationManager{
    private String smtpServerAddr;
    private int smtpServerPort;
    private List<Person> victims;
    private List<String> messages;
    private int nbGroups;
    private List<Person> personsToCC;
    private List<Person> personsToBCC;
    private final String MESSAGE_SEPARATOR = "===";
    private final String ENCODAGE_UTF8 = "UTF-8";
    
    /* Constructeur sans paramètre */
    public ConfigurationManager() throws IOException{
        String del;
        del = FileSystems.getDefault().getSeparator();
        
        victims = loadEmailAddressesFromFile("."+del+"src"+del+"heigvd"+del+"res"+del+"fee"+del+"pkg2018"+del+"labo"+del+"pkg03"+del+"config"+del+"victims.RES.utf8");
        messages = loadBodyMessagesFromFile("."+del+"src"+del+"heigvd"+del+"res"+del+"fee"+del+"pkg2018"+del+"labo"+del+"pkg03"+del+"config"+del+"messages.utf8");
        loadProperties("."+del+"src"+del+"heigvd"+del+"res"+del+"fee"+del+"pkg2018"+del+"labo"+del+"pkg03"+del+"config"+del+"config.properties");
    }
    
    /* Constructeur avec les chemins des fichiers à utiliser */
    public ConfigurationManager(String pathFileVictims, String pathFileMessages, String pathFileConfig) throws IOException{
        victims = loadEmailAddressesFromFile(pathFileVictims);
        messages = loadBodyMessagesFromFile(pathFileMessages);
        loadProperties(pathFileConfig);
    }
    
    /* Constructeur avec les différents attributs passés en paramètre */
    public ConfigurationManager(String smtpServerAddr, int smtpServerPort, List<Person> victims, List<String> messages, int nbGroups, List <Person> personsToCC, List<Person> personsToBCC){
        this.smtpServerAddr = smtpServerAddr;
        this.smtpServerPort = smtpServerPort;
        this.victims = victims;
        this.messages = messages;
        this.nbGroups = nbGroups;
        this.personsToCC = personsToCC;
        this.personsToBCC = personsToBCC;
    }
    
    /**
     * Permet de charger différents informations depuis le fichier config.properties
     * Notamment l'adresse du serveur, le port, le nombre de groupe et les personnes
     * qui seront en Cc de l'email
     * @param fileName
     * @throws IOException 
     */
    private void loadProperties(String fileName) throws IOException{
        FileInputStream fis = new FileInputStream(fileName);
        Properties properties = new Properties();
        /* Permet de lire une liste de propriétés depuis un InputStream */
        properties.load(fis);
        /* Recherche et affectation par les noms des propriétés */
        this.smtpServerAddr = properties.getProperty("smtpServerAddress");
        this.smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        this.nbGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));
        
        /* Comme il est possible d'avoir plusieurs personnes en Cc, il est nécessaire
           d'utiliser le même séparateur avec .split que celui utiliser dans le fichier
           config.properties */
        this.personsToCC = new ArrayList<>();
        String strPersonsToCC = properties.getProperty("personsToCC");
        String[] personsToCCAddr = strPersonsToCC.split(";");
        for(String emailAddr : personsToCCAddr){
            this.personsToCC.add(new Person(emailAddr));
        }
        
        /* Comme il est possible d'avoir plusieurs personnes en Bcc, il est nécessaire
           d'utiliser le même séparateur avec .split que celui utiliser dans le fichier
           config.properties */
        this.personsToBCC = new ArrayList<>();
        String strPersonsToBCC = properties.getProperty("personsToBCC");
        String[] personsToBCCAddr = strPersonsToBCC.split(";");
        for(String emailAddr : personsToBCCAddr){
            this.personsToBCC.add(new Person(emailAddr));
        }
    }
    
    /**
     * Charge les adresses emails des victimes depuis le fichier victims.RES.utf8
     * @param fileName
     * @return la liste des victimes chacune d'elle est un objet Person
     * @throws IOException 
     */
    private List<Person> loadEmailAddressesFromFile(String fileName) throws IOException{
        /* Crée un input stream à partir d'un string */
        FileInputStream fis = new FileInputStream(fileName);
        /* Lis la input stream en tant que UTF-8 et crée l'objet InputStreamReader */
        InputStreamReader isr = new InputStreamReader(fis, ENCODAGE_UTF8);
        /* On le place dans un BufferedReader pour ensuite le lire ligne par ligne 
           chaque ligne est une adresses emails différentes */
        BufferedReader br = new BufferedReader(isr);
        List<Person> tempVictims = new ArrayList<>();
        String address = br.readLine();
        while(address != null){
            /* Instancie un objet Person avec l'adresse email */
            tempVictims.add(new Person(address));
            address = br.readLine();
        }
        
        return tempVictims;
    }
    
    /**
     * Permet de charger les différents messages à envoyer aux victimes depuis
     * un fichier
     * @param fileName
     * @return la liste des différents messages sous forme d'une liste de String
     * @throws IOException 
     */
    private List<String> loadBodyMessagesFromFile(String fileName) throws IOException{
        /* Crée un input stream à partir d'un string */
        FileInputStream fis = new FileInputStream(fileName);
        /* Lis la input stream en tant que UTF-8 et crée l'objet InputStreamReader */
        InputStreamReader isr = new InputStreamReader(fis, ENCODAGE_UTF8);
        /* On le place dans un BufferedReader pour ensuite le lire ligne par ligne 
           chaque ligne */
        BufferedReader reader = new BufferedReader(isr);
        List<String> tempMessages = new ArrayList<>();
        String line = reader.readLine();
        while(line != null){
            StringBuilder body = new StringBuilder();
            while((line != null) && (!line.equals(MESSAGE_SEPARATOR))){
                body.append(line);
                body.append(ProtocoleSMTP.LINE_FEED_AND_CARRIAGE_RETURN);
                line = reader.readLine();
            }
            tempMessages.add(body.toString());
            line = reader.readLine();
        }
        return tempMessages;
    }
    
    @Override
    public List<Person> getVictims(){
        return victims;
    }
    
    @Override
    public void setVictims(List<Person> victims){
        this.victims = victims;
    }
    
    @Override
    public List<String> getMessages(){
        return messages;
    }
    
    @Override
    public void setMessages(List<String> messages){
        this.messages = messages;
    }
    
    @Override
    public int getNbGroups(){
        return nbGroups;
    }
    
    @Override
    public void setNbGroups(int nbGroups){
        this.nbGroups = nbGroups;
    }
    
    @Override
    public String getSMTPServerAddr(){
        return smtpServerAddr;
    }
    
    @Override
    public void setSMTPServerAddr(String smtpServerAddr){
        this.smtpServerAddr = smtpServerAddr;
    }
    
    @Override
    public int getSMTPServerPort(){
        return smtpServerPort;
    }
    
    @Override
    public void setSMTPServerPort(int smtpServerPort){
        this.smtpServerPort = smtpServerPort;
    }
    
    @Override
    public List<Person> getPersonsToCC(){
        return personsToCC;
    }
    
    @Override
    public void setPersonsToCC(List<Person> personsToCC){
        this.personsToCC = personsToCC;
    }
    
    @Override
    public List<Person> getPersonsToBCC(){
        return personsToBCC;
    }
    
    @Override
    public void setPersonsToBCC(List<Person> personsToBCC){
        this.personsToBCC = personsToBCC;
    }
}

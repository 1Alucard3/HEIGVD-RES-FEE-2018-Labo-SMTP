/*
 * Description: Cette classe est un objet qui contient tous les éléments d'un
                email.
 * Fichier:     Message.java
 * Auteurs:     Cyril de Bourgues
 *              Nuno Miguel Cerca Abrantes Silva
 * Date:        23.04.2018
 */
package heigvd.res.fee.pkg2018.labo.pkg03.model.mail;

public class Message {
    private String from = "";
    private String[] to = new String[0];
    private String[] cc = new String[0];
    private String[] bcc = new String[0];
    private String subject = "";
    private String body = "";
    
    /* Constructeur sans paramètre */
    public Message(){}
    
    /* Constructeur avec tous les attributs en paramètres */
    public Message(String from, String[] to, String[] cc, String[] bcc, String subject, String body){
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
    }
    
    /**
     * Retourne l'expéditeur
     * @return 
     */
    public String getFrom(){
        return from;
    }
    
    /**
     * Set un expéditeur
     * @param from 
     */
    public void setFrom(String from){
        this.from = from;
    }
    
    /**
     * Retourne un tableau (peuvent plusieurs) avec les récepteurs
     * @return 
     */
    public String[] getTo(){
        return to;
    }
    
    /**
     * Set un tableau de récepteurs
     * @param to 
     */
    public void setTo(String[] to){
        this.to = to;
    }
    
    /**
     * Retourne un tableau (peuvent plusieurs) avec les Cc
     * @return 
     */
    public String[] getCc(){
        return cc;
    }
    
    /**
     * Set un tableau de Cc
     * @param cc 
     */
    public void setCc(String[] cc){
        this.cc = cc;
    }
    
    /**
     * Retourne un tableau (peuvent plusieurs) avec les Bcc
     * @return 
     */
    public String[] getBcc(){
        return bcc;
    }
    
    /**
     * Set un tableau de Bcc
     * @param bcc 
     */
    public void setBcc(String[] bcc){
        this.bcc = bcc;
    }
    
    /**
     * Retourne le sujet de l'email
     * @return 
     */
    public String getSubject(){
        return subject;
    }
    
    /**
     * Set un sujet à l'email
     * @param subject 
     */
    public void setSubject(String subject){
        this.subject = subject;
    }
    
    /**
     * Retourne le corps de l'email
     * @return 
     */
    public String getBody(){
        return body;
    }
    
    /**
     * Set le corps de l'email
     * @param body 
     */
    public void setBody(String body){
        this.body = body;
    }
}

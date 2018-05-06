/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heigvd.res.fee.pkg2018.labo.pkg03.model.mail;

/**
 *
 * @author migue
 */
public class Message {
    private String from = "";
    private String[] to = new String[0];
    private String[] cc = new String[0];
    private String[] bcc = new String[0];
    private String subject = "";
    private String body = "";
    
    public String getFrom(){
        return from;
    }
    
    public void setFrom(String from){
        this.from = from;
    }
    
    public String[] getTo(){
        return to;
    }
    
    public void setTo(String[] to){
        this.to = to;
    }
    
    public String[] getCc(){
        return cc;
    }
    
    public void setCc(String[] cc){
        this.cc = cc;
    }
    
    public String[] getBcc(){
        return bcc;
    }
    
    public void setBcc(String[] bcc){
        this.bcc = bcc;
    }
    
    public String getSubject(){
        return subject;
    }
    
    public void setSubject(String subject){
        this.subject = subject;
    }
    
    public String getBody(){
        return body;
    }
    
    public void setBody(String body){
        this.body = body;
    }
}

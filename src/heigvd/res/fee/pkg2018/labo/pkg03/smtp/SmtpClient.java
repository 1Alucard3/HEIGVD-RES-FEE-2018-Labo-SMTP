/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heigvd.res.fee.pkg2018.labo.pkg03.smtp;

//import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Message;
import heigvd.res.fee.pkg2018.labo.pkg03.smtp.ProtocoleSMTP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */

public class SmtpClient implements ISmtpClient{
    
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
    
    private String smtpServerAddress;
    private int smtpServerPort;
    
    private Socket socket;
    private PrintWriter pw;
    private BufferedReader br;
    //private OutputStream os;
    //private InputStream is;
    private boolean autoFlush = true;
    
    public SmtpClient(String smtpServerAddress, int smtpServerPort){
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = smtpServerPort;
    }
    
    @Override
    public void connect(){
        try {
            socket = new Socket(smtpServerAddress, smtpServerPort);
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            //os = socket.getOutputStream();
            //is = socket.getInputStream();
            
            String line = br.readLine();
            //String line = is.re
            LOG.info(line);
            
            if(!line.startsWith("220 ")){
            //if(!line.startsWith(ProtocoleSMTP.REP_SERVER_250)){
                throw new IOException("SMTP error: " + line);
            }
            
            pw.println(ProtocoleSMTP.CMD_HELLO);
            pw.flush();
            
            //line = br.readLine();
            //LOG.info(line);
            
            while(!line.startsWith(ProtocoleSMTP.REP_SERVER_END_OK)){
                
                line = br.readLine();
                LOG.info(line);
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(SmtpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void disconnect() {
        try {
            String line;
            pw.println(ProtocoleSMTP.CMD_QUIT);
            line = br.readLine();
            LOG.info(line);
            pw.flush();
            br.close();
            pw.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(SmtpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(Message message) throws IOException {
        String line;
      
        pw.print(ProtocoleSMTP.CMD_MAIL_FROM);
        pw.println(message.getFrom());
        //pw.println();
        pw.flush();
        line = br.readLine();
        LOG.info(line);
        
        for(String to : message.getTo()){
            pw.print(ProtocoleSMTP.CMD_RCPT_TO);
            pw.println(to);
            //pw.println();
            pw.flush();
            line = br.readLine();
            LOG.info(line);
        }
        
        for(String to : message.getCc()){
            pw.print(ProtocoleSMTP.CMD_RCPT_TO);
            pw.println(to);
            //pw.println();
            pw.flush();
            line = br.readLine();
            LOG.info(line);
        }
        
        for(String to : message.getBcc()){
            pw.print(ProtocoleSMTP.CMD_RCPT_TO);
            pw.println(to);
            //pw.println();
            pw.flush();
            line = br.readLine();
            LOG.info(line);
        }
        
        pw.println(ProtocoleSMTP.CMD_DATA);
        //pw.println();
        pw.flush();
        line = br.readLine();
        LOG.info(line);
        pw.println("Content-Type: text/plain; charset=\"utf-8\"");
        pw.println(ProtocoleSMTP.MESSAGE_FROM + message.getFrom());
        
        pw.print(ProtocoleSMTP.MESSAGE_TO + message.getTo()[0]);
        for(int i = 1; i < message.getTo().length; ++i){
            pw.print(", " + message.getTo()[i]);
        }
        pw.println();
        
        pw.print(ProtocoleSMTP.MESSAGE_CC + message.getCc()[0]);
        for(int i = 1; i < message.getCc().length; ++i){
            pw.print(", " + message.getCc()[i]);
        }
        pw.println();
        
        if(message.getBcc().length > 0){
            pw.print(ProtocoleSMTP.MESSAGE_BCC + message.getBcc()[0]);
            for(int i = 1; i < message.getBcc().length; ++i){
                pw.print(", " + message.getBcc()[i]);
            }
            pw.println();
        }
        
        if(!message.getSubject().equals("")){
            pw.println(ProtocoleSMTP.MESSAGE_SUBJECT + message.getSubject());
            pw.println();
        }
        
        pw.flush();
        
        LOG.info(message.getBody());
        pw.println(message.getBody());
        pw.println();
        pw.println(".");
        //pw.write(ProtocoleSMTP.LINE_FEED_AND_CARRIAGE_RETURN);
        pw.flush();
        line = br.readLine();
        LOG.info(line);
        
    }

    
}

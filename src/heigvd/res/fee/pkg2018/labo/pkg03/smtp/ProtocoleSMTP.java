/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heigvd.res.fee.pkg2018.labo.pkg03.smtp;

/**
 *
 * @author migue
 */
public class ProtocoleSMTP {
    public final static String CMD_HELLO = "EHLO Cyel";
    public final static String CMD_MAIL_FROM = "MAIL FROM: ";
    public final static String CMD_RCPT_TO = "RCPT TO: ";
    public final static String CMD_DATA = "DATA";
    public final static String CMD_RSET = "RSET";
    public final static String CMD_QUIT = "QUIT";
    public final static String CMD_VRFY = "VRFY";
    
    public final static String REP_SERVER_250 = "250";
    public final static String REP_SERVER_NOT_END_OK = "250-";
    public final static String REP_SERVER_END_OK = "250 ";
    
    public final static String LINE_FEED_AND_CARRIAGE_RETURN = "\n\r";
    
    public final static String MESSAGE_FROM = "From: ";
    public final static String MESSAGE_TO = "To: ";
    public final static String MESSAGE_CC = "Cc: ";
    public final static String MESSAGE_BCC = "Bcc: ";
    public final static String MESSAGE_SUBJECT = "Subject: ";
}

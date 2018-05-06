/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heigvd.res.fee.pkg2018.labo.pkg03.smtp;

import heigvd.res.fee.pkg2018.labo.pkg03.model.mail.Message;
import java.io.IOException;

/**
 *
 * @author migue
 */
public interface ISmtpClient {
    public void connect();
    public void disconnect();
    public void sendMessage(Message message) throws IOException;
}

package com.notification.notificationservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

@Service
public class EmailService {

    @Value("${sender.email}")
    private String senderEmail;

    @Value("${sender.password}")
    private String senderPassword;

    public void sendEmail(String senderEmail, String recipientEmail, String subject, String body) {
        System.out.println("Listner received: " + body + " 😀");
        // Set up mail server properties
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        // Create a mail session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            MimeMessage msg = new MimeMessage(session);
            // Set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            // Set sender address
            msg.setFrom(new InternetAddress(senderEmail));

            // Set recipient address
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

            // Set email subject
            msg.setSubject(subject, "UTF-8");

            // Set email body
            msg.setText(body , "UTF-8");

            // Set sent date
            msg.setSentDate(new Date());

            // Print message status
            System.out.println("Message is ready");

            // Send the email
            Transport.send(msg);

            // Print confirmation
            System.out.println("Email Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

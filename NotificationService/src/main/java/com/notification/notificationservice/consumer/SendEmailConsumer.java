package com.notification.notificationservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.notificationservice.dto.SendEmailDto;
import com.notification.notificationservice.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import java.util.Properties;

@Service
public class SendEmailConsumer {
    private ObjectMapper objectMapper;
    private EmailService emailService;

    public SendEmailConsumer(ObjectMapper objectMapper, EmailService emailService) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    //This method should be called if we receive an event for sending an email(Sigup)
    //This method/consumer should register itself to the singUp topic.
    @KafkaListener(topics = "singUp", groupId = "emailService")
    public void handleSignUpEvent(String message) {
        //We are getting String message.
        //Convert this string message to object using ObjectMapper
        try {
            SendEmailDto sendEmailDto = objectMapper.readValue(message, SendEmailDto.class);
            emailService.sendEmail(sendEmailDto.getFrom(), sendEmailDto.getTo(), sendEmailDto.getSubject(), sendEmailDto.getBody());
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
}
package com.cb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Async
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("vlad_wolf338@mail.ru");
        try {
            mailSender.send(message);
            System.out.println("Письмо успешно отправлено");
        } catch (Exception e) {
            System.out.println("Ошибка при отправке письма: " + e.getMessage());
        }
    }
}

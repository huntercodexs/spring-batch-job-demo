package com.huntercodexs.demojobs.jobs.enrollmentValidation.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class MailHandler {

    @Autowired
    private final JavaMailSender javaMailSender;

    public MailHandler(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String to, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            javaMailSender.send(message);
        } catch (MailSendException me) {
            System.out.println(me.getMessage());
        }
    }

    public void attached(String to, String subject, String content) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
            javaMailSender.send(message);
        } catch (MessagingException me) {
            System.out.println(me.getMessage());
        }
    }

    public String subject(String stamp, String info, String username) {
        return "["+stamp+"] " + info + " " + username;
    }

    public String content(String username, String message) {
        String body = "<html><head></head><body>";
        body += "<p>Username: " + username + "</p>";
        body += "<p>It's just a test, please don't consider this email</p>";
        body += "<p>Message: " + message + "</p>";
        body += "</body></html>";
        return body;
    }

}

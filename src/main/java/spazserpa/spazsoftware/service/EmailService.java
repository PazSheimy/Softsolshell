package spazserpa.spazsoftware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.io.File;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("infotjournal@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            logger.info("Email sent to {} with subject: {}", to, subject);
        } catch (Exception e) {
            logger.error("Error occurred while sending email to {}: {}", to, e.getMessage());
        }
    }

    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();

            // true indicates multi-part message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("infotjournal@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            File file = new File(pathToAttachment);
            helper.addAttachment(file.getName(), file);

            emailSender.send(message);

            logger.info("Email with attachment sent to {} with subject: {}", to, subject);
        } catch (MessagingException e) {
            logger.error("Error occurred while sending email with attachment to {}: {}", to, e.getMessage());
        }
    }

}

package com.example.quartzdemo.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.example.quartzdemo.config.MailConfig;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Component
public class EmailJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(EmailJob.class);

    @Autowired
    private JavaMailSender mailSender;

//    @Autowired
//    private MailProperties mailProperties;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());

        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String subject = jobDataMap.getString("subject");
        String body = jobDataMap.getString("body");
        String recipientEmail = jobDataMap.getString("email");

        sendMail("kaoutarZaia@gmail.com", recipientEmail, subject, body);
    }

    private void sendMail(String fromEmail, String toEmail, String subject, String body) {
               logger.info("Sending Email to {}", toEmail);
            
//            Properties properties = new Properties();
//            properties.setProperty("mail.transport.protocol", "smtp");
//            properties.setProperty("mail.smtp.auth", "false");
//            properties.setProperty("mail.smtp.starttls.enable", "false");
//            properties.setProperty("mail.debug", "false");
//            properties.setProperty("mail.smtp.host", "smtp.gmail.com");
//            properties.setProperty("mail.smtp.port", "587");
//            
//            Session session = Session.getDefaultInstance(properties);
            SimpleMailMessage message = new SimpleMailMessage();
            
            message.setSubject(subject);
            message.setText(body);
            message.setFrom(fromEmail);
            message.setTo(toEmail);

            mailSender.send(message);
        
    }
}

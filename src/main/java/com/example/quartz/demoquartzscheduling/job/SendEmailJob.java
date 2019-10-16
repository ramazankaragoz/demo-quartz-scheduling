package com.example.quartz.demoquartzscheduling.job;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.nio.charset.StandardCharsets;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author Ramazan Karagöz
 * @date 10/15/2019
 */
@Component
public class SendEmailJob extends QuartzJobBean {

  private static final Logger logger = LoggerFactory.getLogger(SendEmailJob.class);

  private final JavaMailSender mailSender;

  private final MailProperties mailProperties;

  public SendEmailJob(JavaMailSender mailSender, MailProperties mailProperties) {
    this.mailSender = mailSender;
    this.mailProperties = mailProperties;
  }

  @Override
  protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());

    JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
    String subject = jobDataMap.getString("subject");
    String body = jobDataMap.getString("body");
    String recipientEmail = jobDataMap.getString("email");

    sendMail(mailProperties.getUsername(), recipientEmail, subject, body);
  }

  private void sendMail(String fromEmail, String toEmail, String subject, String body) {
    try {
      logger.info("Sending Email to {}", toEmail);
      MimeMessage message = mailSender.createMimeMessage();

      MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
      messageHelper.setSubject(subject);
      messageHelper.setText(body, true);
      messageHelper.setFrom(fromEmail);
      messageHelper.setTo(toEmail);

      mailSender.send(message);
    } catch (MessagingException ex) {
      logger.error("Failed to send email to {}", toEmail);
    }
  }

  }


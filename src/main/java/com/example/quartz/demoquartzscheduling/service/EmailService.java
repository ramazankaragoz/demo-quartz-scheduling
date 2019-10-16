package com.example.quartz.demoquartzscheduling.service;

import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

import com.example.quartz.demoquartzscheduling.dto.EmailResponseDTO;
import com.example.quartz.demoquartzscheduling.dto.SendEmailDTO;
import com.example.quartz.demoquartzscheduling.job.SendEmailJob;
import com.example.quartz.demoquartzscheduling.util.JobQuartzBuilder;

/**
 * @author Ramazan Karagöz
 * @date 10/16/2019
 */
@Service
@Slf4j
public class EmailService implements IEmailService {

  private final Scheduler scheduler;

  public EmailService(Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  @Override
  public EmailResponseDTO sendEmailByDefinedTime(SendEmailDTO sendEmailDTO) {
    try {

      ZonedDateTime dateTime = ZonedDateTime.of(sendEmailDTO.getDateTime(), sendEmailDTO.getTimeZone());

      if (dateTime.isBefore(ZonedDateTime.now())) {
        return new EmailResponseDTO(false,
            "dateTime değeri şuanki zaman değerinden büyük olmalıdır.");
      }

      JobDataMap jobDataMap = new JobDataMap();

      jobDataMap.put("email", sendEmailDTO.getEmail());
      jobDataMap.put("subject", sendEmailDTO.getSubject());
      jobDataMap.put("body", sendEmailDTO.getBody());

      JobDetail jobDetail = JobQuartzBuilder.build().buildJobDetail(jobDataMap, "email-jobs", "Send Email Job", SendEmailJob.class);
      Trigger trigger = JobQuartzBuilder.build().buildJobTriggerToStartedDate(jobDetail, dateTime, "email-triggers", "Send Email Trigger");
      scheduler.scheduleJob(jobDetail, trigger);

      return new EmailResponseDTO(true,
          jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Email planlaması başarılı!");


    } catch (SchedulerException ex) {
      log.error("Hata scheduling email", ex);

      return new EmailResponseDTO(false,
          "Hata scheduling email. Lütfen sonra tekrar deneyiniz!");
    }
  }
}

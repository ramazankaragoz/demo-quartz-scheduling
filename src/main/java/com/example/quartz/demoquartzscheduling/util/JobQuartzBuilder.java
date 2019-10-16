package com.example.quartz.demoquartzscheduling.util;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;


/**
 * @author Ramazan Karagöz
 * @date 10/15/2019
 */

public abstract class JobQuartzBuilder {


  private JobQuartzBuilder() {

  }


  public static JobBuild build() {

    return new JobBuild();
  }


  public static class JobBuild {

    public JobDetail buildJobDetail(final JobDataMap jobDataMap, final String group, final String description, final Class clazz) {

      return org.quartz.JobBuilder.newJob(clazz)
          .withIdentity(UUID.randomUUID().toString(), group)
          .withDescription(description)
          .usingJobData(jobDataMap)
          .storeDurably()
          .build();
    }

    public Trigger buildJobTriggerToStartedDate(JobDetail jobDetail, ZonedDateTime startAt, final String group, final String description) {
      return TriggerBuilder.newTrigger()
          .forJob(jobDetail)
          .withIdentity(jobDetail.getKey().getName(), group)
          .withDescription(description)
          .startAt(Date.from(startAt.toInstant()))
          .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
          .build();
    }

    public Trigger buildJobTriggerToRepeatSecondlyForever(JobDetail jobDetail, ZonedDateTime startAt, final int seconds, final String group,
        final String description) {
      return TriggerBuilder.newTrigger()
          .forJob(jobDetail)
          .withIdentity(jobDetail.getKey().getName(), group)
          .withDescription(description)
          .startAt(Date.from(startAt.toInstant()))
          .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(seconds))
          .build();
    }

    public Trigger buildJobTriggerToWeeklyOnDayAndHourAndMinute(JobDetail jobDetail, ZonedDateTime startAt, final int dayOfWeek, final int hour,
        final int minute, final String group, final String description) {
      return TriggerBuilder.newTrigger()
          .forJob(jobDetail)
          .withIdentity(jobDetail.getKey().getName(), group)
          .withDescription(description)
          .startAt(Date.from(startAt.toInstant()))
          .withSchedule(CronScheduleBuilder.weeklyOnDayAndHourAndMinute(dayOfWeek, hour, minute))
          .build();
    }

  }

}

package com.huntercodexs.demojobs.jobs.enrollmentValidation.scheduler;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.config.EnrollmentValidationConfigQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnrollmentValidationScheduler {

    @Bean
    public JobDetail enrollmentValidationJobDetail() {

        System.out.println("[SCHEDULER][JOB-DETAIL] >>> enrollmentValidationJobDetail");

        return JobBuilder
            .newJob(EnrollmentValidationConfigQuartz.class)
            .storeDurably()
            .build();
    }

    @Bean
    public Trigger enrollmentValidationTrigger() {

        System.out.println("[SCHEDULER][TRIGGER] >>> enrollmentValidationTrigger");

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
            .dailyAtHourAndMinute(23, 59);

        return TriggerBuilder
            .newTrigger()
            .forJob(enrollmentValidationJobDetail())
            .withSchedule(cronScheduleBuilder)
            .build();
    }
}

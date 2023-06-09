package com.huntercodexs.demojobs.jobs.enrollmentValidation.scheduler;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.config.EnrollmentValidationQuartzConfig;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class EnrollmentValidationScheduler {

    @Value("${job.enrollment-validation.enabled:true}")
    boolean jobEnabled;

    @Value("${job.enrollment-validation.hour:0}")
    int jobHour;

    @Value("${job.enrollment-validation.minute:0}")
    int jobMinute;

    @Bean
    public JobDetail enrollmentValidationJobDetail() {

        if (!jobEnabled) {
            log.warn("Schedule say: (JobDetail) enrollmentValidationJob is disabled by properties");
            return null;
        }

        return JobBuilder
                .newJob(EnrollmentValidationQuartzConfig.class)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger enrollmentValidationTrigger() {

        if (!jobEnabled) {
            log.warn("Schedule say: (Trigger) enrollmentValidationJob is disabled by properties");
            return null;
        }

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
            .dailyAtHourAndMinute(jobHour, jobMinute);

        log.info("Schedule say: (Trigger) enrollmentValidationJob is starting");
        log.info("Schedule say: Cron Settings: " + cronScheduleBuilder);

        return TriggerBuilder
            .newTrigger()
            .forJob(enrollmentValidationJobDetail())
            .withSchedule(cronScheduleBuilder)
            .build();
    }
}

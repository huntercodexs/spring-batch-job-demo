package com.huntercodexs.demojobs.jobs.enrollmentValidation.scheduler;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.config.EnrollmentValidationQuartzConfig;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        if (!jobEnabled) return null;

        System.out.println("[SCHEDULER][JOB-DETAIL] >>> enrollmentValidationJobDetail");

        return JobBuilder
                .newJob(EnrollmentValidationQuartzConfig.class)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger enrollmentValidationTrigger() {
        if (!jobEnabled) return null;

        System.out.println("[DEBUG] [SCHEDULER][TRIGGER] >>> enrollmentValidationTrigger");

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
            .dailyAtHourAndMinute(jobHour, jobMinute);

        return TriggerBuilder
            .newTrigger()
            .forJob(enrollmentValidationJobDetail())
            .withSchedule(cronScheduleBuilder)
            .build();
    }
}

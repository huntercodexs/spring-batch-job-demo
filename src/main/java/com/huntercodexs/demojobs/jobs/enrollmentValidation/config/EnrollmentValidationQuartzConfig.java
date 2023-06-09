package com.huntercodexs.demojobs.jobs.enrollmentValidation.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

@Slf4j
@DisallowConcurrentExecution
public class EnrollmentValidationQuartzConfig extends QuartzJobBean {

    @Value("${job.enrollment-validation.enabled:true}")
    boolean jobEnabled;

    @Autowired
    @Qualifier("enrollmentValidationJob")
    private Job job;
    @Autowired
    private JobExplorer jobExplorer;
    @Autowired
    private JobLauncher jobLauncher;

    @Override
    protected void executeInternal(JobExecutionContext context) {

        if (!jobEnabled) {
            log.warn("Quartz Config say: (executeInternal) enrollmentValidationJob is disabled by properties");
            return;
        }

        JobParameters jobParameters = new JobParametersBuilder(this.jobExplorer)
            .addDate("date", new Date())
            .getNextJobParameters(this.job)
            .toJobParameters();

        try {
            this.jobLauncher.run(this.job, jobParameters);

            log.info("Quartz Config say: (executeInternal) enrollmentValidationJob is starting");
            log.info("Quartz Config say: (executeInternal) jobParameters: " + jobParameters);

        } catch (Exception e) {
            log.error("Quartz Config say: (executeInternal) exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package com.huntercodexs.demojobs.jobs.enrollmentValidation.config;

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
        if (!jobEnabled) return;

        System.out.println("[DEBUG] [QUARTZ] >>> executeInternal");

        JobParameters jobParameters = new JobParametersBuilder(this.jobExplorer)
            .addDate("date", new Date())
            .getNextJobParameters(this.job)
            .toJobParameters();

        try {
            this.jobLauncher.run(this.job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

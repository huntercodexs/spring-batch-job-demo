package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.mail.MailHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ReportFirstWriter implements ItemWriter<EnrollmentValidationDto> {

    private final String USERNAME = "John Smith";
    private final String STAMP = "ENROLLMENT VALIDATION";
    private final String INFO = "testing";
    private final String MAILTO = "jereelton-devel@email.com";
    private String mailMessage = "<h3>Processed Products</h3>";
    private String mailContent;

    @Autowired
    MailHandler mailHandler;

    @Override
    public void write(List<? extends EnrollmentValidationDto> enrollmentValidationDto) {

        /*Prevent send mail when not exists items*/
        if (enrollmentValidationDto.size() == 0) {
            log.info("ReportFirstWriter say: (write) not exists items to send mail");
            return;
        }

        enrollmentValidationDto.forEach(enrollmentItem -> {
            message(enrollmentItem);
        });

        this.mailContent = mailHandler.content(USERNAME, this.mailMessage);
        send();

    }

    private void message(EnrollmentValidationDto enrollmentItem) {
        this.mailMessage += "<p>"+enrollmentItem.toString()+"</p>";
    }

    private void send() {
        String subject = mailHandler.subject(STAMP, INFO, USERNAME);
        mailHandler.attached(MAILTO, subject, this.mailContent);
    }
}

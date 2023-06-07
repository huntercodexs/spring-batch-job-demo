package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.mail.MailHandler;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportSecondWriter implements ItemWriter<String> {

    private final String USERNAME = "John Smith";
    private final String STAMP = "ENROLLMENT VALIDATION";
    private final String INFO = "download";
    private final String MAILTO = "jereelton-devel@email.com";
    private String mailMessage = "<h3>Download Files from Sftp</h3>";
    private String mailContent;

    @Autowired
    MailHandler mailHandler;

    @Override
    public void write(List<? extends String> list) {

        list.forEach(item -> {
            message(item);
        });

        this.mailContent = mailHandler.content(USERNAME, this.mailMessage);
        send();

    }

    private void message(String list) {
        this.mailMessage += "<p>"+list+"</p>";
    }

    private void send() {
        String subject = mailHandler.subject(STAMP, INFO, USERNAME);
        mailHandler.attached(MAILTO, subject, this.mailContent);
    }
}

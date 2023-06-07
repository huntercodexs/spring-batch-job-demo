package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportSecondWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> list) {

        System.out.println("456XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println(list);
        System.out.println(list.size());
        System.out.println("456XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

    }
}

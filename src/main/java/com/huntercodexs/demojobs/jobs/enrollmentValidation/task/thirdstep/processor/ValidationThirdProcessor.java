package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ValidationThirdProcessor implements ItemProcessor<String,String> {

    public String process(String list) throws Exception {

        if (list.contains(".download")) {
            return list;
        }

        return null;
    }
}

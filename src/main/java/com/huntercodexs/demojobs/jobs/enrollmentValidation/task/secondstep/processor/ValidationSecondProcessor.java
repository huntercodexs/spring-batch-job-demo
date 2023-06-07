package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ValidationSecondProcessor implements ItemProcessor<String, String> {

    public String process(String list) throws Exception {

        System.out.println("[VALIDATION-PROCESSOR] >>> process");
        System.out.println(list);

        if (list.contains(".txt.response")) {
            System.out.println(">>> "+list);
            return list;
        }

        return null;
    }
}

package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ValidationThirdProcessor implements ItemProcessor<String,String> {

    public String process(String list) throws Exception {

        System.out.println("[VALIDATOR-THIRD-PROCESSOR] >>> process");
        System.out.println(list);

        if (list.contains(".download")) {
            System.out.println(" >>> " + list);
            return list;
        }

        return null;
    }
}

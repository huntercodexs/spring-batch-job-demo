package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidationThirdProcessor implements ItemProcessor<String,String> {

    public String process(String list) throws Exception {

        log.info("ValidationThirdProcessor say: (process) process is starting");
        log.info("ValidationThirdProcessor say: (process) list: " + list);

        if (list.contains(".download")) {
            return list;
        }

        return null;
    }
}

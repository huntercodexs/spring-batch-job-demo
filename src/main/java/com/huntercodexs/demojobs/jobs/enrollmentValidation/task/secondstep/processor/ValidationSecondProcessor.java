package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidationSecondProcessor implements ItemProcessor<String, String> {

    @Value("${sftp.extension-files-download}")
    String expectedFileExtension;

    public String process(String list) throws Exception {

        log.info("ValidationSecondProcessor say: (process) process is starting");
        log.info("ValidationSecondProcessor say: (process) list: " + list);

        if (list.contains(expectedFileExtension)) {
            return list;
        }

        return null;
    }
}

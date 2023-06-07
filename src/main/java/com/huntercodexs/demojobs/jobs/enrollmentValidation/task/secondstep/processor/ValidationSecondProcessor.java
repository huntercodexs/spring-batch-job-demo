package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValidationSecondProcessor implements ItemProcessor<String, String> {

    @Value("${sftp.extension-files-download}")
    String expectedFileExtension;

    public String process(String list) throws Exception {

        if (list.contains(expectedFileExtension)) {
            return list;
        }

        return null;
    }
}

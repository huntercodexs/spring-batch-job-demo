package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValidationSecondProcessor implements ItemProcessor<String, String> {

    @Value("${sftp.extension-files-download}")
    String expectedFileExtension;

    public String process(String list) throws Exception {

        System.out.println("[VALIDATION-PROCESSOR] >>> process");
        System.out.println(list);

        if (list.contains(expectedFileExtension)) {
            System.out.println(">>> "+list);
            return list;
        }

        return null;
    }
}

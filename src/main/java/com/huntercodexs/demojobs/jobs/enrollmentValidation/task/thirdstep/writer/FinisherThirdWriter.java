package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class FinisherThirdWriter implements ItemWriter<String> {

    @Value("${sftp.localfolder-receive-path}")
    String localFilepathDownload;

    @Value("${sftp.localfolder-finished-path}")
    String localFilepathFinished;

    @Override
    public void write(List<? extends String> list) throws IOException {

        try {
            makeFileFinished(list);
        } catch (RuntimeException re) {
            log.error("FinisherThirdWriter say: (write) exception: " + re.getMessage());
        }

    }

    private void makeFileFinished(List<? extends String> list) {

        list.forEach(item -> {

            File oldName = new File(sanitizePath(localFilepathDownload) + item);
            File newName = new File(sanitizePath(localFilepathFinished) + item +".finished");

            if (oldName.renameTo(newName)) {
                log.info("FinisherThirdWriter say: (makeFileFinished) File "+ oldName +" renamed successful to " + newName);
            } else {
                log.info("FinisherThirdWriter say: (makeFileFinished) File "+ oldName +" NOT renamed to " + newName);
            }

        });

    }

    private String sanitizePath(String path) {
        return path.replaceAll("/$", "") + "/";
    }
}

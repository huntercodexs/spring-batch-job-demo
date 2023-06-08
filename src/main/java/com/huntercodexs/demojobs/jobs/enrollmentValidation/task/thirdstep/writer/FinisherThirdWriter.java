package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class FinisherThirdWriter implements ItemWriter<String> {

    @Value("${sftp.localfolder-receive-path}")
    String localFilepathDownload;

    @Value("${sftp.localfolder-finished-path}")
    String localFilepathFinished;

    @Override
    public void write(List<? extends String> list) throws IOException {

        try {

            System.out.println("[WRITE] write !!!!");
            System.out.println(list);

            makeFileFinished(list);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }

    }

    private void makeFileFinished(List<? extends String> list) {

        list.forEach(item -> {

            System.out.println("[WRITE] makeFileFinished !!!!");
            System.out.println(item);

            String[] array = item.split("\\.");
            String name = array[0];
            String extension = array[1];

            File oldName = new File(sanitizePath(localFilepathDownload) + name);
            File newName = new File(sanitizePath(localFilepathFinished) + name +"-."+ extension +".finished");

            if (oldName.renameTo(newName)) {
                System.out.println("File "+ oldName +" renamed successful to " + newName);
            } else {
                System.out.println("File "+ oldName +" NOT renamed to " + newName);
            }

        });

    }

    private String sanitizePath(String path) {
        return path.replaceAll("/$", "") + "/";
    }
}

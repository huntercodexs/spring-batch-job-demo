package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.sftp.SftpHandler;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DownloadFileFromSftpSecondWriter implements ItemWriter<String> {

    @Value("${sftp.folder-download-path}")
    String sftpDownloadPath;

    @Autowired
    SftpHandler sftpHandler;

    @Override
    public void write(List<? extends String> list) {

        System.out.println("678XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println(list);
        System.out.println(list.size());
        System.out.println("678XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        list.forEach(filename -> {
            try {
                sftpHandler.download(filename);
                sftpHandler.delete(sanitizePath(sftpDownloadPath)+filename);
            } catch (RuntimeException | IOException re) {
                System.out.println(re.getMessage());
            }
        });

    }

    private String sanitizePath(String path) {
        return path.replaceAll("/$", "") + "/";
    }

}

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

        downloadFiles(list);
        deleteFiles(list);

    }

    private void downloadFiles(List<? extends String> list) {
        list.forEach(filename -> {
            try {
                sftpHandler.download(filename);
                System.out.println("File downloaded: " + filename);
            } catch (RuntimeException | IOException re) {
                System.out.println("SFTP Download file error: " + re.getMessage());
            }
        });
    }

    private void deleteFiles(List<? extends String> list) {
        list.forEach(filename -> {
            try {
                sftpHandler.delete(sanitizePath(sftpDownloadPath)+filename);
                System.out.println("File deleted: " + filename);
            } catch (RuntimeException | IOException re) {
                System.out.println("SFTP Delete file error: " + re.getMessage());
            }
        });
    }

    private String sanitizePath(String path) {
        return path.replaceAll("/$", "") + "/";
    }

}

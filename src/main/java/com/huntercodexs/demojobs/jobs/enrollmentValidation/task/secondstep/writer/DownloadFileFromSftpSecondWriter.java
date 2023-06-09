package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.sftp.SftpHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class DownloadFileFromSftpSecondWriter implements ItemWriter<String> {

    @Value("${sftp.folder-download-path}")
    String sftpDownloadPath;

    @Autowired
    SftpHandler sftpHandler;

    @Override
    public void write(List<? extends String> list) {

        log.info("DownloadFileFromSftpSecondWriter say: (write) starting");

        downloadFiles(list);
        deleteFiles(list);

    }

    private void downloadFiles(List<? extends String> list) {
        list.forEach(filename -> {
            try {
                sftpHandler.download(filename);
                log.info("DownloadFileFromSftpSecondWriter say: (downloadFiles) File downloaded: " + filename);
            } catch (RuntimeException | IOException re) {
                log.error("DownloadFileFromSftpSecondWriter say: (downloadFiles) SFTP Download file error: " + re.getMessage());
            }
        });
    }

    private void deleteFiles(List<? extends String> list) {
        list.forEach(filename -> {
            try {
                sftpHandler.delete(sanitizePath(sftpDownloadPath)+filename);
                log.info("DownloadFileFromSftpSecondWriter say: (deleteFiles) File deleted: " + filename);
            } catch (RuntimeException | IOException re) {
                log.error("DownloadFileFromSftpSecondWriter say: (deleteFiles) SFTP Delete file error: " + re.getMessage());
            }
        });
    }

    private String sanitizePath(String path) {
        return path.replaceAll("/$", "") + "/";
    }

}

package com.huntercodexs.demojobs.jobs.enrollmentValidation.sftp;

import com.jcraft.jsch.ChannelSftp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class SftpHandler {

    @Value("${sftp.server.address}")
    String sftpHost;

    @Value("${sftp.server.port}")
    int sftpServerPort;

    @Value("${sftp.username}")
    String sftpUsername;

    @Value("${sftp.password}")
    String sftpPassword;

    @Value("${sftp.folder-upload-path}")
    String sftpUploadPath;

    @Value("${sftp.folder-download-path}")
    String sftpDownloadPath;

    @Value("${sftp.localfolder-receive-path}")
    String sftpLocalFolderPath;

    @Value("${sftp.extension-files-download:}")
    String sftpExtensionFiles;

    @Value("${sftp.allow.unknown-hosts}")
    boolean sftpAllowUnknownHosts;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("uuuuMMddHHmmss");

    private String sanitizePath(String path) {
        return path.replaceAll("/$", "") + "/";
    }

    private String createDownloadName(String filename) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        String dateTimeFormat = dateTimeNow.format(FORMATTER);
        return sftpLocalFolderPath.
                replaceAll("/$", "") + "/" +
                filename.split("\\.")[0] + "-" + dateTimeFormat + ".download";
    }

    private String definePath(String path) {
        if (path == null) {
            path = sanitizePath(sftpDownloadPath) + "/";
        } else {
            path = sanitizePath(path);
        }

        if (!sftpExtensionFiles.equals("")) {
            path = sanitizePath(path) + "/*" + sftpExtensionFiles;
        }

        log.info("SftpHandler say: (definedPath) A path was defined: " + path);

        return path;
    }

    private DefaultSftpSessionFactory sftpConnect() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();

        try {
            factory.setHost(sftpHost);
            factory.setPort(sftpServerPort);
            factory.setUser(sftpUsername);
            factory.setPassword(sftpPassword);
            factory.setAllowUnknownKeys(sftpAllowUnknownHosts);
            /*If exists the trusted file to "know_hosts"*/
            /*factory.setKnownHostsResource(new FileSystemResource("know_hosts"));*/
        } catch (RuntimeException re) {
            log.error("SftpHandler say: (sftpConnect) Exception when tried create an factory: " + re.getMessage());
            throw new RuntimeException(re.getMessage());
        }

        log.info("SftpHandler say: (sftpConnect) factory: " + factory);

        return factory;
    }

    private void ftpStore(InputStream inputStream, String filename) throws IOException {
        try {
            SftpSession session = sftpConnect().getSession();
            session.write(inputStream, sftpUploadPath
                    .replaceAll("/$", "")+"/"+filename);

            log.info("SftpHandler say: (ftpStore) Sftp Send file successfully: " + filename);

        } catch (RuntimeException re) {

            log.error("SftpHandler say: (ftpStore) Sftp Error to send file: " + filename);
            log.error("SftpHandler say: (ftpStore) message error: " + re.getMessage());

            throw new RuntimeException("Sftp Error " + re.getMessage());
        }
    }

    private void ftpRead(String filename) throws IOException {
        try {
            OutputStream os = new FileOutputStream(createDownloadName(filename));
            SftpSession session = sftpConnect().getSession();
            session.read(sftpDownloadPath.replaceAll("/$", "") + "/" + filename, os);

            log.info("SftpHandler say: (ftpRead) Sftp Download file successfully: " + filename);

        } catch (RuntimeException re) {

            log.error("SftpHandler say: (ftpRead) Sftp Error to receive file: " + filename);
            log.error("SftpHandler say: (ftpRead) message error: " + re.getMessage());

            throw new RuntimeException(re.getMessage());
        }
    }

    public ChannelSftp.LsEntry[] all(String path) throws IOException {
        try {
            SftpSession session = sftpConnect().getSession();
            return session.list(definePath(path));
        } catch (RuntimeException re) {

            log.error("SftpHandler say: (all) Sftp Error to list files: " + path);
            log.error("SftpHandler say: (all) message error: " + re.getMessage());

            throw new RuntimeException(re.getMessage());
        }
    }

    public String[] names(String path) throws IOException {
        try {
            SftpSession session = sftpConnect().getSession();
            return session.listNames(definePath(path));
        } catch (RuntimeException re) {

            log.error("SftpHandler say: (names) Sftp Error to list files name: " + path);
            log.error("SftpHandler say: (names) message error: " + re.getMessage());

            throw new RuntimeException(re.getMessage());
        }
    }

    public boolean delete(String filepath) throws IOException {
        try {
            SftpSession session = sftpConnect().getSession();
            return session.remove(filepath);
        } catch (RuntimeException re) {

            log.error("SftpHandler say: (delete) Sftp Error to remove file: " + filepath);
            log.error("SftpHandler say: (delete) message error: " + re.getMessage());

            throw new RuntimeException(re.getMessage());
        }
    }

    public void upload(InputStream inputStream, String filename) throws IOException {
        try {
            ftpStore(inputStream, filename);
            log.info("SftpHandler say: (upload) Upload file successful: " + filename);
        } catch (RuntimeException re) {

            log.error("SftpHandler say: (upload) Sftp Error to upload file: " + filename);
            log.error("SftpHandler say: (upload) message error: " + re.getMessage());

            throw new RuntimeException("uploadFile: Sftp Error " + re.getMessage());
        }
    }

    public void download(String filename) throws IOException {
        try {
            ftpRead(filename);
            log.info("SftpHandler say: (download) Download file successful: " + filename);
        } catch (RuntimeException re) {

            log.error("SftpHandler say: (download) Sftp Error to download file: " + filename);
            log.error("SftpHandler say: (download) message error: " + re.getMessage());

            throw new RuntimeException("downloadFile: Sftp Error " + re.getMessage());
        }
    }
}

package com.huntercodexs.demojobs.jobs.enrollmentValidation.sftp;

import com.jcraft.jsch.ChannelSftp;
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

    private String sanitizePath(String path) {
        return path.replaceAll("/$", "") + "/";
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("uuuuMMddHHmmss");

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

        return path;
    }

    private DefaultSftpSessionFactory sftpConnect() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
        factory.setHost(sftpHost);
        factory.setPort(sftpServerPort);
        factory.setUser(sftpUsername);
        factory.setPassword(sftpPassword);
        factory.setAllowUnknownKeys(sftpAllowUnknownHosts);
        /*If exists the trusted file to "know_hosts"*/
        /*factory.setKnownHostsResource(new FileSystemResource("know_hosts"));*/

        return factory;
    }

    private void ftpStore(InputStream inputStream, String fileName) throws IOException {
        try {
            SftpSession session = sftpConnect().getSession();
            session.write(inputStream, sftpUploadPath
                    .replaceAll("/$", "")+"/"+fileName);
            System.out.println("Sftp Send file successfully !");
        } catch (RuntimeException re) {
            System.out.println("Sftp Error to send file !");
            throw new RuntimeException("Sftp Error " + re.getMessage());
        }
    }

    private void ftpRead(String filename) throws IOException {
        try {
            OutputStream os = new FileOutputStream(createDownloadName(filename));
            SftpSession session = sftpConnect().getSession();
            session.read(sftpDownloadPath.replaceAll("/$", "") + "/" + filename, os);
            System.out.println("Sftp Download file successfully !");
        } catch (RuntimeException re) {
            System.out.println("Sftp Error to receive file !");
            throw new RuntimeException(re.getMessage());
        }
    }

    public ChannelSftp.LsEntry[] all(String path) throws IOException {
        try {
            SftpSession session = sftpConnect().getSession();
            return session.list(definePath(path));
        } catch (RuntimeException re) {
            System.out.println("Sftp Error to list files !");
            throw new RuntimeException(re.getMessage());
        }
    }

    public String[] names(String path) throws IOException {
        try {
            SftpSession session = sftpConnect().getSession();
            return session.listNames(definePath(path));
        } catch (RuntimeException re) {
            System.out.println("Sftp Error to list files name !");
            throw new RuntimeException(re.getMessage());
        }
    }

    public boolean delete(String filepath) throws IOException {
        try {
            SftpSession session = sftpConnect().getSession();
            return session.remove(filepath);
        } catch (RuntimeException re) {
            System.out.println("Sftp Error to remove file !");
            throw new RuntimeException(re.getMessage());
        }
    }

    public void upload(InputStream inputStream, String fileName) throws IOException {
        try {
            ftpStore(inputStream, fileName);
        } catch (RuntimeException re) {
            throw new RuntimeException("uploadFile: Sftp Error " + re.getMessage());
        }
    }

    public void download(String filename) throws IOException {
        try {
            ftpRead(filename);
        } catch (RuntimeException re) {
            throw new RuntimeException("downloadFile: Sftp Error " + re.getMessage());
        }
    }
}

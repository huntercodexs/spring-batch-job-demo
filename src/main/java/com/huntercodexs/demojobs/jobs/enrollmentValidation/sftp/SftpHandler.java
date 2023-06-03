package com.huntercodexs.demojobs.jobs.enrollmentValidation.sftp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

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

    @Value("${sftp.folder-path}")
    String sftpFolderPath;

    @Value("${sftp.allow.unknown-hosts}")
    boolean sftpAllowUnknownHosts;

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
            session.write(inputStream, sftpFolderPath
                    .replaceAll("/$", "")+"/"+fileName);
            System.out.println("Sftp Send file successfully !");
        } catch (RuntimeException re) {
            System.out.println("Sftp Error to send file !");
            throw new RuntimeException("Sftp Error " + re.getMessage());
        }
    }

    public void uploadFile(InputStream inputStream, String fileName) throws IOException {
        try {
            ftpStore(inputStream, fileName);
        } catch (RuntimeException re) {
            throw new RuntimeException("Sftp Error " + re.getMessage());
        }
    }
}

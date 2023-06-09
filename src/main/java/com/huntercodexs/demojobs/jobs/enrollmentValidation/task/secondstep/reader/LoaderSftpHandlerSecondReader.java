package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.reader;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.sftp.SftpHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class LoaderSftpHandlerSecondReader implements ItemReader<String> {

    private int nextFilenameIndex;
    private String[] filenames;
    public SftpHandler sftpHandler;

    LoaderSftpHandlerSecondReader(SftpHandler sftpHandler) throws IOException {
        this.sftpHandler = sftpHandler;
        nextFilenameIndex = 0;
    }

    @Override
    public String read() throws Exception {

        /*Prevent overload requests*/
        if (this.filenames == null) {
            this.filenames = this.sftpHandler.names(null);

            log.info("LoaderSftpHandlerSecondReader say: (read) filenames: " + Arrays.toString(filenames));
        }

        if (this.filenames.length == 0) {
            log.info("LoaderSftpHandlerSecondReader say: (read) There is not files to read yet");
            return null;
        }

        String nextItem = null;

        if (nextFilenameIndex < filenames.length) {
            nextItem = filenames[nextFilenameIndex];
            nextFilenameIndex++;
        } else {
            nextFilenameIndex = 0;
            this.filenames = null;
        }

        return nextItem;
    }
}







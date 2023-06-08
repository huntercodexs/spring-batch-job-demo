package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.reader;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.sftp.SftpHandler;
import org.springframework.batch.item.ItemReader;

import java.io.IOException;
import java.util.Arrays;

public class InMemorySecondReader implements ItemReader<String> {

    private int nextFilenameIndex;
    private String[] filenames;

    public SftpHandler sftpHandler;

    InMemorySecondReader(SftpHandler sftpHandler) throws IOException {
        this.sftpHandler = sftpHandler;
        nextFilenameIndex = 0;
    }

    @Override
    public String read() throws Exception {

        /*Prevent overload requests*/
        if (this.filenames == null) {
            this.filenames = this.sftpHandler.names(null);
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







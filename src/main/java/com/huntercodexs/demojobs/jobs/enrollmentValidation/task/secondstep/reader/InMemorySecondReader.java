package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.reader;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.sftp.SftpHandler;
import org.springframework.batch.item.ItemReader;

import java.io.IOException;

public class InMemorySecondReader implements ItemReader<String> {

    private int nextFilenameIndex;
    private final String[] filenames;

    InMemorySecondReader(SftpHandler sftpHandler) throws IOException {
        filenames = sftpHandler.names(null);
        nextFilenameIndex = 0;
    }

    @Override
    public String read() throws Exception {
        String nextItem = null;

        if (nextFilenameIndex < filenames.length) {
            nextItem = filenames[nextFilenameIndex];
            nextFilenameIndex++;
        } else {
            nextFilenameIndex = 0;
        }

        return nextItem;
    }
}







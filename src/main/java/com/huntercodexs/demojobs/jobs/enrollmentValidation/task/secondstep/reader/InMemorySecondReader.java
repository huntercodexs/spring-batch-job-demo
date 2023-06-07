package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.reader;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.sftp.SftpHandler;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

public class InMemorySecondReader implements ItemReader<String> {

    private int nextFilenameIndex;
    private String[] filenames;

    @Autowired
    SftpHandler sftpHandler;

    InMemorySecondReader() {
        initialize();
    }

    private void initialize() {
        filenames = new String[]{"file1.txt"};
        nextFilenameIndex = 0;
    }

    @Override
    public String read() throws Exception {
        String nextStudent = null;

        if (nextFilenameIndex < filenames.length) {
            nextStudent = filenames[nextFilenameIndex];
            nextFilenameIndex++;
        }
        else {
            nextFilenameIndex = 0;
        }

        return nextStudent;
    }
}







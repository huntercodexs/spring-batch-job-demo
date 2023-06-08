package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.reader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

@Service
public class FileHandlerThirdReader {

    @Value("${sftp.localfolder-receive-path}")
    String listFolderPath;

    public String[] list() {

        File file = new File(listFolderPath);
        String[] filenames = file.list();

        System.out.println(Arrays.toString(filenames));

        return filenames;
    }
}

package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

@Slf4j
@Service
public class FileHandlerThirdReader {

    @Value("${sftp.localfolder-receive-path}")
    String listFolderPath;

    public String[] list() {

        File file = new File(listFolderPath);
        String[] filenames = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".download");
            }
        });
        
        log.info("FileHandlerThirdReader say: (list) filenames " + Arrays.toString(filenames));

        return filenames;
    }
}

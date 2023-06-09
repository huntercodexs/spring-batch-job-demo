package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class LoaderFileHandlerThirdReader implements ItemReader<String> {

    private int nextFilenameIndex;
    public FileHandlerThirdReader fileHandlerThirdReader;
    private String[] filenames;

    LoaderFileHandlerThirdReader(FileHandlerThirdReader fileHandlerThirdReader) throws IOException {
        this.fileHandlerThirdReader = fileHandlerThirdReader;
        nextFilenameIndex = 0;
    }

    @Override
    public String read() throws Exception {

        /*Prevent overload requests*/
        if (this.filenames == null) {
            this.filenames = this.fileHandlerThirdReader.list();

            log.info("LoaderFileHandlerThirdReader say: (read) filenames: " + Arrays.toString(filenames));
        }

        if (this.filenames.length == 0) {
            log.info("LoaderFileHandlerThirdReader say: (read) There is not files to read yet");
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

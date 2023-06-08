package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.reader;

import org.springframework.batch.item.ItemReader;

import java.io.IOException;

public class InMemoryThirdReader implements ItemReader<String> {

    private int nextFilenameIndex;
    private final String[] filenames;

    InMemoryThirdReader(FileHandlerThirdReader fileHandlerThirdReader) throws IOException {

        System.out.println("[READER-THIRD-STEP] >>> InMemoryThirdReader");

        filenames = fileHandlerThirdReader.list();
        nextFilenameIndex = 0;
    }

    @Override
    public String read() throws Exception {

        System.out.println("[READER-THIRD-STEP] >>> InMemoryThirdReader read()");

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







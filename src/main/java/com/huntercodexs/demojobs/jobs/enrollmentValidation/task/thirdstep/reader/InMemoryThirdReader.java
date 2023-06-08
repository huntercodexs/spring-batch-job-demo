package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.reader;

import org.springframework.batch.item.ItemReader;

import java.io.IOException;

public class InMemoryThirdReader implements ItemReader<String> {

    private int nextFilenameIndex;

    public FileHandlerThirdReader fileHandlerThirdReader;
    private String[] filenames;

    InMemoryThirdReader(FileHandlerThirdReader fileHandlerThirdReader) throws IOException {

        System.out.println("[READER-THIRD-STEP] >>> InMemoryThirdReader Constructor");

        this.fileHandlerThirdReader = fileHandlerThirdReader;
        nextFilenameIndex = 0;
    }

    @Override
    public String read() throws Exception {

        if (this.filenames == null) {
            this.filenames = this.fileHandlerThirdReader.list();
        }

        System.out.println("[READER-THIRD-STEP] >>> InMemoryThirdReader read()");

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

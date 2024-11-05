package com.example.session36.components;

import java.util.List;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;

public class MyReader implements ItemReader<String>, StepExecutionListener {
    private JobParameters jobParameters;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // Access JobParameters from StepExecution
        this.jobParameters = stepExecution.getJobParameters();
    }

    @Override
    public String read() throws Exception {
        String inputFilePath = jobParameters.getString("inputFilePath");
        String currentDate = jobParameters.getString("currentDate");
        System.out.println("Processing file: " + inputFilePath);
        System.out.println("start time: " + currentDate);
        // Implement reading logic here
        return null;
    }
}

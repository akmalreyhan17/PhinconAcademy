package com.example.session36.controller;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job myJob;

    @GetMapping("/start-job")
    public String startJob() {
        try {
            
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("inputFilePath", "/path/to/file.csv") // Parameter 1
                    .addDate("currentDate", new Date()) // Parameter 2
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(myJob, jobParameters);
            
            System.out.println("Job Status: " + execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return "Job failed.";
        }
        return null;
    }
}

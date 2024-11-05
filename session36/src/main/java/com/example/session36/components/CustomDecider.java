package com.example.session36.components;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

public class CustomDecider implements JobExecutionDecider {
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        // Custom logic to decide the next step
        boolean condition = true; // Simulate a condition
        if (condition) {
            return new FlowExecutionStatus("PASS");
        } else {
            return new FlowExecutionStatus("FAIL");
        }
    }
}

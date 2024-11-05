package com.example.session48.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConditionalScheduledTask {

    private boolean taskEnabled = false;

    // Runs every 5 seconds, but only executes if taskEnabled is true
    @Scheduled(fixedRate = 5000) 
    public void runTask() {
        if (taskEnabled) {
            System.out.println("Task is running...");
        } else {
            System.out.println("Task is disabled.");
        }
    }

    public void enableTask() {
        this.taskEnabled = true;
    }

    public void disableTask() {
        this.taskEnabled = false;
    }
}

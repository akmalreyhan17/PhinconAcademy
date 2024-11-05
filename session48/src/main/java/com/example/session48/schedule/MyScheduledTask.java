package com.example.session48.schedule;

import java.util.concurrent.CompletableFuture;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduledTask {

    // This method will run every 5 seconds (5000 milliseconds)
    @Scheduled(fixedRate = 5000)
    public void runTask() {
        System.out.println("Task running every 5 seconds...");
    }

    @Scheduled(fixedDelay = 5000)
    public void runTaskWithDelay() {
        System.out.println("Task runs 5 seconds after the previous one finishes...");
    }

    @Scheduled(fixedRate = 5000, initialDelay = 10000)
    public void runTaskWithInitialDelay() {
        System.out.println("Task starts after 10 seconds, then runs every 5 seconds...");
    }

    @Scheduled(cron = "0 0 8 * * MON")
    public void weeklyTask() {
        System.out.println("Running every Monday at 8 AM...");
    }

    @Scheduled(fixedRate = 5000)
    @ConditionalOnProperty(name = "scheduler.enabled", havingValue = "true")
    public void performConditionalTask() {
        System.out.println("Executing task based on configuration...");
        // Task logic
    }

    @Retryable(value = { RuntimeException.class }, // Retry on these exceptions
            maxAttempts = 3, // Retry up to 3 times
            backoff = @Backoff(delay = 2000) // Wait 2 seconds between retries
    )
    public void doSomething() {
        System.out.println("Trying to execute task...");
        throw new RuntimeException("Oops, something went wrong!");
    }

    @Recover
    public CompletableFuture<String> recoverAsync(RuntimeException e) {
        System.out.println("Async task failed after retries. Error: " + e.getMessage());
        return CompletableFuture.completedFuture("Fallback result");
    }

    @Retryable(
        value = { RuntimeException.class },
        maxAttempts = 3,
        backoff = @Backoff(delay = 1000)
    )
    public void performTask(String param) {
        RetryContext context = RetrySynchronizationManager.getContext();
        System.out.println("Attempt #" + context.getRetryCount() + " with param: " + param);

        // Adjust param based on retry attempt
        if (context.getRetryCount() == 1) {
            param = "adjusted-param";
        } else if (context.getRetryCount() == 2) {
            param = "correct-param";
        }

        // Simulate a failure
        if (!param.equals("correct-param")) {
            throw new RuntimeException("Failed with param: " + param);
        }

        System.out.println("Task succeeded with param: " + param);
    }

}

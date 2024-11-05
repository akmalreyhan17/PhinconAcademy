package com.example.session48.schedule;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class MyExecutors {
    public void execA() {
        Executor executor = new ThreadPoolTaskExecutorBuilder()
                .corePoolSize(5)
                .maxPoolSize(25)
                .keepAlive(Duration.ofSeconds(100))
                .queueCapacity(5)
                .build();

        for (int i = 0; i < 10; i++) {
            Runnable task = new Task("" + i);
            executor.execute(task);
        }
    }

    public void execB() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void execC() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

        executor.scheduleAtFixedRate(new Task("Periodic Task"), 0, 10, TimeUnit.SECONDS);

        executor.scheduleWithFixedDelay(new Task("Delayed Task"), 0, 10, TimeUnit.SECONDS);
    }

    public void execD() {
        Executor executor = Executors.newSingleThreadExecutor();
    }

    public void myExecutor() {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future<String> future = executor.submit(new MyCallableTask());

        try {
            // The result can be retrieved with Future.get()
            String result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execE() {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.submit(new MyRunnableTask());
        executor.submit(new MyCallableTask());
        executor.submit(() -> {
            System.out.println("Processing in: " + Thread.currentThread().getName());
        });

    }

    public void execF() throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        completionService.submit(() -> "Task 1 complete");
        completionService.submit(() -> "Task 2 complete");

        // Retrieve the results as they finish
        for (int i = 0; i < 2; i++) {
            Future<String> future = completionService.take(); // Blocks until a task is completed
            System.out.println(future.get());
        }
    }

    @Async
    public void asyncTask() {
        // Long-running task here
        System.out.println("Executing async task in: " + Thread.currentThread().getName());
    }

    @Async
    public CompletableFuture<String> asyncTaskWithResult() {
        try {
            Thread.sleep(2000); // Simulate long task
            return CompletableFuture.completedFuture("Task completed");
        } catch (InterruptedException e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async("customExecutor")
    public void runTask(int taskId) {
        System.out.println("Running task " + taskId + " on thread: " + Thread.currentThread().getName());
        
        // Simulate some long-running task
        try {
            Thread.sleep(2000); // 2 seconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Task " + taskId + " completed.");
    }
}

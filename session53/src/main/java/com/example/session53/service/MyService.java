package com.example.session53.service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executor;

import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;

import com.example.session53.model.Order;
import com.example.session53.model.Product;
import com.example.session53.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class MyService {

    public Mono<Order> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public Mono<Void> myMethod() {

        Flux<Long> fastProducer = Flux.interval(Duration.ofMillis(100)); // Produces events every 100ms

        fastProducer.onBackpressureDrop() // Drop events that can't be handled in time
                .subscribe(this::handleEvent);

        fastProducer.onBackpressureBuffer(100) // Buffer up to 100 events
                .subscribe(this::handleEvent);

        fastProducer.onBackpressureLatest() // Keep only the latest event
                .subscribe(this::handleEvent);

        fastProducer.onBackpressureError() // Throws an error if overwhelmed
                .subscribe(this::handleEvent);

        Flux<Integer> numbers = Flux.range(1, 100);

        numbers.limitRate(10) // Request 10 elements at a time
                .subscribe(this::processNumber);

        Flux.range(1, 100)
                .flatMap(i -> processAsync(i), 5) // Handle 5 items concurrently
                .subscribe();

        Flux<Integer> throttledProducer = Flux.interval(Duration.ofMillis(100)) // Slow down the producer
                .flatMap(i -> produceData());

        throttledProducer.subscribe(this::processData);

        Flux<Integer> dataStream = Flux.range(1, 100);

        dataStream.buffer(50) // Collect data in batches of 50 items
                .subscribe(batch -> processBatch(batch));

        Executor myExecutor = new ThreadPoolTaskExecutorBuilder()
                .corePoolSize(5)
                .maxPoolSize(25)
                .keepAlive(Duration.ofSeconds(30))
                .build();

        Scheduler myScheduler = Schedulers.fromExecutor(myExecutor);

        System.out.println(myScheduler);

        Mono.fromCallable(() -> readFile("data.txt"))
                .subscribeOn(Schedulers.boundedElastic()) // The entire flow starts on boundedElastic
                .map(content -> processHeavyData(content)) // Still running on boundedElastic
                .subscribe(result -> System.out.println("Done!"));

        Mono.fromCallable(() -> readFile("data.txt"))
                .subscribeOn(Schedulers.boundedElastic()) // Start on boundedElastic
                .map(content -> processHeavyData(content)) // Still on boundedElastic
                .publishOn(Schedulers.parallel()) // Switch to parallel thread pool for this task
                .map(data -> furtherProcessing(data)) // Now running on parallel threads
                .subscribe(result -> System.out.println("Done!"));

        // Flux<String> stream1 = Flux.just("A", "B", "C");
        // Flux<String> stream2 = Flux.just("D", "E", "F");

        // Flux<String> combined = Flux.concat(stream1, stream2);
        // combined.subscribe(System.out::println); // Output: A B C D E F

        // Flux<String> stream1 = Flux.just("1",
        // "2").delayElements(Duration.ofMillis(50));
        // Flux<String> stream2 = Flux.just("A",
        // "B").delayElements(Duration.ofMillis(30));

        // Flux<String> merged = Flux.merge(stream1, stream2);
        // merged.subscribe(System.out::println); // Output may vary: A 1 B 2

        // Flux<String> stream1 = Flux.just("1", "2", "3");
        // Flux<String> stream2 = Flux.just("A", "B", "C");

        // Flux<String> zipped = Flux.zip(stream1, stream2, (s1, s2) -> s1 + s2);
        // zipped.subscribe(System.out::println); // Output: 1A 2B 3C

        Flux<String> stream1 = Flux.just("1", "2").delayElements(Duration.ofMillis(100));
        Flux<String> stream2 = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(50));

        Flux<String> latest = Flux.combineLatest(stream1, stream2, (s1, s2) -> s1 + s2);
        latest.subscribe(System.out::println); // Output varies based on timing: 1A, 1B, 2B, 2C...

        return null;
    }

    private Object furtherProcessing(Object data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'furtherProcessing'");
    }

    private Object processHeavyData(Object content) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processHeavyData'");
    }

    private Object readFile(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readFile'");
    }

    public Mono<Void> handleEvent(Long num) {
        return Mono.empty();
    }

    public Mono<Void> processNumber(Integer num) {
        return Mono.empty();
    }

    public Mono<Void> processAsync(Integer num) {
        return Mono.empty();
    }

    public Mono<Integer> produceData() {
        return Mono.empty();
    }

    public Mono<Integer> processData(Integer num) {
        return Mono.empty();
    }

    public Mono<Integer> processBatch(List<Integer> batch) {
        processTransaction(null);
        return Mono.empty();
    }

    private static Order processTransaction(Order order) {
        // Simulate CPU-bound work with a simple operation (e.g., calculating a hash)
        order.setHash(order.getAmount().hashCode() * order.getId());
        return order;
    }

}

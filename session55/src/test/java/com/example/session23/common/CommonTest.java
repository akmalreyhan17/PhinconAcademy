// package com.example.session23.common;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;

// import com.example.session23.service.CommonSubs;

// import reactor.core.publisher.Flux;
// import reactor.test.StepVerifier;
// import reactor.test.publisher.TestPublisher;
// import reactor.test.publisher.TestPublisher.Violation;

// @SpringBootTest
// public class CommonTest {

//     @MockBean
//     CommonSubs commonSubs;

//     @Test
//     public void testStepVerifier() {
//         Flux<String> animal = Flux.just("Cat", "Dog", "Cow")
//                 .filter(c -> c.startsWith("C"));

//         StepVerifier
//                 .create(animal)
//                 .expectNextCount(2)
//                 .expectComplete()
//                 .verifyThenAssertThat()
//                 .hasDiscarded("Dog");
//     }

//     @Test
//     public void testTestPublisher() {
//         final TestPublisher<String> test = TestPublisher.createNoncompliant(Violation.ALLOW_NULL);
//         CommonSubs commonSubs = new CommonSubs(test.flux());

//         StepVerifier.create(commonSubs.toUpperCase())
//                 .then(() -> test.emit("cat", "dog", "cow", null))
//                 .expectNext("CAT", "DOG", "COW")
//                 .expectNext("NO")
//                 .verifyComplete();
//     }
// }

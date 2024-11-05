// package com.example.session23.service;

// import org.springframework.stereotype.Service;

// import reactor.core.publisher.Flux;

// @Service
// public class CommonSubs {
//     private final Flux<String> source;

//     public CommonSubs(Flux<String> source) {
//         this.source = source;
//     }

//     public Flux<String> toUpperCase() {
//         return source.map(s -> (s != null) ? s.toUpperCase() : "NO" );
//     }
// }

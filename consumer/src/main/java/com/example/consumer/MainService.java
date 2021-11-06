package com.example.consumer;

import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainService {
    private final RestTemplate restTemplate;

    @TimeLimiter(name = "backendA", fallbackMethod = "fallback")
    public CompletableFuture<String> slowAsync() {
        return CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://localhost:9000/slow", String.class));
    }

    public CompletableFuture<String> fallback(Exception e) {
        return CompletableFuture.supplyAsync(() -> "Provider Service is down");
    }
}

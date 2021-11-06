package com.example.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class MainResource {
    private final MainService service;

    @GetMapping("/slowAsync")
    public CompletableFuture<String> slowAsync(){
        return service.slowAsync();
    }


}

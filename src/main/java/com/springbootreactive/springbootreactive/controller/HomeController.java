package com.springbootreactive.springbootreactive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    @GetMapping
    Mono<String> home() {
        return Mono.just("home");
    }
}

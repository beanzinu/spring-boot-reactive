package com.springbootreactive.springbootreactive.controller;

import com.springbootreactive.springbootreactive.domain.Dish;
import com.springbootreactive.springbootreactive.service.KitchenService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
public class ServerController {

    private final KitchenService kitchen;

    public ServerController(KitchenService kitchen) {
        this.kitchen = kitchen;
    }

    @GetMapping(value = "/server" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> ServeDishes() {
        return this.kitchen.getDishes();
    }

    @GetMapping(value = "/served-dishes" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> deliverDishes() {
        return this.kitchen.getDishes()
                .map( dish -> Dish.deliver(dish));
    }

}

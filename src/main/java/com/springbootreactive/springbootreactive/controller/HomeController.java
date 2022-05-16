package com.springbootreactive.springbootreactive.controller;

import com.springbootreactive.springbootreactive.domain.Cart;
import com.springbootreactive.springbootreactive.domain.CartItem;
import com.springbootreactive.springbootreactive.domain.Item;
import com.springbootreactive.springbootreactive.repository.CartRepository;
import com.springbootreactive.springbootreactive.repository.ItemRepository;
import com.springbootreactive.springbootreactive.service.CartService;
import com.springbootreactive.springbootreactive.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    private ItemRepository itemRepository;
    private CartRepository cartRepository;
    private CartService cartService;
    private ItemService itemService;

    public HomeController(ItemRepository itemRepository, CartRepository cartRepository, CartService cartService, ItemService itemService) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @GetMapping
    Mono<Rendering> home() {
        return Mono.just(Rendering.view("home.html")
                .modelAttribute("items",this.itemRepository.findAll()
                )
                .modelAttribute("cart",this.cartRepository.findById("My Cart")
                .defaultIfEmpty(new Cart("My Cart")))
                .build());
    }

    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id) {
        return this.cartService.addToCart("My Cart",id)
                .thenReturn("redirect:/");
    }

    @GetMapping("/search")
    Mono<Rendering> search( @RequestParam(required = false) String name ,@RequestParam(required = false) String description, //
        @RequestParam boolean useAnd) {
        return Mono.just(Rendering.view("home.html") //
                .modelAttribute("items",itemService.searchByExample(name,description,useAnd))
                .modelAttribute("cart",this.cartRepository.findById("My Cart").defaultIfEmpty(new Cart("My Cart")))
                .build());
    }
}

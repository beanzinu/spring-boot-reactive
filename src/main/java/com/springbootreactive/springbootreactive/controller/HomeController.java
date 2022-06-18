package com.springbootreactive.springbootreactive.controller;

import com.springbootreactive.springbootreactive.domain.Cart;
import com.springbootreactive.springbootreactive.domain.CartItem;
import com.springbootreactive.springbootreactive.domain.Item;
import com.springbootreactive.springbootreactive.domain.Post;
import com.springbootreactive.springbootreactive.repository.CartRepository;
import com.springbootreactive.springbootreactive.repository.ItemRepository;
import com.springbootreactive.springbootreactive.repository.PostRepository;
import com.springbootreactive.springbootreactive.service.CartService;
import com.springbootreactive.springbootreactive.service.InventoryService;
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
    private PostRepository postRepository;
    private CartService cartService;
    private ItemService itemService;
    private InventoryService inventoryService;

    public HomeController(ItemRepository itemRepository, CartRepository cartRepository, PostRepository postRepository, CartService cartService, ItemService itemService, InventoryService inventoryService) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.postRepository = postRepository;
        this.cartService = cartService;
        this.itemService = itemService;
        this.inventoryService = inventoryService;
    }

    //    @GetMapping("/")
//    Mono<Rendering> home() {
//        return Mono.just(Rendering.view("home.html")
//                .modelAttribute("items",this.inventoryService.getInventory()
//                )
//                .modelAttribute("cart",this.inventoryService.getCart("My Cart")
//                .defaultIfEmpty(new Cart("My Cart")))
//                .build());
//    }

    @GetMapping("/")
    Mono<Rendering> home() {
        return Mono.just(Rendering.view("post.html")
                .modelAttribute("posts",this.postRepository.findAll()
                        .defaultIfEmpty(new Post(null,null,null,null))
                )
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

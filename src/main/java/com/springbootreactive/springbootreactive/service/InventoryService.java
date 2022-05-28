package com.springbootreactive.springbootreactive.service;

import com.springbootreactive.springbootreactive.domain.Cart;
import com.springbootreactive.springbootreactive.domain.CartItem;
import com.springbootreactive.springbootreactive.domain.Item;
import com.springbootreactive.springbootreactive.repository.CartRepository;
import com.springbootreactive.springbootreactive.repository.ItemRepository;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class InventoryService {

    CartRepository cartRepository;
    ItemRepository itemRepository;

    public InventoryService(CartRepository cartRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    public Flux<Item> getInventory(){
        return this.itemRepository.findAll();
    }
    public Mono<Cart> getCart(String cartId){
        return this.cartRepository.findById(cartId);
    }

    public Mono<Cart> addItemToCart(String cartId, String itemId){
        return this.cartRepository.findById(cartId)
                .log("foundCart")
                .defaultIfEmpty(new Cart(cartId))
                .log("emptyCart")
                .flatMap(cart -> cart.getCartItems().stream()
                    .filter(cartItem -> cartItem.getItem()
                        .getId().equals(itemId))
                    .findAny()
                    .map(cartItem -> {
                        cartItem.increment();
                        return Mono.just(cart).log("newCartItem");
                    })
                // findAny 실패시
                .orElseGet(() -> {
                    return this.itemRepository.findById(itemId)
                            .log("fetchedItem")
                            .map(item -> new CartItem(item))
                            .log("cartItem")
                            .map(cartItem -> {
                                cart.getCartItems().add(cartItem);
                                return cart;
                            }).log("addedCartItem");
                }))
                .log("cartWithAnotherItem")
                .flatMap(cart -> this.cartRepository.save(cart))
                .log("savedCart");
    }
}

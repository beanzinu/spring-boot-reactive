package com.springbootreactive.springbootreactive.controller;

import com.springbootreactive.springbootreactive.domain.Item;
import com.springbootreactive.springbootreactive.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/api/items")
    // Flux<>을 반환하므로 0개 또는 그 이상의 아이템 객체가 JSON 구조로 직렬화되어 응답 본문에 기록된다.
    // 스프링 데이터의 페이징(pagination)을 적용해 조회 한도를 정하는 편이 좋다.
    Flux<Item> findAll(){
        return this.itemRepository.findAll();
    }

    @PostMapping("/api/items")
    Mono<ResponseEntity<?>> addNewItem(@RequestBody Mono<Item> item){

        return item.flatMap(s -> this.itemRepository.save(s))
                .map( savedItem ->
                    ResponseEntity.created(URI.create("/api/items"+savedItem.getId()))
                    .body(savedItem)
                );
    }

    @PutMapping("/api/items/id")
    public Mono<ResponseEntity<?>> updateItem(@RequestBody Mono<Item> item,@PathVariable String id){
        return item.map(content -> new Item(id,content.getName(),content.getDescription(),content.getPrice() ))
                .flatMap(this.itemRepository::save)
                .map(ResponseEntity::ok);
    }
}

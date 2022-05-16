package com.springbootreactive.springbootreactive.service;

import com.springbootreactive.springbootreactive.domain.Item;
import com.springbootreactive.springbootreactive.repository.ItemRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    // Example 쿼리 예제
    public Flux<Item> searchByExample(String name, String description, boolean useAnd){
        Item item = new Item(name,description,0.0);

        ExampleMatcher matcher = (useAnd
            ? ExampleMatcher.matchingAll() //
            : ExampleMatcher.matchingAny())  //
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("price");

        Example<Item> probe = Example.of(item,matcher);

        return repository.findAll(probe);
    }

}

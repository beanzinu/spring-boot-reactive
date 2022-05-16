package com.springbootreactive.springbootreactive.repository;

import com.springbootreactive.springbootreactive.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemRepository extends ReactiveCrudRepository<Item, String>, ReactiveQueryByExampleExecutor<Item> {
    Flux<Item> findByNameContaining(String partialName);
}

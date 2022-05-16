package com.springbootreactive.springbootreactive.repository;

import com.springbootreactive.springbootreactive.domain.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public interface CartRepository extends ReactiveCrudRepository<Cart,String> {
}

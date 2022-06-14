package com.springbootreactive.springbootreactive.repository;

import com.springbootreactive.springbootreactive.domain.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends ReactiveCrudRepository<Post,String> {
}

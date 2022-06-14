package com.springbootreactive.springbootreactive.repository;

import com.springbootreactive.springbootreactive.domain.PostSubComment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostSubCommentRepository extends ReactiveCrudRepository<PostSubComment,String> {
}

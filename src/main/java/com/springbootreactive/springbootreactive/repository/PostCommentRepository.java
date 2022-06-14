package com.springbootreactive.springbootreactive.repository;

import com.springbootreactive.springbootreactive.domain.PostComment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends ReactiveCrudRepository<PostComment,String> {
}

package com.springbootreactive.springbootreactive.service;

import com.springbootreactive.springbootreactive.domain.Post;
import com.springbootreactive.springbootreactive.domain.PostComment;
import com.springbootreactive.springbootreactive.domain.PostSubComment;
import com.springbootreactive.springbootreactive.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class PostSubCommentService {

    PostRepository postRepository;

    public PostSubCommentService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Mono<?> addPostSubComment(Mono<PostSubComment> postSubCommentMono){
        return postSubCommentMono.flatMap( p -> {
            // Post 생성
            return postRepository.findById("post1")
                    .defaultIfEmpty(new Post("post1","post title","post content",new ArrayList<>()))
                    .flatMap( post -> {
                        // Post - PostComment 찾기
                        PostComment findPostComment = post.getPostComments().stream().findAny()
                                .orElseGet(() -> post.addPostComment(new PostComment()));
                        // PostComment.postSubCommentList - PostSubComment 추가
                        findPostComment.addPostSubComment(new PostSubComment(p.getComment()));
                        return postRepository.save(post);
                    });
        });
    }


}

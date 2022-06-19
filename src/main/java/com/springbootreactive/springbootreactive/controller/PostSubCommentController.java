package com.springbootreactive.springbootreactive.controller;

import com.springbootreactive.springbootreactive.domain.Post;
import com.springbootreactive.springbootreactive.domain.PostComment;
import com.springbootreactive.springbootreactive.domain.PostSubComment;
import com.springbootreactive.springbootreactive.repository.PostCommentRepository;
import com.springbootreactive.springbootreactive.repository.PostRepository;
import com.springbootreactive.springbootreactive.repository.PostSubCommentRepository;
import com.springbootreactive.springbootreactive.service.PostSubCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class PostSubCommentController {

    PostSubCommentRepository postSubCommentRepository;
    PostCommentRepository postCommentRepository;
    PostRepository postRepository;
    PostSubCommentService postSubCommentService;

    public PostSubCommentController(PostSubCommentRepository postSubCommentRepository, PostCommentRepository postCommentRepository, PostRepository postRepository, PostSubCommentService postSubCommentService) {
        this.postSubCommentRepository = postSubCommentRepository;
        this.postCommentRepository = postCommentRepository;
        this.postRepository = postRepository;
        this.postSubCommentService = postSubCommentService;
    }

    @PostMapping("/api/postsubcomment")
//    public Mono<?> registerPostSubComment(@RequestParam(name="id")String id, @RequestParam(name="comment")String comment){
    public Mono<?> registerPostSubComment(@RequestBody Mono<PostSubComment> postSubCommentMono){
        return postSubCommentService.addPostSubComment(postSubCommentMono);
    }

    @GetMapping("/api/post")
    public Flux<Post> getPost(){
        return postRepository.findAll()
                .defaultIfEmpty(new Post(null,null,null,null));
    }

    @GetMapping("/api/postsubcomment")
    public Flux<PostSubComment> getPostSubComments(){
        return postSubCommentRepository.findAll()
                .defaultIfEmpty(null);
    }


}

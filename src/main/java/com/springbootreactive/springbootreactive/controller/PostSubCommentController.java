package com.springbootreactive.springbootreactive.controller;

import com.springbootreactive.springbootreactive.domain.Post;
import com.springbootreactive.springbootreactive.domain.PostComment;
import com.springbootreactive.springbootreactive.domain.PostSubComment;
import com.springbootreactive.springbootreactive.repository.PostCommentRepository;
import com.springbootreactive.springbootreactive.repository.PostRepository;
import com.springbootreactive.springbootreactive.repository.PostSubCommentRepository;
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

    public PostSubCommentController(PostSubCommentRepository postSubCommentRepository, PostCommentRepository postCommentRepository, PostRepository postRepository) {
        this.postSubCommentRepository = postSubCommentRepository;
        this.postCommentRepository = postCommentRepository;
        this.postRepository = postRepository;
    }

    @PostMapping("/api/postsubcomment")
//    public Mono<?> registerPostSubComment(@RequestParam(name="id")String id, @RequestParam(name="comment")String comment){
    public Mono<?> registerPostSubComment(@RequestBody Mono<PostSubComment> postSubCommentMono){
        return postSubCommentMono.flatMap( p -> {
                // Post 생성
                return postRepository.findById("post1")
                    .defaultIfEmpty(new Post("post1","post title","post content",new ArrayList<>()))
                    .flatMap( post -> {
                        // Post - PostComment 찾기

                        PostComment findPostComment = post.getPostComments().stream().findAny()
                                .orElseGet(() -> post.addPostComment(new PostComment("postComment1")));
                        // PostComment.postSubCommentList - PostSubComment 추가
                        findPostComment.addPostSubComment(new PostSubComment(p.getId(),p.getComment()));
                        return postRepository.save(post);
                });
        });


                // subComment -> postComment -> post 순서로 저장
//                return postSubComment.map( p -> new PostSubComment(p.getId(),p.getComment()))
//                .flatMap( p -> postSubCommentRepository.save(p))
//                .log("new PostSubComment")
//                .flatMap( p -> {
//                    // 임의의 PostComment 생성
//                    Mono<PostComment> findPostComment = postCommentRepository.findById("postComment1")
//                            .defaultIfEmpty(new PostComment("postComment1"));
//                    return findPostComment.flatMap( postComment -> {
//                        postComment.addPostSubComment(p);
//                        return postCommentRepository.save(postComment);
//                    });
//                })
//                .log("new PostComment")
//                .flatMap( postComment -> {
//                    // 임의의 Post 생성
//                    Mono<Post> findPost = postRepository.findById("post1")
//                            .defaultIfEmpty(new Post("post1", "post title", "post content", new ArrayList<>()));
//                    return findPost.flatMap( post -> {
//                        post.addPostComment(postComment);
//                        return postRepository.save(post);
//                    });
//                })
//                .log("new Post")
//                .flatMap( post -> postSubCommentRepository.findById("1"));
    }

    @GetMapping("/api/post")
    public Flux<Post> getPost(){
        return postRepository.findAll()
                .defaultIfEmpty(new Post(null,null,null,null));
    }

    @GetMapping("/api/postsubcomment")
    public Flux<PostSubComment> getPostSubComments(){
        return postSubCommentRepository.findAll()
                .defaultIfEmpty(new PostSubComment(null,null));
    }


}

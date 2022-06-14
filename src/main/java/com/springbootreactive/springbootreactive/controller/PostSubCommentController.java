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
    public Mono<PostSubComment> registerPostSubComment(@RequestBody Mono<PostSubComment> postSubComment){
                return postSubComment.flatMap( p -> postSubCommentRepository.save(p) )
                .log("new PostSubComment")
                .flatMap( p -> {
                    // 임의의 PostComment 생성
                    PostComment newPostComment = new PostComment("postComment1");
                    newPostComment.addPostSubComment(p);
                    return postCommentRepository.save(newPostComment);
                })
                .log("new PostComment")
                .flatMap( postComment -> {
                    // 임의의 Post 생성
                    Post newPost = new Post("post1","제목","내용",new ArrayList<>());
                    newPost.addPostComment(postComment);
                    return postRepository.save(newPost);
                })
                .log("new Post")
                .map( post -> {
                    Optional<PostComment> findPostComment = post.getPostComments().stream().findAny();
                    if( findPostComment.isPresent() ) {
                        Optional<PostSubComment> findPostSubComment = findPostComment.get().getPostSubCommentList().stream().findAny();
                        return findPostSubComment.get();
                    }
                    return new PostSubComment(null,null);
                })
                ;


//        return postRepository.save(new Post("post1","제목","내용",new ArrayList<>()) )
//                .log("new Post")
//                .flatMap( post -> postCommentRepository.save( post.addPostComment(new PostComment("postComment1") ) ) )
//                .log("new PostComment")
//                .flatMap( postComment ->
//                    postSubComment.flatMap( p -> {
//                        postComment.addPostSubComment(p);
//                        return postSubCommentRepository.save(new PostSubComment(p.getId(),p.getComment()));
////                        return p;
//                    })
//                );
    }

    @GetMapping("/api/post")
    public Flux<Post> getPost(){
        return postRepository.findAll().defaultIfEmpty(new Post(null,null,null,null));
    }

    @GetMapping("/api/postsubcomment")
    public Flux<PostSubComment> getPostSubComments(){
        return postSubCommentRepository.findAll().defaultIfEmpty(new PostSubComment(null,null));
    }


}

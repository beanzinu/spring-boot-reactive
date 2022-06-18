package com.springbootreactive.springbootreactive.domain;

import org.springframework.data.annotation.Id;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class PostComment {

     private @Id String id;
     private List<PostSubComment> postSubCommentList;

     public PostComment(String id) {
          this.id = id;
          this.postSubCommentList = new ArrayList<>();
     }

     public PostSubComment addPostSubComment(PostSubComment postSubComment){
          this.postSubCommentList.add(postSubComment);
          return postSubComment;
     }

     public String getId() {
          return id;
     }

     public List<PostSubComment> getPostSubCommentList() {
          return postSubCommentList;
     }
}

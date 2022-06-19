package com.springbootreactive.springbootreactive.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.MongoId;
import reactor.core.publisher.Mono;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

public class PostComment {

     @MongoId
     private String id;
     private List<PostSubComment> postSubCommentList;

     public PostComment() {
          this.id = new ObjectId().toString();
          this.postSubCommentList = new ArrayList<>();
     }

     public PostSubComment addPostSubComment(PostSubComment postSubComment){
          this.postSubCommentList.add(postSubComment);
          return postSubComment;
     }

     public String getId() {
          return this.id;
     }

     public List<PostSubComment> getPostSubCommentList() {
          return this.postSubCommentList;
     }
}

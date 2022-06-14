package com.springbootreactive.springbootreactive.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Post {
    private @Id String id;
    private String title;
    private String content;
    private List<PostComment> postComments;

    public Post(String id, String title, String content, List<PostComment> postComments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postComments = postComments;
    }

    public PostComment addPostComment(PostComment postComment){
        this.postComments.add(postComment);
        return postComment;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }
}

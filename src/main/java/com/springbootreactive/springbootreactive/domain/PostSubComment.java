package com.springbootreactive.springbootreactive.domain;

import org.springframework.data.annotation.Id;

public class PostSubComment {
    private @Id String id;
    private String comment;

    public PostSubComment(String id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }
}

package com.springbootreactive.springbootreactive.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;


public class PostSubComment {
    private @Id String id;
    private String comment;

    public PostSubComment(String comment) {
        this.id = new ObjectId().toString();
        this.comment = comment;
    }

    @PersistenceConstructor
    protected PostSubComment(String id,String comment){
        this.id = id;
        this.comment = comment;
    }


    public String getId() {
        return this.id;
    }

    public String getComment() {
        return this.comment;
    }
}

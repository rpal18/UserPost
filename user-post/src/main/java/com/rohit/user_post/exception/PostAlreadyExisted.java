package com.rohit.user_post.exception;

public class PostAlreadyExisted extends RuntimeException{
    public PostAlreadyExisted(String message){
        super(message);
    }
}

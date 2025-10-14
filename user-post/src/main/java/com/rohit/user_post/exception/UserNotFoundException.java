package com.rohit.user_post.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (String message ) {
        super(message);
    }
}

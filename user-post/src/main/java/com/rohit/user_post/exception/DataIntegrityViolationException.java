package com.rohit.user_post.exception;

public class DataIntegrityViolationException extends RuntimeException{

    public DataIntegrityViolationException(String message){
        super(message);
    }
}

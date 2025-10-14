package com.rohit.user_post.exception;


public class UserAlreadyExistException extends RuntimeException{
    public  UserAlreadyExistException(String message){
        super(message);
    }
}

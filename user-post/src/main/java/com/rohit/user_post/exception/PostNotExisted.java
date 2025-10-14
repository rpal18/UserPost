package com.rohit.user_post.exception;

public class PostNotExisted extends RuntimeException{
 public PostNotExisted(String message){
     super(message);
 }
}

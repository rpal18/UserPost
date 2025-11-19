package com.rohit.user_post.controller;

import com.rohit.user_post.Dto.PostResponse;
import com.rohit.user_post.Dto.UserDto;
import com.rohit.user_post.services.PostService;
import com.rohit.user_post.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/api")
public class PostController {

    private PostService postService;
    private UserService userService;

    @Autowired
    public PostController(PostService postService , UserService userService){
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/posts")
    public PostResponse getAllPosts( @Valid @RequestBody UserDto userDto,
                                     @RequestParam(name = "pageNumber" , defaultValue = "0")  Integer pageNumber ,
                                     @RequestParam(name = "pageSize" , defaultValue = "10") Integer pageSize){
      return userService.getPosts(userDto , pageNumber , pageSize);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PostResponse> deletePost(@PathVariable int id){
      PostResponse response =  postService.deletePostById(id);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/allPosts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(name = "pageNumber" , defaultValue = "0") Integer pageNumber  ,
                                                    @RequestParam(name = "pageSize" , defaultValue = "10") Integer pageSize){
        PostResponse response = postService.getAllPosts(pageNumber , pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

package com.rohit.user_post.controller;
import com.rohit.user_post.Dto.PostResponse;
import com.rohit.user_post.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api")
public class UserController {


    private UserService userservice;

    @Autowired
    public UserController(UserService userservice){
        this.userservice = userservice;
    }

    @GetMapping("/posts/{id}")
    public PostResponse sayHello(@PathVariable int id) {
        return userservice.getPostsById(id);
    }


}

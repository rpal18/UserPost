package com.rohit.user_post;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.rohit.user_post.model.Post;
import com.rohit.user_post.repository.UserRepository;
import com.rohit.user_post.services.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rohit.user_post.model.User;
import com.rohit.user_post.services.UserService;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;



    @Test
    public void createUserTest(){
        User user = new User();
        user.setName("ravi");
        user.setDob(LocalDate.of(2019 ,03, 21));
        user.setEmail("abc@example.com");
        user.setMobileNumber("5676347666");

        userService.createUser(user);

    }

    @Test
    public void deleteUserTest(){
       userService.deleteUser(2);
    }


    @Test
    public void addPostTest(){
        Post post = new Post();
        post.setContent("this post is all about testing addPost servise defined in PostService class : ");
        User user = userRepository.findById(2).orElseThrow() ;
        post.setUser(user);
        post.setTitle("test");

        postService.addPost(post);

    }

    @Test
    public void getPostByIdTest(){
        List<Post> post =userService.getPostsById(3);
        System.out.println(post);
    }





}

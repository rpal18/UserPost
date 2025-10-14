package com.rohit.user_post.services;

import com.rohit.user_post.exception.PostAlreadyExisted;
import com.rohit.user_post.exception.PostNotExisted;
import com.rohit.user_post.model.Post;
import com.rohit.user_post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    //adding post

    @Transactional
    public void addPost(Post post){
       int postId =  post.getPostId();
       if(postRepository.existsById(postId)) throw new PostAlreadyExisted("USer has already posted this post:");

       postRepository.save(post);
    }


    //deleting post

    @Transactional
    public void deletePostById(int postId){
        // check whether existed or not
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotExisted("no such post found"));

        //hard deleting

        postRepository.delete(post);
    }


    //updating post
    @Transactional
    public void updatePostById(int postId , String content){

        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotExisted("no such post found"));

        post.setContent(content);

        postRepository.save(post);

    }

}

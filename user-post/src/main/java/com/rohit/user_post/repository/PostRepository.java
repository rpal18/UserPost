package com.rohit.user_post.repository;

import com.rohit.user_post.Dto.PostDto;
import com.rohit.user_post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {



    List<Post> findPostByUserId(int id);

    Page<Post> findPostByUserId(int id, Pageable pageDetails);
}
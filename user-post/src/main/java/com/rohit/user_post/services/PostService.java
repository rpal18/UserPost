package com.rohit.user_post.services;

import com.rohit.user_post.Dto.PostDto;
import com.rohit.user_post.Dto.PostResponse;
import com.rohit.user_post.exception.PostAlreadyExisted;
import com.rohit.user_post.exception.PostNotExisted;
import com.rohit.user_post.model.Post;
import com.rohit.user_post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Transactional
    public ResponseEntity<PostDto> addPost(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        if (postRepository.existsById(post.getPostId()))
            throw new PostAlreadyExisted("USer has already posted this post:");
        Post post1 = postRepository.save(post);
        PostDto dto = modelMapper.map(post1, PostDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }




    @Transactional
    public PostResponse deletePostById(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new PostNotExisted("No such Post found with this Post id:"));
        postRepository.delete(post);
        List<PostDto> postDtos = new ArrayList<>();
        PostDto dto = modelMapper.map(post , PostDto.class);
        postDtos.add(dto);
        PostResponse response = new PostResponse();
        response.setContent(postDtos);
        return response;
    }

    @Transactional
    public void updatePostById(int postId, String content) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotExisted("no such post found"));

        post.setContent(content);

        postRepository.save(post);

    }

    /*
    -----------------------------------------------------------------------------------
    Getting all posts in the database , not useful but doing it for practice .
    -----------------------------------------------------------------------------------
     */
  public PostResponse getAllPosts(Integer pageNumber , Integer pageSize){
      Pageable pageDetails = PageRequest.of(pageNumber , pageSize);
      Page<Post> postPage = postRepository.findAll(pageDetails);
      List<Post> allPosts = postPage.getContent();
      if(allPosts.isEmpty()){
          throw new PostNotExisted("There is no post in the database saved till now or database empty :");
      }

      List<PostDto> allpostDto = allPosts.stream().
              map((element) -> modelMapper.map(element, PostDto.class)).toList();

      PostResponse postResponse = new PostResponse();
      postResponse.setContent(allpostDto);
      postResponse.setLast(postPage.isLast());
      postResponse.setPageNumber(postPage.getNumber());
      postResponse.setTotalElements((int)postPage.getTotalElements());
      postResponse.setTotalPageNumber(postPage.getTotalPages());
      postResponse.setPageSize(postPage.getSize());
      return postResponse;
  }

}

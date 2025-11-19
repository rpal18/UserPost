package com.rohit.user_post.services;
import com.rohit.user_post.Dto.PostDto;
import com.rohit.user_post.Dto.PostResponse;
import com.rohit.user_post.Dto.UserDto;

import com.rohit.user_post.Dto.UserResponse;
import com.rohit.user_post.exception.PostNotExisted;
import com.rohit.user_post.model.Post;
import com.rohit.user_post.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rohit.user_post.exception.UserAlreadyExistException;
import com.rohit.user_post.exception.UserNotFoundException;

import com.rohit.user_post.model.User;
import com.rohit.user_post.repository.UserRepository;

import jakarta.transaction.Transactional;
import java.util.List;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepository postRepository;

    @Transactional
    /*
    here , I have marked it as Transactional because if this method execute
    fully then only I want to make changes in database ( creating new User)
    ------------------------------------------------------------------------------------------------------------------------------------------------------
    mobile number will always be unique ( I know that userId will also be
    unique , But it would be created after saving into database
    , so while creating User I will be having only mobile number and email address
    */

    public ResponseEntity<UserResponse> createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        String mobileNumber = user.getMobileNumber();
        if (userRepository.existsByMobileNumber(mobileNumber))
            throw new UserAlreadyExistException(" this user exists already !! please login instead !!");
        User user1 = userRepository.save(user);
        UserResponse userResponse = new UserResponse(user1.getName() , user1.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);


    }

    public ResponseEntity<UserDto> deleteUser(UserDto userDto) {

        User user = modelMapper.map(userDto, User.class);

        if (!userRepository.existsById(user.getId()))
            throw new UserNotFoundException(" cannot " + "delete as User does not exist in database :");
   /*
    EXPLANATION FOR LATER ME ( FOR LEARNING PURPOSE )
   -------------------------------------------------------------------------------
         here, I have two ways to delete user from database
        1) soft delete ( prefer i real world Use case )
        2) hard delete good for practice
           here I have used hard delete
   -------------------------------------------------------------------------------
    */
        User user1 = userRepository.getUserById(user.getId());
        UserDto dto = modelMapper.map(user1, UserDto.class);
        userRepository.deleteById(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(dto);

    }


    public PostResponse getPosts(UserDto userDto , Integer pageNumber , Integer pageSize) {
        User user = modelMapper.map(userDto, User.class);
        Pageable pageDetails = PageRequest.of(pageNumber , pageSize);
        Page<Post> postPage = postRepository.findPostByUserId(user.getId() , pageDetails);

        List<Post> allposts = postPage.getContent();
        if (allposts.isEmpty()) {
            throw new PostNotExisted("No such Post created by this user");
        }

        List<PostDto> allPostDto = allposts.stream().map((element) -> modelMapper.
                map(element, PostDto.class)).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPostDto);
        postResponse.setPageSize(pageSize);
        postResponse.setTotalElements((int)postPage.getTotalElements());
        postResponse.setTotalPageNumber(postResponse.getTotalPageNumber());
        postResponse.setLast(postPage.isLast());
        return postResponse;

    }

    public PostResponse getPostsById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No Such User Exists"));
        List<Post> allPosts = postRepository.findPostByUserId(id);
        List<PostDto> postDtos = allPosts.stream().map((element) -> modelMapper.map(element, PostDto.class)).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        return postResponse;
    }
}


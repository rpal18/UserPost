package com.rohit.user_post.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rohit.user_post.exception.UserAlreadyExistException;
import com.rohit.user_post.exception.UserNotFoundException;
import com.rohit.user_post.model.Post;
import com.rohit.user_post.model.User;
import com.rohit.user_post.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    // creating user
    //it should not  be already available
    //
   @Transactional
    /*
    here , I have marked it as Transactional because if this method execute fully then only I
    want to make changes in database ( creating new User )
     */
    public void createUser(User user) {

        /* mobile number will always be unique ( i know that userId will also be unique , But it would be created
          after saving into database , so while creating User i will be having only mobile number and email address
        */

        String mobileNumber = user.getMobileNumber();
        if (userRepository.existsByMobileNumber(mobileNumber)) throw new UserAlreadyExistException
                (" this user exists already !! please login instaed !!");
        // primary key will be managed by database
        userRepository.save(user);
    }

    // getting User id

    public int getUserId(User user){
        if(!userRepository.existsByMobileNumber(user.getMobileNumber())) throw new UserNotFoundException("User does not exist in database :");

        return user.getUserId();
    }


    //delete User

    public void deleteUser(int userId){

        if(!userRepository.existsByUserId(userId)) throw new UserNotFoundException(" cannot " +
                "delete as User does not exist in database :") ;

        // here i have two ways to delete user from database
        //1) soft delete ( prefer i real world usecases
        //2) hard delete good for practice
        // here i have used hard delete
        userRepository.deleteById(userId);
    }


    //getPosts of user

    public  List<Post> getPostsById(int userId){

        User user = (User)userRepository.findById(userId).orElseThrow(() ->  new UserNotFoundException(" cannot " +
                "fetch Post as User does not exits") );

        List<Post> posts = user.getPostList();
        return posts;
    }
}


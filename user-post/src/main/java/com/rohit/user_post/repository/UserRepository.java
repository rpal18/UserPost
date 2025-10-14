package com.rohit.user_post.repository;

import com.rohit.user_post.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


    boolean existsByMobileNumber(String num);
    boolean existsByUserId(int id);
}
package com.rohit.user_post.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "users")


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false , name = "user_name")
    private String name ;
    @Column(nullable = false , name = "email" , unique = true)
    private String email;
    @Column(nullable = false , name  = "mobile_number" , unique = true)
    @Pattern(regexp = "//d{10}" , message = "{Invalid mobile number }")
    private String mobileNumber ;
    @Column(nullable = false , name ="date_of_birth" )
    private LocalDate dob;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Post> postList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public User() {
    }

    public User(int userId, String name, String email, String mobileNumber, LocalDate dob, List<Post> postList) {
        this.id = userId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.dob = dob;
        this.postList = postList;
    }
}

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
//import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId ;
    @Column(nullable = false , name = "user_name")
    private String name ;
    @Column(nullable = false , name = "email" , unique = true)
    private String email;
    @Column(nullable = false , name  = "mobile_number" , unique = true)
//    @Pattern(regexp = "//d{10}" , message = "{Invalid mobile number }")
    private String mobileNumber ;
    @Column(nullable = false , name ="date_of_birth" )
    private LocalDate dob;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Post> postList;

}

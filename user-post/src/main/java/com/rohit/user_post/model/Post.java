package com.rohit.user_post.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PostId;

    @Column(nullable = false , name = "title")
    private String title ;


    @Column(nullable = false , name = "content")
    private String content ;

    @ManyToOne
    @JoinColumn
    private User user;


}

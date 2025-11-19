package com.rohit.user_post.model;

import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false , name = "title")
    private String title ;


    @Column(nullable = false , name = "content")
    private String content ;

    @ManyToOne
    @JoinColumn
    private User user;

    public int getPostId() {
        return id;
    }

    public void setPostId(int postId) {
        id = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

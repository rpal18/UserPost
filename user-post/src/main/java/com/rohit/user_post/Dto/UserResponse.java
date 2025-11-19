package com.rohit.user_post.Dto;

public class UserResponse {
  private String userName;
  private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserResponse(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public UserResponse() {
    }
}

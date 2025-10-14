package com.rohit.user_post.exception;

import lombok.*;

/*
   this is a DTO (Data transfer object ) class , that will return Api error as an Object in client friendly way , withput
   exposing much information ,

   it exposes only essential information
 */
@Getter
@Setter
@AllArgsConstructor

@NoArgsConstructor
@Builder
public class ApiError {

    private String timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;

}

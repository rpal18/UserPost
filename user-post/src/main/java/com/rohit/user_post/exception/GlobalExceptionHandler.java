package com.rohit.user_post.exception;
/*

this is a handler class to  handel when there would be an exception occurs .

It is a controller class , spring manage it automatically .

 */

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // here I am going to use logger
    // this field is final because , we don't want it to reassign this

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiError> handlesUserExists(UserAlreadyExistException ex , HttpServletRequest req){
        log.warn("Conflict : {}" , ex.getMessage());
        ApiError errorDto = new ApiError(
                Instant.now().toString() ,
                HttpStatus.CONFLICT.value() ,
                HttpStatus.CONFLICT.getReasonPhrase() ,
                ex.getMessage() ,
                req.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);

    }
    /*
    we can define Exception handler for different  exception as well , lets say DB unique Constraint exception

     */

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDBException(DataIntegrityViolationException ex , HttpServletRequest req){
        log.warn("DataBase constraint violated : {}  " , ex.getMessage()) ;
        String msg =  "database constraint violated" ;
        ApiError body = new ApiError(
                Instant.now().toString() ,
                HttpStatus.CONFLICT.value() ,
                HttpStatus.CONFLICT.getReasonPhrase(),
                msg ,
                req.getRequestURI()

        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    // unexpected error ( Unhandled error )

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnhandledException(Exception ex , HttpServletRequest req){
        log.warn("Unexpected error is there : {} " , ex.getMessage());
        String msg = "Something Went Wrong !!" ;
        ApiError body = new ApiError(
          Instant.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                msg ,
                req.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(UserNotFoundException ex , HttpServletRequest req){
        log.warn("user not found : {} " , ex.getMessage());

        ApiError error = new ApiError(
                 Instant.now().toString() ,
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                req.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(PostAlreadyExisted.class)
    public ResponseEntity<ApiError> handlePostExists(PostAlreadyExisted ex , HttpServletRequest req){
        log.warn("Post already exists : {}" , ex.getMessage());

        ApiError body = new ApiError(
                Instant.now().toString(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                req.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(PostNotExisted.class)
    public ResponseEntity<ApiError> handlePostExists(PostNotExisted ex , HttpServletRequest req){
        log.warn("Post not  exists conflict occurs : {}" , ex.getMessage());

        ApiError body = new ApiError(
                Instant.now().toString(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                req.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }


}

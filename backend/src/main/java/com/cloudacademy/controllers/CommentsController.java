package com.cloudacademy.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.cloudacademy.jwt.TokenManagement;
import com.cloudacademy.pojo.Comment;
import com.cloudacademy.serializable.CommentRequest;
import com.cloudacademy.service.CommentService;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class CommentsController {

  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/comments", 
                  method = RequestMethod.GET, 
                  produces = "application/json")
  List<Comment> comments(@RequestHeader(value="x-auth-token") String token) {
    System.out.println("/comments GET called...");
    
    TokenManagement.authenticateJWTToken(token);
    var allComments = CommentService.getAllComments();
  
    return allComments;
  }

  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/comments", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
  Comment createComment(@RequestHeader(value="x-auth-token") String token, @RequestBody CommentRequest input) {
    System.out.println("/comments POST called...");
    System.out.println("token: " + token);
    System.out.println("input.body: " + input.body);

    var userName = TokenManagement.authenticateJWTToken(token);
    var newComment = CommentService.createComment(userName, input.body);

    return newComment;
  }

  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE, produces = "application/json")
  Boolean deleteComment(@RequestHeader(value="x-auth-token") String token, @PathVariable("id") String id) {
    System.out.println("/comments DELETE called...");
    
    TokenManagement.authenticateJWTToken(token);
    var deletedOk = CommentService.deleteComment(id);

    return deletedOk;
  }
}
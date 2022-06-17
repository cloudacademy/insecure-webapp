package com.cloudacademy.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import com.cloudacademy.Unauthorized;
import com.cloudacademy.jwt.TokenManagement;
import com.cloudacademy.pojo.User;
import com.cloudacademy.serializable.LoginRequest;
import com.cloudacademy.serializable.LoginResponse;
import com.cloudacademy.service.UserService;
import com.cloudacademy.crypto.Hash;

@RestController
@EnableAutoConfiguration
public class LoginController {
	
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", 
                    method = RequestMethod.POST, 
                    produces = "application/json", 
                    consumes = "application/json")
    LoginResponse login(@RequestBody LoginRequest input) {
        System.out.println("login POST called...");
        var authenticated = false;

        User user = UserService.getUser(input.username);

        System.out.println("database hash=" + user.hashedPassword);
        System.out.println("java hash=" + Hash.md5(input.password));

        if (Hash.md5(input.password).equals(user.hashedPassword)) {
          authenticated = true;
        } 

        if (authenticated) {
            System.out.println("authenticated successfully: " + input.username);
            String sessionToken = TokenManagement.createJWTToken(input.username);
            return new LoginResponse(sessionToken);
        } else {
            throw new Unauthorized("Access Denied");
        }
	}
}
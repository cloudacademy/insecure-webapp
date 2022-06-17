package com.cloudacademy.serializable;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    public String token;
   
    public LoginResponse(String token) { 
        this.token = token;
    }
}

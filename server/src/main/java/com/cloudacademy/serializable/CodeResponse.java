package com.cloudacademy.serializable;

import java.io.Serializable;

public class CodeResponse implements Serializable {
    public String output;
   
    public CodeResponse(String output) { 
        this.output = output;
    }
}

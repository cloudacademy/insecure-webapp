package com.cloudacademy.serializable;

import java.io.Serializable;

public class AsciiResponse implements Serializable {
    public String output;
   
    public AsciiResponse(String output) { 
        this.output = output;
    }
}

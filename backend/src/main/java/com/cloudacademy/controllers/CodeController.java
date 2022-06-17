package com.cloudacademy.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import com.cloudacademy.jwt.TokenManagement;
import com.cloudacademy.serializable.CodeRequest;
import com.cloudacademy.serializable.CodeResponse;
import java.util.Base64;
import java.io.*;

@RestController
@EnableAutoConfiguration
public class CodeController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/execute", 
                    method = RequestMethod.POST, 
                    consumes = "application/json",
                    produces = "application/json") 
    CodeResponse executeCode(@RequestHeader(value="x-auth-token") String token, @RequestBody CodeRequest input) {
        System.out.println("/execute POST called...");
        System.out.println("token: " + token);
        System.out.println("input.code: " + input.code);

        TokenManagement.authenticateJWTToken(token);

        CodeResponse result = null;
        try{
			//String python = "import sys\nprint (int(sys.argv[1])+int(sys.argv[2]))\n";
			BufferedWriter out = new BufferedWriter(new FileWriter("script.py"));
			out.write(input.code);
			out.close();
			 
			ProcessBuilder pb = new ProcessBuilder("python3", "script.py");
			Process p = pb.start();
			 
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            StringBuilder sb = new StringBuilder();
            for (String line; (line = in.readLine()) != null;)
            {
                sb.append(line+"\n");
            }            

            var output = sb.toString();
            byte[] encodedBytes = Base64.getEncoder().encode(output.getBytes());
            var b64 = new String(encodedBytes);
            System.out.println(b64);
            result = new CodeResponse(b64);
		}catch(Exception e){
			System.out.println(e);
		}

        return result;
    }
}
package com.cloudacademy.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class OkController {
    @RequestMapping(value = "/ok", method = RequestMethod.GET, produces = "text/plain")
    String ok() {
        System.out.println("ok GET called...");

        return "ok!\n";
	}
}
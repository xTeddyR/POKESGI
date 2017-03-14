package com.pokesgi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @RequestMapping("/")
    public String homePage(){
        return "Hello World !";
    }
}

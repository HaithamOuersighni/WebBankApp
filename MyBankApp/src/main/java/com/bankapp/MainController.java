package com.bankapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller

public class MainController{

    @GetMapping(value="/login")
    public String showLoginPage(){
        return "login";
    }
}
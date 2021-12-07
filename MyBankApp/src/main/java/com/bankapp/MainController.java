package com.bankapp;

import com.bankapp.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;


@Controller

public class MainController{

    @GetMapping(value="/login")
    public String showLoginPage(Model model){
        model.addAttribute("user", new User());
        return "login";
    }
}
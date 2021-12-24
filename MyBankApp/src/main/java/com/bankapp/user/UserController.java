package com.bankapp.user;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
//@Controller
public class UserController {
    @Autowired private UserService service;

    @PostMapping("/users/login")
    public String loginUser(User user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message","The user has been saved successfully");
        return "redirect:/index";
    }

    @DeleteMapping("/users/disconnect")
    public void disconnectUser() {
        service.disconnect();
    }

    @GetMapping("/getAccount")
    public Account getAccount(){
        return service.getConnected();
    }

    @PostMapping("/addMoney")
    public String addMoney(){
        service.addMoney();
        return "index";
    }

    @PostMapping("/subMoney")
    public Boolean subMoney(){
        if(service.subMoney()){
            return true;
        }else{
            return false;
        }
    }
}
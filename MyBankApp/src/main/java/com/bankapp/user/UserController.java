package com.bankapp.user;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired private UserService service;

    @DeleteMapping("/users/disconnect")
    public void disconnectUser() {
        service.disconnect();
    }

    @GetMapping("/getAccount")
    public Account getAccount(){
        return service.getConnected();
    }

    @PostMapping("/addMoney")
    public void addMoney() throws JSONException {
        service.addMoney();
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
package com.bankapp.user;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandlerController implements ErrorController{
    @RequestMapping("/error")
    @ResponseBody
    public String getErrorPath() {
        return "<center><h1>Si vous êtes sur cette pages il se peut qu'il y a un problème avec votre base de données . Veuillez la supprimer et la recréer s'il vous plait</h1></center>";
    }
}
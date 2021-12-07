package com.bankapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/index")
    public String showHomePage(Model model){
        return "index";
    }
    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers=service.listAllUser();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle","Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute User user, RedirectAttributes ra){
        System.out.println(user);
        service.save(user);
        ra.addFlashAttribute("message","The user has been saved successfully");
        return "redirect:/index";
    }

    @PostMapping("/users/login")
    public String loginUser(User user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message","The user has been saved successfully");
        return "redirect:/index";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            User user=service.get(id);
            model.addAttribute("user",user);
            model.addAttribute("pageTitle", "Edit User(ID" +id+ ")");
            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message","The user ID"+id+"has been deleted");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/users";
    }
}
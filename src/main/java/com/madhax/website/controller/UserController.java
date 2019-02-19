package com.madhax.website.controller;

import com.madhax.website.domain.User;
import com.madhax.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users/user";
    }

    @GetMapping("/details/{id}")
    public String viewUserDetails(@PathVariable long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "users/details";
    }

    @GetMapping("/add")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "users/addUser";
    }

    @PostMapping("/add")
    public String saveUser(@ModelAttribute User user) {
        userService.save(user);
        return "users/details";
    }

}

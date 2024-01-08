package com.project.taskmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/createUser")
    public String createUser() {
        return "createUser";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
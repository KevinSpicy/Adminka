package com.example.demo.controllers;

import com.example.demo.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class GetAllUsers {

    @Autowired
    IUserDao jdbc;

    @GetMapping
    public String userView(Model model) {
        model.addAttribute("USERS", jdbc.getAll());
        return "users";
    }
}

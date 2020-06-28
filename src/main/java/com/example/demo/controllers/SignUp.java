package com.example.demo.controllers;

import com.example.demo.dao.IUserDao;
import com.example.demo.model.User;
import com.example.demo.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/users/new")
public class SignUp {

    @Autowired
    IUserDao jdbc;

    @Autowired
    UserValidator userValidator;

    @GetMapping
    public String viewCreator() {
        return "sign_up";
    }

    @ModelAttribute("user")
    User user() {
        return new User();
    }

    @PostMapping
    public String createUser(@ModelAttribute("password2") String pass2, @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (!user.getPassword().equals(pass2) && !bindingResult.hasFieldErrors("password")) {
            bindingResult.rejectValue("password","","Passwords mismatches");
        }

        if(bindingResult.hasErrors()) {
            return "sign_up";
        }

        user.setCreatedAt(new Date());
        jdbc.save(user);
        return "redirect:/users";
    }
}

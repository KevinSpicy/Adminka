package com.example.demo.validators;

import com.example.demo.dao.IUserDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    IUserDao userDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (user.getEmail().equals("")) {
            errors.rejectValue("email", "", "Empty email");
        }

        if (userDao.getOneByEmail(user.getEmail()) != null) {
            errors.rejectValue("email","", "Email in use");
        }

        if (userDao.getOneByLogin(user.getLogin()) != null) {
            errors.rejectValue("login","", "Login in use");
        }
    }
}

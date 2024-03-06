package com.xm.controller;

import com.xm.model.User;
import com.xm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/account/admin/add")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
}

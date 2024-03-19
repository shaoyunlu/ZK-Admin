package com.xm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xm.model.AjaxResponse;
import com.xm.model.User;
import com.xm.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/list")
    public AjaxResponse getAllUsers(@RequestParam(value = "pageNum") int page,
                                    @RequestParam(value = "pageSize") int size){
        Page<User> pageInfo = userService.getAllUsers(0 ,100);

        return AjaxResponse.ok(pageInfo);
    }
}

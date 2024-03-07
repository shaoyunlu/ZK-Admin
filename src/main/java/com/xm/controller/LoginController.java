package com.xm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xm.model.AjaxResponse;
import com.xm.model.User;

import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

    @PostMapping("/login")
    public AjaxResponse login(@RequestBody User user ,HttpSession session) {
        String sessionCaptcha = (String)session.getAttribute("captcha");
        System.out.println(session.getAttribute("captcha"));
        return new AjaxResponse(true, null);
    }
}

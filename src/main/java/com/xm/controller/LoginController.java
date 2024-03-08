package com.xm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xm.model.AjaxResponse;
import com.xm.model.User;
import com.xm.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public AjaxResponse login(@RequestBody Map<String, Object> jsonPayload ,HttpSession session) {
        String sessionCaptcha = (String)session.getAttribute("captcha");
        if (!sessionCaptcha.equals(jsonPayload.get("validateNum")) ){
            return AjaxResponse.fail("验证码不正确");
        }
        User user = userService.findByName((String)jsonPayload.get("name"));
        if (user == null){
            return AjaxResponse.fail("用户不存在");
        }
        if (!user.getPassword().equals(jsonPayload.get("password"))){
            return AjaxResponse.fail("密码不正确");
        }
        return AjaxResponse.ok(null);
    }
}

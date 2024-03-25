package com.xm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xm.model.AjaxResponse;
import com.xm.model.User;
import com.xm.util.XmConstants;

import jakarta.servlet.http.HttpSession;

@RestController
public class SessionController {
    
    @GetMapping("/session/user")
    public AjaxResponse getSessionUser(HttpSession session){

        User user = (User)session.getAttribute(XmConstants.SESSION_USER_KEY);

        return AjaxResponse.ok(user);
    }
}

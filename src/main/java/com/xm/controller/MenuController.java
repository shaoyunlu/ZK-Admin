package com.xm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xm.model.AjaxResponse;
import com.xm.model.Menu;
import com.xm.service.MenuService;

@RestController

public class MenuController {
    @Autowired MenuService menuService;

    @GetMapping("/menu/list")
    public AjaxResponse getAllMenus(){
        List<Menu> list = menuService.getAllMenus();
        return AjaxResponse.ok(list);
    }
}

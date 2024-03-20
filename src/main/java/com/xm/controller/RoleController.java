package com.xm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xm.model.AjaxResponse;
import com.xm.model.Role;
import com.xm.service.RoleService;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role/list")
    public AjaxResponse getAllRoles(@RequestParam(value = "pageNum") int page,
                                    @RequestParam(value = "pageSize") int size){
        Page<Role> pageInfo = roleService.getAllRoles(page, size);

        return AjaxResponse.ok(pageInfo);
    }
}

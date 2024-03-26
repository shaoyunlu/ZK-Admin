package com.xm.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xm.dto.UserDto;
import com.xm.model.AjaxResponse;
import com.xm.model.User;
import com.xm.service.UserService;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/list")
    public AjaxResponse getAllUsers(@ModelAttribute UserDto userDto){

        Page<User> users = userService.getAllUsers(userDto);
        Page<UserDto> userDtos = users.map(this::convertToDto);

        return AjaxResponse.ok(userDtos);
    }

    @PostMapping("/user/add")
    public AjaxResponse addUser(@RequestBody UserDto userDto) {
        
        userService.addUser(userDto);
        return AjaxResponse.ok(null);
    }

    @PostMapping("/user/delete")
    public AjaxResponse deleteUser(@RequestBody UserDto userDto) {

        userService.deleteUser(userDto);
        return AjaxResponse.ok(null);
    }

    @PostMapping("/user/delete/batch")
    public AjaxResponse batchDeleteUser(@RequestBody List<Long> idList){

        userService.batchDeleteUsers(idList);
        return AjaxResponse.ok(null);

    }

    @PostMapping("/user/update")
    public AjaxResponse updateUser(@RequestBody UserDto userDto) {
        
        userService.updateUser(userDto);
        return AjaxResponse.ok(null);
    }
    
    private UserDto convertToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setRealName(user.getRealName());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        userDto.setUpdateTime(user.getUpdateTime());
        return userDto;
    }
}

package com.xm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xm.dto.UserDto;
import com.xm.model.AjaxResponse;
import com.xm.model.User;
import com.xm.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/list")
    public AjaxResponse getAllUsers(@RequestParam(value = "pageNum") int page,
                                    @RequestParam(value = "pageSize") int size,
                                    @ModelAttribute UserDto userDto){

        Page<User> users = userService.getAllUsers(userDto ,page ,size);
        Page<UserDto> userDtos = users.map(this::convertToDto);

        return AjaxResponse.ok(userDtos);
    }

    @PostMapping("/user/add")
    public AjaxResponse addUser(@RequestBody UserDto userDto) {
        
        userService.addUser(userDto);
        return AjaxResponse.ok(null);
    }

    @PostMapping("/user/delete")
    public AjaxResponse postMethodName(@RequestBody UserDto userDto) {

        userService.deleteUser(userDto);
        return AjaxResponse.ok(null);
    }
    
    

    private UserDto convertToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        userDto.setUpdateTime(user.getUpdateTime());
        return userDto;
    }
}

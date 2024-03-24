package com.xm.service;

import com.xm.dto.UserDto;
import com.xm.model.User;
import com.xm.model.Role;
import com.xm.repository.RoleRepository;
import com.xm.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Page<User> getAllUsers(UserDto userDto ,int page ,int size){
        return userRepository.getAllWithRoles(userDto.getName() ,PageRequest.of(page ,size));
    }

    @Transactional
    public void addUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        if (userDto.getRoleIds() != null && !userDto.getRoleIds().isEmpty()){
            Set<Role> roles = new HashSet<>(roleRepository.findAllById(userDto.getRoleIds()));
            user.setRoles(roles);
        }

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(UserDto userDto){
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + userDto.getId()));
        user.getRoles().clear();
        userRepository.save(user);

        userRepository.deleteById(userDto.getId());
    }

}

package com.xm.service;

import com.xm.model.User;
import com.xm.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Page<User> getAllUsers(int page ,int size){
        return userRepository.getAllWithRoles(PageRequest.of(page ,size));
    }

}

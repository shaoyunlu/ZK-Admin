package com.xm.service;

import com.xm.model.User;
import com.xm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    

}

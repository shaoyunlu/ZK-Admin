package com.xm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xm.model.Role;
import com.xm.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Page<Role> getAllRoles(int page ,int size){
        return roleRepository.findAll(PageRequest.of(page ,size));
    }

}

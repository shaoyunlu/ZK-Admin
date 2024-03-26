package com.xm.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xm.dto.UserDto;
import com.xm.model.Role;
import com.xm.model.User;
import com.xm.repository.RoleRepository;
import com.xm.repository.UserRepository;
import com.xm.specification.UserSpecification;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User findByName(String name){
        return userRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Page<User> getAllUsers(UserDto userDto){
        Pageable pageable = PageRequest.of(userDto.getPageNum(), userDto.getPageSize());

        if (userDto.getSortDirection() != null && !userDto.getSortDirection().isEmpty() && userDto.getSortBy() != null && !userDto.getSortBy().isEmpty()) {
            Sort sort = "ascending".equalsIgnoreCase(userDto.getSortDirection()) ? 
                Sort.by(userDto.getSortBy()).ascending() : 
                Sort.by(userDto.getSortBy()).descending();
            pageable = PageRequest.of(userDto.getPageNum(), userDto.getPageSize(), sort);
        } else {
            pageable = PageRequest.of(userDto.getPageNum(), userDto.getPageSize());
        }

        Specification<User> spec = Specification.where(UserSpecification.nameOrRealNameContains(userDto.getName()))
                                                        .and(UserSpecification.nameIsNotAdmin());
        return userRepository.findAll(spec, pageable);
    }

    @Transactional
    public void addUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setRealName(userDto.getRealName());
        user.setEmail(userDto.getEmail());
        user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));

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

    @Transactional
    public void batchDeleteUsers(List<Long> userIds) {
        for (Long userId : userIds) {
            UserDto userDto = new UserDto();
            userDto.setId(userId);
            deleteUser(userDto); // 通过自引用调用，确保@Transactional生效
        }
    }

    @Transactional
    public void updateUser(UserDto userDto){
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userDto.getId()));

        user.setName(userDto.getName());
        user.setRealName(userDto.getRealName());
        user.setEmail(userDto.getEmail());

        if (userDto.getRoleIds() != null && !userDto.getRoleIds().isEmpty()){
            Set<Role> roles = new HashSet<>(roleRepository.findAllById(userDto.getRoleIds()));
            user.setRoles(roles);
        }

        userRepository.save(user);
    }

}

package com.xm.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xm.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    @Query("SELECT u FROM User u JOIN FETCH u.roles")
    Page<User> getAllWithRoles(Pageable pageable);

}
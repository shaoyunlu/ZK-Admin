package com.xm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xm.model.Menu;
import com.xm.repository.MenuRepository;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus(){
        return menuRepository.findAll();
    }
    
}

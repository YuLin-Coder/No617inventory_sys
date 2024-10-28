package com.inventory.service.impl;

import com.inventory.dao.AdminDao;
import com.inventory.entity.Admin;
import com.inventory.service.AdminService;

import java.util.List;
import java.util.Map;


public class AdminServiceImpl implements AdminService {

    AdminDao dao = new AdminDao();


    @Override
    public void updateAdminPassword(String newpass, Integer id) {
        dao.updateAdminPassword(newpass,id);
    }

    @Override
    public Admin selectAdmin(String username, String password) {
        return  dao.selectAdmin(username,password);
    }
}

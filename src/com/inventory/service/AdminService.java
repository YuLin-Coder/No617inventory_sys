package com.inventory.service;


import com.inventory.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {

    void updateAdminPassword(String newpass, Integer id);

    Admin selectAdmin(String username, String password);
}

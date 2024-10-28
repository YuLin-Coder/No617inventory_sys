package com.inventory.dao;


import com.inventory.entity.Admin;
import com.inventory.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminDao {

    private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

    public void updateAdminPassword(String newpass, Integer id) {
        try {//执行更改
            runner.update("update admin set pwd=? where id=?",
                    newpass,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    public Admin selectAdmin(String username, String password) {
        try {//返回查询的信息
            return runner.query("select * from admin where username=? and pwd =? ", new BeanHandler<Admin>(Admin.class),
                    username,password
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }
}

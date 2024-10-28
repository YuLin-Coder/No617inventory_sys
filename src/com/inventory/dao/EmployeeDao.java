package com.inventory.dao;


import com.inventory.entity.Employee;
import com.inventory.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EmployeeDao {

    private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

    public Employee findEmployeeById(String id) {
        try {//返回查询的信息
            return runner.query("select * from employee where id=? ", new BeanHandler<Employee>(Employee.class),Integer.parseInt(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    public void deleteEmployee(String id) {
        Integer ids = Integer.parseInt(id);
        try {//执行更改
            runner.update("delete from  employee  where id=?",
                    ids);
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    public Employee selectEmployeeByKey(Employee employee) {
        try {//返回查询的信息
            return runner.query("select * from employee where eno=?  ", new BeanHandler<Employee>(Employee.class),
                    employee.getEno());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    public void addEmployee(Employee employee) {
        try {
            //执行插入sql
            runner.update("insert into employee(eno,realname,phone,pwd,sex,work,role) values (?,?,?,?,?,?,?)",
                    employee.getEno(),employee.getRealname(),employee.getPhone()
            ,employee.getPwd(),employee.getSex(),employee.getWork(),employee.getRole());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> getEmployeePage(int pageNum, int pageSize, Map map) {
        String sql="select * from employee where 1 = 1 ";
        if(map.get("key") !=null && !map.get("key").toString().equals("")){
            sql+= " and realname like  '%"+map.get("key").toString()+"%'";
        }
        if(map.get("uid") !=null && !map.get("uid").toString().equals("")){
            sql+= " and id = "+map.get("uid");
        }
        sql+=" limit ?,? ";
        int startNo=(pageNum-1)*pageSize;
        List<Employee> list=null;
        try {
            list= runner.query(sql, new BeanListHandler<Employee>(Employee.class),new Object[] {startNo,pageSize});//添加实体类的适配器进行类的反射
            return list;
        } catch (SQLException e) {//捕获异常
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    public int queryEmployeeCount(Map map) {
        String sql="select count(*) from employee where 1 = 1  ";
        if(map.get("key") !=null && !map.get("key").toString().equals("")){
            sql+= " and realname like  '%"+map.get("key").toString()+"%'";
        }
        if(map.get("uid") !=null && !map.get("uid").toString().equals("")){
            sql+= " and id = "+map.get("uid");
        }
        try {
            Long count =  (Long) runner.query(sql, new ScalarHandler());
            //将long的类型转成int类型
            return count.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateEmployee(Employee employee) {
        try {//执行更改
            runner.update("update employee set realname=?,phone=?,pwd=?,sex=?,work=? where id=?",
                    employee.getRealname(),employee.getPhone()
                    ,employee.getPwd(),employee.getSex(),employee.getWork(),employee.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    public Employee selectEmployee(String username, String password,String role) {
        try {//返回查询的信息
            return runner.query("select * from employee where eno=? and pwd =? and role =?  ",
                    new BeanHandler<Employee>(Employee.class),
                    username,password,role
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }
}

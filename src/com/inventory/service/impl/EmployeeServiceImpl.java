package com.inventory.service.impl;

import com.inventory.dao.EmployeeDao;
import com.inventory.entity.Employee;
import com.inventory.service.EmployeeService;

import java.util.List;
import java.util.Map;


public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao dao = new EmployeeDao();

    @Override
    public Employee findEmployeeById(String id) {
        return dao.findEmployeeById(id);
    }

    @Override
    public void deleteEmployee(String id) {
        dao.deleteEmployee(id);
    }

    @Override
    public Employee selectEmployeeByKey(Employee employee) {
        return   dao.selectEmployeeByKey(employee);
    }

    @Override
    public void addEmployee(Employee employee) {
        dao.addEmployee(employee);
    }

    @Override
    public List<Employee> getEmployeePage(int pageNum, int pageSize, Map map) {
        return dao.getEmployeePage(pageNum, pageSize, map) ;
    }

    @Override
    public int queryEmployeeCount(Map map) {
        return  dao.queryEmployeeCount(map);
    }

    @Override
    public void updateEmployee(Employee employee) {
        dao.updateEmployee(employee);
    }


    @Override
    public Employee selectEmployee(String username, String password,String type) {
        return  dao.selectEmployee(username,password,type);
    }
}

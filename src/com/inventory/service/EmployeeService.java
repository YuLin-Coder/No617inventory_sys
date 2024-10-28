package com.inventory.service;


import com.inventory.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee findEmployeeById(String id);

    void deleteEmployee(String id);

    Employee selectEmployeeByKey(Employee employee);

    void addEmployee(Employee employee);

    List<Employee> getEmployeePage(int pageNum, int pageSize, Map map);

    int queryEmployeeCount(Map map);

    void updateEmployee(Employee employee);


    Employee selectEmployee(String username, String password,String type);
}

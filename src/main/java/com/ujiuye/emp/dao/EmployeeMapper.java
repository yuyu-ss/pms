package com.ujiuye.emp.dao;

import com.ujiuye.emp.bean.Employee;

import java.util.List;


public interface EmployeeMapper {

    public List<Employee> getEmployeeList();

    List<Employee> getManagerList();

    Employee getEmployeeById(Integer eid);
}

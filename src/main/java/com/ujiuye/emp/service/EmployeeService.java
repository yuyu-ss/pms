package com.ujiuye.emp.service;

import com.ujiuye.emp.bean.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeeList();

    List<Employee> getManagerList();
}

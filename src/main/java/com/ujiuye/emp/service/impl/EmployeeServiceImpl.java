package com.ujiuye.emp.service.impl;

import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.dao.EmployeeMapper;
import com.ujiuye.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;


    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = employeeMapper.getEmployeeList();
        return employeeList;
    }


    public List<Employee> getManagerList() {
        return employeeMapper.getManagerList();
    }
}

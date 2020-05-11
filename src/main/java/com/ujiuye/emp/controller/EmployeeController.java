package com.ujiuye.emp.controller;

import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getEmployeeList(){
        List<Employee>  list = employeeService.getEmployeeList();
        return list;
    }
}

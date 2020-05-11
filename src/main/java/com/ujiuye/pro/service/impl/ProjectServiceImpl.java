package com.ujiuye.pro.service.impl;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.dao.CustomerMapper;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.dao.EmployeeMapper;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.dao.ProjectMapper;
import com.ujiuye.pro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    public void saveInfo(Project project) {
        project.setEmpFk(1);
        projectMapper.insert(project);
    }

    public List<Project> jsonList() {
        ProjectExample example = new ProjectExample();
        List<Project> list = projectMapper.selectByExample(example);

        for (Project project : list) {
            Integer cid = project.getComname();
            Customer customer= customerMapper.selectByPrimaryKey(cid);
            project.setCustomer(customer);
            Integer empFk = project.getEmpFk();
            Employee employee=employeeMapper.getEmployeeById(empFk);
            project.setEmployee(employee);
        }
        return list;
    }

    public boolean delete(int[] ids) {
        int id=0;
        for (int ida:ids){
            projectMapper.deleteByPrimaryKey(ida);
            id++;
        }
        System.out.println(ids.length == id);
        return ids.length == id;
    }
}

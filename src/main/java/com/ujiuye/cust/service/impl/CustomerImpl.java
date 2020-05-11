package com.ujiuye.cust.service.impl;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.dao.CustomerMapper;
import com.ujiuye.cust.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Date;
import java.util.List;


@Service
public class CustomerImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public void saveInfo(Customer customer) {
        customer.setAddtime(new Date());
        int insert = customerMapper.insert(customer);

    }

    public List infoList() {
        List<Customer> customers = customerMapper.getCustomerList();
            return customers;
    }

    public Customer edit(int id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        return customer;
    }

    public void edit(Customer customer) {
        customer.setAddtime(new Date());
        customerMapper.updateByPrimaryKey(customer);
    }

    public Customer detail(int id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        return customer;
    }

    public List<Customer> search(int cid, String keyword, int orderby) {
        return  customerMapper.search(cid,keyword,orderby);
    }

    public boolean delete(int ids) {
        String str = Integer.toString(ids);
        String[] arr1 = str.split(",");
        int a=0;
        for (int i = 0; i < arr1.length; i++) {
            int anInt=Integer.parseInt(arr1[i]);
            customerMapper.deleteByPrimaryKey(anInt);
            a++;
        }
       return arr1.length == a;
    }

}

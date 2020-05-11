package com.ujiuye.cust.service;

import com.ujiuye.cust.bean.Customer;

import java.util.List;

public interface CustomerService {

    void saveInfo(Customer customer);

    List infoList();
   Customer edit(int id);
   void edit(Customer customer);
   Customer detail(int id);
   List<Customer> search(int cid,String keyword,int orderby);

    boolean delete(int ids);

}

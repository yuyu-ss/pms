package com.ujiuye.cust.dao;

import java.util.List;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.bean.CustomerExample;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    int countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExample(Customer example);

    Customer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    List getCustomerList ();

    List<Customer> search(@Param("cid")int cid,@Param("keyword")String keyword,@Param("orderby")int orderby);
}
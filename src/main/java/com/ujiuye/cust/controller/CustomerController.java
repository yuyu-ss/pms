package com.ujiuye.cust.controller;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/Customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Customer customer){
        customerService.saveInfo(customer);
        return "redirect:/Customer/list";
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView infoList(){
        List<Customer> list = customerService.infoList();
        ModelAndView view = new ModelAndView("customer");
        view.addObject("list",list);
        return view;
    }
    @RequestMapping(value = "edit/{id}")
    public String edit(@PathVariable("id")int id, Map<String,Object> map){

        Customer customer=customerService.edit(id);
        map.put("customer",customer);
        return "customer-edit";

    }
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public String edit(Customer customer){
        customerService.edit(customer);
        return "redirect:/Customer/list";
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") int id){
        Customer customer = customerService.detail(id);
            ModelAndView view=new ModelAndView("customer-look");
            view.addObject("customer",customer);
            return view;
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ModelAndView search(int cid,String keyword,int orderby){
        List<Customer> search = customerService.search(cid, keyword, orderby);
        ModelAndView view =new ModelAndView("customer");
        view.addObject("list",search);
        return view;
    }

    @RequestMapping(value="/delete/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("ids") int ids){
        Map<String,Object> map=new HashMap<String, Object>();
        boolean delete = customerService.delete(ids);
        if (delete){
            map.put("statuscode",200);
            map.put("message","删除成功");
        }else{
            map.put("statuscode",500);
            map.put("message","删除失败");
        }
        return map;
    }
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> jsonList(){
        List<Customer> list = customerService.infoList();
        return list;
    }
}

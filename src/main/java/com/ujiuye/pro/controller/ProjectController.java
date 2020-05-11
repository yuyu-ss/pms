package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(value="/saveinfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveInfo(Project project,String comnamewithid){
        String cid=comnamewithid.split(",")[1];
        project.setComname(Integer.valueOf(cid));
        projectService.saveInfo(project);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("statusCode",200);
        map.put("message","成功");
        return map;
    }

    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getJsonList(){
        return projectService.jsonList();
    }

    @RequestMapping(value="/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> delete(@RequestParam("ids[]") int[] ids){

        boolean delete1 = projectService.delete(ids);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("statuCode",200);
        map.put("message","删除失败");
        return map;
    }
}

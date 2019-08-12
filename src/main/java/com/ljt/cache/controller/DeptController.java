package com.ljt.cache.controller;/**
 * @author yonrun
 * @Title: DeptController
 * @ProjectName springboot_cache_02
 * @Description: TODO
 * @date 2019/8/9 18:21
 */

import com.ljt.cache.entity.Department;
import com.ljt.cache.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @description
 * @date 2019/8/9
 */

@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id){
        return deptService.getDepartmentById(id);

    }
}

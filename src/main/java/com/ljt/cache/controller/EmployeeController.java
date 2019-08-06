package com.ljt.cache.controller;/**
 * @author yonrun
 * @Title: EmployeeController
 * @ProjectName springboot_cache_02
 * @Description: TODO
 * @date 2019/8/5 21:10
 */

import com.ljt.cache.entity.Employee;
import com.ljt.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @description
 * @date 2019/8/5
 */
@RestController
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        Employee employee=employeeService.getEmp(id);
        return employee;//返回格式：json

    }

    @GetMapping("/emp")
    public Employee update(Employee employee){
        Employee employee1=employeeService.updateEmp(employee);
        return employee1;
    }


    @GetMapping("/delemp")
    public String deleteEmp(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }
}

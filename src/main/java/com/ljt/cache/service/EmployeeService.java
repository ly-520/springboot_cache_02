package com.ljt.cache.service;/**
 * @author yonrun
 * @Title: EmployeeService
 * @ProjectName springboot_cache_02
 * @Description: TODO
 * @date 2019/8/5 21:07
 */

import com.ljt.cache.entity.Employee;
import com.ljt.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @description
 * @date 2019/8/5
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工号");
        Employee emp=employeeMapper.getEmpById(id);
        return emp;
    }
}

package com.ljt.cache.service;

import com.ljt.cache.entity.Department;
import com.ljt.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author yonrun
 * @Title: DeptService
 * @ProjectName springboot_cache_02
 * @Description: TODO
 * @date 2019/8/9 18:14
 */
@Service
public class DeptService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Cacheable(cacheNames = "dept",cacheManager ="deptCacheManager" )
    public Department getDepartmentById(Integer id){
        Department department=departmentMapper.getDepartById(id);
        return department;

    }
}

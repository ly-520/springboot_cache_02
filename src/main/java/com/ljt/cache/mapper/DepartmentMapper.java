package com.ljt.cache.mapper;

/**
 * @author yonrun
 * @Title: DepartmentMapper
 * @ProjectName springboot_cache_01
 * @Description: TODO
 * @date 2019/8/5 19:46
 */

import com.ljt.cache.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id=#{id}")
    Department getDepartById(Integer id);
}

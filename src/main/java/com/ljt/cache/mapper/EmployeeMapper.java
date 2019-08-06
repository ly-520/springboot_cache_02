package com.ljt.cache.mapper;

import com.ljt.cache.entity.Employee;
import org.apache.ibatis.annotations.*;

/**
 * @author yonrun
 * @Title: EmployeeMapper
 * @ProjectName springboot_cache_01
 * @Description: TODO
 * @date 2019/8/5 19:46
 */
@Mapper
public interface EmployeeMapper {


    @Select("SELECT * FROM employee WHERE id=#{id}")
    public Employee getEmpById(Integer id);

    @Update("UPDATE employee SET last_name=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    public void updateEmp(Employee employee);

    @Delete("DELETE FROM employee WHERE id=#{id}")
    public void deleteEmpById(Integer id);

    @Insert("INSERT INTO employee(last_name,email,gender,d_id) VALUES(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);

}

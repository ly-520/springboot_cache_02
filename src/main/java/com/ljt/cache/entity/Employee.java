package com.ljt.cache.entity;/**
 * @author yonrun
 * @Title: Employee
 * @ProjectName springboot_cache_01
 * @Description: TODO
 * @date 2019/8/5 19:39
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @author
 * @description
 * @date 2019/8/5
 */
@Data
public class Employee implements Serializable {
    private int id;
    private String lastName;
    private String email;
    private String gender;
    private int dId;
}

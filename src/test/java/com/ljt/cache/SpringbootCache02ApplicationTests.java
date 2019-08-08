package com.ljt.cache;

import com.ljt.cache.entity.Employee;
import com.ljt.cache.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCache02ApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate; //操作k-v字符串类型

    @Autowired
    RedisTemplate redisTemplate;  //k-v都是对象类型的

    @Autowired
    RedisTemplate<Object,Employee> employeeRedisTemplate;

    /**
     * String （字符串）、List(列表)、Set(集合)、Hash(散列)、ZSet(有序集合)
     * stringRedisTemplate.opsForValue(); {String (字符串)}
     * stringRedisTemplate.opsForList();{List(列表)}
     * stringRedisTemplate.opsForSet();  {Set(集合)}
     * stringRedisTemplate.opsForHash(); {Hash(散列)}
     * stringRedisTemplate.opsForZSet();{ZSet(有序集合)}
     */
    @Test
    public void test(){
        //向redis中保存数据
        //stringRedisTemplate.opsForValue().append("msg","liu");
        //获取key的值
        /*String msg=stringRedisTemplate.opsForValue().get("msg");
        log.info("msg:"+msg);
        System.out.println(msg);*/

        stringRedisTemplate.opsForList().leftPush("mylist","1");
        stringRedisTemplate.opsForList().leftPush("mylist","2");
        stringRedisTemplate.opsForList().leftPush("mylist","3");
        stringRedisTemplate.opsForList().leftPush("mylist","4");
        stringRedisTemplate.opsForList().leftPush("mylist","5");
    }

    @Test
    public void test_02(){
        Employee employee=employeeMapper.getEmpById(1);
        //如果保存数据，默认使用jdk序列化机制，序列化后的数据保存到redis中
       // redisTemplate.opsForValue().set("employee",employee);

        //1、将数据以json的方式
        //(1)自己将对象转为json
        //(2)redisTemplate默认的序列化规则;改变默认的序列化规则
        employeeRedisTemplate.opsForValue().set("emp_01",employee);
    }


    @Test
    public void contextLoads() {
        Employee employee=employeeMapper.getEmpById(1);
        System.out.println(employee.toString());
    }

}

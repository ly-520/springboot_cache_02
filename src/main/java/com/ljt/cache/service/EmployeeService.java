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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author
 * @description
 * @date 2019/8/5
 */
//@CacheConfig(cacheNames = "emp")   //抽取缓存的公共配置类中的缓存名：如下列的方法注解中共有的value值
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
      * 将方法的运行结果进行缓存，以后再使用相同的数据，直接从缓存中获取，不用调用方法；
     *
     *
     *      原理：
     *       1、自动配置类：CacheAutoConfiguration
     *       2、缓存配置类
     *            org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     *            org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     *            org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     *            org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     *            org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     *            org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     *            org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     *            org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     *            org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
     *            org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration【默认】
     *            org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     *
     *
     *
     *            默认生效的是SimpleCacheConfiguration
     *
     *
     *       3、给容器中注册了一个CacheManager:ConcurrentMapCacheManager
     *       4、可以获取和创建ConcurrentMapCache类型的缓存组件.
     *
     *   运行流程：
     *      @Cacheable
     *       1、方法运行之前，先去查询Cache（缓存组件），按照cachenames指定的名字获取；
     *          （CacheManager先获取相应的缓存），第一次获取缓存如果没有,cache组件会自动创建。
     *       2、去Cache中查找缓存的内容，使用一个key,默认就是方法的参数；
     *           key是按照某种策略生成的；默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key
     *       3、没有查到缓存就调用目标方法；
     *       4、将目标方法返回的结果，放进缓存中
     *
     * @Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数胡值作为key
     * 去查询缓存， 如果没有就运行方法并将结果放入缓存；
     *
     *
     * 几个属性：
     *      *      cacheNames/value:指定缓存组件的名字   ：将方法的返回结果放在哪个缓存中，以数组的方式，可以指定多个缓存；
     *      *      key：缓存数据使用的key；可以用它指定，默认是使用方法参数的值
     *      *             编写SpEL表达式：#id；参数id的值  #
     *      *      KeyGenerator: key的生成器，可以自己指定key的生成器的组件id
     *      *             key/keyGenerator:二选一使用
     *      *      cacheManager:指定缓存管理器；或者cacheresoulver指定获取解析器
     *      *      condition :指定符合条件下的情况才缓存
     *      *      unless：否定缓存；当unless指定的条件为true,方法的返回值就不会被缓存；可以获取到结果进行判断
     *      *      sync:是否使用异步模式
     */

    //@Cacheable(value = {"emp"})等同于@Cacheable(cacheNames = {"emp"})
    @Cacheable(value = {"emp"}/*,condition = "#id>1",unless = "#a0==2"*/)  //等同于@Cacheable(cacheNames = {"emp"})
    public Employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工号");
        Employee emp=employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * @CachePut :既修改数据，又更新缓存数据，使两者保持同步
     * 注意：需要让修改后的key值和查询的key值保持为同一个，（cache默认key为返回的对象）
     *运行时机：
     * 1.先调用目标方法
     * 2.将目标方法的结果缓存起来
     */
    @CachePut(value="emp",key="#result.id")
    public Employee updateEmp(Employee employee){
        System.out.println("员工更新："+employee);
       employeeMapper.updateEmp(employee);
       return employee;
    }

    /**
     * @CacheEvict：缓存清除
     * key:指定要清楚的数据
     * allEntries=true 删除emp模块中的所有缓存数据
     * beforeInvocation= false :缓存的清除是否在方法之前执行
     */
    //@CacheEvict(value="emp",allEntries=true)
    @CacheEvict(value="emp",key = "#id")
    public void deleteEmp(Integer id){

        System.out.println("deleteEmp:"+id);
        //employeeMapper.deleteEmpById(id);
    }
}

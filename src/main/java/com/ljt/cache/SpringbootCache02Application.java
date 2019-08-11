package com.ljt.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 一、搭建基本环境
 *    1、创建数据库文件
 *    2、创建javaBean封装数据
 *    3.整合Mybatis操作数据库
 *           1、配置数据源信息
 *           2.使用注解版的Mybatis
 *               (1)@MapperScan指定要扫描的mapper接口所在的包
 * 二、快速体验缓存
 *     步骤：
 *          1.开启基于注解的缓存  @EnableCaching
 *          2.标注缓存注解即可
 *              @Cacheable
 *              @CacheEvict
 *              @CachePut
 *  默认使用的是ConcurrentMapCacheManager==ConcurrentMapCache  将数据保存在ConcurrentMap<Object,object>
 *   开发中使用缓存中间件：redis、memcached、ehcache;
 *
 *   三、整合redis作为缓存
 *    Redis是一个开源（BDS许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件
 *    1、服务器安装redis
 *    2、引入redis的starter
 *    3、配置redis
 *    4、测试缓存
 *          原理：Cachemanager===Cache缓存组件来实际给缓存中存取数据
 *    四、整个消息中间件RabbitMQ
 *      自动配置：
 *      1、RabbitAutoConfiguration
 *      2、有自动配置的连接工厂ConnectionFactory
 *      3、RabbitProperties 封装了rabbitmq 的配置
 *      4、RabbitMQTemplate:给RabbitMQ发送消息和接收消息
 *      5、AmqpAdmin :RabbitMQ系统管理功能组件（不用与发接收消息，而是用于创建队列和交换器等）
 *      6、
 *
 */
@MapperScan("com.ljt.cache.mapper")
@SpringBootApplication
//@EnableCaching
@EnableScheduling
public class SpringbootCache02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCache02Application.class, args);
    }

}

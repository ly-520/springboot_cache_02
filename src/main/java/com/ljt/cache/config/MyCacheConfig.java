package com.ljt.cache.config;/**
 * @author yonrun
 * @Title: MyCacheConfig
 * @ProjectName springboot_cache_02
 * @Description: TODO
 * @date 2019/8/6 17:13
 */

import org.springframework.context.annotation.Configuration;

import javax.crypto.KeyGenerator;

/**
 * @author
 * @description
 * @date 2019/8/6
 */
@Configuration
public class MyCacheConfig {
    public KeyGenerator keyGenerator(){
       /* new KeyGenerator (){    //匿名内部类

            public java.lang.Object generate(java.lang.Object target, Method method, java.lang.Object... params){
                return method.getName()+"["+ Arrays.asList(params).toString()+"]";
            }

        };*/
        return null;
    }
}

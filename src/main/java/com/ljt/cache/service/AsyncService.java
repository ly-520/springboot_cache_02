package com.ljt.cache.service;/**
 * @author yonrun
 * @Title: AsyncService
 * @ProjectName springboot_cache_02
 * @Description: TODO
 * @date 2019/8/21 18:49
 */

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author
 * @description
 * @date 2019/8/21
 */
@Service
public class AsyncService {


    @Async     //异步标注
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }
}

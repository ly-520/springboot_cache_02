package com.ljt.cache.service;/**
 * @author yonrun
 * @Title: Scheduled
 * @ProjectName springboot_cache_02
 * @Description: TODO
 * @date 2019/8/21 19:06
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author
 * @description
 * @date 2019/8/21
 */
@Service
public class ScheduledService {


    /**
     * second(秒)， minute(分)， hour(时),day of month(日),month(月),day of week(周几)
     *
     *" 0 * * * * MON-SAT"  从右到左读取：周一至周五每一月每一日每一时每一分
     *
     *  枚举 ","     "0,1,2,3,4,5 * * * * MON-SAT"
     *  区间 "-"     "0-5 * * * * MON-SAT"      0-5秒每秒都执行
     *  步长 "/"      "0/5 * * * * MON-SAT"     每隔五秒执行一次（不是从0开始）
     *
     */
    @Scheduled(cron = "0/5 * * * * MON-SAT")
    public void  hello(){
        System.out.println("hello......");
    }
}

package com.ljt.cache.controller;/**
 * @author yonrun
 * @Title: AsyncController
 * @ProjectName springboot_cache_02
 * @Description: TODO
 * @date 2019/8/21 18:51
 */

import com.ljt.cache.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @description
 * @date 2019/8/21
 */
@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;
    @RequestMapping("/helloAsync")
    public String asyncTest(){
        asyncService.hello();
        return "success";
    }
}

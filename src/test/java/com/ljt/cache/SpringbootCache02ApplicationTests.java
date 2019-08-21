package com.ljt.cache;

import com.ljt.cache.entity.Book;
import com.ljt.cache.entity.Employee;
import com.ljt.cache.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    RabbitTemplate rabbitTemplate;

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


    //单播模式（点对点）：将一个消息发布到队列里面
    @Test
    public void testRabbitMQ(){
        //Message需要自己构造一个；定制消息体内容和消息头
        //rabbitTemplate.send(exchange,routKey,message);

        //Object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
        //rabbitTemplate.convertAndSend(exchange,routKey,Object);
        Map<String,Object> map=new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("helloworld",1234,true));
        //对象被序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news", new Book("西游记","吴承恩"));
    }


    //接收消息,如何将数据转为json
    @Test
    public void reviceMessage(){

        //receice 可以接受消息，receiveAndConvert接收并转化
        Object o=rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    //广播
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("测试","liu罗贯中"));

    }


    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    public void sendQQEmailMsg(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件设置
        simpleMailMessage.setSubject("通知-今晚开会");
        simpleMailMessage.setText("刘先生进行月总结讲话");
       // simpleMailMessage.setTo("2428778405@qq.com");
        simpleMailMessage.setTo("15036890387@163.com");
        simpleMailMessage.setFrom("2022290377@qq.com");
        javaMailSender.send(simpleMailMessage);
    }

    @Test
    public void sendHightQQEmailMsg()throws Exception{
        //1、创建一个复杂的消息邮件
        MimeMessage mimeMessage= javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);

        //邮件设置
        helper.setSubject("高级邮件：今晚谈理想");
        helper.setText("<b>测试测试测试测试测试测试测试测试</b>",true);

        helper.setTo("15036890387@163.com");
        helper.setTo("2428778405@qq.com");
        helper.setFrom("2022290377@qq.com");

        //上传文件
        helper.addAttachment("1.png",new File("E:\\img\\1.png"));
        helper.addAttachment("2.png",new File("E:\\img\\2.png"));
        javaMailSender.send(mimeMessage);
    }
}

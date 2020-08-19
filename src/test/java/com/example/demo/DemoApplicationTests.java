package com.example.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    public void test(){
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getMinIdle());
    }

    @Test
    public void contextLoads() {
        Map<String,Object> map = new HashMap<>();
        map.put("msg","消息1");
        rabbitTemplate.convertAndSend("exchange.direct","atguigu",map);
    }

    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu");
        System.out.println(o);
    }

    @Test
    public void createQueue(){
        amqpAdmin.declareQueue(new Queue("TestQueue"));
    }



}

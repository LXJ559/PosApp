package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RabbitmqConfig {

    /*
     加这个配置是改变消息的序列化机制为json,默认是java,可以不加，
     不影响发送接收消息，只是影响查看
    */
    @Resource
    AmqpAdmin amqpAdmin;

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /*
    * 创建队列，默认是持久化的
    * */
    @Bean
    public void creatQueue(){
        amqpAdmin.declareQueue(new Queue("pos.news"));
        amqpAdmin.declareQueue(new Queue("pos.topic"));
    }

    /*
    * 创建交换机，默认也是持久化的
    * */
    @Bean
    public void createExchange(){
        amqpAdmin.declareExchange(new FanoutExchange("pos.fanout"));
        amqpAdmin.declareExchange(new TopicExchange("pos.topic"));
    }

    /*
    * 创建绑定关系
    * */
    @Bean
    public void binding(){
        amqpAdmin.declareBinding(new Binding("pos.news",Binding.DestinationType.QUEUE,
                "pos.fanout","pos.*",null));
        amqpAdmin.declareBinding(new Binding("pos.topic",Binding.DestinationType.QUEUE,
                "pos.fanout","abd",null));
        amqpAdmin.declareBinding(new Binding("pos.news",Binding.DestinationType.QUEUE,
                "pos.topic","*.topic",null));
        amqpAdmin.declareBinding(new Binding("pos.topic",Binding.DestinationType.QUEUE,
                "pos.topic","*.topic",null));

    }

    /*只能创建单个，比较麻烦，上述每个bean可创建多个
    @Bean
    public Queue TestDirectQueue() {
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("TestDirectQueue",true);
    }

    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange TestDirectExchange() {
        return new DirectExchange("TestDirectExchange",true,false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }
*/
}

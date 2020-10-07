package gzhu.edu.cn.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: exam
 * @description: rabbit配置
 * @author: 丁国柱
 * @create: 2020-10-07 18:14
 */
@Configuration
public class RabbitMqConfig {


    /**
     * 1.创建队列
     * @return
     */
    @Bean
    public Queue judgeQueue(){
        return QueueBuilder.durable("judge_queue").build();
    }

    @Bean
    public Exchange judgeExchange(){
        return new TopicExchange("JUDGE-EXCHANGE-1", true, false);
    }

   /* *//**
     * 2.创建交换机
     * @return
     *//*
    @Bean
    public Exchange judgeTopicExchange(){
        return ExchangeBuilder.topicExchange("judge_topic_exchange").build();
    }

    @Bean
    public Binding judgeQueueExchange(Queue queue,Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("judge.*").noargs();
    }*/

}
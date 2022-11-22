package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 *
 * @author stream
 */
@Configuration
public class RabbitmqConfig {

    /**
     * 队列 queue
     */
    public static final String QUEUE = "demo-queue";

    /**
     * 交换机 exchange
     */
    public static final String EXCHANGE = "demo-exchange";

    /**
     * 路由键 routingkey
     * #表示0或多个单词
     * *表示一个单词
     */
    public static final String ROUTING_KEY = "key.#";

    @Bean
    public Queue secKillQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public DirectExchange secKillExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingSecKill() {
        return BindingBuilder.bind(secKillQueue()).to(secKillExchange()).with(ROUTING_KEY);
    }

}

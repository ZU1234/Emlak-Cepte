package com.emlakcepte.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmlakcepteRealtyQueue {
    private String queueName = "emlakcepte.notification.status";
    private String exchange = "emlakcepte.notification.status";

    @Bean
    public Queue realtyQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    public DirectExchange realtyExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding realtybinding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("");
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}


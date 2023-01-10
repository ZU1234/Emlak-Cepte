package com.emlakcepte.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmlakcepteInvoiceQueue {
    private String queueName = "emlakcepte.notification.invoice";

    private String exchange = "emlakcepte.notification.invoice";

    @Bean
    public Queue invoiceQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    public DirectExchange invoiceExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding invoiceBinding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("");
    }


/*
   @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate =new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
**/

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

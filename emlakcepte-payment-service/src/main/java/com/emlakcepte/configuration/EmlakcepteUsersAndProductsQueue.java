package com.emlakcepte.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmlakcepteUsersAndProductsQueue {
   // @Value("${rabbitmq.queue}")
    private String queueName="emlakcepte.notification.usersAndProducts";

   //@Value("${rabbitmq.exchange}")
    private String exchange="emlakcepte.notification.usersAndProducts";



    @Bean
    public Queue usersAndProductsQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    public DirectExchange usersAndProductsExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding usersAndProductsBinding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("");
    }
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
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

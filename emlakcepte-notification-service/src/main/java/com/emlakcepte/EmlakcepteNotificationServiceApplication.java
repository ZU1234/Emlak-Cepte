package com.emlakcepte;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class EmlakcepteNotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmlakcepteNotificationServiceApplication.class, args);
    }

}

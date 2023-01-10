package com.emlakcepte;

import com.emlakcepte.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@EnableEurekaClient
public class EmlakcepteserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmlakcepteserviceApplication.class, args);
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

}

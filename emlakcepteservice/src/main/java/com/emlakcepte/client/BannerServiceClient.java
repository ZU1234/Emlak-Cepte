package com.emlakcepte.client;


import com.emlakcepte.client.model.Banner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value="accepted-banner-service",url="http://localhost:8889")
public interface BannerServiceClient {
    @PostMapping(value = "/banner")
    void create(@RequestBody Banner banner);
}

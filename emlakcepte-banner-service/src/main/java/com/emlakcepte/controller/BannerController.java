package com.emlakcepte.controller;

import com.emlakcepte.model.Banner;
import com.emlakcepte.service.BannerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @PostMapping
    public Banner create(@RequestBody Banner banner) {
        bannerService.create(banner);
        return banner;
    }

    @GetMapping
    public ResponseEntity<List<Banner>> getAll() {
        return new ResponseEntity<>(bannerService.getAll(), HttpStatus.OK);
    }

}

package com.emlakcepte.controller;

import com.emlakcepte.model.Invoice;
import com.emlakcepte.request.PaymentRequest;
import com.emlakcepte.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Invoice payment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.payment(paymentRequest);
    }

}

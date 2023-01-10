package com.emlakcepte.client;


import com.emlakcepte.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "accepted-bank-service", url = "http://localhost:8890/creditcards")
public interface BankServiceClient {
    @PostMapping
    Boolean isPayment(PaymentRequest paymentRequest);
}

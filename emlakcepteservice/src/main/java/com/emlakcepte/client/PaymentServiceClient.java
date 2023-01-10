package com.emlakcepte.client;

import com.emlakcepte.model.Invoice;
import com.emlakcepte.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "accepted-payment-service", url = "http://localhost:8891")
public interface PaymentServiceClient {
    @PostMapping(value = "/payments")
    Invoice payment(@RequestBody PaymentRequest paymentRequest);
}

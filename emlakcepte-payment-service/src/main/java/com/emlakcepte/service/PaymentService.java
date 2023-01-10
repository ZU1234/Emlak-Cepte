package com.emlakcepte.service;


import com.emlakcepte.client.BankServiceClient;
import com.emlakcepte.configuration.EmlakcepteUsersAndProductsQueue;
import com.emlakcepte.model.Invoice;
import com.emlakcepte.model.RealtyProduct;
import com.emlakcepte.request.PaymentRequest;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class PaymentService {
    private final int REALTY_PACKAGE_PIECE = 10;
    private final int ONE_PACKAGE_VALUE_BY_MONTH = 1;
    @Autowired
    private BankServiceClient bankServiceClient;

    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private EmlakcepteUsersAndProductsQueue emlakcepteUsersAndProductsQueue;


    public Invoice payment(PaymentRequest paymentRequest) {

        if (bankServiceClient.isPayment(paymentRequest)) {
            rabbitTemplate.convertAndSend(emlakcepteUsersAndProductsQueue.getQueueName(), convertRealtyProduct(paymentRequest));
            return convertInvoice(paymentRequest);
        }
        return null;
    }


    public Invoice convertInvoice(PaymentRequest paymentRequest) {
        Invoice response = new Invoice();
        response.setProductType(paymentRequest.getProductType());
        response.setPrice(paymentRequest.getPrice());
        response.setUserId(paymentRequest.getUserId());
        return response;
    }

    public RealtyProduct convertRealtyProduct(PaymentRequest paymentRequest) {
        RealtyProduct response = new RealtyProduct();
        response.setUserId(paymentRequest.getUserId());
        response.setProductType(paymentRequest.getProductType());

        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue() + REALTY_PACKAGE_PIECE * ONE_PACKAGE_VALUE_BY_MONTH;
        int day = localDate.getDayOfMonth();
        int year = localDate.getYear();

        response.setPackageEndDate(day + "." + month + "." + year);


        return response;
    }
}

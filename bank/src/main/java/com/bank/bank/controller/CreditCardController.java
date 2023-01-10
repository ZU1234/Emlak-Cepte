package com.bank.bank.controller;


import com.bank.bank.model.CreditCard;
import com.bank.bank.request.PaymentRequest;
import com.bank.bank.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/creditcards")
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    @PostMapping(value = "/add")
    public ResponseEntity<CreditCard> create(@RequestBody CreditCard creditCard) {
        creditCardService.saveRequest(creditCard);
        return ResponseEntity.ok(creditCard);
    }

    @PostMapping
    public ResponseEntity<Boolean> isPayment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(creditCardService.isPayment(paymentRequest));
    }

}

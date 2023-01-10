package com.bank.bank.request;

import com.bank.bank.enums.Product;

public class PaymentRequest {
    private String cardNo;
    private Integer userId;  // kart sahibi
    private Integer cvv;
    private Integer price;
    private Product product;


    public PaymentRequest() {
        super();
    }

    public PaymentRequest(String cardNo, Integer userId, Integer cvv, Integer price, Product product) {
        this.cardNo = cardNo;
        this.userId = userId;
        this.cvv = cvv;
        this.price = price;
        this.product = product;

    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "cardNo='" + cardNo + '\'' +
                ", userId=" + userId +
                ", cvv=" + cvv +
                ", price=" + price +
                '}';
    }
}

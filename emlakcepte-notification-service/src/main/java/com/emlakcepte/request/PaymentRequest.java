package com.emlakcepte.request;

import com.emlakcepte.enums.ProductType;

public class PaymentRequest {

    private String cardNo;
    private Integer userId;  // kart sahibi
    private Integer cvv;
    private Integer price;
    private Integer piece;
    private ProductType productType;

    public PaymentRequest() {
        super();
    }

    public PaymentRequest(String cardNo, Integer userId, Integer cvv, Integer price, Integer piece, ProductType productType) {
        this.cardNo = cardNo;
        this.userId = userId;
        this.cvv = cvv;
        this.price = price;
        this.piece = piece;
        this.productType = productType;
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

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    public ProductType getProduct() {
        return productType;
    }

    public void setProduct(ProductType productType) {
        this.productType = productType;
    }
}

package com.emlakcepte.response;

import com.emlakcepte.enums.ProductType;


public class PaymentResponse {


    private Integer userId;  // kart sahibi
    private Integer price;
    private ProductType productType;
    private Integer piece;

    public PaymentResponse() {
        super();
    }

    public PaymentResponse(Integer userId, Integer price, ProductType productType, Integer piece) {
        this.userId = userId;
        this.price = price;
        this.productType = productType;
        this.piece = piece;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ProductType getProduct() {
        return productType;
    }

    public void setProduct(ProductType productType) {
        this.productType = productType;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "userId=" + userId +
                ", price=" + price +
                ", productType=" + productType +
                ", piece=" + piece +
                '}';
    }
}

package com.emlakcepte.model;

import com.emlakcepte.enums.ProductType;

import java.io.Serializable;


public class ProductAndUserId implements Serializable {

    private Integer userId;

    private ProductType productType;

    public ProductAndUserId() {
        super();
    }

    public ProductAndUserId(Integer userId, ProductType productType) {
        this.userId = userId;
        this.productType = productType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}

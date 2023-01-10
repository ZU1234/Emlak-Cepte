package com.emlakcepte.model;

import com.emlakcepte.enums.ProductType;

import java.io.Serializable;

public class ProductAndUserId implements Serializable {
    private Integer userId;

    private ProductType productType;

    // default constructor


    public ProductAndUserId(Integer userId, ProductType productType) {
        this.userId = userId;
        this.productType = productType;
    }
}

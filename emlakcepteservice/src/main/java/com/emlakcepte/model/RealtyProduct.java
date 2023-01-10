package com.emlakcepte.model;

import com.emlakcepte.enums.ProductType;

import javax.persistence.*;

@Entity
@IdClass(ProductAndUserId.class)
@Table(name = "users_and_products")
public class RealtyProduct {
    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Id
    @Enumerated(value = EnumType.STRING)
    @Column(name = "productType")
    private ProductType productType;
    @Column(name = "packageEndDate")
    private String packageEndDate;

    public RealtyProduct() {
        super();
    }

    public RealtyProduct(Integer userId, ProductType productType, String packageEndDate) {

        this.userId = userId;
        this.productType = productType;
        this.packageEndDate = packageEndDate;
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

    public String getPackageEndDate() {
        return packageEndDate;
    }

    public void setPackageEndDate(String packageEndDate) {
        this.packageEndDate = packageEndDate;
    }
}

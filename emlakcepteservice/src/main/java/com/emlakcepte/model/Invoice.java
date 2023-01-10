package com.emlakcepte.model;

import com.emlakcepte.enums.ProductType;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;  // kart sahibi
    @Column(name = "price")
    private Integer price;
    @Column(name = "productType")
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Column(name = "product_piece")
    private Integer piece;
    @Column(name = "create_Date ")
    private String createDate;

    public Invoice(){
        super();
    }

    public Invoice(Integer id, Integer userId, Integer price, ProductType productType, Integer piece, String createDate) {
        this.id = id;
        this.userId = userId;
        this.price = price;
        this.productType = productType;
        this.piece = piece;
        this.createDate=createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}

package com.emlakcepte.model;

import com.emlakcepte.enums.ProductType;
import javax.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "user_id")
    private int userId;  // kart sahibi
    @Column(name = "price")
    private int price;
    @Column(name = "product")
    //TODO product düzenle
    private ProductType productType;
    public Invoice(){
        super();
    }
    public Invoice(int id, int userId, int price, ProductType productType) {
        this.id = id;
        this.userId = userId;
        this.price = price;
        this.productType = productType;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}

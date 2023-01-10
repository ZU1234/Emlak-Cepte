package com.bank.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "creditcards")
public class CreditCard {
    @Id
    @Column(name = "card_no")
    private String cardNo;
    @Column(name = "cvv")
    private Integer cvv;
    @Column(name = "user_id")
    private Integer userId;  // kart sahibi
    @Column(name = "balance", scale = 2)
    //@Column(name = "balance")
    private long balance; //bakiye


    public CreditCard() {
    }

    public CreditCard(String cardNo, Integer cvv, Integer userId, long balance) {
        this.cardNo = cardNo;
        this.cvv = cvv;
        this.userId = userId;
        this.balance = balance;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNo='" + cardNo + '\'' +
                ", cvv=" + cvv +
                ", userId=" + userId +
                ", balance=" + balance +
                '}';
    }
}

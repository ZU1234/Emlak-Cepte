package com.emlakcepte.model;

import com.emlakcepte.enums.UserType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "mail")
    private String mail;
    @Column(name = "password")
    private String password;
    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private UserType type;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "realty_id", referencedColumnName = "id")
    private List<Realty> realtyList;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createDate;


    public User() {
        super();
    }

    public User(String name, String mail, String password, UserType type) {
        super();
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.type = type;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public List<Realty> getRealtyList() {
        return realtyList;
    }

    public void setRealtyList(List<Realty> realtyList) {
        this.realtyList = realtyList;
    }

    /*public List<Realty> getFavoriteRealtyList() {
        return favoriteRealtyList;
    }
    public void setFavoriteRealtyList(List<Realty> favoriteRealtyList) {
        this.favoriteRealtyList = favoriteRealtyList;
    }
    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }**/
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", realtyList=" + realtyList +
                ", createDate=" + createDate +
                '}';
    }
}

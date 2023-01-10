package com.emlakcepte.model;

import com.emlakcepte.enums.RealtyType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "realtyes")
public class Realty {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "realty_no", nullable = false)
    private Integer no;
    @Column(name = "title", nullable = false, length = 200)
    private String title;
    @Column(name = "create_Date ")
    private LocalDateTime createDate;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RealtyType status;
    @Column(name = "province")
    private String province;

    public Realty() {
        super();
    }

    public Realty(Integer no, String title, LocalDateTime createDate, Integer userId, RealtyType status, String province) {
        super();
        this.no = no;
        this.title = title;
        this.createDate = createDate;
        this.userId = userId;
        this.status = status;
        this.province = province;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public RealtyType getStatus() {
        return status;
    }

    public void setStatus(RealtyType status) {
        this.status = status;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Realty{" +
                "id=" + id +
                ", no=" + no +
                ", title='" + title + '\'' +
                ", createDate=" + createDate +
                ", status=" + status +
                ", province='" + province + '\'' +
                '}';
    }
}

package com.emlakcepte.request;

import com.emlakcepte.enums.RealtyType;

public class RealtyStatusUpdateRequest {
    private Integer id;
    private RealtyType status;

    public RealtyStatusUpdateRequest(Integer id, RealtyType status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealtyType getStatus() {
        return status;
    }

    public void setStatus(RealtyType status) {
        this.status = status;
    }
}

package com.emlakcepte.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RealtyType {
    @JsonProperty("ACTIVE")    //Http sorguları atarken kullanılacak isim , büyük küçük harf duyarlıdır.
    ACTIVE,
    @JsonProperty("PASSIVE")
    PASSIVE,
    @JsonProperty("IN_REVIEW")
    IN_REVIEW

}

package com.emlakcepte.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProductType {
    @JsonProperty(value = "REALTY_CREATE")  //Http sorguları atarken kullanılacak isim , büyük küçük harf duyarlıdır.
    REALTY_CREATE
}

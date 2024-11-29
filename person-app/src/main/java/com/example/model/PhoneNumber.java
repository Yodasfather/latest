package com.example.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

// @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
// @JsonSubTypes({
//     @JsonSubTypes.Type(value = DomesticNumber.class, name = "domestic"),
//     @JsonSubTypes.Type(value = InternationalNumber.class, name = "international")
// })
// @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public abstract class PhoneNumber {
    public int areaCode;
    public int local;

    public PhoneNumber() {}

    public PhoneNumber(int areaCode, int local) {
        this.areaCode = areaCode;
        this.local = local;
    }

    @Override
    public String toString() {
        return "PhoneNumber{areaCode=" + areaCode + ", local=" + local + '}';
    }
}

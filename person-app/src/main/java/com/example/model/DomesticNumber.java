package com.example.model;

public class DomesticNumber extends PhoneNumber {
    public DomesticNumber() {}

    public DomesticNumber(int areaCode, int local) {
        super(areaCode, local);
    }

    @Override
    public String toString() {
        return "DomesticNumber{areaCode=" + areaCode + ", local=" + local + '}';
    }
}

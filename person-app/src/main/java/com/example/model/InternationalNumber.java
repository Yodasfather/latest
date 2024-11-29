package com.example.model;

public class InternationalNumber extends PhoneNumber {
    public int countryCode;

    public InternationalNumber() {}

    public InternationalNumber(int countryCode, int areaCode, int local) {
        super(areaCode, local);
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "InternationalNumber{countryCode=" + countryCode + ", areaCode=" + areaCode + ", local=" + local + '}';
    }
}

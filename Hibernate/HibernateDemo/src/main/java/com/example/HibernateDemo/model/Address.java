package com.example.HibernateDemo.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    String houseNo;
    String city;
    Long pinCode;

    public Address(String houseNo, String city, Long pinCode) {
        this.houseNo = houseNo;
        this.city = city;
        this.pinCode = pinCode;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPinCode() {
        return pinCode;
    }

    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
    }
}

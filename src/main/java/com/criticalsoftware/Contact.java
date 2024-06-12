package com.criticalsoftware;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Contact extends PanacheMongoEntity {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email may not be blank")
    private String email;

    @NotNull(message = "PhoneNumber may not be blank")
    private Integer phoneNumber;

    @NotBlank(message = "Address may not be blank")
    private String address;

    public Contact() {
    }

    public Contact(String email, int phoneNumber, String address) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
package com.criticalsoftware;


import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Contact extends PanacheMongoEntity {

    private String email;
    private int phoneNumber;
    private String address;


    public Contact() {

    }

    public Contact(String email, int phoneNumber, String address) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

    //email
    public String getemail(){
        return this.email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    //phoneNumber
    public int getphoneNumber(){
        return this.phoneNumber;
    }

    public void setphoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //address
    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

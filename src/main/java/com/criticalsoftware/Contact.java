package com.criticalsoftware;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;

public class Contact extends PanacheMongoEntity {

    private ObjectId id;
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
    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //phoneNumber
    public int getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //address
    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //Id
    public ObjectId getId(){
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}

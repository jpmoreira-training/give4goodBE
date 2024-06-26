package com.criticalsoftware;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class Announcement {

    private String id;
    private Product product;
    private LocalDateTime date;
    private ObjectId userDonorId;
    private ObjectId userDoneeId;

    public Announcement() {}

    public Announcement(String Id, Product product, ObjectId userDonorId, ObjectId userDoneeId) {
        this.id = id;
        this.product = product;
        this.date = LocalDateTime.now();
        this.userDonorId = userDonorId;
        this.userDoneeId = userDoneeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Product
    public Product getProduct(){
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    //Date
    public LocalDateTime getDate(){
        return this.date;
    }

    public void setDate (LocalDateTime date) {
        this.date = date;
    }

    //userDonor
    public ObjectId getUserDonorId(){
        return this.userDonorId;
    }

    public void setUserDonorId (ObjectId userDonorId) {
        this.userDonorId = userDonorId;
    }

    //userDonee
    public ObjectId getUserDoneeId(){
        return this.userDoneeId;
    }

    public void setUserDoneeId (ObjectId userDoneeId) {
        this.userDoneeId = userDoneeId;
    }
}

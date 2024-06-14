package com.criticalsoftware;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;

//Announcement Response
public class AnnouncementResponse {

    private ObjectId id;
    private Product product;
    private LocalDateTime date;
    private ObjectId userDonorId;


    public AnnouncementResponse(ObjectId id, Product product, ObjectId userDonorId, LocalDateTime localDateTime) {

    }

    public AnnouncementResponse(ObjectId id,Product product, LocalDateTime localDateTime, ObjectId userDonorId) {
        this.id = id;
        this.product = product;
        this.date = LocalDateTime.now();
        this.userDonorId = userDonorId;

    }

    // Getters and Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    //Product
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    //Date
    public LocalDateTime getLocalDateTime() {
        return this.date;
    }

    public void setLocalDateTime(LocalDateTime date) {
        this.date = date;
    }

    //userDonor
    public ObjectId getUserDonorId() {
        return this.userDonorId;
    }

    public void setUserDonorId(ObjectId userDonor) {
        this.userDonorId = userDonor;
    }

}
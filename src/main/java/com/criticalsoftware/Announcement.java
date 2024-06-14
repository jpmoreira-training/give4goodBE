package com.criticalsoftware;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class Announcement {
    private ObjectId id;
    private Product product;
    private ObjectId userDonorId;
    private LocalDateTime date;

    public Announcement() {}

    public Announcement(Product product, ObjectId userDonorId, LocalDateTime date) {
        this.product = product;
        this.userDonorId = userDonorId;
        this.date = date;
    }

    // Getters and setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ObjectId getUserDonorId() {
        return userDonorId;
    }

    public void setUserDonorId(ObjectId userDonorId) {
        this.userDonorId = userDonorId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

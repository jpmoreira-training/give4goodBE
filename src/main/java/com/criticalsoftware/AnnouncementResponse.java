package com.criticalsoftware;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class AnnouncementResponse {

    private String id;
    private Product product;
    private ObjectId userDonorId;
    private ObjectId userDonneeId;
    private LocalDateTime date;

    public AnnouncementResponse() {
    }

    public AnnouncementResponse(String id, Product product, ObjectId userDonorId, ObjectId userDonneeId, LocalDateTime date) {
        this.id = id;
        this.product = product;
        this.userDonorId = userDonorId;
        this.userDonneeId = userDonneeId;
        this.date = date;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public ObjectId getUserDonneeId() {
        return userDonneeId;
    }

    public void setUserDonneeId(ObjectId userDonneeId) {
        this.userDonneeId = userDonneeId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

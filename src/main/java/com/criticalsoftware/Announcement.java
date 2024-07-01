package com.criticalsoftware;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;

@Getter
@Setter
public class Announcement {
    private String id;
    private Product product;
    private ObjectId userDonorId;
    private ObjectId userDoneeId;
    private LocalDateTime date;

    public Announcement() {
    }

    public Announcement(Product product, ObjectId userDonorId) {
        this.product = product;
        this.userDonorId = userDonorId;
        this.date = LocalDateTime.now();
    }

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ObjectId getUserDoneeId() {
        return userDoneeId;
    }

    public void setUserDoneeId(ObjectId userDoneeId) {
        this.userDoneeId = userDoneeId;
    }
}

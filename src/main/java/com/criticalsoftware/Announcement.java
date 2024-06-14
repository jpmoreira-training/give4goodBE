package com.criticalsoftware;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class Announcement extends PanacheMongoEntity {

    private ObjectId id;
    private Product product;
    private LocalDateTime date;
    private User userDonor;
    private User userDonee;

    public Announcement() {
    }

    public Announcement(ObjectId id, Product product, User userDonor, User userDonee) {
        this.id = id;
        this.product = product;
        this.date = LocalDateTime.now();
        this.userDonor = userDonor;
        this.userDonee = userDonee;
    }

    // Getters and Setters

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUserDonor() {
        return this.userDonor;
    }

    public void setUserDonor(User userDonor) {
        this.userDonor = userDonor;
    }

    public User getUserDonee() {
        return this.userDonee;
    }

    public void setUserDonee(User userDonee) {
        this.userDonee = userDonee;
    }

    public ObjectId getId() {
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}

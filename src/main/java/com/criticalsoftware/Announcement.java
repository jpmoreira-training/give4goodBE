package com.criticalsoftware;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Getter
@Setter
public class Announcement extends PanacheMongoEntity {

    private String id;
    private Product product;
    private LocalDateTime date;
    private ObjectId userDonorId;
    private ObjectId userDoneeId;

    public Announcement() {
    }

    public Announcement(String id, Product product, ObjectId userDonorId, ObjectId userDoneeId) {
        this.id = id;
        this.product = product;
        this.date = LocalDateTime.now();
        this.userDonorId = userDonorId;
        this.userDoneeId = userDoneeId;
    }
}

package com.criticalsoftware;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class Announcement extends PanacheMongoEntity {

    private ObjectId id;
    private Product product;
    private LocalDateTime date;
    private ObjectId userDonorId;
    private ObjectId userDoneeId;

    public Announcement() {}

    public Announcement(Product product, ObjectId userDonorId, ObjectId userDoneeId) {
        this.product = product;
        this.date = LocalDateTime.now();
        this.userDonorId = userDonorId;
        this.userDoneeId = userDoneeId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
    public LocalDateTime getdate(){
        return this.date;
    }

    public void setLocalDateTime(LocalDateTime date) {
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


    public LocalDateTime getLocalDateTime() {
        return this.date;
    }
}

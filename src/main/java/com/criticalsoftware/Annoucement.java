package com.criticalsoftware;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Annoucement extends PanacheMongoEntity {

    private Product product;
    private LocalDateTime date;
    private User userDonor;
    private User userDonee;

    public Annoucement() {

    }


    public Annoucement(Product product, LocalDateTime date, User userDonor, User userDonee) {
        this.product = product;
        this.date = date;
        this.userDonor = userDonor;
        this.userDonee = userDonee;

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
    public User getuserDonor(){
        return this.userDonor;
    }

    public void setUserDonor (User userDonor) {
        this.userDonor = userDonor;
    }

    //userDonee
    public User getuserDonee(){
        return this.userDonee;
    }

    public void setUserDonee (User userDonee) {
        this.userDonee = userDonee;
    }

}

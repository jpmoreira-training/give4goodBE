package com.criticalsoftware;
import org.bson.types.ObjectId;
import java.awt.image.BufferedImage;

public class Product {

    private ObjectId id;
    private String description;
    private BufferedImage photo;
    private String category;
    private User user;

    public Product() {
    }

    public Product(String description, BufferedImage photo, String category, User user) {
        this.description = description;
        this.photo = photo;
        this.category = category;
        this.user = user;
    }

    //description
    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //photo
    public BufferedImage getPhoto(){
        return this.photo;
    }

    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
    }

    //category
    public String getCategory(){
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //user
    public User getUser(){
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //Id
    public ObjectId getId(){
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}

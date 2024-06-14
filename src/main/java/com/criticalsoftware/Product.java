package com.criticalsoftware;
import org.bson.types.ObjectId;
import java.awt.image.BufferedImage;

public class Product {

    private ObjectId id;
    private String description;
    private String photoUrl;
    private String category;
    private String name;

    public Product() {
    }

    public Product(String description, String photoUrl, String category, String name) {
        this.description = description;
        this.photoUrl = photoUrl;
        this.category = category;
        this.name = name;
    }

    //description
    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //photo
    public String getPhotoUrl(){
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    //category
    public String getCategory(){
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //name
    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Id
    public ObjectId getId(){
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}

package com.criticalsoftware;

import org.bson.types.ObjectId;

public class Product {
    private ObjectId id;
    private String name;
    private String description;
    private String photoUrl;
    private String category;

    public Product(String name, String description, String photoUrl, String category) {
        this.name = name;
        this.description = description;
        this.photoUrl = photoUrl;
        this.category = category;
    }

    // Getters e setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

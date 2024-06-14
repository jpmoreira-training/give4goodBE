package com.criticalsoftware;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

public class Product {
    private ObjectId id;

    @NotBlank(message = "Product name is mandatory")
    @Size(max = 30, message = "Product name must be less than or equal to 30 characters")
    private String name;

    @NotBlank(message = "Product description is mandatory")
    @Size(max = 255, message = "Product description must be less than or equal to 255 characters")
    private String description;

    @NotBlank(message = "Photo URL is mandatory")
    @Pattern(regexp = "^(http|https)://.*$", message = "Photo URL must be a valid URL")
    private String photoUrl;

    @NotBlank(message = "Category is mandatory")
    @Size(max = 50, message = "Category must be less than or equal to 30  characters")
    private String category;

    public Product() {
    }

    public Product(String name, String description, String photoUrl, String category) {
        this.name = name;
        this.description = description;
        this.photoUrl = photoUrl;
        this.category = category;
    }

    // Getters and setters
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

package com.criticalsoftware;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AnnouncementRequest {

    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Product name must only contain alphanumeric characters and spaces")
    @NotBlank(message = "User donor ID is mandatory and cannot be blank")
    private String userDonorId;

    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Product name must only contain alphanumeric characters and spaces")
    @NotBlank(message = "Product name is mandatory and cannot be blank")
    @Size(max = 30, message = "Product name must be less than or equal to 30 characters")
    private String productName;

    @NotBlank(message = "Description is mandatory and cannot be blank")
    @Size(max = 500, message = "Description must be less than or equal to 500 characters")
    private String productDescription;

    @NotBlank(message = "Photo URL is mandatory and cannot be blank")
    @Size(max = 200, message = "Photo URL must be less than or equal to 200 characters")
    @Pattern(regexp = "^(http|https)://.*$", message = "Photo URL must be a valid URL")
    private String productPhotoUrl;

    @NotBlank(message = "Category is mandatory and cannot be blank")
    @Size(max = 255, message = "Category must be less than or equal to 255 characters")
    private String productCategory;

    public AnnouncementRequest() {
    }

    public AnnouncementRequest(String userDonorId, String productName, String productDescription, String productPhotoUrl, String productCategory) {
        this.userDonorId = userDonorId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPhotoUrl = productPhotoUrl;
        this.productCategory = productCategory;
    }

    // Getters and setters
    public String getUserDonorId() {
        return userDonorId;
    }

    public void setUserDonorId(String userDonorId) {
        this.userDonorId = userDonorId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPhotoUrl() {
        return productPhotoUrl;
    }

    public void setProductPhotoUrl(String productPhotoUrl) {
        this.productPhotoUrl = productPhotoUrl;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}

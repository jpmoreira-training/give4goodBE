package com.criticalsoftware;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.json.bind.annotation.JsonbDateFormat;
import java.time.LocalDateTime;

public class AnnouncementRequest {

    @NotBlank(message = "User donor ID is mandatory")
    private String userDonorId;

    @NotNull(message = "Date is mandatory")
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime date;

    @Valid
    @NotNull(message = "Produto is mandatory" )
    private Product product;

    // Getters and setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUserDonorId() {
        return userDonorId;
    }

    public void setUserDonorId(String userDonorId) {
        this.userDonorId = userDonorId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

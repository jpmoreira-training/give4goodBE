package com.criticalsoftware;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import java.time.LocalDate;

@MongoEntity(collection = "users")
public class User extends PanacheMongoEntity {

    private String name;
    private LocalDate dateBirth;
    private Contact contact;

    public User(String name, LocalDate dateBirth, Contact contact) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.contact = contact;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
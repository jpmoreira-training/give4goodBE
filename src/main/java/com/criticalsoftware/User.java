package com.criticalsoftware;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import java.time.LocalDate;


public class User extends PanacheMongoEntity {

    private String name;
    private LocalDate dateBirth;
    private Contact contact;


    public User() {

    }


    public User(String name, LocalDate dateBirth, Contact contact) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.contact = contact;

    }

    //name
    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //dateBirth
    public LocalDate getDateBirth(){
        return this.dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    //contact
    public Contact getContact(){
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}





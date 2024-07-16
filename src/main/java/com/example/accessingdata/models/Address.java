package com.example.accessingdata.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "location")
    private String location;

    @OneToOne(mappedBy = "address")
    private Library library;

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, location='%s']",
                id, location);
    }

    public String getLocation() {
        return this.location;
    }

    public Address(String location) {
        this.location = location;
    }

    public Address(long id, String location, Library library) {
        this.id = id;
        this.location = location;
        this.library = library;
    }

    public Address() {
    }
}

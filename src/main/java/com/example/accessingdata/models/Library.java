package com.example.accessingdata.models;

import java.util.List;

// import org.springframework.data.rest.core.annotation.RestResource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Library {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    // @RestResource(path = "libraryAddress", rel = "address")
    private Address address;

    @OneToMany(mappedBy = "library")
    private List<Book> books;

    public String toString() {
        return String.format(
                "Customer[id=%d, location='%s']",
                id, name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Library(String name) {
        this.name = name;
    }

    public Library(long id, String name, Address address, List<Book> books) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.books = books;
    }

    public Library() {
    }
}

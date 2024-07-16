package com.example.accessingdata.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdata.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {}

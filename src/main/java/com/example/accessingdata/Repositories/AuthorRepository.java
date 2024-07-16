package com.example.accessingdata.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdata.models.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {}

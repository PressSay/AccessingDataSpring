package com.example.accessingdata.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdata.models.Library;

public interface LibraryRepository extends CrudRepository<Library, Long> {}

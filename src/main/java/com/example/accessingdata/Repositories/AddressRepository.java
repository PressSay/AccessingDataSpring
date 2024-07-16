package com.example.accessingdata.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdata.models.Address;

public interface AddressRepository extends CrudRepository<Address, Long>  {}

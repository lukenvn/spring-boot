package com.luke.example.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by nvnhung on 06/01/2017.
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    List<CustomerEntity> findByLastName(String lastName);
}
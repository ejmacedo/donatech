package com.donatech.projetodonatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donatech.projetodonatech.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}

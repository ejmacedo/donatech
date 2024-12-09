package com.donatech.projetodonatech.repository;

import com.donatech.projetodonatech.entities.Image;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

}



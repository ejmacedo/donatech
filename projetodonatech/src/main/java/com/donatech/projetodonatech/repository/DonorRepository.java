package com.donatech.projetodonatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donatech.projetodonatech.entities.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long>{

}

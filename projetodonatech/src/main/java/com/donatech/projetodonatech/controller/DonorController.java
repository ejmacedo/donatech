package com.donatech.projetodonatech.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donatech.projetodonatech.dto.DonorDto;
import com.donatech.projetodonatech.dto.UpdateDonorDto;
import com.donatech.projetodonatech.entities.Donor;
import com.donatech.projetodonatech.services.DonorService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/v1/doadores")
public class DonorController {

	private DonorService donorService;


	public DonorController(DonorService donorService) {
		super();
		this.donorService = donorService;
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Donor> createDonor(@RequestBody DonorDto donor) {
		donorService.createDonor(donor);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{donorId}")
	public ResponseEntity<Donor> getDonorById(@PathVariable Long donorId) {
		var donor = donorService.getDonorById(donorId);
		if (donor.isPresent()) {
			return ResponseEntity.ok(donor.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Donor>> listDonors() {
		var donors = donorService.listDonors();

		return ResponseEntity.ok(donors);
	}

	@PutMapping("/{donorId}")
	public ResponseEntity<Donor> updateDonorById(@PathVariable Long donorId, @RequestBody UpdateDonorDto updateDonorDto) {
		donorService.updateDonorById(donorId, updateDonorDto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{donorId}")
	public ResponseEntity<Void> deleteDonorById(@PathVariable Long donorId) {
		donorService.deleteDonorById(donorId);
		return ResponseEntity.noContent().build();
	}

}

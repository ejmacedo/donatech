package com.donatech.projetodonatech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.donatech.projetodonatech.dto.DonorDto;
import com.donatech.projetodonatech.dto.UpdateDonorDto;
import com.donatech.projetodonatech.entities.Donor;
import com.donatech.projetodonatech.repository.DonorRepository;
import com.donatech.projetodonatech.repository.ProductRepository;

@Service
public class DonorService {

	private DonorRepository donorRepository;
	private ProductRepository productRepository;

	public DonorService(DonorRepository donorRepository, ProductRepository productRepository) {
		super();
		this.donorRepository = donorRepository;
		this.productRepository = productRepository;
	}

//METODO SERVICE PARA SALVAR DOADOR
	public Long createDonor(DonorDto donor) {

		var produto = productRepository.getReferenceById(donor.id_produto());
		if (produto == null) {
			throw new IllegalArgumentException("O produto não pode ser nulo.");
		}
		var entity = new Donor();
		entity.setNome(donor.nome());
		entity.setCpf(donor.cpf());
		entity.setEmail(donor.email());
		entity.setSenha(donor.senha());
		entity.setRua(donor.rua());
		entity.setBairro(donor.bairro());
		entity.setNum_casa(donor.num_casa());
		entity.setNum_telefone(donor.num_telefone());
		entity.setProcuct(produto);

		var donorSaved = donorRepository.save(entity);

		return donorSaved.getId_doador();
	}

	public Optional<Donor> getDonorById(Long id_doador) {
		return donorRepository.findById(id_doador);
	}

	public List<Donor> listDonors() {
		return donorRepository.findAll();
	}

//String nome, String email, String senha, String rua, String bairro, int num_casa 
	public void updateDonorById(Long donorId, UpdateDonorDto updateDonorDto) {
		var donorExists = donorRepository.findById(donorId);
		var produto = productRepository.getReferenceById(updateDonorDto.id_produto());

		if (donorExists.isPresent()) {
			Donor existingDonor = donorExists.get();

			// Update fields based on request body (assuming getters exist)
			if (updateDonorDto.nome() != null) {
				existingDonor.setNome(updateDonorDto.nome());
			}
			if (updateDonorDto.email() != null) {
				existingDonor.setEmail(updateDonorDto.email());
			}
			if (updateDonorDto.senha() != null) {
				existingDonor.setSenha(updateDonorDto.senha());
			}
			if (updateDonorDto.rua() != null) {
				existingDonor.setRua(updateDonorDto.rua());
			}
			if (updateDonorDto.bairro() != null) {
				existingDonor.setBairro(updateDonorDto.bairro());
			}
			if (updateDonorDto.num_casa() != null) {
				existingDonor.setNum_casa(updateDonorDto.num_casa());
			}
			if (updateDonorDto.num_telefone() != null) {
				existingDonor.setNum_telefone(updateDonorDto.num_telefone());
			}
			if (updateDonorDto.id_produto() != null) {
				existingDonor.setProcuct(produto);
			}

			donorRepository.save(existingDonor);

		}
	}

	public void deleteDonorById(Long id_doador) {
		var id = id_doador;
		var donorExist = donorRepository.existsById(id);

		if (donorExist) {
			donorRepository.deleteById(id);
		}
	}

}

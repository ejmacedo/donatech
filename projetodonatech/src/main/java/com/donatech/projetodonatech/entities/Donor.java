package com.donatech.projetodonatech.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_doador")
public class Donor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_doador;
	private String nome;
	private Long cpf;
	private String email;
	private String senha;
	private String rua;
	private String bairro;
	private String num_casa;
	private String num_telefone;

	// muitos produtos pertencem a um doador
	@OneToOne
	@JoinColumn(name = "id_produto")
	private Product procuct;

	public Donor() {
	}

	public Donor(Long id_doador, String nome, Long cpf, String email, String senha, String rua, String bairro,
			String num_casa, String num_telefone, Product product) {
		this.id_doador = id_doador;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.rua = rua;
		this.bairro = bairro;
		this.num_casa = num_casa;
		this.num_telefone = num_telefone;
		this.procuct = product;
	}

	public Long getId_doador() {
		return id_doador;
	}

	public void setId_doador(Long id_doador) {
		this.id_doador = id_doador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNum_casa() {
		return num_casa;
	}

	public void setNum_casa(String num_casa) {
		this.num_casa = num_casa;
	}

	public String getNum_telefone() {
		return num_telefone;
	}

	public void setNum_telefone(String num_telefone) {
		this.num_telefone = num_telefone;
	}

	public Product getProcuct() {
		return procuct;
	}

	public void setProcuct(Product product) {
		this.procuct = product;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donor other = (Donor) obj;
		return Objects.equals(id_doador, other.id_doador);
	}

}

package com.donatech.projetodonatech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.donatech.projetodonatech.dto.UpdateProductDto;
import com.donatech.projetodonatech.entities.Product;
import com.donatech.projetodonatech.repository.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository ProductRepository) {
		super();
		this.productRepository = ProductRepository;
	}

	public Long createProduct(Product product) {
		
		var entity = new Product(null,
				product.getNome(),
				product.getDescricao(),
				product.getCategoria(),
				product.getEstado_produto());
		
		var productSaved = productRepository.save(entity);
		
		return productSaved.getId_produto();
	}
	
	public Optional<Product> getProductById(Long id_product){
		return productRepository.findById(id_product);
	}
	
	public List<Product> listProducts(){
		return productRepository.findAll();
	}
	
	public void updateProductById (Long id_product, UpdateProductDto updateProductDto) {
		var productExist = productRepository.findById(id_product);
		
		if(productExist.isPresent()) {
				var product = productExist.get();
				
				if(updateProductDto.nome() != null) {
					product.setNome(updateProductDto.nome());
				}
				
				if(updateProductDto.descricao() != null) {
					product.setDescricao(updateProductDto.descricao());
				}
				
				if(updateProductDto.categoria() != null) {
					product.setCategoria(updateProductDto.categoria());
				}
				
				if(updateProductDto.estado_produto() != null) {
					product.setEstado_produto(updateProductDto.estado_produto());
				}
				
				productRepository.save(product);
		}
	}
	
	public void deleteProduct(Long id_product) {
		
		var productExist = productRepository.findById(id_product);
		
		if(productExist != null) {
			productRepository.deleteById(id_product);
		}
	}
}

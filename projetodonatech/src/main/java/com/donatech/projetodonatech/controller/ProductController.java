package com.donatech.projetodonatech.controller;

import java.net.URI;
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

import com.donatech.projetodonatech.dto.UpdateProductDto;
import com.donatech.projetodonatech.entities.Product;
import com.donatech.projetodonatech.services.ProductService;

@RestController
@RequestMapping("/v1/produtos")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		var productId = productService.createProduct(product);
		return ResponseEntity.created(URI.create("/v1/produtos" + productId.toString())).build();
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
		var product = productService.getProductById(productId);
		
		if(product.isPresent()) {
			return ResponseEntity.ok(product.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> listProducts(){
		var products = productService.listProducts();
		
		return ResponseEntity.ok(products);
	}
	
	
	@PutMapping("/{productId}")
	public ResponseEntity<Void> updateProductById(@PathVariable Long productId, @RequestBody UpdateProductDto updateProductDto){
		productService.updateProductById(productId, updateProductDto);
		
		return ResponseEntity.noContent().build();
	}

	
	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProductById(@PathVariable Long productId) {
		productService.deleteProduct(productId);
		return ResponseEntity.noContent().build();
		
	}
	
}

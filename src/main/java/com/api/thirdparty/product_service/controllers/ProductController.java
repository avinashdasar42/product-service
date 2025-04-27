package com.api.thirdparty.product_service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.thirdparty.product_service.exceptions.ProductNotFoundException;
import com.api.thirdparty.product_service.models.Product;
import com.api.thirdparty.product_service.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
		Product product = productService.getProductById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = productService.getAllProducts();
		if(products ==null) {
			 return new ResponseEntity<List<Product>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
		Product savedProduct = productService.createProduct(product);
		return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
		try {
			productService.deleteProduct(id);
			return new ResponseEntity<>("Product "+id+" deleted Successfully",HttpStatus.NO_CONTENT);
		}catch(Exception ex) {
			return new ResponseEntity<>("Bad Request",HttpStatus.BAD_REQUEST);
		}
	}

}

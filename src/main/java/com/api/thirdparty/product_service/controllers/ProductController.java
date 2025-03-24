package com.api.thirdparty.product_service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.thirdparty.product_service.models.Product;
import com.api.thirdparty.product_service.models.ProductDto;
import com.api.thirdparty.product_service.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
		Optional<Product> product = productService.getProductById(id);
		if(product.isEmpty()) {
//			throw exception
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
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
	public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
		Product product = productService.createProduct(productDto);	
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	

}

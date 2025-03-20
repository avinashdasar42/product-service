package com.api.thirdparty.product_service.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.thirdparty.product_service.models.Product;
import com.api.thirdparty.product_service.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") Long id) {
		Optional<Product> product = productService.getProductById(id);
		if(product.isEmpty()) {
//			throw exception
			return null; //work-around
		}
		return product.get();
	}

}

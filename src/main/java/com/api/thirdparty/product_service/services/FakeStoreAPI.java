package com.api.thirdparty.product_service.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.api.thirdparty.product_service.models.Product;

@FeignClient(name = "fake-store-api", url = "https://fakestoreapi.com/products")
public interface FakeStoreAPI {

	@GetMapping("/{id}")
	Product getProductById(@PathVariable("id") Long id);
	
	@GetMapping
	List<Product> getAllProducts(); 
	
	@PostMapping
	Product createProduct(Product product);
}

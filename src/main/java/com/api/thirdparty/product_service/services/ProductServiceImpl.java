package com.api.thirdparty.product_service.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.thirdparty.product_service.models.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
	private final RestTemplate restTemplate;
	
	public ProductServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public Optional<Product> getProductById(Long id) {
		
		//1. Using Rest Template
		Product product = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, Product.class);
		return Optional.ofNullable(product);
	}

}

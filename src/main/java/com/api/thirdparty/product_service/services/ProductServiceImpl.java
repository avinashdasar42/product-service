package com.api.thirdparty.product_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.thirdparty.product_service.models.Product;
import com.api.thirdparty.product_service.models.ProductDto;

@Service
public class ProductServiceImpl implements ProductService{
	
//	private final RestTemplate restTemplate;
//	
//	public ProductServiceImpl(RestTemplate restTemplate) {
//		this.restTemplate = restTemplate;
//	}
	
//	1. Using Rest Template	
//	@Override
//	public Optional<Product> getProductById(Long id) {
//		Product product = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, Product.class);
//		return Optional.ofNullable(product);
//	}

	public final FakeStoreAPI fakeStoreAPI;
	
	public ProductServiceImpl(FakeStoreAPI fakeStoreAPI) {
		this.fakeStoreAPI = fakeStoreAPI;
	}

	//	2. Using Open Feign Client
	@Override
	public Optional<Product> getProductById(Long id) {
		Product product = fakeStoreAPI.getProductById(id);
		System.out.println("Using Open Feign Client");
		return Optional.ofNullable(product);
	}

	@Override
	public List<Product> getAllProducts() {		
		return fakeStoreAPI.getAllProducts();
	}

	@Override
	public Product createProduct(ProductDto dto) {
		Product product = ProductDto.convertToProductFrom(dto);
		return fakeStoreAPI.createProduct(product);		
	}
}

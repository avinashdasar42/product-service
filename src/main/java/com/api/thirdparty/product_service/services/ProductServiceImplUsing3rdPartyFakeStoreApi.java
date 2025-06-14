package com.api.thirdparty.product_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.thirdparty.product_service.exceptions.ProductNotFoundException;
import com.api.thirdparty.product_service.models.Product;

@Service
public class ProductServiceImplUsing3rdPartyFakeStoreApi implements ProductService{
	
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
	
	public ProductServiceImplUsing3rdPartyFakeStoreApi(FakeStoreAPI fakeStoreAPI) {
		this.fakeStoreAPI = fakeStoreAPI;
	}

	//	2. Using Open Feign Client
	@Override
	public Product getProductById(Long id) throws ProductNotFoundException {
		Product product = fakeStoreAPI.getProductById(id);
		if(product == null) {
			throw new ProductNotFoundException("Product with id : "+id+" not found");
		}
		return product;
	}

	@Override
	public List<Product> getAllProducts() {		
		return fakeStoreAPI.getAllProducts();
	}

	@Override
	public Product createProduct(Product product) {
		return fakeStoreAPI.createProduct(product);		
	}

	@Override
	public void deleteProduct(Long id) {
		fakeStoreAPI.deleteProduct(id);
	}

	@Override
	public List<Product> productCostlierThan(double price) {
		// TODO Auto-generated method stub
		return null;
	}
}

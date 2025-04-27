package com.api.thirdparty.product_service.services;

import java.util.List;
import java.util.Optional;

import com.api.thirdparty.product_service.exceptions.ProductNotFoundException;
import com.api.thirdparty.product_service.models.Product;

public interface ProductService {
	
	Product getProductById(Long id) throws ProductNotFoundException;
	
	List<Product> getAllProducts();
	
	Product createProduct(Product dto);
	
	void deleteProduct(Long id);
	
	List<Product> productCostlierThan(double price);
	
}

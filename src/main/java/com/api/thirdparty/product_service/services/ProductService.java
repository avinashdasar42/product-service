package com.api.thirdparty.product_service.services;

import java.util.List;
import java.util.Optional;

import com.api.thirdparty.product_service.models.Product;
import com.api.thirdparty.product_service.models.ProductDto;

public interface ProductService {
	
	Optional<Product> getProductById(Long id);
	List<Product> getAllProducts();
	Product createProduct(ProductDto dto);
}

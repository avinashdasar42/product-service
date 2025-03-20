package com.api.thirdparty.product_service.services;

import java.util.Optional;

import com.api.thirdparty.product_service.models.Product;

public interface ProductService {
	
	Optional<Product> getProductById(Long id);
}

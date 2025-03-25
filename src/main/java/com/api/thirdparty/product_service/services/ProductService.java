package com.api.thirdparty.product_service.services;

import java.util.List;
import java.util.Optional;

import com.api.thirdparty.product_service.exceptions.ProductNotFoundException;
import com.api.thirdparty.product_service.models.Product;
import com.api.thirdparty.product_service.models.ProductDto;

public interface ProductService {
	
	Product getProductById(Long id) throws ProductNotFoundException;
	List<Product> getAllProducts();
	Product createProduct(ProductDto dto);
	Product deleteProduct(Long id);
}

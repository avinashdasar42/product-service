package com.api.thirdparty.product_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.api.thirdparty.product_service.exceptions.ProductNotFoundException;
import com.api.thirdparty.product_service.models.Category;
import com.api.thirdparty.product_service.models.Product;
import com.api.thirdparty.product_service.repos.CategoryRepository;
import com.api.thirdparty.product_service.repos.ProductRepository;

@Service
@Primary
public class MyProductServiceImplUsingDBData implements ProductService{

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	
	public MyProductServiceImplUsingDBData(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Product getProductById(Long id) throws ProductNotFoundException {
		Optional<Product> product = productRepository.findById(id);
		if(product.isEmpty()) {
			throw new ProductNotFoundException("Product with id : "+id+" not found");
		}
		return product.get();
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product createProduct(Product product) {
		Category category = product.getCategory();
		if(category.getId()==null) {
			Category savedCategory = categoryRepository.save(category);
			product.setCategory(savedCategory);
		}
		return productRepository.save(product);		
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> productCostlierThan(double price) {
		// TODO Auto-generated method stub
		return null;
	}

}

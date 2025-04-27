package com.api.thirdparty.product_service.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.thirdparty.product_service.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("select p from Product p where p.price > :price")
	List<Product> findProductCostlierThan(double price);
	
}

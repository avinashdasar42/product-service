package com.api.thirdparty.product_service.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.thirdparty.product_service.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}

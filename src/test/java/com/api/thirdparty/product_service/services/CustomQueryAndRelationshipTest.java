package com.api.thirdparty.product_service.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.api.thirdparty.product_service.models.Product;
import com.api.thirdparty.product_service.repos.ProductRepository;

//Question: when to use @DataJpaTest
//Answer > Test if  custom queries, relationships like ManyTOne, ManyToMany etc, JPQL or native queries
//@DataJpaTest >> automatically pick up h2 embedded DB from src\test\resources
@DataJpaTest
class CustomQueryAndRelationshipTest {
	
	@Autowired
	ProductRepository productRepository;

	@Test
	void testWhenProductCostlierThan_GivenPrice() {
		Product p1 = new Product();
		p1.setTitle("Iphone");
		p1.setPrice(2000.00);
		p1.setDescription("Mobile");
		
		Product p2 = new Product();
		p2.setTitle("Nike");
		p2.setPrice(1000.00);
		p2.setDescription("Shoes");
		
		productRepository.save(p1);
		productRepository.save(p2);
				
		List<Product> products = productRepository.findProductCostlierThan(1000.00);
		
		assertThat(products).hasSize(1);
		assertThat(products.get(0).getTitle()).isEqualTo("Iphone");
		
		
	}

}

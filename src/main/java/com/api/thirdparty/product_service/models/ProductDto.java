package com.api.thirdparty.product_service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

	private String title;
	private double price;
	private String description;
	private String image;
	private String category;
	
	public static Product convertToProductFrom(ProductDto dto) {
		Product product = new Product();
		product.setTitle(dto.getTitle());
		product.setPrice(dto.getPrice());
		product.setImage(dto.getImage());
		product.setDescription(dto.getDescription());
		product.setCategory(dto.getCategory());
		return product;
	}
}

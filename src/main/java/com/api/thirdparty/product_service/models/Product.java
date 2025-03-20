package com.api.thirdparty.product_service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

	private Long id;
	private String title;
	private double price;
	private String description;
	private String image;
	private String category;
}

package com.api.thirdparty.product_service.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{

	@NotBlank(message = "title is mandatory")
	private String title;
	@Positive(message = "price must be positive")
	private double price;
	private String description;
	@ManyToOne(cascade = {CascadeType.PERSIST})
	private Category category;
	
	public Product() {}
	
	public Product(String title, double price, String description, Category category) {
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
	}
	
}

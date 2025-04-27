package com.api.thirdparty.product_service.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends BaseModel{

	@NotBlank(message="category title is mandatory")
	private String title;
	
	public Category(){}
	
	public Category(String title) {
		this.title = title;
	}
}

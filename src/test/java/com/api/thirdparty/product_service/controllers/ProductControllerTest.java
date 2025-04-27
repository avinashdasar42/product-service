package com.api.thirdparty.product_service.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.api.thirdparty.product_service.exceptions.ProductNotFoundException;
import com.api.thirdparty.product_service.models.Product;
import com.api.thirdparty.product_service.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ProductService productService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Captor
	ArgumentCaptor<Product> productCaptor;
		
	@Test
	@DisplayName("Should create product")
	public void testCreateProductApi_Success() throws Exception {
		
		Product newProduct = spy(Product.class);
		newProduct.setTitle("Laptop");
		newProduct.setPrice(1200.00);
		
		Product savedProduct = spy(Product.class);
        savedProduct.setId(1L); // New ID after saving
        savedProduct.setTitle("Laptop");
        savedProduct.setPrice(1200.00);

        // Mock the behavior of productService
        when(productService.createProduct(any(Product.class))).thenReturn(savedProduct);

        // When & Then
       mockMvc.perform(post("/products") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProduct)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.title").value("Laptop"))
            .andExpect(jsonPath("$.price").value(1200.00));        
        
	}
	
//	This way you test not just the response, but also what input was internally passed.
	@Test
	@DisplayName("Should create product and return 201")
	public void testCreateProductApiWithArgumentCaptor() throws Exception {
		Product newProduct = new Product();
		newProduct.setTitle("Laptop");
		newProduct.setPrice(1200.00);
		
		Product savedProduct = spy(Product.class);
        savedProduct.setId(1L); // New ID after saving
        savedProduct.setTitle("Laptop");
        savedProduct.setPrice(1200.00);
		// Mock the behavior of productService
        when(productService.createProduct(any(Product.class))).thenReturn(savedProduct);

        // When & Then
       MvcResult result =  mockMvc.perform(post("/products") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProduct)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.title").value("Laptop"))
            .andExpect(jsonPath("$.price").value(1200.00))
            .andReturn();
       
    // Capture
//     ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
       verify(productService).createProduct(productCaptor.capture());  // Capture the actual argument

       Product capturedProduct = productCaptor.getValue();

       // Assert captured values
       assertEquals("Laptop", capturedProduct.getTitle());
       assertEquals(1200.00, capturedProduct.getPrice());

	}
	
	@Test
	@DisplayName("Should return 400 Bad Request when product title is missing")
	void testCreateProductFails_WhenTitleMissing() throws JsonProcessingException, Exception {
		Product newProduct = new Product();
//		newProduct.setTitle(null); //empty or null
		newProduct.setPrice(1200.00);
		
		String writeValueAsString = objectMapper.writeValueAsString(newProduct);
		System.out.println("writeValueAsString ::"+writeValueAsString);
		mockMvc.perform(post("/products") 
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(newProduct)))
	            	.andExpect(status().isBadRequest());      
	}

	@Test
	@DisplayName("Should return 400 Bad Request when product price is negative")
	void testCreateProductFails_WhenInvalidPrice() throws JsonProcessingException, Exception {
		Product newProduct = new Product();
		newProduct.setTitle("Laptop");
		newProduct.setPrice(-1200.00);
		
		 mockMvc.perform(post("/products") 
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(newProduct)))
	            	.andExpect(status().isBadRequest());
	           
	}
	
	@Test
	@DisplayName("Should getAllProducts and status code as 200")
	void testGetAllProductsApi_Success() throws Exception {
		Product newProduct1 = new Product();
		newProduct1.setTitle("Laptop");
		newProduct1.setPrice(1200.00);
		
		Product newProduct2 = new Product();
		newProduct2.setTitle("Iphone");
		newProduct2.setPrice(1900.00);
		
		List<Product> products = List.of(newProduct1,newProduct2);
		
		when(productService.getAllProducts()).thenReturn(products);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()", is(2))); 
	}
	
	@Test
	@DisplayName("Should fail when getAllProducts and status code as 400")
	void testGetAllProductsApi_Fails() throws Exception {
		when(productService.getAllProducts()).thenReturn(null);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
			.andExpect(status().isBadRequest()); 
	}

	@Test
	@DisplayName("Should getProductByID succeed and status code as 200")
	void testGetProductsByIdApi_Success() throws Exception {
		Product existingProduct = new Product();
		existingProduct.setTitle("Laptop");
		existingProduct.setPrice(1200.00);
		
		Long id = 1L;
		
		when(productService.getProductById(id)).thenReturn(existingProduct);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/products/"+id).accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("Laptop")); 
	}
	
	@Test
	@DisplayName("Should throw ProductNotFoundException and status code as 404")
	void testGetProductsByIdApi_ThrowsProductNotFoundException() throws Exception {
		Product existingProduct = new Product();
		existingProduct.setTitle("Laptop");
		existingProduct.setPrice(1200.00);
		
		Long id = 100L;
		
		when(productService.getProductById(id)).thenThrow(new ProductNotFoundException("product with id: "+id+" not found"));
		
		RequestBuilder request = MockMvcRequestBuilders.get("/products/"+id).accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
			.andExpect(status().isNotFound());
	}
	
	
	@Test
	@DisplayName("Should delete Product successfully and status code as 204")
	void testDeleteProductApi_Success() throws Exception {
		Long id = 1L;
		
		doNothing().when(productService).deleteProduct(id);
		
		RequestBuilder request = MockMvcRequestBuilders.delete("/products/"+id).accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().isNoContent()); 
		
		verify(productService).deleteProduct(id);
	}
	
	@Test
	@DisplayName("Should delete Product fails when id is null and status code as 400")
	void testDeleteProductApi_Fails() throws Exception {
		Long id = null;
		
		doNothing().when(productService).deleteProduct(id);
		
		RequestBuilder request = MockMvcRequestBuilders.delete("/products/"+id).accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().isBadRequest()); 
		
	}
	
	
}

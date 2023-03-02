package com.galaxe.AjioClone.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.galaxe.AjioClone.entity.ProductDetails;
import com.galaxe.AjioClone.exceptions.NegativePriceException;
import com.galaxe.AjioClone.exceptions.OfferPriceException;
import com.galaxe.AjioClone.repository.ProductRepository;
import com.galaxe.AjioClone.serviceImpl.ProductServiceImpl;



@DataJpaTest
class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	
	private ProductDetails productDetails;
	
	@BeforeEach
	public void setUp() {
		
		productDetails = ProductDetails.builder()
				.productId(1L)
				.brandName("Puma")
				.description("Mens's wear")
				.actualPrice(2000)
				.offerPrice(1200)
				.imageUrl("this is the url")
				.build();
		
	}
	@DisplayName("JUnit test for saveEmployee method")
	@Test
	public void addNewProductTest() {
		
		when(productRepository.findById(productDetails.getProductId()))
		.thenReturn(Optional.empty());
		
		when(productRepository.save(productDetails)).thenReturn(productDetails);
		System.out.println(productRepository);
		System.out.println(productServiceImpl);
		
		String savedProduct = "";
		try {
			savedProduct = productServiceImpl.addAProduct(productDetails);
		} catch (NegativePriceException | OfferPriceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(savedProduct);
		assertEquals("Product added Successfully", savedProduct);
		
	}
	
}

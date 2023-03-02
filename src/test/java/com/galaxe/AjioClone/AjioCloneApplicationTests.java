package com.galaxe.AjioClone;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.galaxe.AjioClone.dto.ProductDetailsDto;
import com.galaxe.AjioClone.entity.ProductCategory;
import com.galaxe.AjioClone.entity.ProductDetails;
import com.galaxe.AjioClone.exceptions.ItemNotFoundException;
import com.galaxe.AjioClone.repository.ProductRepository;
import com.galaxe.AjioClone.service.ProductService;


@SpringBootTest
class AjioCloneApplicationTests {

	@Autowired
	private ProductService productService;
	
	@MockBean
	private ProductRepository productRepository;
	
	@BeforeEach
	void setUp() {
		
		Optional<ProductDetails> product = Optional.of(new ProductDetails(1L,"Woodland","the new shoe","the image url",2000.0,1200.0,ProductCategory.MEN));
		Mockito.when(productRepository.findById(1L)).thenReturn(product);
			
	}
		
	@Test
	public void getProductByIdTest() throws ItemNotFoundException {
		String brandName = "Woodland";
		ProductDetails productId = productService.getProductById(1L);
		assertEquals(brandName, productId.getBrandName());
		
	}
	
	
}

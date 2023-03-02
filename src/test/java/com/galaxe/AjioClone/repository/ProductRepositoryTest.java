package com.galaxe.AjioClone.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.galaxe.AjioClone.entity.ProductCategory;
import com.galaxe.AjioClone.entity.ProductDetails;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void addANewProductTest() {
		ProductDetails productDetails = new ProductDetails(1,"woodland","shoe","image of shoe",200,100,ProductCategory.MEN);
		ProductDetails product =productRepository.save(productDetails);
		assertNotNull(product);
	}

	@Test
	public void findProductByCategory() {
		String CategoryName ="MEN";
		List<ProductDetails> category =  productRepository.findByProductCategory(ProductCategory.MEN);
		assertThat(category.contains(CategoryName));
	}
	
	@Test
	public void updateProductTest() {
		String brandName ="Nike";
		ProductDetails productDetails = new ProductDetails(1,brandName,"shoe","image of shoe",200,100,ProductCategory.MEN);
		productDetails.setActualPrice(5000);
	}
}

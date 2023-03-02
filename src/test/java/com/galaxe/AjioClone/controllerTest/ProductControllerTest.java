package com.galaxe.AjioClone.controllerTest;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galaxe.AjioClone.controller.ProductController;
import com.galaxe.AjioClone.dto.ProductDetailsDto;
import com.galaxe.AjioClone.entity.ProductCategory;
import com.galaxe.AjioClone.entity.ProductDetails;
import com.galaxe.AjioClone.exceptions.EmptyProductListException;
import com.galaxe.AjioClone.exceptions.ItemNotFoundException;
import com.galaxe.AjioClone.repository.ProductRepository;
import com.galaxe.AjioClone.serviceImpl.ProductServiceImpl;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@MockBean
	private ProductServiceImpl productServiceImpl;

	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testSaveANewProduct() throws Exception {

		ProductDetails productDetails = new ProductDetails();
		productDetails.setProductId(1);
		productDetails.setBrandName("woodland");
		productDetails.setDescription("this is shoe");
		productDetails.setActualPrice(2000);
		productDetails.setOfferPrice(1400);
		productDetails.setImageUrl("this is the image url");
		productDetails.setProductCategory(ProductCategory.MEN);

		Mockito.when(productServiceImpl.addAProduct(productDetails)).thenReturn("string saved successfully");
		mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(productDetails))).andExpect(status().isOk());

	}

	@Test
	public void testGetAllProducts() throws Exception {
		List<ProductDetailsDto> products = new ArrayList<ProductDetailsDto>();
		Set<ProductCategory> category = new HashSet<>();

		products.add(new ProductDetailsDto(1, "Woodland", "Shoe", "shoe image", 200, 100, ProductCategory.MEN));
		products.add(new ProductDetailsDto(2, "AllenSolly", "Shirt", "shirt image", 200, 100, ProductCategory.MEN));
		products.add(new ProductDetailsDto(3, "Gucci", "Watch", " watch image", 200, 100, ProductCategory.MEN));

		Mockito.when(productServiceImpl.showAllProducts()).thenReturn(products);
		mockMvc.perform(get("/all")).andExpect(status().isOk());
	}

	@Test
	public void testGetProductById() throws Exception {

		ProductDetails productDetails = new ProductDetails(1, "woodland", "shoe", "image of shoe", 1200, 900,
				ProductCategory.MEN);

		Mockito.when(productServiceImpl.findProductById(1)).thenReturn(productDetails);
		mockMvc.perform(get("/id/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.brandName").value("woodland"));
				

	}

	@Test
	public void testGetProductByCategory() throws Exception {

		List<ProductDetails> products = new ArrayList<ProductDetails>();

		products.add(new ProductDetails(1, "Woodland", "Shoe", "shoe image", 200, 100, ProductCategory.MEN));
		products.add(new ProductDetails(2, "AllenSolly", "Shirt", "shirt image", 200, 100, ProductCategory.MEN));
		products.add(new ProductDetails(3, "Gucci", "Watch", " watch image", 200, 100, ProductCategory.MEN));

		Mockito.when(productServiceImpl.getProductsByCategory(ProductCategory.MEN)).thenReturn(products);
		mockMvc.perform(get("/all/{catageory}", ProductCategory.MEN)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
	
	@Test
	public void testDeleteProductById() throws Exception {
		
		ProductDetails productDetails = new ProductDetails(1, "woodland", "shoe", "image of shoe", 1200, 900,ProductCategory.MEN);
		Mockito.when(productServiceImpl.deleteProduct(1)).thenReturn("prduct deleted");
		mockMvc.perform(delete("/delete/{id}",1)
				.contentType(MediaType.APPLICATION_JSON));
		        
		
		
	}
}

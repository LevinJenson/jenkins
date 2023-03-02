package com.galaxe.AjioClone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.galaxe.AjioClone.dto.ProductDetailsDto;
import com.galaxe.AjioClone.entity.ProductCategory;
import com.galaxe.AjioClone.entity.ProductDetails;
import com.galaxe.AjioClone.exceptions.EmptyProductListException;
import com.galaxe.AjioClone.exceptions.ItemNotFoundException;
import com.galaxe.AjioClone.exceptions.NegativePriceException;
import com.galaxe.AjioClone.exceptions.OfferPriceException;
import com.galaxe.AjioClone.service.ProductService;



/*Controller class*/

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class ProductController {

	@Autowired
	ProductService productService;

	
	/* To save data into database*/
	
	@PostMapping
	public ResponseEntity<String> addNewItem(@RequestBody ProductDetails productDetails) {
		try {
			return new ResponseEntity<>(productService.addAProduct(productDetails), HttpStatus.OK);
		} catch (NegativePriceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} catch (OfferPriceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}

	}

	
	/*To get all products from the database*/
	
	@GetMapping("/all")
	public ResponseEntity<List<ProductDetailsDto>> getAllProducts() {

		return new ResponseEntity<List<ProductDetailsDto>>(productService.showAllProducts(), HttpStatus.OK);
	}
	
	
	/*To get the products based on the category*/
	
	@GetMapping("/all/{catageory}")
	public ResponseEntity<List<ProductDetails>> getAllFoodItemsByCatageory(@PathVariable("catageory") ProductCategory cat){
		try {
		return new ResponseEntity<List<ProductDetails>>(productService.getProductsByCategory(cat),HttpStatus.OK);
		}
		catch(EmptyProductListException e) {
			return new ResponseEntity<List<ProductDetails>>(HttpStatus.NOT_FOUND);
		}
		catch(Exception e) {
			
			return new ResponseEntity<List<ProductDetails>>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	/*To delete particular product from the database*/
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<String>(productService.deleteProduct(id),HttpStatus.ACCEPTED);
		}
		catch(ItemNotFoundException e) {
			
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	/*To update the existing product in the database*/
	
	@PutMapping("/updateProduct")
	public ResponseEntity<String> updateProduct(@RequestBody ProductDetails productDetails) {
		try {
		return new ResponseEntity<String>(productService.updateExistingProduct(productDetails),HttpStatus.OK);
		}
		catch(ItemNotFoundException e) {
			
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	
	/*To get particular product from the database based on product id*/
	
	@GetMapping("/id")
	public ResponseEntity<ProductDetails> getProductById(@RequestParam Long id) {
      // from the front-end i am passing the values as a params
		try {
			return new ResponseEntity<ProductDetails>(productService.findProductById(id),HttpStatus.ACCEPTED);
		}
		catch(ItemNotFoundException e) {
			
			return new ResponseEntity<ProductDetails>(HttpStatus.NOT_FOUND);
		}
	}
	
	
/*To get particular product from the database based on product id*/
	
	@GetMapping("/id/{id}")
	public ResponseEntity<ProductDetails> getParticularProductById(@PathVariable("id") long id) {
     
		try {
			return new ResponseEntity<ProductDetails>(productService.findProductById(id),HttpStatus.ACCEPTED);
		}
		catch(ItemNotFoundException e) {
			
			return new ResponseEntity<ProductDetails>(HttpStatus.NOT_FOUND);
		}
	}

}
package com.galaxe.AjioClone.service;

import java.util.List;

import com.galaxe.AjioClone.dto.ProductDetailsDto;
import com.galaxe.AjioClone.entity.ProductCategory;
import com.galaxe.AjioClone.entity.ProductDetails;
import com.galaxe.AjioClone.exceptions.EmptyProductListException;
import com.galaxe.AjioClone.exceptions.ItemNotFoundException;
import com.galaxe.AjioClone.exceptions.NegativePriceException;
import com.galaxe.AjioClone.exceptions.OfferPriceException;

/*Service Interface*/

public interface ProductService {
	
	/*Method to save product details */
	public String addAProduct(ProductDetails productDetails) throws NegativePriceException,OfferPriceException;
	
	/* Method to Show all products */
	public List<ProductDetailsDto> showAllProducts();
	
	/*Method to delete a particular product */
	public String deleteProduct(long productId) throws ItemNotFoundException;
	
	/*Method to upadate the existing product with change in details */
	public String updateExistingProduct(ProductDetails productDetails) throws ItemNotFoundException;
	
	/*Method to display the products based on the category */
	public List<ProductDetails> getProductsByCategory(ProductCategory productCategory) throws EmptyProductListException;
	
    /* Method to show the particular product based on id*/
	public ProductDetails findProductById(long productId) throws ItemNotFoundException;
	
	/* Method to show the particular product based on id*/
	public ProductDetails getProductById(long productId) throws ItemNotFoundException;
}

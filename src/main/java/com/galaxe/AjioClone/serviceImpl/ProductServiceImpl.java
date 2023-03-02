package com.galaxe.AjioClone.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxe.AjioClone.dto.ProductDetailsDto;
import com.galaxe.AjioClone.dto.ProductDetailsDtoMapperService;
import com.galaxe.AjioClone.entity.ProductCategory;
import com.galaxe.AjioClone.entity.ProductDetails;
import com.galaxe.AjioClone.exceptions.EmptyProductListException;
import com.galaxe.AjioClone.exceptions.ItemNotFoundException;
import com.galaxe.AjioClone.exceptions.NegativePriceException;
import com.galaxe.AjioClone.exceptions.OfferPriceException;
import com.galaxe.AjioClone.repository.ProductRepository;
import com.galaxe.AjioClone.service.ProductService;

/*Service Implementation class*/

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired(required = false)
	ProductDetailsDtoMapperService productDetailsDtoMapperService;

	/* Method to save product details */
	@Override
	public String addAProduct(ProductDetails productDetails) throws NegativePriceException, OfferPriceException {
		String message = null;
		if (productDetails.getActualPrice() < 0 || productDetails.getOfferPrice() < 0) {
			throw new NegativePriceException("Price Should not be negative");

		} else if (productDetails.getActualPrice() < productDetails.getOfferPrice()) {
			throw new OfferPriceException("Offer price should be less than actual price");
		}

		else {
			productRepository.save(productDetails);
			message = "Product added Successfully";
		}
		return message;
	}

	/* Method to Show all products - here i am showing the data's from mapper class */
	@Override
	public List<ProductDetailsDto> showAllProducts() {
		List<ProductDetails> productsList = productRepository.findAll();

		List<ProductDetailsDto> productsDtoList = new ArrayList<ProductDetailsDto>();
		productsList.forEach((product) -> {
			productsDtoList.add(productDetailsDtoMapperService.convertProductListToDto(product));
		});

		return productsDtoList;
	}

	/* Method to delete a particular product */
	@Override
	public String deleteProduct(long productId) throws ItemNotFoundException {
		String message;

		if (!productRepository.existsById(productId)) {

			throw new ItemNotFoundException("Item Not Found to delete");
		} else {
			productRepository.deleteById(productId);
			message = "Deleted Successfully";
		}

		return message;
	}

	/* Method to upadate the existing product with change in details */
	@Override
	public String updateExistingProduct(ProductDetails productDetails) throws ItemNotFoundException {
		String message;

		if (!productRepository.existsById(productDetails.getProductId())) {

			throw new ItemNotFoundException("Item Not Found to Update");
		} else {
			productRepository.save(productDetails);
			message = "Updated Successfully";
		}
		return message;
	}

	/* Method to display the products based on the category */
	@Override
	public List<ProductDetails> getProductsByCategory(ProductCategory productCategory)
			throws EmptyProductListException {
		if (productRepository.findByProductCategory(productCategory).isEmpty()) {

			throw new EmptyProductListException("No Item found with Selected Catageory");
		}

		return (productRepository.findByProductCategory(productCategory));
	}

	/* method to display particular product by id */
	@Override
	public ProductDetails findProductById(long productId) throws ItemNotFoundException {

		Optional<ProductDetails> product = productRepository.findById(productId);
		return product.get();

	}
	
	/* method to display particular product by id -This method is for cart */
	@Override
	public ProductDetails getProductById(long productId) throws ItemNotFoundException {

		Optional<ProductDetails> product = productRepository.findById(productId);
		return product.get();

	}

}

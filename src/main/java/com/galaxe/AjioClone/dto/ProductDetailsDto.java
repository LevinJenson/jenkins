package com.galaxe.AjioClone.dto;


import com.galaxe.AjioClone.entity.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*product details dto class*/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDetailsDto {

	private long productId;
	private String brandName;
	private String description;
	private String imageUrl;
	private double actualPrice;
	private double offerPrice;
	private ProductCategory productCategory;
}

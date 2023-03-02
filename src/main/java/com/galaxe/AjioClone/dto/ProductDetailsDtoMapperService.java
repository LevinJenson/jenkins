package com.galaxe.AjioClone.dto;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.galaxe.AjioClone.entity.ProductDetails;

/*Interface to convert entity to dto and convert the dto to dtoEntity*/

@Mapper
public interface ProductDetailsDtoMapperService {

	@Mapping(target = "productId", source = "productDetails.productId")
	@Mapping(target = "brandName", source = "productDetails.brandName")
	@Mapping(target = "description", source = "productDetails.description")
	@Mapping(target = "imageUrl", source = "productDetails.imageUrl")
	@Mapping(target = "actualPrice", source = "productDetails.actualPrice")
	@Mapping(target = "offerPrice", source = "productDetails.offerPrice")
	@Mapping(target = "productCategory", source = "productDetails.productCategory")
	public ProductDetailsDto convertProductListToDto(ProductDetails productDetails);
	
	
	
	
	@Mapping(target = "productId", source = "productDetailsDto.productId")
	@Mapping(target = "brandName", source = "productDetailsDto.brandName")
	@Mapping(target = "description", source = "productDetailsDto.description")
	@Mapping(target = "imageUrl", source = "productDetailsDto.imageUrl")
	@Mapping(target = "actualPrice", source = "productDetailsDto.actualPrice")
	@Mapping(target = "offerPrice", source = "productDetailsDto.offerPrice")
	@Mapping(target = "productCategory", source = "productDetailsDto.productCategory")
	public ProductDetails convertProductListDtoToEntity(ProductDetailsDto productDetailsDto);
	
}

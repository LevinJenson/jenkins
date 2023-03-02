
package com.galaxe.AjioClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.galaxe.AjioClone.dto.ProductDetailsDto;
import com.galaxe.AjioClone.dto.ProductDetailsDtoMapperService;
import com.galaxe.AjioClone.dto.ProductDetailsDtoMapperServiceImpl;

@SpringBootApplication
public class AjioCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(AjioCloneApplication.class, args);
		System.out.println("Ajio Application Started...");
	}

	@Bean
	public ProductDetailsDtoMapperService getProductDetailsDtoMapperService() {
		return new ProductDetailsDtoMapperServiceImpl();
	}
	
}

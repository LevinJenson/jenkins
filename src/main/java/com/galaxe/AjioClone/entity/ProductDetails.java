package com.galaxe.AjioClone.entity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*product details class*/

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class ProductDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	
	private String brandName;
	private String description;
	private String imageUrl;
	private double actualPrice;
	private double offerPrice;
	@Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
	
	
	
}

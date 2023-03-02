package com.galaxe.AjioClone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.AjioClone.entity.ProductCategory;
import com.galaxe.AjioClone.entity.ProductDetails;

/*Repository interface - the repository used here is JPA*/

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails, Long> {
	public List<ProductDetails> findByProductCategory(ProductCategory catageory);
}

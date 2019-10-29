package com.spring.springmvc_upload.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.springmvc_upload.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

//	@Query("SELECT p FROM Product p WHERE p.id = p.images.product_id")
//	public List<Product> findProductAndImages();
}

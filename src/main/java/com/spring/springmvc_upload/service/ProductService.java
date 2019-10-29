package com.spring.springmvc_upload.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springmvc_upload.entities.Product;
import com.spring.springmvc_upload.entities.Size;
import com.spring.springmvc_upload.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	SizeService sizeService;

	public List<Product> getAllProduct() {
		return (List<Product>) productRepository.findAll();
	}

	public Product findProduct(int id) {

		return productRepository.findOne(id);
	}

	public Product saveProductS(Product p, String[] size) {
		p = productRepository.save(p);
		List<Size> sizes = new ArrayList();
		List<Product> products = new ArrayList();
		products.add(p);
		for (int i = 0; i < size.length; i++) {
			Size si = new Size();
			si.setName(size[i]);
			si.setProducts(products);
			sizeService.save(si);
			sizes.add(si);
		}
		p.setSizes(sizes);
		p = productRepository.save(p);

		return p;
	}
	
	public Product save(Product p) {
		return productRepository.save(p);
	}

	public boolean isDelete(int id) {

		productRepository.delete(id);
		return productRepository.exists(id);
	}
}

package com.example.crud_operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public Page<Product> getAllProducts(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return productRepository.findAll(pageable);
	}

	public Product createProduct(Product product) {
		if (product.getCategory() != null) {
			Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
			if (category == null) {
				throw new RuntimeException("Category not found");
			}
			product.setCategory(category);
		}
		return productRepository.save(product);
	}

	public Product getProductById(long id) {
		return productRepository.findById(id).orElse(null);
	}

	public Product updateProduct(Long id, Product product) {
		if (productRepository.existsById(id)) {
			if (product.getCategory() != null) {
				Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
				if (category == null) {
					throw new RuntimeException("Category not found");
				}
				product.setCategory(category);
			}
			product.setId(id);
			return productRepository.save(product);
		}
		return null;
	}

	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}

}

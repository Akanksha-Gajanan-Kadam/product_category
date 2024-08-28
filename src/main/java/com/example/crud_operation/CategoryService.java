package com.example.crud_operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public Page<Category> getAllCategories(int page, int size) {
		return categoryRepository.findAll(PageRequest.of(page, size));
	}

	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category getCategoryById(long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	public Category updateCategory(long id, Category category) {
		if (categoryRepository.existsById(id)) {
			category.setId(id);
			return categoryRepository.save(category);
		}
		return null;
	}

	public void deleteCategory(long id) {
		categoryRepository.deleteById(id);
	}
}

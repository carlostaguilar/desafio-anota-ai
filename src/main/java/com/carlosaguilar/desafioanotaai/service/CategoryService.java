package com.carlosaguilar.desafioanotaai.service;

import com.carlosaguilar.desafioanotaai.domain.category.Category;
import com.carlosaguilar.desafioanotaai.domain.category.dto.CategoryDTO;
import com.carlosaguilar.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.carlosaguilar.desafioanotaai.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category insert(CategoryDTO categoryDTO) {
        Category newCategory = new Category(categoryDTO);
        this.categoryRepository.save(newCategory);
        return newCategory;
    }

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Category update(String id, CategoryDTO categoryDTO) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryDTO.title().isEmpty()) category.setTitle(categoryDTO.title());
        if (!categoryDTO.description().isEmpty()) category.setDescription(categoryDTO.description());

        this.categoryRepository.save(category);

        return category;
    }

    public void delete(String id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        this.categoryRepository.delete(category);
    }
}

package com.carlosaguilar.desafioanotaai.service;

import com.carlosaguilar.desafioanotaai.domain.aws.dto.MessageDTO;
import com.carlosaguilar.desafioanotaai.domain.category.Category;
import com.carlosaguilar.desafioanotaai.domain.category.dto.CategoryDTO;
import com.carlosaguilar.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.carlosaguilar.desafioanotaai.repositories.CategoryRepository;
import com.carlosaguilar.desafioanotaai.service.aws.AwsSnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AwsSnsService snsService;

    public Category insert(CategoryDTO categoryDTO) {
        Category newCategory = new Category(categoryDTO);
        this.categoryRepository.save(newCategory);

        this.snsService.publish(new MessageDTO(newCategory.toString()));

        return newCategory;
    }

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Optional<Category> getById(String id) {
        return this.categoryRepository.findById(id);
    }

    public Category update(String id, CategoryDTO categoryDTO) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryDTO.title().isEmpty()) category.setTitle(categoryDTO.title());
        if (!categoryDTO.description().isEmpty()) category.setDescription(categoryDTO.description());

        this.categoryRepository.save(category);

        this.snsService.publish(new MessageDTO(category.toString()));

        return category;
    }

    public void delete(String id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        this.categoryRepository.delete(category);
    }
}

package com.carlosaguilar.desafioanotaai.service;

import com.carlosaguilar.desafioanotaai.domain.category.Category;
import com.carlosaguilar.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.carlosaguilar.desafioanotaai.domain.product.Product;
import com.carlosaguilar.desafioanotaai.domain.product.dto.ProductDTO;
import com.carlosaguilar.desafioanotaai.domain.product.exceptions.ProductNotFoundException;
import com.carlosaguilar.desafioanotaai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    public Product insert(ProductDTO productDTO) {
        Category category = this.categoryService.getById(productDTO.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Product newProduct = new Product(productDTO);
        newProduct.setCategory(category);
        this.productRepository.save(newProduct);
        return newProduct;
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Product update(String id, ProductDTO productDTO) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        if(productDTO.categoryId() != null) {
            this.categoryService.getById(productDTO.categoryId())
                    .ifPresent(product::setCategory);
        }

        if (!productDTO.title().isEmpty()) product.setTitle(productDTO.title());
        if (!productDTO.description().isEmpty()) product.setDescription(productDTO.description());
        if (!(productDTO.price() == null)) product.setPrice(productDTO.price());

        this.productRepository.save(product);

        return product;
    }

    public void delete(String id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.productRepository.delete(product);
    }
}

package com.carlosaguilar.desafioanotaai.service;

import com.carlosaguilar.desafioanotaai.domain.aws.dto.MessageDTO;
import com.carlosaguilar.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.carlosaguilar.desafioanotaai.domain.product.Product;
import com.carlosaguilar.desafioanotaai.domain.product.dto.ProductDTO;
import com.carlosaguilar.desafioanotaai.domain.product.exceptions.ProductNotFoundException;
import com.carlosaguilar.desafioanotaai.repositories.ProductRepository;
import com.carlosaguilar.desafioanotaai.service.aws.AwsSnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AwsSnsService snsService;

    public Product insert(ProductDTO productDTO) {
        this.categoryService.getById(productDTO.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Product newProduct = new Product(productDTO);

        this.productRepository.save(newProduct);

        this.snsService.publish(new MessageDTO(newProduct.toString()));

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
                    .orElseThrow(CategoryNotFoundException::new);
            product.setCategory(productDTO.categoryId());
        }

        if (!productDTO.title().isEmpty()) product.setTitle(productDTO.title());
        if (!productDTO.description().isEmpty()) product.setDescription(productDTO.description());
        if (!(productDTO.price() == null)) product.setPrice(productDTO.price());

        this.productRepository.save(product);

        this.snsService.publish(new MessageDTO(product.toString()));

        return product;
    }

    public void delete(String id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.productRepository.delete(product);
    }
}

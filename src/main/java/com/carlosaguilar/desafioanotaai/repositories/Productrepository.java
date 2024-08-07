package com.carlosaguilar.desafioanotaai.repositories;

import com.carlosaguilar.desafioanotaai.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Productrepository extends MongoRepository<Product, String> {
}

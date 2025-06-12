package org.africa.semicolon.data.repositories;

import org.africa.semicolon.data.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepo extends MongoRepository<Product, String> {
    List<Product> findAllByCategoryId(String categoryId);
}

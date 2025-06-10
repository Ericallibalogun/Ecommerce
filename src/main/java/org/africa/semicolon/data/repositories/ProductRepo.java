package org.africa.semicolon.data.repositories;

import org.africa.semicolon.data.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product, String> {
}

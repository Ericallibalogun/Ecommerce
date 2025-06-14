package org.africa.semicolon.data.repositories;

import org.africa.semicolon.data.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepo extends MongoRepository<Product, String> {
    List<Product> findAllByCategoryId(String categoryId);

    @Query("{'$or': [{'name': {$regex:  ?0,$options:  'i'}},{'description': {$regex: ?0,$options: 'i'}}]}")
    List<Product> searchByKeyword(String keyword);

    List<Product> findByCategoryId(String categoryId);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findByQuantityGreaterThan(int quantity);


}

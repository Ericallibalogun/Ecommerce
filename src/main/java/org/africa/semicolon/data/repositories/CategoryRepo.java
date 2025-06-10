package org.africa.semicolon.data.repositories;

import org.africa.semicolon.data.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepo extends MongoRepository<Category, String> {
}

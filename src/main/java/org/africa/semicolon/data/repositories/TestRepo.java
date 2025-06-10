package org.africa.semicolon.data.repositories;

import org.africa.semicolon.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepo extends MongoRepository<User, String> {
}

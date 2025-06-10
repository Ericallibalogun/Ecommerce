package org.africa.semicolon.data.repositories;

import org.africa.semicolon.data.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order, String> {
}

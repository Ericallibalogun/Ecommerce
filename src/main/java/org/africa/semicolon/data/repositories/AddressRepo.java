package org.africa.semicolon.data.repositories;

import org.africa.semicolon.data.models.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepo extends MongoRepository<Address, String> {
}

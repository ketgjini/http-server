package com.ketrina.httpserver.repository;

import com.ketrina.httpserver.model.entities.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {
}

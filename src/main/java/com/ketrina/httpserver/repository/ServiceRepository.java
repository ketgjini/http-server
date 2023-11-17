package com.ketrina.httpserver.repository;

import com.ketrina.httpserver.model.entities.Service;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends MongoRepository<Service, String> {
}

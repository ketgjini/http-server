package com.ketrina.httpserver.repository;

import com.ketrina.httpserver.model.entities.Service;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Service} entities in MongoDB.
 * Uses Spring Data MongoDB and extends {@link org.springframework.data.mongodb.repository.MongoRepository}.
 *
 * @author Ketrina
 */
@Repository
public interface ServiceRepository extends MongoRepository<Service, String> {
}

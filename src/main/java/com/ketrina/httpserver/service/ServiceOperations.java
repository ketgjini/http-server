package com.ketrina.httpserver.service;

import com.ketrina.httpserver.exception.ServiceNotFoundException;
import com.ketrina.httpserver.model.entities.Service;
import com.ketrina.httpserver.repository.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Service class for performing operations on {@link Service} entities.
 * Utilizes Spring's caching mechanism with cache name "services".
 *
 * @author Ketrina
 */
@org.springframework.stereotype.Service
@CacheConfig(cacheNames = "services")
public class ServiceOperations {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceOperations.class);

    private final ServiceRepository serviceRepository;

    /**
     * Parametrized Constructor.
     * @param serviceRepository The repository for managing {@link Service} entities.
     */
    public ServiceOperations(final ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Cacheable(key = "#id")
    public synchronized Service getServiceById(final String id) {
        LOGGER.info("Retrieving Service with id {}", id);
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Could not find the requested Service with id " + id ));
    }

    @Cacheable()
    public synchronized List<Service> getAllServices() {
        LOGGER.info("Retrieving all Services...");
        return serviceRepository.findAll();
    }

    @CachePut(key = "#service.id")
    public synchronized Service createService(final Service service) {
        LOGGER.info("Creating a Service...");
        return serviceRepository.save(service);
    }

    @CachePut(key = "#id")
    public synchronized Service updateService(final String id, final Service service) {
        LOGGER.info("Updating Service {} ", id);
        final Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Service not found for ID: " + id));

        // Update the existing service with the new data
        existingService.setResources(service.getResources());
        return serviceRepository.save(existingService);
    }
}

package com.ketrina.httpserver.service;

import com.ketrina.httpserver.exception.ServiceNotFoundException;
import com.ketrina.httpserver.model.entities.Service;
import com.ketrina.httpserver.repository.ServiceRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceOperations {

    private final ServiceRepository serviceRepository;

    public ServiceOperations(final ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Service getServiceById(final String id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Could not find the requested Service with id " + id ));
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Service createService(final Service service) {
        return serviceRepository.save(service);
    }

    public synchronized Service updateService(final String id, final Service service) {
        final Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Service not found for ID: " + id));

        // Update the existing service with the new data
        existingService.setResources(service.getResources());
        return serviceRepository.save(existingService);
    }
}

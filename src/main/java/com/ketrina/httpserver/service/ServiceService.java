package com.ketrina.httpserver.service;

import com.ketrina.httpserver.exceptions.ServiceNotFoundException;
import com.ketrina.httpserver.model.entities.Service;
import com.ketrina.httpserver.repository.ServiceRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Service getServiceById(final String id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Could not find the requested Service with id " + id ));
    }

    public Service createService(Service service) {
        return serviceRepository.save(service);
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }
}

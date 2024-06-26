package com.ketrina.httpserver.controller;

import com.ketrina.httpserver.model.entities.Service;
import com.ketrina.httpserver.model.input.ServiceInput;
import com.ketrina.httpserver.model.mapper.ServiceMapper;
import com.ketrina.httpserver.model.response.ServiceResponse;
import com.ketrina.httpserver.service.ServiceOperations;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing {@link Service} entities.
 * Handles operations such as retrieving, creating, and updating services.
 *
 * @author Ketrina
 */
@RestController
@RequestMapping("/api/v1/services")
@Profile("test")
public class RestServiceController {

    private final ServiceOperations serviceOperations;
    private final ServiceMapper serviceMapper;

    /**
     * Parametrized constructor.
     */
    public RestServiceController(final ServiceOperations serviceOperations, final ServiceMapper serviceMapper) {
        this.serviceOperations = serviceOperations;
        this.serviceMapper = serviceMapper;
    }

    /**
     * Endpoint to retrieve a service by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getServiceById(@PathVariable final String id) {
        final ServiceResponse service = serviceMapper.toServiceResponse(serviceOperations.getServiceById(id));
        return service != null
                ? new ResponseEntity<>(service, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint to retrieve all services.
     */
    @GetMapping
    public ResponseEntity<List<ServiceResponse>> geAllServices() {
        final List<ServiceResponse> services = serviceMapper.toServiceResponseList(serviceOperations.getAllServices());
        return services != null
                ? new ResponseEntity<>(services, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint to create a new service.
     */
    @PostMapping
    public ResponseEntity<ServiceResponse> createService(@RequestBody final ServiceInput input) {
        final Service createdService = serviceOperations.createService(serviceMapper.toServiceEntity(input));
        return new ResponseEntity<>(serviceMapper.toServiceResponse(createdService), HttpStatus.CREATED);
    }

    /**
     * Endpoint to update an existing service.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> updateService(@PathVariable final String id, @RequestBody final ServiceInput input) {
        final Service updatedService = serviceOperations.updateService(id, serviceMapper.toServiceEntity(input));
        return updatedService != null
                ? new ResponseEntity<>(serviceMapper.toServiceResponse(updatedService), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

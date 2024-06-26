package com.ketrina.httpserver.controller;

import com.ketrina.httpserver.model.entities.Service;
import com.ketrina.httpserver.model.input.ServiceInput;
import com.ketrina.httpserver.model.mapper.ServiceMapper;
import com.ketrina.httpserver.model.response.ServiceResponse;
import com.ketrina.httpserver.service.ServiceOperations;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * GraphQL Controller for managing {@link Service} entities.
 * Handles GraphQL queries and mutations for Service entities.
 *
 * @author Ketrina
 */
@Controller
public class GraphQLServiceController {

    private final ServiceMapper serviceMapper;
    private final ServiceOperations serviceOperations;

    /**
     * Parametrized constructor.
     */
    public GraphQLServiceController(final ServiceMapper serviceMapper, final ServiceOperations serviceOperations) {
        this.serviceMapper = serviceMapper;
        this.serviceOperations = serviceOperations;
    }

    /**
     * Query mapping to retrieve a service by ID.
     */
    @QueryMapping
    public ServiceResponse getServiceById(@Argument final String id) {
        return serviceMapper.toServiceResponse(serviceOperations.getServiceById(id));
    }

    /**
     * Query mapping to retrieve all services.
     */
    @QueryMapping
    public List<ServiceResponse> getAllServices() {
        return serviceMapper.toServiceResponseList(serviceOperations.getAllServices());
    }

    /**
     * Mutation mapping to create a new service.
     */
    @MutationMapping
    public ServiceResponse createService(@Argument final ServiceInput serviceInput) {
        final Service service = serviceOperations.createService(serviceMapper.toServiceEntity(serviceInput));
        return serviceMapper.toServiceResponse(service);
    }

    /**
     * Mutation mapping to update an existing service.
     */
    @MutationMapping
    public ServiceResponse updateService(@Argument final String id, @Argument final ServiceInput serviceInput) {
        final Service service = serviceOperations.updateService(id, serviceMapper.toServiceEntity(serviceInput));
        return serviceMapper.toServiceResponse(service);
    }
}

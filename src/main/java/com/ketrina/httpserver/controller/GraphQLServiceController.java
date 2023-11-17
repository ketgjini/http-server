package com.ketrina.httpserver.controller;

import com.ketrina.httpserver.model.entities.Service;
import com.ketrina.httpserver.model.input.ServiceInput;
import com.ketrina.httpserver.model.mapper.ServiceMapper;
import com.ketrina.httpserver.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphQLServiceController.class);

    private final ServiceMapper serviceMapper;
    private final ServiceService serviceService;

    public GraphQLServiceController(final ServiceMapper serviceMapper, final ServiceService serviceService) {
        this.serviceMapper = serviceMapper;
        this.serviceService = serviceService;
    }

    @QueryMapping
    public Service getServiceById(@Argument final String id) {
        LOGGER.info("Retrieving Service with id {}", id);
        return serviceService.getServiceById(id);
    }

    @QueryMapping
    public List<Service> getAllServices() {
        LOGGER.info("Retrieving all Services...");
        return serviceService.getAllServices();
    }

    @MutationMapping
    public Service createService(@Argument final ServiceInput serviceInput) {
        LOGGER.info("Creating a Service...");
        return serviceService.createService(serviceMapper.toServiceEntity(serviceInput));
    }
}

package com.ketrina.httpserver.controller;

import com.ketrina.httpserver.model.entities.Owner;
import com.ketrina.httpserver.model.entities.Resource;
import com.ketrina.httpserver.model.entities.Service;
import com.ketrina.httpserver.model.input.OwnerInput;
import com.ketrina.httpserver.model.input.ResourceInput;
import com.ketrina.httpserver.model.input.ServiceInput;
import com.ketrina.httpserver.model.mapper.ServiceMapper;
import com.ketrina.httpserver.model.response.OwnerResponse;
import com.ketrina.httpserver.model.response.ResourceResponse;
import com.ketrina.httpserver.model.response.ServiceResponse;
import com.ketrina.httpserver.service.ServiceOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GraphQLServiceControllerTest {

    @Mock
    private ServiceMapper serviceMapper;

    @Mock
    private ServiceOperations serviceOperations;

    @InjectMocks
    private GraphQLServiceController controller;

    private ServiceInput serviceInput;
    private Service service;
    private List<Service> expectedServices;
    private ServiceResponse serviceResponse;
    private List<ServiceResponse> serviceResponseList;

    @BeforeEach
    public void setup() {
        // Service Input
        OwnerInput ownerInput = new OwnerInput("owner_id_1_1", "Owner 1", "AC001", 1);
        ResourceInput resourceInput = new ResourceInput("resource_id_1", List.of(ownerInput));
        serviceInput = new ServiceInput("service_id_1", List.of(resourceInput));

        // Service
        Owner owner = new Owner("owner_id_1_1", "Owner 1", "AC001", 1);
        Resource resource = new Resource("resource_id_1", List.of(owner));
        service = new Service("service_id_1", List.of(resource));
        expectedServices = Arrays.asList(service);

        // Service Response
        OwnerResponse ownerResponse = new OwnerResponse("owner_id_1_1", "Owner 1", "AC001", 1);
        ResourceResponse resourceResponse = new ResourceResponse("resource_id_1", List.of(ownerResponse));
        serviceResponse = new ServiceResponse("service_id_1", List.of(resourceResponse));
        serviceResponseList = List.of(new ServiceResponse("service_id_1", List.of(resourceResponse)));
    }

    @Test
    public void Should_Get_Service_By_Id() {
        // Mock the ServiceOperations behavior to return a Service
        String serviceId = "service_id_1";
        when(serviceOperations.getServiceById(serviceId)).thenReturn(service);

        // Mock the ServiceMapper behavior to return a ServiceResponse
        when(serviceMapper.toServiceResponse(service)).thenReturn(serviceResponse);

        // Call the method we are testing
        ServiceResponse result = controller.getServiceById(serviceId);

        // Assert that the result matches the expected ServiceResponse
        assertEquals(serviceResponse, result);

        // Verify that the getServiceById method of ServiceOperations is called once
        verify(serviceOperations, times(1)).getServiceById(serviceId);
    }

    @Test
    public void Should_Get_All_Services() {
        // Mock the ServiceOperations behavior to return a list of Services
        when(serviceOperations.getAllServices()).thenReturn(expectedServices);

        // Mock the ServiceMapper behavior to return a list of ServiceResponses
        when(serviceMapper.toServiceResponseList(expectedServices)).thenReturn(serviceResponseList);

        // Call the method we are testing
        List<ServiceResponse> result = controller.getAllServices();

        // Assert that the result matches the expected list of ServiceResponses
        assertEquals(serviceResponseList, result);

        // Verify that the getAllServices method of ServiceOperations is called once
        verify(serviceOperations, times(1)).getAllServices();
    }

    @Test
    public void Should_Create_Service() {
        // Mock the ServiceMapper behavior to return a Service Entity and a ServiceResponse
        when(serviceMapper.toServiceEntity(serviceInput)).thenReturn(service);
        when(serviceMapper.toServiceResponse(service)).thenReturn(serviceResponse);

        // Mock the ServiceOperations behavior to return a Service
        when(serviceOperations.createService(service)).thenReturn(service);

        // Call the method we are testing
        ServiceResponse result = controller.createService(serviceInput);

        // Assert that the result matches the expected ServiceResponse
        assertEquals(serviceResponse, result);

        // Verify that the toServiceEntity and createService methods of ServiceMapper and ServiceOperations are called once each
        verify(serviceMapper, times(1)).toServiceEntity(serviceInput);
        verify(serviceOperations, times(1)).createService(service);
    }

    @Test
    public void Should_Update_Service() {
        // Mock the ServiceMapper behavior to return a Service Entity and a ServiceResponse
        when(serviceMapper.toServiceEntity(serviceInput)).thenReturn(service);
        when(serviceMapper.toServiceResponse(service)).thenReturn(serviceResponse);

        // Mock the ServiceOperations behavior to return a Service
        String serviceId = "service_id_1";
        when(serviceOperations.updateService(serviceId, service)).thenReturn(service);

        // Call the method we are testing
        ServiceResponse result = controller.updateService(serviceId, serviceInput);

        // Assert that the result matches the expected ServiceResponse
        assertEquals(serviceResponse, result);

        // Verify that the toServiceEntity and updateService methods of ServiceMapper and ServiceOperations are called once each
        verify(serviceMapper, times(1)).toServiceEntity(serviceInput);
        verify(serviceOperations, times(1)).updateService(serviceId, service);
    }
}

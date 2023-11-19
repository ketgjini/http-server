package com.ketrina.httpserver.service;

import com.ketrina.httpserver.exception.ServiceNotFoundException;
import com.ketrina.httpserver.model.entities.Owner;
import com.ketrina.httpserver.model.entities.Resource;
import com.ketrina.httpserver.model.entities.Service;
import com.ketrina.httpserver.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

/**
 * Test class for the ServiceOperations component.
 * Uses Mockito for mocking dependencies and JUnit 5 for testing.
 * @author Ketrina
 */
@ExtendWith(MockitoExtension.class)
public class ServiceOperationsTest {
    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceOperations serviceOperations;

    private Service service;

    @BeforeEach
    public void setUp() {
        // Create a Service for testing
        Owner owner = new Owner("owner_id_1_1", "Owner 1", "AC001", 1);
        Resource resource = new Resource("resource_id_1", List.of(owner));
        service = new Service("service_id_1", List.of(resource));
    }

    @Test
    @DisplayName("Should return the Service")
    void Should_Get_Service_By_Id() {
        // Mock the ServiceRepository behavior
        String serviceId = "service_id_1";
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(service));

        // Call the method we are testing
        Service result = serviceOperations.getServiceById(serviceId);

        // Verify that the result matches the expected Service
        assertEquals(service, result);
    }

    @Test
    @DisplayName("Should return ServiceNotFoundException when Service does not exist")
    void Should_Return_Not_Found_For_Get_Service_By_Id() {
        // Mock the ServiceRepository behavior to return empty Optional value
        String serviceId = "service_id_2";
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.empty());

        // Verify that calling the GET method throws ServiceNotFoundException
        assertThrows(ServiceNotFoundException.class, () -> serviceOperations.getServiceById(serviceId));
    }

    @Test
    @DisplayName("Should return all the Services")
    void Should_Get_All_Services() {
        // Mock the ServiceRepository behavior to return a List of Services
        when(serviceRepository.findAll()).thenReturn(List.of(service));

        // Call the method we are testing
        List<Service> result = serviceOperations.getAllServices();

        // Verify that the result matches the expected Service List and the size is 1
        assertEquals(List.of(service), result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should return the created Service")
    void Should_Create_Service() {
        // Mock the ServiceRepository behavior to save a Service
        when(serviceRepository.save(service)).thenReturn(service);

        // Call the method we are testing
        Service result = serviceOperations.createService(service);

        // Verify that the result matches the expected Service we saved
        assertEquals(service, result);
    }

    @Test
    @DisplayName("Should return the updated Service")
    void Should_Update_Service() {
        // Create updated service
        String serviceId = "service_id_2";
        Owner owner = new Owner("owner_id_1_2", "Owner 2", "AC002", 1);
        Resource resource = new Resource("resource_id_1", List.of(owner));
        Service updatedService = new Service(serviceId, List.of(resource));

        // Mock the ServiceRepository behavior
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(service));
        when(serviceRepository.save(service)).thenReturn(updatedService);

        // Call the method we are testing
        Service result = serviceOperations.updateService(serviceId, updatedService);

        // Verify that the result matches the updated Service
        assertEquals(updatedService, result);
        assertEquals(updatedService.getResources().size(), result.getResources().size());
    }

    @Test
    @DisplayName("Should return ServiceNotFoundException when trying to update non-existent Service")
    void Should_Return_Not_Found_On_Update_Service() {
        // Create updated service
        String serviceId = "service_id_2";
        Service updatedService = new Service(serviceId, List.of(new Resource()));

        // Mock the ServiceRepository behavior to return empty Optional
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.empty());

        // Verify that calling the Update method throws ServiceNotFoundException
        assertThrows(ServiceNotFoundException.class, () -> serviceOperations.updateService(serviceId, updatedService));
    }
}

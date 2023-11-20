package com.ketrina.httpserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Profile("test")
public class RestServiceControllerIntegrationTest {

    @Mock
    private ServiceOperations serviceOperations;

    @Mock
    private ServiceMapper serviceMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private RestServiceController restServiceController;

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
    public void testGetServiceById() throws Exception {
        String serviceId = "service_id_1";
        when(serviceMapper.toServiceResponse(any())).thenReturn(serviceResponse);
        when(serviceOperations.getServiceById(serviceId)).thenReturn(service);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/services/{id}", serviceId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(serviceId));
    }

    @Test
    public void testGetAllServices() throws Exception {
        when(serviceMapper.toServiceResponseList(any())).thenReturn(serviceResponseList);
        when(serviceOperations.getAllServices()).thenReturn(expectedServices);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/services"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void testCreateService() throws Exception {
        when(serviceMapper.toServiceResponse(any())).thenReturn(serviceResponse);
        when(serviceOperations.createService(any())).thenReturn(service);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/services")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(serviceInput)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(serviceResponse.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resources[0].id").value("resource_id_1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resources[0].owners[0].id").value("owner_id_1_1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resources[0].owners[0].name").value("Owner 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resources[0].owners[0].accountNumber").value("AC001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resources[0].owners[0].level").value(1));
    }

    @Test
    public void testUpdateService() throws Exception {
        // Change input service
        String serviceId = "service_id_1";
        OwnerInput ownerInput = new OwnerInput("owner_id_1_2", "Owner 2", "AC002", 1);
        serviceInput.getResources().get(0).setOwners(List.of(ownerInput));

        // Change Service
        Owner owner = new Owner("owner_id_1_2", "Owner 2", "AC002", 1);
        service.getResources().get(0).setOwners(List.of(owner));

        // Change response service
        OwnerResponse ownerResponse = new OwnerResponse("owner_id_1_2", "Owner 2", "AC002", 1);
        serviceResponse.getResources().get(0).setOwners(List.of(ownerResponse));

        when(serviceMapper.toServiceResponse(any())).thenReturn(serviceResponse);
        when(serviceOperations.updateService(serviceId, service)).thenReturn(service);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/services/{id}", serviceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(serviceInput)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(serviceResponse.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resources[0].id").value("resource_id_1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resources[0].owners[0].id").value("owner_id_1_2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resources[0].owners[0].name").value("Owner 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resources[0].owners[0].accountNumber").value("AC002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resources[0].owners[0].level").value(1));

    }
}

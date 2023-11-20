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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Integration test class for the GraphQLServiceController using GraphQlTester and Mockito.
 * @author Ketrina
 */
@GraphQlTest(GraphQLServiceController.class)
public class GraphQLServiceControllerIntegrationTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private ServiceMapper serviceMapper;

    @MockBean
    private ServiceOperations serviceOperations;

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
    public void getServiceByIdIT() {
        when(serviceMapper.toServiceResponse(any())).thenReturn(serviceResponse);
        when(serviceOperations.getServiceById("service_id_1")).thenReturn(service);

        String document = """
                query getServiceById($id: ID!) {
                  getServiceById(id: $id) {
                    id
                    resources {
                      id
                      owners {
                        id
                        name
                        accountNumber
                        level
                      }
                    }
                  }
                }
              """;

        graphQlTester.document(document)
                .variable("id", "service_id_1")
                .execute()
                .path("getServiceById")
                .entity(ServiceResponse.class)
                .satisfies(serviceResponse -> {
                    assertEquals("service_id_1", serviceResponse.getId());
                    assertEquals(1, serviceResponse.getResources().size());
                    assertEquals("Owner 1", serviceResponse.getResources().get(0).getOwners().get(0).getName());
                });
    }

    @Test
    public void getAllServicesIT() {
        when(serviceMapper.toServiceResponseList(any())).thenReturn(serviceResponseList);
        when(serviceOperations.getAllServices()).thenReturn(expectedServices);

        String document = """
                  query {
                      getAllServices {
                        id
                        resources {
                          id
                          owners {
                            id
                            name
                            accountNumber
                            level
                          }
                        }
                      }
                    }
                """;

        graphQlTester.document(document)
                .execute()
                .path("getAllServices")
                .entityList(ServiceResponse.class)
                .hasSize(1);
    }

    @Test
    public void createServiceIT() {
        when(serviceMapper.toServiceResponse(any())).thenReturn(serviceResponse);
        when(serviceOperations.createService(service)).thenReturn(service);

        String document = """
                  mutation createService($serviceInput: ServiceInput) {
                       createService(serviceInput: $serviceInput) {
                         id
                         resources {
                           id
                           owners {
                             id
                             name
                             accountNumber
                             level
                           }
                         }
                       }
                     }
                """;

        Map<String, Object> serviceInputMap = convertServiceInputToMap(serviceInput);

        graphQlTester.document(document)
                .variable("serviceInput", serviceInputMap)
                .execute()
                .path("createService")
                .entity(ServiceResponse.class)
                .satisfies(serviceResponse -> {
                    assertEquals("service_id_1", serviceResponse.getId());
                    assertEquals(1, serviceResponse.getResources().size());
                    assertEquals("Owner 1", serviceResponse.getResources().get(0).getOwners().get(0).getName());
                });
    }

    @Test
    public void updateServiceIT() {
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

        String document = """
                  mutation createService($serviceInput: ServiceInput) {
                       createService(serviceInput: $serviceInput) {
                         id
                         resources {
                           id
                           owners {
                             id
                             name
                             accountNumber
                             level
                           }
                         }
                       }
                     }
                """;

        Map<String, Object> updatedServiceInputMap = convertServiceInputToMap(serviceInput);

        graphQlTester.document(document)
                .variable("serviceInput", updatedServiceInputMap)
                .execute()
                .path("createService")
                .entity(ServiceResponse.class)
                .satisfies(serviceResponse -> {
                    assertEquals("service_id_1", serviceResponse.getId());
                    assertEquals(1, serviceResponse.getResources().size());
                    assertEquals("Owner 2", serviceResponse.getResources().get(0).getOwners().get(0).getName());
                    assertEquals("AC002", serviceResponse.getResources().get(0).getOwners().get(0).getAccountNumber());
                });
    }

    private Map<String, Object> convertServiceInputToMap(ServiceInput serviceInput) {
        Map<String, Object> serviceInputMap = new HashMap<>();
        serviceInputMap.put("id", serviceInput.getId());

        // Convert List<ResourceInput> to List<Map<String, Object>>
        List<Map<String, Object>> resourceInputs = serviceInput.getResources().stream()
                .map(this::convertResourceInputToMap)
                .toList();
        serviceInputMap.put("resources", resourceInputs);

        return serviceInputMap;
    }

    private Map<String, Object> convertResourceInputToMap(ResourceInput resourceInput) {
        Map<String, Object> resourceInputMap = new HashMap<>();
        resourceInputMap.put("id", resourceInput.getId());

        // Convert List<OwnerInput> to List<Map<String, Object>>
        List<Map<String, Object>> ownerInputs = resourceInput.getOwners().stream()
                .map(this::convertOwnerInputToMap)
                .toList();
        resourceInputMap.put("owners", ownerInputs);

        return resourceInputMap;
    }

    private Map<String, Object> convertOwnerInputToMap(OwnerInput ownerInput) {
        Map<String, Object> ownerInputMap = new HashMap<>();
        ownerInputMap.put("id", ownerInput.getId());
        ownerInputMap.put("name", ownerInput.getName());
        ownerInputMap.put("accountNumber", ownerInput.getAccountNumber());
        ownerInputMap.put("level", ownerInput.getLevel());

        return ownerInputMap;
    }
}

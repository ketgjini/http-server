package com.ketrina.httpserver.model.response;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServiceResponse {
    private String id;
    private List<ResourceResponse> resources = new CopyOnWriteArrayList<>();

    // Empty constructor
    public ServiceResponse() { }

    // Parameterized constructor
    public ServiceResponse(final String id, final List<ResourceResponse> resources) {
        this.id = id;
        this.resources = new CopyOnWriteArrayList<>(resources);
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<ResourceResponse> getResources() {
        return resources;
    }

    public void setResources(List<ResourceResponse> resources) {
        this.resources = new CopyOnWriteArrayList<>(resources);
    }
}

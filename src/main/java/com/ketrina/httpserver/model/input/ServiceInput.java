package com.ketrina.httpserver.model.input;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServiceInput {
    private String id;
    private List<ResourceInput> resources = new CopyOnWriteArrayList<>();

    // Empty constructor
    public ServiceInput() { }

    // Parameterized constructor
    public ServiceInput(String id, final List<ResourceInput> resources) {
        this.id = id;
        this.resources = new CopyOnWriteArrayList<>(resources);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ResourceInput> getResources() {
        return resources;
    }

    public void setResources(List<ResourceInput> resources) {
        this.resources = new CopyOnWriteArrayList<>(resources);
    }
}

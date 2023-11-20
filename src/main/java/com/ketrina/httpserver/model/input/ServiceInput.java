package com.ketrina.httpserver.model.input;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Input class representing service information used for creating or updating a service.
 * @author Ketrina
 */
public class ServiceInput {
    private String id;
    private List<ResourceInput> resources = new CopyOnWriteArrayList<>();

    /**
     * Default constructor.
     */
    public ServiceInput() { }

    /**
     * Parameterized constructor.
     */
    public ServiceInput(final String id, final List<ResourceInput> resources) {
        this.id = id;
        this.resources = new CopyOnWriteArrayList<>(resources);
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<ResourceInput> getResources() {
        return resources;
    }

    public void setResources(List<ResourceInput> resources) {
        this.resources = new CopyOnWriteArrayList<>(resources);
    }
}

package com.ketrina.httpserver.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Entity class representing a Service.
 * @author Ketrina
 */
@Document(collection = "services")
public class Service {
    @Id
    private String id;
    private List<Resource> resources = new CopyOnWriteArrayList<>();

    /**
     * Default constructor.
     */
    public Service() { }

    /**
     * Parameterized constructor.
     */
    public Service(final String id, final List<Resource> resources) {
        this.id = id;
        this.resources = new CopyOnWriteArrayList<>(resources);
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(final List<Resource> resources) {
        this.resources = new CopyOnWriteArrayList<>(resources);
    }
}

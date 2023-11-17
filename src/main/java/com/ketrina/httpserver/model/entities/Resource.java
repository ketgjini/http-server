package com.ketrina.httpserver.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Document(collection = "resources")
public class Resource {
    @Id
    private String id;
    private List<Owner> owners = new CopyOnWriteArrayList<>();

    // Empty constructor
    public Resource() { }

    // Parameterized constructor
    public Resource(final String id, final List<Owner> owners) {
        this.id = id;
        this.owners = new CopyOnWriteArrayList<>(owners);
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(final List<Owner> owners) {
        this.owners = new CopyOnWriteArrayList<>(owners);
    }
}

package com.ketrina.httpserver.model.input;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Input class representing resource information used for creating or updating a resource.
 * @author Ketrina
 */
public class ResourceInput {
    private String id;
    private List<OwnerInput> owners = new CopyOnWriteArrayList<>();

    /**
     * Default constructor.
     */
    public ResourceInput() { }

    /**
     * Parameterized constructor.
     */
    public ResourceInput(final String id, final List<OwnerInput> owners) {
        this.id = id;
        this.owners = new CopyOnWriteArrayList<>(owners);
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<OwnerInput> getOwners() {
        return owners;
    }

    public void setOwners(List<OwnerInput> owners) {
        this.owners = new CopyOnWriteArrayList<>(owners);
    }
}

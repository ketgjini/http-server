package com.ketrina.httpserver.model.response;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ResourceResponse {
    private String id;
    private List<OwnerResponse> owners = new CopyOnWriteArrayList<>();

    // Empty constructor
    public ResourceResponse() { }

    // Parameterized constructor
    public ResourceResponse(final String id, final List<OwnerResponse> owners) {
        this.id = id;
        this.owners = new CopyOnWriteArrayList<>(owners);
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<OwnerResponse> getOwners() {
        return owners;
    }

    public void setOwners(List<OwnerResponse> owners) {
        this.owners = new CopyOnWriteArrayList<>(owners);
    }
}

package com.ketrina.httpserver.model.mapper;

import com.ketrina.httpserver.model.entities.Owner;
import com.ketrina.httpserver.model.entities.Resource;
import com.ketrina.httpserver.model.entities.Service;
import com.ketrina.httpserver.model.input.OwnerInput;
import com.ketrina.httpserver.model.input.ResourceInput;
import com.ketrina.httpserver.model.input.ServiceInput;
import com.ketrina.httpserver.model.response.OwnerResponse;
import com.ketrina.httpserver.model.response.ResourceResponse;
import com.ketrina.httpserver.model.response.ServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper class to convert between DTOs (Data Transfer Objects) and entity objects.
 * @author Ketrina
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class ServiceMapper {

    /**
     * Maps a ServiceInput object to a Service entity.
     */
    @Mappings({
            @Mapping(source = "resources", target = "resources"),
    })
    public abstract Service toServiceEntity(final ServiceInput serviceInput);

    /**
     * Maps a ResourceInput object to a Resource entity.
     */
    @Mappings({
            @Mapping(source = "owners", target = "owners"),
    })
    public abstract Resource toResourceEntity(final ResourceInput resourceInput);

    /**
     * Maps an OwnerInput object to an Owner entity.
     */
    public abstract Owner toOwnerEntity(final OwnerInput ownerInput);

    /**
     * Maps a Service entity to a ServiceResponse object.
     */
    @Mappings({
            @Mapping(source = "resources", target = "resources"),
    })
    public abstract ServiceResponse toServiceResponse(final Service service);

    /**
     * Maps a Resource entity to a ResourceResponse object.
     */
    @Mappings({
            @Mapping(source = "owners", target = "owners"),
    })
    public abstract ResourceResponse toResourceResponse(final Resource resource);

    /**
     * Maps an Owner entity to an OwnerResponse object.
     */
    public abstract OwnerResponse toOwnerResponse(final Owner owner);

    /**
     * Maps a list of Service entities to a list of ServiceResponse objects.
     */
    public abstract List<ServiceResponse> toServiceResponseList(final List<Service> services);
}

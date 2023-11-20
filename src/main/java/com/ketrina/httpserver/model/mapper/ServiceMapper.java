package com.ketrina.httpserver.model.mapper;

import com.ketrina.httpserver.model.entities.Service;
import com.ketrina.httpserver.model.input.ServiceInput;
import com.ketrina.httpserver.model.response.ServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper class to convert between Service DTOs (Data Transfer Objects) and entity object.
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
     * Maps a Service entity to a ServiceResponse object.
     */
    @Mappings({
            @Mapping(source = "resources", target = "resources"),
    })
    public abstract ServiceResponse toServiceResponse(final Service service);

    /**
     * Maps a list of Service entities to a list of ServiceResponse objects.
     */
    public abstract List<ServiceResponse> toServiceResponseList(final List<Service> services);
}

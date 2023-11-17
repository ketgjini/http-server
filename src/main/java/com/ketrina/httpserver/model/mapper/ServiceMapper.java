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

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class ServiceMapper {

    @Mappings({
            @Mapping(source = "resources", target = "resources"),
    })
    public abstract Service toServiceEntity(final ServiceInput serviceInput);

    @Mappings({
            @Mapping(source = "owners", target = "owners"),
    })
    public abstract Resource toResourceEntity(final ResourceInput resourceInput);

    public abstract Owner toOwnerEntity(final OwnerInput ownerInput);

    @Mappings({
            @Mapping(source = "resources", target = "resources"),
    })
    public abstract ServiceResponse toServiceResponse(final Service service);

    @Mappings({
            @Mapping(source = "owners", target = "owners"),
    })
    public abstract ResourceResponse toResourceResponse(final Resource resource);

    public abstract OwnerResponse toOwnerResponse(final Owner owner);

    public abstract List<ServiceResponse> toServiceResponseList(final List<Service> services);
}

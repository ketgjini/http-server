package com.ketrina.httpserver.model.mapper;

import com.ketrina.httpserver.model.entities.Owner;
import com.ketrina.httpserver.model.entities.Resource;
import com.ketrina.httpserver.model.entities.Service;
import com.ketrina.httpserver.model.input.OwnerInput;
import com.ketrina.httpserver.model.input.ResourceInput;
import com.ketrina.httpserver.model.input.ServiceInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

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
}

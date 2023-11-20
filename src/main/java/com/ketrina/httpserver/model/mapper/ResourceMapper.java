package com.ketrina.httpserver.model.mapper;

import com.ketrina.httpserver.model.entities.Resource;
import com.ketrina.httpserver.model.input.ResourceInput;
import com.ketrina.httpserver.model.response.ResourceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper class to convert between Resource DTOs (Data Transfer Objects) and entity object.
 * @author Ketrina
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class ResourceMapper {
    /**
     * Maps a ResourceInput object to a Resource entity.
     */
    @Mappings({
            @Mapping(source = "owners", target = "owners"),
    })
    public abstract Resource toResourceEntity(final ResourceInput resourceInput);

    /**
     * Maps a Resource entity to a ResourceResponse object.
     */
    @Mappings({
            @Mapping(source = "owners", target = "owners"),
    })
    public abstract ResourceResponse toResourceResponse(final Resource resource);

}

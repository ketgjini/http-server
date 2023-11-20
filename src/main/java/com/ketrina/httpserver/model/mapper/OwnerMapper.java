package com.ketrina.httpserver.model.mapper;

import com.ketrina.httpserver.model.entities.Owner;
import com.ketrina.httpserver.model.input.OwnerInput;
import com.ketrina.httpserver.model.response.OwnerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper class to convert between Owner DTOs (Data Transfer Objects) and entity object.
 * @author Ketrina
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class OwnerMapper {

    /**
     * Maps an OwnerInput object to an Owner entity.
     */
    public abstract Owner toOwnerEntity(final OwnerInput ownerInput);


    /**
     * Maps an Owner entity to an OwnerResponse object.
     */
    public abstract OwnerResponse toOwnerResponse(final Owner owner);
}

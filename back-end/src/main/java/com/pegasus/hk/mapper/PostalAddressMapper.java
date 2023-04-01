package com.pegasus.hk.mapper;

import org.mapstruct.Mapper;

import com.pegasus.hk.dto.AddressDto;
import com.pegasus.hk.entity.PostalAddress;

@Mapper(componentModel = "spring")
public abstract class PostalAddressMapper {

    public abstract AddressDto toPostalAddressDto(PostalAddress postalAddress);
}

package com.pegasus.hk.mapper;

import com.pegasus.hk.dto.AddressDto;
import com.pegasus.hk.dto.PersonDto;
import com.pegasus.hk.entity.Person;
import com.pegasus.hk.entity.PostalAddress;
import com.pegasus.hk.repository.PostalAddressRepository;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {

    @Autowired
    private PostalAddressRepository postalAddressRepository;

    @Autowired
    private PostalAddressMapper postalAddressMapper;

    @Mapping(target = "address", source = "addressId", qualifiedByName = "setAddressToPerson")
    public abstract PersonDto toPersonDto(Person person);

    @Named("setAddressToPerson")
    public AddressDto setAddressToPerson(Long id){
        return postalAddressMapper.toPostalAddressDto(postalAddressRepository.findById(id).orElse(new PostalAddress()));
    }
}

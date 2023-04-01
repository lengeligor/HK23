package com.pegasus.hk.mapper;

import com.pegasus.hk.dto.AddressDto;
import com.pegasus.hk.entity.PostalAddress;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T18:18:43+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class PostalAddressMapperImpl extends PostalAddressMapper {

    @Override
    public AddressDto toPostalAddressDto(PostalAddress postalAddress) {
        if ( postalAddress == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.setId( postalAddress.getId() );
        addressDto.setMunicipality( postalAddress.getMunicipality() );
        addressDto.setRegion( postalAddress.getRegion() );
        addressDto.setState( postalAddress.getState() );
        addressDto.setZip( postalAddress.getZip() );
        addressDto.setStreetName( postalAddress.getStreetName() );
        addressDto.setStreetNumber( postalAddress.getStreetNumber() );
        addressDto.setRegistrationNumber( postalAddress.getRegistrationNumber() );

        return addressDto;
    }
}

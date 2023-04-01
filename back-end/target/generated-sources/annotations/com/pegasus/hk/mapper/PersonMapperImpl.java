package com.pegasus.hk.mapper;

import com.pegasus.hk.dto.PersonDto;
import com.pegasus.hk.entity.Person;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T18:18:43+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class PersonMapperImpl extends PersonMapper {

    @Override
    public PersonDto toPersonDto(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonDto personDto = new PersonDto();

        personDto.setAddress( setAddressToPerson( person.getAddressId() ) );
        personDto.setId( person.getId() );
        personDto.setName( person.getName() );
        personDto.setLastname( person.getLastname() );
        personDto.setEmail( person.getEmail() );
        personDto.setPassword( person.getPassword() );
        personDto.setType( person.getType() );
        personDto.setGender( person.getGender() );
        personDto.setAge( person.getAge() );
        personDto.setDateOfBirth( person.getDateOfBirth() );
        personDto.setBalance( person.getBalance() );

        return personDto;
    }
}

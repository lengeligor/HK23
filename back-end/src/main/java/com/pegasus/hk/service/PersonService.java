package com.pegasus.hk.service;

import javax.transaction.Transactional;

import com.pegasus.hk.dto.PersonDto;
import com.pegasus.hk.exceptions.BusinessException;
import com.pegasus.hk.mapper.PersonMapper;
import com.pegasus.hk.repository.PersonRepository;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(@NonNull PersonRepository personRepository,
                         @NonNull PersonMapper personMapper){
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public PersonDto getPerson(Long id) {
        return personMapper.toPersonDto(
                personRepository.findById(id)
                        .orElseThrow(() -> new BusinessException(String.format("Osoba s %s neexistuje!", id))));
    }
}

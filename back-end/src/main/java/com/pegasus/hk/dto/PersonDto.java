package com.pegasus.hk.dto;

import lombok.Data;

@Data
public class PersonDto {

    private Long id;

    private String name;

    private String lastname;

    private String email;

    private String password;

    private String type;

    private String gender;

    private Long age;

    private String dateOfBirth;

    private Long balance;

    private AddressDto address;
}

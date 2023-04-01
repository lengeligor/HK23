package com.pegasus.hk.dto;

import lombok.Data;

@Data
public class AddressDto {

    private Long id;

    private String municipality;

    private String region;


    private String state;

    private String zip;

    private String streetName;

    private String streetNumber;

    private String registrationNumber;
}

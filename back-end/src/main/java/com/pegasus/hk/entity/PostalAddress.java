package com.pegasus.hk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
public class PostalAddress implements Serializable {

    @Id
    @GeneratedValue
    @Column(unique = true , nullable = false)
    private Long id;

    private String municipality;

    private String region;


    private String state;

    private String zip;

    private String streetName;

    private String streetNumber;

    private String registrationNumber;

}

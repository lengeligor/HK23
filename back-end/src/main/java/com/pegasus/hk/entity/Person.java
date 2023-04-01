package com.pegasus.hk.entity;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue
    @Column(unique = true , nullable = false)
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

    private Long addressId;
}

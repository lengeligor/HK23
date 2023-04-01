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
@Table(name = "transaction")
public class Transaction implements Serializable {

    //Date,Category,Subcategory,Description,Amount,Type,Balance Before,Balance After,Label
    @Id
    @GeneratedValue
    @Column(unique = true , nullable = false)
    private Long id;

    private Long personId;

    private String date;

    private String category;

    private String subcategory;

    private String description;

    private String amount;

    private String type;

    private Long balanceBefore;

    private Long balanceAfter;

    private String label;
}

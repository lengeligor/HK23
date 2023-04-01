package com.pegasus.hk.dto;

import lombok.Data;

@Data
public class TransactionDto {

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

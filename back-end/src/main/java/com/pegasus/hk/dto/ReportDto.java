package com.pegasus.hk.dto;

import lombok.Data;

@Data
public class ReportDto {

    private Long travel = 0L;
    private Long utilities = 0L;
    private Long category = 0L;
    private Long transportation = 0L;
    private Long income = 0L;
    private Long shopping = 0L;
    private Long gifts = 0L;
    private Long subscription = 0L;
    private Long food = 0L;
    private Long remainingFinances = 0L;
    private Long daysToEndOfMonth = 0L;
    private Long lastMonthSituationAtThisDay = 0L;
    private Long incomes = 0L;
    private Long expenses = 0L;
    private Boolean opinion = null;
    private String message = null;
}

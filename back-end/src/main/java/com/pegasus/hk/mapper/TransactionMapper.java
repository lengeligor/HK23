package com.pegasus.hk.mapper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.pegasus.hk.dto.ReportDto;
import com.pegasus.hk.dto.TransactionDto;
import com.pegasus.hk.entity.Transaction;
import com.pegasus.hk.enums.CategoryEnum;

@Mapper(componentModel = "spring")
public abstract class TransactionMapper {

    public abstract TransactionDto toTransactionDto(Transaction transaction);

    public ReportDto toReportDto(List<Transaction> transactions) {
        ReportDto reportDto = new ReportDto();
        long[] incomes = new long[1];
        long[] expenses = new long[1];
        transactions.forEach(transaction -> {
            if (CategoryEnum.FOOD.value().equals(transaction.getCategory())){
                reportDto.setFood(reportDto.getFood() + Long.parseLong(transaction.getAmount()));
            }
            if (CategoryEnum.SUBSCRIPTION.value().equals(transaction.getCategory())){
                reportDto.setSubscription(reportDto.getSubscription() + Long.parseLong(transaction.getAmount()));
            }
            if (CategoryEnum.GIFTS.value().equals(transaction.getCategory())){
                reportDto.setGifts(reportDto.getGifts() + Long.parseLong(transaction.getAmount()));
            }
            if (CategoryEnum.SHOPPING.value().equals(transaction.getCategory())){
                reportDto.setShopping(reportDto.getShopping() + Long.parseLong(transaction.getAmount()));
            }
            if (CategoryEnum.INCOME.value().equals(transaction.getCategory())){
                reportDto.setIncome(reportDto.getIncome() + Long.parseLong(transaction.getAmount()));
            }
            if (CategoryEnum.TRANSPORTATION.value().equals(transaction.getCategory())){
                reportDto.setTransportation(reportDto.getTransportation() + Long.parseLong(transaction.getAmount()));
            }
            if (CategoryEnum.CATEGORY.value().equals(transaction.getCategory())){
                reportDto.setCategory(reportDto.getCategory() + Long.parseLong(transaction.getAmount()));
            }
            if (CategoryEnum.UTILITIES.value().equals(transaction.getCategory())){
                reportDto.setUtilities(reportDto.getUtilities() + Long.parseLong(transaction.getAmount()));
            }
            if (CategoryEnum.TRAVEL.value().equals(transaction.getCategory())){
                reportDto.setTravel(reportDto.getTravel() + Long.parseLong(transaction.getAmount()));
            }
            if (transaction.getType() != null) {
                if (transaction.getType().contains("Expense")){
                    expenses[0] += Long.parseLong(transaction.getAmount());
                } else if (transaction.getType().contains("Income")) {
                    incomes[0] += Long.parseLong(transaction.getAmount());
                }
            }
        });
        reportDto.setTransportation(reportDto.getTransportation() * -1);
        reportDto.setTravel(reportDto.getTravel() * -1);
        reportDto.setUtilities(reportDto.getUtilities() * -1);
        reportDto.setCategory(reportDto.getCategory() * -1);
        reportDto.setShopping(reportDto.getShopping() * -1);
        reportDto.setGifts(reportDto.getGifts() * -1);
        reportDto.setFood(reportDto.getFood() * -1);
        reportDto.setIncomes(incomes[0]);
        reportDto.setExpenses(expenses[0] * -1);
        reportDto.setRemainingFinances(incomes[0] - (expenses[0]*-1));
        if (reportDto.getRemainingFinances() <= 0) {
            reportDto.setOpinion(false);
        } else {
            reportDto.setOpinion(true);
        }
        LocalDate today = LocalDate.now();
        reportDto.setDaysToEndOfMonth(ChronoUnit.DAYS.between(today , today.withDayOfMonth(today.lengthOfMonth())));

        return reportDto;
    }
}

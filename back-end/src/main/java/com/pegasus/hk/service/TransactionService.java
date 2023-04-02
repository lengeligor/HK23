package com.pegasus.hk.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.swing.text.DateFormatter;
import javax.transaction.Transactional;

import com.pegasus.hk.client.BotClient;
import com.pegasus.hk.dto.PersonDto;
import com.pegasus.hk.dto.ReportDto;
import com.pegasus.hk.dto.TransactionDto;
import com.pegasus.hk.entity.QTransaction;
import com.pegasus.hk.entity.Transaction;
import com.pegasus.hk.mapper.TransactionMapper;
import com.pegasus.hk.model.request.MessageRequest;
import com.pegasus.hk.model.response.ChatGptResponse;
import com.pegasus.hk.repository.TransactionRepository;
import com.querydsl.core.BooleanBuilder;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import lombok.NonNull;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final BotClient botClient;
    private final PersonService personService;

    private final EntityManager entityManager;

    public TransactionService(@NonNull TransactionRepository transactionRepository,
                              @NonNull TransactionMapper transactionMapper,
                              @NonNull BotClient botClient,
                              @NonNull PersonService personService,
                              @NonNull EntityManager entityManager){
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.botClient = botClient;
        this.personService = personService;
        this.entityManager = entityManager;
    }

    public List<TransactionDto> getPersonTransactions(Pageable pageable, Long id, String monthFilter) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QTransaction.transaction.personId.eq(id));
        if (monthFilter != null){
            builder.and(QTransaction.transaction.date.contains("-" + monthFilter + "-"));
        }
        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
        }
        return transactionRepository.findAll(builder, pageable)
                .stream()
                .map(transactionMapper::toTransactionDto)
                .collect(Collectors.toList());
    }

    public ReportDto getAnalysisReport(Long id, Long analysisDuration){
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QTransaction.transaction.personId.eq(id));
        Pageable pageable = PageRequest.of(0,1,Sort.by("id").descending());
        Transaction lastTransaction = transactionRepository.findAll(QTransaction.transaction.personId.in(id), pageable).getContent().get(0);
        String month = lastTransaction.getDate().split("-")[1];
        BooleanBuilder months = new BooleanBuilder();
        for (int i = 0; i < analysisDuration; i++){
            String cont = "-" + month + "-";
            months.or(QTransaction.transaction.date.contains(cont));
            month = String.valueOf(Long.parseLong(month) -1);
            if (month.length() ==1){
                month = "0" + month;
            }
        }
        builder.and(months);
        List<Transaction> transactions = transactionRepository.findAll(builder, Pageable.unpaged()).getContent();
        ReportDto reportDto = transactionMapper.toReportDto(transactions);
        MessageRequest messageRequest = new MessageRequest();
        PersonDto personDto = personService.getPerson(id);
        HashMap<String, Long> sortedExpanses = new HashMap<>();
        sortedExpanses.put("traveling", reportDto.getTravel());
        sortedExpanses.put("utilities", reportDto.getUtilities());
        sortedExpanses.put("transportation", reportDto.getTransportation());
        sortedExpanses.put("shopping", reportDto.getShopping());
        sortedExpanses.put("gifts", reportDto.getGifts());
        sortedExpanses.put("subscription", reportDto.getSubscription());
        sortedExpanses.put("food", reportDto.getFood());
        sortedExpanses = sortByValue(sortedExpanses);
        String message = "Give advices to " + personDto.getName() + " " + personDto.getLastname()
                + ", which is male and he is " + personDto.getAge() + " years old, and he live in " + personDto.getAddress().getMunicipality()+ ","
                + personDto.getAddress().getState() + "and his financial situation in last  "
                + analysisDuration + " months is this : " + personDto.getName() + " has incomes " + reportDto.getIncomes()
                + ", expanses " + reportDto.getExpenses() + ", and his balance (incomes-expanses) is " + reportDto.getRemainingFinances()
                + "euros. His expanses look like that: ";
        for (Map.Entry<String, Long> entry : sortedExpanses.entrySet()) {
            String key = entry.getKey();
            Long value = entry.getValue();
            message = message + key + ": " + value + " eur,";
        }
        message = message + "Write 2-3 sentences in a first person.";
        messageRequest.setMessage(message);
        try {
            ChatGptResponse response = botClient.askQuestion(messageRequest);
            Optional.ofNullable(response)
                    .flatMap(r -> Optional.ofNullable(r.getChoices()))
                    .ifPresent(choices -> {
                        if (!choices.isEmpty()) {
                            reportDto.setMessage(choices.get(0).getText());
                        }
                    });
        } catch (HttpClientErrorException e){
            reportDto.setMessage("Our advisor is temporary unavailable. Try later.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now().minusMonths(1);
        Transaction transactionPast = null;
        while (transactionPast == null){
            transactionPast = transactionRepository.findOne(QTransaction.transaction.date.eq(date.format(formatter))
                    .and(QTransaction.transaction.personId.eq(id))).orElse(null);
            date = date.minusDays(1);
        }
        Long balanceAfterPast = transactionPast.getBalanceAfter();
        reportDto.setLastMonthSituationAtThisDay(balanceAfterPast);

        /*List<Transaction> endOfMonthsTransactions = transactionRepository.getPersonYearlyBalance(id);
        endOfMonthsTransactions = endOfMonthsTransactions.stream()
                .filter(t ->t.getDate().contains(LocalDate.now().format(formatter).split("-")[1])
                        || t.getDate().contains(LocalDate.now().minusMonths(1).format(formatter).split("-")[1]))
                .collect(Collectors.toList());

        try {
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            Date dateBefore = formatter2.parse(transactionPast.getDate());
            Date dateAfter = formatter2.parse(endOfMonthsTransactions.get(0).getDate().contains(LocalDate.now().format(formatter).split("-")[1]) ? endOfMonthsTransactions.get(1).getDate() : endOfMonthsTransactions.get(0).getDate());
            long dateBeforeInMs = dateBefore.getTime();
            long dateAfterInMs = dateAfter.getTime();
            long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
            long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
            System.out.println();
        } catch (ParseException e){

        }*/

        //Long balanceAfterNow = lastTransaction.getBalanceAfter();

        return reportDto;
    }

    public static HashMap<String, Long> sortByValue(HashMap<String, Long> hm) {
        List<Map.Entry<String, Long> > list =
                new LinkedList<Map.Entry<String, Long> >(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Long> >() {
            public int compare(Map.Entry<String, Long> o1,
                               Map.Entry<String, Long> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<String, Long> temp = new LinkedHashMap<String, Long>();
        for (Map.Entry<String, Long> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public HashMap<String, Long> getPersonYearlyBalance(Long id, Long year) {
        HashMap<String, Long> result = new HashMap<>();
        List<Transaction> transactions = transactionRepository.getPersonYearlyBalance(id);
        transactions.forEach(t ->
                result.put(t.getDate(), t.getBalanceBefore()));
        return result;
    }
}
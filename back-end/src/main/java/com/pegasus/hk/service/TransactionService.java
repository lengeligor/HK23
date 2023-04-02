package com.pegasus.hk.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
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
        sortedExpanses.put("cestovanie", reportDto.getTravel());
        sortedExpanses.put("úžitkové veci", reportDto.getUtilities());
        sortedExpanses.put("doprava", reportDto.getTransportation());
        sortedExpanses.put("nakupovanie", reportDto.getShopping());
        sortedExpanses.put("darčeky", reportDto.getGifts());
        sortedExpanses.put("pravidelné odbery(subscription)", reportDto.getSubscription());
        sortedExpanses.put("jedlo", reportDto.getFood());
        sortedExpanses = sortByValue(sortedExpanses);
        String message = "sk: Aké rady by si dal " + personDto.getName() + " " + personDto.getLastname()
                + ", ktorý je muž, má " + personDto.getAge() + " rokov, býva v " + personDto.getAddress().getMunicipality()+ ","
                + personDto.getAddress().getState() + "a jeho finančná situácia za obdobie posledných "
                + analysisDuration + " mesiacov je následovná : " + personDto.getName() + " má príjmy vo výške " + reportDto.getIncomes()
                + ", výdaje vo výške " + reportDto.getExpenses() + ", jeho +/- bilancia je na úrovni " + reportDto.getRemainingFinances()
                + "eur. Jeho výdaje podľa kategórii vyzerajú takto: ";
        for (Map.Entry<String, Long> entry : sortedExpanses.entrySet()) {
            String key = entry.getKey();
            Long value = entry.getValue();
            message = message + key + ": " + value + " eur,";
        }
        message = message + "Napíš to v prvej osobe.";
        messageRequest.setMessage(message);
        ChatGptResponse response = botClient.askQuestion(messageRequest);
        Optional.ofNullable(response)
                .flatMap(r -> Optional.ofNullable(r.getChoices()))
                .ifPresent(choices -> {
                    if (!choices.isEmpty()){
                        reportDto.setMessage(choices.get(0).getText());
                    }
        });
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
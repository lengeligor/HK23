package com.pegasus.hk.repository;

import java.util.List;

import com.pegasus.hk.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, QuerydslPredicateExecutor<Transaction> {

    @Query(value = "SELECT * "
            + "FROM transaction AS t WHERE t.person_id=?1 AND (t.date, DATE_TRUNC('month', t.date)) " +
            "IN (SELECT MAX(tr.date), DATE_TRUNC('month', tr.date) FROM transaction AS tr GROUP BY DATE_TRUNC('month', tr.date))", nativeQuery = true)
    List<Transaction> getPersonYearlyBalance(Long id);
}

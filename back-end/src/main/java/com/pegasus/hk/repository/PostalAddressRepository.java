package com.pegasus.hk.repository;

import com.pegasus.hk.entity.PostalAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface PostalAddressRepository extends JpaRepository<PostalAddress, Long>, QuerydslPredicateExecutor<PostalAddress> {
}

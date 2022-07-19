package com.matheus.restaurant.payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemoryPaymentRepository extends CrudRepository<Payment, Long> {}

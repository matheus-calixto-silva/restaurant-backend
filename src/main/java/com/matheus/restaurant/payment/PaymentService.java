package com.matheus.restaurant.payment;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@EnableMapRepositories
public class PaymentService {
    private final CrudRepository<Payment, Long> repository;

    public PaymentService(CrudRepository<Payment, Long> repository) {
      this.repository = repository;
      this.repository.saveAll(defaultPayments());
    }
    
    private static List<Payment> defaultPayments() {
      return List.of(
        new Payment(1L, "PayPal"),
        new Payment(2L, "Cash"),
        new Payment(3L, "Credit Card")   
      );
    }

    public List<Payment> findAll() {
        List<Payment> list = new ArrayList<>();
        Iterable<Payment> payments = repository.findAll();
        payments.forEach(list::add);
        return list;
    }
    
    public Optional<Payment> find(Long id) {
        return repository.findById(id);
    }

    public Payment create(Payment payment) {
        Payment copy = new Payment(
                new Date().getTime(),
                payment.getDescription()
        );
        return repository.save(copy);
    }

    public Optional<Payment> update( Long id, Payment newPayment) {
        return repository.findById(id)
                .map(oldPayment -> {
                   Payment updated = oldPayment.updateWith(newPayment);
                   return repository.save(updated);
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
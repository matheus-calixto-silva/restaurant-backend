package com.matheus.restaurant.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

@CrossOrigin(origins = "http://127.0.0.1:5173/")
@RestController
@RequestMapping("api/menu/payments")

public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
      this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> findAll() {
        List<Payment> payments = service.findAll();
        return ResponseEntity.ok().body(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> find(@PathVariable("id") Long id) {
        Optional<Payment> payment = service.find(id);
        return ResponseEntity.of(payment);
    }    

    @PostMapping
    public ResponseEntity<Payment> create(@Valid @RequestBody Payment payment) {
        Payment created = service.create(payment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody Payment updatedPayment) {

        Optional<Payment> updated = service.update(id, updatedPayment);

        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    Payment created = service.create(updatedPayment);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Payment> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
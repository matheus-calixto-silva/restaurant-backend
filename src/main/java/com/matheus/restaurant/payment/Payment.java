package com.matheus.restaurant.payment;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;

public class Payment {
  private final Long id;

    @NotNull(message = "description is required")
    @Pattern(regexp="^[a-zA-Z ]+$", message = "check your email and try again")
    private final String description;

    public Payment(Long id, String description) {
      this.id = id;
      this.description = description;
    }

    @Id

    public Long getId() {
      return id;
    }

    public String getDescription() {
      return description;
    }

    public Payment updateWith(Payment payment) {
    return new Payment(
        this.id,
        payment.description
    );
  }

}
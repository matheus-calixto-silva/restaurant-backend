package com.matheus.restaurant.user;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;

public class User {
  private final Long id;

    @NotNull(message = "name is required")
    @Pattern(regexp="^[a-zA-Z ]+$", message = "check your email and try again")
    private final String name;

    @NotNull(message = "phone is required")
    @Pattern(regexp="[0-9]+", message = "only numbers for cpf")
    private final String phone;

    @NotNull(message = "cpf is required")
    @Pattern(regexp="[0-9]+", message = "only numbers for cpf")
    private final String cpf;

    @NotNull(message = "email is required")
    @Pattern(regexp="\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "check your email and try again")
    private final String email;

    @NotNull(message = "password is required")
    @Pattern(regexp="^[a-zA-Z0-9]*$", message = "check your password and try again")
    private final String password;

    @NotNull(message = "image is required")
    @URL(message = "image must be a URL")
    private final String image;
    
    public User(
            Long id,
            String name,
            String phone,
            String cpf,
            String email,
            String password,
            String image
    ) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    @Id

    public Long getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public String getPhone() {
      return phone;
    }

    public String getCpf() {
      return cpf;
    }

    public String getEmail() {
      return email;
    }

    public String getPassword() {
      return password;
    }

    public String getImage() {
        return image;
    }

    public User updateWith(User user) {
    return new User(
        this.id,
        user.name,
        user.phone,
        user.cpf,
        user.email,
        user.password,
        user.image
    );
  }
  
}
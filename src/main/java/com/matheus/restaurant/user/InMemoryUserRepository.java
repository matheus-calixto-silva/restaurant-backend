package com.matheus.restaurant.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemoryUserRepository extends CrudRepository<User, Long> {}

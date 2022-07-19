package com.matheus.restaurant.user;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@EnableMapRepositories
public class UserService {
    private final CrudRepository<User, Long> repository;

    public UserService(CrudRepository<User, Long> repository) {
      this.repository = repository;
      this.repository.saveAll(defaultUsers());
    }
    
    private static List<User> defaultUsers() {
      return List.of(
        new User(1L, "John Doe", "81999999999", "12345678910", "johndoe@mail.com", "1234567890", "https://images.unsplash.com/photo-1570295999919-56ceb5ecca61?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=580&q=80"),
        new User(2L, "Jane Doe", "81988888888", "12345678910", "janedoe@mail.com", "1234567890", "https://images.unsplash.com/photo-1597223557154-721c1cecc4b0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=580&q=80"),
        new User(3L, "Arto Hellas", "81977777777", "12345678910", "artohellas@mail.com", "1234567890", "https://images.unsplash.com/photo-1610216705422-caa3fcb6d158?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=580&q=80")   
      );
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> users = repository.findAll();
        users.forEach(list::add);
        return list;
    }
    
    public Optional<User> find(Long id) {
        return repository.findById(id);
    }

    public User create(User user) {
        User copy = new User(
                new Date().getTime(),
                user.getName(),
                user.getPhone(),
                user.getCpf(),
                user.getEmail(),
                user.getPassword(),
                user.getImage()
        );
        return repository.save(copy);
    }

    public Optional<User> update( Long id, User newUser) {
        return repository.findById(id)
                .map(oldUser -> {
                   User updated = oldUser.updateWith(newUser);
                   return repository.save(updated);
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
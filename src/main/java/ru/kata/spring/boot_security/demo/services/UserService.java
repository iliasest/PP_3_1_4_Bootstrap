package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<User> getUsers();

    void add(User user);

    void delete(int id);

    User getUserById(int id);
}

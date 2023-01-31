package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    void createUser(User user);

    List<User> getAllUsers();

    User readUser(Long id);

    void updateUser(Long id, User user);

    void deleteUser(Long id);

}

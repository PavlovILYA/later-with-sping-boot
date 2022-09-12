package ru.practicum.laterwithspringboot.user.service;

import ru.practicum.laterwithspringboot.user.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);

    User getUserById(Long userId);
}

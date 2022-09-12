package ru.practicum.laterwithspringboot.user.service;

import ru.practicum.laterwithspringboot.user.model.User;
import ru.practicum.laterwithspringboot.user.model.UserCreateDto;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(UserCreateDto userCreateDto);

    User getUserById(Long userId);
}

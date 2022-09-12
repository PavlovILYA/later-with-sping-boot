package ru.practicum.laterwithspringboot.user;

import ru.practicum.laterwithspringboot.user.model.User;
import ru.practicum.laterwithspringboot.user.model.UserCreateDto;

import java.time.LocalDate;

public class UserMapper {
    public static User toItem(UserCreateDto userCreateDto) {
        User user = new User();
        user.setName(userCreateDto.getName());
        user.setEmail(userCreateDto.getEmail());
        user.setRegistrationDate(LocalDate.now());
        return user;
    }
}

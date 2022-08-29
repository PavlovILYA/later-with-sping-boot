package ru.practicum.laterwithspringboot.user;

import lombok.Data;

@Data
public class User {
    Long id;
    String email;
    String name;
}

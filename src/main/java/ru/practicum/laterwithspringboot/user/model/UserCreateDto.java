package ru.practicum.laterwithspringboot.user.model;

import lombok.Builder;
import lombok.Data;

@Data
public class UserCreateDto {
    String email;
    String name;
}

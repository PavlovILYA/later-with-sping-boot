package ru.practicum.laterwithspringboot.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemCreateDto {
    private String url;
    private String description;
}

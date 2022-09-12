package ru.practicum.laterwithspringboot.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ItemCreateDto {
    private String url;
    private String description;
    private Set<String> tags;
}

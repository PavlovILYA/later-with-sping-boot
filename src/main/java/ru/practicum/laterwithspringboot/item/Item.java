package ru.practicum.laterwithspringboot.item;

import lombok.Data;

@Data
public class Item {
    private long id;
    private long userId;
    private String url;
}

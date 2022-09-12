package ru.practicum.laterwithspringboot.item.repository;

import ru.practicum.laterwithspringboot.item.model.Item;

public interface CheckUrlItemRepository {
    Item saveItemAfterCheckUrl(Item item);
}

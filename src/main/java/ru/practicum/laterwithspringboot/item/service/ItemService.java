package ru.practicum.laterwithspringboot.item.service;

import ru.practicum.laterwithspringboot.item.model.Item;
import ru.practicum.laterwithspringboot.item.model.ItemCreateDto;

import java.util.List;

public interface ItemService {
    List<Item> getAllItems(long userId);
    Item saveItem(Long userId, ItemCreateDto itemCreateDto);
    Item getItemById(long userId, long itemId);
    void removeItem(long userId, long id);
}

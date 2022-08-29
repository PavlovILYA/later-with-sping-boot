package ru.practicum.laterwithspringboot.item;

import java.util.List;

public interface ItemService {
    List<Item> getAllItems(long userId);
    Item saveItem(long userId, Item item);
    void removeItem(long userId, long id);
}

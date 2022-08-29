package ru.practicum.laterwithspringboot.item;

import java.util.List;
import java.util.Map;

public interface ItemRepository {
    Item save(Item item);
    List<Item> findByUserId(long userId);
    void removeByUserIdAndItemId(long userId, long id);
}

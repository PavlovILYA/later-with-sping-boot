package ru.practicum.laterwithspringboot.item;

import ru.practicum.laterwithspringboot.item.model.Item;
import ru.practicum.laterwithspringboot.item.model.ItemCreateDto;
import ru.practicum.laterwithspringboot.user.model.User;

import java.time.LocalDateTime;

public class ItemMapper {
    public static Item toItem(ItemCreateDto itemCreateDto, User user) {
        Item item = new Item();
        item.setUrl(itemCreateDto.getUrl());
        item.setDescription(itemCreateDto.getDescription());
        item.setCreatedTime(LocalDateTime.now());
        item.setUser(user);
        return item;
    }
}

package ru.practicum.laterwithspringboot.item;

import ru.practicum.laterwithspringboot.user.User;

public class ItemMapper {
    public static Item toItem(ItemCreateDto itemCreateDto, User user) {
        return new Item(null, user, itemCreateDto.getUrl());
    }
}

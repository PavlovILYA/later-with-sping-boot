package ru.practicum.laterwithspringboot.item;

import ru.practicum.laterwithspringboot.item.model.Item;
import ru.practicum.laterwithspringboot.item.model.ItemCreateDto;
import ru.practicum.laterwithspringboot.urlmetadata.model.UrlMetadata;
import ru.practicum.laterwithspringboot.user.model.User;

import java.time.LocalDateTime;

public class ItemMapper {
    public static Item toItem(ItemCreateDto itemCreateDto, User user, UrlMetadata urlMetadata) {
        return Item.builder()
                .description(itemCreateDto.getDescription())
                .tags(itemCreateDto.getTags())
                .user(user)
                .normalUrl(urlMetadata.getNormalUrl())
                .resolvedUrl(urlMetadata.getResolvedUrl())
                .mimeType(urlMetadata.getMimeType())
                .title(urlMetadata.getTitle())
                .hasImage(urlMetadata.isHasImage())
                .hasVideo(urlMetadata.isHasVideo())
                .resolvedDate(urlMetadata.getResolvedDate())
                .build();
    }
}

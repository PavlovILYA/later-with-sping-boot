package ru.practicum.laterwithspringboot.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.laterwithspringboot.exception.ItemNotFoundException;
import ru.practicum.laterwithspringboot.exception.UserNotFoundException;
import ru.practicum.laterwithspringboot.item.ItemMapper;
import ru.practicum.laterwithspringboot.item.model.Item;
import ru.practicum.laterwithspringboot.item.model.ItemCreateDto;
import ru.practicum.laterwithspringboot.item.repository.ItemRepository;
import ru.practicum.laterwithspringboot.urlmetadata.model.UrlMetadata;
import ru.practicum.laterwithspringboot.urlmetadata.service.UrlMetadataRetriever;
import ru.practicum.laterwithspringboot.user.model.User;
import ru.practicum.laterwithspringboot.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final UrlMetadataRetriever urlMetadataRetriever;

    @Override
    public List<Item> getAllItems(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException(userId);
        });
        return itemRepository.findAllByUser(user);
    }

    @Override
    public Item saveItem(Long userId, ItemCreateDto itemCreateDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException(userId);
        });
        UrlMetadata urlMetadata = urlMetadataRetriever.retrieve(itemCreateDto.getUrl());
        Item item = ItemMapper.toItem(itemCreateDto, user, urlMetadata);
        return itemRepository.saveItemAfterCheckUrl(item);
    }

    @Override
    public Item getItemById(long userId, long itemId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException(userId);
        });
        return itemRepository.findByIdAndUser(itemId, user).orElseThrow(() -> {
            throw new ItemNotFoundException(itemId);
        });
    }

    @Override
    public void removeItem(long userId, long itemId) {
        Item item = getItemById(userId, itemId);
        itemRepository.delete(item);
    }
}

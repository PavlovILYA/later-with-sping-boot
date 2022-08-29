package ru.practicum.laterwithspringboot.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems(long userId) {
        return itemRepository.findByUserId(userId);
    }

    @Override
    public Item saveItem(long userId, Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void removeItem(long userId, long id) {
        itemRepository.removeByUserIdAndItemId(userId, id);
    }
}

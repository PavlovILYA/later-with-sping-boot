package ru.practicum.laterwithspringboot.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.laterwithspringboot.exception.ItemNotFoundException;
import ru.practicum.laterwithspringboot.exception.UserNotFoundException;
import ru.practicum.laterwithspringboot.user.User;
import ru.practicum.laterwithspringboot.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

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
        Item item = ItemMapper.toItem(itemCreateDto, user);
        return itemRepository.save(item);
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

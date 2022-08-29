package ru.practicum.laterwithspringboot.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.practicum.laterwithspringboot.IdGenerator;
import ru.practicum.laterwithspringboot.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Long, Map<Long, Item>> items = new HashMap<>();
    private final IdGenerator idGenerator;

    @Override
    public Item save(Item item) {
        item.setId(idGenerator.generate());
        items.putIfAbsent(item.getUserId(), new HashMap<>());
        items.get(item.getUserId())
                .put(item.getId(), item);
        return item;
    }

    @Override
    public List<Item> findByUserId(long userId) {
        if (!items.containsKey(userId)) {
            throw new UserNotFoundException("User " + userId + " is absent");
        }
        return new ArrayList<>(items.get(userId).values());
    }

    @Override
    public void removeByUserIdAndItemId(long userId, long id) {
        if (items.containsKey(userId)) {
            items.get(userId).remove(id);
        }
    }
}

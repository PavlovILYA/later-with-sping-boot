package ru.practicum.laterwithspringboot.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<Item> getAllItems(@RequestHeader("X-Later-User-Id") long userId) {
        return itemService.getAllItems(userId);
    }

    @PostMapping
    public Item saveItem(@RequestHeader("X-Later-User-Id") long userId,
                         @RequestBody Item item) {
        return itemService.saveItem(userId, item);
    }

    @DeleteMapping("/{itemId}")
    void removeItem(@RequestHeader("X-Later-User-Id") long userId,
                    @PathVariable("itemId") long id) {
        itemService.removeItem(userId, id);
    }
}

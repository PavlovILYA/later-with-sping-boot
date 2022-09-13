package ru.practicum.laterwithspringboot.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.laterwithspringboot.item.model.*;
import ru.practicum.laterwithspringboot.item.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<Item> getAllItems(@RequestHeader("X-Later-User-Id") Long userId,
                                  @RequestParam(defaultValue = "ALL") GetItemRequestDto.ContentType contentType,
                                  @RequestParam(defaultValue = "NEWEST") GetItemRequestDto.SortType sort,
                                  @RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(required = false) List<String> tags) {
        return itemService.getAllItems(GetItemRequestDto.builder()
                .userId(userId)
                .contentType(contentType)
                .sort(sort)
                .limit(limit)
                .tags(tags)
                .build());
    }

    @PatchMapping("/{itemId}")
    public Item updateItem(@RequestHeader("X-Later-User-Id") Long userId,
                           @PathVariable("itemId") Long id,
                           @RequestBody ItemCreateDto itemCreateDto) {
        return itemService.updateItem(id, userId, itemCreateDto);
    }

    @PostMapping
    public Item saveItem(@RequestHeader("X-Later-User-Id") Long userId,
                         @RequestBody ItemCreateDto itemCreateDto) {
        return itemService.saveItem(userId, itemCreateDto);
    }

    @DeleteMapping("/{itemId}")
    public void removeItem(@RequestHeader("X-Later-User-Id") Long userId,
                           @PathVariable("itemId") Long id) {
        itemService.removeItem(userId, id);
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@RequestHeader("X-Later-User-Id") Long userId,
                            @PathVariable("itemId") Long itemId) {
        return itemService.getItemById(userId, itemId);
    }
}

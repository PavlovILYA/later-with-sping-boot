package ru.practicum.laterwithspringboot.item.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.practicum.laterwithspringboot.exception.InactiveUrlException;
import ru.practicum.laterwithspringboot.item.model.Item;

import java.util.HashMap;
import java.util.Map;

public class CheckUrlItemRepositoryImpl implements CheckUrlItemRepository {
    private final ItemRepository itemRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final Map<String, HttpStatus> urlStatusCache = new HashMap<>();

    public CheckUrlItemRepositoryImpl(@Lazy ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item saveItemAfterCheckUrl(Item item) {
        HttpStatus status;
        if (urlStatusCache.get(item.getResolvedUrl()) != null) {
            status = urlStatusCache.get(item.getResolvedUrl());
        } else {
            try {
                ResponseEntity<String> response = restTemplate.getForEntity(item.getResolvedUrl(), String.class);
                status = response.getStatusCode();
                urlStatusCache.put(item.getResolvedUrl(), status);
            } catch (Exception e) {
                throw new InactiveUrlException(e.getMessage());
            }
        }

        if (status != HttpStatus.OK && status != HttpStatus.MOVED_PERMANENTLY) {
            throw new InactiveUrlException(item.getResolvedUrl(), status);
        }
        return itemRepository.save(item);
    }
}

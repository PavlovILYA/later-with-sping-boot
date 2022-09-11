package ru.practicum.laterwithspringboot.item;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.practicum.laterwithspringboot.exception.InactiveUrlException;

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
        if (urlStatusCache.get(item.getUrl()) != null) {
            status = urlStatusCache.get(item.getUrl());
        } else {
            ResponseEntity<String> response = restTemplate.getForEntity(item.getUrl(), String.class);
            status = response.getStatusCode();
            urlStatusCache.put(item.getUrl(), status);
        }

        if (status != HttpStatus.OK) {
            throw new InactiveUrlException(item.getUrl(), status);
        }
        return itemRepository.save(item);
    }
}

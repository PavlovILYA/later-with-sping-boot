package ru.practicum.laterwithspringboot.item.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.laterwithspringboot.exception.ItemNotFoundException;
import ru.practicum.laterwithspringboot.exception.UserNotFoundException;
import ru.practicum.laterwithspringboot.item.ItemMapper;
import ru.practicum.laterwithspringboot.item.model.GetItemRequestDto;
import ru.practicum.laterwithspringboot.item.model.Item;
import ru.practicum.laterwithspringboot.item.model.ItemCreateDto;
import ru.practicum.laterwithspringboot.item.model.QItem;
import ru.practicum.laterwithspringboot.item.repository.ItemRepository;
import ru.practicum.laterwithspringboot.urlmetadata.model.UrlMetadata;
import ru.practicum.laterwithspringboot.urlmetadata.service.UrlMetadataRetriever;
import ru.practicum.laterwithspringboot.user.model.User;
import ru.practicum.laterwithspringboot.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final UrlMetadataRetriever urlMetadataRetriever;

    @Override
    public List<Item> getAllItems(GetItemRequestDto request) {
        QItem qItem = QItem.item;
        List<BooleanExpression> conditions = new ArrayList<>();

        conditions.add(qItem.user.id.eq(request.getUserId()));

        GetItemRequestDto.ContentType contentType = request.getContentType();
        if(!contentType.equals(GetItemRequestDto.ContentType.ALL)) {
            conditions.add(makeContentTypeCondition(contentType));
        }

        if(request.getTags() != null) {
            conditions.add(qItem.tags.any().in(request.getTags()));
        }

        BooleanExpression finalCondition = conditions.stream()
                .reduce(BooleanExpression::and)
                .get();

        Sort sort = makeOrderByClause(request.getSort());
        PageRequest pageRequest = PageRequest.of(0, request.getLimit(), sort);

        Iterable<Item> items = itemRepository.findAll(finalCondition, pageRequest);
        return StreamSupport.stream(items.spliterator(), false)
                .collect(Collectors.toList());
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

    private BooleanExpression makeContentTypeCondition(GetItemRequestDto.ContentType contentType) {
        switch (contentType) {
            case TEXT: return QItem.item.mimeType.eq("TEXT");
            case IMAGE: return QItem.item.mimeType.eq("IMAGE");
            case VIDEO: return QItem.item.mimeType.eq("VIDEO");
            default: throw new RuntimeException("Wring content type");
        }
    }

    private Sort makeOrderByClause(GetItemRequestDto.SortType sort) {
        switch (sort) {
            case TITLE: return Sort.by("title").ascending();
            case OLDEST: return Sort.by("dateResolved").ascending();
            case NEWEST:
            default: return Sort.by("dateResolved").descending();
        }
    }
}

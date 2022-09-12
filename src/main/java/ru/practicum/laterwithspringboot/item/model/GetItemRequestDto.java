package ru.practicum.laterwithspringboot.item.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class GetItemRequestDto {
    private Long userId;
    private ContentType contentType;
    private SortType sort;
    private int limit;
    private List<String> tags;

    public enum ContentType {
        TEXT,
        IMAGE,
        VIDEO,
        ALL
    }

    public enum SortType {
        NEWEST,
        OLDEST,
        TITLE
    }
}

package ru.practicum.laterwithspringboot.urlmetadata.model;

import java.time.LocalDateTime;

public interface UrlMetadata {
    String getNormalUrl();
    String getResolvedUrl();
    String getMimeType();
    String getTitle();
    boolean isHasImage();
    boolean isHasVideo();
    LocalDateTime getResolvedDate();
}

package ru.practicum.laterwithspringboot.urlmetadata.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class UrlMetadataImpl implements UrlMetadata {
    private String normalUrl;
    private String resolvedUrl;
    private String mimeType;
    private String title;
    private boolean hasImage;
    private boolean hasVideo;
    private LocalDateTime resolvedDate;
}

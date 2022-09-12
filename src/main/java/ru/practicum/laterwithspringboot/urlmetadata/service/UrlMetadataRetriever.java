package ru.practicum.laterwithspringboot.urlmetadata.service;

import ru.practicum.laterwithspringboot.urlmetadata.model.UrlMetadata;

public interface UrlMetadataRetriever {
    UrlMetadata retrieve(String urlString);
}

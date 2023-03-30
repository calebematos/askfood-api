package com.calebematos.askfood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface PhotoStorageService {

    void store(NewPhoto newPhoto);

    void remove(String fileName);

    InputStream retrieve(String fileName);

    default void replace(String existingFileName, NewPhoto newPhoto) {
        store(newPhoto);
        if (existingFileName != null) {
            remove(existingFileName);
        }
    }

    default String generateFileName(String originalName) {
        return UUID.randomUUID().toString().concat("_").concat(originalName);
    }

    @Getter
    @Builder
    class NewPhoto {
        private String fileName;
        private InputStream inputStream;
    }
}

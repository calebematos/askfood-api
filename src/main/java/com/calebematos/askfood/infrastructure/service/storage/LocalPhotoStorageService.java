package com.calebematos.askfood.infrastructure.service.storage;

import com.calebematos.askfood.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalPhotoStorageService implements PhotoStorageService {

    @Value("${askfood.storage.local.photo-directory}")
    private Path photoDirectory;

    @Override
    public void store(NewPhoto newPhoto) {
        try {
            Path path = getFilePath(newPhoto.getFileName());

            FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(path));

        } catch (Exception e) {
            throw StorageException.of("Could not store the file", e);
        }
    }

    @Override
    public void remove(String fileName) {
        try {
            Path path = getFilePath(fileName);
            Files.deleteIfExists(path);
        } catch (Exception e) {
            throw StorageException.of("Could not remove the file", e);
        }

    }

    @Override
    public InputStream retrieve(String fileName) {
        try {
            Path path = getFilePath(fileName);
            return Files.newInputStream(path);
        } catch (Exception e) {
            throw StorageException.of("Could not retrieve the file", e);
        }
    }

    private Path getFilePath(String fileName) {
        return photoDirectory.resolve(Path.of(fileName));
    }
}

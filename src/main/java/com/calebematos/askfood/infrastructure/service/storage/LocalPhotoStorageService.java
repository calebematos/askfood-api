package com.calebematos.askfood.infrastructure.service.storage;

import com.calebematos.askfood.core.storage.StorageProperties;
import com.calebematos.askfood.domain.service.PhotoStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor
public class LocalPhotoStorageService implements PhotoStorageService {

    private final StorageProperties storageProperties;

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
    public RecoveredPhoto retrieve(String fileName) {
        try {
            Path path = getFilePath(fileName);
            InputStream inputStream = Files.newInputStream(path);

            return RecoveredPhoto.builder()
                    .inputStream(inputStream)
                    .build();
        } catch (Exception e) {
            throw StorageException.of("Could not retrieve the file", e);
        }
    }

    private Path getFilePath(String fileName) {
        return storageProperties.getLocal().getPhotoDirectory()
                .resolve(Path.of(fileName));
    }
}

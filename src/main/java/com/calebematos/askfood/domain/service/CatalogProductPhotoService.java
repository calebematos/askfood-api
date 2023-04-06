package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.exception.ProductPhotoNotFoundException;
import com.calebematos.askfood.domain.model.ProductPhoto;
import com.calebematos.askfood.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class CatalogProductPhotoService {

    private final ProductRepository productRepository;
    private final PhotoStorageService photoStorageService;

    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto, InputStream inputStream) {

        Long productId = productPhoto.getProduct().getId();

        var photoById = productRepository.findPhotoById(productId);
        var fileName = photoStorageService.generateFileName(productPhoto.getFileName());
        String existingFileName = null;

        if (photoById.isPresent()) {
            existingFileName = photoById.get().getFileName();
            productRepository.delete(photoById.get());
        }

        productPhoto.setFileName(fileName);
        ProductPhoto photo = productRepository.save(productPhoto);
        productRepository.flush();

        PhotoStorageService.NewPhoto newPhoto = PhotoStorageService.NewPhoto
                .builder()
                .fileName(fileName)
                .contentType(photo.getContentType())
                .inputStream(inputStream)
                .build();

        photoStorageService.replace(existingFileName, newPhoto);

        return photo;
    }

    public ProductPhoto find(Long restaurantId, Long productId) {
        return productRepository.findPhotoById(productId)
                .orElseThrow(() -> ProductPhotoNotFoundException.of(restaurantId, productId));

    }

    public PhotoStorageService.RecoveredPhoto getPhoto(ProductPhoto productPhoto) {
        return photoStorageService.retrieve(productPhoto.getFileName());
    }

    @Transactional
    public void deletePhoto(Long restaurantId, Long productId) {
        ProductPhoto productPhoto = find(restaurantId, productId);
        productRepository.delete(productPhoto);
        productRepository.flush();

        photoStorageService.remove(productPhoto.getFileName());
    }
}

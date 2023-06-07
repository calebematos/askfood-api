package com.calebematos.askfood.api.v1.controller;

import com.calebematos.askfood.api.v1.mapper.ProductPhotoMapper;
import com.calebematos.askfood.api.v1.model.ProductPhotoModel;
import com.calebematos.askfood.api.v1.model.input.ProductPhotoInput;
import com.calebematos.askfood.api.v1.openapi.controller.RestaurantProductPhotoControllerOpenApi;
import com.calebematos.askfood.domain.exception.EntityNotFoundException;
import com.calebematos.askfood.domain.model.Product;
import com.calebematos.askfood.domain.model.ProductPhoto;
import com.calebematos.askfood.domain.service.CatalogProductPhotoService;
import com.calebematos.askfood.domain.service.PhotoStorageService;
import com.calebematos.askfood.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/products/{productId}/photo")
@RequiredArgsConstructor
public class RestaurantProductPhotoController implements RestaurantProductPhotoControllerOpenApi {

    private final ProductService productService;
    private final CatalogProductPhotoService catalogProductPhotoService;
    private final ProductPhotoMapper mapper;

    @Override
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoModel updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
                                         @Valid ProductPhotoInput productPhotoInput) throws IOException {

        Product product = productService.findById(restaurantId, productId);
        MultipartFile photoFile = productPhotoInput.getFile();

        ProductPhoto photo = new ProductPhoto();
        photo.setProduct(product);
        photo.setSize(photoFile.getSize());
        photo.setDescription(productPhotoInput.getDescription());
        photo.setFileName(photoFile.getOriginalFilename());
        photo.setContentType(photoFile.getContentType());

        ProductPhoto productPhoto = catalogProductPhotoService.save(photo, photoFile.getInputStream());

        return mapper.toModel(productPhoto);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPhotoModel find(@PathVariable Long restaurantId, @PathVariable Long productId) {
        productService.findById(restaurantId, productId);
        ProductPhoto productPhoto = catalogProductPhotoService.find(restaurantId, productId);

        return mapper.toModel(productPhoto);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getPhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
                                      @RequestHeader(name = "accept") String acceptHeader)
            throws HttpMediaTypeNotAcceptableException {

        try {
            productService.findById(restaurantId, productId);
            ProductPhoto productPhoto = catalogProductPhotoService.find(restaurantId, productId);

            MediaType photoMediaType = MediaType.parseMediaType(productPhoto.getContentType());
            List<MediaType> acceptableMediaTypes = MediaType.parseMediaTypes(acceptHeader);

            checkMediaTypeCompatibility(photoMediaType, acceptableMediaTypes);

            PhotoStorageService.RecoveredPhoto recoveredPhoto = catalogProductPhotoService.getPhoto(productPhoto);

            if (recoveredPhoto.hasUrl()) {
                return ResponseEntity.status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, recoveredPhoto.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(new InputStreamResource(recoveredPhoto.getInputStream()));
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePhoto(@PathVariable Long restaurantId, @PathVariable Long productId) {
        catalogProductPhotoService.deletePhoto(restaurantId, productId);
    }

    private void checkMediaTypeCompatibility(MediaType photoMediaType, List<MediaType> acceptableMediaTypes)
            throws HttpMediaTypeNotAcceptableException {

        boolean compatible = acceptableMediaTypes.stream()
                .anyMatch(mediaType -> mediaType.isCompatibleWith(photoMediaType));

        if (!compatible) {
            throw new HttpMediaTypeNotAcceptableException(acceptableMediaTypes);
        }

    }

}

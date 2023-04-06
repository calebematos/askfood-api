package com.calebematos.askfood.infrastructure.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.calebematos.askfood.core.storage.StorageProperties;
import com.calebematos.askfood.domain.service.PhotoStorageService;
import lombok.RequiredArgsConstructor;

import java.net.URL;

@RequiredArgsConstructor
public class S3PhotoStorageService implements PhotoStorageService {

    private final AmazonS3 amazonS3;
    private final StorageProperties storageProperties;

    @Override
    public void store(NewPhoto newPhoto) {
        try {
            String pathFile = getPathFile(newPhoto.getFileName());

            var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(newPhoto.getContentType());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    pathFile,
                    newPhoto.getInputStream(),
                    objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw StorageException.of("Unable to send file to Amazon S3", e);
        }
    }

    @Override
    public void remove(String fileName) {
        try {
            String pathFile = getPathFile(fileName);

            var deleteObjectRequest = new DeleteObjectRequest(
                    storageProperties.getS3().getBucket(),
                    pathFile);

            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw StorageException.of("Could not remove the file from Amazon S3", e);
        }
    }

    @Override
    public RecoveredPhoto retrieve(String fileName) {

        String pathFile = getPathFile(fileName);
        URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), pathFile);
        return RecoveredPhoto.builder()
                .url(url.toString())
                .build();
    }

    private String getPathFile(String fileName) {
        return String.format("%s/%s", storageProperties.getS3().getPhotoDirectory(), fileName);
    }
}

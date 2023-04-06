package com.calebematos.askfood.core.storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.calebematos.askfood.domain.service.PhotoStorageService;
import com.calebematos.askfood.infrastructure.service.storage.LocalPhotoStorageService;
import com.calebematos.askfood.infrastructure.service.storage.S3PhotoStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StorageConfig {

    private final StorageProperties storageProperties;

    @Bean
    @ConditionalOnProperty(name = "askfood.storage.type", havingValue = "s3")
    public AmazonS3 amazonS3() {
        var credentials = new BasicAWSCredentials(storageProperties.getS3().getIdAccessKey(),
                storageProperties.getS3().getSecretAccessKey());

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegion())
                .build();
    }

    @Bean
    public PhotoStorageService photoStorageService() {
        if (StorageProperties.StorageType.S3.equals(storageProperties.getType())) {
            return new S3PhotoStorageService(amazonS3(), storageProperties);
        } else {
            return new LocalPhotoStorageService(storageProperties);
        }
    }

}

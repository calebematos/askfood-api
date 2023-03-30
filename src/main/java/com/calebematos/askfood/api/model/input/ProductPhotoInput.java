package com.calebematos.askfood.api.model.input;

import com.calebematos.askfood.core.validation.FileContentType;
import com.calebematos.askfood.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductPhotoInput {

    @NotNull
    @FileSize(max = "2MB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile file;

    @NotBlank
    private String description;

}

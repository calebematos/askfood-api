package com.calebematos.askfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoModel {

    private String fileName;
    private String description;
    private String contentType;
    private Long size;

}

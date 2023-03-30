package com.calebematos.askfood.domain.repository;

import com.calebematos.askfood.domain.model.ProductPhoto;

public interface ProductRepositoryQueries {

    ProductPhoto save(ProductPhoto productPhoto);

    void delete(ProductPhoto productPhoto);
}

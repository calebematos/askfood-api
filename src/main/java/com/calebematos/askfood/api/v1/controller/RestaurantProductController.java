package com.calebematos.askfood.api.v1.controller;

import com.calebematos.askfood.api.v1.mapper.ProductMapper;
import com.calebematos.askfood.api.v1.model.ProductModel;
import com.calebematos.askfood.api.v1.model.input.ProductInput;
import com.calebematos.askfood.api.v1.openapi.controller.RestaurantProductControllerOpenApi;
import com.calebematos.askfood.domain.model.Product;
import com.calebematos.askfood.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/products")
@RequiredArgsConstructor
public class RestaurantProductController implements RestaurantProductControllerOpenApi {

    private final ProductService productService;
    private final ProductMapper mapper;

    @Override
    @GetMapping
    public List<ProductModel> list(@PathVariable Long restaurantId,
                                   @RequestParam(required = false) boolean addInactive) {
        List<Product> allProducts = productService.findAllProductsByRestaurant(restaurantId, addInactive);
        return mapper.toCollectionModel(allProducts);
    }

    @Override
    @GetMapping("/{productId}")
    public ProductModel find(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = productService.findById(restaurantId, productId);
        return mapper.toModel(product);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductModel add(@PathVariable Long restaurantId, @RequestBody @Valid ProductInput productInput) {
        Product product = mapper.toDomainObject(productInput);
        return mapper.toModel(productService.newProduct(product, restaurantId));
    }

    @Override
    @PutMapping("/{productId}")
    public ProductModel update(@PathVariable Long restaurantId, @PathVariable Long productId,
                               @RequestBody @Valid ProductInput productInput) {
        Product currentProduct = productService.findById(restaurantId, productId);

        mapper.copyToDomainObject(productInput, currentProduct);

        return mapper.toModel(productService.save(currentProduct));
    }


}

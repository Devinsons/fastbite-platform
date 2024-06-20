package com.acme.fastbite.platform.planning.interfaces.rest.transform;


import com.acme.fastbite.platform.planning.domain.model.aggregates.Product;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product product) {
        return new ProductResource(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.getType()
        );
    }
}
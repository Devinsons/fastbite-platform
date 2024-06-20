package com.acme.fastbite.platform.planning.domain.services;

import com.acme.fastbite.platform.planning.domain.model.aggregates.Product;
import com.acme.fastbite.platform.planning.domain.model.commands.CreateProductCommand;

import java.util.Optional;

public interface ProductCommandService {
    //Optional<Product> handle(CreateProductCommand command);
    Product handle(CreateProductCommand command);
}

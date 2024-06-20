package com.acme.fastbite.platform.planning.domain.model.aggregates;

import com.acme.fastbite.platform.planning.domain.model.commands.CreateProductCommand;
import com.acme.fastbite.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Product extends AuditableAbstractAggregateRoot<Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String image;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    protected Product() {}

    public Product(String name, String description, String image, String type, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.type = type;
        this.restaurant = restaurant;
    }
}

package com.acme.fastbite.platform.planning.interfaces.rest;

import com.acme.fastbite.platform.planning.application.internal.outboundservices.acl.ExternalProfileService;
import com.acme.fastbite.platform.planning.domain.model.queries.GetRestaurantByAcmeRestaurantRecordIdQuery;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeRestaurantRecordId;
import com.acme.fastbite.platform.planning.domain.services.RestaurantCommandService;
import com.acme.fastbite.platform.planning.domain.services.RestaurantQueryService;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.CreateRestaurantResource;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.RestaurantResource;
import com.acme.fastbite.platform.planning.interfaces.rest.transform.CreateRestaurantCommandFromResourceAssembler;
import com.acme.fastbite.platform.planning.interfaces.rest.transform.RestaurantResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Restaurants", description = "Restaurant Management Endpoints")
public class RestaurantController {
    private final RestaurantCommandService restaurantCommandService;
    private final RestaurantQueryService restaurantQueryService;
    private final ExternalProfileService externalProfileService;

    public RestaurantController(RestaurantCommandService restaurantCommandService, RestaurantQueryService restaurantQueryService, ExternalProfileService externalProfileService) {
        this.restaurantCommandService = restaurantCommandService;
        this.restaurantQueryService = restaurantQueryService;
        this.externalProfileService = externalProfileService;

    }

    @PostMapping
    public ResponseEntity<RestaurantResource> createStudent(@RequestBody CreateRestaurantResource resource) {
        /**
         *   var createRestaurantCommand= CreateRestaurantCommandFromResourceAssembler.toCommandFromResource(resource);
         *         var restaurantId = restaurantCommandService.handle(createRestaurantCommand);
         *         if (restaurantId.restaurantRecordId().isEmpty()) {
         *             return ResponseEntity.badRequest().build();
         *         }
         *         var getRestaurantByAcmeRestaurantRecordIdQuery = new GetRestaurantByAcmeRestaurantRecordIdQuery(restaurantId);
         *         var restaurant = restaurantQueryService.handle(getRestaurantByAcmeRestaurantRecordIdQuery);
         *         if (restaurant.isEmpty()) {
         *             return ResponseEntity.badRequest().build();
         *         }
         *         var restaurantResource = RestaurantResourceFromEntityAssembler.toResourceFromEntity(restaurant.get());
         *         return new ResponseEntity<>(restaurantResource, HttpStatus.CREATED);
         */
        var createRestaurantCommand = CreateRestaurantCommandFromResourceAssembler.toCommandFromResource(resource);
        var restaurantId = restaurantCommandService.handle(createRestaurantCommand);
        if (restaurantId.restaurantRecordId().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var getRestaurantByAcmeRestaurantRecordIdQuery = new GetRestaurantByAcmeRestaurantRecordIdQuery(restaurantId);
        var restaurant = restaurantQueryService.handle(getRestaurantByAcmeRestaurantRecordIdQuery);
        if (restaurant.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var restaurantResource = RestaurantResourceFromEntityAssembler.toResourceFromEntity(restaurant.get(), externalProfileService);
        return new ResponseEntity<>(restaurantResource, HttpStatus.CREATED);
    }

    @GetMapping("/{restaurantRecordId}")
    public ResponseEntity<RestaurantResource> getStudentByAcmeStudentRecordId(@PathVariable String restaurantRecordId) {
        /**
         * var acmeRestaurantRecordId = new AcmeRestaurantRecordId(restaurantRecordId);
         *         var getRestaurantByAcmeRestaurantRecordIdQuery = new GetRestaurantByAcmeRestaurantRecordIdQuery(acmeRestaurantRecordId);
         *         var restaurant = restaurantQueryService.handle(getRestaurantByAcmeRestaurantRecordIdQuery);
         *         if (restaurant.isEmpty()) {
         *             return ResponseEntity.notFound().build();
         *         }
         *         var restaurantResource = RestaurantResourceFromEntityAssembler.toResourceFromEntity(restaurant.get());
         *         return ResponseEntity.ok(restaurantResource);
         */
        var acmeRestaurantRecordId = new AcmeRestaurantRecordId(restaurantRecordId);
        var getRestaurantByAcmeRestaurantRecordIdQuery = new GetRestaurantByAcmeRestaurantRecordIdQuery(acmeRestaurantRecordId);
        var restaurant = restaurantQueryService.handle(getRestaurantByAcmeRestaurantRecordIdQuery);
        if (restaurant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var restaurantResource = RestaurantResourceFromEntityAssembler.toResourceFromEntity(restaurant.get(), externalProfileService);
        return ResponseEntity.ok(restaurantResource);
    }

    /**
     *  @GetMapping
     *     public ResponseEntity<List<RestaurantResource>> getAllRestaurants() {
     *         var restaurants = restaurantQueryService.getAllRestaurants();
     *         var restaurantResources = restaurants.stream()
     *                 .map(RestaurantResourceFromEntityAssembler::toResourceFromEntity)
     *                 .collect(Collectors.toList());
     *         return ResponseEntity.ok(restaurantResources);
     *     }
     *
     */

    @GetMapping
    public ResponseEntity<List<RestaurantResource>> getAllRestaurants() {
        var restaurants = restaurantQueryService.getAllRestaurants();
        var restaurantResources = restaurants.stream()
                .map(restaurant -> RestaurantResourceFromEntityAssembler.toResourceFromEntity(restaurant, externalProfileService))
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurantResources);
    }
}

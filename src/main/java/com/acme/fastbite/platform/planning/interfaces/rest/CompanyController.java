package com.acme.fastbite.platform.planning.interfaces.rest;

import com.acme.fastbite.platform.planning.application.internal.outboundservices.acl.ExternalProfileService;
import com.acme.fastbite.platform.planning.domain.model.queries.GetCompanyByAcmeCompanyRecordIdQuery;
import com.acme.fastbite.platform.planning.domain.model.valueobjects.AcmeCompanyRecordId;
import com.acme.fastbite.platform.planning.domain.services.CompanyCommandService;
import com.acme.fastbite.platform.planning.domain.services.CompanyQueryService;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.CreateCompanyResource;
import com.acme.fastbite.platform.planning.interfaces.rest.resources.CompanyResource;
import com.acme.fastbite.platform.planning.interfaces.rest.transform.CreateCompanyCommandFromResourceAssembler;
import com.acme.fastbite.platform.planning.interfaces.rest.transform.CompanyResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/companys", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Companys", description = "Company Management Endpoints")
public class CompanyController {
    private final CompanyCommandService companyCommandService;
    private final CompanyQueryService companyQueryService;
    private final ExternalProfileService externalProfileService;

    public CompanyController(CompanyCommandService companyCommandService, CompanyQueryService companyQueryService, ExternalProfileService externalProfileService) {
        this.companyCommandService = companyCommandService;
        this.companyQueryService = companyQueryService;
        this.externalProfileService = externalProfileService;

    }

    @PostMapping
    public ResponseEntity<CompanyResource> createStudent(@RequestBody CreateCompanyResource resource) {
        /**
         *   var createCompanyCommand= CreateCompanyCommandFromResourceAssembler.toCommandFromResource(resource);
         *         var companyId = companyCommandService.handle(createCompanyCommand);
         *         if (companyId.companyRecordId().isEmpty()) {
         *             return ResponseEntity.badRequest().build();
         *         }
         *         var getCompanyByAcmeCompanyRecordIdQuery = new GetCompanyByAcmeCompanyRecordIdQuery(companyId);
         *         var company = companyQueryService.handle(getCompanyByAcmeCompanyRecordIdQuery);
         *         if (company.isEmpty()) {
         *             return ResponseEntity.badRequest().build();
         *         }
         *         var companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(company.get());
         *         return new ResponseEntity<>(companyResource, HttpStatus.CREATED);
         */
        var createCompanyCommand = CreateCompanyCommandFromResourceAssembler.toCommandFromResource(resource);
        var companyId = companyCommandService.handle(createCompanyCommand);
        if (companyId.companyRecordId().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var getCompanyByAcmeCompanyRecordIdQuery = new GetCompanyByAcmeCompanyRecordIdQuery(companyId);
        var company = companyQueryService.handle(getCompanyByAcmeCompanyRecordIdQuery);
        if (company.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(company.get(), externalProfileService);
        return new ResponseEntity<>(companyResource, HttpStatus.CREATED);
    }

    @GetMapping("/{companyRecordId}")
    public ResponseEntity<CompanyResource> getStudentByAcmeStudentRecordId(@PathVariable String companyRecordId) {
        /**
         * var acmeCompanyRecordId = new AcmeCompanyRecordId(companyRecordId);
         *         var getCompanyByAcmeCompanyRecordIdQuery = new GetCompanyByAcmeCompanyRecordIdQuery(acmeCompanyRecordId);
         *         var company = companyQueryService.handle(getCompanyByAcmeCompanyRecordIdQuery);
         *         if (company.isEmpty()) {
         *             return ResponseEntity.notFound().build();
         *         }
         *         var companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(company.get());
         *         return ResponseEntity.ok(companyResource);
         */
        var acmeCompanyRecordId = new AcmeCompanyRecordId(companyRecordId);
        var getCompanyByAcmeCompanyRecordIdQuery = new GetCompanyByAcmeCompanyRecordIdQuery(acmeCompanyRecordId);
        var company = companyQueryService.handle(getCompanyByAcmeCompanyRecordIdQuery);
        if (company.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(company.get(), externalProfileService);
        return ResponseEntity.ok(companyResource);
    }

    /**
     *  @GetMapping
     *     public ResponseEntity<List<CompanyResource>> getAllCompanys() {
     *         var companys = companyQueryService.getAllCompanys();
     *         var companyResources = companys.stream()
     *                 .map(CompanyResourceFromEntityAssembler::toResourceFromEntity)
     *                 .collect(Collectors.toList());
     *         return ResponseEntity.ok(companyResources);
     *     }
     *
     */

    @GetMapping
    public ResponseEntity<List<CompanyResource>> getAllCompanys() {
        var companys = companyQueryService.getAllCompanys();
        var companyResources = companys.stream()
                .map(company -> CompanyResourceFromEntityAssembler.toResourceFromEntity(company, externalProfileService))
                .collect(Collectors.toList());
        return ResponseEntity.ok(companyResources);
    }
}


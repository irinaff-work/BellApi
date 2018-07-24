package ru.bellintegrator.practice.organization.controller;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.organization.view.OrganizationViewFull;
import ru.bellintegrator.practice.validate.SuccessView;

import java.util.List;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/practice", produces = APPLICATION_JSON_VALUE)
@Api(value = "Организации", description = "Список организаций")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    @ApiOperation(value = "Поиск организации по краткому наименованию и ИНН", nickname = "filterOrganization", httpMethod = "POST")
    @PostMapping("organizations/list")
    public @ResponseBody
    Set<OrganizationView> organizationList(@RequestBody OrganizationView organization)
            throws Exception {
        return organizationService.loadByNameAndInn(organization);
    }

    @ApiOperation(value = "Поиск организации по id", nickname = "Organization", httpMethod = "GET")
    @GetMapping("organizations/{id}")
    public @ResponseBody OrganizationViewFull filteredId(
            @PathVariable("id") @ApiParam(value = "Идентификатор организации") Long id
    ) {
        return organizationService.loadById(id);
    }

    @ApiOperation(value = "Редактирование организации по id", nickname = "Organization", httpMethod = "POST")
    @PostMapping("organizations/update")
    public @ResponseBody
    void updateOrganizationView(@RequestBody OrganizationViewFull organization)
    {
        organizationService.update(organization);
    }

    @ApiOperation(value = "Сохранение организации", nickname = "Organization", httpMethod = "POST")
    @PostMapping("organizations/save")
    public @ResponseBody
    void createOrganization(@RequestBody OrganizationViewFull organization)
    {
        organizationService.save(organization);
    }
}


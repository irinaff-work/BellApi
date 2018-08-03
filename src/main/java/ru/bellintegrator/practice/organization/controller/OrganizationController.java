package ru.bellintegrator.practice.organization.controller;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.organization.view.OrganizationViewFull;

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

    /**
     * Получить список всех организаций
     */
    @ApiOperation(value = "Все организации", nickname = "all", httpMethod = "GET")
    @GetMapping("organizations/list/all")
    public Set<OrganizationViewFull> all() {
        return organizationService.all();
    }

    @ApiOperation(value = "Поиск организации по краткому наименованию и ИНН", nickname = "filterOrganization", httpMethod = "POST")
    @PostMapping("organizations/list")
    public @ResponseBody
    Set<OrganizationView> organizationList(@RequestBody OrganizationView organization) {
        return organizationService.loadByNameAndInn(organization);
    }

    @ApiOperation(value = "Поиск организации по id", nickname = "Organization", httpMethod = "GET")
    @GetMapping("organizations/{id}")
    public @ResponseBody OrganizationViewFull filteredId(
            @PathVariable("id") @ApiParam(value = "Идентификатор организации") Long id
    ) {
        return organizationService.loadById(id);
    }

    @ApiOperation(value = "Редактирование организации", nickname = "Organization", httpMethod = "POST")
    @PostMapping("organizations/update")
    public @ResponseBody
    void updateOrganizationView(@RequestBody OrganizationViewFull organization)
    {
        organizationService.update(organization);
    }

    @ApiOperation(value = "Добавление организации", nickname = "Organization", httpMethod = "POST")
    @PostMapping("organizations/add")
    public @ResponseBody
    void createOrganization(@RequestBody OrganizationViewFull organization)
    {
        organizationService.add(organization);
    }

    /**
     * Удалить организацию по Id
     */
    @ApiOperation(value = "Удалить организацию по Id", nickname = "deleteOrganization", httpMethod = "POST")
    @PostMapping("organizations/delete{id}")
    public void deleteOrganization (@PathVariable("id") @ApiParam(value = "Идентификатор организации") Long id) {
        organizationService.delete(id);
    }
}


package ru.bellintegrator.practice.organization.controller;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationSearch;
import ru.bellintegrator.practice.organization.view.OrganizationToSave;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/organizations", produces = APPLICATION_JSON_VALUE)
@Api(value = "Организации", description = "Список организаций")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "Поиск организации по краткому наименованию и ИНН", nickname = "filterOrganization", httpMethod = "POST")
    @PostMapping("/list")
    public @ResponseBody
    List<OrganizationView> organizationList(@RequestBody OrganizationSearch organization) {
        return organizationService.organizationList(organization);
    }

    @ApiOperation(value = "Поиск организации по id", nickname = "Organization", httpMethod = "GET")
    @GetMapping("/{id}")
    public @ResponseBody List<OrganizationView> filteredId(
            @PathVariable("id") @ApiParam(value = "Идентификатор организации") Long id
    ) {
        return organizationService.filteredId(id);
    }

    @ApiOperation(value = "Редактирование организации по id", nickname = "Organization", httpMethod = "POST")
    @PostMapping("/update")
    public @ResponseBody void  updateOrganizationView(@RequestBody OrganizationView organization)
     {
        organizationService.update(organization);
    }

    @ApiOperation(value = "Сохранение организации", nickname = "Organization", httpMethod = "POST")
    @PostMapping("/save")
    public @ResponseBody OrganizationView createOrganization(@RequestBody OrganizationToSave organization)
    {
        return organizationService.createOrganization(organization);
    }
}


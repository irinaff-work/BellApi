package ru.bellintegrator.practice.organization.controller;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.organization.view.OrganizationViewFull;

import java.util.List;
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
    List<OrganizationView> organizationList(@RequestBody OrganizationView organization)
            throws Exception {
            return organizationService.organizationList(organization);
    }

    @ApiOperation(value = "Поиск организации по id", nickname = "Organization", httpMethod = "GET")
    @GetMapping("organizations/{id}")
    public @ResponseBody List<OrganizationViewFull> filteredId(
            @PathVariable("id") @ApiParam(value = "Идентификатор организации") Long id
    ) {
        return organizationService.filteredId(id);
    }

    @ApiOperation(value = "Редактирование организации по id", nickname = "Organization", httpMethod = "POST")
    @PostMapping("organizations/update")
    public @ResponseBody void  updateOrganizationView(@RequestBody OrganizationViewFull organization)
     {
         try {
             organizationService.update(organization);
         } catch ( Exception e) {
             //что-то сделать
         }
    }

    @ApiOperation(value = "Сохранение организации", nickname = "Organization", httpMethod = "POST")
    @PostMapping("organizations/save")
    public @ResponseBody void createOrganization(@RequestBody OrganizationViewFull organization)
    {
        try {
            organizationService.createOrganization(organization);
        } catch ( Exception e) {
            //что-то сделать
        }
    }
}


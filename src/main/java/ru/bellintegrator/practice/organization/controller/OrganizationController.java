package ru.bellintegrator.practice.organization.controller;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organization.service.OrganizationService;
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
    List<OrganizationView> filteredName(
            @RequestParam(value = "name") @ApiParam(value = "Краткое наименование") String name,
            @RequestParam(value = "inn") @ApiParam(value = "ИНН, 12 симоволов", required = false) String inn,
            @RequestParam(value = "isActive") @ApiParam(value = "Действует", required = false) boolean isActive
    ) {
        return organizationService.filteredName(name, inn, isActive);
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
    public @ResponseBody OrganizationView update(
            @RequestParam(value ="id") @ApiParam(value = "Идентификатор организации") Long id,
            @RequestParam(value = "name") @ApiParam(value = "Краткое наименование") String name,
            @RequestParam(value = "fullName") @ApiParam(value = "Полное наименование") String fullName,
            @RequestParam(value = "inn") @ApiParam(value = "ИНН, 12 симоволов") String inn,
            @RequestParam(value = "kpp") @ApiParam(value = "КПП, 9 симоволов") String kpp,
            @RequestParam(value = "address") @ApiParam(value = "Адрес") String address,
            @RequestParam(value = "phone") @ApiParam(value = "Номер телефона") String phone,
            @RequestParam(value = "isActive", defaultValue = "true") @ApiParam(value = "Действует") boolean isActive
    ) {
        return organizationService.update(id, name, fullName, inn, kpp, address, phone, isActive);
    }

    @ApiOperation(value = "Сохранение организации", nickname = "Organization", httpMethod = "POST")
    @PostMapping("/save")
    public @ResponseBody OrganizationView save(
            @RequestParam(value ="id") @ApiParam(value = "Идентификатор организации") Long id,
            @RequestParam(value = "name") @ApiParam(value = "Краткое наименование") String name,
            @RequestParam(value = "fullName") @ApiParam(value = "Полное наименование") String fullName,
            @RequestParam(value = "inn") @ApiParam(value = "ИНН, 12 симоволов") String inn,
            @RequestParam(value = "kpp") @ApiParam(value = "КПП, 9 симоволов") String kpp,
            @RequestParam(value = "address") @ApiParam(value = "Адрес") String address,
            @RequestParam(value = "phone") @ApiParam(value = "Номер телефона") String phone,
            @RequestParam(value = "isActive", defaultValue = "true") @ApiParam(value = "Действует") boolean isActive)
    {
        return organizationService.save(id, name, fullName, inn, kpp, address, phone, isActive);
    }
}


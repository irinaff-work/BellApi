package ru.bellintegrator.practice.organization.controller;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import ru.bellintegrator.practice.organization.service.organizationService;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@RestController
@RequestMapping(value = "/organizations", produces = APPLICATION_JSON_VALUE)
@Api(value = "Организации", description = "Список организаций")
public class OrganizationController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private List<Organization> listOrganization = Arrays.asList(
            new Organization(1L, "Рога и Копыта", "ООО Рога и Копыта", "123456789012", "123456789","234-34-56", "г. Уфа, ул. Запредельная, д.2",true),
            new Organization(2L, "Салют", "ООО Салют", "123456789012", "123456789" ,"234-34-56", "г. Уфа, ул. Запредельная, д.2", true)
    );

    @ApiOperation(value = "Список всех организаций", nickname = "allOrganization", httpMethod = "POST")
    @PostMapping("/organizations/all")
    public @ResponseBody List<Organization> listAll() {
        return listOrganization;
    }

    @ApiOperation(value = "Поиск организации по краткому наименованию и ИНН", nickname = "filterOrganization", httpMethod = "POST")
    @PostMapping("/organizations/list")
    public @ResponseBody
    List<Organization> filteredName(
            @RequestParam(value = "name") @ApiParam(value = "Краткое наименование") String filterName,
            @RequestParam(value = "inn") @ApiParam(value = "ИНН, 12 симоволов") String filterInn,
            @RequestParam(value = "isActive") @ApiParam(value = "Действует") boolean filterActive
    ) {
        List<Organization> filteredOrganization = new ArrayList<Organization>();

        for (Organization item: listOrganization) {
            if ((filterName.isEmpty() || item.getName().equals(filterName))
                    && (filterInn.isEmpty() || item.getInn().equals(filterInn))
                    && (item.isActive() == filterActive)
                    ) {
                item.setActive(true);
                filteredOrganization.add(item);
            }
        }
        return filteredOrganization;
    }

    @ApiOperation(value = "Поиск организации по id", nickname = "Organization", httpMethod = "GET")
    @GetMapping("/organizations/{id}")
    public @ResponseBody List<Organization> filteredId(
            @PathVariable("id") @ApiParam(value = "Идентификатор организации") Long orgId
    ) {
        List<Organization> filteredOrganization = new ArrayList<Organization>();

        for (Organization item: listOrganization) {
            if (item.getId().equals(orgId)) {
                filteredOrganization.add(item);
            }
        }
        return filteredOrganization;
    }

    @ApiOperation(value = "Редактирование организации по id", nickname = "Organization", httpMethod = "POST")
    @PostMapping("/organizations/update")
    public @ResponseBody String updateOrgForId(
            @RequestParam(value ="id") @ApiParam(value = "Идентификатор организации") Long inId,
            @RequestParam(value = "name") @ApiParam(value = "Краткое наименование") String inName,
            @RequestParam(value = "fullName") @ApiParam(value = "Полное наименование") String inFullName,
            @RequestParam(value = "inn") @ApiParam(value = "ИНН, 12 симоволов") String inInn,
            @RequestParam(value = "kpp") @ApiParam(value = "КПП, 9 симоволов") String inKpp,
            @RequestParam(value = "address") @ApiParam(value = "Адрес") String inAddress,
            @RequestParam(value = "phone") @ApiParam(value = "Номер телефона") String inPhone,
            @RequestParam(value = "isActive", defaultValue = "true") @ApiParam(value = "Действует") boolean inIsActive
    ) {
        String isUpdate = "";

        for (Organization item: listOrganization) {
            if (item.getId().equals(inId)) {
                item.setName(inName);
                item.setFullName(inFullName);
                item.setInn(inInn);
                item.setKpp(inKpp);
                item.setAddress(inAddress);
                if (!inPhone.isEmpty()) {
                    item.setPhone(inPhone);
                }
                item.setActive(true);
                isUpdate = "success";
            }
        }
        return isUpdate;
    }

    @ApiOperation(value = "Сохранение организации", nickname = "Organization", httpMethod = "POST")
    @PostMapping("/organizations/save")
    public @ResponseBody String saveOrgForId(
            @RequestParam(value ="id") @ApiParam(value = "Идентификатор организации") Long inId,
            @RequestParam(value = "name") @ApiParam(value = "Краткое наименование") String inName,
            @RequestParam(value = "fullName") @ApiParam(value = "Полное наименование") String inFullName,
            @RequestParam(value = "inn") @ApiParam(value = "ИНН, 12 симоволов") String inInn,
            @RequestParam(value = "kpp") @ApiParam(value = "КПП, 9 симоволов") String inKpp,
            @RequestParam(value = "address") @ApiParam(value = "Адрес") String inAddress,
            @RequestParam(value = "phone") @ApiParam(value = "Номер телефона") String inPhone,
            @RequestParam(value = "isActive", defaultValue = "true") @ApiParam(value = "Действует") boolean inIsActive
    ) {
        listOrganization.add(new Organization(inId,inName,inFullName,inInn,inKpp,inAddress,inPhone,true));
        return "success";
    }
}


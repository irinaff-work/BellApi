package ru.bellintegrator.practice.office.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.office.view.OfficeViewAll;

import javax.websocket.server.PathParam;


@RestController
@RequestMapping(value = "practice", produces = APPLICATION_JSON_VALUE)
@Api(value = "Офисы", description = "Список офисов")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }
    /**
     * Получить список офисов по Id организации
     */
    @ApiOperation(value = "Поиск офиса по id организации", nickname = "filteredOrgId", httpMethod = "POST")
    @PostMapping("office/list/")
    public @ResponseBody
    List<OfficeView> filteredOrgId(@RequestBody OfficeView officeView ) {
        //@PathVariable("idOrg") Long idOrg = officeView.orgId;
        return officeService.filteredOrgId(officeView);
    };

    /**
     * Получить список офисов по Id офиса
     *
     */
    @ApiOperation(value = "Поиск офиса по id офиса", nickname = "filteredId", httpMethod = "GET")
    @GetMapping("office/{id}")
    public @ResponseBody
    List<OfficeViewAll> filteredId(
            @PathVariable("id") @ApiParam(value = "Идентификатор офиса") Long id) {
            return officeService.filteredId(id);
    };

    /**
     * Изменить данные офиса
     */
    @ApiOperation(value = "Изменить информацию об офисе", nickname = "update", httpMethod = "POST")
    @PostMapping("office/update")
    public void update(@RequestBody OfficeViewAll officeView) {
        officeService.update(officeView);
    };

    /**
     * Добавить новый офис
     */
    @ApiOperation(value = "Добавить информацию об офисе", nickname = "createOffice", httpMethod = "POST")
    @PostMapping("office/save")
    public void createOffice (@RequestBody OfficeViewAll officeView) {
        officeService.createOffice(officeView);
    };
}

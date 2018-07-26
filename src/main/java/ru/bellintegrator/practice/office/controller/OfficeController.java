package ru.bellintegrator.practice.office.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.office.view.OfficeViewFull;

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
     * Получить список всех офисов
     */
    @ApiOperation(value = "Все офисы", nickname = "all", httpMethod = "GET")
    @GetMapping("office/list/all")
    public Set<OfficeViewFull> all() {
        return officeService.all();
    }

    /**
     * Получить список офисов по Id организации
     */
    @ApiOperation(value = "Поиск офиса по id организации", nickname = "filteredOrgId", httpMethod = "POST")
    @PostMapping("office/list/")
    public @ResponseBody
    Set<OfficeView> filteredOrgId(@RequestBody OfficeViewFull officeView ) {
        return officeService.loadByOrgId(officeView);
    };

    /**
     * Получить список офисов по Id офиса
     *
     */
    @ApiOperation(value = "Поиск офиса по id офиса", nickname = "filteredId", httpMethod = "GET")
    @GetMapping("office/{id}")
    public @ResponseBody
    OfficeViewFull filteredId(
            @PathVariable("id") @ApiParam(value = "Идентификатор офиса") Long id) {
            return officeService.loadById(id);
    };

    /**
     * Изменить данные офиса
     */
    @ApiOperation(value = "Изменить информацию об офисе", nickname = "update", httpMethod = "POST")
    @PostMapping("office/update")
    public void update(@RequestBody OfficeViewFull officeView) {
        officeService.update(officeView);
    };

    /**
     * Добавить новый офис
     */
    @ApiOperation(value = "Добавить информацию об офисе", nickname = "createOffice", httpMethod = "POST")
    @PostMapping("office/save")
    public void createOffice (@RequestBody OfficeViewFull officeView) {
        officeService.add(officeView);
    };
}

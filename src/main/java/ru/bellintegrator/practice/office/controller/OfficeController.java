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
import ru.bellintegrator.practice.office.view.OfficeViewUpdate;
import ru.bellintegrator.practice.office.view.OfficeViewSave;

@RestController
@RequestMapping(value = "practice", produces = APPLICATION_JSON_VALUE)
@Api(value = "Офисы", description = "Список офисов")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @ApiOperation(value = "Все офисы", nickname = "all", httpMethod = "GET")
    @GetMapping("office/list/all")
    public Set<OfficeViewSave> all() {
        return officeService.all();
    }

    @ApiOperation(value = "Поиск офиса по id организации", nickname = "filteredOrgId", httpMethod = "POST")
    @PostMapping("office/list/")
    public @ResponseBody
    Set<OfficeView> filteredOrgId(@RequestBody OfficeView view ) {
        return officeService.loadByOrgId(view);
    }

    @ApiOperation(value = "Поиск офиса по id офиса", nickname = "filteredId", httpMethod = "GET")
    @GetMapping("office/{id}")
    public @ResponseBody
    OfficeViewSave filteredId(
            @PathVariable("id") @ApiParam(value = "Идентификатор офиса") Long id) {
            return officeService.loadById(id);
    }

    @ApiOperation(value = "Изменить информацию об офисе", nickname = "update", httpMethod = "POST")
    @PostMapping("office/update")
    public void update(@RequestBody OfficeViewUpdate viewUpdate) {
        officeService.update(viewUpdate);
    }

    @ApiOperation(value = "Добавить информацию об офисе", nickname = "createOffice", httpMethod = "POST")
    @PostMapping("office/add")
    public void createOffice (@RequestBody OfficeViewSave viewSave) {
        officeService.add(viewSave);
    }

    @ApiOperation(value = "Удалить офис по Id", nickname = "deleteOffice", httpMethod = "POST")
    @PostMapping("office/delete{id}")
    public void deleteOffice (@PathVariable("id") @ApiParam(value = "Идентификатор офиса") Long id) {
        officeService.delete(id);
    }
}

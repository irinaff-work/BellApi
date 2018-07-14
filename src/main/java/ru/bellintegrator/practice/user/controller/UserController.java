package ru.bellintegrator.practice.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.office.view.OfficeViewAll;
import ru.bellintegrator.practice.user.service.UserService;
import ru.bellintegrator.practice.user.view.UserView;
import ru.bellintegrator.practice.user.view.UserViewShort;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "practice", produces = APPLICATION_JSON_VALUE)
@Api(value = "Пользователи", description = "Список полльзователей")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * Получить список пользователей по Id офиса
     *
     * @param userView
     * @return {@List<OfficeView>}
     */

    @ApiOperation(value = "Поиск пользователя по id офиса", nickname = "filteredOfficeId", httpMethod = "POST")
    @PostMapping("user/list/")
    public @ResponseBody
    List<UserViewShort> filteredOfficeId(@RequestBody UserViewShort userView) {
        return userService.filteredOfficeId(userView);
    };

    /**
     * Получить список пользователей по Id пользователя
     *
     */
    @ApiOperation(value = "Поиск пользователя по id пользователя", nickname = "filteredId", httpMethod = "GET")
    @GetMapping("user/{id}")
    public @ResponseBody List<UserView> filteredId(
            @PathVariable("id") @ApiParam(value = "Идентификатор пользователя") Long id) {
            return userService.filteredId(id);
    };

    /**
     * Изменить данные пользователя
     */
    @ApiOperation(value = "Изменить информацию о пользователе", nickname = "update", httpMethod = "POST")
    @PostMapping("user/update")
    public void update(@RequestBody UserView userView) {
        userService.update(userView);
    };

    /**
     * Добавить новый офис
     */
    @ApiOperation(value = "Добавить информацию о пользователе", nickname = "createOffice", httpMethod = "POST")
    @PostMapping("user/save")
    public void createUser(@RequestBody UserView userView) {
        userService.createUser(userView);
    };
}

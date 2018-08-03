package ru.bellintegrator.practice.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.user.service.UserService;
import ru.bellintegrator.practice.user.view.UserViewFull;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "practice", produces = APPLICATION_JSON_VALUE)
@Api(value = "Пользователи", description = "Список пользователей")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * Получить список всех пользователей
     */
    @ApiOperation(value = "Все пользователи", nickname = "all", httpMethod = "GET")
    @GetMapping("user/list/all")
    public Set<UserViewFull> all() {
        return userService.all();
    }

    /**
     * Получить список пользователей по фильтрам
     */

    @ApiOperation(value = "Поиск пользователя по id офиса", nickname = "filteredOfficeId", httpMethod = "POST")
    @PostMapping("user/list/")
    public @ResponseBody
    Set<UserView> loadByloadByFilter(@RequestBody UserViewFull userView) {
        return userService.loadByFilter(userView);
    };

    /**
     * Получить список пользователей по Id пользователя
     *
     */
    @ApiOperation(value = "Поиск пользователя по id пользователя", nickname = "filteredId", httpMethod = "GET")
    @GetMapping("user/{id}")
    public @ResponseBody UserViewFull filteredId(
            @PathVariable("id") @ApiParam(value = "Идентификатор пользователя") Long id) {
            return userService.loadById(id);
    };

    /**
     * Изменить данные пользователя
     */
    @ApiOperation(value = "Изменить информацию о пользователе", nickname = "update", httpMethod = "POST")
    @PostMapping("user/update")
    public void update(@RequestBody UserViewFull userView) {
        userService.update(userView);
    };

    /**
     * Добавить нового пользователя
     */
    @ApiOperation(value = "Добавить информацию о пользователе", nickname = "createOffice", httpMethod = "POST")
    @PostMapping("user/save")
    public void createUser(@RequestBody UserViewFull userView) {
        userService.add(userView);
    }

    /**
     * Удалить офис по Id
     */
    @ApiOperation(value = "Удалить пользователя по Id", nickname = "deleteUser", httpMethod = "POST")
    @PostMapping("user/delete{id}")
    public void deleteUser (@PathVariable("id") @ApiParam(value = "Идентификатор пользователя") Long id) {
        userService.delete(id);
    }
}

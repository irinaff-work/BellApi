package ru.bellintegrator.practice.user.service;

import ru.bellintegrator.practice.user.view.UserView;
import ru.bellintegrator.practice.user.view.UserViewShort;

import java.util.List;

public interface UserService {

    /**
     * Получить список пользователей по Id офиса
     *
     * @param userView
     * @return {@List<OfficeView>}
     */
    public List<UserViewShort> filteredOfficeId(UserViewShort userView  );

    /**
     * Получить список пользователей по Id пользователя
     *
     * @param id
     * @return {@List<OfficeViewAll>}
     */
    public List<UserView> filteredId(Long id);

    /**
     * Изменить данные пользователя
     *
     * @param userView
     */
    public void update(UserView userView);

    /**
     * Добавить нового пользователя
     *
     * @param userView
     * @return OfficeSave
     */
    public void createUser (UserView userView);
}

package ru.bellintegrator.practice.user.service;

import ru.bellintegrator.practice.user.view.UserViewFull;
import ru.bellintegrator.practice.user.view.UserView;
import ru.bellintegrator.practice.validate.SuccessView;

import java.util.List;

public interface UserService {

    /**
     * Получить список пользователей по Id офиса
     *
     * @param userView
     * @return {@List<OfficeView>}
     */
    public List<UserView> filteredOfficeId(UserView userView  );

    /**
     * Получить список пользователей по Id пользователя
     *
     * @param id
     * @return {@List<OfficeViewAll>}
     */
    public List<UserViewFull> filteredId(Long id);

    /**
     * Изменить данные пользователя
     *
     * @param userView
     */
    public SuccessView update(UserViewFull userView);

    /**
     * Добавить нового пользователя
     *
     * @param userView
     * @return OfficeSave
     */
    public SuccessView save (UserViewFull userView);
}

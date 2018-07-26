package ru.bellintegrator.practice.user.service;

import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserViewFull;
import ru.bellintegrator.practice.user.view.UserView;
import ru.bellintegrator.practice.validate.SuccessView;

import java.util.Set;

public interface UserService {

    /**
     * Получить список всех пользователей
     *
     * @return {@Set<User>}
     */
    public Set<UserViewFull> all ();

    /**
     * Получить список пользователей по Id офиса
     *
     * @param userView
     * @return {@Set<OfficeView>}
     */
    public Set<UserView> loadByOfficeId(UserView userView  );

    /**
     * Получить список пользователей по Id пользователя
     *
     * @param id
     * @return {@Set<OfficeViewAll>}
     */
    public UserViewFull loadById(Long id);

    /**
     * Изменить данные пользователя
     *
     * @param userView
     */
    public void update(UserViewFull userView);

    /**
     * Добавить нового пользователя
     *
     * @param userView
     * @return OfficeSave
     */
    public void add (UserViewFull userView);

}

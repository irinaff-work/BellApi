package ru.bellintegrator.practice.user.service;

import ru.bellintegrator.practice.user.view.UserViewAdd;
import ru.bellintegrator.practice.user.view.UserViewUpdate;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.Set;

public interface UserService {

    /**
     * Получить список всех пользователей
     *
     * @return {@Set<User>}
     */
    public Set<UserViewUpdate> all ();

    /**
     * Получить список пользователей по фильтрам
     *
     * @param userView
     * @return {@Set<OfficeView>}
     */
    public Set<UserView> loadByFilter(UserView userView  );

    /**
     * Получить пользователя по Id
     *
     * @param id
     * @return {@Set<OfficeViewAll>}
     */
    public UserViewUpdate loadById(Long id);

    /**
     * Изменить данные пользователя
     *
     * @param userView
     */
    public void update(UserViewUpdate userView);

    /**
     * Добавить нового пользователя
     *
     * @param userView
     * @return UserViewAdd
     */
    public void add (UserViewAdd userView);

    /**
     * Удалить пользователя по ID
     * @param id
     * @return
     */
    void delete (Long id);
}

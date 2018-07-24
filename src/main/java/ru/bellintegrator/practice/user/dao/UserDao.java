package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.document.model.Document;
import ru.bellintegrator.practice.user.model.User;

import java.util.Set;

public interface UserDao {

    /**
     * Получить список пользователей по Id офиса
     *
     * @param officeId
     * @return {@Set<User>}
     */
    public Set<User> loadByOfficeId(Long officeId, String firstName, String lastName,
                                    String middleName, String position, String docNumber,
                                    String citizenshipCode);

    /**
     * Получить список пользователей по Id пользователя
     *
     * @param id
     * @return {@Set<User>}
     */
    public User loadById(Long id);

    /**
     * Изменить данные пользователя
     *
     * @param user
     */
    public void update(User user);

    /**
     * Добавить нового пользователя
     *
     * @param user
     */
    public void save (User user);
}

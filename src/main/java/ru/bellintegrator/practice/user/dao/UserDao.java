package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.document.model.Document;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.user.model.User;

import java.util.Set;

public interface UserDao {

    /**
     * Получить все объекты Пользователь
     *
     * @return {@Set<User>}
     */
    public Set<User> all ();

    /**
     * Получить список пользователей по фильтрам
     *
     * @param officeId
     * @return {@Set<User>}
     */
    public Set<User> loadByOfficeId(Long officeId, String firstName, String lastName,
                                    String middleName, String position, String docNumber,
                                    String citizenshipCode);

    /**
     * Найти пользователя по Id
     *
     * @param id
     * @return {@Set<User>}
     */
    public User loadById(Long id);

    /**
     * Добавить нового пользователя
     *
     * @param user
     */
    public void save (User user);
}

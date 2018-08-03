package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.dictionary.model.Country;
import ru.bellintegrator.practice.dictionary.model.DocType;
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
     * @param office
     * @return {@Set<User>}
     */
    public Set<User> loadByFilter(Office office, Country country, DocType docType, String firstName, String lastName,
                                  String middleName, String position);
    /**
     * Получить список пользователей по ссылке на офис
     *
     * @param office
     * @return {@Set<User>}
     */
    public Set<User> loadByOffice(Office office);

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

    /*
     * Удаление пользователя по Id
     */
    public void deleteById(Long id);
}

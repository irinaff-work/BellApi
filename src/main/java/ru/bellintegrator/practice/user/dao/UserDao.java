package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.dictionary.model.Country;
import ru.bellintegrator.practice.dictionary.model.DocType;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.user.model.User;

import java.util.Set;

/**
 * DAO для работы с User
 */
public interface UserDao {

    /**
     * Получить все объекты User
     *
     * @return
     */
    public Set<User> all();

    /**
     * Получить список пользователей по фильтрам
     *
     * @param office
     * @param country
     * @param docType
     * @param firstName
     * @param lastName
     * @return
     */
    public Set<User> loadByFilter(Office office, Country country, DocType docType, String firstName, String lastName,
                                  String middleName, String position);

    /**
     * Получить список пользователей по ссылке на офис
     *
     * @param office
     * @return
     */
    public Set<User> loadByOffice(Office office);

    /**
     * Найти пользователя по Id
     *
     * @param id
     * @return
     */
    public User loadById(Long id);

    /**
     * Сохранить пользователя
     *
     * @param user
     */
    public void save(User user);

    /*
     * Удалить пользователя по Id
     *
     * @param id
     */
    public void deleteById(Long id);
}

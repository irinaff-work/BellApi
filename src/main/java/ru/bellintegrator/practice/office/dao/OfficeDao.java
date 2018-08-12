package ru.bellintegrator.practice.office.dao;

import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.model.Organization;

import java.util.Set;

/**
 * DAO для работы с офисами
 */
public interface OfficeDao {

    /**
     * Получить все объекты Офис
     *
     * @return
     */
    public Set<Office> all();

    /**
     * Получить список офисов по фильтру
     *
     * @param organization
     * @param name
     * @param phone
     * @return
     */
    public Set<Office> loadByOrgId(Organization organization, String name, String phone);

    /**
     * Получить список офисов по Id офиса
     *
     * @param id
     * @return
     */
    public Office loadById(Long id);

    /**
     * Добавить новый офис
     *
     * @param office
     * @return
     */
    public void save(Office office);

    /*
     * Удалить офис по Id
     *
     * @param id
     */
    public void deleteById(Long id);
}

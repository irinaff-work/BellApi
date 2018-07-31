package ru.bellintegrator.practice.office.dao;

import ru.bellintegrator.practice.office.model.Office;

import java.util.Set;

public interface OfficeDao {

    /**
     * Получить все объекты Офис
     *
     * @param
     * @return {@Set<OfficeViewFull>}
     */
    public Set<Office> all ();

    /**
     * Получить список офисов по Id организации
     *
     * @param organizationId
     * @return {@Set<OfficeView>}
     */
    public Set<Office> loadByOrgId (Long organizationId, String name, String phone);

    /**
     * Получить список офисов по Id офиса
     *
     * @param id
     * @return {@Office}
     */
    public Office loadById(Long id);


    /**
     * Добавить новый офис
     *
     * @param office
     * @return OfficeSave
     */
    public void save (Office office);

    /*
    * Удаление офиса по Id
     */
    public void deleteById(Long id);
}

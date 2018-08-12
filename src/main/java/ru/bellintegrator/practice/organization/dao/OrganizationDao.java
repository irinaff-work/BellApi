package ru.bellintegrator.practice.organization.dao;


import ru.bellintegrator.practice.organization.model.Organization;

import java.util.Set;

/**
 * DAO для работы с организациями
 */
public interface OrganizationDao {

    /**
     * Получить все объекты организация
     *
     * @return
     */
    public Set<Organization> all();

    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @return {@Set<OrganizationView>}
     */
    public Set<Organization> loadByNameAndInn(String name, String inn);

    /**
     * Получить Organization по Id
     *
     * @param id
     * @return
     */
    public Organization loadById(Long id);

    /**
     * Сохранить Organization
     *
     * @param organization
     * @return
     */
    public void save(Organization organization);

    /*
     * Удаление организации по Id
     */
    public void deleteById(Long id);
}

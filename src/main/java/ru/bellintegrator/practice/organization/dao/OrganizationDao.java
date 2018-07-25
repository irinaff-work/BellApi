package ru.bellintegrator.practice.organization.dao;


import ru.bellintegrator.practice.organization.model.Organization;

import java.util.Set;

public interface OrganizationDao {

    /**
     * Получить список всех офисов
     *
     * @param
     * @return {@Set<Organization>}
     */
    public Set<Organization> all ();

    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @return {@Set<OrganizationView>}
     */
    public Set<Organization> loadByNameAndInn(String name, String inn);
    /**
     * Получить организацию по Id
     *
     * @param id
     * @return {@Set<OrganizationView>}
     */
    public Organization loadById(Long id);
    /**
     * Изменить данные организации
     *
     * @param organization
     */
    public void update(Organization organization);

    /**
     * Добавить новую организацию в БД
     *
     * @param organization
     * @return OrganizationView
     */
    public void save (Organization organization);
}

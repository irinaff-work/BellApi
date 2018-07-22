package ru.bellintegrator.practice.organization.dao;

import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;

public interface OrganizationDao {
    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @return {@List<OrganizationView>}
     */
    public List<Organization> organizationList(String name, String inn);
    /**
     * Получить организацию по Id
     *
     * @param id
     * @return {@List<OrganizationView>}
     */
    public List<Organization> filteredId(Long id);
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

package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.view.OrganizationSearch;
import ru.bellintegrator.practice.organization.view.OrganizationToSave;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationService {

    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @return {@List<OrganizationView>}
     */
    public List<OrganizationView> organizationList(OrganizationSearch organization);
    /**
     * Получить организацию по Id
     *
     * @param id
     * @return {@List<OrganizationView>}
     */
    public List<OrganizationView> filteredId( Long id);
    /**
     * Изменить данные организации
     *
     * @param organization
     */
    public void update(OrganizationView organization);

    /**
     * Добавить новую организацию в БД
     *
     * @param organization
     * @return OrganizationView
     */
    public OrganizationView createOrganization (OrganizationToSave organization);

}

package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.view.OrganizationViewFull;
import ru.bellintegrator.practice.organization.view.OrganizationView;


import java.util.Set;

public interface OrganizationService {

    /**
     * Получить список всех организаций
     *
     * @param
     * @return {@Set<OrganizationViewFull>}
     */
    public Set<OrganizationViewFull> all ();
    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @return {@Set<OrganizationView>}
     */
    public Set<OrganizationView> loadByNameAndInn(OrganizationView organization);
    /**
     * Получить организацию по Id
     *
     * @param id
     * @return {@OrganizationView}
     */
    public OrganizationViewFull loadById(Long id);
    /**
     * Изменить данные организации
     *
     * @param organization
     */
    public void update(OrganizationViewFull organization);

    /**
     * Добавить новую организацию в БД
     *
     * @param organization
     * @return OrganizationView
     */
    public void add (OrganizationViewFull organization);

    /**
     * Удалить организацию по ID
     * @param id
     * @return
     */
    void delete (Long id);


}
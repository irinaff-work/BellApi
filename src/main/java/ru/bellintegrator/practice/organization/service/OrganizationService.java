package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.view.OrganizationViewAdd;
import ru.bellintegrator.practice.organization.view.OrganizationViewUpdate;
import ru.bellintegrator.practice.organization.view.OrganizationView;


import java.util.Set;

public interface OrganizationService {

    /**
     * Получить список всех организаций
     *
     * @param
     * @return {@Set<OrganizationViewFull>}
     */
    public Set<OrganizationViewUpdate> all ();
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
    public OrganizationViewUpdate loadById(Long id);
    /**
     * Изменить данные организации
     *
     * @param organization
     */
    public void update(OrganizationViewUpdate organization);

    /**
     * Добавить новую организацию в БД
     *
     * @param organization
     * @return OrganizationView
     */
    public void add (OrganizationViewAdd organization);

    /**
     * Удалить организацию по ID
     * @param id
     * @return
     */
    void delete (Long id);


}
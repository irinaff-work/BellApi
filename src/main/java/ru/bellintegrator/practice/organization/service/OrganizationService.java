package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.view.OrganizationViewAdd;
import ru.bellintegrator.practice.organization.view.OrganizationViewUpdate;
import ru.bellintegrator.practice.organization.view.OrganizationView;


import java.util.Set;

/**
 * Сервис
 */
public interface OrganizationService {

    /**
     * Получить все организации
     *
     * @return
     */
    public Set<OrganizationViewUpdate> all ();

    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @param organization
     * @return
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
     * Сохранить организацию
     *
     * @param organization
     * @return
     */
    public void add (OrganizationViewAdd organization);

    /**
     * Удалить организацию по ID
     * @param id
     */
    void delete (Long id);


}
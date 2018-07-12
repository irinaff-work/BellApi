package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationService {

    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @return {@List<OrganizationView>}
     */
    public List<OrganizationView> filteredName(String name, String inn, boolean isActive);
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
     * @param id
     */
    public OrganizationView update (Long id, String name, String fullName, String inn, String kpp,
    String address, String phone, boolean isActive);

    /**
     * Добавить новую организацию в БД
     *
     * @param id
     * @return OrganizationView
     */
    public OrganizationView save (Long id, String name, String fullName, String inn, String kpp,
    String address, String phone, boolean isActive);

}

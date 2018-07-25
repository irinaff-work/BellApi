package ru.bellintegrator.practice.office.service;

import ru.bellintegrator.practice.office.view.*;

import java.util.Set;

public interface OfficeService {

    /**
     * Получить список всех офисов
     *
     * @param
     * @return {@Set<mapOfficeAll>}
     */
    public Set<OfficeViewFull> all ();

    /**
     * Получить список офисов по Id организации
     *
     * @param officeView
     * @return {@Set<OfficeView>}
     */
    public Set<OfficeView> loadByOrgId (OfficeViewFull officeView);

    /**
     * Получить офис по Id
     *
     * @param id
     * @return {@OfficeViewAll}
     */
    public OfficeViewFull loadById(Long id);

    /**
     * Изменить данные офиса
     *
     * @param view
     */
    public void update(OfficeViewFull view);

    /**
     * Добавить новый офис
     *
     * @param view
     * @return OfficeSave
     */
    public void add (OfficeViewFull view);
}

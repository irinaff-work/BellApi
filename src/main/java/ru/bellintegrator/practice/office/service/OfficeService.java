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
    public Set<OfficeViewSave> all ();

    /**
     * Получить список офисов по Id организации
     *
     * @param officeView
     * @return {@Set<OfficeView>}
     */
    public Set<OfficeView> loadByOrgId (OfficeView officeView);

    /**
     * Получить офис по Id
     *
     * @param id
     * @return {@OfficeViewAll}
     */
    public OfficeViewSave loadById(Long id);

    /**
     * Изменить данные офиса
     *
     * @param view
     */
    public void update(OfficeViewUpdate view);

    /**
     * Добавить новый офис
     *
     * @param view
     * @return OfficeSave
     */
    public void add (OfficeViewSave view);

    /**
     * Удалить офис по ID
     * @param id
     * @return
     */
    void delete (Long id);
}

package ru.bellintegrator.practice.office.service;

import ru.bellintegrator.practice.office.view.*;
import ru.bellintegrator.practice.validate.SuccessView;
//import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;

public interface OfficeService {

    /**
     * Получить список офисов по Id организации
     *
     * @param officeView
     * @return {@List<OfficeView>}
     */
    public List<OfficeView> filteredOrgId(OfficeView officeView );

    /**
     * Получить список офисов по Id офиса
     *
     * @param id
     * @return {@List<OfficeViewAll>}
     */
    public List<OfficeViewAll> filteredId(Long id);

    /**
     * Изменить данные офиса
     *
     * @param officeView
     */
    public SuccessView update(OfficeViewAll officeView);

    /**
     * Добавить новый офис
     *
     * @param officeView
     * @return OfficeSave
     */
    public SuccessView save (OfficeViewAll officeView);
}

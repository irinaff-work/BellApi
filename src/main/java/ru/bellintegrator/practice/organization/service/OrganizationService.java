package ru.bellintegrator.practice.organization.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bellintegrator.practice.organization.view.OrganizationViewFull;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.validate.SuccessView;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface OrganizationService {

    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @return {@List<OrganizationView>}
     */
    public List<OrganizationView> organizationList(OrganizationView organization);
    /**
     * Получить организацию по Id
     *
     * @param id
     * @return {@List<OrganizationView>}
     */
    public List<OrganizationViewFull> filteredId(Long id);
    /**
     * Изменить данные организации
     *
     * @param organization
     */
    public SuccessView update(OrganizationViewFull organization);

    /**
     * Добавить новую организацию в БД
     *
     * @param organization
     * @return OrganizationView
     */
    public SuccessView save (OrganizationViewFull organization);

    public void validationRequestBody (OrganizationView organization);


}
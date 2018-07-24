package ru.bellintegrator.practice.organization.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bellintegrator.practice.organization.view.OrganizationViewFull;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.validate.SuccessView;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Set;

public interface OrganizationService {

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
    public void save (OrganizationViewFull organization);




}
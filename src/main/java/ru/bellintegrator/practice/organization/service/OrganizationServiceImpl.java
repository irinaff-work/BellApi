package ru.bellintegrator.practice.organization.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.organization.view.OrganizationViewFull;
import ru.bellintegrator.practice.validate.RequestValidationException;
import ru.bellintegrator.practice.validate.SuccessView;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final OrganizationDao dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao) {
        this.dao = dao;
    }


    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @return {@Set<OrganizationView>}
     */
    @Override
    public Set<OrganizationView> loadByNameAndInn(OrganizationView organization) {

        validationRequestBody (organization);
        Set<Organization> listOrganization = dao.loadByNameAndInn(organization.name, organization.inn);

        return listOrganization.stream()
                .map(mapOrganization())
                .collect(Collectors.toSet());

    }

    private Function<Organization, OrganizationView> mapOrganization() {
        return p -> {
            OrganizationView view = new OrganizationView();
            view.id = p.getId();
            view.name = p.getName();
            view.inn = p.getInn();
            view.isActive = p.isActive();

            log.debug(view.toString());

            return view;
        };
    }

    /**
     * Получить организацию по Id
     *
     * @param id
     * @return {@Set<OrganizationToSave>}
     */
    @Override
    public OrganizationViewFull loadById(Long id) {
        Organization organization = dao.loadById(id);

        OrganizationViewFull view = new OrganizationViewFull();
        view.id = organization.getId();
        view.name = organization.getName();
        view.fullName = organization.getFullName();
        view.inn = organization.getInn();
        view.kpp = organization.getKpp();
        view.address = organization.getAddress();
        view.isActive = organization.isActive();

        return view;
    }

    /**
     * Изменить данные организации
     *
     * @param view
     */
    @Override
    public void update(OrganizationViewFull view) {

        validationRequestBody(view);

        Organization organization = new Organization (view.id, view.name, view.fullName,
                view.inn, view.kpp, view.phone, view.address, true);
        dao.update(organization);

    }


    /**
     * Добавить новую организацию в БД
     *
     * @param view
     * @return OrganizationView
     */
    @Override
    public void save (OrganizationViewFull view) {

        validationRequestBody(view);
        Organization organization = new Organization (view.id, view.name, view.fullName,
                view.inn, view.kpp, view.phone, view.address, true);
        dao.save(organization);
    }

    /*
     * Проверить входящий запрос
     * @param organization
     */
    public void validationRequestBody (OrganizationView organization) {
        Pattern numericPattern = Pattern.compile("[0-9]{12}");
        if (organization.inn.isEmpty() || !numericPattern.matcher(organization.inn).find()) {
            throw new RequestValidationException("Поле ИНН должно содержать 12 цифровых символов");
        }
    }
}
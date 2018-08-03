package ru.bellintegrator.practice.organization.service;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.organization.view.OrganizationViewFull;
import ru.bellintegrator.practice.validate.RequestValidationException;

import javax.persistence.NoResultException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final OrganizationDao dao;
    private final OfficeDao officeDao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, OfficeDao officeDao) {
        this.dao = dao;
        this.officeDao = officeDao;
    }


    /**
     * Получить список всех организаций
     *
     * @param
     * @return {@Set<OrganizationViewFull>}
     */
    @Override
    @Transactional(readOnly = true)
    public Set<OrganizationViewFull> all () {
        Set<Organization> organizations = dao.all();

        return organizations.stream()
                .map(mapOrganizationAll())
                .collect(Collectors.toSet());
    };
    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @return {@Set<OrganizationView>}
     */
    @Override
    @Transactional(readOnly = true)
    public Set<OrganizationView> loadByNameAndInn(OrganizationView organization) {

        validationOrgAll (organization);
        Set<Organization> organizations = dao.loadByNameAndInn(organization.name, organization.inn);

        return organizations.stream()
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

    private Function<Organization, OrganizationViewFull> mapOrganizationAll() {
        return p -> {
            OrganizationViewFull view = new OrganizationViewFull();
            view.id = p.getId();
            view.name = p.getName();
            view.fullName = p.getFullName();
            view.inn = p.getInn();
            view.kpp = p.getKpp();
            view.address = p.getAddress();
            view.phone = p.getPhone();
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
    @Transactional(readOnly = true)
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
    @Transactional
    public void update(OrganizationViewFull view) {

        validationOrgAll(view);
        validationOrgUdate(view);

        Organization organization = dao.loadById(view.id);
        organization.setName(view.name);
        organization.setKpp(view.kpp);
        organization.setInn(view.inn);
        organization.setAddress(view.address);
        if (Strings.isNullOrEmpty(view.phone)) {
            organization.setPhone(view.phone);
        }
        organization.setActive(true);
        dao.save(organization);
    }


    /**
     * Добавить новую организацию в БД
     *
     * @param view
     * @return OrganizationView
     */
    @Override
    @Transactional
    public void add (OrganizationViewFull view) {

        validationOrgAll(view);
        Organization organization = new Organization (view.name, view.fullName,
                view.inn, view.kpp, view.phone, view.address, true);
        dao.save(organization);
    }

    /*
     * Проверить входящий запрос
     * @param organization
     */
    public void validationOrgAll (OrganizationView organization) {
        Pattern numericPattern = Pattern.compile("[0-9]{12}");
        if (Strings.isNullOrEmpty(organization.inn) || !numericPattern.matcher(organization.inn).find()) {
            throw new RequestValidationException("Поле ИНН должно содержать 12 цифровых символов");
        }
    }

    public void validationOrgUdate(OrganizationView view) {
        //проверим, есть ли организация
        try {
            Organization organization = dao.loadById(view.id);
        } catch (NoResultException e) {
            throw new RequestValidationException("Нет организации с таким идентификатором");
        }
    }

    /**
     * Удалить офис по ID
     * @param id
     * @return
     */
    @Override
    @Transactional
    public void delete (Long id) {
        Organization organization = dao.loadById(id);
        Set<Office> offices = officeDao.loadByOrgId(organization, null, null);
        for (Office office: offices) {
            office.setOrganization(null);
            officeDao.save(office);
        }
        dao.deleteById(id);
    }
}
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
import ru.bellintegrator.practice.organization.view.OrganizationViewAdd;
import ru.bellintegrator.practice.organization.view.OrganizationViewUpdate;
import ru.bellintegrator.practice.validate.RequestValidationException;

import javax.persistence.NoResultException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
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
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Set<OrganizationViewUpdate> all() {
        Set<Organization> organizations = dao.all();

        return organizations.stream()
                .map(mapOrganizationAll())
                .collect(Collectors.toSet());
    }

    ;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Set<OrganizationView> loadByNameAndInn(OrganizationView view) {

        //validationOrgAll (view);
        Set<Organization> organizations = dao.loadByNameAndInn(view.getName(), view.getInn());

        return organizations.stream()
                .map(mapOrganization())
                .collect(Collectors.toSet());

    }

    private Function<Organization, OrganizationView> mapOrganization() {
        return p -> {
            OrganizationView view = new OrganizationView();
            view.setId(p.getId().toString());
            view.setName(p.getName());
            view.setInn(p.getInn());
            view.setActive(Boolean.toString(p.isActive()));

            log.debug(view.toString());

            return view;
        };
    }

    private Function<Organization, OrganizationViewUpdate> mapOrganizationAll() {
        return p -> {
            OrganizationViewUpdate view = new OrganizationViewUpdate();
            view.setId(p.getId().toString());
            view.setName(p.getName());
            view.setFullName(p.getFullName());
            view.setInn(p.getInn());
            view.setKpp(p.getKpp());
            view.setAddress(p.getAddress());
            view.setPhone(p.getPhone());
            view.setActive(Boolean.toString(p.isActive()));

            log.debug(view.toString());

            return view;
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationViewUpdate loadById(Long id) {
        OrganizationViewUpdate view = new OrganizationViewUpdate();

        try {
            Organization organization = dao.loadById(id);

            view.setId(organization.getId().toString());
            view.setName(organization.getName());
            view.setFullName(organization.getFullName());
            view.setInn(organization.getInn());
            view.setKpp(organization.getKpp());
            view.setAddress(organization.getAddress());
            view.setActive(Boolean.toString(organization.isActive()));
        } catch (Exception e) {
            throw new RequestValidationException("Нет организации с таким идентификатором");
        }
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationViewUpdate view) {

        validationOrgAll(view);
        validationOrgUdate(view);

        Organization organization = dao.loadById(Long.valueOf(view.getId()));
        organization.setName(view.getName());
        organization.setKpp(view.getKpp());
        organization.setInn(view.getInn());
        organization.setAddress(view.getAddress());
        if (Strings.isNullOrEmpty(view.getPhone())) {
            organization.setPhone(view.getPhone());
        }
        organization.setActive(true);
        dao.save(organization);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OrganizationViewAdd view) {

        //validationOrgAll(view);
        Organization organization = new Organization(view.getName(), view.getFullName(),
                view.getInn(), view.getKpp(), view.getPhone(), view.getAddress(), true);
        dao.save(organization);
    }

    /**
     * {@inheritDoc}
     */
    public void validationOrgAll(OrganizationViewUpdate view) {
        Pattern numericPattern = Pattern.compile("[0-9]{12}");
        if (Strings.isNullOrEmpty(view.getInn()) || !numericPattern.matcher(view.getInn()).find()) {
            throw new RequestValidationException("Поле ИНН должно содержать 12 цифровых символов");
        }
    }

    public void validationOrgUdate(OrganizationViewUpdate view) {
        //проверим, есть ли организация
        try {
            Organization organization = dao.loadById(Long.valueOf(view.getId()));
        } catch (NoResultException e) {
            throw new RequestValidationException("Нет организации с таким идентификатором");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(Long id) {
        Organization organization = dao.loadById(id);
        Set<Office> offices = officeDao.loadByOrgId(organization, null, null);
        for (Office office : offices) {
            office.setOrganization(null);
            officeDao.save(office);
        }
        dao.deleteById(id);
    }
}
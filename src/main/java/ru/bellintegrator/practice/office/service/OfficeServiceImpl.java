package ru.bellintegrator.practice.office.service;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.*;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService{

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final OfficeDao dao;
    private final UserDao userDao;
    private final OrganizationDao organizationDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao, OrganizationDao organizationDao, UserDao userDao) {
        this.dao = dao;
        this.organizationDao = organizationDao;
        this.userDao = userDao;
    }

    /**
     * Получить список офисов
     *
     * @param
     * @return {@Set<OfficeView>}
     */
    @Override
    @Transactional(readOnly = true)
    public Set<OfficeViewSave> all () {
        Set<Office> offices = dao.all();

        return offices.stream()
                .map(mapOfficeAll())
                .collect(Collectors.toSet());
    };

    /**
     * Получить список офисов по Id организации
     *
     * @param view
     * @return {@Set<OfficeView>}
     */
    @Override
    @Transactional(readOnly = true)
    public Set<OfficeView> loadByOrgId (OfficeView view) {
        Organization organization = organizationDao.loadById(view.getOrgId());

        Set<Office> offices = dao.loadByOrgId(organization, view.getName(), view.getPhone());

            return offices.stream()
                    .map(mapOffice())
                    .collect(Collectors.toSet());
        }

    private Function<Office, OfficeView> mapOffice() {
        return p -> {
            OfficeView view = new OfficeView();
            view.setId(p.getId());
            view.setOrgId(p.getOrgId());
            view.setName(p.getName());
            view.setActive(p.isActive());
            return view;
        };
    }
    /**
     * Получить офис по Id
     *
     * @param id
     * @return {@List<OfficeViewAll>}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeViewSave loadById(Long id) {
        Office office = dao.loadById(id);

        OfficeViewSave view = new OfficeViewSave();
        view.setId(office.getId());
        view.setName(office.getName());
        view.setPhone(office.getPhone());
        view.setAddress(office.getAddress());
        view.setActive(office.isActive());
        return view;
    };

    private Function<Office, OfficeViewSave> mapOfficeAll() {
        return p -> {
            OfficeViewSave view = new OfficeViewSave();
            view.setId(p.getId());
            view.setName(p.getName());
            view.setPhone(p.getPhone());
            view.setAddress(p.getAddress());
            view.setActive(p.isActive());
            return view;
        };
    }
    /**
     * Изменить данные офиса
     *
     * @param view
     */
    @Override
    @Transactional
    public void update(OfficeViewUpdate view) {
        Office office = dao.loadById(view.getId());

        office.setName(view.getName());
        office.setAddress(view.getAddress());

        if (!Strings.isNullOrEmpty(view.getPhone())) {
            office.setPhone(view.getPhone());
        }

        office.setActive(true);
        dao.save(office);
    }

    /**
     * Добавить новый офис
     *
     * @param view
     * @return OfficeSave
     */
    @Override
    @Transactional
    public void add (OfficeViewSave view) {
        Office office = new Office();
        Organization organization = organizationDao.loadById(view.getId());
        office.setOrganization(organization);

        office.setName(view.getName());

        if (!Strings.isNullOrEmpty(view.getAddress())) {
            office.setAddress(view.getAddress());
        }

        if (!Strings.isNullOrEmpty(view.getPhone())) {
            office.setPhone(view.getPhone());
        }

        office.setActive(true);

        dao.save(office);
    }

    /**
     * Удалить офис по ID
     * @param id
     * @return
     */
    @Override
    @Transactional
    public void delete (Long id) {
        Office office = dao.loadById(id);
        Set<User> users = userDao.loadByOffice(office);
        for (User user: users) {
            user.setOffice(null);
            userDao.save(user);
        }
        dao.deleteById(id);
    }
}

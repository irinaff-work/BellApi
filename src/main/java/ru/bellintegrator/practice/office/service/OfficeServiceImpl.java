package ru.bellintegrator.practice.office.service;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.*;
import ru.bellintegrator.practice.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService{

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final OfficeDao dao;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao) {
        this.dao = dao;
    }

    /**
     * Получить список офисов по Id организации
     *
     * @param officeView
     * @return {@Set<OfficeView>}
     */
    @Override
    public Set<OfficeView> loadByOrgId (OfficeViewFull officeView) {

        Set<Office> offices = dao.loadByOrgId(officeView.orgId, officeView.name, officeView.phone);

            return offices.stream()
                    .map(mapOffice())
                    .collect(Collectors.toSet());
        }

    private Function<Office, OfficeView> mapOffice() {
        return p -> {
            OfficeView view = new OfficeView();
            view.id = p.getId();
            view.orgId = p.getOrgId();
            view.name = p.getName();
            view.isActive = p.isActive();
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
    public OfficeViewFull loadById(Long id) {
        Office office = dao.loadById(id);

        OfficeViewFull view = new OfficeViewFull();
        view.id = office.getId();
        view.orgId = office.getOrgId();
        view.name = office.getName();
        view.phone = office.getPhone();
        view.address = office.getAddress();
        view.isActive = office.isActive();
        return view;
    };

    private Function<Office, OfficeViewFull> mapOfficeAll() {
        return p -> {
            OfficeViewFull view = new OfficeViewFull();
            view.id = p.getId();
            view.orgId = p.getOrgId();
            view.name = p.getName();
            view.phone = p.getPhone();
            view.address = p.getAddress();
            view.isActive = p.isActive();
            return view;
        };
    }
    /**
     * Изменить данные офиса
     *
     * @param view
     */
    @Override
    public void update(OfficeViewFull view) {
        Office office = dao.loadById(view.id);
        office.setName(view.name);
        office.setAddress(view.address);

        if (!Strings.isNullOrEmpty(view.phone)) {
            office.setPhone(view.phone);
        }

        office.setActive(true);
    }

    /**
     * Добавить новый офис
     *
     * @param view
     * @return OfficeSave
     */
    @Override
    public void add (OfficeViewFull view) {
        Office office = new Office();

        office.setName(view.name);

        if (!Strings.isNullOrEmpty(view.address)) {
            office.setAddress(view.address);
        }

        if (!Strings.isNullOrEmpty(view.phone)) {
            office.setPhone(view.phone);
        }

        office.setActive(true);

        dao.add(office);
    };
}

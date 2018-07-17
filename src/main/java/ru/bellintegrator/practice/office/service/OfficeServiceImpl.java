package ru.bellintegrator.practice.office.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.*;
import ru.bellintegrator.practice.validate.SuccessView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService{

    private List<Office> listOffices = new ArrayList(Arrays.asList(
            new Office(1L, 3L,"Офис Капитал", "222-22-22", "г. Уфа, ул. Запредельная, д.2",true),
            new Office(2L, 4L,"Салют", "333-33-33", "г. Уфа, ул. Запредельная, д.2", true)
    ));

    /**
     * Получить список офисов по Id организации
     *
     * @param office
     * @return {@List<OfficeView>}
     */
    @Override
    public List<OfficeView> filteredOrgId(OfficeView office) {

            List<Office> filteredOffices = new ArrayList<Office>();

            for (Office item: listOffices) {
                if ((item.getOrgId().equals(office.orgId))) {
                    item.setActive(true);
                    filteredOffices.add(item);
                }
            }
            return filteredOffices.stream()
                    .map(mapOffice())
                    .collect(Collectors.toList());

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
     * Получить список офисов по Id офиса
     *
     * @param id
     * @return {@List<OfficeViewAll>}
     */
    @Override
    public List<OfficeViewFull> filteredId(Long id) {
        List<Office> filteredOffices = new ArrayList<Office>();

        for (Office item: listOffices) {
            if (item.getId().equals(id)) {
                item.setActive(true);
                filteredOffices.add(item);
            }
        }
        return filteredOffices.stream()
                .map(mapOfficeAll())
                .collect(Collectors.toList());
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
     * @param office
     */
    @Override
    public SuccessView update(OfficeViewFull office) {
        for (Office item: listOffices) {
            if (item.getId().equals(office.id)) {

                if (!office.name.isEmpty()) {
                    item.setName(office.name);
                }

                if (!office.phone.isEmpty()) {
                    item.setPhone(office.phone);
                }

                if (!office.address.isEmpty()) {
                    item.setAddress(office.address);
                }
                item.setActive(office.isActive);
            }
        }

        SuccessView successView = new SuccessView();
        successView.result = "success";

        return successView;
    }

    /**
     * Добавить новый офис
     *
     * @param office
     * @return OfficeSave
     */
    @Override
    public SuccessView save (OfficeViewFull office) {

        Office officeSave = new Office(office.id, office.orgId, office.name, office.phone,
                office.address, office.isActive);
        listOffices.add(officeSave);

        SuccessView successView = new SuccessView();
        successView.result = "success";

        return successView;
    };
}

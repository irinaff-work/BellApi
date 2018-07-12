package ru.bellintegrator.practice.organization.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.person.model.Person;
import ru.bellintegrator.practice.person.view.PersonView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private List<Organization> listOrganization = Arrays.asList(
            new Organization(1L, "Рога и Копыта", "ООО Рога и Копыта", "123456789012", "123456789","234-34-56", "г. Уфа, ул. Запредельная, д.2",true),
            new Organization(2L, "Салют", "ООО Салют", "123456789012", "123456789" ,"234-34-56", "г. Уфа, ул. Запредельная, д.2", true)
    );


    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @return {@List<OrganizationView>}
     */
    @Override
    public List<OrganizationView> filteredName(String name, String inn, boolean isActive) {
        List<Organization> filteredOrganization = new ArrayList<Organization>();

        for (Organization item: listOrganization) {
            if ((name.isEmpty() || item.getName().equals(name))
                    && (inn.isEmpty() || item.getInn().equals(inn))
                    && (item.isActive() == isActive)
                    ) {
                item.setActive(true);
                filteredOrganization.add(item);
            }
        }
        return filteredOrganization.stream()
                .map(mapOrganization())
                .collect(Collectors.toList());

    }

    private Function<Organization, OrganizationView> mapOrganization() {
        return p -> {
            OrganizationView view = new OrganizationView();
            view.id = p.getId();
            view.name = p.getName();
            view.fullName = p.getFullName();
            view.inn = p.getInn();
            view.kpp = p.getKpp();
            view.address = p.getAddress();
            view.isActive = p.isActive();

            log.debug(view.toString());

            return view;
        };
    }
    /**
     * Получить организацию по Id
     *
     * @param id
     * @return {@List<OrganizationView>}
     */
    @Override
    public List<OrganizationView> filteredId( Long id) {
        List<Organization> filteredOrganization = new ArrayList<Organization>();

        for (Organization item: listOrganization) {
            if (item.getId().equals(id)) {
                filteredOrganization.add(item);
            }
        }
        return filteredOrganization.stream()
                .map(mapOrganization())
                .collect(Collectors.toList());
    }
    /**
     * Изменить данные организации
     *
     * @param id
     */
    @Override
//    public String update(OrganizationView view) {
//        String isSuccess = "";
//
//        for (Organization item: listOrganization) {
//            if (item.getId().equals(view.id)) {
//                item.setName(view.name);
//                item.setFullName(view.fullName);
//                item.setInn(view.inn);
//                item.setKpp(view.kpp);
//                item.setAddress(view.address);
//                if (!view.phone.isEmpty()) {
//                    item.setPhone(view.phone);
//                }
//                item.setActive(view.isActive);
//                isSuccess = "success";
//            }
//        }
//        return isSuccess;
//    }

    public OrganizationView update (Long id, String name, String fullName, String inn, String kpp,
                    String address, String phone, boolean isActive) {
        OrganizationView view = new OrganizationView();

        for (Organization item: listOrganization) {
            if (item.getId().equals(id)) {
                item.setName(name);
                item.setFullName(fullName);
                item.setInn(inn);
                item.setKpp(kpp);
                item.setAddress(address);
                if (!phone.isEmpty()) {
                    item.setPhone(phone);
                }
                item.setActive(true);
                view.id = item.getId();
                view.name = item.getName();
                view.fullName = item.getFullName();
                view.inn = item.getInn();
                view.kpp = item.getKpp();
                view.address = item.getAddress();
                view.isActive = item.isActive();
            }
        }
        return view;
    }

    /**
     * Добавить новую организацию в БД
     *
     * @param id
     * @return OrganizationView
     */
    @Override
//    public String save(OrganizationView view) {
//        String isSuccess = "success";
//        listOrganization.add(new Organization(view.id, view.name, view.fullName,
//                view.inn, view.kpp, view.phone, view.address, view.isActive));
//        return isSuccess;
//    }

    public OrganizationView save(Long id, String name, String fullName, String inn, String kpp,
                       String address, String phone, boolean isActive) {

        OrganizationView view = new OrganizationView();
        Organization org = new Organization(id, name, fullName,
                inn, kpp, phone, address, isActive);

        listOrganization.add(org);

        view.id = org.getId();
        view.name = org.getName();
        view.fullName = org.getFullName();
        view.inn = org.getInn();
        view.kpp = org.getKpp();
        view.address = org.getAddress();
        view.isActive = org.isActive();

        return view;
    }
}

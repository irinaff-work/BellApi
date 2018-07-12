package ru.bellintegrator.practice.organization.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationSearch;
import ru.bellintegrator.practice.organization.view.OrganizationToSave;
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
    public List<OrganizationView> organizationList(OrganizationSearch organization) {
        List<Organization> filteredOrganization = new ArrayList<Organization>();

        for (Organization item: listOrganization) {
            if ((organization.name.isEmpty() || item.getName().equals(organization.name))
                    && (organization.inn.isEmpty() || item.getInn().equals(organization.inn))
                    && (item.isActive() == organization.isActive)
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
     * @param organization
     */
    @Override
    public void update(OrganizationView organization) {
        for (Organization item: listOrganization) {
            if (item.getId().equals(organization.id)) {
                item.setName(organization.name);
                item.setFullName(organization.fullName);
                item.setInn(organization.inn);
                item.setKpp(organization.kpp);
                item.setAddress(organization.address);
                if (!organization.phone.isEmpty()) {
                    item.setPhone(organization.phone);
                }
                item.setActive(organization.isActive);
            }
        }
    }


    /**
     * Добавить новую организацию в БД
     *
     * @param organization
     * @return OrganizationView
     */
    @Override
    public OrganizationView createOrganization (OrganizationToSave organization) {

        Organization orgSave = new Organization(organization.id, organization.name, organization.fullName,
                organization.inn, organization.kpp, organization.phone, organization.address, organization.isActive);

        //listOrganization.add(orgSave);

        OrganizationView organizationView = new OrganizationView();

        organizationView.id = orgSave.getId();
        organizationView.name = orgSave.getName();
        organizationView.fullName = orgSave.getFullName();
        organizationView.inn = orgSave.getInn();
        organizationView.kpp = orgSave.getKpp();
        organizationView.address = orgSave.getAddress();
        organizationView.isActive = orgSave.isActive();

        return organizationView;
    }
}

package ru.bellintegrator.practice.organization.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationToSave;
import ru.bellintegrator.practice.organization.view.OrganizationView;

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
    public List<OrganizationView> organizationList(OrganizationView organization) {
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
     * @return {@List<OrganizationToSave>}
     */
    @Override
    public List<OrganizationToSave> filteredId( Long id) {
        List<Organization> filteredOrganization = new ArrayList<Organization>();

        for (Organization item: listOrganization) {
            if (item.getId().equals(id)) {
                filteredOrganization.add(item);
            }
        }
        return filteredOrganization.stream()
                .map(mapOrganizationSave())
                .collect(Collectors.toList());
    }

    private Function<Organization, OrganizationToSave> mapOrganizationSave() {
        return p -> {
            OrganizationToSave view = new OrganizationToSave();
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
     * Изменить данные организации
     *
     * @param organization
     */
    @Override
    public void update(OrganizationToSave organization) {
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
    public void createOrganization (OrganizationToSave organization) {

        Organization orgSave = new Organization(organization.id, organization.name, organization.fullName,
                organization.inn, organization.kpp, organization.phone, organization.address, organization.isActive);

        //listOrganization.add(orgSave);

        OrganizationToSave organizationToSave = new OrganizationToSave();

        organizationToSave.id = orgSave.getId();
        organizationToSave.name = orgSave.getName();
        organizationToSave.fullName = orgSave.getFullName();
        organizationToSave.inn = orgSave.getInn();
        organizationToSave.kpp = orgSave.getKpp();
        organizationToSave.address = orgSave.getAddress();
        organizationToSave.isActive = orgSave.isActive();
    }
}

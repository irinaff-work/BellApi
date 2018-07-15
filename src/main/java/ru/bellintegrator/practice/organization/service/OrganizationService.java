package ru.bellintegrator.practice.organization.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bellintegrator.practice.organization.view.OrganizationToSave;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface OrganizationService {

    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @return {@List<OrganizationView>}
     */
    public List<OrganizationView> organizationList(OrganizationView organization) throws ValidationException;
    /**
     * Получить организацию по Id
     *
     * @param id
     * @return {@List<OrganizationView>}
     */
    public List<OrganizationToSave> filteredId( Long id);
    /**
     * Изменить данные организации
     *
     * @param organization
     */
    public void update(OrganizationToSave organization);

    /**
     * Добавить новую организацию в БД
     *
     * @param organization
     * @return OrganizationView
     */
    public void createOrganization (OrganizationToSave organization);

    public void validationRequestBody (OrganizationView organization) throws OrgValidationException;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Неверный формат входных данных" )
    class OrgValidationException extends ValidationException {

        public OrgValidationException(String message) {
            super(message);
        }
    }
}

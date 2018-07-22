package ru.bellintegrator.practice.organization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao{

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl (EntityManager em) {
        this.em = em;
    }
    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @return {@List<Organization>}
     */
    @Override
    public List<Organization> organizationList(String name, String inn) {
        TypedQuery<Organization> query = em.createQuery("SELECT p FROM Organization p", Organization.class);
        return query.getResultList();
    };
    /**
     * Получить организацию по Id
     *
     * @param id
     * @return {@List<Organization>}
     */
    @Override
    public List<Organization> filteredId(Long id) {

    };
    /**
     * Изменить данные организации
     *
     * @param organization
     */
    @Override
    public void update(Organization organization) {

    };

    /**
     * Добавить новую организацию в БД
     *
     * @param organization
     * @return Organization
     */
    @Override
    public void save (Organization organization) {

    };
}

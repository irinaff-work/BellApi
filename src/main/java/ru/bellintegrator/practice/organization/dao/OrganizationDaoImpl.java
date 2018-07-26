package ru.bellintegrator.practice.organization.dao;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class OrganizationDaoImpl implements OrganizationDao{

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl (EntityManager em) {
        this.em = em;
    }


    /**
     * Получить список всех офисов
     *
     * @param
     * @return {@Set<Organization>}
     */
    @Override
    public Set<Organization> all () {
        TypedQuery<Organization> query = em.createQuery("SELECT p FROM Organization p", Organization.class);
        return query.getResultList().stream().collect(Collectors.toSet());
    };

    /**
     * Получить список организаций по наименованию и ИНН
     *
     * @return {@List<Organization>}
     */
    @Override
    public Set<Organization> loadByNameAndInn(String name, String inn) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);

        criteriaQuery.where(organizationRoot.get("name").in(name));
        criteriaQuery.where(organizationRoot.get("inn").in(inn));
        criteriaQuery.orderBy(criteriaBuilder.asc(organizationRoot.get("name")));

        TypedQuery<Organization> query = em.createQuery(criteriaQuery);

        return query.getResultList().stream().collect(Collectors.toSet());
    };
    /**
     * Получить организацию по Id
     *
     * @param id
     * @return {@List<Organization>}
     */
    @Override
    public Organization loadById(Long id) {
        return em.find(Organization.class, id);
    };
    /**
     * Изменить данные организации
     *
     * @param organization
     */
    @Override
    public void update(Organization organization) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaUpdate<Organization> criteriaUpdate = criteriaBuilder.
                createCriteriaUpdate(Organization.class);
        Root<Organization> organizationRoot = criteriaUpdate.from(Organization.class);
        criteriaUpdate.set("name", organization.getName());
        criteriaUpdate.set("inn", organization.getInn());
        criteriaUpdate.set("fullName", organization.getFullName());
        criteriaUpdate.set("kpp", organization.getKpp());
        criteriaUpdate.set("address", organization.getAddress());
        if (!Strings.isNullOrEmpty(organization.getPhone())) {
            criteriaUpdate.set("phone", organization.getPhone());
        }
        criteriaUpdate.set("is_active", "true");
        criteriaUpdate.where(criteriaBuilder.equal(organizationRoot.get("id"),
                organization.getId()));
        this.em.createQuery(criteriaUpdate).executeUpdate();
    };

    /**
     * Добавить новую организацию в БД
     *
     * @param organization
     * @return Organization
     */
    @Override
    public void save (Organization organization) {
        em.persist(organization);
    };
}

package ru.bellintegrator.practice.organization.dao;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao{

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl (EntityManager em) {
        this.em = em;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Organization> all () {
        TypedQuery<Organization> query = em.createQuery("SELECT p FROM Organization p", Organization.class);
        return query.getResultList().stream().collect(Collectors.toSet());
    };

    /**
     * {@inheritDoc}
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(Long id) {
        return em.find(Organization.class, id);
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public void save (Organization organization) {
        em.persist(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        Organization organization = em.find(Organization.class, id);
        em.remove(organization);
    }
}

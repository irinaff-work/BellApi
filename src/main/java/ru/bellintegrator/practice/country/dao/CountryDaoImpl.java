package ru.bellintegrator.practice.country.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.country.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class CountryDaoImpl implements CountryDao {

    private final EntityManager em;

    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * получить ссылку на страну  по citizenshipCode
     *
     * @param citizenshipCode
     * @return {@Country}
     */
    @Override
    public Country findByCode(String citizenshipCode) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> сountryRoot = criteriaQuery.from(Country.class);

        criteriaQuery.where(сountryRoot.get("citizenship_сode").in(citizenshipCode));
        TypedQuery<Country> query = em.createQuery(criteriaQuery);

        return query.getSingleResult();
    }
}

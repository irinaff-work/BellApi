package ru.bellintegrator.practice.dictionary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionary.model.Country;

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
     * @param code
     * @return {@Country}
     */
    @Override
    public Country findByCode(String code) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> сountryRoot = criteriaQuery.from(Country.class);

        criteriaQuery.where(сountryRoot.get("сode").in(code));
        TypedQuery<Country> query = em.createQuery(criteriaQuery);

        return query.getSingleResult();
    }
}

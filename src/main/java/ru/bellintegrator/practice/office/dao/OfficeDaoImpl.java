package ru.bellintegrator.practice.office.dao;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.office.model.Office;
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
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * Получить все объекты Офис
     *
     * @param
     * @return {@Set<OfficeView>}
     */
    @Override
    public Set<Office> all () {
        TypedQuery<Office> query = em.createQuery("SELECT p FROM Office p", Office.class);
        return query.getResultList().stream().collect(Collectors.toSet());
    };

    /**
     * Получить список офисов по Id организации
     *
     * @param organization
     * @return {@Set<OfficeView>}
     */
    @Override
    public Set<Office> loadByOrgId (Organization organization, String name, String phone) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);

        criteriaQuery.where(officeRoot.get("organization").in(organization));
        if (!Strings.isNullOrEmpty(name)) {
            criteriaQuery.where(officeRoot.get("name").in(name));
        }

        if (!Strings.isNullOrEmpty(phone)) {
            criteriaQuery.where(officeRoot.get("phone").in(phone));
        }

        criteriaQuery.where(officeRoot.get("isActive").in(true));

        criteriaQuery.orderBy(criteriaBuilder.asc(officeRoot.get("name")));

        TypedQuery<Office> query = em.createQuery(criteriaQuery);

        return query.getResultList().stream().collect(Collectors.toSet());


    };

    /**
     * Получить список офисов по Id офиса
     *
     * @param id
     * @return {@Office>}
     */
    @Override
    public Office loadById(Long id) {
        return em.find(Office.class, id);
    };


    /**
     * Добавить новый офис
     *
     * @param office
     * @return OfficeSave
     */
    @Override
    public void save (Office office) {
        em.persist(office);
    }

    /*
     * Удаление офиса по Id
     *
     * @param id
     * @return
     */
    @Override
    public void deleteById(Long id) {
        Office office = em.find(Office.class, id);
        em.remove(office);
    }
}

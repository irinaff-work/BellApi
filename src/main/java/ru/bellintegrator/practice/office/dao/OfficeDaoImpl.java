package ru.bellintegrator.practice.office.dao;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.office.model.Office;

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
     * Получить список офисов по Id организации
     *
     * @param organizationId
     * @return {@Set<OfficeView>}
     */
    @Override
    public Set<Office> loadByOrgId (Long organizationId, String name, String phone) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);

        criteriaQuery.where(officeRoot.get("org_id").in(organizationId));
        if (!Strings.isNullOrEmpty(name)) {
            criteriaQuery.where(officeRoot.get("name").in(name));
        }

        if (!Strings.isNullOrEmpty(phone)) {
            criteriaQuery.where(officeRoot.get("phone").in(phone));
        }

        criteriaQuery.where(officeRoot.get("is_active").in(true));

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
    public Office loadById(Long id) {
        return em.find(Office.class, id);
    };

    /**
     * Изменить данные офиса
     *
     * @param office
     */
    public void update(Office office) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaUpdate<Office> criteriaUpdate = criteriaBuilder.
                createCriteriaUpdate(Office.class);

        Root<Office> officeRoot = criteriaUpdate.from(Office.class);
        criteriaUpdate.set("name", office.getName());

        if (!Strings.isNullOrEmpty(office.getAddress())) {
            criteriaUpdate.set("address", office.getAddress());
        }

        if (!Strings.isNullOrEmpty(office.getPhone())) {
            criteriaUpdate.set("phone", office.getPhone());
        }
        criteriaUpdate.set("is_active", "true");

        criteriaUpdate.where(criteriaBuilder.equal(officeRoot.get("id"),
                office.getId()));
        this.em.createQuery(criteriaUpdate).executeUpdate();
    };

    /**
     * Добавить новый офис
     *
     * @param office
     * @return OfficeSave
     */
    public void add (Office office) {
        em.persist(office);
    };
}

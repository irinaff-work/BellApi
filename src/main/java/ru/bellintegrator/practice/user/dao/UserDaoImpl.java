package ru.bellintegrator.practice.user.dao;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * Получить список пользователей по Id офиса
     *
     * @param officeId
     * @return {@Set<User>}
     */
    @Override
    public Set<User> loadByOfficeId (Long officeId, String firstName, String lastName,
                                    String middleName, String position, String docNumber,
                                    String citizenshipCode) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        criteriaQuery.where(userRoot.get("officeId").in(officeId));
        if (!Strings.isNullOrEmpty(firstName)) {
            criteriaQuery.where(userRoot.get("firstName").in(firstName));
        }

        if (!Strings.isNullOrEmpty(lastName)) {
            criteriaQuery.where(userRoot.get("lastName").in(lastName));
        }

        if (!Strings.isNullOrEmpty(middleName)) {
            criteriaQuery.where(userRoot.get("middleName").in(middleName));
        }

        if (!Strings.isNullOrEmpty(position)) {
            criteriaQuery.where(userRoot.get("position").in(position));
        }

        if (!Strings.isNullOrEmpty(docNumber)) {
            criteriaQuery.where(userRoot.get("docNumber").in(docNumber));
        }

        if (!Strings.isNullOrEmpty(citizenshipCode)) {
            criteriaQuery.where(userRoot.get("citizenshipCode").in(citizenshipCode));
        }
        criteriaQuery.orderBy(criteriaBuilder.asc(userRoot.get("firstName")));

        TypedQuery<User> query = em.createQuery(criteriaQuery);

        return query.getResultList().stream().collect(Collectors.toSet());

    };

    /**
     * Получить список пользователей по Id пользователя
     *
     * @param id
     * @return {@Set<User>}
     */
    @Override
    public User loadById(Long id) {
        return em.find(User.class, id);
    };

    /**
     * Изменить данные пользователя
     *
     * @param user
     */
    @Override
    public void update(User user) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.
                createCriteriaUpdate(User.class);

        Root<User> userRoot = criteriaUpdate.from(User.class);
        criteriaUpdate.set("first_name", user.getFirstName());

        if (!Strings.isNullOrEmpty(user.getLastName())) {
            criteriaUpdate.set("last_name", user.getLastName());
        }

        if (!Strings.isNullOrEmpty(user.getMiddleName())) {
            criteriaUpdate.set("middle_name", user.getMiddleName());
        }

        criteriaUpdate.set("position", user.getPosition());

        if ( user.getDocument().getId() != 0) {
            criteriaUpdate.set("doc_id", user.getDocument().getId());
        }

        if (user.getCountry().getId() != 0) {
            criteriaUpdate.set("country_id", user.getCountry().getId());
        }

        if (!Strings.isNullOrEmpty(user.getPhone())) {
            criteriaUpdate.set("phone", user.getPhone());
        }
        criteriaUpdate.set("is_active", "true");

        criteriaUpdate.where(criteriaBuilder.equal(userRoot.get("id"),
                user.getId()));
        this.em.createQuery(criteriaUpdate).executeUpdate();
    };

    /**
     * Добавить нового пользователя
     *
     * @param user
     */
    @Override
    public void save (User user) {
            em.persist(user);
    };

}
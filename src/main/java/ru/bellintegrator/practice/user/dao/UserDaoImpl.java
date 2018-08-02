package ru.bellintegrator.practice.user.dao;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionary.model.Country;
import ru.bellintegrator.practice.dictionary.model.DocType;
import ru.bellintegrator.practice.document.model.Document;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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
     * Получить все объекты Пользователь
     *
     * @return {@Set<User>}
     */
    public Set<User> all () {
        TypedQuery<User> query = em.createQuery("SELECT p FROM User p", User.class);
        return query.getResultList().stream().collect(Collectors.toSet());
    };

    /**
     * Получить список пользователей по фильтрам
     *
     * @param office
     * @return {@Set<User>}
     */
    @Override
    public Set<User> loadByFilter (Office office, Country country, DocType docType, String firstName, String lastName,
                                   String middleName, String position) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = criteriaQuery.from(User.class);

        criteriaQuery.where(userRoot.get("office").in(office));

        if (country != null) {
            criteriaQuery.where(userRoot.get("country").in(country));
        }

        if (docType != null) {
            Join <User, Document> docs = userRoot.join("document", JoinType.LEFT);
            criteriaQuery.select(userRoot);
            criteriaQuery.where(docs.get("docType").in(docType));
        }

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

        criteriaQuery.orderBy(criteriaBuilder.asc(userRoot.get("firstName")));

        TypedQuery<User> query = em.createQuery(criteriaQuery);

        return query.getResultList().stream().collect(Collectors.toSet());

    };

    /**
     * Получить список пользователей по ссылке на офис
     *
     * @param office
     * @return {@Set<User>}
     */

    @Override
    public Set<User> loadByOffice(Office office) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        criteriaQuery.where(userRoot.get("office").in(office));
        TypedQuery<User> query = em.createQuery(criteriaQuery);

        return query.getResultList().stream().collect(Collectors.toSet());
    }

    /**
     * Найти пользователя по Id
     *
     * @param id
     * @return {@Set<User>}
     */
    @Override
    public User loadById(Long id) {
        return em.find(User.class, id);
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

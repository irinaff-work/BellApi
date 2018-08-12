package ru.bellintegrator.practice.dictionary.dao;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionary.model.DocType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class DocTypeDaoImpl implements DocTypeDao {

    private final EntityManager em;

    @Autowired
    public DocTypeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DocType findByDocName(String docName) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteriaQuery = criteriaBuilder.createQuery(DocType.class);
        Root<DocType> docTypeRoot = criteriaQuery.from(DocType.class);

        criteriaQuery.where(docTypeRoot.get("docName").in(docName));
        TypedQuery<DocType> query = em.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    /**
     * @inheritDoc
     */
    public DocType findByDocCode(String docCode, String docName) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteriaQuery = criteriaBuilder.createQuery(DocType.class);
        Root<DocType> docTypeRoot = criteriaQuery.from(DocType.class);

        criteriaQuery.where(docTypeRoot.get("docCode").in(docCode));

        if (!Strings.isNullOrEmpty(docName)) {
            criteriaQuery.where(docTypeRoot.get("docName").in(docName));
        }

        TypedQuery<DocType> query = em.createQuery(criteriaQuery);

        return query.getSingleResult();
    }
}

package ru.bellintegrator.practice.docType.dao;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.docType.model.DocType;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.stream.Collectors;

@Repository
public class DocTypeDaoImpl implements DocTypeDao {

    private final EntityManager em;

    @Autowired
    public DocTypeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * получить ссылку на тип документа по docName
     *
     * @param docName
     * @return {@DocType}
     */
    @Override
    public DocType findByDocName(String docName) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteriaQuery = criteriaBuilder.createQuery(DocType.class);
        Root<DocType> docTypeRoot = criteriaQuery.from(DocType.class);

        criteriaQuery.where(docTypeRoot.get("doc_name").in(docName));
        TypedQuery<DocType> query = em.createQuery(criteriaQuery);

        return query.getSingleResult();
    };
}

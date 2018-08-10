package ru.bellintegrator.practice.document.dao;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionary.model.DocType;
import ru.bellintegrator.practice.document.model.Document;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * {@inheritDoc}
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {


    private final EntityManager em;

    @Autowired
    public DocumentDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Document findDocument(DocType docType, String docNumber, String docDate) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Document> criteriaQuery = criteriaBuilder.createQuery(Document.class);
        Root<Document> documentRoot = criteriaQuery.from(Document.class);

        criteriaQuery.where(documentRoot.get("doc_type_id").in(docType.getId()));

        if (!Strings.isNullOrEmpty(docNumber)) {
            criteriaQuery.where(documentRoot.get("doc_number").in(docNumber));
        }

        if (!Strings.isNullOrEmpty(docDate)) {
            criteriaQuery.where(documentRoot.get("doc_date").in(docDate));
        }

        TypedQuery<Document> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public void save (Document document) {
        em.persist(document);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        Document document = em.find(Document.class, id);
        em.remove(document);
    }
}

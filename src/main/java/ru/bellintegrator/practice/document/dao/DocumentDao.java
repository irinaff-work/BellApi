package ru.bellintegrator.practice.document.dao;

import ru.bellintegrator.practice.docType.model.DocType;
import ru.bellintegrator.practice.document.model.Document;

public interface DocumentDao {

    /**
     * получить ссылку на документ по docType, docNumber и docDate
     *
     * @param docNumber
     * @return {Long}
     */
    public Document findDocument(DocType docType, String docNumber, String docDate);

    /**
     * добавить документ по docNumber и docDate
     *
     * @param document
     * @return {Long}
     */
    public void save (Document document);
}

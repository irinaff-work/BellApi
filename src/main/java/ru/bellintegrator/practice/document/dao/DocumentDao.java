package ru.bellintegrator.practice.document.dao;

import ru.bellintegrator.practice.document.model.Document;

public interface DocumentDao {

    /**
     * получить ссылку на документ по docType, docNumber и docDate
     *
     * @param docNumber
     * @return {Long}
     */
    public Long findDocument(Long docType, String docNumber, String docDate);

    /**
     * добавить документ по docNumber и docDate
     *
     * @param document
     * @return {Long}
     */
    public Long add (Document document);
}
